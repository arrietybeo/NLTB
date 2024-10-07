package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.Util;
import code.screen.screen.ChangScr;
import code.screen.screen.FontTeam;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.InfoTextShow;
import code.screen.screen.MainMenu;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Item extends Actor {
    public static byte TYPE_AO = 0;

    public static byte TYPE_QUAN = 1;

    public static byte TYPE_NON = 2;

    public static byte TYPE_KIEM = 3;

    public static byte TYPE_DAO = 4;

    public static byte TYPE_CUNG = 5;

    public static byte TYPE_BUA = 6;

    public static byte TYPE_BUT = 7;

    public static byte TYPE_TOC = 8;

    public static byte TYPE_NHAN = 9;

    public static byte TYPE_DAY_CHUYEN = 10;

    public static byte TYPE_NGOC_BOI = 11;

    public static byte TYPE_AO_CHOANG = 12;

    public static byte TYPE_HP = 14;

    public static byte TYPE_MP = 15;

    public int quantity = 1;

    public short idTemplate = 0;

    public byte gender = 2;

    public byte idclass = 5;

    public byte type;

    public byte pos;

    public mVector options = new mVector();

    public int idIcon;

    public int priceShop;

    public byte colorname;

    public byte lock = 0;

    public short xTo;

    public short yTo;

    public short level;

    boolean isFly;

    public byte priceType;

    public int ddx;

    public int ddy;

    public int vx;

    public int vy;

    public int angle;

    public int speed;

    public String name = "";

    public static String[] moneyType;

    public boolean isItemShop = false;

    public long timewait;

    private long timelive;

    public short[] arrPart;

    DataSkillEff effPhatSang = null;

    DataSkillEff effIcon = null;

    public static Effect_UpLv_Item eff_UpLv = new Effect_UpLv_Item();

    public void setTimelive(long time) {
        this.timelive = time;
    }

    public void setXYflyto(short xto, short yto) {
        this.xTo = xto;
        this.yTo = yto;
        this.speed = 15;
        this.isFly = true;
    }

    public void setColorname(byte color) {
        this.colorname = color;
    }

    public ItemTemplate getItemTemplate() {
        return (ItemTemplate) ItemTemplate.ALL_ITEM_TEMPLATE.get((new StringBuilder(String.valueOf(this.idTemplate))).toString());
    }

    public static short ID_EFF_PHAT_SANG = 233;

    public static short ID_EFF_ICON = 232;

    mVector different;

    boolean isMatchX;

    boolean isMatchY;

    public boolean cantrade;

    public boolean cansell;

    public byte plus;

    public int charClazz;

    public boolean isSelling;

    public int dayUse;

    public static final byte COLOR_ITEM_WHITE = 0;

    public static final byte COLOR_ITEM_BLUE = 1;

    public static final byte COLOR_ITEM_YELLOW = 2;

    public static final byte COLOR_ITEM_VIOLET = 3;

    public static final byte COLOR_ITEM_OGRANGE = 4;

    public static final byte COLOR_ITEM_GREEN = 5;

    public static final byte COLOR_ITEM_RED = 6;

    public DataSkillEff getEffPhatSang() {
        if (this.effPhatSang != null)
            return this.effPhatSang;
        return null;
    }

    public DataSkillEff getEffIcon() {
        if (this.effIcon != null)
            return this.effIcon;
        return null;
    }

    public String getInfoItem() {
        String info = (new StringBuilder(String.valueOf(this.name))).toString();
        if (this.charClazz < 5 && this.charClazz > -1)
            info = String.valueOf(info) + "\n" + T.className[this.charClazz];
        if (this.level > 0)
            info = String.valueOf(info) + "\n" + T.level + ": " + this.level;
        for (int i = 0; i < this.options.size(); i++) {
            String info1 = "";
            ItemOption item = (ItemOption) this.options.elementAt(i);
            String name1 = item.getName(0);
            if (item.isNormal() || item.isPercent() || item.isTime() || item.isOptionMinMax()) {
                String vl1 = (new StringBuilder(String.valueOf(item.value))).toString();
                if (item.isTime()) {
                    vl1 = Util.convertMilis2S(item.value);
                } else if (item.isPercent() || item.isPercentSecond()) {
                    vl1 = Util.getDotPercent(item.value);
                }
                info1 = Util.replaceString(name1, "#", vl1);
                if (item.value2 > 0) {
                    String vltemp = (new StringBuilder(String.valueOf(item.value2))).toString();
                    if (item.isPercent())
                        vltemp = Util.getDotPercent(item.value2);
                    if (item.isPercentSecond() || item.isPointSecond() || item.isTime())
                        vltemp = Util.convertMilis2S(item.value2);
                    if (item.isOptionMinMax()) {
                        info1 = Util.replaceString(info1, "#", vltemp);
                    } else {
                        info1 = String.valueOf(info1) + "\n" + Util.replaceString(String.valueOf(name1) + " " + T.dongdoi, "#", vltemp);
                    }
                }
            } else if (item.isOptionPercentPercent()) {
                info1 = Util.replaceString(item.getName(0), "#", Util.getDotPercent(item.value));
                info1 = Util.replaceString(info1, "#", Util.getDotPercent(item.value2));
            } else {
                String vltemp = (new StringBuilder(String.valueOf(item.value2))).toString();
                if (item.isPercentSecond())
                    vltemp = Util.convertMilis2S(item.value2);
                info1 = Util.replaceString(name1, "#", item.isPercentSecond() ? Util.getDotPercent(item.value) : (new StringBuilder(String.valueOf(item.value))).toString());
                info1 = Util.replaceString(info1, "#", vltemp);
            }
            info = String.valueOf(info) + "\n" + info1;
        }
        if (this.isItemShop)
            info = String.valueOf(info) + "\n" + T.gia + this.priceShop + " " + moneyType[this.priceType];
        return info;
    }

    public ItemOption getOptionById(int idOption) {
        for (int i = 0; i < this.options.size(); i++) {
            ItemOption item = (ItemOption) this.options.elementAt(i);
            if (item.id == idOption)
                return item;
        }
        return null;
    }

    public Item() {
        this.different = new mVector();
    }

    public mVector getInfoItemShow(Item itCompare, boolean showPrice) {
        mVector info = new mVector();
        info.addElement(new InfoTextShow(new String[]{this.name}, this.colorname));
        InfoTextShow tshow = null;
        if (this.charClazz < 5 && this.charClazz > -1)
            info.addElement(new InfoTextShow(new String[]{T.className[this.charClazz]}, (this.charClazz == GameScr.mainChar.clazz) ? 0 : 6));
        if (this.level > 0)
            info.addElement(new InfoTextShow(new String[]{String.valueOf(T.level) + ": " + this.level}, (GameScr.mainChar.level >= this.level) ? 0 : 6));
        mVector infoOption = new mVector();
        int i;
        for (i = 0; i < this.options.size(); i++) {
            String info1 = "";
            ItemOption item = (ItemOption) this.options.elementAt(i);
            ItemOption itemcompare = null;
            if (itCompare != null)
                itemcompare = itCompare.getOptionById(item.id);
            String name1 = item.getName(0);
            if (item.isNormal() || item.isPercent() || item.isTime() || item.isOptionMinMax()) {
                String vl1 = (new StringBuilder(String.valueOf(item.value))).toString();
                if (item.isTime()) {
                    vl1 = Util.convertMilis2S(item.value);
                } else if (item.isPercent() || item.isPercentSecond()) {
                    vl1 = Util.getDotPercent(item.value);
                }
                info1 = Util.replaceString(name1, "#", vl1);
                if (item.value2 > 0) {
                    String vltemp = (new StringBuilder(String.valueOf(item.value2))).toString();
                    if (item.isPercent())
                        vltemp = Util.getDotPercent(item.value2);
                    if (item.isPercentSecond() || item.isPointSecond() || item.isTime())
                        vltemp = Util.convertMilis2S(item.value2);
                    if (item.isOptionMinMax()) {
                        info1 = Util.replaceString(info1, "#", vltemp);
                        tshow = new InfoTextShow(new String[]{info1}, item.getColorPaint(false));
                        tshow.id = item.id;
                        if (itemcompare != null)
                            if (itemcompare.value2 > item.value2) {
                                tshow.idCompare = 0;
                            } else if (itemcompare.value2 < item.value2) {
                                tshow.idCompare = 1;
                            }
                        infoOption.addElement(tshow);
                    } else {
                        tshow = new InfoTextShow(new String[]{info1}, item.getColorPaint(false));
                        tshow.id = item.id;
                        if (itemcompare != null)
                            if (itemcompare.value > item.value) {
                                tshow.idCompare = 0;
                            } else if (itemcompare.value < item.value) {
                                tshow.idCompare = 1;
                            }
                        infoOption.addElement(tshow);
                        infoOption.addElement(new InfoTextShow(new String[]{Util.replaceString(name1 + " " + T.dongdoi, "#", vltemp)}, item.getColorPaint(false)));

//                        infoOption.addElement(new InfoTextShow(new String[]{Util.replaceString(String.valueOf(name1) + " " + T.dongdoi, "#", vltemp)}item.getColorPaint(false)));
                    }
                } else {
                    tshow = new InfoTextShow(new String[]{info1}, item.getColorPaint(false));
                    tshow.id = item.id;
                    if (itemcompare != null)
                        if (itemcompare.value > item.value) {
                            tshow.idCompare = 0;
                        } else if (itemcompare.value < item.value) {
                            tshow.idCompare = 1;
                        }
                    infoOption.addElement(tshow);
                }
            } else {
                String vltemp = (new StringBuilder(String.valueOf(item.value2))).toString();
                if (item.isPercentSecond())
                    vltemp = Util.convertMilis2S(item.value2);
                if (item.isOptionPercentPercent()) {
                    info1 = Util.replaceString(item.getName(0), "#", Util.getDotPercent(item.value));
                    info1 = Util.replaceString(info1, "#", Util.getDotPercent(item.value2));
                } else {
                    info1 = Util.replaceString(name1, "#", item.isPercentSecond() ? Util.getDotPercent(item.value) : (new StringBuilder(String.valueOf(item.value))).toString());
                    info1 = Util.replaceString(info1, "#", vltemp);
                }
                tshow = new InfoTextShow(new String[]{info1}, item.getColorPaint(false));
                tshow.id = item.id;
                if (itemcompare != null)
                    if (itemcompare.value > item.value) {
                        tshow.idCompare = 0;
                    } else if (itemcompare.value < item.value) {
                        tshow.idCompare = 1;
                    }
                infoOption.addElement(tshow);
            }
        }
        infoOption = MainMenu.setGroupOption(infoOption);
        for (i = 0; i < infoOption.size(); i++)
            info.addElement(infoOption.elementAt(i));
        if (this.isItemShop) {
            info.addElement(new InfoTextShow(new String[]{String.valueOf(T.gia) + Res.getDotNumber(this.priceShop) + " " + moneyType[this.priceType]}, 0));
        } else if (showPrice) {
            info.addElement(new InfoTextShow(new String[]{String.valueOf(T.gia_ban_lai) + this.priceShop + " " + moneyType[this.priceType]}, 0));
        }
        if (itCompare != null) {
            mVector infodif = itCompare.getInfoItemShow((Item) null, false);
            boolean havedif = false;
            for (int j = 0; j < infodif.size(); j++) {
                InfoTextShow tf = (InfoTextShow) infodif.elementAt(j);
                boolean istrung = false;
                for (int k = 0; k < info.size(); k++) {
                    InfoTextShow tf1 = (InfoTextShow) info.elementAt(k);
                    if (tf.id == tf1.id) {
                        istrung = true;
                        break;
                    }
                }
                if (!istrung) {
                    if (!havedif) {
                        info.addElement(new InfoTextShow(null, 0));
                        havedif = true;
                    }
                    tf.idCompare = 3;
                    info.addElement(tf);
                }
            }
        }
        return info;
    }

    public int getPriceSellShop() {
        return 10 * (this.level + this.colorname + this.options.size());
    }

    public byte[] getColorPaint() {
        int size = 0;
        size = this.options.size() + 1;
        if (this.isItemShop)
            size = this.options.size() + 2;
        byte[] arr = new byte[size];
        arr[0] = this.colorname;
        if (this.isItemShop)
            arr[size - 1] = 2;
        for (int i = 0; i < this.options.size(); i++) {
            ItemOption item = (ItemOption) this.options.elementAt(i);
            if (item != null)
                arr[i + 1] = item.idColor;
        }
        return arr;
    }

    public byte[] getColorPaint(int lineWidth) {
        mVector vcolor = new mVector();
        ItemOption item1 = new ItemOption();
        item1.idColor = this.colorname;
        vcolor.addElement(item1);
        for (int i = 0; i < this.options.size(); i++) {
            String info1 = "";
            ItemOption item = (ItemOption) this.options.elementAt(i);
            String name = item.getName(0);
            if (item.isNormal() || item.isPercent() || item.isTime() || item.isOptionMinMax()) {
                info1 = Util.replaceString(name, "#", (item.isPercent() || item.isPercentSecond()) ? Util.getDotPercent(item.value) : (new StringBuilder(String.valueOf(item.value))).toString());
                if (item.value2 > 0)
                    if (item.isOptionMinMax()) {
                        info1 = Util.replaceString(info1, "#", (new StringBuilder(String.valueOf(item.value2))).toString());
                    } else {
                        info1 = String.valueOf(info1) + "\n" + Util.replaceString(String.valueOf(name) + " " + T.dongdoi, "#", item.isPercent() ? Util.getDotPercent(item.value2) : (new StringBuilder(String.valueOf(item.value2))).toString());
                    }
            } else if (item.isOptionPercentPercent()) {
                info1 = Util.replaceString(name, "#", Util.getDotPercent(item.value));
                info1 = Util.replaceString(info1, "#", Util.getDotPercent(item.value2));
            } else {
                info1 = Util.replaceString(name, "#", (new StringBuilder(String.valueOf(item.value))).toString());
                info1 = Util.replaceString(info1, "#", (new StringBuilder(String.valueOf(item.value2))).toString());
            }
            String[] arr = FontTeam.normalFont[0].splitFontBStrInLine(info1, lineWidth);
            for (int k = 0; k < arr.length; k++) {
                ItemOption ito = new ItemOption();
                ito.idColor = item.idColor;
                vcolor.addElement(ito);
            }
        }
        int size = vcolor.size();
        if (this.isItemShop)
            size++;
        byte[] mcolor = new byte[size];
        mcolor[size - 1] = 2;
        for (int j = 0; j < vcolor.size(); j++) {
            ItemOption mit = (ItemOption) vcolor.elementAt(j);
            if (mit != null)
                mcolor[j] = mit.idColor;
        }
        vcolor.removeAllElements();
        vcolor = null;
        return mcolor;
    }

    public void paint(mGraphics g, int x, int y, boolean isclip) {
        int f = -1;
        if (this.colorname == 0) {
            f = -1;
        } else if (this.colorname == 1) {
            f = 0;
        } else if (this.colorname == 2) {
            f = 1;
        } else if (this.colorname == 3) {
            f = 2;
        } else if (this.colorname == 4) {
            f = 3;
        } else if (this.colorname == 5) {
            f = 4;
        }
        if (f >= 0)
            g.drawRegion(GameScr.coloritem, 0, f * 25, 25, 25, 0, x - 1, y, 3, isclip);
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        int tir = this.plus;
        if (this.colorname == 0) {
            eff_UpLv.paintUpgradeEffect(x, y + 1, tir, 24, g, 1);
        } else {
            eff_UpLv.paintUpgradeEffectItemColor(x, y + 1, tir, 22, g, 1);
        }
        if (img != null && img.img != null) {
            g.drawImage(img.img, x, y, 3, isclip);
            if (this.quantity > 1) {
                int ds = 13;
                FontTeam.numberSmall_yeallow.drawString(g, (new StringBuilder(String.valueOf(Util.getKMBNumber(this.quantity)))).toString(), x + ds - 3, y + ds - FontTeam.numberSmall_yeallow.getHeight(), 1, isclip);
            }
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    x, y, 3, isclip);
        }
        if (this.lock == 1)
            g.drawImage(GameScr.imgBlock, x - 13, y + 6, 0, isclip);
    }

    public void paint(mGraphics g, int x, int y) {
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, x + 5, y + 3, 0, false);
            if (this.quantity > 1)
                FontTeam.numberSmall_yeallow.drawString(g, (new StringBuilder(String.valueOf(Util.getKMBNumber(this.quantity)))).toString(), x + 23, y + 20 - mFont.smallFont.getHeight(), 1, false);
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    x + 5, y + 5, 0, true);
        }
        if (this.lock == 1)
            g.drawImage(GameScr.imgBlock, x - 13, y + 6, 0, true);
    }

    public void paintIconPP(mGraphics g, int x, int y) {
        int f = -1;
        if (this.colorname == 0) {
            f = -1;
        } else if (this.colorname == 1) {
            f = 0;
        } else if (this.colorname == 2) {
            f = 1;
        } else if (this.colorname == 3) {
            f = 2;
        } else if (this.colorname == 4) {
            f = 3;
        } else if (this.colorname == 5) {
            f = 4;
        }
        if (f >= 0)
            g.drawRegion(GameScr.coloritem_small, 0, f * 19, 20, 19, 0, x + 3, y + 3, 0, true);
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, x + 5, y + 4, 0, false);
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    x + 5, y + 5, 0, true);
        }
        if (this.lock == 1)
            g.drawImage(GameScr.imgBlock, x + 15, y + 15, 0, true);
    }

    public void paintIcon(mGraphics g, int x, int y) {
        int f = -1;
        if (this.colorname == 0) {
            f = -1;
        } else if (this.colorname == 1) {
            f = 0;
        } else if (this.colorname == 2) {
            f = 1;
        } else if (this.colorname == 3) {
            f = 2;
        } else if (this.colorname == 4) {
            f = 3;
        } else if (this.colorname == 5) {
            f = 4;
        }
        if (f >= 0)
            g.drawRegion(GameScr.coloritem_small, 0, f * 19, 20, 19, 0, x + 3, y + 3, 0, true);
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, x + 5, y + 3, 0, false);
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    x + 5, y + 5, 0, true);
        }
        if (this.lock == 1)
            g.drawImage(GameScr.imgBlock, x - 13, y + 6, 0, true);
    }

    public void paintQuickSlot(mGraphics g, int x, int y) {
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, x + 5, y + 3, 0, false);
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    x, y, 0, true);
        }
        if (this.lock == 1)
            g.drawImage(GameScr.imgBlock, x - 13, y + 6, 0, true);
    }

    public void paintIconWearing(mGraphics g, int x, int y) {
        int f = -1;
        if (this.colorname == 0) {
            f = -1;
        } else if (this.colorname == 1) {
            f = 0;
        } else if (this.colorname == 2) {
            f = 1;
        } else if (this.colorname == 3) {
            f = 2;
        } else if (this.colorname == 4) {
            f = 3;
        } else if (this.colorname == 5) {
            f = 4;
        }
        if (f >= 0)
            g.drawRegion(GameScr.coloritem_small, 0, f * 19, 20, 19, 0, x, y, 3, true);
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, x, y, 3, true);
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    x, y, 3, true);
        }
        if (this.lock == 1)
            g.drawImage(GameScr.imgBlock, x - 13 + 3, y + 6 - 3, 0, true);
        int tir = this.plus;
        eff_UpLv.paintUpgradeEffect(x, y, tir, mSystem.isAndroid ? 20 : 21, g, 1);
    }

    public void paint(mGraphics g) {
        mFont.tahoma_7b_black.drawString(g, this.name, this.x + 1, this.y - 30 + 1, 2, false);
        mFont f = getColorPaintName(this.colorname);
        f.drawString(g, this.name, this.x, this.y - 30, 2, false);
        ImageIcon img = GameData.getImgIcon((short) (this.idIcon + Res.ID_ITEM));
        if (img != null && img.img != null) {
            g.drawImage(img.img, this.x, this.y, 3, false);
        } else {
            g.drawRegion(GameScr.imgloading, 0,
                    GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0,
                    this.x, this.y, 3, true);
        }
    }

    public void update() {
        if (this.timelive - mSystem.currentTimeMillis() <= 0L) {
            if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor.equals(this))
                GameCanvas.gameScr.focusedActor = null;
            this.wantDestroy = true;
        }
        if (this.isFly) {
            boolean isMatchX = false;
            boolean isMatchY = false;
            int dx1 = GameCanvas.abs(this.x - this.xTo);
            int dy1 = GameCanvas.abs(this.y - this.yTo);
            int spd = this.speed;
            if (dx1 > 100 || dy1 > 100) {
                spd = this.speed + 4;
            } else if (dx1 > 70 || dy1 > 70) {
                spd = this.speed + 3;
            } else if (dx1 > 50 || dy1 > 50) {
                spd = this.speed + 2;
            } else if (dx1 > 30 || dy1 > 30) {
                spd = this.speed + 1;
            }
            if (dx1 < spd) {
                this.x = this.xTo;
                isMatchX = true;
            }
            if (dy1 < spd) {
                this.y = this.yTo;
                isMatchY = true;
            }
            if (isMatchX && isMatchY) {
                if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor.equals(this))
                    GameCanvas.gameScr.focusedActor = null;
                this.wantDestroy = true;
            }
            if (this.x < this.xTo) {
                this.x = (short) (this.x + spd);
            } else if (this.x > this.xTo) {
                this.x = (short) (this.x - spd);
            }
            if (this.y > this.yTo) {
                this.y = (short) (this.y - spd);
            } else if (this.y < this.yTo) {
                this.y = (short) (this.y + spd);
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isItem() {
        return (this.catagory == 3);
    }

    public boolean isPotion() {
        return (this.catagory == 4);
    }

    public boolean isGem() {
        return (this.catagory == 6);
    }

    public int getLevel() {
        return this.level;
    }

    public static mFont getColorPaintName(int idcolor) {
        mFont f = mFont.tahoma_7b_white;
        if (idcolor == 0)
            return f;
        if (idcolor == 1)
            return mFont.tahoma_7b_blue;
        if (idcolor == 2)
            return mFont.tahoma_7b_yellow;
        if (idcolor == 3)
            return mFont.tahoma_7b_violet;
        if (idcolor == 4)
            return mFont.tahoma_7b_orange;
        if (idcolor == 5)
            return mFont.tahoma_7b_green;
        if (idcolor == 6)
            return mFont.tahoma_7b_red;
        if (idcolor == 7)
            return mFont.tahoma_7_gray;
        return f;
    }

    public static mFont getColorPaintOption(int idcolor) {
        mFont f = mFont.tahoma_7_white;
        if (idcolor == 0)
            return f;
        if (idcolor == 1)
            return mFont.tahoma_7_blue;
        if (idcolor == 2)
            return mFont.tahoma_7_yellow;
        if (idcolor == 3)
            return mFont.tahoma_7_violet;
        if (idcolor == 4)
            return mFont.tahoma_7_orange;
        if (idcolor == 5)
            return mFont.tahoma_7_green;
        if (idcolor == 6)
            return mFont.tahoma_7_red;
        if (idcolor == 7)
            return mFont.tahoma_7_gray;
        return f;
    }

    public void setTypeItem(byte type) {
        this.type = type;
    }

    public int getTypeItem() {
        return this.type;
    }

    public int getTimeLive() {
        return -1;
    }

    public int getColorItem() {
        return this.colorname;
    }

    public String getName() {
        return this.name;
    }
}
