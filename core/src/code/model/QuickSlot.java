package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.SkillTemplate;
import code.screen.screen.ChangScr;
import code.screen.screen.FontTeam;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;

import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.mGraphics;
import lib.mSystem;
import lib2.mFont;

public class QuickSlot {
    public byte quickslotType = -1;
    public byte idSkill = -1;
    public byte ItemType;
    public short iDPotion;
    public static final byte TYPE_SKILL = 1;
    public static final byte TYPE_ITEM = 2;
    public static final byte TYPE_POTION = 2;
    public static Image img = null;
    public boolean is_Buff = false;
    public short idicon;
    public int cooldown;
    public int num;
    public short idIninven;
    public short idimgPotion;
    private long timewait;
    private long timewaitChar;
    public byte typePotion;
    public byte idQuickSlot;

    public static void getImg() {
        if (img == null) {
            img = GameCanvas.loadImage("/interface/delayskill.png");
        }

    }

    public QuickSlot(int id) {
        this.quickslotType = -1;
        this.idQuickSlot = (byte) id;
    }

    public void setIsSkill(int skillType, boolean isBuff) {
        try {
            if (Char.levelSkill[skillType] > 0) {
                this.quickslotType = 1;
                this.idSkill = (byte) skillType;
                this.is_Buff = isBuff;
                SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(this.idSkill);
                if (skill != null && Char.levelSkill != null) {
                    this.idicon = (short) skill.iconId;
                    this.cooldown = skill.getCoolDown(Char.levelSkill[this.idSkill] - 1);
                }

                if (this.cooldown < 1000) {
                    this.cooldown = 1000;
                }

                if (this.idSkill == 0) {
                    this.cooldown = 100;
                }
            } else {
                GameCanvas.startOKDlg(T.chuahoc);
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }

    }

    public void setIsPotion(short idicon) {
        this.quickslotType = 2;
        this.idicon = idicon;

        for (int i = 0; i < Char.inventory.size(); ++i) {
            Item ite = (Item) Char.inventory.elementAt(i);
            if (ite != null && ite.idIcon == this.idicon) {
                this.idIninven = (short) i;
                this.iDPotion = ite.ID;
                this.typePotion = ite.type;
            }
        }

        this.cooldown = 2000;
    }

    public void setIsPotion(short idinInven, short idItem, byte typePotion, int idicon) {
        this.quickslotType = 2;
        this.idIninven = idinInven;
        this.iDPotion = idItem;
        this.cooldown = 2000;
        this.typePotion = typePotion;
    }

    public void reset() {
        this.idIninven = -1;
        this.iDPotion = -1;
        this.cooldown = -1;
        this.typePotion = -1;
        this.quickslotType = -1;
        this.idicon = -1;
    }

    public void paint(mGraphics g, int x, int y, int ypc) {
        try {
            if (this.quickslotType == -1) {
                return;
            }

            if (this.quickslotType == 1) {
                ImageIcon imgskill = GameData.getImgIcon((short) (this.idicon + Res.ID_ICON_SKILL));
                if (imgskill != null && imgskill.img != null) {
                    if (GameCanvas.isTouch) {
                        g.drawImage(imgskill.img, x, y, 0, false);
                    } else {
                        g.drawImage(imgskill.img, x, y, 3, false);
                    }
                } else if (GameCanvas.isTouch) {
                    g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, x + 3, y + 4, 0, false);
                } else {
                    g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, x, y + 1, 3, false);
                }
            } else if (this.quickslotType == 2) {
                for (int i = 0; i < Char.inventory.size(); ++i) {
                    Item item = (Item) Char.inventory.elementAt(i);
                    if (item != null && item.idIcon == this.idicon) {
                        ImageIcon img = GameData.getImgIcon((short) (item.idIcon + Res.ID_ITEM));
                        this.num = 0;
                        if (item != null) {
                            this.num = item.quantity;
                        }

                        if (GameCanvas.isTouch) {
                            int ypt = 20;
                            FontTeam.numberSmall_yeallow.drawString(g, String.valueOf(this.num), x + 10, y + ypt - 1, 2, false);
                            if (img != null && img.img != null) {
                                g.drawImage(img.img, x + 5, y + 3, 0, false);
                            } else {
                                g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, x, y, 0, true);
                            }
                        } else if (img != null && img.img != null) {
                            if (!img.isLoad) {
                                g.drawImage(img.img, x + 5 - 12, y + 3 - 10, 0, false);
                            } else {
                                g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, x - 12, y - 10, 0, true);
                            }
                        }
                    }
                }
            }

            if (!GameScr.mainChar.canUseSkill() && this.quickslotType == 1) {
                if (GameCanvas.isTouch) {
                    g.drawRegion(QuickSlot.img, 0, 0, 28, 28, 0, x, y, 0, false);
                } else {
                    g.drawRegion(QuickSlot.img, 0, 0, 28, 28, 0, x, y, 3, false);
                }
            }

            if (this.timewait - mSystem.currentTimeMillis() > 0L) {
                if (GameCanvas.isTouch) {
                    int xq = 0;
                    int yq = 0;
                    if (this.idQuickSlot == 0) {
                        xq = 3;
                        yq = -11;
                    }

                    if (this.idQuickSlot == 4) {
                        yq = -3;
                        xq = 2;
                    }

                    int yp = 0;
                    if (mSystem.isAndroid) {
                        yp = -mFont.number_white.getHeight() / 2 + 2;
                    }

                    int yip = 0;
                    if (mSystem.isIP) {
                        yip = -1;
                    }

                    int xpc = 0;
                    if (mSystem.isPC) {
                        xpc = 24;
                    }

                    FontTeam.numberSmall_white.drawString(g, this.gettimeCooldown(), x - 20 + xq + 7 + xpc, y + yq + yp + yip + ypc, 2, false);
                } else {
                    FontTeam.numberSmall_white.drawString(g, this.gettimeCooldown(), x, y, 2, false);
                }
            }
        } catch (Exception var10) {
            Cout.println("ERROR QUICKSLOT " + var10.toString());
        }

    }

    public boolean isBuff() {
        return this.is_Buff;
    }

    public byte getSkillType() {
        return this.idSkill;
    }

    public short getidPotion() {
        return this.iDPotion;
    }

    public boolean getBuffType() {
        return this.is_Buff;
    }

    public void setCoolDownChar(int time) {
        this.timewaitChar = mSystem.currentTimeMillis() + (long) time;
    }

    public void startCoolDown(int time) {
        this.timewait = mSystem.currentTimeMillis() + (long) this.cooldown + (long) time;
    }

    public boolean canfight() {
        return this.timewait - mSystem.currentTimeMillis() <= 0L && this.timewaitChar - mSystem.currentTimeMillis() <= 0L && this.quickslotType == 1;
    }

    public boolean canUsePotion() {
        return this.timewait - mSystem.currentTimeMillis() <= 0L && this.quickslotType == 2 && this.num > 0;
    }

    public void update() {
    }

    public int getPotionType() {
        return 0;
    }

    public byte getItemType() {
        return this.ItemType;
    }

    private String gettimeCooldown() {
        long time = (this.timewait - mSystem.currentTimeMillis()) / 1000L;
        long t = 0L;
        if (time == 0L) {
            t = (this.timewait - mSystem.currentTimeMillis()) % 1000L / 100L;
            if (t > 0L) {
                return String.valueOf(t);
            }
        } else if (time > 0L) {
            return "" + time;
        }

        return "";
    }

    public void setIsIteam(int potionType) {
        this.quickslotType = 2;
        this.ItemType = (byte) potionType;
    }

    public void setIsNothing() {
        this.quickslotType = -1;
    }
}
