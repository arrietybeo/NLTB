// Decompiled with: CFR 0.152
// Class Version: 6
package lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import lib.LibSysTem;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;

public class SysTemFont {
    public static String charLits = " áàảãạăắằẳẵặâấầẩẫậéèẻẽẹêế�?ểễệíìỉĩịóò�?õ�?ôốồổỗộơớ�?ởỡợúùủũụưứừửữựýỳỷỹỵđ�?ÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆ�?ÌỈĨỊÓÒỎÕỌÔ�?ỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰ�?ỲỶỸỴ�?";
    public BitmapFont font;
    private float r;
    private float g;
    private float b;
    private float a;
    public float[] charWidth;
    public byte charHeight;
    private float charSpace;
    float yAddFont;
    public Color cl;

    public SysTemFont(int ID, int color) {
        float width;
        String name = "big";
        name = ID <= 8 || ID >= 30 ? "big" : "mini";
        if (mGraphics.zoomLevel == 1) {
            name = "big";
        }
        if (mGraphics.zoomLevel == 2) {
            this.yAddFont = 1.5f;
        } else if (mGraphics.zoomLevel == 3) {
            this.yAddFont = 2.0f;
        } else if (mGraphics.zoomLevel == 4) {
            this.yAddFont = 2.3f;
        }
        this.font = new BitmapFont(Gdx.files.internal(String.valueOf(LibSysTem.font) + mGraphics.zoomLevel + "/" + name + ".fnt"), Gdx.files.internal(String.valueOf(LibSysTem.font) + mGraphics.zoomLevel + "/" + name + ".png"), true);
        this.cl = mSystem.setColor(color);
        this.font.setColor(this.cl);
        this.charWidth = new float[charLits.length()];
        this.charWidth = new float[charLits.length()];
        GlyphLayout layout = new GlyphLayout();
        int i = 0;
        while (i < this.charWidth.length) {
            layout.setText(this.font, String.valueOf(charLits.charAt(i)));
            width = layout.width;
            float height = layout.height;
            this.charWidth[i] = width / (float) mGraphics.zoomLevel;
            ++i;
        }
        layout.setText(this.font, "A");
        float height = layout.height;
        this.charHeight = (byte) (height / (float) mGraphics.zoomLevel + 3.0f);
        layout.setText(this.font, " ");
        width = layout.width;
        this.charSpace = width / (float) mGraphics.zoomLevel;
    }

    public void initFontX1(int ID, int color) {
        float width;
        if (mGraphics.zoomLevel == 2) {
            this.yAddFont = 1.5f;
        } else if (mGraphics.zoomLevel == 3) {
            this.yAddFont = 2.0f;
        } else if (mGraphics.zoomLevel == 4) {
            this.yAddFont = 2.3f;
        }
        int size = 9;
        size = ID == 26 ? 6 : (ID == 25 ? 5 : (ID == 24 ? 8 : (ID == 23 ? 8 : (ID == 102 ? 13 : (ID == 101 ? 13 : (ID == 100 ? 13 : (ID == -1 ? 5 : (ID == 32 ? 9 : (ID == 19 ? 5 : (ID == 22 ? 8 : (ID >= 30 && ID != 32 && ID < 100 ? 12 : (ID < 9 ? 8 : 8))))))))))));
        int si = 0;
        String Fontname = "";
        if (ID >= 100) {
            Fontname = "FontSys/staccato.ttf";
        } else if (ID <= 8 && ID >= 0 || ID >= 30 && ID < 100) {
            Fontname = "FontSys/Nokia Standard.ttf";
        } else {
            Fontname = "FontSys/Nokia Standard.ttf";
            si = -1;
        }
        FileHandle fontFile = Gdx.files.internal(String.valueOf(LibSysTem.font) + "/" + Fontname);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (size += si) * mGraphics.zoomLevel;
        parameter.characters = charLits;
        parameter.genMipMaps = true;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        parameter.flip = true;
        this.font = generator.generateFont(parameter);
        parameter = null;
        generator.dispose();
        this.cl = mSystem.setColor(color);
        this.font.setColor(this.cl);
        this.charWidth = new float[charLits.length()];
        this.charWidth = new float[charLits.length()];
        GlyphLayout layout = new GlyphLayout();
        int i = 0;
        while (i < this.charWidth.length) {
            layout.setText(this.font, String.valueOf(charLits.charAt(i)));
            width = layout.width;
            this.charWidth[i] = width / (float) mGraphics.zoomLevel;
            ++i;
        }
        layout.setText(this.font, "A");
        float height = layout.height;
        this.charHeight = (byte) (height / (float) mGraphics.zoomLevel + 3.0f);
        layout.setText(this.font, " ");
        width = layout.width;
        this.charSpace = width / (float) mGraphics.zoomLevel;
    }

    public Color rgba8888ToColor(int rgba8888) {
        Color color = new Color();
        color.r = (float) ((rgba8888 & 0xFF000000) >>> 24) / 255.0f;
        color.g = (float) ((rgba8888 & 0xFF0000) >>> 16) / 255.0f;
        color.b = (float) ((rgba8888 & 0xFF00) >>> 8) / 255.0f;
        color.a = (float) (rgba8888 & 0xFF) / 255.0f;
        return color;
    }

    public SysTemFont(String name, int color, float a) {
        float width;
        this.font = new BitmapFont(Gdx.files.internal(String.valueOf(LibSysTem.font) + name + ".fnt"), Gdx.files.internal(String.valueOf(LibSysTem.font) + name + ".png"), true);
        this.cl = mSystem.setColor(color);
        this.font.setColor(this.cl);
        this.charWidth = new float[charLits.length()];
        GlyphLayout layout = new GlyphLayout();
        int i = 0;
        while (i < this.charWidth.length) {
            layout.setText(this.font, String.valueOf(charLits.charAt(i)));
            width = layout.width;
            this.charWidth[i] = width / (float) mGraphics.zoomLevel;
            ++i;
        }
        layout.setText(this.font, "A");
        float height = layout.height;
        this.charHeight = (byte) height;
        layout.setText(this.font, " ");
        width = layout.width;
        this.charSpace = width / (float) mGraphics.zoomLevel;
    }

    public int getWidth(String st) {
        GlyphLayout layout = new GlyphLayout();
        layout.setText(this.font, st);
        return (int) layout.width / mGraphics.zoomLevel;
    }

    public int convert_RGB_to_ARGB(int rgb) {
        int r = rgb >> 16 & 0xFF;
        int g = rgb >> 8 & 0xFF;
        int b = rgb >> 0 & 0xFF;
        return 0xFF000000 | r << 16 | g << 8 | b;
    }

    public String[] splitString(String _text, String _searchStr) {
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
        mVector lines = this._splitFont(src, lineWidth);
        String[] arr = new String[lines.size()];
        int i = 0;
        while (i < lines.size()) {
            arr[i] = lines.elementAt(i).toString();
            ++i;
        }
        return arr;
    }

    public static String[] _splitString(String _text, String _searchStr) {
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

    public String replace(String _text, String _searchStr, String _replacementStr) {
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

    public String[] splitFont(String src, int lineWidth) {
        mVector lines = this._splitFont(src, lineWidth);
        String[] arr = new String[lines.size()];
        int i = 0;
        while (i < lines.size()) {
            arr[i] = lines.elementAt(i).toString();
            ++i;
        }
        return arr;
    }

    public mVector _splitFont(String src, int lineWidth) {
        mVector lines = new mVector();
        if (lineWidth <= 0) {
            lines.add(src);
            return lines;
        }
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

    public int getHeight() {
        return this.charHeight;
    }

    public void drawString(mGraphics g, String st, int x, int y, int align, boolean useClip) {
        int al = 8;
        switch (align) {
            case 0: {
                break;
            }
            case 1: {
                al = 16;
                break;
            }
            case 2: {
                al = 1;
            }
        }
        g.drawString(st, x, (float) y + this.yAddFont, this.font, al, useClip);
    }

    public mVector splitFontVector(String src, int lineWidth) {
        mVector lines = new mVector();
        if (lineWidth <= 0) {
            lines.add(src);
            return lines;
        }
        String line = "";
        int i = 0;
        while (i < src.length()) {
            if (src.charAt(i) == '\n' || src.charAt(i) == '\b') {
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
}
