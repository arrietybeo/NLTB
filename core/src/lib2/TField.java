package lib2;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.IActionListener;
import code.model.mCommand;
import code.screen.MsgChat;
import code.screen.screen.FontTeam;
import code.screen.screen.ObjGame;
import code.screen.screen.ScreenTeam;

import javax.microedition.lcdui.Image;

import lib.mGraphics;

public class TField extends ObjGame implements IActionListener {
    public boolean isFocus;
    public boolean lockArrow = false;
    public boolean paintFocus = true;
    public static int typeXpeed = 2;
    private static final int[] MAX_TIME_TO_CONFIRM_KEY = new int[]{18, 14, 11, 9, 6, 4, 2};
    private static int CARET_HEIGHT = 0;
    private static final int CARET_WIDTH = 1;
    private static final int CARET_SHOWING_TIME = 5;
    private static final int TEXT_GAP_X = 4;
    private static final int MAX_SHOW_CARET_COUNER = 10;
    public static final int INPUT_TYPE_ANY = 0;
    public static final int INPUT_TYPE_NUMERIC = 1;
    public static final int INPUT_TYPE_PASSWORD = 2;
    public static final int INPUT_ALPHA_NUMBER_ONLY = 3;
    private static String[] print = new String[]{" 0", ".,@?!_1\"/$-():*+<=>;%&~#%^&*{}[];'/1", "abc2áàảãạâấầẩẫậăắằẳẵặ2", "def3đéèẻẽẹêếềểễệ3", "ghi4íìỉĩị4", "jkl5", "mno6óòỏõọôốồổỗộơớờởỡợ6", "pqrs7", "tuv8úùủũụưứừửữự8", "wxyz9ýỳỷỹỵ9", "*", "#"};
    private static String[] printA = new String[]{"0", "1", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9", "0", "0"};
    private static String[] printBB = new String[]{" 0", "er1", "ty2", "ui3", "df4", "gh5", "jk6", "cv7", "bn8", "m9", "0", "0", "qw!", "as?", "zx", "op.", "l,"};
    private String text = "";
    private String passwordText = "";
    private String paintedText = "";
    private int caretPos = 0;
    private int counter = 0;
    private int maxTextLenght = 50;
    private int offsetX = 0;
    private static int lastKey = -1984;
    private int keyInActiveState = 0;
    private int indexOfActiveChar = 0;
    private int showCaretCounter = 10;
    private int inputType = 0;
    public static boolean isQwerty;
    public static int typingModeAreaWidth;
    public static int mode = 0;
    public static int timeChangeMode;
    public static final String[] modeNotify = new String[]{"abc", "Abc", "ABC", "123"};
    public static final int NOKIA = 0;
    public static final int MOTO = 1;
    public static final int ORTHER = 2;
    public static final int BB = 3;
    public static int changeModeKey = 11;
    public static final byte abc = 0;
    public static final byte Abc = 1;
    public static final byte ABC = 2;
    public static final byte number123 = 3;
    public static GameCanvas c;
    public static GameMidlet m;
    public String title = "";
    public String strnull = "";
    public boolean ischat;
    public mCommand cmdClear;
    public static Image imgEr;
    ScreenTeam parentScr;
    int holdCount;
    public static int[][] BBKEY = new int[][]{{32, 48}, {49, 69}, {50, 84}, {51, 85}, {52, 68}, {53, 71}, {54, 74}, {55, 67}, {56, 66}, {57, 77}, {42, 128}, {35, 137}, {33, 113}, {63, 97}, {64, 121, 122}, {46, 111}, {44, 108}};

    public void doChangeToTextBox() {
    }

    public static void setVendorTypeMode(int mode) {
        if (mode == 1) {
            print[0] = "0";
            print[10] = " *";
            print[11] = "#";
            changeModeKey = 35;
        } else if (mode == 0) {
            print[0] = " 0";
            print[10] = "*";
            print[11] = "#";
            changeModeKey = 35;
        } else if (mode == 2) {
            print[0] = "0";
            print[10] = "*";
            print[11] = " #";
            changeModeKey = 42;
        }

    }

    private void init() {
        CARET_HEIGHT = FontTeam.normalFontColor.getHeight() + 1;
        this.cmdClear = new mCommand(GameCanvas.isTouch ? "" : "Xóa", this, 1);
        if (this.parentScr != null) {
            this.parentScr.right = this.cmdClear;
        }

        if (imgEr == null) {
            imgEr = GameCanvas.loadImage("/hd/er.png");
        }

    }

    public TField(ScreenTeam parentScr) {
        this.text = "";
        this.parentScr = parentScr;
        this.init();
    }

    public TField() {
        this.text = "";
        this.init();
    }

    public TField(int x, int y, int w, int h) {
        this.text = "";
        this.init();
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public TField(String text, int maxLen, int inputType) {
        this.text = text;
        this.maxTextLenght = maxLen;
        this.inputType = inputType;
        this.init();
        this.isTfield = true;
    }

    public void setStringNull(String str) {
        this.strnull = str;
    }

    public void clear() {
        if (this.caretPos > 0 && this.text.length() > 0) {
            this.text = this.text.substring(0, this.caretPos - 1) + this.text.substring(this.caretPos, this.text.length());
            --this.caretPos;
            this.setOffset();
            this.setPasswordTest();
        }

    }

    public void setOffset() {
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }

        if (this.offsetX < 0 && mFont.tahoma_7b_white.getWidth(this.paintedText) + this.offsetX < this.width - 4 - 13 - typingModeAreaWidth) {
            this.offsetX = this.width - 10 - typingModeAreaWidth - mFont.tahoma_7b_white.getWidth(this.paintedText);
        }

        if (this.offsetX + mFont.tahoma_7b_white.getWidth(this.paintedText.substring(0, this.caretPos)) <= 0) {
            this.offsetX = -mFont.tahoma_7b_white.getWidth(this.paintedText.substring(0, this.caretPos));
            this.offsetX += 40;
        } else if (this.offsetX + mFont.tahoma_7b_white.getWidth(this.paintedText.substring(0, this.caretPos)) >= this.width - 32 - typingModeAreaWidth) {
            this.offsetX = this.width - 20 - typingModeAreaWidth - mFont.tahoma_7b_white.getWidth(this.paintedText.substring(0, this.caretPos)) - 8;
        }

        if (this.offsetX > 0) {
            this.offsetX = 0;
        }

    }

    public void setOffsetPC() {
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }

        if (this.offsetX < 0 && mFont.tahoma_7b_white.getWidth(this.paintedText) + this.offsetX < this.width - 4 - 13 - typingModeAreaWidth) {
            this.offsetX = this.width - 10 - typingModeAreaWidth - mFont.tahoma_7b_white.getWidth(this.paintedText);
        }

        if (this.offsetX + mFont.tahoma_7b_white.getWidth(this.paintedText.substring(0, this.caretPos)) <= 0) {
            this.offsetX = -mFont.tahoma_7b_white.getWidth(this.paintedText.substring(0, this.caretPos));
            this.offsetX += 40;
        }

        if (this.offsetX > 0) {
            this.offsetX = 0;
        }

    }

    private void keyPressedAny(int keyCode) {
        String[] print;
        if (GameCanvas.isBB) {
            print = printBB;
        } else if (this.inputType != 2 && this.inputType != 3) {
            print = TField.print;
        } else {
            print = printA;
        }

        if (GameCanvas.isBB) {
            keyCode = this.getBBKeyCode(keyCode);
            if (keyCode == -1) {
                return;
            }
        }

        char c;
        String ttext;
        if (keyCode == lastKey) {
            this.indexOfActiveChar = (this.indexOfActiveChar + 1) % print[keyCode - 48].length();
            c = print[keyCode - 48].charAt(this.indexOfActiveChar);
            if (mode == 0) {
                c = Character.toLowerCase(c);
            } else if (mode == 1) {
                c = Character.toUpperCase(c);
            } else if (mode == 2) {
                c = Character.toUpperCase(c);
            } else {
                c = print[keyCode - 48].charAt(print[keyCode - 48].length() - 1);
            }

            ttext = this.text.substring(0, this.caretPos - 1) + c;
            if (this.caretPos < this.text.length()) {
                ttext = ttext + this.text.substring(this.caretPos, this.text.length());
            }

            this.text = ttext;
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            this.setPasswordTest();
        } else if (this.text.length() < this.maxTextLenght) {
            if (mode == 1 && lastKey != -1984) {
                mode = 0;
            }

            this.indexOfActiveChar = 0;
            c = print[keyCode - 48].charAt(this.indexOfActiveChar);
            if (mode == 0) {
                c = Character.toLowerCase(c);
            } else if (mode == 1) {
                c = Character.toUpperCase(c);
            } else if (mode == 2) {
                c = Character.toUpperCase(c);
            } else {
                c = print[keyCode - 48].charAt(print[keyCode - 48].length() - 1);
            }

            ttext = this.text.substring(0, this.caretPos) + c;
            if (this.caretPos < this.text.length()) {
                ttext = ttext + this.text.substring(this.caretPos, this.text.length());
            }

            this.text = ttext;
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            ++this.caretPos;
            this.setPasswordTest();
            this.setOffset();
        }

        lastKey = keyCode;
    }

    private void keyPressedAscii(int keyCode) {
        if (this.inputType != 2 && this.inputType != 3 || keyCode >= 48 && keyCode <= 57 || keyCode >= 65 && keyCode <= 90 || keyCode >= 97 && keyCode <= 122) {
            if (this.text.length() < this.maxTextLenght) {
                String ttext = this.text.substring(0, this.caretPos) + (char) keyCode;
                if (this.caretPos < this.text.length()) {
                    ttext = ttext + this.text.substring(this.caretPos, this.text.length());
                }

                this.text = ttext;
                ++this.caretPos;
                this.setPasswordTest();
                this.setOffset();
            }

        }
    }

    public void keyHold(int keyCode) {
        ++this.holdCount;
        if (this.holdCount > 15 && !isQwerty && keyCode < print.length) {
            this.clear();
            this.keyPressedAscii(print[keyCode].charAt(print[keyCode].length() - 1));
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            this.holdCount = 0;
        }

    }

    public static void setMode() {
        ++mode;
        if (mode > 3) {
            mode = 0;
        }

        lastKey = changeModeKey;
        timeChangeMode = (int) (System.currentTimeMillis() / 1000L);
    }

    public boolean keyPressed(int keyCode) {
        if (!this.isFocused()) {
            return true;
        } else if (keyCode != -8 && keyCode != 204) {
            isQwerty = true;
            if (!GameCanvas.isBB && keyCode >= 65 && keyCode <= 122) {
                isQwerty = true;
                typingModeAreaWidth = 0;
            }

            this.holdCount = 0;
            if (keyCode >= 65 && keyCode <= 122) {
                isQwerty = true;
                typingModeAreaWidth = 0;
            }

            if (isQwerty && !GameCanvas.isBB) {
                if (keyCode == 45) {
                    if (keyCode == lastKey && this.keyInActiveState < MAX_TIME_TO_CONFIRM_KEY[typeXpeed]) {
                        this.text = this.text.substring(0, this.caretPos - 1) + '_';
                        this.paintedText = this.text;
                        this.setPasswordTest();
                        this.setOffset();
                        lastKey = -1984;
                        return false;
                    }

                    lastKey = 45;
                }

                if (keyCode >= 32) {
                    this.keyPressedAscii(keyCode);
                    return true;
                }
            }

            if (keyCode == changeModeKey) {
                ++mode;
                if (mode > 3) {
                    mode = 0;
                }

                this.keyInActiveState = 1;
                lastKey = keyCode;
                return true;
            } else {
                if (keyCode == 42) {
                    keyCode = 58;
                }

                if (keyCode == 35) {
                    keyCode = 59;
                }

                if (GameCanvas.isBB && keyCode >= 48) {
                    if (isQwerty) {
                        this.keyPressedAscii(keyCode);
                        this.keyInActiveState = 1;
                    } else if (this.inputType != 0 && this.inputType != 2 && this.inputType != 3) {
                        if (this.inputType == 1) {
                            this.keyPressedAscii(keyCode);
                            this.keyInActiveState = 1;
                        }
                    } else {
                        this.keyPressedAny(keyCode);
                    }
                } else {
                    if (keyCode >= 48 && keyCode <= 59) {
                        if (this.inputType != 0 && this.inputType != 2 && this.inputType != 3) {
                            if (this.inputType == 1) {
                                this.keyPressedAscii(keyCode);
                                this.keyInActiveState = 1;
                            }
                        } else {
                            this.keyPressedAny(keyCode);
                        }

                        return true;
                    }

                    this.indexOfActiveChar = 0;
                    lastKey = -1984;
                    if (keyCode == -3 && !this.lockArrow) {
                        if (this.caretPos > 0) {
                            --this.caretPos;
                            this.setOffset();
                            this.showCaretCounter = 10;
                            return true;
                        }
                    } else if (keyCode == -4 && !this.lockArrow) {
                        if (this.caretPos < this.text.length()) {
                            ++this.caretPos;
                            this.setOffset();
                            this.showCaretCounter = 10;
                            return true;
                        }
                    } else {
                        if (keyCode == -8) {
                            this.clear();
                            return true;
                        }

                        lastKey = keyCode;
                    }
                }

                return false;
            }
        } else {
            this.clear();
            return true;
        }
    }

    public void pointerRelease(int px, int py) {
    }

    public void paint(mGraphics g) {
        boolean isFocus = this.isFocused();
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }

        if (isFocus) {
            if (this.paintFocus) {
                g.setColor(12742927);
                g.fillRect(this.x + 1, this.y + 1, this.width - 1, this.height - 1, false);
                g.setColor(-8826880);
            }
        } else {
            g.setColor(3812871);
            g.fillRect(this.x + 1, this.y + 1, this.width - 1, this.height - 1, false);
            g.setColor(-10797305);
        }

        int yss = -1;
        int ypass = 0;
        if (this.inputType == 2) {
            ypass = 2;
        }

        g.setClip(this.x + 2, this.y + 1, this.width - typingModeAreaWidth - 4, this.height - 4);
        g.setColor(0);
        if (this.paintedText.equals("")) {
            mFont ff = mFont.tahoma_7b_orange;
            if (isFocus) {
                ff = mFont.tahoma_7b_brown;
            }

            ff.drawString(g, this.strnull, 4 + this.offsetX + this.x + 2, this.y + this.height / 2 - 6 + 1 + yss, 0, true);
        } else {
            mFont.tahoma_7b_white.drawString(g, this.paintedText, 4 + this.offsetX + this.x + 2, this.y + this.height / 2 - 6 + ypass + 1 + yss - 1, 0, true);
        }

        if (this.isFocused() && this.width > 50 && this.keyInActiveState == 0 && (this.showCaretCounter > 0 || this.counter / 5 % 6 == 0)) {
            g.setColor(-1);
            g.fillRect(7 + this.offsetX + this.x + FontTeam.normalFont[0].getWidth(this.paintedText.substring(0, this.caretPos)) - 1, this.y + this.height / 2 - 5 + 1 + yss - 1, 1, CARET_HEIGHT, false);
        }

        if (this.paintedText.endsWith("") && this.paintedText.length() > 0 && this.width > 50) {
            boolean var10000 = GameCanvas.isTouch;
        }

        GameCanvas.resetTrans(g);
        g.setColor(-10797305);
        g.drawRect(this.x + 1, this.y + 1, this.width - 3, this.height - 4, false);
    }

    public void paint(mGraphics g, boolean isClip) {
        boolean isFocus = this.isFocused();
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }

        if (isFocus) {
            if (this.paintFocus) {
                g.setColor(12742927);
                g.fillRect(this.x + 1, this.y + 1, this.width - 1, this.height - 1, false);
                g.setColor(-8826880);
            }
        } else {
            g.setColor(3812871);
            g.fillRect(this.x + 1, this.y + 1, this.width - 1, this.height - 1, false);
            g.setColor(-10797305);
        }

        int yss = -1;
        int ypass = 0;
        if (this.inputType == 2) {
            ypass = 2;
        }

        g.setClip(this.x + 2, this.y + 1, this.width - typingModeAreaWidth - 4, this.height - 4);
        g.setColor(0);
        if (this.paintedText.equals("")) {
            mFont ff = mFont.tahoma_7b_orange;
            if (isFocus) {
                ff = mFont.tahoma_7b_brown;
            }

            ff.drawString(g, this.strnull, 4 + this.offsetX + this.x + 2, this.y + this.height / 2 - 6 + 1 + yss, 0, true);
        } else {
            mFont.tahoma_7b_white.drawString(g, this.paintedText, 4 + this.offsetX + this.x + 2, this.y + this.height / 2 - 6 + ypass + 1 + yss - 1, 0, true);
        }

        if (this.isFocused() && this.width > 50 && this.keyInActiveState == 0 && (this.showCaretCounter > 0 || this.counter / 5 % 6 == 0)) {
            g.setColor(-1);
            g.fillRect(7 + this.offsetX + this.x + FontTeam.normalFont[0].getWidth(this.paintedText.substring(0, this.caretPos)) - 1, this.y + this.height / 2 - 5 + 1 + yss - 1, 1, CARET_HEIGHT, isClip);
        }

        if (this.paintedText.endsWith("") && this.paintedText.length() > 0 && this.width > 50) {
            boolean var10000 = GameCanvas.isTouch;
        }

        GameCanvas.resetTrans(g);
        g.setColor(-10797305);
        g.drawRect(this.x + 1, this.y + 1, this.width - 3, this.height - 4, isClip);
    }

    private boolean isFocused() {
        return this.isFocus;
    }

    private void setPasswordTest() {
        if (this.inputType == 2) {
            this.passwordText = "";

            for (int i = 0; i < this.text.length(); ++i) {
                this.passwordText = this.passwordText + "*";
            }

            if (this.keyInActiveState > 0 && this.caretPos > 0) {
                this.passwordText = this.passwordText.substring(0, this.caretPos - 1) + this.text.charAt(this.caretPos - 1) + this.passwordText.substring(this.caretPos, this.passwordText.length());
            }
        }

    }

    public void paintFocus(mGraphics g) {
    }

    public void updateList() {
        ++this.counter;
        if (this.keyInActiveState > 0) {
            --this.keyInActiveState;
            if (this.keyInActiveState == 0) {
                this.indexOfActiveChar = 0;
                if (mode == 1 && lastKey != changeModeKey) {
                    mode = 0;
                }

                lastKey = -1984;
                this.setPasswordTest();
            }
        }

        if (GameCanvas.isKeyPressed(5)) {
            this.okTextBox();
        }

        if (this.showCaretCounter > 0) {
            --this.showCaretCounter;
        }

        if (GameCanvas.isPointerClick[0] && (GameCanvas.currentDialog == null || GameCanvas.currentDialog == GameCanvas.inputDlg) && !GameCanvas.menu.showMenu) {
            this.setTextBox();
        }

        if (GameCanvas.isKeyPressed(5)) {
            this.okTextBox();
        }

    }

    public void update() {
        ++this.counter;
        if (this.keyInActiveState > 0) {
            --this.keyInActiveState;
            if (this.keyInActiveState == 0) {
                this.indexOfActiveChar = 0;
                if (mode == 1 && lastKey != changeModeKey) {
                    mode = 0;
                }

                lastKey = -1984;
                this.setPasswordTest();
            }
        }

        if (GameCanvas.isKeyPressed(5)) {
            this.okTextBox();
        }

        if (this.showCaretCounter > 0) {
            --this.showCaretCounter;
        }

        if (GameCanvas.isPointerClick[0] && (GameCanvas.currentDialog == null || GameCanvas.currentDialog == GameCanvas.inputDlg) && !GameCanvas.menu.showMenu) {
            this.setTextBox();
        }

    }

    public void clearAllText() {
        this.text = "";
        this.caretPos = 0;
        this.setOffset();
        this.setPasswordTest();
    }

    private void setTextBox() {
        if (GameCanvas.isPointerHoldIn(this.x, this.y, this.width, this.height, 0)) {
            this.isFocus = true;
        } else {
            this.isFocus = false;
        }

    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        if (text != null) {
            lastKey = -1984;
            this.keyInActiveState = 0;
            this.indexOfActiveChar = 0;
            this.text = text;
            this.paintedText = text;
            this.setPasswordTest();
            this.caretPos = text.length();
            this.setOffset();
        }
    }

    public void setTextPC(String text) {
        if (text != null) {
            lastKey = -1984;
            this.keyInActiveState = 0;
            this.indexOfActiveChar = 0;
            this.text = text;
            this.paintedText = text;
            this.setPasswordTest();
            this.caretPos = text.length();
            this.setOffsetPC();
        }
    }

    public void insertText(String text) {
        this.text = this.text.substring(0, this.caretPos) + text + this.text.substring(this.caretPos);
        this.setPasswordTest();
        this.caretPos += text.length();
        this.setOffset();
    }

    public int getMaxTextLenght() {
        return this.maxTextLenght;
    }

    public void setMaxTextLenght(int maxTextLenght) {
        this.maxTextLenght = maxTextLenght;
    }

    public int getIputType() {
        return this.inputType;
    }

    public void setIputType(int iputType) {
        this.inputType = iputType;
    }

    public int getBBKeyCode(int keyCode) {
        for (int i = 0; i < BBKEY.length; ++i) {
            for (int j = 0; j < BBKEY[i].length; ++j) {
                if (BBKEY[i][j] == keyCode) {
                    return i + 48;
                }
            }
        }

        return -1;
    }

    public void setFocus(boolean isfocus) {
        this.isFocus = isfocus;
    }

    public mCommand setCmdClear() {
        return this.cmdClear;
    }

    public static int getHeight() {
        return 28;
    }

    public void okTextBox() {
        if (GameCanvas.gameScr != null && (GameCanvas.gameScr.chatMode || GameCanvas.gameScr.chatWorld)) {
            GameCanvas.gameScr.doChat();
        }

        if (this.ischat && GameCanvas.isTouch) {
            MsgChat.cmdChat.performAction();
        }

        if (ChatTextField.isShow) {
            ChatTextField.isShow = false;
        }

        this.isFocus = false;
    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 1:
                this.clear();
            case 2:
            default:
        }
    }
}
