package code.model;

import code.main.GameCanvas;
import code.network.GameService;
import code.screen.SkillTemplate;
import code.screen.Util;
import code.screen.screen.GameScr;
import lib.Rms;
import lib.Tilemap;
import lib.mHashtable;
import lib.mSystem;
import lib.mVector;

public class MainChar extends Char {
    public boolean check;
    public boolean canMove;
    public static QuickSlot[] mQuickslot;
    public byte idSkill = 0;
    public byte limItemBag;
    public static mVector gemitemImbue = new mVector();
    public static mVector listFriend = new mVector();
    public static mHashtable timegetIemAuto = new mHashtable();
    public static long timeStartCoolDow_HP;
    public static long timeStartCoolDow_MP;
    public mVector tItems = new mVector();
    public mVector rItems = new mVector();
    public static short[] itemQuest;
    public int dedicationPoint;
    public int pArena;
    public static String[] infoOptionClan;
    public static ItemOption itemOptionMainChar = null;
    public static short idItemOptioninVector = -1;
    public boolean isFirstTimeGetItem;
    public boolean isFirstTimeGetMoney;
    public boolean isFirstTimeGetPotion;
    public String nameHusband_wife = "";
    public mVector options = new mVector();
    public static boolean blockkey = false;
    public static byte MaxInven = 40;
    public boolean sendMove = false;
    public static boolean isloadQSL = false;
    public static byte[] skillIntro = new byte[]{0, 1, 2, 1, 2, 1};

    public MainChar() {
        this.height = 60;
        this.catagory = 0;
        this.speed = 7;
        this.sendMove = true;
    }

    public String getPercent() {
        return this.lvpercent / 10 + "." + this.lvpercent % 10 + "%";
    }

    private void updateWayPointer() {
        if (Util.abs(this.x - this.xTo) <= 3 && Util.abs(this.y - this.yTo) <= 3) {
            for (int i = this.posTransRoad.length - 1 - this.countRoad; i >= 0; --i) {
                if (this.posTransRoad[i] > 0) {
                    byte xx = (byte) (GameScr.xStart + (this.posTransRoad[i] >> 8));
                    byte yy = (byte) (GameScr.yStart + (this.posTransRoad[i] & 255));
                    if (GameScr.iTaskAuto != 2 && (this.dir == 1 || Tilemap.isOfflineMap) && GameCanvas.gameScr.checkCanChangeMap(xx * 16 + this.speed, yy * 16 + this.speed, this.dir)) {
                        this.posTransRoad = null;
                        this.countRoad = 0;
                        return;
                    }

                    this.moveTo((short) (xx * 16 + this.speed), (short) (yy * 16 + this.speed));
                    this.posTransRoad[i] = -1;
                    ++this.countRoad;
                    break;
                }

                if (i == 0) {
                    this.posTransRoad = null;
                    this.countRoad = 0;
                    break;
                }
            }
        }

    }

    public static void loadQuickSlot() {
        mQuickslot = new QuickSlot[5];

        for (int i = 0; i < 5; ++i) {
            mQuickslot[i] = new QuickSlot(i);
        }

        if (!GameScr.isIntro) {
            Rms.loadQuickSlot();
            Rms.LoadAutoBuff();
        }

        isloadQSL = true;
    }

    public static void newQuickSlot() {
        try {
            mQuickslot = new QuickSlot[5];

            for (int i = 0; i < 5; ++i) {
                mQuickslot[i] = new QuickSlot(i);
            }

            mQuickslot[0].setIsSkill(0, false);
            Rms.saveQuickSlot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadAuto() {
        GameCanvas.menuSelectitem.isAutoDanh[0] = Rms.loadRMSInt(Rms.Auto_fight) == 1;
        GameCanvas.menuSelectitem.isAutoDanh[1] = Rms.loadRMSInt(Rms.Auto_HP) == 1;
        GameCanvas.menuSelectitem.isAutoDanh[3] = Rms.loadRMSInt(Rms.Auto_MP) == 1;
        GameCanvas.menuSelectitem.indexPerHp = Rms.loadRMSInt(Rms.PerCent_HP);
        if (GameCanvas.menuSelectitem.indexPerHp < 1) {
            GameCanvas.menuSelectitem.indexPerHp = 5;
        }

        GameCanvas.menuSelectitem.indexPerMp = Rms.loadRMSInt(Rms.PerCent_MP);
        if (GameCanvas.menuSelectitem.indexPerMp < 1) {
            GameCanvas.menuSelectitem.indexPerMp = 5;
        }

        Rms.LoadSound();
    }

    public static void putSkill2QuickSlot() {
        for (int i = 0; i < 5; ++i) {
            SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(skillIntro[i]);
            boolean isbuff = false;
            if (skill != null) {
                if (skill.type == SkillTemplate.TYPE_BUFF) {
                    isbuff = true;
                }

                mQuickslot[i].setIsSkill(skillIntro[i], isbuff);
            }
        }

    }

    private void checkChangemapTouch() {
        if (GameCanvas.instance.hasPointerEvents() && GameScr.iTaskAuto != 2) {
            if (this.canMove && !this.check && this.state == 1) {
                int xx = 0;
                int yy = 0;
                if (this.dir == 2) {
                    xx = -32;
                } else if (this.dir == 3) {
                    xx = 32;
                }

                if (this.dir == 0) {
                    yy = 32;
                } else if (this.dir == 1) {
                    yy = -32;
                }

                if ((Math.abs(this.x - this.xBegin) >= 16 || Math.abs(this.y - this.yBegin) >= 16) && GameCanvas.gameScr.checkCanChangeMap(this.x + xx, this.y + yy, this.dir)) {
                    this.posTransRoad = null;
                    this.check = true;
                    return;
                }
            }

            if (this.state == 1) {
                this.canMove = true;
            } else {
                this.canMove = false;
            }
        }

    }

    public void update() {
        super.update();
        if (this.hp <= 0 && this.state != 3) {
            this.BeginDie();
        }

        if (mSystem.currentTimeMillis() - GameScr.timeMove >= 0L && !this.sendMove) {
            GameScr.timeMove = mSystem.currentTimeMillis() + (long) GameScr.dtmove;
            GameService.gI().moveChar(this.x, this.y);
            this.sendMove = true;
        }

        if (this.posTransRoad != null) {
            this.updateWayPointer();
            this.check = false;
        }

        if (this.state == 0 && this.posTransRoad != null) {
            this.posTransRoad = null;
        }

        this.checkChangemapTouch();
    }

    public boolean isMainChar() {
        return true;
    }

    public boolean setFireChar(Actor c) {
        if (c.beFire()) {
            return true;
        } else if (!c.canFocus()) {
            return false;
        } else if (c.equals(this)) {
            return false;
        } else if (Tilemap.ismapLang) {
            return false;
        } else if (this.typePK != 0 && this.typePK != 6) {
            if (c.getTypeMove() == 5) {
                return false;
            } else if (c.isNPC()) {
                return false;
            } else {
                if (c.catagory == 0) {
                    if (this.typePK == 0 || c.getTypePK() == 0) {
                        return true;
                    }

                    if (c.getTypePK() == 0 || c.getTypePK() == 6) {
                        return true;
                    }

                    if (c.getState() == 3) {
                        return false;
                    }

                    if (this.typePK == 6) {
                        return false;
                    }

                    if (this.typePK == -1 && c.getTypePK() != 0 && c.getTypePK() != 6) {
                        return false;
                    }

                    if (this.typePK != -1 && this.typePK != 0 && this.typePK != 6 && (this.typePK == c.getTypePK() || c.getTypePK() == -1)) {
                        return false;
                    }

                    if (this.typePK >= 1 && this.typePK <= 5 && c.getTypePK() >= 1 && c.getTypePK() <= 5 && this.typePK != c.getTypePK()) {
                        return true;
                    }

                    if (this.typePK >= 1 && this.typePK <= 5 && c.getTypePK() >= 1 && c.getTypePK() <= 5 && this.typePK == c.getTypePK()) {
                        return false;
                    }
                }

                if (c.catagory == 1) {
                    if (c.getTypeMove() == 6) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    public static void CheckQuicSlotSkill(int SkillType) {
        for (int i = 0; i < mQuickslot.length; ++i) {
            if (mQuickslot[i] != null && mQuickslot[i].quickslotType == 1 && mQuickslot[i].idSkill == SkillType) {
                mQuickslot[i].quickslotType = 0;
                mQuickslot[i].idSkill = -1;
                mQuickslot[i].idicon = -1;
                mQuickslot[i].cooldown = -1;
            }
        }

    }

    public static void CheckQuicSlotPotion(short idinInven) {
        for (int i = 0; i < mQuickslot.length; ++i) {
            if (mQuickslot[i] != null && mQuickslot[i].quickslotType == 2 && mQuickslot[i].typePotion == idinInven) {
                mQuickslot[i].idIninven = -1;
                mQuickslot[i].iDPotion = -1;
                mQuickslot[i].cooldown = -1;
                mQuickslot[i].typePotion = -1;
                mQuickslot[i].quickslotType = -1;
            }
        }

    }

    public boolean isFullInven() {
        return inventory.size() >= MaxInven;
    }
}
