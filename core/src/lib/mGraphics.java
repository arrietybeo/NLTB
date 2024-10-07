// Decompiled with: CFR 0.152
// Class Version: 6
package lib;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.team.ngulong.MyGdxGame;

import java.util.Enumeration;
import javax.microedition.lcdui.Image;

import lib.Tilemap;
import lib.mHashtable;
import lib.mVector;

public class mGraphics {
    public static int zoomLevel = 1;
    private float r;
    private float gl;
    private float b;
    private float a;
    public boolean isTranslate;
    public boolean isClip;
    public int clipX;
    public int clipY;
    public int clipW;
    public int clipH;
    public static final byte ZOOM_IOS = 2;
    public static final boolean isTrue = true;
    public static final boolean isFalse = false;
    private int clipTX;
    private int clipTY;
    int translateX;
    int translateY;
    public static int HCENTER = 1;
    public static int VCENTER = 2;
    public static int LEFT = 4;
    public static int RIGHT = 8;
    public static int TOP = 16;
    public static int BOTTOM = 32;
    public static final int TRANS_NONE = 0;
    public static final int TRANS_ROT90 = 5;
    public static final int TRANS_ROT180 = 3;
    public static final int TRANS_ROT270 = 6;
    public static final int TRANS_MIRROR = 2;
    public static final int TRANS_MIRROR_ROT90 = 7;
    public static final int TRANS_MIRROR_ROT180 = 1;
    public static final int TRANS_MIRROR_ROT270 = 4;
    public static mHashtable cachedTextures = new mHashtable();
    public SpriteBatch g;
    public boolean isRorate;
    public int xRotate;
    public int yRotate;
    public float rotation;
    int wImg;
    int hImg;

    public void translate(int tx, int ty) {
        this.translateX += (tx *= zoomLevel);
        this.translateY += (ty *= zoomLevel);
        this.isTranslate = true;
        if (this.translateX == 0 && this.translateY == 0) {
            this.isTranslate = false;
        }
    }

    public static int getImageWidth(Image img) {
        return img.getWidth();
    }

    public static mGraphics getGraphics(Image img) {
        return new mGraphics();
    }

    public static int getImageHeight(Image img) {
        return img.getHeight();
    }

    public void begin() {
        this.g.begin();
    }

    public void end() {
        this.isClip = false;
        this.translateX = 0;
        this.translateY = 0;
        this.isTranslate = false;
        this.isClip = false;
        this.g.end();
    }

    public int getTranslateX() {
        return this.translateX / zoomLevel;
    }

    public int getTranslateY() {
        return this.translateY / zoomLevel;
    }

    public void enableBlending(float alpha) {
        this.g.setColor(1.0f, 1.0f, 1.0f, alpha);
    }

    public void disableBlending() {
        this.g.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void setClip(int x, int y, int w, int h) {
        if (w <= 0) {
            w = 1;
        }
        if (h <= 0) {
            h = 1;
        }
        x *= zoomLevel;
        y *= zoomLevel;
        w *= zoomLevel;
        h *= zoomLevel;
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        this.clipX = x;
        this.clipY = y;
        this.clipW = w;
        this.clipH = h;
        this.isClip = true;
    }

    public void beginClip() {
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(this.clipX, this.clipY, this.clipW, this.clipH);
        ScissorStack.calculateScissors(MyGdxGame.me.camera, this.g.getTransformMatrix(), clipBounds, scissors);
        ScissorStack.pushScissors(scissors);
    }

    public void endClip() {
    }

    public void endClip0() {
        this.g.flush();
        ScissorStack.popScissors();
    }

    public mGraphics(SpriteBatch g) {
        this.g = g;
    }

    public mGraphics() {
    }

    void cache(String key, Texture value) {
        if (cachedTextures.size() > 500) {
            Enumeration k = cachedTextures.keys();
            while (k.hasMoreElements()) {
                String k0 = (String) k.nextElement();
                Texture img = (Texture) cachedTextures.get(k0);
                cachedTextures.remove(k0);
                cachedTextures.remove(img);
                img.dispose();
                img = null;
            }
            cachedTextures.clear();
            System.gc();
        }
        cachedTextures.put(key, value);
    }

    public void clearCache() {
        Enumeration k = cachedTextures.keys();
        while (k.hasMoreElements()) {
            String k0 = (String) k.nextElement();
            Texture img = (Texture) cachedTextures.get(k0);
            cachedTextures.remove(img);
            cachedTextures.remove(k0);
            img.dispose();
            Object var3_3 = null;
        }
        cachedTextures.clear();
        System.gc();
    }

    public void drawTextureRegion(Image tx, int x, int y, int alg) {
        x *= zoomLevel;
        y *= zoomLevel;
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        this.g.draw(tx.tRegion, x, y);
    }

    public void fillRect(int x, int y, int w, int h, boolean useClip) {
        String key;
        Texture rgb_texture;
        x *= zoomLevel;
        y *= zoomLevel;
        if ((w *= zoomLevel) < 0 || (h *= zoomLevel) < 0) {
            return;
        }
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        if ((rgb_texture = (Texture) cachedTextures.get(key = "fr" + this.r + this.gl + this.b + this.a)) == null) {
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
        }
        if (this.isClip && useClip) {
            this.beginClip();
        }
        this.g.draw(rgb_texture, x, y, 0.0f, 0.0f, 1.0f, 1.0f, w, h, 0.0f, 0, 0, 1, 1, false, false);
        if (this.isClip && useClip) {
            this.endClip0();
        }
    }

    public float getAngle(Vector2 centerPt, Vector2 target) {
        float angle = (float) Math.toDegrees(Math.atan2(target.y - centerPt.y, target.x - centerPt.x));
        if (angle < 0.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public void drawLine(int x1, int y1, int x2, int y2, boolean useClip) {
        String key;
        Texture rgb_texture;
        x1 *= zoomLevel;
        y1 *= zoomLevel;
        x2 *= zoomLevel;
        y2 *= zoomLevel;
        if (this.isTranslate) {
            x1 += this.translateX;
            y1 += this.translateY;
            x2 += this.translateX;
            y2 += this.translateY;
        }
        if ((rgb_texture = (Texture) cachedTextures.get(key = "dl" + this.r + this.gl + this.b)) == null) {
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
        }
        float xSl = (float) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        int ySl = zoomLevel;
        Vector2 start = new Vector2(x1, y1);
        Vector2 end = new Vector2(x2, y2);
        float angle = this.getAngle(start, end);
        if (this.isClip && useClip) {
            this.beginClip();
        }
        this.g.draw(rgb_texture, x1, y1, 0.0f, 0.0f, 1.0f, 1.0f, xSl, ySl, angle, 0, 0, 1, 1, false, false);
        if (this.isClip && useClip) {
            this.endClip0();
        }
    }

    public void setColor(int rgb) {
        float R = rgb >> 16 & 0xFF;
        float B = rgb >> 8 & 0xFF;
        float G = rgb & 0xFF;
        this.b = B / 256.0f;
        this.gl = G / 256.0f;
        this.r = R / 256.0f;
        this.a = 1.0f;
    }

    public void setColor(int rgb, float alpha) {
        float R = rgb >> 16 & 0xFF;
        float B = rgb >> 8 & 0xFF;
        float G = rgb & 0xFF;
        this.b = B / 256.0f;
        this.gl = G / 256.0f;
        this.r = R / 256.0f;
        this.a = alpha;
    }

    public void drawRegion(Image img, int x, int y, int arg3, int arg4, int arg5, int arg6, int arg7, int anchor, boolean useClip) {
        if (img == null) {
            return;
        }
        this._drawRegion(img.texture, x *= 2, y *= 2, arg3 *= 2, arg4 *= 2, arg5, arg6 *= zoomLevel, arg7 *= zoomLevel, anchor, useClip, false);
    }

    public void drawRegion(Image img, int x, int y, int arg3, int arg4, int arg5, int arg6, int arg7, int anchor, boolean isScale, boolean useClip) {
        if (img == null) {
            return;
        }
        this._drawRegion(img.texture, x *= zoomLevel, y *= zoomLevel, arg3 *= zoomLevel, arg4 *= zoomLevel, arg5, arg6 *= zoomLevel, arg7 *= zoomLevel, anchor, useClip, isScale);
    }

    public void drawRegionNotSetClip(Image img, int x, int y, int arg3, int arg4, int arg5, int arg6, int arg7, int anchor) {
        if (img == null) {
            return;
        }
        this._drawRegion(img.texture, x *= zoomLevel, y *= zoomLevel, arg3 *= zoomLevel, arg4 *= zoomLevel, arg5, arg6 *= zoomLevel, arg7 *= zoomLevel, anchor, false, false);
    }

    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, boolean useClip) {
    }

    public void _drawRegion(Texture imgscr, int x_src, int y_src, int width, int height, int flip, int x_dest, int y_dest, int anchor, boolean isUseSetClip, boolean isScale) {
        if (imgscr == null) {
            return;
        }
        int wt = width * zoomLevel / 2;
        int ht = height * zoomLevel / 2;
        if (this.isTranslate) {
            x_dest += this.translateX;
            y_dest += this.translateY;
        }
        if (this.isClipWithWHZero()) {
            return;
        }
        boolean flipX = false;
        boolean flipY = true;
        int scX = 1;
        int scY = 1;
        boolean y0 = false;
        float orx = 0.0f;
        float ory = 0.0f;
        int ixA = 0;
        int iyA = 0;
        switch (anchor) {
            case 0:
            case 20: {
                ixA = 0;
                iyA = 0;
                break;
            }
            case 17: {
                ixA = wt / 2;
                if (flip == 4 || flip == 6 || flip == 5 || flip == 7) {
                    ixA = ht / 2;
                }
                iyA = 0;
                break;
            }
            case 24: {
                ixA = wt;
                if (flip == 4 || flip == 6 || flip == 5 || flip == 7) {
                    ixA = ht;
                }
                iyA = 0;
                break;
            }
            case 6: {
                ixA = 0;
                iyA = ht / 2;
                if (flip != 4 && flip != 7 && flip != 6 && flip != 5) break;
                iyA = wt / 2;
                break;
            }
            case 3: {
                ixA = wt / 2;
                iyA = ht / 2;
                if (flip != 4 && flip != 7 && flip != 6 && flip != 5) break;
                ixA = ht / 2;
                iyA = wt / 2;
                break;
            }
            case 10: {
                ixA = wt;
                iyA = ht / 2;
                if (flip != 4 && flip != 7 && flip != 6 && flip != 5) break;
                ixA = ht;
                iyA = wt / 2;
                break;
            }
            case 36: {
                ixA = 0;
                iyA = ht;
                if (flip != 4 && flip != 7 && flip != 6 && flip != 5) break;
                iyA = wt;
                break;
            }
            case 33: {
                ixA = wt / 2;
                iyA = ht;
                if (flip != 4 && flip != 7 && flip != 6 && flip != 5) break;
                ixA = ht / 2;
                iyA = wt;
                break;
            }
            case 40: {
                ixA = wt;
                iyA = ht;
                if (flip != 4 && flip != 7 && flip != 6 && flip != 5) break;
                ixA = ht;
                iyA = wt;
            }
        }
        x_dest -= ixA;
        y_dest -= iyA;
        int ix = 0;
        int iy = 0;
        switch (flip) {
            case 2: {
                flip = 0;
                flipX = true;
                break;
            }
            case 3: {
                flip = 180;
                iy = -ht;
                ix = -wt;
                break;
            }
            case 6: {
                flip = 90;
                flipY = false;
                flipX = true;
                ix = -ht;
                scY = -1;
                break;
            }
            case 5: {
                flip = 90;
                flipY = true;
                flipX = false;
                ix = -ht;
                iy = 0;
                scX = -1;
                break;
            }
            case 1: {
                flip = 0;
                flipY = false;
                break;
            }
            case 4: {
                flip = 90;
                flipY = false;
                flipX = false;
                ix = -ht;
                iy = 0;
                scX = -1;
                break;
            }
            case 7: {
                flip = 90;
                flipY = true;
                flipX = true;
                ix = -ht;
                scX = -1;
            }
        }
        if (this.isClip && isUseSetClip) {
            this.beginClip();
        }
        float valuef = (float) zoomLevel / 2.0f;
        this.g.draw(imgscr, x_dest - ix, y_dest - iy, orx, ory, width, height, valuef, valuef, flip, x_src, y_src, width, height, flipX, flipY);
        if (this.isClip && isUseSetClip) {
            this.endClip0();
        }
    }

    public void resetRotate() {
        this.isRorate = false;
        this.xRotate = 0;
        this.yRotate = 0;
    }

    public void rotate(int pAngle, int x, int y) {
        if (pAngle == 0) {
            return;
        }
        this.isRorate = true;
        this.rotation = pAngle;
        this.xRotate = x;
        this.yRotate = y;
    }

    public void drawImageMap(Image img, int x, int y) {
        if (img == null) {
            return;
        }
        x *= zoomLevel;
        y *= zoomLevel;
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        int w = img._getWidth();
        int h = img._getHeight();
        this.g.draw(img.texture, x, y + h, w, -h);
    }

    public void drawImage(Image img, int x, int y, int anchor, boolean useClip) {
        this._drawRegion(img.texture, 0, 0, img._getWidth(), img._getHeight(), 0, x *= zoomLevel, y *= zoomLevel, anchor, useClip, false);
    }

    public void _drawImage(Texture img, int x, int y, int anchor, boolean useClip) {
        if (img == null) {
            return;
        }
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        int ixA = 0;
        int iyA = 0;
        int iyA1 = 0;
        int ixA1 = 0;
        int w = img.getWidth();
        int h = img.getHeight();
        switch (anchor) {
            case 0:
            case 20: {
                ixA = 0;
                ixA1 = w;
                iyA = h;
                iyA1 = -iyA;
                break;
            }
            case 17: {
                ixA = -w / 2;
                ixA1 = w;
                iyA = h;
                iyA1 = -iyA;
                break;
            }
            case 24: {
                ixA = -w;
                ixA1 = w;
                iyA = h;
                iyA1 = -iyA;
                break;
            }
            case 6: {
                ixA = 0;
                ixA1 = w;
                iyA = h / 2;
                iyA1 = -h;
                break;
            }
            case 3: {
                ixA = -w / 2;
                ixA1 = w;
                iyA = h / 2;
                iyA1 = -h;
                break;
            }
            case 10: {
                ixA = -w;
                ixA1 = w;
                iyA = h / 2;
                iyA1 = -h;
                break;
            }
            case 36: {
                ixA = 0;
                ixA1 = w;
                iyA = 0;
                iyA1 = -h;
                break;
            }
            case 33: {
                ixA = -w / 2;
                ixA1 = w;
                iyA = 0;
                iyA1 = -h;
                break;
            }
            case 40: {
                ixA = -w;
                ixA1 = w;
                iyA = 0;
                iyA1 = -h;
            }
        }
        if (this.isClip && useClip) {
            this.beginClip();
        }
        this.g.draw(img, x + ixA, y + iyA, ixA1, iyA1);
        if (this.isClip && useClip) {
            this.endClip0();
        }
    }

    public void drawRect(int x, int y, int w, int h, boolean useClip) {
        int xx = 1;
        this.fillRect(x, y, w, xx, useClip);
        this.fillRect(x, y, xx, h, useClip);
        this.fillRect(x + w, y, xx, h + 1, useClip);
        this.fillRect(x, y + h, w + 1, xx, useClip);
    }

    public void drawRoundRect(int x, int y, int w, int h, int a, int b, boolean useClip) {
        this.drawRect(x, y, w, h, useClip);
    }

    public void fillRoundRect(int x, int y, int w, int h, int a, int b, boolean useClip) {
        this.fillRect(x, y, w, h, useClip);
    }

    public void drawString(mVector total) {
    }

    public void drawStringNotSetClip(mVector total) {
    }

    public void drawString(String s, float x, float y, BitmapFont font, int al, boolean useClip) {
        if (this.isClipWithWHZero()) {
            return;
        }
        x *= (float) zoomLevel;
        y *= (float) zoomLevel;
        if (this.isTranslate) {
            x += (float) this.translateX;
            y += (float) this.translateY;
        }
        if (this.isClip && useClip) {
            this.beginClip();
        }
        font.draw(this.g, s, x, y, 0.0f, al, useClip);
        if (this.isClip && useClip) {
            this.endClip0();
        }
    }

    public void setClipTrung(int x, int y, int w, int h) {
        x *= zoomLevel;
        y *= zoomLevel;
        w *= zoomLevel;
        h *= zoomLevel;
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        this.clipX = x;
        this.clipY = y;
        this.clipW = w;
        this.clipH = h;
        this.isClip = true;
    }

    public static Image blend(Image img, float level, int color) {
        int[] rgbdata = new int[img._getWidth() * img._getHeight()];
        img.texture.getTextureData().prepare();
        Pixmap a = img.texture.getTextureData().consumePixmap();
        int ww = img._getWidth();
        int hh = img._getHeight();
        int R = 0xFF & color >> 24;
        int B = 0xFF & color >> 16;
        int G = 0xFF & color >> 8;
        int A = 0xFF & color >> 0;
        int x = 0;
        while (x < ww) {
            int y = 0;
            while (y < hh) {
                int pixel = a.getPixel(x, y);
                if (pixel != -256) {
                    float r = (float) (R - (pixel >> 24 & 0xFF)) * level + (float) (pixel >> 24 & 0xFF);
                    float g = (float) (B - (pixel >> 16 & 0xFF)) * level + (float) (pixel >> 16 & 0xFF);
                    float b = (float) (G - (pixel >> 8 & 0xFF)) * level + (float) (pixel >> 8 & 0xFF);
                    float al = (float) (A - (pixel >> 0 & 0xFF)) * level + (float) (pixel >> 0 & 0xFF);
                    if (r > 255.0f) {
                        r = 255.0f;
                    }
                    if (r < 0.0f) {
                        r = 0.0f;
                    }
                    if (g > 255.0f) {
                        g = 255.0f;
                    }
                    if (g < 0.0f) {
                        g = 0.0f;
                    }
                    if (b < 0.0f) {
                        b = 0.0f;
                    }
                    if (b > 255.0f) {
                        b = 255.0f;
                    }
                    int rr = (int) r;
                    int gg = (int) g;
                    int bb = (int) b;
                    int aa = (int) al;
                    a.setColor((rr << 24) + (gg << 16) + (bb << 8) + (aa << 0));
                    a.drawPixel(x, y);
                }
                ++y;
            }
            ++x;
        }
        Image image = Image.createImage(ww, hh);
        image.texture = new Texture(a);
        a.dispose();
        return image;
    }

    public boolean isClipWithWHZero() {
        return this.isClip && (this.clipH == 0 || this.clipW == 0);
    }

    public void fillRect(int x, int y, int w, int h, int color, int alpha, boolean useClip) {
        String key;
        Texture rgb_texture;
        x *= zoomLevel;
        y *= zoomLevel;
        if ((w *= zoomLevel) < 0 || (h *= zoomLevel) < 0 || this.isClipWithWHZero()) {
            return;
        }
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        if ((rgb_texture = (Texture) cachedTextures.get(key = "fr" + this.r + this.gl + this.b + this.a + color)) == null) {
            this.setColor(color, 0.5f);
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
        }
        if (this.isClip && useClip) {
            this.beginClip();
        }
        this.g.draw(rgb_texture, x, y, 0.0f, 0.0f, 1.0f, 1.0f, w, h, 0.0f, 0, 0, 1, 1, false, false);
        if (this.isClip && useClip) {
            this.endClip0();
        }
    }

    public void fillRectAlpha(int x, int y, int w, int h, int tran, int alpha, boolean useClip) {
        String key;
        Texture rgb_texture;
        x *= zoomLevel;
        y *= zoomLevel;
        int color = 0;
        if ((w *= zoomLevel) < 0 || (h *= zoomLevel) < 0 || this.isClipWithWHZero()) {
            return;
        }
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        if ((rgb_texture = (Texture) cachedTextures.get(key = "fr" + this.r + this.gl + this.b + this.a + color)) == null) {
            this.setColor(color, 0.5f);
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
        }
        if (this.isClip && useClip) {
            this.beginClip();
        }
        this.g.draw(rgb_texture, x, y, 0.0f, 0.0f, 1.0f, 1.0f, w, h, 0.0f, 0, 0, 1, 1, false, false);
        if (this.isClip && useClip) {
            this.endClip0();
        }
    }

    public void ClipRec(int x, int y, int Width, int Height) {
    }

    public void ClipRec2(int x, int y, int Width, int Height) {
    }

    public void translateAndroid(int x, int y) {
    }

    public static void resetTransAndroid(mGraphics g) {
    }

    public void saveCanvas() {
    }

    public void restoreCanvas() {
    }

    public void fillRecAlpla(int x, int y, int w, int h, int color) {
        this.drawRecAlpa(0, 0, Tilemap.w * 16, y, color);
        this.drawRecAlpa(0, y, x, Tilemap.h * 16 - y, color);
        this.drawRecAlpa(x, y + h, Tilemap.w * 16 - x, Tilemap.h * 16 - (y + h), color);
        this.drawRecAlpa(x + w, y, Tilemap.w * 24 - (x + w), h, color);
    }

    public void drawRecAlpa(int x, int y, int w, int h, int color) {
        x *= zoomLevel;
        y *= zoomLevel;
        if ((w *= zoomLevel) < 0 || (h *= zoomLevel) < 0) {
            return;
        }
        if (this.isTranslate) {
            x += this.translateX;
            y += this.translateY;
        }
        this.setColor(color);
        this.a = 0.5f;
        String key = "fr" + this.r + this.gl + this.b + this.a;
        Texture rgb_texture = (Texture) cachedTextures.get(key);
        if (rgb_texture == null) {
            Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            p.setColor(this.r, this.b, this.gl, this.a);
            p.drawPixel(0, 0);
            rgb_texture = new Texture(p);
            p.dispose();
            this.cache(key, rgb_texture);
        }
        this.g.draw(rgb_texture, x, y, 0.0f, 0.0f, 1.0f, 1.0f, w, h, 0.0f, 0, 0, 1, 1, false, false);
    }
}
