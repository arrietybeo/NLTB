// Decompiled with: CFR 0.152
// Class Version: 6
package code.screen.screen;

import code.main.GameCanvas;
import code.model.Char;
import code.model.IActionListener;
import code.model.MainChar;
import code.model.T;
import code.model.mCommand;
import code.network.GameService;
import code.screen.MenuLogin;
import code.screen.Res;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;
import lib.mGraphics;
import lib.mSystem;
import lib2.TField;
import lib2.mFont;

public class CharSelectScr
        extends ScreenTeam
        implements IActionListener {
    public static CharSelectScr me;
    int xInfoWearing;
    int yInfoWearing;
    int wInfoWearing;
    int hInfoWearing;
    int xItemFill;
    int yItemFill;
    int wItemFill;
    int hItemFill;
    public int x;
    public int y;
    public int w;
    public int h;
    public int size;
    public int colum;
    public int sizeH;
    public int wShowText;
    public int xShowText;
    public int indexFocus;
    public int tick;
    public int clazzcount;
    boolean isShowFill = true;
    public TField tfname;
    mCommand cmdhoanthanh;
    public MainChar nam;
    public MainChar nu;
    int tickback;
    int ali = 20;
    int hpaint;
    public byte[] list1;
    public byte[] list2;

    public static CharSelectScr gI() {
        return me == null ? (me = new CharSelectScr()) : me;
    }

    @Override
    public void switchToMe() {
        super.switchToMe();
        if (this.tfname != null) {
            this.tfname.setText("");
        }
        this.indexFocus = Res.random(2);
    }

    public CharSelectScr() {
        byte[] byArray = new byte[4];
        byArray[1] = 1;
        byArray[2] = 3;
        byArray[3] = 4;
        this.list1 = byArray;
        this.list2 = new byte[]{2, 1, 3, 4};
        if (!GameCanvas.isTouch) {
            this.ali = 5;
        }
        if (GameCanvas.isSmallScreen) {
            this.ali = 0;
        }
        this.indexFocus = 0;
        int xbg = this.x + this.w - 7 - this.colum * this.size;
        this.size = 26;
        this.colum = 5;
        this.sizeH = this.size;
        this.h = this.size * (this.colum + 3);
        if (GameCanvas.h > 200) {
            this.sizeH = 35;
        }
        this.xInfoWearing = xbg + 2 * this.size - 15;
        this.yInfoWearing = this.y + this.sizeH;
        this.wInfoWearing = 3 * this.size + 5;
        this.hInfoWearing = this.h - this.sizeH - 2 * this.size;
        this.hpaint = this.hInfoWearing - this.size + 30;
        if (GameCanvas.isSmallScreen) {
            this.hpaint -= 15;
        }
        if (!GameCanvas.isTouch) {
            this.size = 24;
            if (GameCanvas.isScreenSize200) {
                this.size = 22;
            }
            this.w = this.size * (this.colum + 2) + 2;
            this.x = GameCanvas.w / 2 - this.w / 2;
            if (this.x < 2) {
                this.x = 2;
            }
        } else {
            this.size = 26;
            this.w = this.size * (this.colum + 2);
            this.x = GameCanvas.w / 2 - this.w;
        }
        if (GameCanvas.isTouch) {
            this.xShowText = this.x + this.w - 15;
            this.wShowText = this.w - 4;
            if (this.xShowText + this.wShowText > GameCanvas.w - 2) {
                this.wShowText = GameCanvas.w - this.xShowText - 2;
            }
        }
        int yip = 0;
        int htf = 0;
        int ytf = 0;
        if (GameCanvas.isTouch && mSystem.isIP && mGraphics.zoomLevel == 3) {
            yip = 22;
            htf = -5;
            ytf = -8;
        }
        this.tfname = new TField(this.ali, this.hpaint + 30 + ytf, 2 * this.size * 2 + this.ali, 30 + htf);
        if (GameCanvas.isTouch) {
            this.tfname.height = ScreenTeam.ITEM_HEIGHT + (GameCanvas.isTouch ? 8 : 2);
        }
        this.tfname.setIputType(3);
        if (!GameCanvas.isTouch) {
            this.tfname.isFocus = true;
        }
        this.cmdhoanthanh = new mCommand(T.hoanthanh, this, 0);
        this.cmdhoanthanh.setXY(this.ali + (2 * this.size * 2 + this.ali) / 2 - mCommand.wButtonCmd / 2, this.hpaint + 35 + 37 - yip);
        if (mSystem.isPC || mSystem.isIP) {
            this.tfname.setStringNull(T.tennhanvat);
        }
        if (!GameCanvas.isTouch) {
            this.center = this.cmdhoanthanh;
        }
        if (!GameCanvas.isTouch && this.tfname.isFocus) {
            this.right = this.tfname.cmdClear;
        }
        this.tfname.setMaxTextLenght(16);
        this.nam = new MainChar();
        this.nam.clazz = Char.VO_DANG;
        this.nam.setInfoWearing(Char.idPartTest[Char.CAI_BANG][0]);
        this.nu = new MainChar();
        this.nu.clazz = Char.NGA_MI;
        this.nu.setInfoWearing(Char.idPartTest[Char.NGA_MI][0]);
        this.nam.setState(0);
        this.nu.setState(0);
    }

    @Override
    public boolean keyPress(int keyCode) {
        boolean swallow;
        if (this.tfname.isFocus && (swallow = this.tfname.keyPressed(keyCode))) {
            return true;
        }
        return super.keyPress(keyCode);
    }

    @Override
    public void paint(mGraphics g) {
        g.setColor(-16777216);
        g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        Res.paintDlgDragonFullNew(g, 0, 0, GameCanvas.w, GameCanvas.h - 5, 60, 60, GameScr.imgBk[0], false);
        int x000 = this.x;
        int www = (GameCanvas.isTouch ? this.xShowText + this.wShowText + 2 : this.w + this.x) - x000;
        if (!GameCanvas.isTouch) {
            www = this.w - 1;
        }
        g.setColor(-9751532);
        g.drawRect(0, this.y, GameCanvas.w, this.sizeH - 3, false);
        g.drawRect(0, this.y + 1, GameCanvas.w, this.sizeH - 5, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, GameCanvas.w, this.y + this.sizeH - 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, 0, this.y + this.sizeH - 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, 0, this.y, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, GameCanvas.w, this.y, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, GameCanvas.w, GameCanvas.h - 5, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, 0, GameCanvas.h - 5, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, 0, this.y + this.sizeH - 3, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, GameCanvas.w, this.y + this.sizeH - 3, mGraphics.TOP | mGraphics.RIGHT, false);
        int i = 0;
        while (i < 7) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, GameCanvas.hw - 42 + i * 12, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
            ++i;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, GameCanvas.hw - 44, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me) {
            ys = -1;
        }
        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, GameCanvas.hw + 44, this.y + this.sizeH / 2 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        FontTeam.fontTile.drawString(g, T.thongtin, GameCanvas.w / 2, this.y + this.sizeH / 2 - 6, 2, false);
        this.paintCharWearing(g);
        this.tfname.paint(g);
        int yip = 0;
        if (GameCanvas.isTouch && mSystem.isIP && mGraphics.zoomLevel == 3) {
            yip = -5;
        }
        FontTeam.borderFont.drawString(g, T.tennhanvat, this.ali + (2 * this.size * 2 + this.ali) / 2, this.hpaint + 15 + yip, 2, false);
        if (this.cmdhoanthanh != null && GameCanvas.isTouch) {
            this.cmdhoanthanh.paint(g);
        }
        g.drawRegion(GameScr.imghand, 0, 0, mGraphics.getImageWidth(GameScr.imghand), mGraphics.getImageHeight(GameScr.imghand), 5, GameCanvas.w - mGraphics.getImageHeight(GameScr.imghand) - 22 + this.tickback, 10, 0, false);
        super.paint(g);
    }

    private void paintCharWearing(mGraphics g) {
        int i;
        int xbg = this.ali;
        int x2 = xbg + 2 * this.size;
        int x3 = xbg + 3 + x2 * 2;
        int yShowText1 = this.yInfoWearing + 2 + 5;
        int xShowText1 = x3;
        int wShowText1 = GameCanvas.w - xShowText1 - this.ali + 2;
        int hShowText1 = this.hInfoWearing - 4 - this.size;
        int y2 = GameCanvas.h - yShowText1 - 15 - (GameCanvas.isTouch ? 0 : 20);
        int ybg = yShowText1;
        g.setColor(-13232632);
        g.fillRect(xShowText1 - 2, yShowText1, wShowText1, y2, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 1 - 2, yShowText1 + 1, wShowText1 - 2, y2 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 2 - 2, yShowText1 + 2, wShowText1 - 4, y2 - 4, false);
        g.setColor(-11064572);
        g.fillRect(xShowText1 + 3 - 2, yShowText1 + 3, wShowText1 - 6, y2 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 2, yShowText1 + y2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1 - 2, yShowText1 + y2, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1 - 2, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 2, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
        g.setColor(-9751532);
        int hh0 = hShowText1;
        g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
        g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
        g.setColor(-12246258);
        g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
        g.setColor(-110);
        g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
        g.setColor(-4034289);
        g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
        g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
        if (this.indexFocus == 0) {
            g.setColor(-6728397);
            g.fillRect(xbg + 1 + 2, ybg + 3, 2 * this.size - 2 - 4, hh0 - 6, false);
        }
        g.setColor(-4760043);
        g.fillRect(xbg + 1 + this.size / 2, hh0 - 6 + ybg + 4, this.size, 1, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg += hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.setColor(-9751532);
        g.fillRect(xbg + 3 + x2, ybg -= hShowText1, 2 * this.size - 6, hh0, false);
        g.fillRect(xbg + x2, ybg + 3, 2 * this.size, hh0 - 6, false);
        g.setColor(-12246258);
        g.fillRect(xbg + 3 + x2, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
        g.setColor(-110);
        g.fillRect(xbg + 3 + x2, ybg + 1, 2 * this.size - 6, 1, false);
        g.setColor(-4034289);
        g.fillRect(xbg + 1 + x2, ybg + 16, 1, hh0 - 18, false);
        g.fillRect(xbg - 2 + 2 * this.size + x2, ybg + 16, 1, hh0 - 18, false);
        if (this.indexFocus == 1) {
            g.setColor(-6728397);
            g.fillRect(xbg + x2 + 1 + 2, ybg + 3, 2 * this.size - 2 - 4, hh0 - 6, false);
        }
        g.setColor(-4760043);
        g.fillRect(xbg + x2 + 1 + this.size / 2, hh0 - 6 + ybg + 4, this.size, 1, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg + x2, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size + x2, ybg, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg + x2, ybg += hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size + x2, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        ybg -= hShowText1;
        String[] text = FontTeam.borderFont.splitFontArray(T.monphai, wShowText1);
        if (text != null) {
            i = 0;
            while (i < text.length) {
                FontTeam.arialFontW.drawString(g, text[i], xShowText1 + 5, yShowText1 + 10 + 12 * i, 0, false);
                ++i;
            }
        }
        if (this.indexFocus != -1) {
            i = 0;
            while (i < T.infoclass[this.indexFocus].length) {
                mFont mfont = mFont.tahoma_7_white;
                if (this.clazzcount == i) {
                    mfont = mFont.tahoma_7b_yellow;
                }
                mfont.drawString(g, "- " + T.infoclass[this.indexFocus][i], xShowText1 + 5, yShowText1 + 10 + 15 * text.length + i * 15, 0, false);
                ++i;
            }
        }
        int xp1 = xbg + 2 * this.size / 2;
        int xp2 = xbg + x2 + 2 * this.size / 2;
        this.nam.paintSelectChar(g, xp1, ybg + hh0 - 15, 0);
        this.nu.paintSelectChar(g, xp2, ybg + hh0 - 15, 0);
        g.setColor(-1136094);
        g.fillRect(xShowText1 + 5, yShowText1 + 17 + 20 * text.length + 36 + 6, wShowText1 - 14, 1, false);
        if (this.indexFocus != -1) {
            String[] clazzinfo = FontTeam.arialFontW.splitFontArray(T.classintro[this.indexFocus][this.clazzcount], wShowText1 - 20);
            int i2 = 0;
            while (i2 < clazzinfo.length) {
                FontTeam.arialFontW.drawString(g, clazzinfo[i2], xShowText1 + 5, yShowText1 + 15 + 20 * text.length + 48 + i2 * 12, 0, false);
                ++i2;
            }
        }
    }

    @Override
    public void update() {
        this.tickback = GameCanvas.gameTick % 7 * -1;
        if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(GameCanvas.w - mGraphics.getImageHeight(GameScr.imghand) - 30, 3, 30, 30, 0)) {
            GameCanvas.isPointerClick[0] = false;
            MenuLogin.gI().switchToMe();
        }
        if (this.indexFocus == 0) {
            this.nam.update();
        } else if (this.indexFocus == 1) {
            this.nu.update();
        }
        this.tfname.update();
        ++this.tick;
        if (GameCanvas.isTouch) {
            int xbg = this.ali;
            int x2 = xbg + 2 * this.size;
            int x3 = xbg + 3 + x2 * 2;
            int yShowText1 = this.yInfoWearing + 2 + 5;
            int xShowText1 = x3;
            int wShowText1 = GameCanvas.w - xShowText1 - this.ali + 2;
            String[] text = FontTeam.borderFont.splitFontArray(T.monphai, wShowText1);
            if (this.indexFocus != -1) {
                int i = 0;
                while (i < T.infoclass[this.indexFocus].length) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(xShowText1 + 5, yShowText1 + 10 + 12 * text.length + i * 15, wShowText1, 20, 0)) {
                        if (this.clazzcount != i) {
                            this.clazzcount = i;
                        }
                        GameCanvas.isPointerClick[0] = false;
                    }
                    ++i;
                }
            }
        }
        if (this.cmdhoanthanh != null && GameCanvas.isTouch && this.getCmdPointerLast(this.cmdhoanthanh)) {
            GameCanvas.isPointerJustRelease[0] = false;
            this.cmdhoanthanh.performAction();
        }
        if (GameCanvas.isTouch && GameCanvas.isPointerClick[0]) {
            if (GameCanvas.isPointer(20, this.yInfoWearing + 2 + 5, 2 * this.size - 6, this.hInfoWearing - 4 - this.size, 0)) {
                this.indexFocus = 0;
                this.clazzcount = 0;
            }
            if (GameCanvas.isPointer(20 + 3 * this.size - 6, this.yInfoWearing + 2 + 5, 2 * this.size - 6, this.hInfoWearing - 4 - this.size, 0)) {
                this.indexFocus = 1;
                this.clazzcount = 0;
            }
            GameCanvas.isPointerClick[0] = false;
        }
        if (GameCanvas.isKeyPressed(4)) {
            this.indexFocus = this.indexFocus == 0 ? 1 : 0;
            this.clazzcount = 0;
        }
        if (GameCanvas.isKeyPressed(6)) {
            this.indexFocus = this.indexFocus == 0 ? 1 : 0;
            this.clazzcount = 0;
        }
        if (GameCanvas.isKeyPressed(8)) {
            ++this.clazzcount;
            if (this.clazzcount > T.infoclass[this.indexFocus].length - 1) {
                this.clazzcount = 0;
            }
        }
        if (GameCanvas.isKeyPressed(2)) {
            --this.clazzcount;
            if (this.clazzcount < 0) {
                this.clazzcount = T.infoclass[this.indexFocus].length - 1;
            }
        }
        super.update();
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0: {
                GameService.gI().doSelectCharName(this.indexFocus, this.tfname.getText());
                MainChar.newQuickSlot();
            }
        }
    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }
}
