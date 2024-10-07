package code.model;

import code.main.GameCanvas;
import code.network.GameService;
import code.screen.Res;
import code.screen.screen.FontTeam;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.Khu;
import code.screen.screen.MainMenu;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Menu2 implements IActionListener {
    public int xKhungAuto;
    public int yKhungAuto;
    public int wKhungAuto;
    public int hKhungAuto;
    public int sizeH = 40;
    public int indexSelect = -1;
    public static Menu2 me;
    public Scroll mScroll = new Scroll();
    public mCommand cmdClose;
    public mCommand cmdpk;
    public mCommand cmdchange;
    public mVector menuItem = new mVector();
    public mVector menucmdAction = new mVector();
    public byte type;
    public byte typeInfo;
    public static final byte TYPE_TEXT = 0;
    public static final byte TYPE_PK = 1;
    public static final byte TYPE_FRIEND = 2;
    public static final byte TYPE_PARTY = 3;
    public static final byte TYPE_INFO = 4;
    public static final byte TYPE_CHAR_DIE = 5;
    public String masterName = "";
    public boolean isShow;
    public String tile;
    public short idIcon;
    public int nitem;
    public int idNPC;
    public int idMenu;
    int[] xpk;

    public void startArt(mVector menuItem, int type, String tile, int numitem) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.nitem = numitem;
        this.type = (byte) type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        if (type == 1) {
            this.wKhungAuto = 170;
            this.hKhungAuto = GameCanvas.h / 4;
        }

        if (type == 4) {
            this.wKhungAuto = 200;
            if (this.wKhungAuto > GameCanvas.w) {
                this.wKhungAuto = GameCanvas.w;
            }
        }

        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand(T.close, this, 200);
        int xcmd = this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2;
        this.indexSelect = -1;
        int i;
        if (type == 1) {
            xcmd = this.xKhungAuto;
            this.xpk = new int[5];

            for (i = 0; i < this.xpk.length; ++i) {
                this.xpk[i] = this.xKhungAuto + 18 + 33 * i;
            }

            this.cmdpk = new mCommand(T.thaoco, this, 1);
            this.cmdpk.setXY(this.xKhungAuto + this.wKhungAuto / 2 + 5, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.mainChar.getTypePK() - 1;
        }

        this.cmdClose.setXY(xcmd, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        ServerInfo sv;
        int j;
        byte wslip;
        String[] arr;
        if (type == 2) {
            for (i = 0; i < menuItem.size(); ++i) {
                sv = (ServerInfo) menuItem.elementAt(i);
                if (sv != null) {
                    for (j = 0; j < sv.info.length; ++j) {
                        wslip = 0;
                        if (!GameCanvas.isTouch) {
                            wslip = 20;
                        }

                        arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.wKhungAuto + wslip - 20);
                        this.nitem += arr.length;
                    }
                }
            }

            this.sizeH = 50;
        }

        this.isShow = true;
        if (type == 4) {
            for (i = 0; i < menuItem.size(); ++i) {
                sv = (ServerInfo) menuItem.elementAt(i);
                if (sv != null) {
                    for (j = 0; j < sv.info.length; ++j) {
                        wslip = 0;
                        if (!GameCanvas.isTouch) {
                            wslip = 20;
                        }

                        arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.wKhungAuto + wslip - 20);
                        if (arr != null && arr.length > 1) {
                            this.nitem += arr.length - 1;
                        }
                    }
                }
            }
        }

    }

    public void startArt(mVector menuItem, int type, String tile) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.type = (byte) type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        if (type == 1) {
            this.wKhungAuto = 170;
            this.hKhungAuto = GameCanvas.h / 4;
        }

        if (type == 4) {
            this.wKhungAuto = 200;
            if (this.wKhungAuto > GameCanvas.w) {
                this.wKhungAuto = GameCanvas.w;
            }
        }

        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("Đóng", this, 200);
        int xcmd = this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2;
        this.indexSelect = -1;
        if (type == 1) {
            xcmd = this.xKhungAuto;
            this.xpk = new int[5];

            for (int i = 0; i < this.xpk.length; ++i) {
                this.xpk[i] = this.xKhungAuto + 18 + 33 * i;
            }

            this.cmdpk = new mCommand(T.thaoco, this, 1);
            this.cmdpk.setXY(this.xKhungAuto + this.wKhungAuto / 2 + 5, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.mainChar.getTypePK() - 1;
        }

        this.cmdClose.setXY(xcmd, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        if (type == 2) {
            this.sizeH = 50;
        }

        this.isShow = true;
    }

    public void startArt(mVector menuItem, int type, String tile, int idmenu, int idNPC) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.type = (byte) type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        this.idMenu = idmenu;
        this.idNPC = idNPC;
        if (type == 1) {
            this.wKhungAuto = 170;
            this.hKhungAuto = GameCanvas.h / 4;
        }
        if (type == 5) {
            this.wKhungAuto = menuItem.size() * 65 + 10;
            this.hKhungAuto = 79;
            this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
            this.yKhungAuto = GameCanvas.h - this.hKhungAuto - 5;
            for (int i = 0; i < menuItem.size(); i++) {
                mCommand cmd = (mCommand) menuItem.elementAt(i);
                if (cmd != null) {
                    cmd.setindexImage(2);
                    cmd.setPos(this.xKhungAuto + 7 + i * 65, this.yKhungAuto + 11);
                }
            }
        }
        if (type == 4) {
            this.wKhungAuto = 200;
            if (this.wKhungAuto > GameCanvas.w)
                this.wKhungAuto = GameCanvas.w;
        }
        this.cmdClose = new mCommand("Đóng", this, 200);
        int xcmd = this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2;
        this.indexSelect = -1;
        if (type == 1) {
            xcmd = this.xKhungAuto;
            this.xpk = new int[5];
            for (int i = 0; i < this.xpk.length; i++)
                this.xpk[i] = this.xKhungAuto + 18 + 33 * i;
            this.cmdpk = new mCommand(T.thaoco, this, 1);
            this.cmdpk.setXY(this.xKhungAuto + this.wKhungAuto / 2 + 5, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.mainChar.getTypePK() - 1;
        }
        this.cmdClose.setXY(xcmd, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        if (type == 2)
            this.sizeH = 50;
        this.isShow = true;
        if (type == 5)
            this.cmdClose = null;
    }

    public void startArtParty(mVector menuItem, int type, String tile, String mastername) {
        this.mScroll.moveTo(0);
        this.sizeH = 40;
        this.masterName = mastername;
        this.type = (byte) type;
        this.menuItem = menuItem;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("Đóng", this, 200);
        this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2, this.yKhungAuto + this.hKhungAuto + 2);
        this.menucmdAction.removeAllElements();
        this.menucmdAction.addElement(new mCommand("Rời đội", this, 2));
        if (GameScr.mainChar.getName().equals(mastername)) {
            this.menucmdAction.addElement(new mCommand("Trục xuất", this, 1));
            this.menucmdAction.addElement(new mCommand("Giải tán", this, 3));
        }

        this.menucmdAction.addElement(new mCommand("Chát nhóm", this, 4));
        this.tile = tile;
        this.indexSelect = -1;
        this.isShow = true;
    }

    public void startArt(mVector listChar, int type, String tile, String[] cmdAction) {
        this.mScroll.moveTo(0);
        this.typeInfo = (byte) type;
        this.type = 2;
        this.menuItem = listChar;
        this.menucmdAction.removeAllElements();
        if (cmdAction != null) {
            for (int i = 0; i < cmdAction.length; ++i) {
                this.menucmdAction.addElement(new mCommand(cmdAction[i], this, i));
            }
        }

        this.sizeH = 40;
        this.wKhungAuto = 140;
        this.hKhungAuto = 130;
        if (this.type == 2 && this.typeInfo == 1) {
            this.wKhungAuto = 170;
        }

        this.xKhungAuto = GameCanvas.w / 2 - this.wKhungAuto / 2;
        this.yKhungAuto = GameCanvas.h / 2 - this.hKhungAuto / 2;
        this.cmdClose = new mCommand("Đóng", this, 200);
        this.cmdClose.setXY(this.xKhungAuto + this.wKhungAuto / 2 - mCommand.wButtonCmd / 2, this.yKhungAuto + this.hKhungAuto + 2);
        this.tile = tile;
        this.indexSelect = -1;
        if (this.type == 2 && this.typeInfo == 0) {
            this.sizeH = 40;
        }

        if (this.type == 2 && this.typeInfo == 1) {
            this.cmdchange = new mCommand(T.doi, this, 2);
            if (cmdAction != null && cmdAction.length > 0) {
                this.cmdchange.caption = cmdAction[0];
            }

            this.cmdchange.setXY(this.xKhungAuto + this.wKhungAuto - mCommand.wButtonCmd, this.yKhungAuto + this.hKhungAuto + 2);
            this.indexSelect = GameScr.arena - 1;
            this.mScroll.moveTo(this.indexSelect * this.sizeH);
            this.cmdClose.x = this.xKhungAuto;
        }

        if (this.typeInfo == 1) {
            this.sizeH = 35;
        }

        this.isShow = true;
    }

    public static Menu2 gI() {
        return me == null ? (me = new Menu2()) : me;
    }

    public void update() {
        if (this.isShow) {
            if (GameCanvas.isKeyPressed(12)) {
                if (this.cmdClose != null) {
                    this.cmdClose.performAction();
                }

            } else {
                mCommand cmdf;
                if (GameCanvas.isKeyPressed(5)) {
                    if (this.type == 0) {
                        cmdf = (mCommand) this.menuItem.elementAt(this.indexSelect);
                        if (cmdf != null) {
                            cmdf.performAction();
                        }
                    }

                    if (this.cmdchange != null) {
                        this.cmdchange.performAction();
                    }

                    if (this.cmdClose != null) {
                        this.cmdClose.performAction();
                    }

                } else {
                    if (this.cmdClose != null && GameCanvas.isPointerHoldIn(this.cmdClose.x, this.cmdClose.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && !GameCanvas.menu.showMenu) {
                        if (GameCanvas.isPointerDown[0]) {
                            this.cmdClose.isFocus = true;
                        }

                        if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                            this.cmdClose.performAction();
                            GameCanvas.isPointerJustRelease[0] = false;
                            GameCanvas.isPointerClick[0] = false;
                            return;
                        }
                    } else if (this.cmdClose != null) {
                        this.cmdClose.isFocus = false;
                    }

                    int i;
                    if (this.type == 1) {
                        for (i = 0; i < this.xpk.length; ++i) {
                            if (GameCanvas.isPointer(this.xpk[i] - 15, this.yKhungAuto + this.hKhungAuto / 2 - 15, 30, 30, 0) && this.indexSelect != i) {
                                this.indexSelect = i;
                            }
                        }

                        if (GameCanvas.isPointerHoldIn(this.cmdpk.x, this.cmdpk.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0)) {
                            if (GameCanvas.isPointerDown[0]) {
                                this.cmdpk.isFocus = true;
                            }

                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                                this.cmdpk.performAction();
                                GameCanvas.isPointerJustRelease[0] = false;
                                GameCanvas.isPointerClick[0] = false;
                            }
                        } else {
                            this.cmdpk.isFocus = false;
                        }

                        if (GameCanvas.isKeyPressed(4)) {
                            --this.indexSelect;
                            if (this.indexSelect < 0) {
                                this.indexSelect = 4;
                            }
                        }

                        if (GameCanvas.isKeyPressed(6)) {
                            ++this.indexSelect;
                            if (this.indexSelect > 4) {
                                this.indexSelect = 0;
                            }
                        }

                        if (GameCanvas.isKeyPressed(13)) {
                            this.cmdpk.performAction();
                        }

                        if (GameCanvas.isKeyPressed(12)) {
                            this.cmdClose.performAction();
                        }

                        if (GameCanvas.isKeyPressed(5)) {
                            this.cmdClose.performAction();
                        }

                        if (this.cmdClose != null && GameCanvas.isPointerHoldIn(this.cmdClose.x, this.cmdClose.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && !GameCanvas.menu.showMenu) {
                            if (GameCanvas.isPointerDown[0]) {
                                this.cmdClose.isFocus = true;
                            }

                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                                this.cmdClose.performAction();
                                GameCanvas.isPointerJustRelease[0] = false;
                                GameCanvas.isPointerClick[0] = false;
                            }
                        } else if (this.cmdClose != null) {
                            this.cmdClose.isFocus = false;
                        }

                    } else {
                        if (this.type != 4 && this.type != 5) {
                            ScrollResult var1 = this.mScroll.updateKey();
                        }

                        if (this.cmdchange != null) {
                            if (GameCanvas.isPointerHoldIn(this.cmdchange.x, this.cmdchange.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0)) {
                                if (GameCanvas.isPointerDown[0]) {
                                    this.cmdchange.isFocus = true;
                                }

                                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                                    this.cmdchange.performAction();
                                    GameCanvas.isPointerJustRelease[0] = false;
                                    GameCanvas.isPointerClick[0] = false;
                                }
                            } else {
                                this.cmdchange.isFocus = false;
                            }
                        }

                        if (this.cmdClose != null && GameCanvas.isPointerHoldIn(this.cmdClose.x, this.cmdClose.y, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && !GameCanvas.menu.showMenu) {
                            if (GameCanvas.isPointerDown[0]) {
                                this.cmdClose.isFocus = true;
                            }

                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                                this.cmdClose.performAction();
                                GameCanvas.isPointerJustRelease[0] = false;
                                GameCanvas.isPointerClick[0] = false;
                            }
                        } else if (this.cmdClose != null) {
                            this.cmdClose.isFocus = false;
                        }

                        if (GameCanvas.isPointerClick[0] && this.mScroll.selectedItem != -1 && !this.mScroll.isDownWhenRunning && this.type != 1 && this.type != 5 && GameCanvas.isPointer(this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 0)) {
                            if (this.indexSelect != this.mScroll.selectedItem) {
                                this.indexSelect = this.mScroll.selectedItem;
                                return;
                            }

                            if (this.type == 0) {
                                cmdf = (mCommand) this.menuItem.elementAt(this.indexSelect);
                                if (cmdf != null) {
                                    cmdf.performAction();
                                }
                            } else if (this.type == 2) {
                                if (this.typeInfo == 0) {
                                    if (this.menucmdAction.size() == 1) {
                                        cmdf = (mCommand) this.menucmdAction.elementAt(0);
                                        if (cmdf != null) {
                                            cmdf.performAction();
                                        }
                                    } else {
                                        GameCanvas.menu.startAt(this.menucmdAction, 2);
                                    }
                                } else if (this.typeInfo == 1) {
                                    this.cmdchange.performAction();
                                }
                            } else if (this.type == 3) {
                                if (this.menucmdAction.size() == 1) {
                                    cmdf = (mCommand) this.menucmdAction.elementAt(0);
                                    if (cmdf != null) {
                                        cmdf.performAction();
                                    }
                                } else {
                                    GameCanvas.menu.startAt(this.menucmdAction, 2);
                                }
                            }
                        }

                        if (this.type == 4) {
                            if (GameCanvas.isKeyPressed(2)) {
                                --this.indexSelect;
                                if (this.indexSelect < 0) {
                                    this.indexSelect = this.nitem - 1;
                                }

                                this.mScroll.moveTo(this.indexSelect * 15);
                            }

                            if (GameCanvas.isKeyPressed(8)) {
                                ++this.indexSelect;
                                if (this.indexSelect > this.nitem - 1) {
                                    this.indexSelect = 0;
                                }

                                this.mScroll.moveTo(this.indexSelect * 15);
                            }

                            if (GameCanvas.isKeyPressed(5)) {
                                this.cmdClose.performAction();
                            }

                            this.mScroll.updateKey();
                            this.mScroll.updatecm();
                            if (GameCanvas.isKeyPressed(13)) {
                                this.cmdClose.performAction();
                            }
                        }

                        if (this.type != 1 && this.type != 4 && this.type != 5) {
                            if (GameCanvas.isKeyPressed(2)) {
                                --this.indexSelect;
                                if (this.indexSelect < 0) {
                                    this.indexSelect = this.menuItem.size() - 1;
                                }

                                this.mScroll.moveTo(this.indexSelect * this.sizeH);
                            }

                            if (GameCanvas.isKeyPressed(8)) {
                                ++this.indexSelect;
                                if (this.indexSelect > this.menuItem.size() - 1) {
                                    this.indexSelect = 0;
                                }

                                this.mScroll.moveTo(this.indexSelect * this.sizeH);
                            }

                            if (GameCanvas.isKeyPressed(5)) {
                                if (this.type == 0) {
                                    cmdf = (mCommand) this.menuItem.elementAt(this.indexSelect);
                                    if (cmdf != null) {
                                        cmdf.performAction();
                                    }
                                } else if (this.type == 2) {
                                    if (this.menucmdAction.size() == 1) {
                                        cmdf = (mCommand) this.menucmdAction.elementAt(0);
                                        if (cmdf != null) {
                                            cmdf.performAction();
                                        }
                                    } else {
                                        GameCanvas.menu.startAt(this.menucmdAction, 2);
                                    }
                                } else if (this.type == 3) {
                                    if (this.menucmdAction.size() == 1) {
                                        cmdf = (mCommand) this.menucmdAction.elementAt(0);
                                        if (cmdf != null) {
                                            cmdf.performAction();
                                        }
                                    } else {
                                        GameCanvas.menu.startAt(this.menucmdAction, 2);
                                    }
                                }
                            }

                            this.mScroll.updatecm();
                            if (GameCanvas.isKeyPressed(13)) {
                                this.cmdClose.performAction();
                            }
                        }

                        if (this.type == 5) {
                            if (GameCanvas.gameScr.chatMode || GameCanvas.gameScr.chatWorld || GameCanvas.currentScreen == MainMenu.gI() || GameCanvas.currentDialog != null) {
                                return;
                            }

                            mCommand cmd;
                            if (GameCanvas.isTouch) {
                                for (i = 0; i < this.menuItem.size(); ++i) {
                                    cmd = (mCommand) this.menuItem.elementAt(i);
                                    if (cmd != null) {
                                        if (GameCanvas.isPointerHoldIn(cmd.x, cmd.y, 60, 59, 0)) {
                                            if (GameCanvas.isPointerDown[0]) {
                                                cmd.isFocus = true;
                                            }

                                            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                                                this.indexSelect = i;
                                                cmd.performAction();
                                                GameCanvas.isPointerJustRelease[0] = false;
                                                GameCanvas.isPointerClick[0] = false;
                                                this.isShow = false;
                                            }
                                        } else {
                                            cmd.isFocus = false;
                                        }
                                    }
                                }
                            } else {
                                if (GameCanvas.isKeyPressed(4)) {
                                    --this.indexSelect;
                                    if (this.indexSelect < 0) {
                                        this.indexSelect = this.menuItem.size() - 1;
                                    }

                                    for (i = 0; i < this.menuItem.size(); ++i) {
                                        cmd = (mCommand) this.menuItem.elementAt(i);
                                        if (cmd != null) {
                                            cmd.isFocus = false;
                                        }
                                    }

                                    cmdf = (mCommand) this.menuItem.elementAt(this.indexSelect);
                                    if (cmdf != null) {
                                        cmdf.isFocus = true;
                                    }
                                }

                                if (GameCanvas.isKeyPressed(6)) {
                                    ++this.indexSelect;
                                    if (this.indexSelect > this.menuItem.size() - 1) {
                                        this.indexSelect = 0;
                                    }

                                    for (i = 0; i < this.menuItem.size(); ++i) {
                                        cmd = (mCommand) this.menuItem.elementAt(i);
                                        if (cmd != null) {
                                            cmd.isFocus = false;
                                        }
                                    }

                                    cmdf = (mCommand) this.menuItem.elementAt(this.indexSelect);
                                    if (cmdf != null) {
                                        cmdf.isFocus = true;
                                    }
                                }

                                if (GameCanvas.isKeyPressed(5)) {
                                    cmdf = (mCommand) this.menuItem.elementAt(this.indexSelect);
                                    if (cmdf != null) {
                                        cmdf.performAction();
                                        this.isShow = false;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public void paint(mGraphics g) {
        if (this.isShow) {
            if (this.type != 5 || !GameCanvas.gameScr.chatMode && !GameCanvas.gameScr.chatWorld && GameCanvas.currentScreen != MainMenu.gI() && GameCanvas.currentDialog == null) {
                Res.paintDlgDragonFullNew(g, this.xKhungAuto, this.yKhungAuto, this.wKhungAuto, this.hKhungAuto, 60, 60, GameScr.imgBk[0], false);
                g.setColor(-9751532);
                g.fillRect(this.xKhungAuto, this.yKhungAuto - 28, this.wKhungAuto, 28, false);

                int i;
                for (i = 0; i < 3; ++i) {
                    g.setColor(Res.nColor[i]);
                    g.drawRect(this.xKhungAuto + i, this.yKhungAuto - 28 + i, this.wKhungAuto - i * 2, 28 - i * 2, false);
                }

                GameCanvas.resetTrans(g);
                if (this.type != 1 && this.type != 5) {
                    i = this.hKhungAuto - 10;
                    int hs = 0;
                    if (this.type == 4) {
                        i = this.hKhungAuto - 10 + 3 - (this.idIcon != -1 ? 20 : 0);
                        if (this.idIcon != -1) {
                            hs = 20;
                        }

                        this.mScroll.setStyle(this.nitem, 16, this.xKhungAuto + 6, this.yKhungAuto, this.wKhungAuto - 12, this.hKhungAuto - 12, true, 1);
                    } else {
                        this.mScroll.setStyle(this.menuItem.size(), this.sizeH, this.xKhungAuto + 6, this.yKhungAuto, this.wKhungAuto - 12, this.hKhungAuto - 12, true, 1);
                    }

                    g.ClipRec(this.xKhungAuto + 6, this.yKhungAuto + 5 + hs, this.wKhungAuto - 12, i);
                    this.mScroll.setClip(g, this.xKhungAuto + 6, this.yKhungAuto + 5 + hs, this.wKhungAuto - 12, i);
                    int yy = this.yKhungAuto + 10;

                    for (int cc = 0; cc < this.menuItem.size(); ++cc) {
                        label217:
                        {
                            if (cc == this.indexSelect && this.type != 4) {
                                g.setColor(-15904409);
                                if (this.type == 2 && (this.typeInfo == 1 || this.typeInfo == 0)) {
                                    if (this.typeInfo == 1) {
                                        Res.paintFocus(g, this.xKhungAuto + 8, this.yKhungAuto + 6 + cc * this.sizeH, this.wKhungAuto - 10);
                                    }

                                    if (this.typeInfo == 0) {
                                        Res.paintFocus(g, this.xKhungAuto + 8, this.yKhungAuto + 8 + cc * this.sizeH, this.wKhungAuto - 10);
                                    }
                                } else if (this.type == 0) {
                                    Res.paintFocus(g, this.xKhungAuto + 8, this.yKhungAuto + 8 + cc * this.sizeH, this.wKhungAuto - 10);
                                } else {
                                    g.fillRect(this.xKhungAuto + 8, this.yKhungAuto + 8 + cc * this.sizeH, this.wKhungAuto - 10, this.sizeH - 1 - 5, true);
                                }
                            }

                            ServerInfo sv;
                            int j;
                            switch (this.type) {
                                case 0:
                                    mCommand cmd = (mCommand) this.menuItem.elementAt(cc);
                                    if (cmd != null) {
                                        GameScr.Font3d(g, cmd.caption, GameCanvas.w / 2, this.yKhungAuto - 25 + (cc + 1) * this.sizeH + 5, 2, mFont.tahoma_7b_white);
                                    }
                                case 1:
                                default:
                                    break label217;
                                case 2:
                                    if (this.typeInfo == 0) {
                                        Char chardem = (Char) this.menuItem.elementAt(cc);
                                        if (chardem != null) {
                                            chardem.paintFriend(g, this.xKhungAuto + 30, this.yKhungAuto - 5 + (cc + 1) * this.sizeH + 30, 1);
                                            mFont.tahoma_7_white.drawString(g, chardem.getName(), this.xKhungAuto + 50, this.yKhungAuto - 60 + (cc + 1) * this.sizeH + 30, 0, true);
                                            mFont.tahoma_7_white.drawString(g, "Lv: " + chardem.getLevel(), this.xKhungAuto + 50, this.yKhungAuto - 44 + (cc + 1) * this.sizeH + 30, 0, true);
                                            g.drawRegion(GameScr.statusInfo, 0, 11 * (chardem.online == 0 ? 2 : 0), 11, 11, 0, this.xKhungAuto + this.wKhungAuto - 30, this.yKhungAuto - 45 + (cc + 1) * this.sizeH + 30, mGraphics.VCENTER | mGraphics.HCENTER, true);
                                            ImageIcon img = GameData.getImgIcon(chardem.idiConList);
                                            if (img != null && img.img != null) {
                                                g.drawImage(img.img, this.xKhungAuto + 10, this.yKhungAuto - 45 + (cc + 1) * this.sizeH + 30 + 1, 3, true);
                                            }
                                        }
                                    } else if (this.typeInfo == 1) {
                                        Khu khu = (Khu) this.menuItem.elementAt(cc);
                                        if (khu != null) {
                                            g.drawRegion(GameScr.statusInfo, 0, 11 * khu.getTrangThai(), 11, 11, 0, GameCanvas.w / 2 - mFont.tahoma_7b_white.getWidth(khu.getTitle()) / 2 - 10, this.yKhungAuto + (cc + 1) * this.sizeH - this.sizeH / 2 + 4, 3, true);
                                            GameScr.Font3d(g, khu.getTitle(), GameCanvas.w / 2, this.yKhungAuto + (cc + 1) * this.sizeH - this.sizeH / 2, 2, mFont.tahoma_7b_white);
                                        }
                                    }
                                    break label217;
                                case 3:
                                    Info_Party info = (Info_Party) this.menuItem.elementAt(cc);
                                    if (info != null) {
                                        if (!info.isMaster) {
                                            GameScr.Font3d(g, info.nameMember, GameCanvas.w / 2, this.yKhungAuto - 25 + (cc + 1) * this.sizeH + 5, 2, mFont.tahoma_7b_white);
                                        } else {
                                            mFont.tahoma_7_yellow.drawString(g, info.nameMember, GameCanvas.w / 2, this.yKhungAuto - 25 + (cc + 1) * this.sizeH + 5, 2, true);
                                        }
                                    }
                                    break label217;
                                case 4:
                                    sv = (ServerInfo) this.menuItem.elementAt(cc);
                                    if (sv == null) {
                                        break label217;
                                    }

                                    mFont.tahoma_7b_white.drawString(g, sv.tile, this.xKhungAuto + 10, yy + 10, 0, true);
                                    yy += 15;
                                    j = 0;
                            }

                            while (j < sv.info.length) {
                                int wslip = 0;
                                if (!GameCanvas.isTouch) {
                                    wslip = 20;
                                }

                                String[] arr = mFont.tahoma_7_white.splitFontArray(sv.info[j], this.wKhungAuto + wslip - 20);

                                for (int k = 0; k < arr.length; ++k) {
                                    mFont.tahoma_7_white.drawString(g, arr[k], this.xKhungAuto + 12, yy + 10, 0, true);
                                    yy += 15;
                                }

                                ++j;
                            }
                        }

                        if (this.type != 4) {
                            g.setColor(Res.nColor[1]);
                            if (cc < this.menuItem.size() - 1) {
                                g.fillRect(this.xKhungAuto + 8, this.yKhungAuto + 5 + (cc + 1) * this.sizeH, this.wKhungAuto - 8, 1, true);
                            }
                        }
                    }

                    mGraphics.resetTransAndroid(g);
                    g.restoreCanvas();
                } else if (this.type == 1) {
                    this.paintPK(g);
                } else if (this.type == 5) {
                    this.paintCharDie(g);
                }

                GameCanvas.resetTrans(g);

                for (i = 0; i < 7; ++i) {
                    g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, this.xKhungAuto + this.wKhungAuto / 2 - 42 + i * 12, this.yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
                }

                g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, this.xKhungAuto + this.wKhungAuto / 2 - 44, this.yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
                int ys = 0;
                if (!mSystem.isj2me) {
                    ys = -1;
                }

                g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, this.xKhungAuto + this.wKhungAuto / 2 + 44, this.yKhungAuto - 14 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
                FontTeam.fontTile.drawString(g, this.tile, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 20 + 1, 2, false);
                if (GameCanvas.isTouch && this.cmdClose != null) {
                    this.cmdClose.paint(g);
                    if (this.type == 2 && this.typeInfo == 1) {
                        this.cmdchange.paint(g);
                    }
                }

                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xKhungAuto, this.yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xKhungAuto, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xKhungAuto + this.wKhungAuto + 1, this.yKhungAuto - 28, mGraphics.TOP | mGraphics.RIGHT, false);
                if (this.type == 4 && this.idIcon != -1) {
                    ImageIcon img = GameData.getImgIcon(this.idIcon);
                    if (img != null && img.img != null) {
                        Item.eff_UpLv.paintUpgradeEffect(this.xKhungAuto + this.wKhungAuto / 2 + 1, this.yKhungAuto - 20 + 1 + 30 + 2, 15, 16, g, 1);
                        g.drawImage(img.img, this.xKhungAuto + this.wKhungAuto / 2, this.yKhungAuto - 20 + 1 + 30 + 2, 3, false);
                    }
                }

            }
        }
    }

    public void paintCharDie(mGraphics g) {
        for (int i = 0; i < this.menuItem.size(); ++i) {
            mCommand cmd = (mCommand) this.menuItem.elementAt(i);
            if (cmd != null) {
                cmd.paintMenu2(g);
            }
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void paintPK(mGraphics g) {
        for (int j = 0; j < this.xpk.length; ++j) {
            int indexpk = j + 1;
            if (this.indexSelect != j) {
                g.drawRegion(GameScr.imgPK, 0, 12 * indexpk * 3, 12, 12, 0, this.xpk[j], this.yKhungAuto + this.hKhungAuto / 2, 3, true);
            } else {
                g.drawRegion(GameScr.imgPK, 0, 12 * (indexpk * 3 + GameCanvas.gameTick / 3 % 3), 12, 12, 0, this.xpk[j], this.yKhungAuto + this.hKhungAuto / 2, 3, true);
            }
        }

        if (GameCanvas.isTouch) {
            this.cmdpk.paint(g);
        } else {
            if (this.cmdClose != null) {
                mFont.tahoma_7b_black.drawString(g, this.cmdClose.caption, 6, GameCanvas.h - 15 + 1, 0, false);
                FontTeam.fontTile.drawString(g, this.cmdClose.caption, 5, GameCanvas.h - 15, 0, false);
            }

            if (this.cmdpk != null) {
                mFont.tahoma_7b_black.drawString(g, this.cmdpk.caption, GameCanvas.w - 5 + 1, GameCanvas.h - 15 + 1, 1, false);
                FontTeam.fontTile.drawString(g, this.cmdpk.caption, GameCanvas.w - 5, GameCanvas.h - 15, 1, false);
            }
        }

    }

    public void perform(int idAction, Object p) {
        if (this.type == 2 && this.typeInfo == 0 && idAction != 200) {
            GameService.gI().ListInfo(this.typeInfo, this.tile, (byte) idAction, (short) this.indexSelect);
            this.isShow = false;
        } else if (this.type == 3 && idAction != 200) {
            this.performCmdParty(idAction);
            this.isShow = false;
        } else {
            Char chardem;
            switch (idAction) {
                case -1:
                    if (this.indexSelect == 0) {
                        GameService.gI().changePK((byte) -1);
                    } else {
                        GameService.gI().changePK((byte) this.indexSelect);
                    }

                    this.isShow = false;
                    break;
                case 0:
                    if (this.type == 1 && this.indexSelect != -1) {
                        GameService.gI().changePK((byte) (this.indexSelect + 1));
                    }

                    this.isShow = false;
                    break;
                case 1:
                    GameService.gI().changePK((byte) -1);
                    this.isShow = false;
                    break;
                case 2:
                    GameService.gI().ListInfo(this.typeInfo, this.tile, (byte) idAction, (short) this.indexSelect);
                    this.isShow = false;
                    break;
                case 3:
                    GameService.gI().Dynamic_Menu((short) this.idNPC, (byte) this.idMenu, (byte) this.indexSelect);
                case 4:
                case 5:
                default:
                    break;
                case 6:
                    GameService.gI().RequestInfoClan((byte) this.indexSelect, 13);
                    this.isShow = false;
                    break;
                case 100:
                    if (this.indexSelect != -1 && this.indexSelect < this.menuItem.size()) {
                        chardem = (Char) this.menuItem.elementAt(this.indexSelect);
                        GameCanvas.addChat(chardem.getName(), "", true);
                    }

                    this.isShow = false;
                    break;
                case 101:
                    if (this.indexSelect != -1 && this.indexSelect < this.menuItem.size()) {
                        mSystem.println("xem thong tin ne ");
                        chardem = (Char) this.menuItem.elementAt(this.indexSelect);
                        GameService.gI().requestSomeOneInfo(chardem.ID, (byte) 0);
                    }

                    this.isShow = false;
                    break;
                case 102:
                    if (this.indexSelect != -1 && this.indexSelect < this.menuItem.size()) {
                        chardem = (Char) this.menuItem.elementAt(this.indexSelect);
                        GameService.gI().Friend((byte) 3, chardem.getName());
                    }

                    this.isShow = false;
                    break;
                case 200:
                    if (this.type == 1 && this.indexSelect != -1) {
                        GameService.gI().changePK((byte) (this.indexSelect + 1));
                    }

                    this.isShow = false;
                    break;
                case 201:
                    String[] listProduct = mSystem.google_productIds;
                    if (this.indexSelect < 0 || this.indexSelect > listProduct.length) {
                        return;
                    }

                    mSystem.makePurchase(listProduct[this.indexSelect]);
            }

        }
    }

    public void performCmdParty(int idAction) {
        switch (idAction) {
            case 0:
            default:
                break;
            case 1:
                Info_Party info = (Info_Party) this.menuItem.elementAt(this.indexSelect);
                if (info != null) {
                    GameService.gI().doCreateParty((byte) 3, (short) this.indexSelect, (short) -1, info.nameMember);
                    mSystem.println("kich 1 thang ra khoi party " + info.nameMember + " " + this.indexSelect);
                }
                break;
            case 2:
                GameService.gI().doCreateParty((byte) 5, (short) -1, (short) -1, "");
                break;
            case 3:
                GameService.gI().doCreateParty((byte) 4, (short) -1, (short) -1, "");
                break;
            case 4:
                GameCanvas.addChat(T.nhom1, "", true);
        }

    }
}
