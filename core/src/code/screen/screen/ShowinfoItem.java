package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Item;
import code.model.Scroll;
import code.model.T;
import code.model.mCommand;
import code.network.GameService;
import code.screen.Res;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class ShowinfoItem extends ScreenTeam implements IActionListener {
    public static ShowinfoItem me;
    public static Item item;
    public static String tile = "";
    public static String info = "";
    public String[] arrInfo;
    int disString = 12;
    public int xShow;
    public int yShow;
    public int wShow;
    public int hShow;
    public int numItemStart;
    public int speedStart;
    public mCommand cmdClose;
    public mCommand cmdOK;
    Scroll scr = new Scroll();
    public boolean isHalfstart;
    public boolean runStart;
    public static mVector vecinfo = new mVector();
    public static int idItem;

    public static ShowinfoItem gI() {
        return me == null ? (me = new ShowinfoItem()) : me;
    }

    public void switchToMe(ScreenTeam lastSCR) {
        super.switchToMe(lastSCR);
        this.setInfo();
    }

    public void setInfo() {
        this.wShow = 170;
        this.hShow = 130;
        this.xShow = GameCanvas.w / 2 - this.wShow / 2;
        this.yShow = GameCanvas.h / 2 - this.hShow / 2;
        this.cmdClose = new mCommand(T.close, this, 0);
        this.cmdOK = new mCommand(T.OK, this, 1);
        this.cmdClose.setXY(this.xShow, this.yShow + this.hShow + 2);
        this.cmdOK.setXY(this.xShow + this.wShow - mCommand.wButtonCmd, this.yShow + this.hShow + 2);
        vecinfo = item.getInfoItemShow((Item) null, true);
        this.arrInfo = mFont.tahoma_7b_white.splitFontArray(info, this.wShow - 10);
        this.setinfovecinfo();
        this.numItemStart = item.plus;
        if (this.numItemStart % 2 != 0) {
            this.isHalfstart = true;
        }

        this.numItemStart /= 2;
    }

    public void setinfovecinfo() {
        String[] data = null;
        int w0 = 0;

        for (int i = 0; i < vecinfo.size(); ++i) {
            InfoTextShow info = (InfoTextShow) vecinfo.elementAt(i);
            if (info.info != null) {
                if (i == 0) {
                    data = mFont.tahoma_7b_black.splitFontArray(info.info[0], this.wShow - 20);
                    info.setInfo(data, Item.getColorPaintName(info.color));
                } else {
                    data = mFont.tahoma_7_white.splitFontArray(info.info[0], this.wShow - 20);
                    info.setInfo(data, Item.getColorPaintOption(info.color));
                }

                int ww = info.getMaxWidth();
                w0 = w0 > ww ? w0 : ww;
            }
        }

    }

    public void PaintShowText(mGraphics g) {
        int yy = 0;
        int hclip = this.hShow - 10;
        GameCanvas.resetTrans(g);
        this.scr.setStyle(vecinfo.size() + 2, 16, this.xShow + 6, this.yShow + this.arrInfo.length * 15 - 5, this.wShow - 12, this.hShow - 12, true, 1);
        g.ClipRec(this.xShow + 6, this.yShow + 5 + this.arrInfo.length * 15 - 5, this.wShow - 12, hclip - this.arrInfo.length * 15);
        this.scr.setClip(g, this.xShow + 6, this.yShow + 5 + this.arrInfo.length * 15 - 5, this.wShow - 12, hclip - this.arrInfo.length * 15);

        for (int i = 0; i < vecinfo.size(); ++i) {
            if (i == 1 && this.numItemStart > 0) {
                yy += this.disString;

                for (int k = 0; k < this.numItemStart; ++k) {
                    g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShow + 15 + k * 11, this.yShow + yy + 3 + this.arrInfo.length * 15 - 5, 3, true);
                }

                if (this.isHalfstart) {
                    g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShow + 15 + this.numItemStart * 11, this.yShow + yy + 3 + this.arrInfo.length * 15 - 5, 3, true);
                }

                if (this.runStart) {
                    if (this.speedStart < this.numItemStart) {
                        g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0, this.xShow + 15 + this.speedStart * 11, this.yShow + yy + 3 + this.arrInfo.length * 15 - 5, 3, true);
                    }

                    if (this.speedStart >= 1 && this.speedStart < this.numItemStart + 1) {
                        g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0, this.xShow + 15 + (this.speedStart - 1) * 11, this.yShow + yy + 3 + this.arrInfo.length * 15 - 5, 3, true);
                    }

                    if (this.speedStart >= 2 && this.speedStart < this.numItemStart + 2) {
                        g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0, this.xShow + 15 + (this.speedStart - 2) * 11, this.yShow + yy + 3 + this.arrInfo.length * 15 - 5, 3, true);
                    }
                }
            }

            InfoTextShow info = (InfoTextShow) vecinfo.elementAt(i);
            if (info != null && info.info != null) {
                mFont f = info.f;
                if (info.info != null && info.info[info.info.length - 1] != null && f != null && info.info[info.info.length - 1] != null) {
                    for (int j = 0; j < info.info.length; ++j) {
                        f.drawString(g, info.info[j], this.xShow + 10, this.arrInfo.length * 15 + this.yShow + 10 + yy - 5, 0, true);
                        yy += this.disString;
                    }
                }
            }
        }

        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void paint(mGraphics g) {
        if (this.lastSCR != null)
            this.lastSCR.paint(g);
        Res.paintDlgDragonFullNew(g, this.xShow, this.yShow, this.wShow, this.hShow, 60, 60, GameScr.imgBk[0], false);
        g.setColor(-9751532);
        g.fillRect(this.xShow, this.yShow - 28, this.wShow, 28, false);
        int i;
        for (i = 0; i < 7; i++)
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, this.xShow + this.wShow / 2 - 42 + i * 12, this.yShow - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
        for (i = 0; i < 3; i++) {
            g.setColor(Res.nColor[i]);
            g.drawRect(this.xShow + i, this.yShow - 28 + i, this.wShow - i * 2, 28 - i * 2, false);
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, this.xShow + this.wShow / 2 - 44, this.yShow - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me)
            ys = -1;
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xShow + this.wShow / 2 + 44, this.yShow - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        FontTeam.fontTile.drawString(g, tile, this.xShow + this.wShow / 2, this.yShow - 20 + 1, 2, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xShow + this.wShow + 1, this.yShow + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xShow, this.yShow + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xShow, this.yShow - 28, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xShow + this.wShow + 1, this.yShow - 28, mGraphics.TOP | mGraphics.RIGHT, false);
        PaintShowText(g);
        GameCanvas.resetTrans(g);
        if (this.cmdClose != null)
            this.cmdClose.paint(g);
        if (this.cmdOK != null)
            this.cmdOK.paint(g);
        int yy2 = 0;
        for (int j = 0; j < this.arrInfo.length; j++) {
            mFont.tahoma_7b_white.drawString(g, this.arrInfo[j], this.xShow + 10, this.yShow + 7 + yy2, 0, false);
            yy2 += this.disString;
        }
        super.paint(g);
    }

    public void update() {
        if (GameCanvas.isKeyPressed(12)) {
            this.cmdClose.performAction();
        }

        if (GameCanvas.isKeyPressed(13)) {
            this.cmdOK.performAction();
        }

        if (GameCanvas.isPointer(this.xShow, this.yShow, this.wShow, this.hShow, 0)) {
            this.scr.updateKey();
        }

        this.scr.updatecm();
        if (GameCanvas.gameTick % 80 == 0) {
            this.runStart = true;
        }

        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            ++this.speedStart;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }

        if (this.lastSCR != null) {
            this.lastSCR.update();
        }

        if (GameCanvas.isTouch) {
            if (this.cmdClose != null && this.getCmdPointerLast(this.cmdClose)) {
                GameCanvas.isPointerJustRelease[0] = false;
                if (this.cmdClose != null) {
                    this.cmdClose.performAction();
                }
            }

            if (this.cmdOK != null && this.getCmdPointerLast(this.cmdOK)) {
                GameCanvas.isPointerJustRelease[0] = false;
                if (this.cmdOK != null) {
                    this.cmdOK.performAction();
                }
            }
        }

        super.update();
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0:
                this.lastSCR.switchToMe();
                vecinfo.removeAllElements();
                break;
            case 1:
                GameService.gI().doReciveItem(idItem);
                this.lastSCR.switchToMe();
                vecinfo.removeAllElements();
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }
}
