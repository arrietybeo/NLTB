package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.ImageIcon;
import code.model.mCommand;
import code.network.GameService;
import code.screen.Util;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.SoundMn;
import lib2.mFont;

public class Menu implements IActionListener {
    public boolean showMenu;
    public mVector menuItems;
    public int menuSelectedItem;
    public int idBig;
    public int menuX;
    public int menuY;
    public int menuW;
    public int menuH;
    public int menuTemY1;
    public int selected;
    public static int[] menuTemY;
    public static int cmtoX;
    public static int cmx;
    public static int cmdy;
    public static int cmvy;
    public static int cmxLim;
    public static int xc;
    mCommand left = new mCommand("Chọn", 0);
    mCommand right;
    mCommand center;
    public static Image imgMenu1 = GameCanvas.loadImage("/mainImage/btB_0.png");
    public static Image imgMenu2 = GameCanvas.loadImage("/mainImage/btB_1.png");
    boolean disableClose;
    boolean isOptionMenu;
    public int IDNPC;
    public int ID;
    int cvyb;
    int cdyb;
    int ctoyBag;
    int cyBag;
    int cylim;
    String infomore;
    public String[] info;
    public boolean isWait;
    int h;
    public int wShowPaper;
    public int maxWShow;
    public int vShow;
    boolean Canpaint;
    public static final int ITEM_HEIGHT;
    public int tDelay;
    public int w;
    int pa;
    boolean trans;
    private int pointerDownTime;
    private int pointerDownFirstX;
    private int[] pointerDownLastX;
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private boolean wantUpdateList;
    private int waitToPerform;
    private int cmRun;
    private boolean touch;
    private boolean close;
    int cmvx;
    int cmdx;
    boolean isClose;
    public boolean[] isNotClose;

    static {
        ITEM_HEIGHT = FontTeam.normalFont[0].getHeight() + 6;
    }

    public Menu() {
        this.right = new mCommand("Đóng", 0, GameCanvas.w - 71, GameCanvas.h - mCommand.hButtonCmd + 1);
        this.center = null;
        this.isOptionMenu = false;
        this.cyBag = 0;
        this.cylim = 0;
        this.infomore = "";
        this.vShow = 1;
        this.pa = 0;
        this.trans = false;
        this.pointerDownLastX = new int[3];
    }

    public void startAt_MenuOption(mVector menuItems, int id, int idNPC) {
        this.isOptionMenu = true;
        this.ID = id;
        this.IDNPC = idNPC;
        this.startAt(menuItems, 0);
        this.perFormNPC();
    }

    public void startAt_MenuOption(mVector menuItems, int id, int idNPC, String str) {
        this.isOptionMenu = true;
        this.ID = id;
        this.idBig = -1;
        this.IDNPC = idNPC;
        this.infomore = str;
        this.startAt(menuItems, 0);
        if (!this.infomore.equals("")) {
            this.Canpaint = false;
            this.maxWShow = FontTeam.arialFont.getWidth(this.infomore) + 20;
            if (this.maxWShow > GameCanvas.w - 20) {
                this.maxWShow = GameCanvas.w - 30;
            }

            if (this.maxWShow < GameCanvas.hw) {
                this.maxWShow = GameCanvas.hw + 20;
            }

            this.wShowPaper = 10;
            this.info = FontTeam.arialFont.splitFontArray(this.infomore, this.maxWShow - 40);
            this.h = 80;
            if (this.info.length >= 5) {
                this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
            }

            this.isWait = false;
        }

        this.perFormNPC();
    }

    public void startAt_MenuOption(mVector menuItems, int id, int idNPC, String str, int idBig) {
        this.isOptionMenu = false;
        this.ID = id;
        this.idBig = idBig;
        mSound.playSound(28, mSound.volumeSound);
        this.IDNPC = idNPC;
        this.infomore = str;
        this.startAt(menuItems, 0);
        if (!this.infomore.equals("")) {
            this.Canpaint = false;
            this.maxWShow = FontTeam.arialFont.getWidth(this.infomore) + 20;
            if (this.maxWShow > GameCanvas.w - 20) {
                this.maxWShow = GameCanvas.w - 30;
            }

            if (this.maxWShow < GameCanvas.hw) {
                this.maxWShow = GameCanvas.hw + 20;
            }

            this.wShowPaper = 10;
            this.info = FontTeam.arialFont.splitFontArray(this.infomore, this.maxWShow - 40);
            this.h = 80;
            if (this.info.length >= 5) {
                this.h = this.info.length * FontTeam.arialFont.getHeight() + 20;
            }

            this.isWait = false;
        }

        this.perFormNPC();
    }

    public void startAt(mVector menuItems, int pos) {
        if (!this.showMenu) {
            this.isClose = false;
            this.touch = false;
            this.close = false;
            this.tDelay = 0;
            SoundMn.gI().openMenu();
            this.isNotClose = new boolean[menuItems.size()];

            int i;
            for (i = 0; i < this.isNotClose.length; ++i) {
                this.isNotClose[i] = false;
            }

            this.disableClose = false;
            if (menuItems.size() != 0) {
                this.menuItems = menuItems;
                this.menuW = 62;
                this.menuH = 60;

                for (i = 0; i < menuItems.size(); ++i) {
                    mCommand c = (mCommand) menuItems.elementAt(i);
                    c.isPlaySoundButton = false;
                    int w = mFont.tahoma_7b_white.getWidth(c.caption);
                    if (w > this.menuW - 8) {
                        c.subCaption = mFont.tahoma_7b_white.splitFontArray(c.caption, this.menuW - 15);
                    }
                }

                menuTemY = new int[menuItems.size()];
                this.menuX = (GameCanvas.w - menuItems.size() * this.menuW) / 2;
                if (this.menuX < 1) {
                    this.menuX = 1;
                }

                this.menuY = GameCanvas.h - this.menuH - 15 - 1;
                if (GameCanvas.isTouch) {
                    this.menuY -= 3;
                }

                this.menuY += 17;

                for (i = 0; i < menuTemY.length; ++i) {
                    menuTemY[i] = GameCanvas.h;
                }

                this.showMenu = true;
                this.menuSelectedItem = 0;
                cmxLim = this.menuItems.size() * this.menuW - GameCanvas.w;
                if (cmxLim < 0) {
                    cmxLim = 0;
                }

                cmtoX = 0;
                cmx = 0;
                xc = 50;
                this.w = menuItems.size() * this.menuW - 1;
                if (this.w > GameCanvas.w - 2) {
                    this.w = GameCanvas.w - 2;
                }

                if (GameCanvas.isTouch) {
                    this.menuSelectedItem = -1;
                }

                this.perFormNPC();
            }
        }
    }

    public boolean isScrolling() {
        return !this.isClose && menuTemY[menuTemY.length - 1] > this.menuY || this.isClose && menuTemY[menuTemY.length - 1] < GameCanvas.h;
    }

    public void updateMenuKey() {
        if (this.showMenu) {
            if (!this.isScrolling()) {
                boolean changeFocus = false;
                if (!GameCanvas.keyPressedz[2] && !GameCanvas.keyPressedz[4]) {
                    if (!GameCanvas.keyPressedz[8] && !GameCanvas.keyPressedz[6]) {
                        if (GameCanvas.keyPressedz[5]) {
                            if (this.center != null) {
                                if (this.center.idAction > 0 && this.center.actionListener != GameScr.gI()) {
                                    this.perform(this.center.idAction, this.center.p);
                                }
                            } else {
                                this.waitToPerform = 2;
                            }
                        } else if (GameCanvas.keyPressedz[12]) {
                            if (this.isScrolling()) {
                                return;
                            }

                            if (this.left.idAction > 0) {
                                this.perform(this.left.idAction, this.left.p);
                            } else {
                                this.waitToPerform = 2;
                            }

                            SoundMn.gI().buttonClose();
                        } else if (!this.disableClose && GameCanvas.keyPressedz[13]) {
                            if (this.isScrolling()) {
                                return;
                            }

                            if (!this.close) {
                                this.close = true;
                            }

                            this.isClose = true;
                            SoundMn.gI().buttonClose();
                        }
                    } else {
                        changeFocus = true;
                        ++this.menuSelectedItem;
                        if (this.menuSelectedItem > this.menuItems.size() - 1) {
                            this.menuSelectedItem = 0;
                        }
                    }
                } else {
                    changeFocus = true;
                    --this.menuSelectedItem;
                    if (this.menuSelectedItem < 0) {
                        this.menuSelectedItem = this.menuItems.size() - 1;
                    }
                }

                if (changeFocus) {
                    cmtoX = this.menuSelectedItem * this.menuW + this.menuW - GameCanvas.w / 2;
                    if (cmtoX > cmxLim) {
                        cmtoX = cmxLim;
                    }

                    if (cmtoX < 0) {
                        cmtoX = 0;
                    }

                    if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
                        cmx = cmtoX;
                    }
                }

                if (!this.disableClose && GameCanvas.isPointerJustRelease[0] && !GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH, 0) && !this.pointerIsDowning) {
                    if (!this.isScrolling()) {
                        this.pointerDownTime = this.pointerDownFirstX = 0;
                        this.pointerIsDowning = false;
                        GameCanvas.clearAllPointerEvent();
                        this.isClose = true;
                        this.close = true;
                        SoundMn.gI().buttonClose();
                    }
                } else {
                    int dx;
                    int s;
                    if (GameCanvas.isPointerDown[0]) {
                        if (!this.pointerIsDowning && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH, 0)) {
                            for (dx = 0; dx < this.pointerDownLastX.length; ++dx) {
                                this.pointerDownLastX[0] = GameCanvas.px[0];
                            }

                            this.pointerDownFirstX = GameCanvas.px[0];
                            this.pointerIsDowning = true;
                            this.isDownWhenRunning = this.cmRun != 0;
                            this.cmRun = 0;
                        } else if (this.pointerIsDowning) {
                            ++this.pointerDownTime;
                            if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px[0] && !this.isDownWhenRunning) {
                                this.pointerDownFirstX = -1000;
                                this.menuSelectedItem = (cmtoX + GameCanvas.px[0] - this.menuX) / this.menuW;
                            }

                            dx = GameCanvas.px[0] - this.pointerDownLastX[0];
                            if (dx != 0 && this.menuSelectedItem != -1) {
                                this.menuSelectedItem = -1;
                            }

                            for (s = this.pointerDownLastX.length - 1; s > 0; --s) {
                                this.pointerDownLastX[s] = this.pointerDownLastX[s - 1];
                            }

                            this.pointerDownLastX[0] = GameCanvas.px[0];
                            cmtoX -= dx;
                            if (cmtoX < 0) {
                                cmtoX = 0;
                            }

                            if (cmtoX > cmxLim) {
                                cmtoX = cmxLim;
                            }

                            if (cmx < 0 || cmx > cmxLim) {
                                dx /= 2;
                            }

                            cmx -= dx;
                            if (cmx < -(GameCanvas.h / 3)) {
                                this.wantUpdateList = true;
                            } else {
                                this.wantUpdateList = false;
                            }
                        }
                    }

                    if (GameCanvas.isPointerJustRelease[0] && this.pointerIsDowning) {
                        dx = GameCanvas.px[0] - this.pointerDownLastX[0];
                        GameCanvas.isPointerJustRelease[0] = false;
                        if (Util.abs(dx) < 20 && Util.abs(GameCanvas.px[0] - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                            this.cmRun = 0;
                            cmtoX = cmx;
                            this.pointerDownFirstX = -1000;
                            this.menuSelectedItem = (cmtoX + GameCanvas.px[0] - this.menuX) / this.menuW;
                            this.pointerDownTime = 0;
                            this.waitToPerform = 10;
                        } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                            this.pointerDownTime = 0;
                            this.waitToPerform = 1;
                        } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                            if (cmx < 0) {
                                cmtoX = 0;
                            } else if (cmx > cmxLim) {
                                cmtoX = cmxLim;
                            } else {
                                s = GameCanvas.px[0] - this.pointerDownLastX[0] + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                                if (s > 10) {
                                    s = 10;
                                } else if (s < -10) {
                                    s = -10;
                                } else {
                                    s = 0;
                                }

                                this.cmRun = -s * 100;
                            }
                        }

                        this.pointerIsDowning = false;
                        this.pointerDownTime = 0;
                        GameCanvas.isPointerJustRelease[0] = false;
                    }

                    GameCanvas.clearKeyPressed();
                    GameCanvas.clearKeyHold();
                }
            }
        }
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            cmtoX += this.cmRun / 100;
            if (cmtoX < 0) {
                cmtoX = 0;
            } else if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            } else {
                cmx = cmtoX;
            }

            this.cmRun = this.cmRun * 9 / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }

        if (cmx != cmtoX && !this.pointerIsDowning) {
            this.cmvx = cmtoX - cmx << 2;
            this.cmdx += this.cmvx;
            cmx += this.cmdx >> 4;
            this.cmdx &= 15;
        }

    }

    public void paintMenu(mGraphics g) {
        this.paintNormal(g);
    }

    public void paintNormal(mGraphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.translate(-cmx, 0);

        for (int i = 0; i < this.menuItems.size(); ++i) {
            if (i == this.menuSelectedItem) {
                g.drawImage(imgMenu2, this.menuX + i * this.menuW + 1, menuTemY[i] + 1, 0, false);
            } else {
                g.drawImage(imgMenu1, this.menuX + i * this.menuW + 1, menuTemY[i] + 1, 0, false);
            }

            mCommand cmd = (mCommand) this.menuItems.elementAt(i);
            String[] sc = ((mCommand) this.menuItems.elementAt(i)).subCaption;
            if (sc == null) {
                sc = new String[]{((mCommand) this.menuItems.elementAt(i)).caption};
            }

            int yCaptionStart = menuTemY[i] + (this.menuH - sc.length * 14) / 2 + 1 + (sc.length == 3 ? sc.length * 4 : 10);
            int yss = 4;
            if (sc.length == 1) {
                yss = 5;
            }

            int ysf = 0;
            int xs = 0;
            if (mSystem.isPC || mSystem.isIP) {
                ysf = -1;
            }

            int timecount = 0;
            if (cmd.timecountDown - mSystem.currentTimeMillis() > 0L) {
                timecount = (int) ((cmd.timecountDown - mSystem.currentTimeMillis()) / 1000L);
            }

            for (int k = 0; k < sc.length; ++k) {
                if (i == this.menuSelectedItem) {
                    xs = 1;
                }

                if (timecount > 0) {
                    if (k == sc.length - 1) {
                        mFont.tahoma_7_gray.drawString(g, sc[k] + " " + timecount, this.menuX + i * this.menuW + this.menuW / 2 + xs, yCaptionStart + k * 14 - yss - sc.length * 2 + ysf + xs, 2, false);
                    } else {
                        mFont.tahoma_7_gray.drawString(g, sc[k], this.menuX + i * this.menuW + this.menuW / 2 + xs, yCaptionStart + k * 14 - yss - sc.length * 2 + ysf + xs, 2, false);
                    }
                } else {
                    FontTeam.fontTile.drawString(g, sc[k], this.menuX + i * this.menuW + this.menuW / 2 + xs, yCaptionStart + k * 14 - yss - sc.length * 2 + ysf + xs, 2, false);
                }
            }
        }

        g.translate(-g.getTranslateX(), -g.getTranslateY());
        if (!this.infomore.equals("")) {
            this.paintDialog(g);
        }

    }

    public void paintDialog(mGraphics g) {
        GameCanvas.resetTrans(g);
        g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
        int yDlg = GameCanvas.h - this.h - 58;
        if (this.idBig != -1) {
            ImageIcon img = GameData.getImgIcon((short) this.idBig);
            if (img != null && img.img != null) {
                g.drawImage(img.img, GameCanvas.hw + this.wShowPaper / 2 + 10, yDlg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            }
        }

        GameCanvas.paintz.paintPaper(g, GameCanvas.hw - this.wShowPaper / 2, yDlg - 5, this.wShowPaper, this.h, this.maxWShow);
        if (this.info != null) {
            int hFont = FontTeam.arialFont.getHeight();
            int leght = this.info.length;
            int yBegin = yDlg + this.h / 2 - leght * hFont / 2 - 5;
            if (this.Canpaint) {
                for (int i = 0; i < leght; ++i) {
                    FontTeam.arialFont.drawString(g, this.info[i], GameCanvas.hw, yBegin, 2, false);
                    yBegin += hFont;
                }
            }
        }

        GameCanvas.resetTrans(g);
    }

    public void doCloseMenu() {
        this.isClose = false;
        this.showMenu = false;
        this.infomore = "";
        if (this.touch && this.menuSelectedItem >= 0) {
            mCommand c = (mCommand) this.menuItems.elementAt(this.menuSelectedItem);
            if (c != null && c.timecountDown - mSystem.currentTimeMillis() <= 0L) {
                c.performAction();
            }
        }

    }

    public void doCloseMenuPC() {
        this.isClose = false;
        this.showMenu = false;
        this.infomore = "";
    }

    public void performSelect() {
        if (this.menuSelectedItem >= 0) {
            if (this.isOptionMenu) {
                GameService.gI().Dynamic_Menu((short) this.IDNPC, (byte) this.ID, (byte) this.menuSelectedItem);
                this.isOptionMenu = false;
            } else {
                mCommand c = (mCommand) this.menuItems.elementAt(this.menuSelectedItem);
                if (c != null) {
                    c.performAction();
                }
            }
        }

    }

    public void perFormNPC() {
    }

    public void endScroll() {
        for (int i = 0; i < menuTemY.length; ++i) {
            if (menuTemY[i] > this.menuY) {
                menuTemY[i] = this.menuY;
            }
        }

        this.wShowPaper = this.maxWShow;
        this.waitToPerform = 0;
    }

    public void updateMenu() {
        this.moveCamera();
        if (!this.infomore.equals("")) {
            this.updatePaper();
        }

        int[] var10000;
        int i;
        int delta;
        if (!this.isClose) {
            ++this.tDelay;

            for (i = 0; i < menuTemY.length; ++i) {
                if (menuTemY[i] > this.menuY) {
                    delta = menuTemY[i] - this.menuY >> 1;
                    if (delta < 1) {
                        delta = 1;
                    }

                    if (this.tDelay > i) {
                        var10000 = menuTemY;
                        var10000[i] -= delta;
                    }
                }
            }

            if (menuTemY[menuTemY.length - 1] <= this.menuY) {
                this.tDelay = 0;
            }
        } else {
            ++this.tDelay;

            for (i = 0; i < menuTemY.length; ++i) {
                if (menuTemY[i] < GameCanvas.h) {
                    delta = (GameCanvas.h - menuTemY[i] >> 1) + 2;
                    if (delta < 1) {
                        delta = 1;
                    }

                    if (this.tDelay > i) {
                        var10000 = menuTemY;
                        var10000[i] += delta;
                    }
                }
            }

            if (menuTemY[menuTemY.length - 1] >= GameCanvas.h) {
                this.tDelay = 0;
                this.doCloseMenu();
            }
        }

        if (xc != 0) {
            xc >>= 1;
            if (xc < 0) {
                xc = 0;
            }
        }

        if (!this.isScrolling()) {
            if (this.waitToPerform > 0) {
                --this.waitToPerform;
                if (this.waitToPerform == 0 && this.menuSelectedItem < this.isNotClose.length && this.menuSelectedItem >= 0) {
                    if (!this.isNotClose[this.menuSelectedItem]) {
                        this.isClose = true;
                        this.touch = true;
                    } else {
                        this.performSelect();
                    }
                }
            }

        }
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case -1:
                if (this.IDNPC == 53) {
                    GameScr.mainChar.addEffectSkill(156, GameScr.mainChar.x, GameScr.mainChar.y);
                }

                GameService.gI().Dynamic_Menu((short) this.IDNPC, (byte) this.ID, (byte) this.menuSelectedItem);
            default:
        }
    }

    public void updatePaper() {
        if (this.wShowPaper < this.maxWShow) {
            this.wShowPaper += this.vShow;
            if (this.wShowPaper >= this.maxWShow) {
                this.wShowPaper = this.maxWShow;
                this.Canpaint = true;
                this.vShow = 20;
            }

            if (this.vShow <= 100) {
                this.vShow += 25;
                if (this.vShow > 100) {
                    this.vShow = 100;
                }
            }
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }
}
