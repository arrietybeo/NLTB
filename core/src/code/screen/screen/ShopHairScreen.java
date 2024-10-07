package code.screen.screen;

import code.main.GameCanvas;
import code.model.Char;
import code.model.IActionListener;
import code.model.Item;
import code.model.Scroll;
import code.model.T;
import code.model.mCommand;
import code.network.GameService;
import code.screen.Res;
import lib.Cout;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class ShopHairScreen extends ScreenTeam implements IActionListener {
    public static ShopHairScreen me;
    public int yBg;
    public int hTab;
    public int xbg;
    public int wTab;
    boolean isFocusClose;
    public Char c;
    mCommand cmdL;
    mCommand cmdR;
    mCommand cmdCenter;
    int xArrowL;
    int xArrowR;
    int yArrowL;
    int yArrowR;
    int xChar;
    int yChar;
    int disString;
    byte indexHair;
    Scroll scr = new Scroll();
    public static String[] arrayNameHair;
    public static int[] ID_PART_HAIR = new int[]{60, 303, 305, 309};
    public static mVector vecItemHair = new mVector();
    int countL;
    int countR;
    byte totalInfoshow = 0;
    mVector showText = new mVector();

    public void switchToMe(ScreenTeam lastSCR) {
        super.switchToMe(lastSCR);
        this.indexHair = 0;
        arrayNameHair = new String[vecItemHair.size()];

        for (int i = 0; i < arrayNameHair.length; ++i) {
            Item ite = (Item) vecItemHair.elementAt(i);
            if (ite != null) {
                arrayNameHair[i] = ite.name;
            }
        }

        Item itee = (Item) vecItemHair.elementAt(0);
        if (itee != null) {
            this.c.setInfoWearingShopModel(itee.arrPart);
            this.setShowText(itee.getInfoItemShow((Item) null, true), (String[]) null, true, false);
        }

        this.c.dir = 0;
    }

    public static ShopHairScreen gI() {
        return me == null ? (me = new ShopHairScreen()) : me;
    }

    public ShopHairScreen() {
        int w0 = mGraphics.getImageWidth(GameScr.imgButton[0]);
        int h0 = mGraphics.getImageHeight(GameScr.imgButton[0]) / 2;
        this.hTab = GameCanvas.h - h0 * 2;
        if (this.hTab > 240 - h0 * 2) {
            this.hTab = 240 - h0 * 2;
        }

        this.yBg = GameCanvas.hh - this.hTab / 2;
        this.xbg = GameCanvas.hw - 140;
        this.wTab = GameCanvas.w - 40;
        if (this.wTab > 280) {
            this.wTab = 280;
        }

        this.cmdL = new mCommand(T.Free, this, 2);
        this.cmdL.x = GameCanvas.hw - this.wTab / 2;
        this.cmdL.y = this.yBg + this.hTab;
        this.cmdR = new mCommand(T.Mua_Bang_Ngoc, this, 1);
        this.cmdR.x = GameCanvas.hw + this.wTab / 2 - w0;
        this.cmdR.y = this.yBg + this.hTab;
        this.cmdCenter = new mCommand(T.Buy, this, 3);
        this.cmdCenter.x = GameCanvas.hw - w0 / 2;
        this.cmdCenter.y = this.yBg + this.hTab + 2;
        this.c = GameScr.mainChar;
        this.disString = 12;
    }

    public void perform(int idAction, Object p) {
        Cout.Debug("ID Action: " + idAction);
        switch (idAction) {
            case 3:
                Item ite = (Item) vecItemHair.elementAt(this.indexHair);
                if (ite != null) {
                    GameService.gI().doBuyItemFashion(ite.ID);
                }
            case 1:
            case 2:
            default:
        }
    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void update() {
        this.scr.updateKey();
        this.scr.updatecm();
        if (this.lastSCR != null) {
            this.lastSCR.update();
        }

        this.UpdateTouch();
        if (this.countL > 0) {
            --this.countL;
        }

        if (this.countR > 0) {
            --this.countR;
        }

        if (GameScr.mainChar != null) {
            this.xChar = GameCanvas.hw;
            this.yChar = GameCanvas.hh - GameScr.mainChar.getHeight();
            if (this.yChar < 10 + GameScr.mainChar.getHeight()) {
                this.yChar = 10 + GameScr.mainChar.getHeight();
            }

            this.xArrowL = GameCanvas.hw - GameScr.mainChar.getWidth() - 30;
            this.xArrowR = GameCanvas.hw + GameScr.mainChar.getWidth() + 30;
            this.yArrowL = this.yChar + 11;
            this.yArrowR = this.yChar + 11;
        }

        mCommand[] allCommand = new mCommand[]{this.cmdL, this.cmdR, this.cmdCenter};

        for (int i = 0; i < allCommand.length; ++i) {
            mCommand cmd = allCommand[i];
            if (cmd != null && this.getCmdPointerLast(cmd)) {
                GameCanvas.isPointerJustRelease[0] = false;
                cmd.performAction();
                break;
            }
        }

        super.update();
    }

    public void updateKey() {
        if (GameCanvas.currentDialog == null) {
            if (GameCanvas.isKeyPressed(5)) {
                this.cmdCenter.performAction();
            }

            if (GameCanvas.isKeyPressed(13)) {
                this.doClose();
            }

            Item ite;
            if (GameCanvas.isKeyPressed(6)) {
                this.countR = 5;
                ++this.indexHair;
                if (this.indexHair > vecItemHair.size() - 1) {
                    this.indexHair = 0;
                }

                ite = (Item) vecItemHair.elementAt(this.indexHair);
                if (ite != null && ite.arrPart != null) {
                    this.c.setInfoWearingShopModel(ite.arrPart);
                    this.setShowText(ite.getInfoItemShow((Item) null, true), (String[]) null, true, false);
                }
            }

            if (GameCanvas.isKeyPressed(4)) {
                this.countL = 5;
                --this.indexHair;
                if (this.indexHair < 0) {
                    this.indexHair = (byte) (vecItemHair.size() - 1);
                }

                ite = (Item) vecItemHair.elementAt(this.indexHair);
                if (ite != null && ite.arrPart != null) {
                    this.c.setInfoWearingShopModel(ite.arrPart);
                    this.setShowText(ite.getInfoItemShow((Item) null, true), (String[]) null, true, false);
                }
            }

            if (GameCanvas.canTouch()) {
                if (GameCanvas.isPointer(this.xArrowL - 20, this.yArrowL - 20, 50, 50, 0)) {
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                        this.countL = 5;
                        GameCanvas.isPointerClick[0] = false;
                        --this.indexHair;
                        if (this.indexHair < 0) {
                            this.indexHair = (byte) (vecItemHair.size() - 1);
                        }

                        ite = (Item) vecItemHair.elementAt(this.indexHair);
                        if (ite != null && ite.arrPart != null) {
                            this.c.setInfoWearingShopModel(ite.arrPart);
                            this.setShowText(ite.getInfoItemShow((Item) null, true), (String[]) null, true, false);
                        }
                    }
                } else if (GameCanvas.isPointer(this.xArrowR - 20, this.yArrowR - 20, 50, 50, 0) && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.countR = 5;
                    GameCanvas.isPointerClick[0] = false;
                    ++this.indexHair;
                    if (this.indexHair > vecItemHair.size() - 1) {
                        this.indexHair = 0;
                    }

                    ite = (Item) vecItemHair.elementAt(this.indexHair);
                    if (ite != null && ite.arrPart != null) {
                        this.c.setInfoWearingShopModel(ite.arrPart);
                        this.setShowText(ite.getInfoItemShow((Item) null, true), (String[]) null, true, false);
                    }
                }
            }

        }
    }

    public void UpdateTouch() {
        if (GameCanvas.isTouch) {
            this.isFocusClose = false;
            int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xbg + this.wTab - 21, this.yBg - 21, wc, wc, 0) && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    this.doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }

        }
    }

    public void doClose() {
        mSound.playSound(26, mSound.volumeSound);
        this.lastSCR.switchToMe();
    }

    public void paint(mGraphics g) {
        if (this.lastSCR != null) {
            this.lastSCR.paint(g);
        }

        GameCanvas.resetTrans(g);
        g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
        this.paintMoney(g);
        if (GameScr.mainChar != null) {
            g.setColor(-13232632);
            g.fillRect(this.xbg - 2, this.yBg, this.wTab, this.hTab, false);
            g.setColor(-1596632);
            g.fillRect(this.xbg + 1 - 2, this.yBg + 1, this.wTab - 2, this.hTab - 2, false);
            g.setColor(-13232632);
            g.fillRect(this.xbg + 2 - 2, this.yBg + 2, this.wTab - 4, this.hTab - 4, false);
            g.setColor(-16180203);
            g.fillRect(this.xbg + 3 - 2, this.yBg + 3, this.wTab - 6, this.hTab - 6, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, this.xbg + this.wTab - 2, this.yBg + this.hTab, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, this.xbg - 2, this.yBg + this.hTab, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, this.xbg - 2, this.yBg, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, this.xbg + this.wTab - 2, this.yBg, mGraphics.TOP | mGraphics.RIGHT, false);
            int w0;
            int h0;
            if (GameCanvas.isTouch) {
                GameCanvas.resetTrans(g);
                w0 = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                h0 = mGraphics.getImageWidth(GameScr.imgButton[4]);
                g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * w0, h0, w0, 0, this.xbg + this.wTab - 21, this.yBg - 21, 0, false);
            }

            this.c.paintCharShop(g, this.xChar, this.yChar);
            g.drawImage(GameScr.imgArrow[0], this.xArrowL - this.countL, this.yArrowL, mGraphics.VCENTER | mGraphics.LEFT, false);
            w0 = mGraphics.getImageWidth(GameScr.imgArrow[0]);
            h0 = mGraphics.getImageHeight(GameScr.imgArrow[0]);
            g.drawRegion(GameScr.imgArrow[0], 0, 0, w0, h0, 2, this.xArrowR + this.countR, this.yArrowR, mGraphics.VCENTER | mGraphics.RIGHT, false);
            g.setColor(-4760043);
            g.fillRect(this.xbg, this.yChar + 25, this.wTab - 4, 1, false);
            mFont.tahoma_7b_yellow.drawString(g, arrayNameHair[this.indexHair], GameCanvas.hw, this.yChar + 10 - mFont.tahoma_7b_yellow.getHeight() / 2, 2, false);
        }

        g.fillRect(0, 0, 0, 0, false);
        GameCanvas.resetTrans(g);
        this.paintShowText(g);
        GameCanvas.resetTrans(g);
        if (this.cmdCenter != null) {
            this.cmdCenter.paint(g);
        }

        super.paint(g);
    }

    public void paintShowText(mGraphics g) {
        int yy = 0;
        this.scr.setStyle(this.showText.size() + 2, 16, this.xbg + 6, this.yChar + 30, this.wTab - 12, this.hTab - 60, true, 1);
        g.ClipRec(this.xbg + 6, this.yChar + 30, this.wTab, this.hTab - 65);
        this.scr.setClip(g, this.xbg + 6, this.yChar + 30, this.wTab, this.hTab - 65);

        for (int i = 0; i < this.showText.size(); ++i) {
            InfoTextShow info = (InfoTextShow) this.showText.elementAt(i);
            if (info != null && info.info != null) {
                mFont f = info.f;
                if (info.info != null && info.info[info.info.length - 1] != null && f != null && info.info[info.info.length - 1] != null) {
                    if (info.idCompare > -1 && info.idCompare == 3) {
                        f = mFont.tahoma_7_gray;
                    }

                    int ws = f.getWidth(info.info[info.info.length - 1]);

                    for (int j = 0; j < info.info.length; ++j) {
                        f.drawString(g, info.info[j], this.xbg + 10, this.yChar + 30 + 10 + yy, 0, true);
                        yy += this.disString;
                    }

                    if (info.idCompare > -1 && info.idCompare != 3) {
                        g.drawImage(GameScr.imgSo[info.idCompare], this.xbg + 10 + ws + 1, this.yChar + 30 + 12 + yy - this.disString, 0, false);
                    }
                }
            }
        }

        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
    }

    private void setShowText(mVector text, String[] arr, boolean isTile, boolean isSetGroup) {
        if (text != null || arr != null) {
            this.totalInfoshow = 0;
            if (arr == null) {
                String[] data = null;
                int w0 = 0;
                if (isSetGroup) {
                    this.showText = MainMenu.setGroupFull(text);
                } else {
                    this.showText = text;
                }

                try {
                    for (int i = 0; i < this.showText.size(); ++i) {
                        InfoTextShow info = (InfoTextShow) text.elementAt(i);
                        if (info != null && info.info != null) {
                            if (info.info[0] != null) {
                                if (isTile && i == 0) {
                                    data = mFont.tahoma_7b_black.splitFontArray(info.info[0], this.wTab - 20);
                                    info.setInfo(data, Item.getColorPaintName(info.color));
                                } else {
                                    data = mFont.tahoma_7_white.splitFontArray(info.info[0], this.wTab - 20);
                                    info.setInfo(data, Item.getColorPaintOption(info.color));
                                }
                            }

                            this.totalInfoshow = (byte) (this.totalInfoshow + info.info.length);
                            int ww = info.getMaxWidth();
                            w0 = w0 > ww ? w0 : ww;
                        } else {
                            ++this.totalInfoshow;
                        }
                    }
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            }

        }
    }

    public void paintMoney(mGraphics g) {
        if (GameCanvas.isTouch) {
            int sizeH = 0;
            int xss = 0, yp = 0;
            yp = (this.yBg + sizeH - 14) / 2 + (this.yBg + sizeH - 14) / 2 - 4;
            xss = this.xbg + this.wTab - 42;
            String xu = Res.getDotNumber(GameScr.mainChar.charXu);
            String luong = Res.getDotNumber(GameScr.mainChar.luong);
            int ypp = 0;
            if (mSystem.isAndroid)
                ypp = -3;
            if (mSystem.isIP)
                ypp = -1;
            g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, xss + 5, yp, 0, false);
            FontTeam.numberSmall_white.drawString(g, xu, xss, yp + ypp - 1, 1, false);
            g.drawRegion(GameScr.imgMoney, 0, 0, 9, 7, 0, xss + 5, yp + 11, 0, false);
            FontTeam.numberSmall_white.drawString(g, luong, xss, yp + 10 + ypp, 1, false);
        } else {
            int xpxu = 0, xpluong = 0, xss = 0, xmn = 0;
            String xu = Res.getDotNumber(GameScr.mainChar.charXu);
            String luong = Res.getDotNumber(GameScr.mainChar.luong);
            xpxu = FontTeam.numberSmall_white.getWidth(xu);
            xpluong = FontTeam.numberSmall_white.getWidth(luong);
            xss = (xpxu > xpluong) ? xpxu : xpluong;
            xmn = GameCanvas.w - 50 - xss;
            if (!GameCanvas.isTouch)
                xmn = 10;
            paintMoney(g, xmn, 5, xmn, 5, xu, luong);
        }
    }

    public void paintMoney(mGraphics g, int x1, int y1, int x2, int y2, String xu, String luong) {
        g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, x1, y1, 0, false);
        FontTeam.numberSmall_white.drawString(g, xu, x1 + 10, y1 - 2, 0, false);
        g.drawRegion(GameScr.imgMoney, 0, 0, 9, 7, 0, x2, y2 + 9, 0, false);
        FontTeam.numberSmall_white.drawString(g, luong, x2 + 10, y2 + 9, 0, false);
    }
}
