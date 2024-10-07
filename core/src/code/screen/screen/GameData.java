package code.screen.screen;

import code.effect.EffectData;
import code.main.GameCanvas;
import code.model.FrameImage;
import code.model.ImageIcon;
import code.network.GameService;
import code.screen.Res;

import java.util.Enumeration;
import javax.microedition.lcdui.Image;

import lib.MyStream;
import lib.Rms;
import lib.Session_ME;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSystem;
import lib.mVector;

public class GameData {
    private static GameData me;
    public static FrameImage imgSkillIcon;
    public static mHashtable listImgIcon = new mHashtable();
    public static mHashtable listbyteData = new mHashtable();
    boolean loadData = false;
    static byte idWait = 0;

    public static GameData gI() {
        return me == null ? (me = new GameData()) : me;
    }

    public static void saveImgSkill(int ver, byte[] array) {
        imgSkillIcon = new FrameImage(Image.createImage((byte[]) array, 0, array.length), 20, 20);
    }

    public static void saveImgGem(byte ver, byte[] array) {
        MyStream dos = new MyStream(array, true);

        try {
            dos.writeByte(ver);
            dos.writeShort(array.length);
            dos.write(array);
            Rms.saveRMS("gemItem", dos.toByteArray());
            dos.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void setIndexWait() {
        if (GameCanvas.gameTick % 5 == 0) {
            idWait = (byte) ((idWait + 1) % (GameScr.imgloading.getHeight() / 18));
        }

    }

    public static void paintIcon(mGraphics g, short id, int x, int y) {
        ImageIcon img = getImgIcon(id);
        if (img != null) {
            if (!img.isLoad) {
                g.drawImage(img.img, x, y, 3, false);
            } else {
                g.drawRegion(GameScr.imgloading, 0, idWait * 18, 18, 18, 0, x, y, 3, false);
                setIndexWait();
            }
        }

    }

    public static void paintIcon(mGraphics g, short id, int x, int y, int align) {
        ImageIcon img = getImgIcon(id);
        if (img != null) {
            if (!img.isLoad) {
                g.drawImage(img.img, x, y, align, false);
            } else {
                g.drawRegion(GameScr.imgloading, 0, idWait * 18, 18, 18, 0, x, y, 3, false);
                setIndexWait();
            }
        }

    }

    public static void removeAllImgTree() {
        Enumeration<String> k = listImgIcon.keys();
        mVector keyremove = new mVector();
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            int intKey = Integer.parseInt(key);
            if ((intKey >= Res.ID_ITEM_MAP && intKey < Res.ID_START_SKILL) || intKey < Res.ID_ITEM || (intKey >= Res.ID_START_SKILL && intKey < Res.ID_ICON_NPC))
                keyremove.addElement(key);
        }
        for (int i = 0; i < keyremove.size(); i++) {
            String key = (String) keyremove.elementAt(i);
            ImageIcon img = (ImageIcon) listImgIcon.get(key);
            if (img != null)
                img.reset();
            listImgIcon.remove(key);
        }
    }

    public static EffectData getByteData(short id) {
        EffectData data = (EffectData) listbyteData.get("" + id);
        if (data == null) {
            data = new EffectData();
            data.load(id);
        } else {
            data.timeremove = mSystem.currentTimeMillis() + 60000L;
        }

        return data;
    }

    public static ImageIcon getImgIcon(short id) {
        ImageIcon img = (ImageIcon) listImgIcon.get(String.valueOf(id));
        if (img != null && (img == null || img.img != null)) {
            img.timeRemove = mSystem.currentTimeMillis() + 60000L;
        } else {
            if (img == null) {
                img = new ImageIcon();
                listImgIcon.put(String.valueOf(id), img);
            }

            img.id = id;
            img.isLoad = true;
            if (img.img == null && mSystem.currentTimeMillis() - img.timeGetBack >= 0L) {
                img.timeGetBack = mSystem.currentTimeMillis() + 10000L;
                if (id >= Res.ID_EFFECT_SKILL) {
                    img.img = GameCanvas.loadImage("/hu/hu" + (id - Res.ID_EFFECT_SKILL) + ".png", String.valueOf(id));
                } else if (id >= Res.ID_ICON_SKILL) {
                    img.img = GameCanvas.loadImage("/iconskill/" + (id - Res.ID_ICON_SKILL) + ".png", String.valueOf(id));
                } else if (id < Res.ID_ICON_NPC) {
                    int idImage;
                    if (id >= Res.ID_START_SKILL) {
                        idImage = id - Res.ID_START_SKILL;
                        img.img = GameCanvas.loadImage("/effskill/" + idImage + ".png", String.valueOf(id));
                    } else if (id >= Res.ID_ITEM_MAP) {
                        idImage = id - Res.ID_ITEM_MAP;
                        img.img = GameCanvas.loadImage("/tree/tob_tree_" + idImage + ".png", String.valueOf(id));
                    } else if (id >= Res.ID_CHAR) {
                        idImage = id - Res.ID_CHAR;
                        img.img = GameCanvas.loadImage("/c/big" + idImage + ".png", String.valueOf(id));
                    } else if (id >= Res.ID_NPC) {
                        img.img = GameCanvas.loadImage("/imgNPC/" + (id - Res.ID_NPC) + ".png", String.valueOf(id));
                    } else if (id >= Res.ID_ITEM) {
                        img.img = GameCanvas.loadImage("/iconitem/" + (id - Res.ID_ITEM) + ".png", String.valueOf(id));
                    } else {
                        idImage = id - Res.ID_QUAI;
                        img.img = GameCanvas.loadImage("/imgmons/" + idImage + ".png", String.valueOf(id));
                    }
                }

                if (img.img == null) {
                    if (Session_ME.gI().isConnected()) {
                        GameService.gI().doGetImgIcon(id, "getImgIcon gamedata2");
                        img.timeGetBack = mSystem.currentTimeMillis() + 10000L;
                    }
                } else {
                    img.isLoad = false;
                }

                img.timeRemove = mSystem.currentTimeMillis() + 60000L;
            }
        }

        if (img != null && img.img != null) {
            img.isLoad = false;
        }

        return img;
    }

    public static void setImgIcon(short id, byte[] data) {
        try {
            ImageIcon img = (ImageIcon) listImgIcon.get(String.valueOf(id));
            if (img == null) {
                img = new ImageIcon();
                img.id = id;
                img.isLoad = false;
                listImgIcon.put(String.valueOf(id), img);
                if (data.length > 0) {
                    img.img = Image.createImage((byte[]) data, 0, data.length);
                } else {
                    img.timeGetBack = mSystem.currentTimeMillis() + 10000L;
                    img.isLoad = true;
                }

                img.timeRemove = mSystem.currentTimeMillis() + 60000L;
            }
        } catch (Exception var3) {
        }

    }
}
