package code.screen;

import code.main.GameCanvas;
import code.model.ChatDetail;
import code.model.IActionListener;
import code.model.ListNew;
import code.model.MainTextChat;
import code.model.Scroll;
import code.model.T;
import code.model.mCamera;
import code.model.mCommand;
import code.screen.screen.ScreenTeam;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.TField;
import lib2.mFont;

public class MsgChat extends ScreenTeam implements IActionListener {
    int wMainTab;
    int maxTab;
    int min;
    int max;
    int xdich;
    byte maxText = 5;
    static int idSelect = 0;
    int xselect;
    public static mVector vecChatTab = new mVector();
    static String[] strName;
    public static ChatDetail curentfocus;
    public static mCommand cmdChat;
    public static mCommand cmdDelete;
    public static mCommand cmdClose;
    public static mCommand cmdMenu;
    public static mCommand cmdOkAdd;
    public static mCommand cmdCancelAdd;
    public static mCommand cmdDochat;
    boolean isLeft;
    boolean isRight;
    Scroll scroll = new Scroll();
    ListNew list;
    mCamera cam = new mCamera();
    public int wOneItem;
    public int wOne5;
    int minchat;
    int maxchat;
    int hchat;
    public boolean isShow;
    public boolean isPaintTF;
    public int wDia;
    public int hDia;
    public int xDia;
    public int yDia;
    public int sizeBack;
    public static MsgChat me;

    public static MsgChat gI() {
        return me == null ? (me = new MsgChat()) : me;
    }

    public void switchToMe(ScreenTeam lastSCR) {
        this.lastSCR = lastSCR;
        super.switchToMe(lastSCR);
    }

    public MsgChat() {
        this.wOneItem = GameCanvas.wOneItem;
        this.wOne5 = GameCanvas.wOne5;
        this.wDia = GameCanvas.w / 4 * 3;
        if (this.wDia < 160) {
            this.wDia = 160;
        } else if (this.wDia > 240) {
            this.wDia = 240;
        }

        this.hDia = GameCanvas.h - mCommand.hButtonCmd * 2 - 16;
        if (this.hDia > 240) {
            this.hDia = 240;
        }

        this.hchat = (this.hDia - 3 * this.wOneItem) / GameCanvas.hText + 5;
        this.xDia = GameCanvas.w / 2 - this.wDia / 2;
        this.yDia = GameCanvas.h / 2 - this.hDia / 2;
        this.yDia += 8;
        this.cam.setAll(0, 0, 0, 0);
        this.wMainTab = this.wOneItem * 3;
        if (this.wMainTab > 70) {
            this.wMainTab = 70;
        }

        if (this.wOneItem < 20) {
            this.maxText = 4;
        }

        this.maxTab = (this.wDia - this.wMainTab - 20) / (this.wOneItem - 1) + 1;
        this.min = idSelect - this.maxTab / 2;
        if (this.min < 0) {
            this.min = 0;
        }

        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
        }

        this.xdich = (this.wDia - (this.maxTab - 1) * (this.wOneItem - 1) - this.wMainTab) / 2 - 18;
        cmdChat = new mCommand("chat", this, 0);
        this.list = new ListNew(this.xDia, this.yDia + this.wOneItem, this.wDia, this.hDia - 3 * this.wOneItem, 0, 0, 0);
        this.scroll.setInfo(this.xDia + this.wDia - 6, this.yDia + this.wOneItem + 10, this.hDia - this.wOneItem - TField.getHeight() - 25, ScreenTeam.color[9]);
        cmdMenu = new mCommand(T.close, this, 1);
        cmdClose = new mCommand(T.close, this, 1);
        cmdClose.setXY(this.xDia + this.wDia / 2 + 15, this.yDia + this.hDia - 8);
        this.sizeBack = mFont.tahoma_7b_white.getWidth(T.trochuyen);
        cmdDochat = new mCommand(T.chat, this, 4);
        cmdDochat.setXY(this.xDia + this.wDia / 2 - mCommand.wButtonCmd - 15, this.yDia + this.hDia - 8);
        this.hDia -= 16;
        this.init();
    }

    public void setCaptionCmd() {
    }

    public void init() {
        if (vecChatTab.size() != 0) {
            if (curentfocus == null && vecChatTab.size() > 0) {
                if (idSelect < 0 || idSelect >= vecChatTab.size()) {
                    idSelect = 0;
                }

                curentfocus = (ChatDetail) vecChatTab.elementAt(idSelect);
                this.updateSelect();
            }

            if (curentfocus != null) {
                this.updateSelect();
            }

            this.left = cmdMenu;
        }

    }

    public void backTab() {
        idSelect = 0;
        this.cam.setAll(0, 0, 0, 0);
        this.list = new ListNew(this.xDia, this.yDia + this.wOneItem, this.wMainTab, this.hDia - 3 * this.wOneItem, 0, 0, 0);
    }

    public void paint(mGraphics g) {
        if (this.lastSCR != null)
            this.lastSCR.paint(g);
        GameCanvas.resetTrans(g);
        if (mSystem.isPC)
            g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
        paintDialogNew(g, this.xDia - 10, this.yDia - GameCanvas.hCommand - 6, this.wDia + 20,
                this.hDia + GameCanvas.hCommand + 12, 0);
        int xp = this.xDia + this.xdich, yp = this.yDia + this.wOne5;
        if (vecChatTab.size() > 0 && curentfocus != null) {
            if (this.min > 0)
                g.drawRegion(ScreenTeam.imgTab[13], 0, (
                                this.isLeft && GameCanvas.gameTick % 6 < 3) ? 16 : 0, 13,
                        8, 6, xp - 6, yp + this.wOne5 * 2, mGraphics.VCENTER |
                                mGraphics.HCENTER, true);
            g.setColor(ScreenTeam.color[7]);
            int i;
            for (i = this.min; i < idSelect; i++) {
                ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
                paintRectNew_(g, xp, yp, this.wOneItem, this.wOneItem, chat.isNew,
                        chat.timeNew);
                mFont.tahoma_7b_white.drawString(g, strName[i], xp + this.wOneItem /
                        2 + 2, yp + this.wOneItem / 2 - 7, 2, true);
                xp += this.wOneItem + 5;
            }
            this.xselect = xp;
            xp += this.wMainTab - 1;
            for (i = this.max - 1; i >= idSelect + 1; i--) {
                ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
                paintRectNew(g, xp + (i - idSelect - 1) * (this.wOneItem + 4), yp, this.wOneItem, this.wOneItem, chat.isNew,
                        chat.timeNew);
                mFont.tahoma_7b_white.drawString(g, strName[i], xp + this.wOneItem /
                        2, yp + this.wOneItem / 2 - 7, 2, true);
            }
            if (this.max < vecChatTab.size())
                g.drawRegion(ScreenTeam.imgTab[13], 0, (
                                this.isRight && GameCanvas.gameTick % 6 < 3) ? 24 : 8,
                        13, 8, 6, xp + 7, yp + this.wOne5 * 2, mGraphics.VCENTER |
                                mGraphics.HCENTER, true);
            paintTabFocus(g, this.xselect, this.yDia - GameCanvas.hCommand - 6 +
                    13 + GameCanvas.hCommand - 20, this.wMainTab);
            String strname = "";
            if (mFont.tahoma_7b_white.getWidth(curentfocus.name) < this.wMainTab - 4) {
                strname = curentfocus.name;
            } else {
                strname = String.valueOf(curentfocus.name.substring(0, (this.maxText - 1) * 2)) +
                        "..";
            }
            mFont f = mFont.tahoma_7b_white;
            f.drawString(g, strname, this.xselect + this.wMainTab / 2, yp + this.wOneItem / 2 -
                    7, 2, true);
            yp += this.wOneItem;
            int ycclip = (curentfocus.typeChat == 0) ? 17 : 0;
            if (GameCanvas.isTouch)
                ycclip = 3;
            g.setClip(this.xDia, yp, this.wDia, this.hDia - this.wOneItem - 7 - ycclip);
            g.ClipRec(this.xDia, yp, this.wDia, this.hDia - this.wOneItem - 7 - ycclip);
            g.translate(0, -this.cam.yCam);
            this.minchat = this.cam.yCam / GameCanvas.hText - 2;
            if (this.minchat < 0)
                this.minchat = 0;
            this.maxchat = this.minchat + this.hchat;
            for (int j = this.minchat; j <= this.maxchat; j++) {
                if (j < curentfocus.vecDetail.size() && j >= 0) {
                    MainTextChat t = (MainTextChat) curentfocus.vecDetail
                            .elementAt(j);
                    if (t.color == 1) {
                        mFont.tahoma_7_green.drawString(g, t.text,
                                this.xDia + this.wOne5 + this.wDia, yp + 2 + j * GameCanvas.hText, 1, true);
                    } else {
                        mFont.tahoma_7_white.drawString(g, t.text,
                                this.xDia + this.wOne5, yp + 2 + j * GameCanvas.hText, 0, true);
                    }
                }
            }
            mGraphics.resetTransAndroid(g);
            g.restoreCanvas();
            g.endClip();
            GameCanvas.resetTrans(g);
            if (this.cam.yLimit > 0)
                this.scroll.paint(g);
            if (curentfocus.typeChat == 0)
                if (!GameCanvas.isTouch) {
                    curentfocus.tfchat.paint(g);
                } else if ((this.isPaintTF && mSystem.isAndroid) || mSystem.isPC) {
                    curentfocus.tfchat.paint(g);
                }
        }
        Res.paintBackTile(g, this.xDia + this.wDia / 2, this.yDia - GameCanvas.hCommand + 9, this.sizeBack, mFont.tahoma_7b_white, T.trochuyen);
        if (cmdClose != null && GameCanvas.isTouch)
            cmdClose.paint(g);
        cmdDochat.paint(g);
        if (!GameCanvas.isTouch)
            super.paint(g);
    }

    public void updatekey() {
        if (vecChatTab.size() > 0 && curentfocus != null) {
            int t = idSelect;
            if (GameCanvas.keyHold[4]) {
                if (idSelect != 0) {
                    --idSelect;
                }

                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyHold[6]) {
                ++idSelect;
                GameCanvas.clearKeyHold(6);
            }

            idSelect = this.resetSelect(idSelect, vecChatTab.size() - 1, true);
            mCamera var10000;
            if (GameCanvas.keyHold[2]) {
                var10000 = this.cam;
                var10000.yTo -= GameCanvas.hText;
                if (this.cam.yTo < 0) {
                    this.cam.yTo = 0;
                }

                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyHold[8]) {
                var10000 = this.cam;
                var10000.yTo += GameCanvas.hText;
                if (this.cam.yTo > this.cam.yLimit) {
                    this.cam.yTo = this.cam.yLimit;
                }

                GameCanvas.clearKeyHold(8);
            }

            if (t != idSelect) {
                this.updateSelect();
            }
        }

        if (GameCanvas.isKeyPressed(5) && curentfocus != null && curentfocus.tfchat.getText() != null) {
            cmdChat.performAction();
        }

    }

    public void update() {
        if (this.lastSCR != null) {
            this.lastSCR.update();
        }

        if (GameCanvas.isTouch) {
            this.list.update_Pos_UP_DOWN();
            this.cam.yCam = this.list.cmx;
        }

        if (mSystem.isPC && curentfocus != null && curentfocus.tfchat != null) {
            curentfocus.tfchat.update();
        }

        if (cmdClose != null && GameCanvas.isTouch && this.getCmdPointerLast(cmdClose)) {
            GameCanvas.isPointerJustRelease[0] = false;
            cmdClose.performAction();
        }

        if (cmdDochat != null && GameCanvas.isTouch && this.getCmdPointerLast(cmdDochat)) {
            GameCanvas.isPointerJustRelease[0] = false;
            cmdDochat.performAction();
        }

        if (!GameCanvas.isTouch && GameCanvas.isKeyPressed(13) && cmdClose != null) {
            cmdClose.performAction();
        }

        this.isLeft = false;
        this.isRight = false;
        if (vecChatTab.size() > 0 && curentfocus != null) {
            for (int i = 0; i < vecChatTab.size(); ++i) {
                if (i < this.min || i >= this.max) {
                    ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
                    if (chat.isNew) {
                        if (i < this.min) {
                            this.isLeft = true;
                        } else {
                            this.isRight = true;
                        }
                    }
                }
            }

            if (vecChatTab.size() == 0) {
                idSelect = 0;
            }

            if (this.cam.yLimit > 0) {
                this.scroll.setYScrool(this.cam.yCam, this.cam.yLimit);
            }

            if (GameCanvas.isTouch) {
                this.list.moveCamera();
            } else {
                this.cam.UpdateCamera();
                this.cam.UpdateCamera();
            }

            if (curentfocus.typeChat == 0) {
                if (!GameCanvas.isTouch) {
                    curentfocus.tfchat.update();
                }

                if (GameCanvas.isTouch && this.isPaintTF) {
                    curentfocus.tfchat.update();
                }
            }
        } else if (curentfocus == null && vecChatTab.size() > 0) {
            idSelect = 0;
            curentfocus = (ChatDetail) vecChatTab.elementAt(idSelect);
            this.updateSelect();
        }

        this.updatekey();
        this.updatePointer();
        super.update();
    }

    public boolean keyPress(int keyCode) {
        super.keyPress(keyCode);
        if (vecChatTab.size() > 0 && curentfocus != null && curentfocus.typeChat == 0) {
            boolean swallow = curentfocus.tfchat.keyPressed(keyCode);
            if (swallow) {
                return true;
            }
        }

        return super.keyPress(keyCode);
    }

    public static void getStrName() {
        strName = new String[vecChatTab.size()];

        for (int i = 0; i < strName.length; ++i) {
            ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
            if (chat.name.length() <= 4) {
                strName[i] = chat.name;
            } else {
                strName[i] = chat.name.substring(0, 4);
            }
        }

    }

    public void updateCameraNew(int size) {
        if (GameCanvas.isTouch) {
            this.list.cmxLim = curentfocus.limY;
            if (this.list.cmx + this.hDia / 4 > this.list.cmxLim && this.list.cmxLim > 0) {
                ListNew var10000 = this.list;
                var10000.cmtoX += GameCanvas.hText * size;
            }
        } else {
            this.cam.yLimit = curentfocus.limY;
            if (this.cam.yCam + this.hDia / 4 > this.cam.yLimit) {
                mCamera var2 = this.cam;
                var2.yTo += GameCanvas.hText * size;
            }
        }

    }

    public void updateSelect() {
        curentfocus = (ChatDetail) vecChatTab.elementAt(idSelect);
        if (this.left == null) {
            this.left = cmdMenu;
        }

        if (curentfocus.typeChat == 0) {
            curentfocus.tfchat.setText("");
            if (!GameCanvas.isTouch) {
                curentfocus.tfchat.setFocus(true);
                this.center = cmdChat;
                this.right = curentfocus.tfchat.setCmdClear();
            }
        } else if (curentfocus.typeChat == 2) {
            this.center = cmdOkAdd;
            this.right = cmdCancelAdd;
        } else {
            this.center = null;
            this.right = null;
        }

        if (curentfocus.timeNew > 0) {
            curentfocus.timeNew = -1;
            curentfocus.isNew = false;
        }

        if (curentfocus.limY > 0) {
            int yt = curentfocus.limY - this.hDia / 4;
            if (yt < 0) {
                yt = 0;
            }

            if (GameCanvas.isTouch) {
                this.list.cmxLim = curentfocus.limY;
                this.list.cmtoX = curentfocus.limY;
            } else {
                this.cam.setAll(0, curentfocus.limY, 0, yt);
                this.cam.yTo = curentfocus.limY;
            }
        } else if (!GameCanvas.isTouch) {
            this.cam.setAll(0, 0, 0, 0);
        } else {
            this.list.cmxLim = 0;
            this.list.cmtoX = 0;
        }

        this.min = idSelect - this.maxTab / 2;
        if (this.min < 0) {
            this.min = 0;
        }

        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
            this.min = this.max - this.maxTab;
            if (this.min < 0) {
                this.min = 0;
            }
        }

        getStrName();
    }

    public void updatePointer() {
        if (vecChatTab.size() > 0 && curentfocus != null) {
            if (curentfocus.typeChat == 0) {
                curentfocus.tfchat.updateKey();
            }

            if (GameCanvas.isPointerClick[0]) {
                int xp = this.xDia;
                int yp = this.yDia;
                int xDiaTab = xp + this.xdich;
                int xendTab = xDiaTab + (this.maxTab - 1) * (this.wOneItem - 1) + this.wMainTab;
                int t = idSelect;
                if (GameCanvas.isPointer(this.xDia, yp, this.wDia, this.wOneItem, 0)) {
                    if (GameCanvas.px[0] < this.xselect && GameCanvas.px[0] > xDiaTab) {
                        t = idSelect + (GameCanvas.px[0] - this.xselect) / (this.wOneItem - 1) - 1;
                    } else if (GameCanvas.px[0] > this.xselect + this.wMainTab && GameCanvas.px[0] < xendTab) {
                        t = idSelect + (GameCanvas.px[0] - this.xselect - this.wMainTab) / (this.wOneItem - 1) + 1;
                    } else if (GameCanvas.px[0] < xDiaTab) {
                        t = idSelect - this.maxTab;
                    } else if (GameCanvas.px[0] > xendTab) {
                        t = idSelect + this.maxTab;
                    }
                }

                t = this.resetSelect(t, vecChatTab.size() - 1, false);
                if (t != idSelect) {
                    idSelect = t;
                    this.updateSelect();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
        }

    }

    public int resetSelect(int select, int max, boolean isreset) {
        if (select < 0) {
            if (isreset) {
                select = max;
            } else {
                select = 0;
            }
        } else if (select > max) {
            if (isreset) {
                select = 0;
            } else {
                select = max;
            }
        }

        return select;
    }

    public void addNewChat(String name, String FristContent, String content, byte type, boolean isFocus) {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
            if (chat.name.compareTo(name) == 0) {
                if (isFocus) {
                    idSelect = i;
                }

                if (content.length() > 0) {
                    chat.addString(FristContent + content, name);
                }

                return;
            }
        }

        ChatDetail chat = new ChatDetail(name, type);
        if (type == 0) {
            chat.addString(T.beginChat + name, name);
        }

        if (content.length() > 0) {
            chat.addString(FristContent + content, name);
        }

        vecChatTab.addElement(chat);
        this.set_text_min_max();
        if (vecChatTab.size() > 1) {
            this.left.caption = T.tuychon;
        }

        if (isFocus) {
            idSelect = vecChatTab.size() - 1;
        }

    }

    public void set_text_min_max() {
        getStrName();
        this.min = idSelect - this.maxTab / 2;
        if (this.min < 0) {
            this.min = 0;
        }

        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
            this.min = this.max - this.maxTab;
            if (this.min < 0) {
                this.min = 0;
            }
        }

    }

    public void paintRectNew(mGraphics g, int x, int y, int w, int h, boolean isNew, int time) {
        g.drawImage(imgTab[17], x, y - 15, 0, true);
        if (isNew && GameCanvas.gameTick % 10 == 0) {
            g.drawImage(imgTab[10], x, y - 15, 0, true);
        }

    }

    public void paintRectNew_(mGraphics g, int x, int y, int w, int h, boolean isNew, int time) {
        g.drawRegion(imgTab[17], 0, 0, 32, 32, 2, x, y - 15, 0, true);
        if (isNew && GameCanvas.gameTick % 10 == 0) {
            g.drawRegion(imgTab[10], 0, 0, 32, 32, 2, x, y - 15, 0, true);
        }

    }

    public void paintTabFocus(mGraphics g, int x, int y, int wMainTab) {
        for (int i = 0; i <= wMainTab / 32; ++i) {
            if (i == 0) {
                g.drawImage(ScreenTeam.imgTab[9], x, y, 0, true);
            } else if (i == wMainTab / 32) {
                g.drawRegion(ScreenTeam.imgTab[9], 0, 0, 32, 32, 2, x + wMainTab - 32, y, 0, true);
            } else {
                g.drawImage(ScreenTeam.imgTab[16], x + i * 32, y, 0, true);
            }
        }

    }

    public boolean setAddFriend(String name) {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
            if (chat.typeChat == 2 && chat.friend.compareTo(name) == 0) {
                return false;
            }
        }

        return true;
    }

    public static void setFocus(int index) {
        if (vecChatTab != null && index >= 0 && index < vecChatTab.size()) {
            idSelect = index;
        }

    }

    public static void setIndexFocus(String name) {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
            if (chat.name.compareTo(name) == 0) {
                idSelect = i;
                break;
            }
        }

    }

    public void checkRemoveText() {
        for (int i = 0; i < vecChatTab.size(); ++i) {
            ChatDetail chat = (ChatDetail) vecChatTab.elementAt(i);
            int size = chat.vecDetail.size();
            if (size > 80) {
                for (int j = 0; j < size - 80; ++j) {
                    chat.vecDetail.removeElementAt(i);
                }

                chat.setLim();
            }
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0:
                curentfocus.addStartChat("");
                this.isPaintTF = false;
                break;
            case 1:
                if (vecChatTab.size() > 1) {
                    mVector mlist = new mVector();
                    mCommand cmd1 = new mCommand(T.dongtap, this, 2);
                    mCommand cmd2 = new mCommand(T.dongtrochyen, this, 3);
                    mlist.addElement(cmd1);
                    mlist.addElement(cmd2);
                    GameCanvas.menu.startAt(mlist, 0);
                } else {
                    this.isShow = false;
                    this.lastSCR.switchToMe();
                    this.lastSCR = null;
                }
                break;
            case 2:
                if (idSelect >= 0 && idSelect < vecChatTab.size()) {
                    ChatDetail chat = (ChatDetail) vecChatTab.elementAt(idSelect);
                    if (chat != null) {
                        vecChatTab.removeElementAt(idSelect);
                        if (idSelect > 0) {
                            --idSelect;
                        }

                        if (vecChatTab.size() == 0) {
                            curentfocus = null;
                            this.left = null;
                            this.center = null;
                            this.right = cmdClose;
                        } else {
                            this.updateSelect();
                        }
                    }

                    if (vecChatTab.size() == 1) {
                        this.left.caption = T.close;
                    }
                    break;
                }

                return;
            case 3:
                this.isShow = false;
                this.lastSCR.switchToMe();
                this.lastSCR = null;
                break;
            case 4:
                if (!mSystem.isPC) {
                    curentfocus.tfchat.doChangeToTextBox();
                    this.isPaintTF = true;
                } else {
                    curentfocus.addStartChat("");
                    this.isPaintTF = false;
                }
        }

    }
}
