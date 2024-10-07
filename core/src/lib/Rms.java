package lib;

import code.main.GameCanvas;
import code.model.Char;
import code.model.Item;
import code.model.MainChar;
import code.model.QuickSlot;
import code.network.GameService;
import code.screen.MenuLogin;
import code.screen.SkillTemplate;
import code.screen.screen.GameScr;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Rms {
    public static final String path = "rms/";
    public static String User_Pass = "nltblogin";
    public static String User_Quick_Play = "nltbname";
    public static String Pass_Quick_Play = "nltbpass";
    public static String Quick_Slot = "nqshQuickSlot";
    public static String Auto_fight = "Autofight";
    public static String Auto_HP = "Auto_HP";
    public static String Auto_Buff = "Auto_Buff";
    public static String PerCent_HP = "PerCent_HP";
    public static String Auto_MP = "Auto_MP";
    public static String PerCent_MP = "PerCent_MP";
    public static String Quick_Slot_Potion = "potion";
    public static String Quick_Slot_Skill = "skill";
    public static String CharName = "CharName";
    public static String ScreenSize = "screenSize";
    public static String Sound = "Sound";
    public static String InDexSV = "index_sv";

    public static void saveRMSInt(String file, int x) {
        try {
            saveRMS(file, new byte[]{(byte) x});
        } catch (Exception var3) {
        }

    }

    public static String loadIP() {
        byte[] data = loadRMS("NRIP");
        return data == null ? null : new String(data);
    }

    public static void saveRMS(final String filename, final byte[] data) throws Exception {
        try {
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    FileHandle file = Gdx.files.local("rms/" + filename);
                    file.writeBytes(data, false);
                }
            });
        } catch (Exception var3) {
        }

    }

    public static void deleteRMS(String filename) {
        try {
            FileHandle file = Gdx.files.local("rms/" + filename);
            file.delete();
        } catch (Exception var2) {
        }

    }

    public static byte[] loadRMS(String filename) {
        byte[] data = null;
        try {
            FileHandle file = Gdx.files.local(path + filename);
            data = file.readBytes();
        } catch (Exception exception) {
        }
        return data;
    }

    public static void loadQuickSlot() {
        byte[] data = loadRMS(Quick_Slot + GameScr.mainChar.name);
        if (data == null) {
            MainChar.mQuickslot[0].setIsSkill(0, false);
            saveQuickSlot();
        } else {
            try {
                ByteArrayInputStream bi = new ByteArrayInputStream(data);
                DataInputStream dis = new DataInputStream(bi);

                for (int i = 0; i < MainChar.mQuickslot.length; ++i) {
                    QuickSlot qs = MainChar.mQuickslot[i];
                    int type = dis.readByte();
                    if (type != 1) {
                        if (type == 2) {
                            try {
                                short idicon = dis.readShort();

                                for (int j = 0; j < Char.inventory.size(); ++j) {
                                    Item ite = (Item) Char.inventory.elementAt(j);
                                    if (ite != null && ite.idIcon == idicon) {
                                        qs.setIsPotion(idicon);
                                    }
                                }
                            } catch (Exception var10) {
                            }
                        }
                    } else {
                        int idskill = dis.readByte();
                        boolean isbuff = dis.readBoolean();

                        for (int j = 0; j < GameScr.vec_skill.size(); ++j) {
                            SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(j);
                            if (skill != null && j == idskill && Char.levelSkill[j] > 0 && (skill.type == SkillTemplate.TYPE_BUFF || skill.type == SkillTemplate.TYPE_ATTACK)) {
                                qs.setIsSkill(idskill, isbuff);
                            }
                        }
                    }
                }

                dis.close();
            } catch (Exception var11) {
                mSystem.println("loi load qsl " + var11.toString());
            }

        }
    }

    public static int loadRMSInt(String file) {
        byte[] b = loadRMS(file);
        return b == null ? -1 : b[0];
    }

    public static void saveRMSString(String filename, String s) {
        try {
            saveRMS(filename, s.getBytes("UTF-8"));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static String loadRMSString(String filename) {
        byte[] data = loadRMS(filename);
        if (data == null) {
            return null;
        } else {
            try {
                String s = new String(data, "UTF-8");
                return s;
            } catch (Exception var3) {
                return new String(data);
            }
        }
    }

    public static void clearRMS(String fileName) {
        try {
            Gdx.files.local(fileName).delete();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static String getPathRMS() {
        return "rms/" + Gdx.files.getLocalStoragePath();
    }

    public static void saveQuickSlot() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            for (int i = 0; i < MainChar.mQuickslot.length; ++i) {
                QuickSlot qs = MainChar.mQuickslot[i];
                dos.writeByte(qs.quickslotType);
                if (qs.quickslotType == 1) {
                    dos.writeByte(qs.getSkillType());
                    dos.writeBoolean(qs.getBuffType());
                } else if (qs.quickslotType == 2) {
                    dos.writeShort(qs.idicon);
                }
            }

            byte[] bData = bo.toByteArray();
            saveRMS(Quick_Slot + GameScr.mainChar.name, bData);
            dos.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        GameService.gI().doSendChangeQuickSlot();
    }

    public static String loadCharName() {
        String name = "";
        byte[] data = loadRMS(CharName);
        if (data != null) {
            ByteArrayInputStream bi = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bi);

            try {
                name = dis.readUTF();
            } catch (Exception var17) {
            } finally {
                try {
                    bi.close();
                } catch (Exception var16) {
                }

                try {
                    dis.close();
                } catch (Exception var15) {
                }

            }
        }

        return name;
    }

    public static byte LoadScreenSize() {
        byte size = 1;
        byte[] data = loadRMS(ScreenSize);
        if (data != null) {
            ByteArrayInputStream bi = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bi);

            try {
                size = dis.readByte();
            } catch (Exception var17) {
            } finally {
                try {
                    bi.close();
                } catch (Exception var16) {
                }

                try {
                    dis.close();
                } catch (Exception var15) {
                }

            }
        }

        return size;
    }

    public static void saveScreenSize() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            dos.writeByte(TCanvas.ScreenSize);
            saveRMS(ScreenSize, bo.toByteArray());
            dos.close();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (Exception var14) {
            }

            try {
                bo.close();
            } catch (Exception var13) {
            }

        }

    }

    public static void saveMainCharName() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            dos.writeUTF(GameScr.mainChar.name);
            saveRMS(CharName, bo.toByteArray());
            dos.close();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (Exception var14) {
            }

            try {
                bo.close();
            } catch (Exception var13) {
            }

        }

    }

    public static void deleteRecordCompareToName() {
        try {
            Gdx.app.postRunnable(new Runnable() {
                public void run() {
                    FileHandle external = Gdx.files.getFileHandle(Rms.getPathRMS(), Files.FileType.Local);

                    for (int i = 0; i < external.list().length; ++i) {
                    }

                }
            });
        } catch (Exception var1) {
        }

    }

    public static void deleteAllRMS() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                FileHandle external = Gdx.files.getFileHandle(Rms.getPathRMS(), Files.FileType.Local);
                external.emptyDirectory();
            }
        });
    }

    public static void saveSound() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            for (int i = 0; i < GameCanvas.menuSelectitem.isTabFocus.length; ++i) {
                dos.writeBoolean(GameCanvas.menuSelectitem.isTabFocus[i]);
            }

            byte[] bData = bo.toByteArray();
            saveRMS(Sound, bData);
            dos.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void saveAutoBuff() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            for (int i = 0; i < GameCanvas.menuSelectitem.idSkillBuff.length; ++i) {
                dos.writeShort(GameCanvas.menuSelectitem.idSkillBuff[i][0]);
                dos.writeShort(GameCanvas.menuSelectitem.idSkillBuff[i][1]);
                dos.writeShort(GameCanvas.menuSelectitem.idSkillBuff[i][3]);
            }

            byte[] bData = bo.toByteArray();
            saveRMS(Auto_Buff + GameScr.mainChar.name, bData);
            dos.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void LoadAutoBuff() {
        byte[] data = loadRMS(Auto_Buff + GameScr.mainChar.name);
        if (data != null) {
            ByteArrayInputStream bi = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bi);

            try {
                for (int i = 0; i < GameCanvas.menuSelectitem.idSkillBuff.length; ++i) {
                    GameCanvas.menuSelectitem.idSkillBuff[i][0] = dis.readShort();
                    GameCanvas.menuSelectitem.idSkillBuff[i][1] = dis.readShort();
                    GameCanvas.menuSelectitem.idSkillBuff[i][3] = dis.readShort();
                }
            } catch (Exception var16) {
            } finally {
                try {
                    bi.close();
                } catch (Exception var15) {
                }

                try {
                    dis.close();
                } catch (Exception var14) {
                }

            }
        }

    }

    public static void saveIndexSV() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bo);

        try {
            dos.writeByte(MenuLogin.indexServer);
            saveRMS(InDexSV, bo.toByteArray());
            dos.close();
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (Exception var14) {
            }

            try {
                bo.close();
            } catch (Exception var13) {
            }

        }

    }

    public static void LoadIndexSv() {
        byte[] data = loadRMS(InDexSV);
        if (data != null) {
            ByteArrayInputStream bi = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bi);

            try {
                MenuLogin.indexServer = dis.readByte();
            } catch (Exception var16) {
            } finally {
                try {
                    bi.close();
                } catch (Exception var15) {
                }

                try {
                    dis.close();
                } catch (Exception var14) {
                }

            }
        }

    }

    public static void LoadSound() {
        byte[] data = loadRMS(Sound);
        if (data != null) {
            ByteArrayInputStream bi = new ByteArrayInputStream(data);
            DataInputStream dis = new DataInputStream(bi);

            try {
                for (int i = 0; i < GameCanvas.menuSelectitem.isTabFocus.length; ++i) {
                    GameCanvas.menuSelectitem.isTabFocus[i] = dis.readBoolean();
                }
            } catch (Exception var16) {
            } finally {
                try {
                    bi.close();
                } catch (Exception var15) {
                }

                try {
                    dis.close();
                } catch (Exception var14) {
                }

            }
        }

    }

    public static void DeleteRMS(String str) {
    }
}
