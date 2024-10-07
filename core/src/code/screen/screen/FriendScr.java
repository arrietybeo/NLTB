package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Scroll;
import code.model.T;
import code.model.mCommand;
import code.screen.Res;
import lib.Cout;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class FriendScr extends ScreenTeam implements IActionListener {
    public int xKhungAuto;
    public int yKhungAuto;
    public int wKhungAuto = 140;
    public int hKhungAuto = 130;
    public int sizeH = 40;
    public int indexSelect = -1;
    public static FriendScr me;
    public Scroll listfriend = new Scroll();
    public mCommand cmdClose;

    public FriendScr() {
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("Đóng", this, 5);
        this.cmdClose.setindexImage(4);
        this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto - 3 * this.cmdClose.wCmd / 4, this.yKhungAuto - 28 - this.cmdClose.hCmd / 4);
        if (GameCanvas.isTouch) {
            this.cmdClose.caption = "";
            this.cmdClose.idAction = 5;
        }

    }

    public static FriendScr gI() {
        return me == null ? (me = new FriendScr()) : me;
    }

    public void update() {
        GameCanvas.gameScr.update();
        this.listfriend.updateKey();
        this.listfriend.updatecm();
        if (this.listfriend.selectedItem != -1) {
            this.indexSelect = this.listfriend.selectedItem;
        }

        if (GameCanvas.isPointerClick[0] && this.getCmdPointerLast(this.cmdClose)) {
            GameCanvas.isPointerJustRelease[0] = false;
            Cout.println("cmdClose ");
            this.cmdClose.performAction();
        }

        if (GameCanvas.isPointerClick[0] && this.listfriend.selectedItem != -1) {
            this.indexSelect = this.listfriend.selectedItem;
            mVector menuItems = new mVector();
            menuItems.addElement(new mCommand(T.trochuyen, this, 10, (Object) null));
            menuItems.addElement(new mCommand(T.Xemthongtin, this, 11, (Object) null));
            menuItems.addElement(new mCommand(T.Tuyetgiao, this, 12, (Object) null));
            GameCanvas.menu.startAt(menuItems, 2);
            GameCanvas.isPointerJustRelease[0] = false;
        }

    }

    public void paint(mGraphics g) {
        GameCanvas.gameScr.paint(g);
        Res.paintDlgDragonFullNew(g, this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 60, 60, GameScr.imgBk[0], false);
        g.setColor(-9751532);
        g.fillRect(this.xKhungAuto, this.yKhungAuto - 28, this.wKhungAuto, 28, false);
        int i;
        for (i = 0; i < 3; i++) {
            g.setColor(Res.nColor[i]);
            g.drawRect(this.xKhungAuto + i, this.yKhungAuto - 28 + i, this.wKhungAuto - i * 2, 28 - i * 2, false);
        }
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xKhungAuto, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xKhungAuto, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.RIGHT, false);
        for (i = 0; i < 7; i++)
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, this.xKhungAuto + this.wKhungAuto / 2 - 42 + i * 12, this.yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, this.xKhungAuto + this.wKhungAuto / 2 - 44, this.yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me)
            ys = -1;
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xKhungAuto + this.wKhungAuto / 2 + 44, this.yKhungAuto - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        FontTeam.fontTile.drawString(g, T.Banbe, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 19, 2, false);
        this.listfriend.setStyle(5, this.sizeH, this.xKhungAuto + 6, this.yKhungAuto + 5, this.wKhungAuto - 12, this.hKhungAuto - 12, true, 1);
        g.ClipRec(this.xKhungAuto + 6, this.yKhungAuto + 5, this.wKhungAuto - 12, this.hKhungAuto - 10);
        this.listfriend.setClip(g, this.xKhungAuto + 6, this.yKhungAuto + 5, this.wKhungAuto - 12, this.hKhungAuto - 10);
        for (int j = 0; j < 6; j++) {
            if (j == this.indexSelect) {
                g.setColor(-15904409);
                g.fillRect(this.xKhungAuto + 4, this.yKhungAuto + 6 + j * this.sizeH, this.wKhungAuto - 8, this.sizeH - 1, true);
            }
            if (j > 0) {
                g.setColor(Res.nColor[1]);
                g.drawLine(this.xKhungAuto + 8, this.yKhungAuto + 5 + j * this.sizeH, this.xKhungAuto + this.wKhungAuto - 8, this.yKhungAuto + 5 + j * this.sizeH, false);
                GameScr.mainChar.paintFriend(g, this.xKhungAuto + 24, this.yKhungAuto + 22 + j * this.sizeH, 1);
                mFont.tahoma_7_white.drawString(g, GameScr.mainChar.name, this.xKhungAuto + 40, this.yKhungAuto - 30 + j * this.sizeH, 0, true);
                mFont.tahoma_7_white.drawString(g, "Lv: " + GameScr.mainChar.lv, this.xKhungAuto + 40, this.yKhungAuto - 16 + j * this.sizeH, 0, true);
            }
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        this.cmdClose.paint(g);
    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 5:
                GameCanvas.gameScr.switchToMe();
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            default:
                break;
            case 10:
                GameCanvas.addChat("", "", true);
        }

    }
}
