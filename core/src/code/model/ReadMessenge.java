package code.model;

import code.effect.EffectData;
import code.main.GameCanvas;
import code.network.GameService;
import code.network.MessageHandler;
import code.screen.MenuLogin;
import code.screen.Res;
import code.screen.SkillTemplate;
import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.ChangScr;
import code.screen.screen.CharSelectScr;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.InfoOtherCharScr;
import code.screen.screen.Khu;
import code.screen.screen.LoadingScr;
import code.screen.screen.Location;
import code.screen.screen.MainMenu;
import code.screen.screen.MenuSelectItem;
import code.screen.screen.ScreenClan;
import code.screen.screen.ServerListScr;
import code.screen.screen.SetInfoData;
import code.screen.screen.ShopHairScreen;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.Rms;
import lib.Session_ME;
import lib.Tilemap;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.Message;
import lib2.mFont;

public class ReadMessenge implements IActionListener {
    short idDialog;
    byte typeDialog;
    public short idNpcReceive = 5;
    public static short[] listIDChangeTreeJava = null;
    public static mVector allImageSaveRms = new mVector();
    public static byte versionImage = -1;
    public static int totalImg = 0;
    public static int allImage;
    LoadingScr loadImgScr = null;
    public static final byte LIST_PARTY = 0;
    public static final byte INVITE_MEMBER = 1;
    public static final byte ACCEPT_MEMBER = 2;
    public static final byte KICK_MEMBER = 3;
    public static final byte DEL_PARTY = 4;
    public static final byte OUT_PARTY = 5;

    public void onNEwAccount(Message msg) {
        try {
            String name = msg.reader().readUTF();
            String pass = msg.reader().readUTF();
            Rms.saveRMSString(Rms.User_Quick_Play, name);
            Rms.saveRMSString(Rms.Pass_Quick_Play, pass);
            this.savePass(name, pass);
            MainChar.newQuickSlot();
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public void savePass(String user, String pass) {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            dos.writeBoolean(true);
            dos.writeUTF(user);
            dos.writeUTF(pass);
            Rms.saveRMS(Rms.User_Pass, bo.toByteArray());
            dos.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void onsetInfoNPC_Server(Message msg) {
        try {
            int size = msg.reader().readByte();

            for (int k = 0; k < size; ++k) {
                String name = msg.reader().readUTF();
                String strTalk = msg.reader().readUTF();
                String cmdname = msg.reader().readUTF();
                short id = msg.reader().readShort();
                short x = msg.reader().readShort();
                short y = msg.reader().readShort();
                short xBlock = msg.reader().readShort();
                short yBlock = msg.reader().readShort();
                byte dx = msg.reader().readByte();
                byte dy = msg.reader().readByte();
                int lenght = msg.reader().readShort();
                byte[] data = new byte[lenght];

                for (int i = 0; i < lenght; ++i) {
                    data[i] = msg.reader().readByte();
                }

                short idimage = msg.reader().readShort();
                short idicon = msg.reader().readShort();
                byte typefocus = msg.reader().readByte();
                NPC npc = null;
                Actor acNPC = GameCanvas.gameScr.findActor(id, 2);
                if (acNPC != null) {
                    acNPC.setinfoNPC(id, x, y, name, idimage, data, idicon);
                } else {
                    npc = new NPC(id, x, y, name, idimage, data, idicon);
                    npc.setText(strTalk, cmdname);
                    npc.typeFocus = typefocus;
                }

                Tilemap.setArialBlock(xBlock, yBlock, dx, dy);

                for (int i = 0; i < GameCanvas.gameScr.actors.size(); ++i) {
                    Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
                    if (ac != null && ac.isNPC() && x == ac.x && y == ac.y) {
                        return;
                    }
                }

                GameCanvas.gameScr.actors.addElement(npc);
            }
        } catch (Exception var23) {
            mSystem.println("loi ham add npc " + var23.toString());
            var23.printStackTrace();
        }

    }

    public void onMenu_Option(Message msg) {
        try {
            byte typemenu = msg.reader().readByte();
            short idNPC = msg.reader().readShort();
            byte ID = msg.reader().readByte();
            byte length = msg.reader().readByte();
            mVector cmd = new mVector();

            for (int i = 0; i < length; ++i) {
                String cap = msg.reader().readUTF();
                short time = msg.reader().readShort();
                mCommand c = null;
                if (typemenu == 0) {
                    c = new mCommand(cap, GameCanvas.menu, typemenu == 0 ? -1 : 3, mSystem.currentTimeMillis() + (long) (time * 1000));
                }

                if (typemenu == 1) {
                    c = new mCommand(cap, GameCanvas.menu2, typemenu == 0 ? -1 : 3, mSystem.currentTimeMillis() + (long) (time * 1000));
                }

                cmd.addElement(c);
            }

            String infoShow = msg.reader().readUTF();
            short idBig = msg.reader().readShort();
            if (typemenu == 0) {
                GameCanvas.menu.startAt_MenuOption(cmd, ID, idNPC, infoShow, idBig);
            }

            if (typemenu == 1) {
                GameCanvas.menu2.startArt(cmd, 5, infoShow, ID, idNPC);
            }
        } catch (Exception var11) {
            mSystem.println("loi trong ham nhan me nu");
        }

    }

    public void onPopupServer(Message msg) {
        try {
            GameCanvas.endDlg();
            this.idDialog = msg.reader().readShort();
            this.typeDialog = msg.reader().readByte();
            String str = msg.reader().readUTF();
            mCommand yesAction = new mCommand("Ok", this, 0);
            mCommand noAction = new mCommand("Hủy", this, 1);
            GameCanvas.startYesNoDlg(str, yesAction, noAction);
        } catch (Exception var5) {
        }

    }

    public void addNotify(Message msg) {
        try {
            String textnotify = msg.reader().readUTF();
            byte type = msg.reader().readByte();
            if (!textnotify.equals("")) {
                GameCanvas.addNotify(textnotify, type);
                if (type == 1) {
                    GameCanvas.mevent.addEvent(T.tinden, (byte) 0, T.tinden + ": " + T.tinden, 0);
                    GameCanvas.msgchat.addNewChat(T.kenhTG, T.tinden + ": ", textnotify, (byte) 1, false);
                }
            }
        } catch (Exception var4) {
        }

    }

    public void TexBox_Server(Message msg) {
        try {
            GameCanvas.endDlg();
            short ID = msg.reader().readShort();
            byte typeDL = msg.reader().readByte();
            String str = msg.reader().readUTF();
            byte sizecmd = msg.reader().readByte();
            String[] minfo = new String[sizecmd];
            byte[] typeinPut = new byte[sizecmd];

            for (int i = 0; i < sizecmd; ++i) {
                minfo[i] = msg.reader().readUTF();
                typeinPut[i] = msg.reader().readByte();
            }

            String tilebox = msg.reader().readUTF();
            String drec = msg.reader().readUTF();
            GameCanvas.inputDlg.setInfo(minfo, str, typeinPut, 100, false, typeDL, ID, tilebox, drec);
            GameCanvas.inputDlg.show();
            GameCanvas.gameScr.hideGUI = 0;
            System.out.println("HIEN LEN NOI DUNG XAC THUC");
        } catch (Exception var10) {
        }

    }

    public void ongetCharList(Message msg) {
        try {
            byte clazz = msg.reader().readByte();
            short currentHead = msg.reader().readShort();
            short bodyStyle = msg.reader().readShort();
            short legStyle = msg.reader().readShort();
            short weaponStyle = msg.reader().readShort();
            byte type = msg.reader().readByte();
            if (type == 0) {
                GameScr.mainChar.clazz = clazz;
                short[] listpard = new short[]{currentHead, bodyStyle, legStyle, weaponStyle};
                GameScr.mainChar.setInfoWearing(listpard);
                GameScr.mainChar.currentHead = currentHead;
                GameScr.mainChar.bodyStyle = bodyStyle;
                GameScr.mainChar.legStyle = legStyle;
                GameScr.mainChar.weaponStyle = weaponStyle;
            }

            if (type == 1) {
                CharSelectScr.gI().switchToMe();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public void clearloadMap(Message msg) {
        try {
            int size = msg.reader().readByte();
            listIDChangeTreeJava = new short[size];

            for (int i = 0; i < size; ++i) {
                listIDChangeTreeJava[i] = msg.reader().readShort();
            }
        } catch (Exception var4) {
        }

        ChangScr loadingscreen = new ChangScr();
        loadingscreen.switchToMe(GameCanvas.gameScr);
        loadingscreen.isnextmap = false;
        GameScr.isnextmap = false;
        Tilemap.clear();
        GameScr.Bossintro = null;
        GameScr.isIntro = false;
        MainChar.blockkey = false;
        if (GameScr.vecCharintro != null) {
            GameScr.vecCharintro.removeAllElements();
        }

        GameCanvas.clearKeyHold();
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
        GameCanvas.currentDialog = null;
        if (GameCanvas.gameScr.actors != null) {
            GameCanvas.gameScr.actors.removeAllElements();
        }

        if (EffectManager.hiEffects != null) {
            EffectManager.hiEffects.removeAllElements();
        }

        if (EffectManager.lowEffects != null) {
            EffectManager.lowEffects.removeAllElements();
        }

        GameScr.nameMap = "";
        GameCanvas.gameScr.focusedActor = null;
        GameData.removeAllImgTree();
        GameData.listbyteData.clear();
        GameData.listImgIcon.clear();
        GameScr.imgBigMap = null;
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
        GameScr.arrowsUp.removeAllElements();
    }

    public void onMap(Message msg) {
        try {
            MessageHandler.countmsg.clear();
            GameCanvas.gameScr.idMapColor = -1;
            GameCanvas.gameScr.isCloud = false;
            GameCanvas.gameScr.vecCloud.removeAllElements();
            GameScr.effAnimate.removeAllElements();
            mSound.stopSoundAll();
            mSound.pauseCurMusic();
            GameCanvas.gameScr.vecCharParty.removeAllElements();
            GameScr.charcountdonw = null;
            GameCanvas.hideAllDialog();
            ChangScr loadingscreen = new ChangScr();
            loadingscreen.switchToMe(GameCanvas.gameScr);
            GameScr.isnextmap = false;
            Tilemap.clear();
            GameScr.Bossintro = null;
            GameScr.isIntro = false;
            MainChar.blockkey = false;
            if (GameScr.vecCharintro != null) {
                GameScr.vecCharintro.removeAllElements();
            }

            GameCanvas.clearKeyHold();
            MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
            GameCanvas.currentDialog = null;
            if (GameCanvas.gameScr.actors != null) {
                GameCanvas.gameScr.actors.removeAllElements();
            }

            if (EffectManager.hiEffects != null) {
                EffectManager.hiEffects.removeAllElements();
            }

            if (EffectManager.lowEffects != null) {
                EffectManager.lowEffects.removeAllElements();
            }

            GameScr.nameMap = "";
            GameCanvas.gameScr.focusedActor = null;
            short pMapid = msg.reader().readShort();
            GameScr.mainChar.x = (short) (msg.reader().readUnsignedByte() * 16);
            GameScr.mainChar.y = (short) (msg.reader().readUnsignedByte() * 16);
            GameScr.mainChar.xTo = GameScr.mainChar.x;
            GameScr.mainChar.yTo = GameScr.mainChar.y;
            GameCanvas.gameScr.beginAuto = false;
            GameScr.mainChar.setCanPaint(true);
            int idMapload = msg.reader().readShort();
            GameScr.nameMap = msg.reader().readUTF();
            int lenByte = msg.reader().readShort();
            byte[] datamap = null;
            if (lenByte > 0) {
                datamap = new byte[lenByte];
                msg.reader().read(datamap);
            }

            if (mSystem.isj2me) {
                Chunk.arr.clear();
            }

            Tilemap.loadMap(idMapload, datamap);
            GameScr.allLocation = null;
            byte nWayPoint = msg.reader().readByte();
            GameScr.allLocation = new Location[nWayPoint];

            for (int i = 0; i < nWayPoint; ++i) {
                Location loca = new Location();
                loca.x = msg.reader().readShort();
                loca.y = msg.reader().readShort();
                int size = 100;
                if (loca.x <= Tilemap.w * 16 / 2 && loca.y > size && loca.y < Tilemap.h * 16 - 100) {
                    loca.dis = 0;
                    loca.x2 = loca.x - 8;
                    loca.y2 = loca.y - 18;
                    loca.f = 0;
                    loca.vx = -1;
                } else if (loca.x > Tilemap.w * 16 / 2 && loca.y > size && loca.y < Tilemap.h * 16 - 100) {
                    loca.dis = 1;
                    loca.f = 1;
                    loca.x2 = loca.x + 8;
                    loca.y2 = loca.y - 18;
                    loca.vx = 1;
                } else if (loca.y < Tilemap.h * 16 / 2) {
                    loca.y -= 10;
                    loca.dis = 2;
                    loca.f = 2;
                    loca.x2 = loca.x;
                    loca.y2 = loca.y + 10;
                    loca.vy = -1;
                } else {
                    loca.dis = 3;
                    loca.f = 2;
                    loca.x2 = loca.x;
                    loca.y2 = loca.y - 20;
                    loca.vy = 1;
                }

                loca.nameMapOut = msg.reader().readUTF();
                GameScr.allLocation[i] = loca;
            }

            Tilemap.ismapLang = msg.reader().readBoolean();
            GameScr.arena = msg.reader().readUnsignedByte();
            GameData.removeAllImgTree();
            int allT = msg.reader().readByte();

            for (int i = 0; i < allT; ++i) {
                short id = msg.reader().readShort();
                int size = msg.reader().readShort();
                if (size > 0) {
                    byte[] data = new byte[size];
                    msg.reader().read(data);
                    GameData.setImgIcon(id, data);
                }
            }

            byte tam = Tilemap.sizeBigmap;
            Tilemap.sizeBigmap = msg.reader().readByte();
            Tilemap.sizeBigmap = tam;
            GameCanvas.gameScr.isStartAutoAttack = false;
            GameScr.mainChar.resetAllSkill();
            GameScr.mainChar.setState(0);
            GameScr.mainChar.posTransRoad = null;
            GameCanvas.gameScr.actors.addElement(GameScr.mainChar);
            int index = 0;
            if (GameScr.allLocation != null && GameScr.allLocation.length > 0 && !GameScr.isIntro && GameScr.lastMap != -1 && GameScr.lastMap != Tilemap.lv) {
                int min = 10000000;

                int mdir;
                for (mdir = 0; mdir < GameScr.allLocation.length; ++mdir) {
                    if (Utils.getDistance(GameScr.mainChar.x, GameScr.mainChar.y, GameScr.allLocation[mdir].x, GameScr.allLocation[mdir].y) < min) {
                        min = Utils.getDistance(GameScr.mainChar.x, GameScr.mainChar.y, GameScr.allLocation[mdir].x, GameScr.allLocation[mdir].y);
                        index = mdir;
                    }
                }

                mdir = GameScr.allLocation[index].dis;
                if (mdir == 0) {
                    GameScr.mainChar.dir = 3;
                } else if (mdir == 1) {
                    GameScr.mainChar.dir = 2;
                } else if (mdir == 2) {
                    GameScr.mainChar.dir = 0;
                } else {
                    GameScr.mainChar.dir = 1;
                }

                int xto = GameScr.mainChar.x;
                int yto = GameScr.mainChar.y;
                int var24;
                if (GameScr.mainChar.dir == 2) {
                    var24 = GameScr.mainChar.x - 16;
                } else if (GameScr.mainChar.dir == 3) {
                    var24 = GameScr.mainChar.x + 16;
                } else {
                    int var25;
                    if (GameScr.mainChar.dir == 1) {
                        var25 = GameScr.mainChar.y - 16;
                    } else if (GameScr.mainChar.dir == 0) {
                        var25 = GameScr.mainChar.y + 16;
                    }
                }
            }

            GameScr.lastMap = Tilemap.lv;
            loadingscreen.isnextmap = true;
            GameScr.isnextmap = true;
            GameScr.mainChar.sendMove = true;
            if (Tilemap.lv != 0) {
                if (Tilemap.idTile == 0 && Tilemap.lv != 56 && Tilemap.lv != 34) {
                    mSound.playMus(1, mSound.volumeMusic, true);
                }

                if (Tilemap.idTile == 5) {
                    mSound.playMus(2, mSound.volumeMusic, true);
                }

                if (Tilemap.idTile == 4) {
                    mSound.playMus(3, mSound.volumeMusic, true);
                }

                if (Tilemap.idTile == 3) {
                    mSound.playMus(4, mSound.volumeMusic, true);
                }

                if (Tilemap.idTile == 6) {
                    mSound.playMus(6, mSound.volumeMusic, true);
                }

                if (Tilemap.idTile == 0 && (Tilemap.lv == 56 || Tilemap.lv == 34 || Tilemap.lv == 58 || Tilemap.lv == 35)) {
                    mSound.playMus(5, mSound.volumeMusic, true);
                }
            }

            try {
                GameCanvas.gameScr.idMapColor = msg.reader().readInt();
                GameCanvas.gameScr.isCloud = msg.reader().readBoolean();
            } catch (Exception var15) {
                GameCanvas.gameScr.idMapColor = -1;
                GameCanvas.gameScr.isCloud = false;
            }

            if (GameCanvas.gameScr.isCloud) {
                GameCanvas.gameScr.createCloud();
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    public void onMove(Message msg) {
        try {
            byte cat = msg.reader().readByte();
            short type = msg.reader().readShort();
            short id = msg.reader().readShort();
            short x = msg.reader().readShort();
            short y = msg.reader().readShort();
            byte iskiller = msg.reader().readByte();
            byte colorname = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            if (ac != null) {
                if (ac.equals(GameScr.mainChar)) {
                    GameScr.mainChar.posTransRoad = null;
                }

                ac.moveTo(x, y);
                ac.setTypePK(iskiller);
            } else {
                ac = GameScr.CreateActor(cat, type, id, x, y, iskiller);
                if (ac != null && ac.catagory > 2) {
                    ac.setColorname(colorname);
                    ((Item) ac).name = msg.reader().readUTF();
                }

                if (ac != null) {
                    GameCanvas.gameScr.actors.addElement(ac);
                }

                if (type == 88 && ac != null && cat == 1) {
                    GameScr.Ghost = ac;
                    GameCanvas.gameScr.focusedActor = ac;
                }
            }

            ac = null;
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }

    public void onAttackMonster(Message msg) {
        try {
            short idAttacker = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findActor(idAttacker, 0);
            byte idskill = msg.reader().readByte();
            byte size = msg.reader().readByte();
            mVector target = new mVector();
            int[] arrdame = new int[size];

            int leftmp;
            int i;
            for (leftmp = 0; leftmp < size; ++leftmp) {
                short id = msg.reader().readShort();
                int dam = msg.reader().readInt();
                i = msg.reader().readInt();
                Actor mons = GameCanvas.gameScr.findMonsterByID(id);
                if (mons != null) {
                    mons.setHp(i);
                    target.addElement(mons);
                }

                arrdame[leftmp] = dam;
            }

            leftmp = msg.reader().readInt();
            int idcount = msg.reader().readByte();
            if (ac != null) {
                if (ac.ID != GameScr.mainChar.ID) {
                    mVector vec_skill = (mVector) GameCanvas.gameScr.ALL_SKILL.elementAt(ac.getClazz());
                    SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(idskill);
                    ac.startAttack(target, skill.idSkillCode, skill.idEffStartSkill, arrdame);
                } else if (ac.ID == GameScr.mainChar.ID) {
                    boolean isadd = false;
                    if (MainChar.itemOptionMainChar != null && MainChar.idItemOptioninVector != -1) {
                        ItemOption item = (ItemOption) GameScr.mainChar.options.elementAt(MainChar.idItemOptioninVector);
                        if (item != null) {
                            --item.value;
                            if (item.value <= 0) {
                                item.value = 0;
                                GameCanvas.gameScr.idIconX2 = -1;
                                MainChar.itemOptionMainChar = null;
                                MainChar.idItemOptioninVector = -1;
                            }
                        }
                    }

                    if (GameScr.mainChar.currentSkill != null && idcount == GameScr.mainChar.currentSkill.idEffServer) {
                        GameScr.mainChar.currentSkill.arrDame = arrdame;
                        isadd = true;
                        GameScr.mainChar.currentSkill.addDame();
                        if (GameScr.mainChar.currentSkill.isFlydame()) {
                            isadd = false;
                        }
                    }

                    if (GameScr.mainChar.clazz == Char.THIEU_LAM && idskill == 6) {
                        isadd = false;
                    }

                    if (!isadd) {
                        for (i = 0; i < target.size(); ++i) {
                            Actor ac1 = (Actor) target.elementAt(i);
                            if (ac1 != null) {
                                GameCanvas.gameScr.startFlyText("- " + arrdame[i], 0, ac1.x, ac1.y - 40, 1, -2);
                            }
                        }
                    }

                    if (target.size() > 0) {
                        GameScr.mainChar.mp = leftmp;
                    }
                }
            }
        } catch (Exception var13) {
        }

    }

    public void onAttackPlayer(Message msg) {
        try {
            short idAttacker = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findActor(idAttacker, 0);
            byte idskill = msg.reader().readByte();
            byte size = msg.reader().readByte();
            mVector target = new mVector();
            int dam = 0;
            System.out.println("ng tan cong: " + idAttacker);
            int[] arrdame = new int[size];

            int charHP;
            for (charHP = 0; charHP < size; ++charHP) {
                short id = msg.reader().readShort();
                dam = msg.reader().readInt();
                int leftHp = msg.reader().readInt();
                Actor mons = GameCanvas.gameScr.findActor(id, 0);
                if (mons != null) {
                    mons.setHp(leftHp);
                    mons.setIsCatSkill(true);
                    target.addElement(mons);
                }

                arrdame[charHP] = dam;
            }

            charHP = msg.reader().readInt();
            int charMP = msg.reader().readInt();
            int idcount = msg.reader().readByte();
            if (ac != null) {
                if (ac.ID != GameScr.mainChar.ID) {
                    mVector vec_skill = (mVector) GameCanvas.gameScr.ALL_SKILL.elementAt(ac.getClazz());
                    SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(idskill);
                    ac.startAttack(target, skill.idSkillCode, skill.idEffStartSkill, arrdame);
                } else if (ac.ID == GameScr.mainChar.ID && target.size() > 0) {
                    GameScr.mainChar.hp = charHP;
                    GameScr.mainChar.mp = charMP;
                    boolean isadd = false;
                    if (GameScr.mainChar.currentSkill != null && idcount == GameScr.mainChar.currentSkill.idEffServer) {
                        GameScr.mainChar.currentSkill.arrDame = arrdame;
                        isadd = true;
                        GameScr.mainChar.currentSkill.addDame();
                        if (GameScr.mainChar.currentSkill.isFlydame()) {
                            isadd = false;
                        }
                    }

                    if (!isadd) {
                        for (int i = 0; i < target.size(); ++i) {
                            Actor ac1 = (Actor) target.elementAt(i);
                            if (ac1 != null) {
                                GameCanvas.gameScr.startFlyText("- " + arrdame[i], 0, ac1.x, ac1.y - 40, 1, -2);
                            }
                        }
                    }
                }
            }
        } catch (Exception var15) {
        }

    }

    public void onMonsterAttackPlayer(Message msg) {
        try {
            byte isAttack = msg.reader().readByte();
            short Attacker = msg.reader().readShort();
            if (isAttack == 1) {
                int hp = msg.reader().readInt();
                byte targetcat = msg.reader().readByte();
                byte numberAttacked = msg.reader().readByte();
                mVector vecTarget = new mVector();
                int[] dame = new int[numberAttacked];
                int i = 0;

                while (true) {
                    if (i >= numberAttacked) {
                        byte idskill = msg.reader().readByte();
                        Monster mt = GameCanvas.gameScr.findMonsterByID(Attacker);
                        if (mt != null) {
                            mt.setmuntiTarget(vecTarget);
                            mt.startAttack(idskill);
                            mt.setFlyDame(dame);
                        }
                        break;
                    }

                    short Attacked = msg.reader().readShort();
                    int hplose = msg.reader().readInt();
                    int hpcon = msg.reader().readInt();
                    Actor ac = GameCanvas.gameScr.findActor(Attacked, targetcat);
                    if (ac != null) {
                        ac.setHp(hpcon);
                        if (ac.equals(GameScr.mainChar) && hplose > 0) {
                            GameCanvas.gameScr.tickHP = 2;
                            GameCanvas.gameScr.inDexHP = 2;
                        }

                        if (hplose > 0) {
                            dame[i] = hplose;
                        }

                        vecTarget.addElement(ac);
                    }

                    ++i;
                }
            }

            if (isAttack == 0) {
                Monster mt = GameCanvas.gameScr.findMonsterByID(Attacker);
                if (mt != null) {
                    mt.comehome();
                }
            }
        } catch (Exception var14) {
        }

    }

    public void onCharInventory(Message m) {
        byte type = -1;
        try {
            type = m.reader().readByte();
            if (type == 0) {
                short size = m.reader().readShort();
                mVector vitem = new mVector();
                for (int i = 0; i < size; i++) {
                    Item it0 = new Item();
                    it0.ID = m.reader().readShort();
                    it0.catagory = m.reader().readByte();
                    it0.level = m.reader().readShort();
                    it0.charClazz = m.reader().readByte();
                    it0.name = m.reader().readUTF();
                    it0.lock = m.reader().readByte();
                    it0.plus = m.reader().readByte();
                    it0.idIcon = m.reader().readShort();
                    it0.colorname = m.reader().readByte();
                    it0.cantrade = m.reader().readBoolean();
                    it0.cansell = m.reader().readBoolean();
                    it0.priceShop = m.reader().readInt();
                    it0.quantity = m.reader().readInt();
                    int nOption = m.reader().readByte();
                    for (int j = 0; j < nOption; j++) {
                        ItemOption itoption = new ItemOption();
                        itoption.id = m.reader().readShort();
                        itoption.idColor = m.reader().readByte();
                        itoption.value = m.reader().readInt();
                        itoption.value2 = m.reader().readInt();
                        it0.options.addElement(itoption);
                    }
                    it0.type = m.reader().readByte();
                    vitem.addElement(it0);
                }
                GameScr.mainChar.charXu = m.reader().readLong();
                GameScr.mainChar.luong = m.reader().readInt();
                GameScr.mainChar.luongKhoa = m.reader().readInt();
                GameScr.mainChar.updateAllitemInInventory(vitem);
            } else if (type == 1) {
                Item it0 = new Item();
                it0.ID = m.reader().readShort();
                it0.catagory = m.reader().readByte();
                it0.level = m.reader().readShort();
                it0.charClazz = m.reader().readByte();
                it0.name = m.reader().readUTF();
                it0.lock = m.reader().readByte();
                it0.plus = m.reader().readByte();
                it0.idIcon = m.reader().readShort();
                it0.colorname = m.reader().readByte();
                it0.cantrade = m.reader().readBoolean();
                it0.cansell = m.reader().readBoolean();
                it0.priceShop = m.reader().readInt();
                it0.quantity = m.reader().readInt();
                int nOption = m.reader().readByte();
                for (int j = 0; j < nOption; j++) {
                    ItemOption itoption = new ItemOption();
                    itoption.id = m.reader().readShort();
                    itoption.idColor = m.reader().readByte();
                    itoption.value = m.reader().readInt();
                    itoption.value2 = m.reader().readInt();
                    it0.options.addElement(itoption);
                }
                it0.type = m.reader().readByte();
                GameScr.mainChar.addnewItem(it0);
            } else if (type == 2) {
                Item it0 = new Item();
                it0.ID = m.reader().readShort();
                it0.catagory = m.reader().readByte();
                it0.level = m.reader().readShort();
                it0.charClazz = m.reader().readByte();
                it0.name = m.reader().readUTF();
                it0.lock = m.reader().readByte();
                it0.plus = m.reader().readByte();
                it0.idIcon = m.reader().readShort();
                it0.colorname = m.reader().readByte();
                it0.cantrade = m.reader().readBoolean();
                it0.cansell = m.reader().readBoolean();
                it0.priceShop = m.reader().readInt();
                it0.quantity = m.reader().readInt();
                int nOption = m.reader().readByte();
                for (int j = 0; j < nOption; j++) {
                    ItemOption itoption = new ItemOption();
                    itoption.id = m.reader().readShort();
                    itoption.idColor = m.reader().readByte();
                    itoption.value = m.reader().readInt();
                    itoption.value2 = m.reader().readInt();
                    it0.options.addElement(itoption);
                }
                it0.type = m.reader().readByte();
                GameScr.mainChar.updateItemInventory(it0);
            } else if (type == 3) {
                short id = m.reader().readByte();
                byte cat = m.reader().readByte();
                GameScr.mainChar.deleteItem(id, cat);
                GameScr.mainChar.charXu = m.reader().readLong();
                GameScr.mainChar.luong = m.reader().readInt();
                GameScr.mainChar.luongKhoa = m.reader().readInt();
                MainMenu.gI().RestItemHanhTrang();
            } else if (type == 4) {
                GameScr.mainChar.charXu = m.reader().readLong();
                GameScr.mainChar.luong = m.reader().readInt();
                GameScr.mainChar.luongKhoa = m.reader().readInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActorDie(Message msg) {
        try {
            int actorID = msg.reader().readShort();
            int cat = msg.reader().readByte();
            int size0 = GameCanvas.gameScr.actors.size();
            Actor ac = null;

            for (int i = 0; i < size0; ++i) {
                ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
                if (ac.ID == actorID && ac.catagory == cat) {
                    if (cat == 1) {
                        ac.dieActor((Actor) null);
                    } else if (cat == 0) {
                        GameCanvas.gameScr.isStartAutoAttack = false;
                        ac.actorDie();
                    } else {
                        ac.wantDestroy = true;
                    }
                    break;
                }
            }
        } catch (Exception var7) {
            Cout.println("LOI TRONG HAM ONACTORDIE TRONG GAMESCR");
        }

    }

    public void onMonsterInfo(Message msg) {
        try {
            MonsterInfo monsterInfo = new MonsterInfo();
            monsterInfo.id = msg.reader().readShort();
            monsterInfo.x = msg.reader().readShort();
            monsterInfo.y = msg.reader().readShort();
            monsterInfo.default_x = msg.reader().readShort();
            monsterInfo.default_y = msg.reader().readShort();
            monsterInfo.hp = msg.reader().readInt();
            monsterInfo.lv = msg.reader().readByte();
            monsterInfo.he = msg.reader().readByte();
            monsterInfo.maxhp = msg.reader().readInt();
            monsterInfo.timeLive = msg.reader().readInt();
            monsterInfo.canFocus = msg.reader().readBoolean();
            monsterInfo.colorName = msg.reader().readByte();
            boolean beFire = msg.reader().readBoolean();
            monsterInfo.beFire = beFire;

            try {
                monsterInfo.totalWay = msg.reader().readByte();
                monsterInfo.dyPaintPk = msg.reader().readShort();
                monsterInfo.nameTieu = msg.reader().readUTF();
            } catch (Exception var5) {
            }

            GameCanvas.gameScr.onMonsterInfo(monsterInfo);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public void onMainCharInfo(Message msg) {
        try {
            GameCanvas.gameScr.idIconX2 = -1;
            MainChar.itemOptionMainChar = null;
            MainChar.idItemOptioninVector = -1;
            GameScr.mainChar.ID = msg.reader().readShort();
            GameScr.mainChar.name = msg.reader().readUTF();
            Rms.saveMainCharName();
            GameScr.mainChar.hp = msg.reader().readInt();
            GameScr.mainChar.maxhp = msg.reader().readInt();
            GameScr.mainChar.mp = msg.reader().readInt();
            GameScr.mainChar.maxmp = msg.reader().readInt();
            GameScr.mainChar.clazz = msg.reader().readByte();
            GameScr.mainChar.options.removeAllElements();
            byte nOption = msg.reader().readByte();

            for (int i = 0; i < nOption; ++i) {
                ItemOption itop = new ItemOption();
                itop.id = msg.reader().readShort();
                itop.idColor = msg.reader().readByte();
                itop.value = msg.reader().readInt();
                itop.value2 = msg.reader().readInt();
                GameScr.mainChar.options.addElement(itop);
                if (GameCanvas.gameScr.idIconX2 == -1 && itop.value > 0) {
                    if (itop.id == 86) {
                        GameCanvas.gameScr.idIconX2 = (short) (318 + Res.ID_ITEM);
                        MainChar.itemOptionMainChar = itop;
                        MainChar.idItemOptioninVector = (short) i;
                    } else if (itop.id == 87) {
                        GameCanvas.gameScr.idIconX2 = (short) (319 + Res.ID_ITEM);
                        MainChar.itemOptionMainChar = itop;
                        MainChar.idItemOptioninVector = (short) i;
                    } else if (itop.id == 88) {
                        GameCanvas.gameScr.idIconX2 = (short) (320 + Res.ID_ITEM);
                        MainChar.itemOptionMainChar = itop;
                        MainChar.idItemOptioninVector = (short) i;
                    }
                }
            }

            byte sizeLvSkill = msg.reader().readByte();
            System.out.println("set level Skill 1");
            Char.levelSkill = new byte[sizeLvSkill];
            Char.Max_Skill_Learn = 0;

            for (int i = 0; i < sizeLvSkill; ++i) {
                Char.levelSkill[i] = msg.reader().readByte();
                if (Char.levelSkill[i] > 0) {
                    ++Char.Max_Skill_Learn;
                }
            }

            GameScr.mainChar.level = msg.reader().readShort();
            GameScr.mainChar.lvpercent = msg.reader().readShort();
            byte size = msg.reader().readByte();
            Item.moneyType = new String[size];

            for (int i = 0; i < size; ++i) {
                Item.moneyType[i] = msg.reader().readUTF();
            }

            MainChar.MaxInven = msg.reader().readByte();
            Char.Diemtiemnang = msg.reader().readShort();
            Char.Skill_Point = msg.reader().readShort();
            Char.sucmanh = msg.reader().readShort();
            Char.linhkhi = msg.reader().readShort();
            Char.sinhkhi = msg.reader().readShort();
            Char.thanphap = msg.reader().readShort();
            GameScr.mainChar.idNhom = msg.reader().readShort();
            short sizeGhost = msg.reader().readShort();
            if (sizeGhost > 0) {
                byte[] imgArr = new byte[sizeGhost];

                for (int j = 0; j < sizeGhost; ++j) {
                    imgArr[j] = msg.reader().readByte();
                }

                GameScr.imgGhost = Image.createImage((byte[]) imgArr, 0, imgArr.length);
            }

            GameScr.isGost = false;
            if (sizeGhost > 0) {
                GameScr.isGost = true;
            }

            if (!GameScr.isGost) {
                GameScr.Ghost = null;
            }

            GameScr.numMSG = 0;
            byte speed = msg.reader().readByte();
            GameScr.mainChar.speed = speed;
            byte sizeAuto = msg.reader().readByte();

            byte totalQuickLot;
            for (int i = 0; i < sizeAuto; ++i) {
                totalQuickLot = msg.reader().readByte();
                MenuSelectItem.FocusAutoNhat[totalQuickLot] = true;
                MenuSelectItem.isAutoNhat[totalQuickLot] = true;
            }

            GameCanvas.currentScreen.doChangeInfo(false);
            GameScr.mainChar.idClan = msg.reader().readShort();
            GameScr.mainChar.idIconClan = msg.reader().readShort();
            GameScr.mainChar.chuc_vu_clan = msg.reader().readByte();
            GameScr.mainChar.aliasNameClan = msg.reader().readUTF();
            byte noptionClan = msg.reader().readByte();
            MainChar.infoOptionClan = new String[noptionClan];

            for (int i = 0; i < noptionClan; ++i) {
                MainChar.infoOptionClan[i] = msg.reader().readUTF();
            }

            if (!MenuLogin.isLoadQL) {
                MainChar.loadQuickSlot();
                MenuLogin.isLoadQL = true;
            }

            totalQuickLot = msg.reader().readByte();

            for (int i = 0; i < totalQuickLot; ++i) {
                byte idSkill = msg.reader().readByte();
                if (idSkill > -1) {
                    if (MainChar.mQuickslot[i] == null) {
                        MainChar.mQuickslot[i] = new QuickSlot(i);
                    }

                    MainChar.CheckQuicSlotSkill(idSkill);
                    SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(idSkill);
                    boolean isbuff = false;
                    if (skill != null && skill.type == SkillTemplate.TYPE_BUFF) {
                        isbuff = true;
                    }

                    MainChar.mQuickslot[i].setIsSkill(idSkill, isbuff);
                }
            }

            GameService.gI().doSendChangeQuickSlot();
            short idBot = msg.reader().readShort();
            GameScr.mainChar.idBot = idBot;
            boolean isphihanh = false;

            try {
                isphihanh = msg.reader().readBoolean();
            } catch (Exception var24) {
                isphihanh = false;
            }

            GameScr.mainChar.setPhihanh(isphihanh);
            boolean var36 = true;

            short idpet;
            try {
                idpet = msg.reader().readShort();
            } catch (Exception var23) {
                idpet = -1;
            }

            Actor pet2 = GameScr.isHavePet(GameScr.mainChar.ID);
            if (idpet != -1) {
                if (pet2 == null) {
                    Pet p = new Pet(GameScr.mainChar, idpet);
                    GameCanvas.gameScr.actors.addElement(p);
                } else {
                    pet2.setidTemplatePet(idpet);
                }
            } else if (pet2 != null) {
                GameCanvas.gameScr.actors.removeElement(pet2);
            }

            boolean var39 = true;

            short idPhiPhong;
            try {
                idPhiPhong = msg.reader().readShort();
            } catch (Exception var22) {
                idPhiPhong = -1;
            }

            GameScr.mainChar.setPhiPhong(idPhiPhong);
            GameScr.mainChar.dxyPhiphong = new byte[msg.reader().readByte()];
            msg.reader().read(GameScr.mainChar.dxyPhiphong);
            byte isauto = msg.reader().readByte();
            GameCanvas.gameScr.checkAutoAttack(isauto);
            short idHorse = msg.reader().readShort();
            GameScr.mainChar.setHorse(idHorse);
            byte[] dxh = new byte[msg.reader().readByte()];
            if (dxh.length > 0) {
                msg.reader().read(dxh);

                for (int i = 0; i < dxh.length; i += 2) {
                    GameScr.mainChar.dxHorse[dxh[i]] = dxh[i + 1];
                }
            }

            byte[] dyh = new byte[msg.reader().readByte()];
            if (dyh.length > 0) {
                msg.reader().read(dyh);

                for (int i = 0; i < dyh.length; i += 2) {
                    GameScr.mainChar.dyHorse[dyh[i]] = dyh[i + 1];
                }
            }

            boolean var42 = true;

            short idhead;
            try {
                idhead = msg.reader().readShort();
            } catch (Exception var21) {
                idhead = -1;
            }

            GameScr.mainChar.setidHead(idhead);
        } catch (Exception var25) {
        }

    }

    public void onCharInfo(Message msg) {
        try {
            short id = msg.reader().readShort();
            Char ac = (Char) GameCanvas.gameScr.findActor(id, 0);
            if (ac != null) {
                ac.name = msg.reader().readUTF();
                ac.hp = msg.reader().readInt();
                ac.maxhp = msg.reader().readInt();
                ac.clazz = msg.reader().readByte();
                short[] listpart = new short[msg.reader().readByte()];

                for (int i = 0; i < listpart.length; ++i) {
                    listpart[i] = msg.reader().readShort();
                }

                ac.idBot = msg.reader().readShort();
                ac.mp = msg.reader().readInt();
                ac.maxmp = msg.reader().readInt();
                ac.setInfoWearing(listpart);
                boolean canFire = msg.reader().readBoolean();
                ac.setCanFocus(canFire);
                byte typePK = msg.reader().readByte();
                short idNhom = msg.reader().readShort();
                short lv = msg.reader().readShort();
                boolean beFire = msg.reader().readBoolean();
                ac.SetbeFire(beFire);
                ac.level = lv;
                ac.setidNhom(idNhom);
                if (idNhom != -1) {
                    boolean canadd = true;

                    for (int i = 0; i < GameCanvas.gameScr.vecCharParty.size(); ++i) {
                        Actor actv = (Actor) GameCanvas.gameScr.vecCharParty.elementAt(i);
                        if (actv != null && actv.equals(ac)) {
                            canadd = false;
                            break;
                        }
                    }

                    if (canadd) {
                        GameCanvas.gameScr.vecCharParty.addElement(ac);
                    }
                }

                if (idNhom == -1 && GameCanvas.gameScr.vecCharParty.size() > 0) {
                    for (int i = 0; i < GameCanvas.gameScr.vecCharParty.size(); ++i) {
                        Actor act = (Actor) GameCanvas.gameScr.vecCharParty.elementAt(i);
                        if (act != null && act != null && act.ID == ac.ID) {
                            GameCanvas.gameScr.vecCharParty.removeElement(act);
                        }
                    }
                }

                ac.setTypePK(typePK);
                ac.setCanFocus(canFire);
                byte speed = msg.reader().readByte();
                ac.speed = speed;
                ac.setCanPaint(true);
                ac.idClan = msg.reader().readShort();
                ac.idIconClan = msg.reader().readShort();
                ac.chuc_vu_clan = msg.reader().readByte();
                ac.aliasNameClan = msg.reader().readUTF();
                boolean isphihanh = false;

                try {
                    isphihanh = msg.reader().readBoolean();
                } catch (Exception var23) {
                    isphihanh = false;
                }

                ac.setPhihanh(isphihanh);
                boolean var30 = true;

                short idpet;
                try {
                    idpet = msg.reader().readShort();
                } catch (Exception var22) {
                    idpet = -1;
                }

                Actor pet2 = GameScr.isHavePet(ac.ID);
                if (idpet != -1) {
                    if (pet2 == null) {
                        Pet p = new Pet(ac, idpet);
                        GameCanvas.gameScr.actors.addElement(p);
                    } else {
                        pet2.setidTemplatePet(idpet);
                    }
                } else if (pet2 != null) {
                    GameCanvas.gameScr.actors.removeElement(pet2);
                }

                boolean var32 = true;

                short idPhiPhong;
                try {
                    idPhiPhong = msg.reader().readShort();
                } catch (Exception var21) {
                    idPhiPhong = -1;
                }

                ac.setPhiPhong(idPhiPhong);
                ac.dxyPhiphong = new byte[msg.reader().readByte()];
                msg.reader().read(ac.dxyPhiphong);
                short idHorse = msg.reader().readShort();
                ac.setHorse(idHorse);
                byte[] dxh = new byte[msg.reader().readByte()];
                if (dxh.length > 0) {
                    msg.reader().read(dxh);

                    for (int i = 0; i < dxh.length; i += 2) {
                        ac.dxHorse[dxh[i]] = dxh[i + 1];
                    }
                }

                byte[] dyh = new byte[msg.reader().readByte()];
                if (dyh.length > 0) {
                    msg.reader().read(dyh);

                    for (int i = 0; i < dyh.length; i += 2) {
                        ac.dyHorse[dyh[i]] = dyh[i + 1];
                    }
                }

                boolean var35 = true;

                short idhead;
                try {
                    idhead = msg.reader().readShort();
                } catch (Exception var20) {
                    idhead = -1;
                }

                ac.setidHead(idhead);
            }
        } catch (Exception var24) {
        }

    }

    public void ongetInfoNPC(Message m) {
        try {
            short idNPC = m.reader().readShort();
            byte cat = m.reader().readByte();
            if (idNPC != -126) {
                String nameShop = m.reader().readUTF();
                byte showInven = m.reader().readByte();
                String[] tile;
                if (showInven == 1) {
                    tile = new String[]{nameShop, T.hanhtrang};
                } else {
                    tile = new String[]{nameShop};
                }

                byte numTab = m.reader().readByte();

                for (int k = 0; k < numTab; ++k) {
                    short size = m.reader().readShort();
                    mVector vitem = new mVector();

                    for (int i = 0; i < size; ++i) {
                        Item it0 = new Item();
                        it0.isItemShop = true;
                        it0.ID = m.reader().readShort();
                        it0.catagory = m.reader().readByte();
                        it0.level = m.reader().readShort();
                        it0.name = m.reader().readUTF();
                        it0.charClazz = m.reader().readByte();
                        it0.lock = m.reader().readByte();
                        it0.plus = m.reader().readByte();
                        it0.idIcon = m.reader().readShort();
                        it0.colorname = m.reader().readByte();
                        it0.cantrade = m.reader().readBoolean();
                        it0.cansell = m.reader().readBoolean();
                        it0.priceShop = m.reader().readInt();
                        it0.quantity = m.reader().readInt();
                        it0.priceType = m.reader().readByte();
                        int nOption = m.reader().readByte();

                        for (int j = 0; j < nOption; ++j) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = m.reader().readShort();
                            itoption.idColor = m.reader().readByte();
                            itoption.value = m.reader().readInt();
                            itoption.value2 = m.reader().readInt();
                            it0.options.addElement(itoption);
                        }

                        it0.type = m.reader().readByte();
                        vitem.addElement(it0);
                    }

                    MainMenu.gI().PutItemSHop(vitem);
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    MainMenu.gI().setMainTabSelect(tile);
                    MainMenu.gI().numtab = tile.length;
                    if (!GameCanvas.isTouch) {
                        MainMenu.gI().left = MainMenu.cmdBuy;
                    }

                    MainMenu.gI().idNPC_Shop = idNPC;
                    MainMenu.gI().catNPC_Shop = cat;
                }
            }

            if (idNPC == -126) {
                GameScr.shop.removeAllElements();
                byte numTab = m.reader().readByte();
                GameScr.idNPCshopInven = idNPC;
                GameScr.catNPCshopInven = cat;

                for (int k = 0; k < numTab; ++k) {
                    short size = m.reader().readShort();

                    for (int i = 0; i < size; ++i) {
                        Item it0 = new Item();
                        it0.isItemShop = true;
                        it0.ID = m.reader().readShort();
                        it0.catagory = m.reader().readByte();
                        it0.level = m.reader().readShort();
                        it0.name = m.reader().readUTF();
                        it0.charClazz = m.reader().readByte();
                        it0.lock = m.reader().readByte();
                        it0.plus = m.reader().readByte();
                        it0.idIcon = m.reader().readShort();
                        it0.colorname = m.reader().readByte();
                        it0.cantrade = m.reader().readBoolean();
                        it0.cansell = m.reader().readBoolean();
                        it0.priceShop = m.reader().readInt();
                        it0.quantity = m.reader().readInt();
                        it0.priceType = m.reader().readByte();
                        int nOption = m.reader().readByte();

                        for (int j = 0; j < nOption; ++j) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = m.reader().readShort();
                            itoption.idColor = m.reader().readByte();
                            itoption.value = m.reader().readInt();
                            itoption.value2 = m.reader().readInt();
                            it0.options.addElement(itoption);
                        }

                        it0.type = m.reader().readByte();
                        GameScr.shop.addElement(it0);
                    }
                }
            }

            MainMenu.captionServer = m.reader().readUTF();
            MainMenu.infoBuySellServer = m.reader().readUTF();
            if (!MainMenu.captionServer.equals("")) {
                MainMenu.cmdBuy.caption = MainMenu.captionServer;
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    public void receiveQuest(Message m) {
        try {
            byte type_Group_Quest = m.reader().readByte();
            byte size = m.reader().readByte();
            if (type_Group_Quest == 2) {
                GameScr.allQuestWorking.removeAllElements();
                GameScr.hashQuestWorking.clear();
            }
            if (type_Group_Quest == 1) {
                GameScr.allQuestFinish.removeAllElements();
                GameScr.hashQuestFinish.clear();
            }
            if (type_Group_Quest == 0) {
                GameScr.allQuestCanReceive.removeAllElements();
                GameScr.hashQuestCanReceive.clear();
            }
            for (int i = 0; i < size; i++) {
                String data[], noidung;
                int k, idQuest = m.reader().readShort();
                Quest q = new Quest(idQuest);
                q.stateQuest = type_Group_Quest;
                q.main_sub = m.reader().readByte();
                q.name = m.reader().readUTF();
                switch (type_Group_Quest) {
                    case 0:
                        q.idNpcReceive = m.reader().readByte();
                        GameScr.hashQuestCanReceive.put((new StringBuilder(String.valueOf(q.idNpcReceive))).toString(), "0");
                        noidung = m.reader().readUTF();
                        data = Util.split(noidung, ">");
                        for (k = 0; k < data.length; k++)
                            q.content.addElement(data[k]);
                        q.type = m.reader().readByte();
                        q.deltaLv = m.reader().readByte();
                        GameScr.allQuestCanReceive.addElement(q);
                        break;
                    case 1:
                        q.idNpcResponse = m.reader().readByte();
                        GameScr.hashQuestFinish.put((new StringBuilder(String.valueOf(q.idNpcResponse))).toString(), "1");
                        data = Util.split(m.reader().readUTF(), ">");
                        for (k = 0; k < data.length; k++)
                            q.rescontent.addElement(data[k]);
                        q.decript = m.reader().readUTF();
                        m.reader().readUTF();
                        GameScr.allQuestFinish.addElement(q);
                        break;
                    case 2:
                        q.type = m.reader().readByte();
                        q.decript = m.reader().readUTF();
                        q.idNpcResponse = m.reader().readByte();
                        GameScr.hashQuestWorking.put((new StringBuilder(String.valueOf(q.idNpcResponse))).toString(), "2");
                        q.deltaLv = m.reader().readByte();
                        m.reader().readUTF();
                        if (q.type == 2) {
                            int nitemGet = m.reader().readByte();
                            for (int n = 0; n < nitemGet; n++) {
                                short iditem = m.reader().readShort();
                                short itemGot = m.reader().readShort();
                                short s1 = m.reader().readShort();
                            }
                        } else if (q.type == 0) {
                            int nmonkil = m.reader().readByte();
                            for (int n = 0; n < nmonkil; n++) {
                                short iditem = m.reader().readShort();
                                short itemGot = m.reader().readShort();
                                short s1 = m.reader().readShort();
                            }
                        } else if (q.type == 4) {
                            m.reader().readShort();
                            m.reader().readShort();
                        }
                        GameScr.allQuestWorking.addElement(q);
                        break;
                }
                if (type_Group_Quest != 1) if (q.main_sub == 0) {
                    GameScr.mainQuest = q;
                } else if (q.main_sub == 1) {
                    GameScr.subQuest = q;
                } else if (q.main_sub == 2) {
                    GameScr.clanQuest = q;
                }
            }
            if (type_Group_Quest == 1 && GameScr.isSendFinishQuest) {
                try {
                    GameCanvas.gameScr.QuestAgain();
                } catch (Exception exception) {
                }
                GameScr.isSendFinishQuest = false;
            }
            byte qsize = m.reader().readByte();
            MainMenu.QuestTile = new String[qsize];
            MainMenu.ListQuest = new mVector[qsize];
            for (int j = 0; j < qsize; j++) {
                MainMenu.ListQuest[j] = new mVector();
                MainMenu.QuestTile[j] = m.reader().readUTF();
                byte qsize2 = m.reader().readByte();
                for (int k = 0; k < qsize2; k++) {
                    QuestInfo q = new QuestInfo();
                    q.name = m.reader().readUTF();
                    q.info = m.reader().readUTF();
                    q.idMap = m.reader().readShort();
                    q.status = m.reader().readByte();
                    q.mainsub = m.reader().readByte();
                    q.idQuest = m.reader().readShort();
                    MainMenu.ListQuest[j].addElement(q);
                }
            }
            GameScr.checkQuest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onImageFromServer(Message msg) {
        try {
            short idImg3 = msg.reader().readShort();
            byte[] arrayImg1 = new byte[msg.reader().available()];
            msg.reader().read(arrayImg1);
            if (allImage > 0) {
                ++totalImg;
                if (totalImg >= allImage || totalImg * 100 / allImage >= 99) {
                    if (totalImg >= allImage) {
                        allImage = 0;
                        totalImg = 0;
                    }

                    Rms.saveRMSString("vsImg", String.valueOf(versionImage));
                    this.loadImgScr.isFinish = true;
                }

                Rms.saveRMS(String.valueOf(idImg3), arrayImg1);
            } else {
                ImageIcon img = (ImageIcon) GameData.listImgIcon.get("" + idImg3);
                if (mSystem.isAndroid || mSystem.isPC || mSystem.isIos || mSystem.isIosAppStore() || mSystem.isIP) {
                    Rms.saveRMS(String.valueOf(idImg3), arrayImg1);
                }

                if (img != null) {
                    img.isLoad = false;
                    img.img = Image.createImage((byte[]) arrayImg1, 0, arrayImg1.length);
                    if (img.img != null && idImg3 >= Res.ID_ITEM_MAP && idImg3 < Res.ID_START_SKILL) {
                        Res.maxHTree = img.img.getHeight() > Res.maxHTree ? img.img.getHeight() : Res.maxHTree;
                        Res.maxWTree = img.img.getWidth() > Res.maxWTree ? img.img.getWidth() : Res.maxWTree;
                    }
                } else {
                    img = new ImageIcon();
                    GameData.listImgIcon.put(String.valueOf(idImg3), img);
                    img.isLoad = false;
                    img.img = Image.createImage((byte[]) arrayImg1, 0, arrayImg1.length);
                    if (img.img != null && idImg3 >= Res.ID_ITEM_MAP && idImg3 < Res.ID_START_SKILL) {
                        Res.maxHTree = img.img.getHeight() > Res.maxHTree ? img.img.getHeight() : Res.maxHTree;
                        Res.maxWTree = img.img.getWidth() > Res.maxWTree ? img.img.getWidth() : Res.maxWTree;
                    }

                    img.timeRemove = mSystem.currentTimeMillis() + 60000L;
                }
            }
        } catch (Exception var5) {
        }

    }

    public void onLoadAllImage(Message msg) {
        try {
            versionImage = msg.reader().readByte();
            int totalImage = msg.reader().readShort();
            allImage = totalImage;
            this.loadImgScr = new LoadingScr();
            this.loadImgScr.switchToMe(GameCanvas.gameScr);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void onUpdateAllImage(Message msg) {
    }

    public void huongdan(Message msg) {
        try {
            byte idNPC = msg.reader().readByte();
            Actor obj = GameCanvas.gameScr.findActor(idNPC, 2);
            if (obj == null) {
                return;
            }

            String strhelp = msg.reader().readUTF();
            GameScr.strHelpNPC = mFont.split(strhelp, "\b");
            GameScr.StepHelpServer = 0;
            SetInfoData mdata = new SetInfoData();
            mdata.idIcon = obj.getIDicon();
            mCommand cmd = new mCommand(T.next, this, 2, mdata);
            GameCanvas.StartHelp(GameScr.strHelpNPC[GameScr.StepHelpServer], cmd, mdata.idIcon, false);
        } catch (Exception var7) {
        }

    }

    public void onGetItem(Message msg) {
        try {
            short whoget = msg.reader().readShort();
            short cat = msg.reader().readShort();
            short iditemget = msg.reader().readShort();
            GameCanvas.gameScr.onGetItemFromGround(whoget, iditemget, cat);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void chat(Message msg) {
        try {
            short id = msg.reader().readShort();
            String text = msg.reader().readUTF();
            byte cat = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            if (ac != null) {
                GameScr.addChat(ac, text, 100);
                if (ac.isNPC()) {
                    GameCanvas.msgchat.addNewChat(T.tinnpc, ac.getName() + ": ", text, (byte) 1, false);
                } else {
                    GameCanvas.msgchat.addNewChat(T.tinden, ac.getName() + ": ", text, (byte) 1, false);
                }

                GameCanvas.mevent.addEvent(T.tinden, (byte) 0, T.tinden + ": " + T.tinden, 0);
            }
        } catch (Exception var6) {
        }

    }

    public void charOut(Message msg) {
        try {
            short charID = msg.reader().readShort();
            GameCanvas.gameScr.charOutGame(charID);
        } catch (Exception var3) {
        }

    }

    public void startBuff(Message msg) {
        try {
            short idchar = msg.reader().readShort();
            byte idbuff = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(idchar, 0);
            if (ac != null && ac != GameScr.mainChar) {
                mVector vec_skill = (mVector) GameCanvas.gameScr.ALL_SKILL.elementAt(ac.getClazz());
                SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(idbuff);
                if (skill != null) {
                    ac.startBuff(skill.idEffStartSkill);
                }
            }
        } catch (Exception var7) {
        }

    }

    public void Set_XP(Message msg) {
        try {
            short idObj = msg.reader().readShort();
            Actor obj = GameCanvas.gameScr.findActor(idObj, 0);
            if (obj != null) {
                short lv = msg.reader().readShort();
                int xpPaint = msg.reader().readInt();
                if (obj.equals(GameScr.mainChar)) {
                    GameCanvas.gameScr.inDexfont = 2;
                    GameCanvas.gameScr.tickpaintFont = 15;
                    String text = msg.reader().readUTF();
                    obj.setlvPercent(lv);
                    GameCanvas.gameScr.textinfomainChar = text;
                }

                if (!obj.equals(GameScr.mainChar)) {
                    GameScr.addFlyText("+" + xpPaint + "exp", obj.getX(), obj.getY() - 40, 3, true);
                }
            }
        } catch (Exception var7) {
        }

    }

    public String getPercent(int percent) {
        return percent / 10 + "." + percent % 10 + "%";
    }

    public void Level_Up(Message msg) {
        try {
            short idObj = msg.reader().readShort();
            Actor obj = GameCanvas.gameScr.findActor(idObj, 0);
            if (obj != null) {
                byte lv = msg.reader().readByte();
                obj.setLV(lv);
                obj.addEffectSkillTime(47, obj.x, obj.y, 0L);
            }
        } catch (Exception var5) {
        }

    }

    public void onSkillInfo(Message msg) {
        GameCanvas.gameScr.ALL_SKILL.removeAllElements();
        try {
            byte nclass = msg.reader().readByte();
            for (int i = 0; i < nclass; i++) {
                mVector skinfo = new mVector();
                GameCanvas.gameScr.ALL_SKILL.addElement(skinfo);
                byte nskill = msg.reader().readByte();
                for (int k = 0; k < nskill; k++) {
                    SkillTemplate skill = new SkillTemplate();
                    skill.idSkillCode = msg.reader().readShort();
                    skill.idLastEff = msg.reader().readShort();
                    skill.idArrow = msg.reader().readShort();
                    skill.idTailMagic = msg.reader().readShort();
                    skill.idEffStartSkill = msg.reader().readShort();
                    String[] data = Util.split(msg.reader().readUTF(), ",");
                    skill.idArrowTool = new short[data.length];
                    for (int m = 0; m < data.length; m++)
                        skill.idArrowTool[m] = Short.parseShort(data[m]);
                    skinfo.addElement(skill);
                }
            }
            byte myCLass = msg.reader().readByte();
            GameCanvas.gameScr.charclass = myCLass;
            mVector mySkill = (mVector) GameCanvas.gameScr.ALL_SKILL.elementAt(myCLass);
            byte nlevel = 0;
            int j;
            for (j = 0; j < mySkill.size(); j++) {
                SkillTemplate skill = (SkillTemplate) mySkill.elementAt(j);
                skill.name = msg.reader().readUTF();
                skill.type = msg.reader().readByte();
                skill.iconId = msg.reader().readShort();
                skill.range = msg.reader().readShort();
                skill.decription = msg.reader().readUTF();
                nlevel = msg.reader().readByte();
                skill.lvRequire = new byte[nlevel];
                msg.reader().read(skill.lvRequire);
                if (skill.type != SkillTemplate.TYPE_PASSIVE) {
                    skill.mp = new short[nlevel];
                    skill.nTarget = new short[nlevel];
                    if (skill.type == SkillTemplate.TYPE_BUFF) skill.timeLive = new int[nlevel];
                    for (int m = 0; m < nlevel; m++) {
                        skill.mp[m] = msg.reader().readShort();
                        skill.nTarget[m] = msg.reader().readShort();
                        if (skill.type == SkillTemplate.TYPE_BUFF) skill.timeLive[m] = msg.reader().readInt();
                    }
                }
                byte nOption = msg.reader().readByte();
                int k;
                for (k = 0; k < nOption; k++) {
                    OptionSkill op = new OptionSkill();
                    op.id = msg.reader().readShort();
                    byte len = msg.reader().readByte();
                    op.value = new int[len][];
                    for (int m = 0; m < len; m++) {
                        op.value[m] = new int[nlevel];
                        for (int n = 0; n < nlevel; n++)
                            op.value[m][n] = msg.reader().readInt();
                    }
                    skill.options.addElement(op);
                }
                skill.coolDown = new int[nlevel];
                for (k = 0; k < nlevel; k++)
                    skill.coolDown[k] = msg.reader().readInt();
                GameScr.vec_skill = (mVector) GameCanvas.gameScr.ALL_SKILL.elementAt(GameCanvas.gameScr.charclass);
            }
            if (GameScr.isIntro) {
                System.out.println("set level Skill");
                Char.levelSkill = new byte[GameScr.vec_skill.size()];
                for (j = 0; j < Char.levelSkill.length; j++)
                    Char.levelSkill[j] = 1;
                MainChar.putSkill2QuickSlot();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMosterDie_(Message msg) {
        try {
            short idattacker = msg.reader().readShort();
            short idattacked = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findCharByID(idattacker);
            Monster mt = GameCanvas.gameScr.findMonsterByID(idattacked);
            if (mt != null) {
                if (mt.idTemplate == 88) {
                    GameScr.Ghost = null;
                    GameScr.isGost = false;
                    GameCanvas.gameScr.actors.removeElement(mt);
                    GameCanvas.gameScr.focusedActor = null;
                } else {
                    if (GameCanvas.gameScr.focusedActor != null && mt.equals(GameCanvas.gameScr.focusedActor)) {
                        GameCanvas.gameScr.focusedActor = null;
                    }

                    mt.state = 5;
                    mt.setTickDie(10);
                }
            }
        } catch (Exception var6) {
        }

    }

    public void onMonsterTemplate(Message msg) {
        try {
            short nmonster = msg.reader().readShort();
            int i;
            for (i = 0; i < nmonster; i++) {
                String name = msg.reader().readUTF();
                short idtemplate = msg.reader().readShort();
                short idImg = msg.reader().readShort();
                byte typemove = msg.reader().readByte();
                short idEff = msg.reader().readShort();
                byte idShadow = msg.reader().readByte();
                MonsterTemplate monsterTemplate = new MonsterTemplate(idtemplate, name, typemove, idImg, idEff, idShadow);
                byte allSkill = msg.reader().readByte();
                monsterTemplate.fAttack = new byte[allSkill][][];
                for (int j = 0; j < allSkill; j++) {
                    byte allWay = msg.reader().readByte();
                    monsterTemplate.fAttack[j] = new byte[allWay][];
                    for (int k = 0; k < allWay; k++) {
                        monsterTemplate.fAttack[j][k] = new byte[msg.reader().readByte()];
                        msg.reader().read(monsterTemplate.fAttack[j][k]);
                    }
                }
                MonsterTemplate.MONSTER.add(monsterTemplate);

                MonsterTemplate.ALL_MONSTER_TEMPLATE.put(idtemplate, monsterTemplate);
            }
            nmonster = msg.reader().readByte();
            GameScr.ID_BOSS = new mHashtable();
            GameScr.ALL_SKILL_TEMPLATE_BOSS = new mHashtable();
            for (i = 0; i < nmonster; i++) {
                short idBoss = msg.reader().readShort();
                GameScr.ID_BOSS.put((new StringBuilder(String.valueOf(idBoss))).toString(), (idBoss));
                byte totalSkill = msg.reader().readByte();
                for (int j = 0; j < totalSkill; j++) {
                    short idSkill = msg.reader().readShort();
                    byte nAnim = msg.reader().readByte();
                    byte[][] arrayFrameAnimUp = new byte[nAnim][];
                    byte[][] arrayFrameAnimDown = new byte[nAnim][];
                    for (int k = 0; k < nAnim; k++) {
                        byte nFrame = msg.reader().readByte();
                        arrayFrameAnimUp[k] = new byte[nFrame];
                        arrayFrameAnimDown[k] = new byte[nFrame];
                        int l;
                        for (l = 0; l < nFrame; l++)
                            arrayFrameAnimUp[k][l] = msg.reader().readByte();
                        for (l = 0; l < nFrame; l++)
                            arrayFrameAnimDown[k][l] = msg.reader().readByte();
                    }
                    SkillBossTemplate skillTem = new SkillBossTemplate();
                    skillTem.arrayAnimAttackUp = arrayFrameAnimUp;
                    skillTem.arrayAnimAttackDown = arrayFrameAnimDown;
                    GameScr.ALL_SKILL_TEMPLATE_BOSS.put((new StringBuilder(String.valueOf(idSkill))).toString(), skillTem);
                }
            }
            System.out.println("onMonsterTemplate-- DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCharWearing(Message msg) {
        try {
            short id = msg.reader().readShort();
            Actor ac = GameCanvas.gameScr.findActor(id, 0);
            if (ac == null) {
                ac = new Char();
                if (GameScr.mainChar != null && id != GameScr.mainChar.ID)
                    ac.ID = id;
            }
            if (GameScr.mainChar != null && id == GameScr.mainChar.ID)
                ac = GameScr.mainChar;
            if (ac != null) {
                short[] listpart = new short[msg.reader().readByte()];
                for (int i = 0; i < listpart.length; i++)
                    listpart[i] = msg.reader().readShort();
                ac.setInfoWearing(listpart);
                byte len = msg.reader().readByte();
                Item[] it = new Item[len];
                for (int j = 0; j < len; j++) {
                    it[j] = null;
                    byte iditem = msg.reader().readByte();
                    if (iditem > -1) {
                        it[j] = new Item();
                        (it[j]).ID = iditem;
                        (it[j]).charClazz = msg.reader().readByte();
                        (it[j]).level = msg.reader().readShort();
                        (it[j]).name = msg.reader().readUTF();
                        (it[j]).plus = msg.reader().readByte();
                        (it[j]).idIcon = msg.reader().readShort();
                        (it[j]).colorname = msg.reader().readByte();
                        it[j].setTypeItem(msg.reader().readByte());
                        int sizeOption = msg.reader().readByte();
                        (it[j]).options = new mVector();
                        for (int k = 0; k < sizeOption; k++) {
                            ItemOption itop = new ItemOption();
                            itop.id = msg.reader().readShort();
                            itop.idColor = msg.reader().readByte();
                            itop.value = msg.reader().readInt();
                            itop.value2 = msg.reader().readInt();
                            (it[j]).options.addElement(itop);
                        }
                        (it[j]).type = msg.reader().readByte();
                    }
                }
                String name = "";
                int hp = 100;
                int maxhp = 100;
                int mp = 100;
                int maxmp = 100;
                try {
                    name = msg.reader().readUTF();
                    hp = msg.reader().readInt();
                    maxhp = msg.reader().readInt();
                    mp = msg.reader().readInt();
                    maxmp = msg.reader().readInt();
                    ac.setName(name);
                    ac.setHp(hp);
                    ac.setMP(mp);
                    ac.setMaxHP(maxhp);
                    ac.setMaxMP(maxmp);
                } catch (Exception e) {
                    name = "";
                    hp = 100;
                    maxhp = 100;
                    maxmp = 100;
                    mp = 100;
                }
                ac.setEquipChar(it);
                if (id != GameScr.mainChar.ID) {
                    InfoOtherCharScr.charFocus = (Char) ac;
                    InfoOtherCharScr.gI().switchToMe(GameCanvas.gameScr);
                    GameCanvas.endDlg();
                }
                ac.setEffWeapon(msg.reader().readShort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onItemTemplate(Message msg) {
        try {
            int nitem = msg.reader().readShort();
            int i;
            for (i = 0; i < nitem; i++) {
                short id = msg.reader().readShort();
                String name = msg.reader().readUTF();
                String decript = msg.reader().readUTF();
                byte cat = msg.reader().readByte();
                String nameMoney = msg.reader().readUTF();
                byte gender = msg.reader().readByte();
                byte type = msg.reader().readByte();
                ItemTemplate item = new ItemTemplate();
                item.id = id;
                item.name = name;
                item.decript = decript;
                item.cat = cat;
                item.namemoney = nameMoney;
                item.gender = gender;
                item.type = type;
                ItemTemplate.ALL_ITEM_TEMPLATE.put((new StringBuilder(String.valueOf(id))).toString(), item);
            }
            nitem = msg.reader().readShort();
            System.out.println("so luong ten: " + nitem);
            ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.removeAllElements();
            for (i = 0; i < nitem; i++) {
                NameAttributeItem name = new NameAttributeItem(i, msg.reader()
                        .readUTF(), msg.reader().readByte());
                name.idColor = msg.reader().readByte();
                ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.addElement(name);
            }
            byte sizeauto = msg.reader().readByte();
            GameScr.infoAutoGetItem = new String[sizeauto];
            MenuSelectItem.FocusAutoNhat = new boolean[sizeauto];
            MenuSelectItem.isAutoNhat = new boolean[sizeauto];
            int j;
            for (j = 0; j < sizeauto; j++)
                GameScr.infoAutoGetItem[j] = msg.reader().readUTF();
            GameScr.TIME_BETWEEN_ATTACK = new short[5];
            for (j = 0; j < GameScr.TIME_BETWEEN_ATTACK.length; j++)
                GameScr.TIME_BETWEEN_ATTACK[j] = msg.reader().readShort();
            short sizeEffinfo = msg.reader().readShort();
            for (int k = 0; k < sizeEffinfo; k++) {
                short id = msg.reader().readShort();
                byte pos = msg.reader().readByte();
                GameScr.hashEffInfo.put((new StringBuilder(String.valueOf(id))).toString(), (new StringBuilder(String.valueOf(pos))).toString());
            }
            byte size = -1;
            try {
                size = msg.reader().readByte();
            } catch (Exception e) {
                size = -1;
            }
            if (size > -1) {
                MainMenu.POS_ITEM_IN_EQUIP = new byte[size];
                for (int n = 0; n < size; n++)
                    MainMenu.POS_ITEM_IN_EQUIP[n] = msg.reader().readByte();
            }
            byte index12Plus = 0;
            try {
                index12Plus = msg.reader().readByte();
            } catch (Exception e) {
                index12Plus = 0;
            }
            if (index12Plus == 0)
                GameCanvas.gameScr.ispaint12Plus = false;
            if (index12Plus == 1)
                GameCanvas.gameScr.ispaint12Plus = true;
            short totalImgRmsRemove = msg.reader().readShort();
            for (int m = 0; m < totalImgRmsRemove; m++)
                Rms.DeleteRMS((new StringBuilder(String.valueOf(msg.reader().readInt()))).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onByteDataServer(Message msg) {
        try {
            int idData = msg.reader().readUnsignedShort();
            byte[] arrayImg1 = new byte[msg.reader().available()];
            msg.reader().read(arrayImg1);
            if (idData < 1000) {
                MonsterTemplate mons = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(String.valueOf(idData));
                mons.setDataMonster(arrayImg1);
                Rms.saveRMS("monst" + idData, arrayImg1);
            } else if (idData < Res.ID_ITEM_MAP || idData >= Res.ID_START_SKILL) {
                EffectData eff = (EffectData) GameData.listbyteData.get("" + idData);
                if (eff != null) {
                    eff.setdata(arrayImg1);
                    Rms.saveRMS("eff" + idData, arrayImg1);
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void onAlertPopUp(Message msg) {
        try {
            String message = msg.reader().readUTF();
            String url = msg.reader().readUTF();
            if (url.equals("")) {
                GameCanvas.startOKDlg(message);
            } else {
                GameCanvas.startOKDlg(message, url);
            }
        } catch (Exception var4) {
        }

    }

    public void onLoginSuccess(Message msg) {
        try {
            ServerListScr.addressSave = Session_ME.host;
            ServerListScr.portSave = (short) Session_ME.port;
            Rms.saveRMSString("portSave", String.valueOf(ServerListScr.portSave));
            Rms.saveRMSString("addressSave", ServerListScr.addressSave);
        } catch (Exception var3) {
        }

    }

    public void onDynamicEffect(Message msg) {
        try {
            byte type = msg.reader().readByte();
            short idEff = msg.reader().readShort();
            short idactor = msg.reader().readShort();
            int timenew = msg.reader().readInt();
            long timeExist = (long) timenew + (timenew > 0 ? System.currentTimeMillis() : 0L);
            int x = msg.reader().readShort();
            int y = msg.reader().readShort();
            byte cat = msg.reader().readByte();
            byte top_bottom = msg.reader().readByte();
            byte stop = msg.reader().readByte();
            int add_remove = msg.reader().readByte();
            int dame = msg.reader().readInt();
            byte loop = msg.reader().readByte();
            byte canPaintActor = msg.reader().readByte();
            byte typeFight = msg.reader().readByte();
            boolean var18 = false;

            byte Waitloop;
            try {
                Waitloop = msg.reader().readByte();
            } catch (Exception var25) {
                Waitloop = 0;
            }

            Actor ac = GameCanvas.gameScr.findActor(idactor, cat);
            boolean canmove = false;
            int colordame = 2;
            if (cat == 0) {
                colordame = 1;
            }

            if (dame > 0 && ac != null) {
                GameCanvas.gameScr.startFlyText("-" + dame, colordame, ac.x, ac.y - ac.getHeight(), 1, -2);
            }

            if (stop == 1) {
                canmove = true;
            }

            boolean canpaint = false;
            if (canPaintActor == 0) {
                canpaint = true;
            }

            boolean canFight = false;
            if (typeFight == 0) {
                canFight = true;
            }

            DataSkillEff eff;
            if (type == 0) {
                eff = new DataSkillEff(idEff, x, y, (long) loop);
                eff.isEffAuto = true;
                eff.setWaitLoop(Waitloop);
                if (top_bottom == 0) {
                    GameCanvas.gameScr.actors.addElement(eff);
                } else {
                    EffectManager.addHiEffect(eff);
                }
            } else if (type == 2) {
                eff = new DataSkillEff(idEff, x, y, timeExist, loop);
                eff.isEffAuto = true;
                eff.setWaitLoop(Waitloop);
                if (top_bottom == 0) {
                    GameCanvas.gameScr.actors.addElement(eff);
                } else {
                    EffectManager.addHiEffect(eff);
                }
            }

            if (type == 1 && ac != null) {
                ac.addEffectSkillTime(idEff, x, y, timeExist, canmove, canpaint, canFight, loop, Waitloop);
            }
        } catch (Exception var26) {
            var26.printStackTrace();
        }

    }

    public void onUpdateHP_MP(Message msg) {
        try {
            short id = msg.reader().readShort();
            int hp = msg.reader().readInt();
            int mp = msg.reader().readInt();
            int hpChange = msg.reader().readInt();
            int mpChange = msg.reader().readInt();
            byte cat = msg.reader().readByte();
            byte idColor = msg.reader().readByte();
            String infoShow = msg.reader().readUTF();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            boolean var11 = true;

            byte idimgFly;
            try {
                idimgFly = msg.reader().readByte();
            } catch (Exception var13) {
                idimgFly = -1;
            }

            if (idimgFly != -1 && ac != null) {
                if (!ac.equals(GameScr.mainChar) && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    return;
                }

                if (idimgFly == 0) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 8, true);
                } else if (idimgFly == 1) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 9, true);
                } else if (idimgFly == 2) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 10, true);
                } else if (idimgFly == 3) {
                    GameScr.addFlyText("", ac.x, ac.y - 35, 11, true);
                }
            }

            if (ac != null) {
                ac.setHp(hp);
                ac.setMP(mp);
                if (!ac.equals(GameScr.mainChar) && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    return;
                }

                if (hpChange > 0) {
                    GameScr.addFlyText("+" + hpChange, ac.x, ac.y - 35, 7, true);
                } else if (hpChange < 0) {
                    GameScr.addFlyText("" + hpChange, ac.x, ac.y - 35, 1, true);
                }

                if (mpChange > 0) {
                    GameScr.addFlyText("+" + mpChange, ac.x, ac.y - 35, 6, true);
                } else if (mpChange < 0) {
                    GameScr.addFlyText("" + mpChange, ac.x, ac.y - 35, 5, true);
                }

                if (!infoShow.equals("")) {
                    GameScr.addFlyText(infoShow, ac.x, ac.y - 35, idColor, true);
                }
            }
        } catch (Exception var14) {
        }

    }

    public void onChat(Message msg) {
        try {
            String name = msg.reader().readUTF();
            String info = msg.reader().readUTF();
            byte typeShow = msg.reader().readByte();
            String nameOther = msg.reader().readUTF();
            if (!nameOther.equals("")) {
                if (!GameCanvas.msgchat.isShow) {
                    GameCanvas.mevent.addEvent(nameOther, (byte) 0, name + ": " + T.tinden, 0);
                }

                GameCanvas.msgchat.addNewChat(nameOther, name + ": ", info, (byte) 0, false);
                if (GameCanvas.msgchat != null && !GameCanvas.msgchat.isShow && GameScr.numMSG <= 0) {
                    ++GameScr.numMSG;
                }
            } else {
                if (!GameCanvas.msgchat.isShow) {
                    GameCanvas.mevent.addEvent(name, (byte) 0, T.tinden, 0);
                }

                GameCanvas.msgchat.addNewChat(name, name + ": ", info, (byte) 0, false);
                if (GameCanvas.msgchat != null && !GameCanvas.msgchat.isShow && GameScr.numMSG <= 0) {
                    ++GameScr.numMSG;
                }

                if (typeShow != 0 && typeShow == 1) {
                    GameCanvas.start_Chat_Dialog();
                }
            }
        } catch (Exception var6) {
        }

    }

    public void changPK(Message msg) {
        try {
            short id = msg.reader().readShort();
            byte cat = msg.reader().readByte();
            byte typePK = msg.reader().readByte();
            Actor ac = GameCanvas.gameScr.findActor(id, cat);
            if (ac != null) {
                ac.setTypePK(typePK);
            }
        } catch (Exception var6) {
        }

    }

    public void Add_Friend(Message msg) {
        try {
            byte type = msg.reader().readByte();
            if (type == 0) {
                String name = msg.reader().readUTF();
                GameCanvas.mevent.addEvent(name, (byte) 1, T.loimoikb, 0);
                ++GameScr.numMSG;
            }
        } catch (Exception var4) {
        }

    }

    public void ThacDau(Message msg) {
        try {
            short id = msg.reader().readShort();
            String text = msg.reader().readUTF();
            int price = msg.reader().readInt();
            GameCanvas.mevent.addEvent(text, (byte) 4, T.loimoiThachdau, price, id);
            ++GameScr.numMSG;
        } catch (Exception var5) {
        }

    }

    public void List_info(Message msg) {
        try {
            byte type = msg.reader().readByte();
            String title = msg.reader().readUTF();
            short size = msg.reader().readShort();
            if (type == 0) {
                mVector listChar = new mVector();
                for (int i = 0; i < size; i++) {
                    Char cF = new Char();
                    cF.idiConList = msg.reader().readShort();
                    cF.name = msg.reader().readUTF();
                    int lv = msg.reader().readUnsignedByte();
                    cF.level = (short) lv;
                    cF.luong = msg.reader().readInt();
                    cF.xu = msg.reader().readLong();
                    cF.online = msg.reader().readByte();
                    String option = msg.reader().readUTF();
                    if (type == 1) {
                        byte b = msg.reader().readByte();
                        short[] listpart = new short[msg.reader().readByte()];
                        for (int k = 0; k < listpart.length; k++)
                            listpart[k] = msg.reader().readShort();
                        cF.setInfoWearing(listpart);
                        listChar.addElement(cF);
                    }
                }
                byte nAction = msg.reader().readByte();
                String[] listTextcmd = new String[nAction];
                for (int j = 0; j < nAction; j++)
                    listTextcmd[j] = msg.reader().readUTF();
                GameCanvas.menu2.startArt(listChar, type, title, listTextcmd);
            } else if (type == 1) {
                mVector listKhu = new mVector();
                for (int i = 0; i < size; i++) {
                    String khu = msg.reader().readUTF();
                    byte index = msg.reader().readByte();
                    listKhu.addElement(new Khu((byte) i, index, khu));
                }
                byte nAction = msg.reader().readByte();
                String[] listTextcmd = new String[nAction];
                for (int j = 0; j < nAction; j++)
                    listTextcmd[j] = msg.reader().readUTF();
                GameCanvas.menu2.startArt(listKhu, type, title, listTextcmd);
            } else if (type == 2) {
                mVector listChar = new mVector();
                for (int i = 0; i < size; i++) {
                    Char cF = new Char();
                    cF.idiConList = msg.reader().readShort();
                    cF.name = msg.reader().readUTF();
                    int lv = msg.reader().readUnsignedByte();
                    cF.level = (short) lv;
                    cF.luong = msg.reader().readInt();
                    cF.xu = msg.reader().readLong();
                    cF.online = msg.reader().readByte();
                    int rank = msg.reader().readInt();
                    short[] listpart = new short[msg.reader().readByte()];
                    for (int k = 0; k < listpart.length; k++)
                        listpart[k] = msg.reader().readShort();
                    cF.setInfoWearing(listpart);
                    listChar.addElement(cF);
                }
                byte nAction = msg.reader().readByte();
                String[] listTextcmd = new String[nAction];
                for (int j = 0; j < nAction; j++)
                    listTextcmd[j] = msg.reader().readUTF();
                GameCanvas.menu2.startArt(listChar, type, title, listTextcmd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onListParty(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0:
                    mVector ListParty = new mVector();
                    String masterName = msg.reader().readUTF();
                    byte size = msg.reader().readByte();

                    for (int i = 0; i < size; ++i) {
                        Info_Party info = new Info_Party();
                        info.nameMember = msg.reader().readUTF();
                        if (info.nameMember.equals(masterName)) {
                            info.isMaster = true;
                        }

                        info.menberLv = msg.reader().readShort();
                        info.nameMap = msg.reader().readUTF();
                        info.menberArena = msg.reader().readByte();
                        ListParty.addElement(info);
                    }

                    GameCanvas.menu2.startArtParty(ListParty, 3, T.nhom1, masterName);
                    GameCanvas.gameScr.hideGUI = 0;
                    break;
                case 1:
                    String invite_CharName = msg.reader().readUTF();
                    GameCanvas.mevent.addEvent(invite_CharName, (byte) 2, T.moivaonhom, 0);
                    ++GameScr.numMSG;
            }
        } catch (Exception var8) {
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void onCountDown(Message msg) {
        try {
            byte cat = msg.reader().readByte();
            short id = msg.reader().readShort();
            String tile = msg.reader().readUTF();
            int time_ = msg.reader().readInt();
            short idicon = -1;
            byte count = -1;
            try {
                idicon = msg.reader().readShort();
                count = msg.reader().readByte();
            } catch (Exception e) {
                idicon = -1;
                count = -1;
            }
            long time = mSystem.currentTimeMillis() + (time_ * 1000);
            if (cat != -1) {
                if (time_ > 0)
                    GameScr.charcountdonw = new CharCountDown(time, tile);
                if (time_ == 0)
                    GameScr.charcountdonw = null;
            }
            if (cat == -1 && time_ >= 0) {
                TimecountDown t = findTime(id);
                int second = time_;
                if (t == null) {
                    t = new TimecountDown(id, idicon, second, tile, count);
                    GameScr.VecTime.addElement(t);
                }
                if (t != null) {
                    t.tile = tile;
                    t.idIcon = idicon;
                    t.setsecond(second);
                    if (count == -2)
                        t.wantdestroy = true;
                }
            }
        } catch (Exception exception) {
        }
    }

    public TimecountDown findTime(int id) {
        for (int i = 0; i < GameScr.VecTime.size(); ++i) {
            TimecountDown t = (TimecountDown) GameScr.VecTime.elementAt(i);
            if (t != null && t.id == id) {
                return t;
            }
        }

        return null;
    }

    public void opentBox(Message msg) {
        try {
            int num = msg.reader().readByte();
            String tile = msg.reader().readUTF();
            mVector vec = new mVector();

            for (int i = 0; i < num; ++i) {
                Item item = new Item();
                item.idIcon = msg.reader().readShort();
                item.colorname = msg.reader().readByte();
                item.quantity = msg.reader().readInt();
                mSystem.println("so luong item " + item.quantity);
                vec.addElement(item);
            }

            GameCanvas.starBoxDialog(tile, vec);
        } catch (Exception var7) {
        }

    }

    public void onImgBigmap(Message msg) {
        try {
            byte id = msg.reader().readByte();
            int size = msg.reader().readInt();
            if (size > 0) {
                byte[] data = new byte[size];

                for (int i = 0; i < size; ++i) {
                    data[i] = msg.reader().readByte();
                }

                String path = GameCanvas.getPath("bigimgmap" + id + "@" + Tilemap.lv);
                Rms.saveRMS(path, data);
            }
        } catch (Exception var6) {
        }

    }

    public void onListNPCsell(Message msg) {
        try {
            GameCanvas.gameScr.idNPC_Sell = null;
            byte size = msg.reader().readByte();
            GameCanvas.gameScr.idNPC_Sell = new short[size];

            for (int i = 0; i < size; ++i) {
                GameCanvas.gameScr.idNPC_Sell[i] = msg.reader().readShort();
            }
        } catch (Exception var4) {
        }

    }

    public void onInfofromserver(Message msg) {
        try {
            GameCanvas.menu2.idIcon = -1;
            mVector vecinfo = new mVector();
            String tile = msg.reader().readUTF();
            int nitem = 0;
            byte size = msg.reader().readByte();
            nitem += size;
            for (int i = 0; i < size; i++) {
                ServerInfo svif = new ServerInfo();
                svif.tile = msg.reader().readUTF();
                byte infosize = msg.reader().readByte();
                nitem += infosize;
                svif.info = new String[infosize];
                for (int j = 0; j < infosize; j++)
                    svif.info[j] = msg.reader().readUTF();
                vecinfo.addElement(svif);
            }
            GameCanvas.menu2.idIcon = msg.reader().readShort();
            GameCanvas.menu2.startArt(vecinfo, 4, tile, nitem);
        } catch (Exception exception) {
        }
    }

    public void onBaoTri(Message msg) {
        try {
            String info = msg.reader().readUTF();
            byte time = msg.reader().readByte();
            if (time == -1) {
                MenuLogin.gI().switchToMe();
                GameCanvas.startOKDlg(info);
            } else {
                ChangScr.timenextLogin = mSystem.currentTimeMillis() + (long) (time * 1000);
                Session_ME.gI().close();
            }
        } catch (Exception var4) {
        }

    }

    public void onGiveItem(Message msg) {
        try {
            int id = msg.reader().readInt();
            String title = msg.reader().readUTF();
            String infoconfirm = msg.reader().readUTF();
            String charname = msg.reader().readUTF();
            Item it0 = new Item();
            it0.ID = msg.reader().readShort();
            it0.catagory = msg.reader().readByte();
            it0.level = msg.reader().readShort();
            it0.charClazz = msg.reader().readByte();
            it0.name = msg.reader().readUTF();
            it0.lock = msg.reader().readByte();
            it0.plus = msg.reader().readByte();
            it0.idIcon = msg.reader().readShort();
            it0.colorname = msg.reader().readByte();
            it0.cantrade = msg.reader().readBoolean();
            it0.cansell = msg.reader().readBoolean();
            it0.priceShop = msg.reader().readInt();
            it0.quantity = msg.reader().readInt();
            int nOption = msg.reader().readByte();

            for (int j = 0; j < nOption; ++j) {
                ItemOption itoption = new ItemOption();
                itoption.id = msg.reader().readShort();
                itoption.idColor = msg.reader().readByte();
                itoption.value = msg.reader().readInt();
                itoption.value2 = msg.reader().readInt();
                it0.options.addElement(itoption);
            }

            it0.type = msg.reader().readByte();
            GameCanvas.mevent.addEvent(charname, (byte) 8, T.quatang, 0, id, it0, title, infoconfirm);
            ++GameScr.numMSG;
        } catch (Exception var10) {
        }

    }

    public void onCharData(Message msg) {
        try {
            Res.VERSION_CHUNK = msg.reader().readByte();
            int chunkLen = msg.reader().readInt();
            byte[] datatemp = null;

            int i;
            short id;
            short idBig;
            for (i = 0; i < chunkLen; ++i) {
                id = msg.reader().readShort();
                idBig = msg.reader().readShort();
                byte[] data = new byte[idBig];
                msg.reader().read(data);
                Res.loadChunkPrivate(data, id);
                if (mSystem.isj2me) {
                    datatemp = data;
                    Res.listGetChunk.remove(String.valueOf(id));
                }
            }

            Char.load();

            for (i = 0; i < chunkLen; ++i) {
                id = msg.reader().readShort();
                idBig = msg.reader().readShort();
                Chunk chunk = (Chunk) Chunk.arr.get(String.valueOf(id));
                if (idBig > -1) {
                    chunk.idBig = idBig;
                    if (mSystem.isj2me && chunkLen == 1) {
                        ByteArrayOutputStream bo = new ByteArrayOutputStream();
                        DataOutputStream d = new DataOutputStream(bo);
                        d.writeShort(idBig);
                        d.write(datatemp);
                        Rms.saveRMS("ck" + id, bo.toByteArray());
                        bo.close();
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public void onShopHair(Message msg) {
        try {
            ShopHairScreen.vecItemHair.removeAllElements();
            byte size = msg.reader().readByte();

            for (int i = 0; i < size; ++i) {
                Item it0 = new Item();
                it0.ID = msg.reader().readShort();
                it0.isItemShop = true;
                it0.name = msg.reader().readUTF();
                it0.level = msg.reader().readShort();
                it0.charClazz = msg.reader().readByte();
                it0.idIcon = msg.reader().readShort();
                it0.colorname = msg.reader().readByte();
                it0.priceShop = msg.reader().readInt();
                it0.priceType = msg.reader().readByte();
                int nOption = msg.reader().readByte();

                for (int j = 0; j < nOption; ++j) {
                    ItemOption itoption = new ItemOption();
                    itoption.id = msg.reader().readShort();
                    itoption.idColor = msg.reader().readByte();
                    itoption.value = msg.reader().readInt();
                    itoption.value2 = msg.reader().readInt();
                    it0.options.addElement(itoption);
                }

                it0.type = msg.reader().readByte();
                byte partSize = msg.reader().readByte();
                short[] arr = new short[partSize];

                for (int k = 0; k < partSize; ++k) {
                    arr[k] = msg.reader().readShort();
                }

                it0.arrPart = arr;
                byte ncommand = msg.reader().readByte();
                String[] cap = new String[ncommand];

                for (int m = 0; m < ncommand; ++m) {
                    cap[m] = msg.reader().readUTF();
                }

                ShopHairScreen.vecItemHair.addElement(it0);
                ShopHairScreen.gI().switchToMe(GameCanvas.gameScr);
            }
        } catch (Exception var11) {
        }

    }

    public void onUpgradeItem(Message msg) {
        try {
            short luckypoint;
            int size, i;
            byte result;
            int size3, j;
            byte size2;
            int k;
            byte type = msg.reader().readByte();
            switch (type) {
                case 0:
                    MainMenu.vecItemDapDo.removeAllElements();
                    luckypoint = msg.reader().readShort();
                    size = msg.reader().readUnsignedByte();
                    for (i = 0; i < size; i++) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        for (int m = 0; m < nOption; m++) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }
                        it0.type = msg.reader().readByte();
                        it0.pos = msg.reader().readByte();
                        if (it0.pos != -1)
                            MainMenu.vecMaterial.addElement(it0);
                        if (!MainMenu.isHopda && MainMenu.mItem != null &&
                                MainMenu.mItem.name.equals(it0.name)) {
                            MainMenu.mItem = it0;
                            MainMenu.gI().updateItemDapdo();
                        }
                        MainMenu.vecItemDapDo.addElement(it0);
                    }
                    MainMenu.isDapdo = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 1:
                    MainMenu.textPercent = msg.reader().readUTF();
                    MainMenu.xuCuongHoa = msg.reader().readInt();
                    MainMenu.moneyType = msg.reader().readByte();
                    break;
                case 2:
                    result = msg.reader().readByte();
                    MainMenu.gI().Upgraderesult(result);
                    break;
                case 3:
                    MainMenu.vecItemDapDo.removeAllElements();
                    size3 = msg.reader().readUnsignedByte();
                    for (j = 0; j < size3; j++) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        for (int m = 0; m < nOption; m++) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }
                        it0.type = msg.reader().readByte();
                        it0.pos = msg.reader().readByte();
                        if (it0.pos != -1)
                            MainMenu.vecMaterial.addElement(it0);
                        if (!MainMenu.isHopda && MainMenu.mItem != null &&
                                MainMenu.mItem.name.equals(it0.name)) {
                            MainMenu.mItem = it0;
                            MainMenu.gI().updateItemDapdo();
                        }
                        MainMenu.vecItemDapDo.addElement(it0);
                    }
                    MainMenu.isDapdo = true;
                    MainMenu.isHopda = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 4:
                    MainMenu.textPercent = msg.reader().readUTF();
                    MainMenu.xuCuongHoa = msg.reader().readInt();
                    MainMenu.moneyType = msg.reader().readByte();
                    MainMenu.ID_CUONG_HOA = msg.reader().readShort();
                    break;
                case 5:
                    size2 = msg.reader().readByte();
                    MainMenu.vecItemCreate.removeAllElements();
                    for (k = 0; k < size2; k++) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        for (int m = 0; m < nOption; m++) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }
                        MainMenu.vecItemCreate.addElement(it0);
                        if (MainMenu.isChuyenHoa && MainMenu.itemChuyenHoa0 != null &&
                                MainMenu.itemChuyenHoa0.name.equals(it0.name))
                            MainMenu.itemChuyenHoa0 = it0;
                    }
                    MainMenu.isChuyenHoa = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 6:
                    MainMenu.gI().ChuyenHoaItemresult();
                    break;
            }
        } catch (Exception exception) {
        }
    }

    public void inviteClan(Message msg) {
        try {
            mVector vecinfo;
            int nitem;
            byte size;
            int i;
            mVector vecmenber;
            int size2, j;
            byte sizeCmdName;
            int k;
            byte sizeOption;
            int m;
            String name;
            byte type = msg.reader().readByte();
            switch (type) {
                case -1:
                    ScreenClan.vecmember.removeAllElements();
                    ScreenClan.infoClan.removeAllElements();
                    ScreenClan.nameCmd = null;
                    ScreenClan.nameOption = null;
                    vecinfo = new mVector();
                    nitem = 0;
                    size = msg.reader().readByte();
                    nitem += size;
                    for (i = 0; i < size; i++) {
                        ServerInfo svif = new ServerInfo();
                        svif.tile = msg.reader().readUTF();
                        byte infosize = msg.reader().readByte();
                        nitem += infosize;
                        svif.info = new String[infosize];
                        for (int n = 0; n < infosize; n++)
                            svif.info[n] = msg.reader().readUTF();
                        vecinfo.addElement(svif);
                    }
                    ScreenClan.iTemSize = nitem;
                    ScreenClan.infoClan = vecinfo;
                    vecmenber = new mVector();
                    size2 = msg.reader().readUnsignedByte();
                    for (j = 0; j < size2; j++) {
                        Char c = new Char();
                        c.name = msg.reader().readUTF();
                        c.lv = (short) msg.reader().readUnsignedByte();
                        c.chucvu = msg.reader().readUTF();
                        c.conghien = msg.reader().readInt();
                        c.online = msg.reader().readByte();
                        c.PartHead = msg.reader().readShort();
                        vecmenber.addElement(c);
                    }
                    ScreenClan.vecmember = vecmenber;
                    sizeCmdName = msg.reader().readByte();
                    ScreenClan.nameCmd = new String[sizeCmdName];
                    for (k = 0; k < sizeCmdName; k++)
                        ScreenClan.nameCmd[k] = msg.reader().readUTF();
                    sizeOption = msg.reader().readByte();
                    ScreenClan.nameOption = new String[sizeOption];
                    for (m = 0; m < sizeOption; m++)
                        ScreenClan.nameOption[m] = msg.reader().readUTF();
                    ScreenClan.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 1:
                    name = msg.reader().readUTF();
                    GameCanvas.mevent.addEvent(name, (byte) 5,
                            T.moivaoclan, 0);
                    GameScr.numMSG = (byte) (GameScr.numMSG + 1);
                    break;
            }
        } catch (Exception exception) {
        }
    }

    public void onCreateItem(Message msg) {
        try {
            int min;
            byte size;
            int i;
            byte rs;
            short idIcon;
            byte size2;
            int j;
            byte size4;
            int k;
            (MainMenu.gI()).miniItem = 0;
            MainMenu.vecItemCreate.removeAllElements();
            byte type = msg.reader().readByte();
            switch (type) {
                case 0:
                    min = msg.reader().readUnsignedByte();
                    (MainMenu.gI()).miniItem = min;
                    size = msg.reader().readByte();
                    for (i = 0; i < size; i++) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        for (int m = 0; m < nOption; m++) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }
                        it0.pos = msg.reader().readByte();
                        MainMenu.vecItemCreate.addElement(it0);
                    }
                    MainMenu.isCheDo = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 1:
                    rs = msg.reader().readByte();
                    idIcon = msg.reader().readShort();
                    MainMenu.gI().CreateItemresult(rs, idIcon);
                    break;
                case 2:
                    size2 = msg.reader().readByte();
                    for (j = 0; j < size2; j++) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        for (int m = 0; m < nOption; m++) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = (short) msg.reader().readUnsignedByte();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }
                        it0.pos = msg.reader().readByte();
                        MainMenu.vecItemCreate.addElement(it0);
                    }
                    MainMenu.isCheDo = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 3:
                    MainMenu.gI().ChuyenHoaItemresult();
                    break;
                case 4:
                    size4 = msg.reader().readByte();
                    for (k = 0; k < size4; k++) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();
                        for (int m = 0; m < nOption; m++) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = (short) msg.reader().readUnsignedByte();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }
                        it0.pos = msg.reader().readByte();
                        MainMenu.vecItemCreate.addElement(it0);
                    }
                    MainMenu.isPhiPhong = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public void onWeather(Message msg) {
        try {
            byte weather = msg.reader().readByte();
            int number = msg.reader().readShort();
            int timeLimit = msg.reader().readInt();
            GameScr.onWeather(weather, number, timeLimit);
        } catch (Exception var5) {
        }

    }

    public void onInfoClan(Message msg) {
    }

    public void onPetattack(Message msg) {
        try {
            short idpet = msg.reader().readShort();
            byte idSkill = msg.reader().readByte();
            byte sizeTarget = msg.reader().readByte();
            mVector ntarget = new mVector();
            int[] dame = new int[sizeTarget];

            for (int i = 0; i < sizeTarget; ++i) {
                short idTarget = msg.reader().readShort();
                byte catTarget = msg.reader().readByte();
                int hpLost = msg.reader().readInt();
                dame[i] = hpLost;
                int hpCon = msg.reader().readInt();
                Actor ac = GameCanvas.gameScr.findActor(idTarget, catTarget);
                if (ac != null) {
                    ac.setHp(hpCon);
                    ntarget.addElement(ac);
                }
            }

            boolean var15 = true;

            short range;
            try {
                range = msg.reader().readShort();
            } catch (Exception var13) {
                range = 30;
            }

            Actor pet = GameScr.isHavePet(idpet);
            if (pet != null) {
                pet.petAttack(idSkill, ntarget, dame, range);
            }
        } catch (Exception var14) {
        }

    }

    public void onGhepPhiPhong(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0:
                    MainMenu.xuCuongHoa = 0;
                    byte typeCreate = msg.reader().readByte();
                    MainMenu.gI().typePhiPhong = typeCreate;
                    MainMenu.vecItemCreate.removeAllElements();
                    int min = msg.reader().readUnsignedByte();
                    MainMenu.gI().miniItem = min;
                    byte size = msg.reader().readByte();

                    for (int i = 0; i < size; ++i) {
                        Item it0 = new Item();
                        it0.ID = msg.reader().readShort();
                        it0.catagory = msg.reader().readByte();
                        it0.level = msg.reader().readShort();
                        it0.charClazz = msg.reader().readByte();
                        it0.name = msg.reader().readUTF();
                        it0.lock = msg.reader().readByte();
                        it0.plus = msg.reader().readByte();
                        it0.idIcon = msg.reader().readShort();
                        it0.colorname = msg.reader().readByte();
                        it0.cantrade = msg.reader().readBoolean();
                        it0.cansell = msg.reader().readBoolean();
                        it0.priceShop = msg.reader().readInt();
                        it0.quantity = msg.reader().readInt();
                        int nOption = msg.reader().readByte();

                        for (int j = 0; j < nOption; ++j) {
                            ItemOption itoption = new ItemOption();
                            itoption.id = msg.reader().readShort();
                            itoption.idColor = msg.reader().readByte();
                            itoption.value = msg.reader().readInt();
                            itoption.value2 = msg.reader().readInt();
                            it0.options.addElement(itoption);
                        }

                        it0.pos = msg.reader().readByte();
                        MainMenu.vecItemCreate.addElement(it0);
                    }

                    MainMenu.xuCuongHoa = msg.reader().readInt();
                    MainMenu.isPhiPhong = true;
                    MainMenu.gI().switchToMe(GameCanvas.gameScr);
                    break;
                case 1:
                    short idIcon = msg.reader().readShort();
                    MainMenu.gI().CreatePhiPhongresult(0, idIcon);
                    break;
                case 2:
                    short min2 = msg.reader().readShort();
                    MainMenu.gI().miniItem = min2;
            }
        } catch (Exception var11) {
        }

    }

    public void onPetBuff(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0:
                    short idchar = msg.reader().readShort();
                    byte idskill = msg.reader().readByte();
                    int time = msg.reader().readInt();
                    byte cat = msg.reader().readByte();
                    Actor ac = GameCanvas.gameScr.findActorByID(idchar, cat);
                    if (ac != null) {
                        ac.addMainBuff(idskill, time);
                    }
            }
        } catch (Exception var8) {
        }

    }

    public void onPetinfo(Message msg) {
        try {
            byte type = msg.reader().readByte();
            switch (type) {
                case 0:
                    byte size = msg.reader().readByte();
                    MainMenu.petInfo = new short[size];

                    for (int i = 0; i < size; ++i) {
                        MainMenu.petInfo[i] = msg.reader().readShort();
                    }

                    MainMenu.vecPetEat.removeAllElements();
                    boolean var12 = false;

                    byte sizeitem;
                    try {
                        sizeitem = msg.reader().readByte();
                    } catch (Exception var10) {
                        sizeitem = -1;
                    }

                    if (sizeitem == -1) {
                        MainMenu.vecPetEat = Char.inventory;
                    }

                    if (sizeitem > 0) {
                        for (int i = 0; i < sizeitem; ++i) {
                            Item it0 = new Item();
                            it0.ID = msg.reader().readShort();
                            it0.catagory = msg.reader().readByte();
                            it0.level = msg.reader().readShort();
                            it0.charClazz = msg.reader().readByte();
                            it0.name = msg.reader().readUTF();
                            it0.lock = msg.reader().readByte();
                            it0.plus = msg.reader().readByte();
                            it0.idIcon = msg.reader().readShort();
                            it0.colorname = msg.reader().readByte();
                            it0.cantrade = msg.reader().readBoolean();
                            it0.cansell = msg.reader().readBoolean();
                            it0.priceShop = msg.reader().readInt();
                            it0.quantity = msg.reader().readInt();
                            int nOption = msg.reader().readByte();

                            for (int j = 0; j < nOption; ++j) {
                                ItemOption itoption = new ItemOption();
                                itoption.id = msg.reader().readShort();
                                itoption.idColor = msg.reader().readByte();
                                itoption.value = msg.reader().readInt();
                                itoption.value2 = msg.reader().readInt();
                                it0.options.addElement(itoption);
                            }

                            MainMenu.vecPetEat.addElement(it0);
                        }
                    }
            }
        } catch (Exception var11) {
        }

    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0:
                GameCanvas.endDlg();
                GameService.gI().OnPopUp_Server(this.idDialog, this.typeDialog, (byte) 1);
                break;
            case 1:
                GameCanvas.endDlg();
                GameService.gI().OnPopUp_Server(this.idDialog, this.typeDialog, (byte) 0);
                break;
            case 2:
                SetInfoData da_ta = (SetInfoData) p;
                SetInfoData mdata = new SetInfoData();
                ++GameScr.StepHelpServer;
                if (GameScr.StepHelpServer >= GameScr.strHelpNPC.length - 1) {
                    GameCanvas.StartHelp(GameScr.strHelpNPC[GameScr.StepHelpServer], (mCommand) null, da_ta.idIcon, true);
                } else {
                    mdata.idIcon = da_ta.idIcon;
                    mCommand cmd = new mCommand(T.next, this, 2, mdata);
                    GameCanvas.StartHelp(GameScr.strHelpNPC[GameScr.StepHelpServer], cmd, mdata.idIcon, false);
                }
        }

    }

    public void onLogOut(Message msg) {
        try {
            GameScr.isMeLogin = msg.reader().readBoolean();
        } catch (Exception var3) {
        }

    }

    public static void onInappPurchaseAndroid(Message msg) {
        try {
            if (mSystem.isAndroidStore()) {
                String productId = msg.reader().readUTF();
                mSystem.Debug(productId);
                AndroidInappBilling.instance().consumePurchase(productId);
            }
        } catch (Exception var2) {
        }

    }
}
