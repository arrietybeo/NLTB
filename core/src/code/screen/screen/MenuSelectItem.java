package code.screen.screen;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.Char;
import code.model.IActionListener;
import code.model.ImageIcon;
import code.model.MainChar;
import code.model.Scroll;
import code.model.ScrollResult;
import code.model.T;
import code.model.mCommand;
import code.network.GameService;
import code.screen.MenuLogin;
import code.screen.Res;
import code.screen.SkillTemplate;
import code.screen.Util;
import code.screen.event.EventScreen;

import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.Rms;
import lib.Session_ME;
import lib.TCanvas;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.SoundMn;
import lib2.mFont;

public class MenuSelectItem extends ScreenTeam implements IActionListener {
    public boolean showMenu;
    public mVector menuItems = new mVector();
    public int menuSelectedItem;
    public int menuX;
    public int menuY;
    public int menuW;
    public int menuH;
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
    public static Image[] imgMenu;
    public final byte autoDanh;
    public final byte autoNhat;
    public final byte autoHoTro;
    public final byte tabFocus;
    public static int indexAuto;
    public int hpaintListSkillBuff;
    public boolean isMenuAuto;
    public int indexPerHp;
    public int indexPerMp;
    public int tick;
    public boolean isViewTab;
    public int indexFocusViewTab;
    public boolean[] isAutoDanh;
    public boolean[] FocusAutoDanh;
    public boolean[] isTabFocus;
    public static boolean[] isAutoNhat;
    public static boolean[] FocusAutoNhat;
    public boolean[] FocusTabMucTieu;
    public int[][] xyCheckAutoDanh;
    public int[][] xyCheckAutoNhat;
    public int[][] xyCheckTabFocus;
    public int[][] xySkillBuff;
    public int[][] xyNutTangHpMp;
    public int[][] idSkillBuff;
    public long[] CoolDownSkillBuff;
    public int wKhungAuto;
    public int hKhungAuto;
    public int xKhungAuto;
    public int yKhungAuto;
    public Scroll listSkillBuf;
    public Scroll listAutoGetitem;
    boolean disableClose;
    boolean isOptionMenu;
    boolean isSelectSkill;
    int sizeauto;
    mCommand cmdClose;
    public mCommand cmdHopThu;
    public mCommand cmdDeoKhan;
    public mCommand cmdnhom;
    public mCommand cmdDoSat;
    public mCommand cmdQuest;
    public mCommand cmdBanBe;
    public mCommand cmdNapTien;
    public mCommand cmdAuto;
    public mCommand cmdDienDan;
    public mCommand cmdThoatGame;
    public mCommand cmdChatWorld;
    public mCommand cmdChangeTouch;
    public mCommand cmdBanghoi;
    public mCommand cmdHoatDong;
    public int IDNPC;
    public int ID;
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

    public static void load() {
        try {
            if (imgMenu == null) {
                imgMenu = new Image[11];
                int[] path = new int[]{0, 1, 2, 3, 4, 5, 7, 9, 10, 11, 6};
                if (mSystem.isPC) {
                    path = new int[]{0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 6};
                    imgMenu = new Image[12];
                }

                if (mSystem.isAnNaptien()) {
                    path = new int[]{0, 1, 2, 3, 4, 5, 7, 9, 11, 6};
                    imgMenu = new Image[10];
                }

                for (int i = 0; i < imgMenu.length; ++i) {
                    if (imgMenu[i] == null) {
                        imgMenu[i] = GameCanvas.loadImage("/hd/menu/c" + path[i] + ".png");
                    }
                }
            }
        } catch (Exception var2) {
        }

    }

    public MenuSelectItem() {
        this.right = new mCommand("Đóng", 0, GameCanvas.w - 71, GameCanvas.h - mCommand.hButtonCmd + 1);
        this.center = null;
        this.autoDanh = 0;
        this.autoNhat = 1;
        this.autoHoTro = 2;
        this.tabFocus = 3;
        this.hpaintListSkillBuff = 40;
        this.indexPerHp = 51;
        this.indexPerMp = 5;
        this.indexFocusViewTab = -1;
        this.isAutoDanh = new boolean[6];
        this.FocusAutoDanh = new boolean[6];
        this.isTabFocus = new boolean[5];
        this.FocusTabMucTieu = new boolean[4];
        this.xyCheckAutoDanh = new int[6][];
        this.xyCheckAutoNhat = new int[3][];
        this.xyCheckTabFocus = new int[5][];
        this.xySkillBuff = new int[3][];
        this.xyNutTangHpMp = new int[4][];
        this.idSkillBuff = new int[4][];
        this.CoolDownSkillBuff = new long[9];
        this.wKhungAuto = 180;
        this.hKhungAuto = 150;
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.listSkillBuf = new Scroll();
        this.listAutoGetitem = new Scroll();
        this.isOptionMenu = false;
        this.pa = 0;
        this.trans = false;
        this.pointerDownLastX = new int[3];
        this.cmdHopThu = new mCommand(T.hopthu, this, 0);
        this.menuItems.addElement(this.cmdHopThu);
        this.cmdDeoKhan = new mCommand(T.deokhan, this, 1);
        this.menuItems.addElement(this.cmdDeoKhan);
        this.cmdDoSat = new mCommand(T.changpk, this, 2);
        this.menuItems.addElement(this.cmdDoSat);
        this.cmdBanBe = new mCommand(T.Banbe, this, 4);
        this.menuItems.addElement(this.cmdBanBe);
        this.cmdAuto = new mCommand(T.auto, this, 6);
        this.menuItems.addElement(this.cmdAuto);
        this.cmdnhom = new mCommand(T.nhom1, this, 9);
        this.menuItems.addElement(this.cmdnhom);
        this.cmdChatWorld = new mCommand(T.chatWorld, this, 10);
        this.menuItems.addElement(this.cmdChatWorld);
        if (mSystem.isPC) {
            if (TCanvas.ScreenSize == 1) {
                this.cmdChangeTouch = new mCommand(T.bigScreen, this, 13);
            } else {
                this.cmdChangeTouch = new mCommand(T.smallScreen, this, 13);
            }

            this.menuItems.addElement(this.cmdChangeTouch);
        }

        this.cmdBanghoi = new mCommand(T.banghoi, this, 15);
        this.menuItems.addElement(this.cmdBanghoi);
        if (!mSystem.isAnNaptien()) {
            this.cmdNapTien = new mCommand(T.naptien, this, 16);
            this.menuItems.addElement(this.cmdNapTien);
        }

        this.cmdHoatDong = new mCommand(T.hoatdong, this, 31);
        this.menuItems.addElement(this.cmdHoatDong);
        this.cmdThoatGame = new mCommand(T.thoat, this, 8);
        this.menuItems.addElement(this.cmdThoatGame);

        int i;
        for (i = 0; i < this.xyCheckAutoDanh.length; ++i) {
            this.xyCheckAutoDanh[i] = new int[2];
            this.xyCheckAutoDanh[i][0] = this.xKhungAuto + 14;
            this.xyCheckAutoDanh[i][1] = this.yKhungAuto + 30 + i * 20;
        }

        for (i = 0; i < this.xyCheckAutoNhat.length; ++i) {
            this.xyCheckAutoNhat[i] = new int[2];
        }

        for (i = 0; i < this.xyCheckTabFocus.length; ++i) {
            this.xyCheckTabFocus[i] = new int[2];
            this.xyCheckTabFocus[i][0] = this.xKhungAuto + 14;
            this.xyCheckTabFocus[i][1] = this.yKhungAuto + 36 + i * 20;
        }

        for (i = 0; i < this.isTabFocus.length; ++i) {
            this.isTabFocus[i] = true;
        }

        this.isTabFocus[3] = false;
        this.xyCheckAutoNhat[0][0] = this.xKhungAuto + 14;
        this.xyCheckAutoNhat[0][1] = this.yKhungAuto + 36;
        this.xyCheckAutoNhat[1][0] = this.xKhungAuto + 14;
        this.xyCheckAutoNhat[1][1] = this.yKhungAuto + 56;
        this.xyCheckAutoNhat[2][0] = this.xKhungAuto + 14;
        this.xyCheckAutoNhat[2][1] = this.yKhungAuto + 76;

        for (i = 0; i < this.xyNutTangHpMp.length; ++i) {
            this.xyNutTangHpMp[i] = new int[2];
        }

        this.xyNutTangHpMp[0][0] = this.xKhungAuto + 14;
        this.xyNutTangHpMp[0][1] = this.xyCheckAutoDanh[2][1] + 5;
        this.xyNutTangHpMp[1][0] = this.xKhungAuto + this.wKhungAuto - 14;
        this.xyNutTangHpMp[1][1] = this.xyCheckAutoDanh[2][1] + 5;
        this.xyNutTangHpMp[2][0] = this.xKhungAuto + 14;
        this.xyNutTangHpMp[2][1] = this.xyCheckAutoDanh[4][1] + 5;
        this.xyNutTangHpMp[3][0] = this.xKhungAuto + this.wKhungAuto - 14;
        this.xyNutTangHpMp[3][1] = this.xyCheckAutoDanh[4][1] + 5;
        this.cmdClose = new mCommand("Đóng", this, 1000);
        if (GameCanvas.isTouch) {
            this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2, this.yKhungAuto + this.hKhungAuto + 2);
        }

        if (!GameCanvas.isTouch) {
            this.right = this.cmdClose;
        }

        for (i = 0; i < this.idSkillBuff.length; ++i) {
            this.idSkillBuff[i] = new int[4];
            this.idSkillBuff[i][0] = -1;
            this.idSkillBuff[i][1] = -1;
            this.idSkillBuff[i][2] = 0;
            this.idSkillBuff[i][3] = 0;
        }

        int size = 30;

        for (int cc = 0; cc < this.xySkillBuff.length; ++cc) {
            this.xySkillBuff[cc] = new int[2];
            this.xySkillBuff[cc][0] = this.xKhungAuto + (this.wKhungAuto - size * 3) / 4 * (cc + 1) + size / 2 + size * (cc % 3);
            this.xySkillBuff[cc][1] = this.yKhungAuto + size / 2 + 30;
        }

    }

    public void startWithoutCloseButton(mVector menuItems, int pos) {
        this.startAt(menuItems, pos);
        this.disableClose = true;
    }

    public void startAt() {
        if (!this.showMenu) {
            mSound.playSound(27, mSound.volumeSound);
            this.isClose = false;
            this.touch = false;
            this.close = false;
            this.tDelay = 0;
            if (this.menuItems.size() == 1) {
                this.menuSelectedItem = 0;
                mCommand c = (mCommand) this.menuItems.elementAt(0);
                if (c != null && c.caption.equals("Nói chuyện")) {
                    c.performAction();
                    this.showMenu = false;
                    return;
                }
            }

            SoundMn.gI().openMenu();
            this.isNotClose = new boolean[this.menuItems.size()];

            int i;
            for (i = 0; i < this.isNotClose.length; ++i) {
                this.isNotClose[i] = false;
            }

            this.disableClose = false;
            if (this.menuItems.size() != 0) {
                this.menuW = 34;
                this.menuH = 40;

                for (i = 0; i < this.menuItems.size(); ++i) {
                    mCommand c = (mCommand) this.menuItems.elementAt(i);
                    c.isPlaySoundButton = false;
                }

                menuTemY = new int[this.menuItems.size()];
                this.menuX = (GameCanvas.w - this.menuItems.size() * this.menuW) / 2;
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
                this.w = this.menuItems.size() * this.menuW - 1;
                if (this.w > GameCanvas.w - 2) {
                    this.w = GameCanvas.w - 2;
                }

                this.menuSelectedItem = -1;
            }
        }
    }

    public boolean isScrolling() {
        return !this.isClose && menuTemY[menuTemY.length - 1] > this.menuY || this.isClose && menuTemY[menuTemY.length - 1] < GameCanvas.h;
    }

    public void updateMenuKey() {
        if (this.showMenu) {
            if (this.isMenuAuto) {
                this.updateKeyAuto();
            } else if (!this.isScrolling()) {
                boolean changeFocus = false;
                if (!GameCanvas.keyPressedz[2] && !GameCanvas.keyPressedz[4]) {
                    if (!GameCanvas.keyPressedz[8] && !GameCanvas.keyPressedz[6]) {
                        if (GameCanvas.keyPressedz[5]) {
                            if (this.center != null) {
                                Cout.println(" ressedz[5]  center.idAction " + this.center.idAction);
                                if (this.center.idAction > 0) {
                                    if (this.center.actionListener == GameScr.gI()) {
                                        Cout.println(" GameScr.gI()    ");
                                    } else {
                                        this.perform(this.center.idAction, this.center.p);
                                    }
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
                            this.waitToPerform = 5;
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
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.translate(-cmx, 0);
        int i;
        for (i = 0; i < this.menuItems.size(); i++) {
            if (i == this.menuSelectedItem) {
                g.drawRegion(imgMenu[i], 0, 34, 34, 34, 0, this.menuX + i * this.menuW + 1, menuTemY[i] + 1 - 10, 0, false);
            } else {
                g.drawRegion(imgMenu[i], 0, 34, 34, 34, 0, this.menuX + i * this.menuW + 1, menuTemY[i] + 1, 0, false);
            }
            String[] sc = ((mCommand) this.menuItems.elementAt(i)).subCaption;
            if (sc == null)
                sc = new String[]{((mCommand) this.menuItems.elementAt(i)).caption};
            int yCaptionStart = menuTemY[i] + (this.menuH - sc.length * 14) / 2 + 1 + ((sc.length == 3) ? (sc.length * 4) : 10);
            for (int k = 0; k < sc.length; k++) {
                if (i == this.menuSelectedItem && !this.isClose) {
                    int xs = 4;
                    GameScr.Font3d(g, sc[k], xs + this.menuX + i * this.menuW + this.menuW / 2 - 2, yCaptionStart + k * 14 - 14 - sc.length * 2 - this.menuH / 2 - 10, 2, mFont.tahoma_7b_yellow);
                }
            }
        }
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        if (this.isMenuAuto) {
            Res.paintDlgDragonFullNew(g, this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 60, 60, GameScr.imgBk[0], false);
            g.setColor(-9751532);
            g.fillRect(this.xKhungAuto, this.yKhungAuto - 28, this.wKhungAuto, 28, false);
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
            g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xKhungAuto + this.wKhungAuto / 2 + 44 + 1, this.yKhungAuto - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
            FontTeam.fontTile.drawString(g, T.auto, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 19, 2, false);
            g.setColor(Res.nColor[1]);
            g.drawLine(this.xKhungAuto + 1, this.yKhungAuto + 22, this.xKhungAuto + this.wKhungAuto - 2, this.yKhungAuto + 22, false);
            int j;
            for (j = 0; j < T.nameTabAuto.length; j++) {
                if (j != indexAuto || (!this.isViewTab && this.tick < 20)) {
                    g.setColor(-9751532);
                } else {
                    g.setColor(-6725831);
                }
                g.fillRect(this.xKhungAuto + j * this.sizeauto, this.yKhungAuto + 1, this.sizeauto - 1, 21, false);
            }
            g.setColor(Res.nColor[1]);
            for (j = 0; j < T.nameTabAuto.length; j++)
                g.drawRect(this.xKhungAuto + j * this.sizeauto, this.yKhungAuto + 1, this.sizeauto - 1, 21, false);
            switch (indexAuto) {
                case 0:
                    paintAutoDanh(g);
                    break;
                case 1:
                    paintAutoNhat(g);
                    break;
                case 2:
                    paintAutoBuff(g);
                    break;
                case 3:
                    paintTabMucTieu(g);
                    break;
            }
            if (GameCanvas.isTouch)
                this.cmdClose.paint(g);
            for (j = 0; j < T.nameTabAuto.length; j++) {
                g.setColor(Res.nColor[1]);
                g.drawRect(this.xKhungAuto + j * this.sizeauto, this.yKhungAuto + 1, this.sizeauto - 1, 21, false);
                if (j != indexAuto) {
                    mFont.tahoma_7_white.drawString(g, T.nameTabAuto[j], this.xKhungAuto + j * this.sizeauto + 21 + 1, this.yKhungAuto + 7, 2, false);
                } else {
                    mFont.tahoma_7_yellow.drawString(g, T.nameTabAuto[j], this.xKhungAuto + j * this.sizeauto + 21 + 1, this.yKhungAuto + 7, 2, false);
                }
            }
        }
        paint(g);
    }


    public void doCloseMenu() {
        if (this.menuSelectedItem != 4) {
            this.isClose = false;
            this.showMenu = false;
            SoundMn.gI().buttonClose();
        }

        if (this.touch && this.menuSelectedItem >= 0) {
            mCommand c = (mCommand) this.menuItems.elementAt(this.menuSelectedItem);
            if (c != null) {
                SoundMn.gI().buttonClose();
                c.performAction();
            }
        }

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

    public void updateMenu() {
        ++this.tick;
        this.moveCamera();
        ScrollResult s1;
        int delta;
        if (indexAuto == 1 && GameCanvas.isTouch) {
            if (GameCanvas.isPointerClick[0] && this.getCmdPointerLast(this.cmdClose)) {
                GameCanvas.isPointerJustRelease[0] = false;
                this.cmdClose.performAction();
                return;
            }

            s1 = this.listAutoGetitem.updateKey();
            this.listAutoGetitem.updatecm();
            if (GameCanvas.isPointerClick[0] && this.listAutoGetitem.selectedItem != -1 && !this.listAutoGetitem.isDownWhenRunning) {
                if (isAutoNhat[this.listAutoGetitem.selectedItem]) {
                    isAutoNhat[this.listAutoGetitem.selectedItem] = false;
                } else {
                    isAutoNhat[this.listAutoGetitem.selectedItem] = true;
                }
            }

            for (delta = 0; delta < T.nameTabAuto.length; ++delta) {
                if (GameCanvas.isPointerClick[0] && delta != indexAuto && GameCanvas.isPointer(this.xKhungAuto + delta * 40 + 1, this.yKhungAuto + 1, 40, 21, 0)) {
                    indexAuto = delta;
                }
            }
        }

        if (this.isMenuAuto) {
            s1 = this.listSkillBuf.updateKey();
            this.listSkillBuf.updatecm();
            if (indexAuto == 2 && this.listSkillBuf.selectedItem != -1 && this.indexFocusViewTab != -1) {
                delta = 0;

                for (int i = 0; i < GameScr.vec_skill.size(); ++i) {
                    SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(i);
                    if (skill != null && skill.type == 1) {
                        if (delta == this.listSkillBuf.selectedItem) {
                            if (this.idSkillBuff[this.indexFocusViewTab][0] != -1 && this.idSkillBuff[this.indexFocusViewTab][1] == skill.idEffStartSkill) {
                                if (this.idSkillBuff[this.indexFocusViewTab][1] == skill.idEffStartSkill) {
                                    this.idSkillBuff[this.indexFocusViewTab][0] = -1;
                                    this.idSkillBuff[this.indexFocusViewTab][1] = -1;
                                    Rms.saveAutoBuff();
                                }
                            } else {
                                boolean isAdded = false;

                                for (int j = 0; j < this.idSkillBuff.length; ++j) {
                                    if (this.idSkillBuff[j][1] == skill.idEffStartSkill) {
                                        isAdded = true;
                                        break;
                                    }
                                }

                                if (!isAdded) {
                                    this.idSkillBuff[this.indexFocusViewTab][0] = i;
                                    this.idSkillBuff[this.indexFocusViewTab][1] = skill.idEffStartSkill;
                                    this.idSkillBuff[this.indexFocusViewTab][3] = skill.getCoolDown(Char.levelSkill[i] - 1);
                                    Rms.saveAutoBuff();
                                }
                            }

                            this.listSkillBuf.selectedItem = -1;
                            break;
                        }

                        ++delta;
                    }
                }
            }

            this.updatePointerAuto();
        } else {
            int[] var10000;
            int i;
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
                    if (!this.isMenuAuto) {
                        this.doCloseMenu();
                    }
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
                    if (this.waitToPerform == 0) {
                        if (!this.isNotClose[this.menuSelectedItem]) {
                            this.isClose = true;
                            this.touch = true;
                        } else {
                            this.performSelect();
                        }
                    }
                }

                super.update();
            }
        }
    }

    public void perform(int idAction, Object p) {
        mVector vec;
        int i;
        mVector vauto;
        int j;
        mSound.playSound(26, mSound.volumeSound);
        switch (idAction) {
            case 0:
                if (EventScreen.vecListEvent.size() == 0) {
                    GameCanvas.addNotify(T.khongcotinnhan, (byte) 0);
                } else if (GameCanvas.mevent != null) {
                    GameCanvas.mevent.init();
                    GameCanvas.mevent.switchToMe(GameCanvas.gameScr);
                }
                GameScr.numMSG = 0;
                break;
            case 1:
                vec = new mVector();
                for (i = 0; i < T.listKhan.length; i++) {
                    mCommand cmd = new mCommand(T.listKhan[i], (IActionListener) GameCanvas.menu2, -1);
                    vec.addElement(cmd);
                }
                GameCanvas.menu2.startArt(vec, 1, T.deokhan);
                break;
            case 2:
                if (GameScr.mainChar.typePK == -1) {
                    GameCanvas.startYesNoDlg(T.dosat, new mCommand(T.bat, this, 30));
                    break;
                }
                GameService.gI().changePK((byte) -1);
                break;
            case 3:
                MainMenu.gI().switchToMe(GameCanvas.gameScr);
                MainMenu.gI().showQuest();
                break;
            case 4:
                GameService.gI().Friend((byte) 4, "");
                break;
            case 5:
                GameCanvas.gameScr.hideGUI = 2;
                GameService.gI().napDiamond();
                break;
            case 6:
                this.isMenuAuto = true;
                indexAuto = 0;
                this.sizeauto = this.wKhungAuto / 4;
                break;
            case 8:
                GameCanvas.endDlg();
                Session_ME.gI().close();
                GameCanvas.gameScr.clearloadMap();
                GameScr.removeAllchat();
                MenuLogin.gI().switchToMe();
                break;
            case 9:
                if (GameScr.mainChar.idNhom != -1) {
                    GameCanvas.gameScr.hideGUI = 2;
                    GameService.gI().doCreateParty((byte) 0, (short) -1, (short) -1, "");
                    break;
                }
                GameCanvas.addNotify(T.khongconhom, (byte) 0);
                break;
            case 10:
                GameCanvas.gameScr.chatWorld = true;
                GameCanvas.gameScr.tfChatWorld.doChangeToTextBox();
                if (mSystem.isPC)
                    GameCanvas.gameScr.tfChatWorld.isFocus = true;
                break;
            case 12:
                GameCanvas.gameScr.isMovebyTouch = !GameCanvas.gameScr.isMovebyTouch;
                break;
            case 13:
                GameCanvas.startYesNoDlg(T.tchangsize, new mCommand(T.OK, this, 14));
                break;
            case 14:
                if (TCanvas.ScreenSize == 1) {
                    TCanvas.ScreenSize = 2;
                } else {
                    TCanvas.ScreenSize = 1;
                }
                Rms.saveScreenSize();
                GameCanvas.endDlg();
                GameMidlet.exitApp();
                break;
            case 15:
                if (GameScr.mainChar.idClan != -1 && MainChar.infoOptionClan != null) {
                    if (GameCanvas.isTouch) {
                        GameService.gI().RequestInfoClan((byte) -1, -1);
                        break;
                    }
                    mVector vecbang = new mVector();
                    for (int k = 0; k < MainChar.infoOptionClan.length; k++) {
                        mCommand cmd = new mCommand(MainChar.infoOptionClan[k], (IActionListener) GameCanvas.menu2, 6);
                        vecbang.addElement(cmd);
                    }
                    GameCanvas.menu2.startArt(vecbang, 0, T.banghoi);
                    break;
                }
                GameCanvas.startOKDlg(T.kcobang);
                break;
            case 16:
                if (mSystem.isAndroidStore() || mSystem.isIosAppStore()) {
                    mVector vecNapGoogle = new mVector();
                    for (int k = 0; k < mSystem.google_listGems.length; k++) {
                        mCommand cmd = new mCommand(mSystem.google_listGems[k], (IActionListener) GameCanvas.menu2, 201);
                        vecNapGoogle.addElement(cmd);
                    }
                    GameCanvas.menu2.startArt(vecNapGoogle, 0, T.napgoogle);
                    break;
                }
                GameService.gI().Dynamic_Menu((short) 39, (byte) 0, (byte) 0);
                break;
            case 30:
                GameService.gI().changePK((byte) 0);
                GameCanvas.endDlg();
                break;
            case 31:
                GameService.gI().Dynamic_Menu((short) 55, (byte) 0, (byte) 4);
                break;
            case 1000:
                this.menuSelectedItem = -1;
                this.isMenuAuto = false;
                doCloseMenu();
                vauto = new mVector();
                for (j = 0; j < isAutoNhat.length; j++) {
                    if (isAutoNhat[j]) {
                        SetInfoData sdt = new SetInfoData();
                        sdt.index = j;
                        vauto.addElement(sdt);
                    }
                }
                if (vauto.size() > 0)
                    GameService.gI().autoGetitem(0, vauto);
                break;
        }
        this.menuSelectedItem = -1;
    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void paintAutoDanh(mGraphics g) {
        if (this.FocusAutoDanh[0] && (!this.FocusAutoDanh[0] || GameCanvas.gameTick % 4 != 0)) {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][0], this.xyCheckAutoDanh[0][0] + 10, this.xyCheckAutoDanh[0][1], 0, false);
        } else {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][0], this.xyCheckAutoDanh[0][0] + 10, this.xyCheckAutoDanh[0][1], 0, false);
        }

        if (this.FocusAutoDanh[1] && (!this.FocusAutoDanh[1] || GameCanvas.gameTick % 4 != 0)) {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][1], this.xyCheckAutoDanh[1][0] + 10, this.xyCheckAutoDanh[1][1], 0, false);
        } else {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][1], this.xyCheckAutoDanh[1][0] + 10, this.xyCheckAutoDanh[1][1], 0, false);
        }

        if (this.FocusAutoDanh[3] && (!this.FocusAutoDanh[3] || GameCanvas.gameTick % 4 != 0)) {
            mFont.tahoma_7_yellow.drawString(g, T.menuAuto[0][2], this.xyCheckAutoDanh[3][0] + 10, this.xyCheckAutoDanh[3][1], 0, false);
        } else {
            mFont.tahoma_7_white.drawString(g, T.menuAuto[0][2], this.xyCheckAutoDanh[3][0] + 10, this.xyCheckAutoDanh[3][1], 0, false);
        }

        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[0] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[0][0], this.xyCheckAutoDanh[0][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[1] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[1][0], this.xyCheckAutoDanh[1][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgCheck, 0, this.isAutoDanh[3] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoDanh[3][0], this.xyCheckAutoDanh[3][1] + 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
        if (this.FocusAutoDanh[2] && (!this.FocusAutoDanh[2] || GameCanvas.gameTick % 4 != 0)) {
            g.setColor(-16777216);
        } else {
            g.setColor(-9751532);
        }

        g.fillRect(this.xKhungAuto + 9, this.xyNutTangHpMp[0][1] - 5, this.wKhungAuto - 18, 10, false);
        if (this.FocusAutoDanh[4] && (!this.FocusAutoDanh[4] || GameCanvas.gameTick % 4 != 0)) {
            g.setColor(-16777216);
        } else {
            g.setColor(-9751532);
        }

        g.fillRect(this.xKhungAuto + 9, this.xyNutTangHpMp[2][1] - 5, this.wKhungAuto - 18, 10, false);
        g.setColor(-12772608);
        g.drawRect(this.xKhungAuto + 10, this.xyNutTangHpMp[0][1] - 5, this.wKhungAuto - 20, 10, false);
        g.drawRect(this.xKhungAuto + 10, this.xyNutTangHpMp[2][1] - 5, this.wKhungAuto - 20, 10, false);
        g.drawRegion(GameScr.imgIncrease, 0, 0, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[0][0], this.xyNutTangHpMp[0][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgIncrease, 0, GameScr.imgIncrease.getHeight() / 2, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[1][0], this.xyNutTangHpMp[1][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgIncrease, 0, 0, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[2][0], this.xyNutTangHpMp[2][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.drawRegion(GameScr.imgIncrease, 0, GameScr.imgIncrease.getHeight() / 2, GameScr.imgIncrease.getWidth(), GameScr.imgIncrease.getHeight() / 2, 0, this.xyNutTangHpMp[3][0], this.xyNutTangHpMp[3][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        g.setColor(-4347277);
        g.fillRect(this.xKhungAuto + 20 + this.indexPerHp * ((this.wKhungAuto - 40) / 10) - 5, this.xyNutTangHpMp[0][1] - 4, 10, 9, false);
        g.fillRect(this.xKhungAuto + 20 + this.indexPerMp * ((this.wKhungAuto - 40) / 10) - 5, this.xyNutTangHpMp[2][1] - 4, 10, 9, false);
        mFont.tahoma_7_white.drawString(g, this.indexPerHp * 10 + "%", this.xKhungAuto + this.wKhungAuto / 2, this.xyNutTangHpMp[0][1] - 5, 2, false);
        mFont.tahoma_7_white.drawString(g, this.indexPerMp * 10 + "%", this.xKhungAuto + this.wKhungAuto / 2, this.xyNutTangHpMp[2][1] - 5, 2, false);
    }

    public void paintAutoNhat(mGraphics g) {
        GameCanvas.resetTrans(g);
        this.listAutoGetitem.setStyle(GameScr.infoAutoGetItem.length, 20, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30, true, 1);
        g.ClipRec(this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30);
        this.listAutoGetitem.setClip(g, this.xKhungAuto, this.yKhungAuto + 30, this.wKhungAuto, this.hKhungAuto - 30);

        for (int i = 0; i < GameScr.infoAutoGetItem.length; ++i) {
            if (GameCanvas.isTouch) {
                mFont.tahoma_7_white.drawString(g, GameScr.infoAutoGetItem[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
            } else if (FocusAutoNhat[i] && (!FocusAutoNhat[i] || GameCanvas.gameTick % 4 != 0)) {
                mFont.tahoma_7_yellow.drawString(g, GameScr.infoAutoGetItem[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
            } else {
                mFont.tahoma_7_white.drawString(g, GameScr.infoAutoGetItem[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, true);
            }

            g.drawRegion(GameScr.imgCheck, 0, isAutoNhat[i] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckAutoNhat[0][0], this.xyCheckAutoNhat[0][1] + 20 * i, mGraphics.VCENTER | mGraphics.HCENTER, true);
        }

        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void paintTabMucTieu(mGraphics g) {
        for (int i = 0; i < T.Tabmuctieu.length; ++i) {
            if (this.FocusTabMucTieu[i] && (!this.FocusTabMucTieu[i] || GameCanvas.gameTick % 4 != 0)) {
                mFont.tahoma_7_yellow.drawString(g, T.Tabmuctieu[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, false);
            } else {
                mFont.tahoma_7_white.drawString(g, T.Tabmuctieu[i], this.xKhungAuto + 25, this.yKhungAuto + 30 + 20 * i, 0, false);
            }

            g.drawRegion(GameScr.imgCheck, 0, this.isTabFocus[i] ? GameScr.imgCheck.getHeight() / 2 : 0, GameScr.imgCheck.getWidth(), GameScr.imgCheck.getHeight() / 2, 0, this.xyCheckTabFocus[i][0], this.xyCheckTabFocus[i][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        }

    }

    public void paintAutoBuff(mGraphics g) {
        int hkill = mGraphics.getImageHeight(GameScr.imgTouchMove[3]) / 2;
        int wkill = mGraphics.getImageWidth(GameScr.imgTouchMove[3]);
        int size = 30;
        g.setColor(-11262464);
        g.drawRect(this.xKhungAuto + 19, this.yKhungAuto + this.hKhungAuto - size - 11, this.wKhungAuto - 39, size + 8, false);
        g.setColor(-15850710);
        g.fillRect(this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 8, false);

        int i;
        int j;
        SkillTemplate skill;
        ImageIcon imgskill;
        for (i = 0; i < 3; ++i) {
            if (i == this.indexFocusViewTab) {
                MainMenu.gI().paintFocus(g, this.xySkillBuff[i][0], this.xySkillBuff[i][1], 1, true);
            }

            g.drawRegion(GameScr.imgTouchMove[3], 0, hkill, wkill, hkill, 0, this.xKhungAuto + (this.wKhungAuto - size * 3) / 4 * (i + 1) + size / 2 + size * (i % 3), this.yKhungAuto + size / 2 + 30, 3, false);

            for (j = 0; j < GameScr.vec_skill.size(); ++j) {
                skill = (SkillTemplate) GameScr.vec_skill.elementAt(j);
                if (skill != null && j == this.idSkillBuff[i][0] && Char.levelSkill[this.idSkillBuff[i][0]] >= 0) {
                    imgskill = GameData.getImgIcon((short) (skill.iconId + Res.ID_ICON_SKILL));
                    if (imgskill != null && imgskill.img != null) {
                        g.drawImage(imgskill.img, this.xKhungAuto + (this.wKhungAuto - size * 3) / 4 * (i + 1) + size / 2 + size * (i % 3), this.yKhungAuto + size / 2 + 30, 3, false);
                    }
                }
            }
        }

        i = 0;
        this.listSkillBuf.setStyle(2, size + 5, this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 10, false, 1);
        g.ClipRec(this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 10);
        this.listSkillBuf.setClip(g, this.xKhungAuto + 20, this.yKhungAuto + this.hKhungAuto - size - 10, this.wKhungAuto - 40, size + 10);

        for (j = 0; j < GameScr.vec_skill.size(); ++j) {
            skill = (SkillTemplate) GameScr.vec_skill.elementAt(j);
            imgskill = GameData.getImgIcon((short) (skill.iconId + Res.ID_ICON_SKILL));
            if (imgskill != null && imgskill.img != null && skill.type == 1 && Char.levelSkill[j] >= 0) {
                int lac = 0;
                int[] list = new int[]{-2, -1, 0, 1, 2, 1, 0, -1};
                if (i == this.listSkillBuf.selectedItem) {
                    lac = list[GameCanvas.gameTick % 8];
                }

                g.drawRegion(GameScr.imgTouchMove[3], 0, hkill, wkill, hkill, 0, this.xKhungAuto + 20 + (size + 5) * (i + 1) - size / 2 + lac, this.yKhungAuto + this.hKhungAuto - size / 2 - 5, 3, false);
                g.drawImage(imgskill.img, this.xKhungAuto + 20 + (size + 5) * (i + 1) - size / 2 + lac, this.yKhungAuto + this.hKhungAuto - size / 2 - 5, 3, false);
                ++i;
            }
        }

        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void updateKeyAuto() {
        if (GameCanvas.keyPressedz[13]) {
            this.cmdClose.performAction();
        }

        boolean isUpdate = false;
        if (!this.isViewTab) {
            if (GameCanvas.keyPressedz[4]) {
                indexAuto = indexAuto - 1 < 0 ? 0 : indexAuto - 1;
                isUpdate = true;
            }

            if (GameCanvas.keyPressedz[6]) {
                indexAuto = indexAuto + 1 > T.nameTabAuto.length - 1 ? T.nameTabAuto.length - 1 : indexAuto + 1;
                isUpdate = true;
            }
        }

        if (GameCanvas.keyPressedz[2] && indexAuto != 2) {
            if (this.indexFocusViewTab > -1) {
                --this.indexFocusViewTab;
            }

            if (this.indexFocusViewTab == -1) {
                this.isViewTab = false;
                this.FocusAutoDanh[0] = false;
                FocusAutoNhat[0] = false;
            }

            if (indexAuto == 1 && this.indexFocusViewTab != -1) {
                this.listAutoGetitem.moveTo(this.indexFocusViewTab * 20);
            }

            isUpdate = true;
        }

        if (GameCanvas.keyPressedz[8] && (indexAuto != 2 || indexAuto == 2 && this.indexFocusViewTab == -1)) {
            ++this.indexFocusViewTab;
            if (indexAuto == 1) {
                if (this.indexFocusViewTab > isAutoNhat.length - 1) {
                    this.indexFocusViewTab = 0;
                }

                this.listAutoGetitem.moveTo(this.indexFocusViewTab * 20);
            }

            if (this.indexFocusViewTab >= 0) {
                this.isViewTab = true;
            }

            isUpdate = true;
        }

        if (this.isViewTab) {
            int i;
            switch (indexAuto) {
                case 0:
                    if (this.indexFocusViewTab > this.FocusAutoDanh.length - 1) {
                        this.indexFocusViewTab = this.FocusAutoDanh.length - 1;
                    }

                    if (isUpdate) {
                        for (i = 0; i < this.FocusAutoDanh.length; ++i) {
                            this.FocusAutoDanh[i] = false;
                        }
                    }

                    this.FocusAutoDanh[this.indexFocusViewTab] = true;
                    if (this.FocusAutoDanh[2] && this.isAutoDanh[1]) {
                        if (GameCanvas.keyPressedz[4]) {
                            this.indexPerHp = this.indexPerHp - 1 <= 1 ? 1 : this.indexPerHp - 1;
                        }

                        if (GameCanvas.keyPressedz[6]) {
                            this.indexPerHp = this.indexPerHp + 1 >= 9 ? 9 : this.indexPerHp + 1;
                        }
                    }

                    if (this.FocusAutoDanh[4] && this.isAutoDanh[3]) {
                        if (GameCanvas.keyPressedz[4]) {
                            this.indexPerMp = this.indexPerMp - 1 <= 1 ? 1 : this.indexPerMp - 1;
                        }

                        if (GameCanvas.keyPressedz[6]) {
                            this.indexPerMp = this.indexPerMp + 1 >= 9 ? 9 : this.indexPerMp + 1;
                        }
                    }

                    if (GameCanvas.keyPressedz[5]) {
                        if (this.FocusAutoDanh[0]) {
                            this.isAutoDanh[0] = !this.isAutoDanh[0];
                        }

                        if (this.FocusAutoDanh[1]) {
                            this.isAutoDanh[1] = !this.isAutoDanh[1];
                        }

                        if (this.FocusAutoDanh[3]) {
                            this.isAutoDanh[3] = !this.isAutoDanh[3];
                        }

                        if (this.FocusAutoDanh[5]) {
                            this.isAutoDanh[5] = !this.isAutoDanh[5];
                        }
                    }
                    break;
                case 1:
                    if (this.indexFocusViewTab > FocusAutoNhat.length - 1) {
                        this.indexFocusViewTab = FocusAutoNhat.length - 1;
                    }

                    if (isUpdate) {
                        for (i = 0; i < FocusAutoNhat.length; ++i) {
                            FocusAutoNhat[i] = false;
                        }

                        FocusAutoNhat[this.indexFocusViewTab] = true;
                    }

                    if (GameCanvas.keyPressedz[5] && this.indexFocusViewTab >= 0) {
                        if (isAutoNhat[this.indexFocusViewTab]) {
                            isAutoNhat[this.indexFocusViewTab] = false;
                        } else {
                            isAutoNhat[this.indexFocusViewTab] = true;
                        }
                    }
                    break;
                case 2:
                    if (GameCanvas.keyPressedz[6] && !this.isSelectSkill) {
                        this.indexFocusViewTab = this.indexFocusViewTab + 1 > 2 ? 2 : this.indexFocusViewTab + 1;
                    }

                    if (GameCanvas.keyPressedz[4] && !this.isSelectSkill) {
                        this.indexFocusViewTab = this.indexFocusViewTab - 1 < 0 ? 0 : this.indexFocusViewTab - 1;
                    }

                    if (GameCanvas.keyPressedz[5]) {
                        this.isSelectSkill = !this.isSelectSkill;
                        if (this.isSelectSkill) {
                            this.listSkillBuf.selectedItem = 0;
                        } else {
                            this.listSkillBuf.selectedItem = -1;
                        }
                    }

                    if (this.isSelectSkill) {
                        if (GameCanvas.keyPressedz[6]) {
                            ++this.listSkillBuf.selectedItem;
                        }

                        if (GameCanvas.keyPressedz[4]) {
                            --this.listSkillBuf.selectedItem;
                        }
                    }

                    if (GameCanvas.keyPressedz[2]) {
                        this.indexFocusViewTab = -1;
                        this.isViewTab = false;
                    }
                    break;
                case 3:
                    if (this.indexFocusViewTab > this.FocusTabMucTieu.length - 1) {
                        this.indexFocusViewTab = this.FocusTabMucTieu.length - 1;
                    }

                    if (isUpdate) {
                        for (i = 0; i < this.FocusTabMucTieu.length; ++i) {
                            this.FocusTabMucTieu[i] = false;
                        }

                        this.FocusTabMucTieu[this.indexFocusViewTab] = true;
                    }

                    if (GameCanvas.keyPressedz[5]) {
                        this.isTabFocus[this.indexFocusViewTab] = !this.isTabFocus[this.indexFocusViewTab];
                        if (this.indexFocusViewTab == 1) {
                            if (!this.isTabFocus[this.indexFocusViewTab]) {
                                mSound.pauseCurMusic();
                            }

                            if (this.isTabFocus[this.indexFocusViewTab]) {
                                GameScr.playSound1();
                            }
                        }

                        Rms.saveSound();
                    }
            }
        }

        GameCanvas.clearKeyPressed();
        GameCanvas.clearKeyHold();
    }

    public void updatePointerAuto() {
        if (this.getCmdPointerLast(this.cmdClose)) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdClose != null) {
                this.cmdClose.performAction();
            }
        }

        if (indexAuto == 1) {
            ScrollResult r = this.listAutoGetitem.updateKey();
            this.listAutoGetitem.updatecm();
        }

        int i;
        for (i = 0; i < T.nameTabAuto.length; ++i) {
            if (GameCanvas.isPointerClick[0] && i != indexAuto && GameCanvas.isPointer(this.xKhungAuto + i * this.sizeauto + 1, this.yKhungAuto + 1, this.sizeauto, 21, 0)) {
                indexAuto = i;
            }
        }

        switch (indexAuto) {
            case 0:
                for (i = 0; i < this.xyCheckAutoDanh.length; ++i) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xyCheckAutoDanh[i][0] - 10, this.xyCheckAutoDanh[i][1] - 10, this.wKhungAuto - 20, 20, 0)) {
                        this.isAutoDanh[i] = !this.isAutoDanh[i];
                        if (i == 0) {
                            Rms.saveRMSInt(Rms.Auto_fight, this.isAutoDanh[i] ? 1 : 0);
                        }

                        if (i == 1) {
                            Rms.saveRMSInt(Rms.Auto_HP, this.isAutoDanh[i] ? 1 : 0);
                        }

                        if (i == 3) {
                            Rms.saveRMSInt(Rms.Auto_MP, this.isAutoDanh[i] ? 1 : 0);
                        }
                    }
                }

                for (i = 0; i < this.xyNutTangHpMp.length; ++i) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xyNutTangHpMp[i][0] - 10, this.xyNutTangHpMp[i][1] - 10, 20, 20, 0)) {
                        if (i == 0) {
                            this.indexPerHp = this.indexPerHp - 1 <= 1 ? 1 : this.indexPerHp - 1;
                        }

                        if (i == 1) {
                            this.indexPerHp = this.indexPerHp + 1 >= 9 ? 9 : this.indexPerHp + 1;
                        }

                        if (i == 2) {
                            this.indexPerMp = this.indexPerMp - 1 <= 1 ? 1 : this.indexPerMp - 1;
                        }

                        if (i == 3) {
                            this.indexPerMp = this.indexPerMp + 1 >= 9 ? 9 : this.indexPerMp + 1;
                        }

                        Rms.saveRMSInt(Rms.PerCent_HP, this.indexPerHp);
                        Rms.saveRMSInt(Rms.PerCent_MP, this.indexPerMp);
                    }
                }
            case 1:
            default:
                break;
            case 2:
                if (GameCanvas.gameTick % 2 == 0) {
                    ++MainMenu.gI().coutFc;
                    if (MainMenu.gI().coutFc > 2) {
                        MainMenu.gI().coutFc = 0;
                    }
                }

                for (i = 0; i < this.xySkillBuff.length; ++i) {
                    if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xySkillBuff[i][0] - 15, this.xySkillBuff[i][1] - 15, 30, 30, 0)) {
                        this.indexFocusViewTab = i;
                    }
                }

                return;
            case 3:
                if (GameCanvas.isPointerClick[0]) {
                    for (i = 0; i < this.xyCheckTabFocus.length; ++i) {
                        if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.xyCheckTabFocus[i][0] - 10, this.xyCheckTabFocus[i][1] - 10, this.wKhungAuto - 20, 20, 0)) {
                            this.isTabFocus[i] = !this.isTabFocus[i];
                            if (i == 1) {
                                if (!this.isTabFocus[i]) {
                                    mSound.pauseCurMusic();
                                }

                                if (this.isTabFocus[i]) {
                                    GameScr.playSound1();
                                }
                            }

                            Rms.saveSound();
                        }
                    }
                }
        }

    }

    public boolean isCanFind(byte typeActor) {
        if (this.isTabFocus[this.isTabFocus.length - 1]) {
            return true;
        } else if (typeActor == 0 && this.isTabFocus[0]) {
            return true;
        } else if (typeActor == 1 && this.isTabFocus[1]) {
            return true;
        } else if (typeActor == 2 && this.isTabFocus[2]) {
            return true;
        } else {
            return (typeActor == 14 || typeActor == 3) && this.isTabFocus[3];
        }
    }
}
