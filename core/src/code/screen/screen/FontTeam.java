// Decompiled with: CFR 0.152
// Class Version: 6
package code.screen.screen;

import code.main.GameCanvas;
import code.model.FilePack;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class FontTeam {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;
    public static final byte TYPE_NORMAL = 0;
    public static final byte TYPE_LIGHT1 = 1;
    public static final byte TYPE_LIGHT2 = 2;
    public static final int COLOR_TRANSPARENT = 0xFF00DD;
    public static final int COLOR_WHITE = -1;
    public static final int COLOR_BLACK = -16777216;
    public static final int COLOR_YELLOW = -2560;
    public static final int COLOR_YELLOW1 = -1118703;
    public static final int COLOR_YELLOW2 = -210431;
    public static final int COLOR_GREY = -11184811;
    public static final int COLOR_RED = -65536;
    public static final int COLOR_GREEN = -7812062;
    public static final int COLOR_GREEN1 = -16711681;
    public static final int COLOR_GREEN2 = -15311616;
    public static final int COLOR_GREEN3 = -15610846;
    public static final int COLOR_GREEN4 = -15597773;
    public static final int COLOR_BLUE = -16742145;
    public static final int COLOR_PURPLE = -3407617;
    public static final int COLOR_PURPLE1 = -8714394;
    public static final int COLOR_PURPLE2 = -5700647;
    public static final int COLOR_ORANGE = -48128;
    public static final int COLOR_ORANGE1 = -2077158;
    public static final int COLOR_ORANGE2 = -22016;
    public static final int COLOR_BROWN = -440206;
    public static int COLOR_KIM = 0xEEBB00;
    public static int COLOR_MOC = 0x11CC22;
    public static int COLOR_THUY = 35071;
    public static int COLOR_HOA = 0xFF0000;
    public static int COLOR_THO = 0xAAAAAA;
    private Image imgFont;
    private String charList;
    public byte[] charWidth;
    private int charHeight;
    private int charSpace;
    public static FontTeam normalFontColor;
    public static FontTeam borderFont;
    public static FontTeam bigFont;
    public static FontTeam blackFont;
    public static FontTeam arialFont;
    public static FontTeam number_Yellow;
    public static FontTeam number_red;
    public static FontTeam number_Green;
    public static FontTeam number_Blue;
    public static FontTeam smallFontYellow;
    public static FontTeam smallFont;
    public static FontTeam arialFontW;
    public static FontTeam fontTile;
    public static FontTeam numberBig;
    public static FontTeam smallFontBlack;
    public static FontTeam numberSmall_white;
    public static FontTeam numberSmall_yeallow;
    public static FontTeam numberSmall_red;
    public static FontTeam numberSmall_green;
    public static FontTeam numberSmall_blue;
    public static FontTeam[] smallFontColor;
    public static FontTeam[] normalFont;
    public static FontTeam[] smallFontArr;
    public mFont mFont1;
    public mFont fontShadow;

    static {
        smallFontColor = new FontTeam[5];
        normalFont = new FontTeam[5];
        smallFontArr = new FontTeam[6];
    }

    public static void init() {
        FilePack.init("/font.sh");
        int i = 0;
        while (i < 5) {
            FontTeam.normalFont[i] = new FontTeam(i + 1);
            ++i;
        }
        normalFontColor = new FontTeam(8);
        borderFont = new FontTeam(6);
        bigFont = new FontTeam(7);
        blackFont = new FontTeam(9);
        arialFont = new FontTeam(10);
        smallFontYellow = new FontTeam("0123456789-+abcdefghijklmnopqrstuvwxyz ", new byte[]{10, 6, 10, 10, 10, 10, 11, 10, 10, 9, 8, 8, 11, 10, 10, 10, 9, 9, 10, 10, 6, 8, 11, 9, 11, 11, 11, 10, 11, 10, 10, 11, 11, 11, 11, 11, 11, 10, 5}, 11, "/mfont/small_f.png", 0, 19);
        number_Green = new FontTeam(" 0123456789+-", new byte[]{5, 10, 7, 10, 9, 10, 10, 10, 10, 10, 10, 8, 8}, 13, "/mfont/number_green.png", 0, 20);
        number_Blue = new FontTeam(" 0123456789+-", new byte[]{5, 10, 7, 10, 9, 10, 10, 10, 10, 10, 10, 8, 8}, 13, "/mfont/number_blue.png", 0, 21);
        number_red = new FontTeam(" 0123456789+-", new byte[]{5, 10, 7, 10, 9, 10, 10, 10, 10, 10, 10, 8, 8}, 13, "/mfont/number_red2.png", 0, 22);
        number_Yellow = new FontTeam(" 0123456789+-", new byte[]{5, 10, 7, 10, 9, 10, 10, 10, 10, 10, 10, 8, 8}, 13, "/mfont/number_yellow.png", 0, 23);
        int tem = 23;
        int i2 = 0;
        while (i2 < 5) {
            FontTeam.smallFontColor[i2] = new FontTeam("0123456789+-%$:abcdefghijklmnopqrstuvwxyz", new byte[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5}, 8, "/mfont/fs" + i2 + ".png", -1, ++tem);
            ++i2;
        }
        smallFont = new FontTeam("0123456789+-./abcdefghijklmnopqrstuvwxyz:@", new byte[]{4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, 5, "/mfont/fss_yellow.png", 1, ++tem);
        ++tem;
        arialFontW = new FontTeam(11);
        numberBig = new FontTeam("0123456789./+-%", new byte[]{16, 11, 16, 15, 15, 16, 16, 14, 14, 16, 5, 11, 9, 9, 16}, 14, "/mfont/fontBig.png", -1, -1);
        i2 = 0;
        while (i2 < 5) {
            FontTeam.smallFontArr[i2] = new FontTeam(11 + i2);
            ++i2;
        }
        smallFontBlack = new FontTeam("0123456789+-./abcdefghijklmnopqrstuvwxyz:@", new byte[]{4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 1, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, 5, "/mfont/fss_black.png", 1, tem);
        numberSmall_white = new FontTeam("0123456789+/km.b%", new byte[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 7, 3, 6, 8}, 8, "/mfont/number_whiteSTR.png", 0, ++tem);
        numberSmall_yeallow = new FontTeam("0123456789+/km.b%", new byte[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 5, 3, 6, 8}, 8, "/mfont/number_yellowSTR.png", 0, ++tem);
        numberSmall_red = new FontTeam("0123456789+/km.b%-", new byte[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 7, 3, 6, 8, 5}, 8, "/mfont/number_redSTR.png", 0, -1);
        numberSmall_green = new FontTeam("0123456789+/km.b%", new byte[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 7, 3, 6, 8}, 8, "/mfont/number_greenSTR.png", 0, -1);
        numberSmall_blue = new FontTeam("0123456789+/km.b%", new byte[]{5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 6, 7, 3, 6, 8}, 8, "/mfont/number_blueSTR.png", 0, -1);
        FontTeam.smallFontArr[5] = new FontTeam(18);
        fontTile = new FontTeam(17);
        mFont.load();
        FilePack.reset();
    }

    void initFont(int id) {
        switch (id) {
            case 19: {
                this.mFont1 = mFont.tahoma_7_yellow;
                this.mFont1.id = id;
                break;
            }
            case 20: {
                this.mFont1 = mFont.tahoma_7_green;
                this.mFont1.id = id;
                break;
            }
            case 21: {
                this.mFont1 = mFont.tahoma_7_blue;
                this.mFont1.id = id;
                break;
            }
            case 22: {
                this.mFont1 = mFont.tahoma_7_red;
                this.mFont1.id = id;
                break;
            }
            case 23: {
                this.mFont1 = mFont.tahoma_7_yellow;
                this.mFont1.id = id;
                break;
            }
            case 24: {
                this.mFont1 = mFont.tahoma_7_black;
                this.mFont1.id = id;
                break;
            }
            case 25: {
                this.mFont1 = mFont.tahoma_7_yellow;
                this.mFont1.id = id;
                break;
            }
            case 26: {
                this.mFont1 = mFont.tahoma_7_green;
                this.mFont1.id = id;
                break;
            }
            case 27: {
                this.mFont1 = mFont.tahoma_7_blue;
                this.mFont1.id = id;
                break;
            }
            case 28: {
                this.mFont1 = mFont.tahoma_7_red;
                this.mFont1.id = id;
                break;
            }
            case 29: {
                this.mFont1 = mFont.tahoma_7_yellow;
                this.mFont1.id = id;
                break;
            }
            case 30: {
                this.mFont1 = mFont.tahoma_7_black;
                this.mFont1.id = id;
                break;
            }
            case 31: {
                this.mFont1 = mFont.tahoma_7_white;
                this.mFont1.id = id;
                break;
            }
            case 32: {
                this.mFont1 = mFont.tahoma_7_yellow;
                this.mFont1.id = id;
            }
        }
    }

    public FontTeam(int id) {
        switch (id) {
            case 1: {
                this.mFont1 = mFont.tahoma_7b_white;
                this.mFont1.id = id;
                break;
            }
            case 2: {
                this.mFont1 = mFont.tahoma_7b_green;
                this.mFont1.id = id;
                break;
            }
            case 3: {
                this.mFont1 = mFont.tahoma_7b_red;
                this.mFont1.id = id;
                break;
            }
            case 4: {
                this.mFont1 = mFont.tahoma_7b_blue;
                this.mFont1.id = id;
                break;
            }
            case 5: {
                this.mFont1 = mFont.tahoma_7b_violet;
                this.mFont1.id = id;
                break;
            }
            case 6: {
                this.mFont1 = mFont.tahoma_7b_white;
                this.mFont1.id = id;
                this.fontShadow = mFont.tahoma_7b_black;
                this.fontShadow.id = id;
                break;
            }
            case 7: {
                this.mFont1 = mFont.tahoma_7b_white;
                this.mFont1.id = id;
                break;
            }
            case 8: {
                this.mFont1 = mFont.tahoma_7b_white;
                this.mFont1.id = id;
                this.fontShadow = mFont.tahoma_7b_black;
                this.fontShadow.id = id;
                break;
            }
            case 9: {
                this.mFont1 = mFont.tahoma_7_black;
                this.mFont1.id = id;
                break;
            }
            case 10: {
                this.mFont1 = mFont.tahoma_7_black;
                this.mFont1.id = id;
                break;
            }
            case 11: {
                this.mFont1 = mFont.tahoma_7_white;
                this.mFont1.id = id;
                break;
            }
            case 12: {
                this.mFont1 = mFont.tahoma_7_green;
                this.mFont1.id = id;
                break;
            }
            case 13: {
                this.mFont1 = mFont.tahoma_7_red;
                this.mFont1.id = id;
                break;
            }
            case 14: {
                this.mFont1 = mFont.tahoma_7_blue;
                this.mFont1.id = id;
                break;
            }
            case 15: {
                this.mFont1 = mFont.tahoma_7_violet;
                this.mFont1.id = id;
                break;
            }
            case 16: {
                this.mFont1 = mFont.tahoma_7_violet;
                this.mFont1.id = id;
                break;
            }
            case 17: {
                this.mFont1 = mFont.mfont_tile;
                this.mFont1.id = id;
                break;
            }
            case 18: {
                this.mFont1 = mFont.tahoma_7_yellow;
                this.mFont1.id = id;
            }
        }
    }

    public FontTeam(String charList, byte[] charWidth, int charHeight, String fontFile, int charSpace, int idFontFix) {
        if (mSystem.isPC && idFontFix > -1 && mGraphics.zoomLevel == 1) {
            this.initFont(idFontFix);
            return;
        }
        this.charSpace = charSpace;
        this.charList = charList;
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        try {
            this.imgFont = GameCanvas.loadImage(fontFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawString(mGraphics g, String st, int x, int y, int align, boolean isClip) {
        try {
            if (this.mFont1 != null) {
                if (this.fontShadow != null) {
                    this.fontShadow.drawString(g, st, x + 1, y + 1, align, isClip);
                }
                this.mFont1.drawString(g, st, x, y, align, isClip);
                return;
            }
            int len = st.length();
            int x1 = align == 0 ? x : (align == 1 ? x - this.getWidth(st) : x - (this.getWidth(st) >> 1));
            int i = 0;
            while (i < len) {
                int pos = this.charList.indexOf(st.charAt(i));
                if (pos == -1) {
                    pos = 0;
                }
                if (pos > -1) {
                    g.drawRegion(this.imgFont, 0, pos * this.charHeight, mGraphics.getImageWidth(this.imgFont), this.charHeight, 0, x1, y, 20, false);
                }
                x1 += this.charWidth[pos] + this.charSpace;
                ++i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getWidth(String st) {
        if (this.mFont1 != null) {
            return this.mFont1.getWidth(st);
        }
        int len = 0;
        int i = 0;
        while (i < st.length()) {
            int pos = this.charList.indexOf(st.charAt(i));
            if (pos == -1) {
                pos = 0;
            }
            len += this.charWidth[pos] + this.charSpace;
            ++i;
        }
        return len;
    }

    public String[] splitFontBStrInLine(String src, int lineWidth) {
        mVector list = new mVector();
        int srclen = src.length();
        if (srclen <= 1) {
            return new String[]{src};
        }
        String tem = "";
        int start = 0;
        int end = 0;
        src.replace('\t', ' ');
        while (true) {
            if (this.getWidth(tem) > lineWidth) {
                if (end > 0) {
                    --end;
                }
            } else {
                tem = String.valueOf(tem) + src.charAt(end);
                if (src.charAt(++end) != '\n') {
                    if (end < srclen - 1) continue;
                    end = srclen - 1;
                }
            }
            if (end != srclen - 1 && src.charAt(end + 1) != ' ') {
                int endAnyway = end;
                while (src.charAt(end + 1) != '\n' && (src.charAt(end + 1) != ' ' || src.charAt(end) == ' ') && end != start) {
                    --end;
                }
                if (end == start) {
                    end = endAnyway;
                }
            }
            list.addElement(src.substring(start, end + 1));
            if (end == srclen - 1) break;
            start = end + 1;
            while (start != srclen - 1 && src.charAt(start) == ' ') {
                ++start;
            }
            if (start == srclen - 1) break;
            end = start;
            tem = "";
        }
        String[] strs = new String[list.size()];
        int i = 0;
        while (i < list.size()) {
            strs[i] = (String) list.elementAt(i);
            while (strs[i].length() > 0 && (strs[i].charAt(0) == '\n' || strs[i].charAt(0) == '\r')) {
                strs[i] = strs[i].substring(1);
            }
            while (strs[i].length() > 0 && (strs[i].charAt(strs[i].length() - 1) == '\n' || strs[i].charAt(strs[i].length() - 1) == '\r')) {
                strs[i] = strs[i].substring(0, strs[i].length() - 1);
            }
            ++i;
        }
        return strs;
    }

    public mVector splitFontVector(String src, int lineWidth) {
        mVector lines = new mVector();
        String line = "";
        int i = 0;
        while (i < src.length()) {
            if (src.charAt(i) == '\n') {
                lines.addElement(line);
                line = "";
            } else {
                if (this.getWidth(line = String.valueOf(line) + src.charAt(i)) > lineWidth) {
                    int j = 0;
                    j = line.length() - 1;
                    while (j >= 0) {
                        if (line.charAt(j) == ' ') break;
                        --j;
                    }
                    if (j < 0) {
                        j = line.length() - 1;
                    }
                    lines.addElement(line.substring(0, j));
                    i = i - (line.length() - j) + 1;
                    line = "";
                }
                if (i == src.length() - 1 && !line.trim().equals("")) {
                    lines.addElement(line);
                }
            }
            ++i;
        }
        return lines;
    }

    public static String[] splitString(String _text, String _searchStr) {
        int count = 0;
        int pos = 0;
        int searchStringLength = _searchStr.length();
        int aa = _text.indexOf(_searchStr, pos);
        while (aa != -1) {
            pos = aa + searchStringLength;
            aa = _text.indexOf(_searchStr, pos);
            ++count;
        }
        String[] sb = new String[count + 1];
        int searchStringPos = _text.indexOf(_searchStr);
        int startPos = 0;
        int index = 0;
        while (searchStringPos != -1) {
            sb[index] = _text.substring(startPos, searchStringPos);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = _text.indexOf(_searchStr, startPos);
            ++index;
        }
        sb[index] = _text.substring(startPos, _text.length());
        return sb;
    }

    public String[] splitFontArray(String src, int lineWidth) {
        mVector lines = this.splitFontVector(src, lineWidth);
        String[] arr = new String[lines.size()];
        int i = 0;
        while (i < lines.size()) {
            arr[i] = lines.elementAt(i).toString();
            ++i;
        }
        return arr;
    }

    public static String replace(String _text, String _searchStr, String _replacementStr) {
        StringBuffer sb = new StringBuffer();
        int searchStringPos = _text.indexOf(_searchStr);
        int startPos = 0;
        int searchStringLength = _searchStr.length();
        while (searchStringPos != -1) {
            sb.append(_text.substring(startPos, searchStringPos)).append(_replacementStr);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = _text.indexOf(_searchStr, startPos);
        }
        sb.append(_text.substring(startPos, _text.length()));
        return sb.toString();
    }

    public int getHeight() {
        if (this.mFont1 != null) {
            return this.mFont1.getHeight();
        }
        return this.charHeight;
    }
}
