package javax.microedition.lcdui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.DataInputStream;

import lib.LibSysTem;
import lib.mGraphics;
import lib.mSystem;

public class Image {
    public Texture texture;
    public TextureRegion tRegion;
    public int width;
    public int height;

    public static Image createRGBImage(int[] encodedData, int w, int h, boolean ispaint) {
        Image img = new Image();

        try {
            Pixmap p = new Pixmap(w, h, Pixmap.Format.RGBA8888);

            for (int i = 0; i < w; ++i) {
                for (int j = 0; j < h; ++j) {
                    p.setColor(mSystem.setColor(encodedData[j * w + i]));
                    p.drawPixel(i, j);
                }
            }

            byte[] a = int2byte(encodedData);
            img.texture = new Texture(p);
            img.width = img.texture.getWidth();
            img.height = img.texture.getHeight();
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } catch (Exception var8) {
        }

        return img;
    }

    public static Image createImage(String url) {
        Image img = new Image();

        try {
            Texture t = null;
            if (mGraphics.zoomLevel == 1) {
                t = new Texture(Gdx.files.internal(LibSysTem.res + url), Pixmap.Format.RGBA4444, false);
            } else {
                t = new Texture(Gdx.files.internal(LibSysTem.res + url));
            }

            img.texture = t;
            img.width = img.texture.getWidth();
            img.height = img.texture.getHeight();
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } catch (Exception var3) {
        }

        return img.texture == null ? null : img;
    }

    public int getWidth() {
        return this.width / 2;
    }

    public int getHeight() {
        return this.height / 2;
    }

    public int _getWidth() {
        return this.width;
    }

    public int _getHeight() {
        return this.height;
    }

    public static Image createImageTextureRegion() {
        Image img = new Image();
        img.tRegion = new TextureRegion();
        img.tRegion.flip(false, true);
        img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return img;
    }

    public static Image createImage(int w, int h) {
        Image img = new Image();
        Texture t = new Texture(w, h, Pixmap.Format.RGBA4444);
        img.texture = t;
        img.width = img.texture.getWidth();
        img.height = img.texture.getHeight();
        img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return img;
    }

    public static Image createImage(byte[] encodedData, int offset, int len) {
        Image img = new Image();

        try {
            img.texture = new Texture(new Pixmap(encodedData, offset, len));
            img.width = img.texture.getWidth();
            img.height = img.texture.getHeight();
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } catch (Exception var5) {
            System.out.println(" khong tao dc hinh tu mang byte " + var5.toString());
        }

        return img;
    }

    public static byte[] int2byte(int[] src) {
        int srcLength = src.length;
        byte[] dst = new byte[srcLength << 2];

        for (int i = 0; i < srcLength; ++i) {
            int x = src[i];
            int j = i << 2;
            dst[j++] = (byte) (x >>> 0 & 255);
            dst[j++] = (byte) (x >>> 8 & 255);
            dst[j++] = (byte) (x >>> 16 & 255);
            dst[j++] = (byte) (x >>> 24 & 255);
        }

        return dst;
    }

    public static Image createImage(int[] encodedData, int w, int h) {
        Image img = new Image();

        try {
            Pixmap p = new Pixmap(w, h, Pixmap.Format.RGBA8888);

            for (int i = 0; i < w; ++i) {
                for (int j = 0; j < h; ++j) {
                    p.setColor(mSystem.setColor(encodedData[j * w + i]));
                    p.drawPixel(i, j);
                }
            }

            byte[] a = int2byte(encodedData);
            img.texture = new Texture(p);
            img.width = img.texture.getWidth();
            img.height = img.texture.getHeight();
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } catch (Exception var7) {
        }

        return img;
    }

    public static Image createImage(Image scr, int x, int y, int w, int h) {
        if (scr == null) {
            throw new IllegalArgumentException("Image scr is NULL-----------.");
        } else {
            Image img = new Image();
            scr.texture.getTextureData().prepare();
            Pixmap a = scr.texture.getTextureData().consumePixmap();
            Pixmap b = new Pixmap(w, h, Pixmap.Format.RGBA4444);
            b.drawPixmap(a, 0, 0, x, y, w, h);
            img.texture = new Texture(b);
            img.width = w;
            img.height = h;
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            a.dispose();
            b.dispose();
            return img;
        }
    }

    public static Image createImageMiniMap(Image imgTile, int wMap, int hMap, int[] dataMap, int testValue, int sizeMini) {
        if (imgTile == null) {
            throw new IllegalArgumentException("Image imgTile is NULL-----------.");
        } else {
            imgTile.texture.getTextureData().prepare();
            Pixmap a = imgTile.texture.getTextureData().consumePixmap();
            Pixmap b = new Pixmap(wMap * sizeMini, hMap * sizeMini, Pixmap.Format.RGBA8888);

            for (int i = 0; i < wMap; ++i) {
                for (int j = 0; j < hMap; ++j) {
                    int u = dataMap[j * wMap + i] - 1;
                    if (u > testValue) {
                        b.drawPixmap(a, i * sizeMini, (hMap - 1 - j) * sizeMini, 0, u * sizeMini, sizeMini, sizeMini);
                    }
                }
            }

            Image img = new Image();
            img.texture = new Texture(b);
            img.width = wMap * sizeMini;
            img.height = hMap * sizeMini;
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            a.dispose();
            b.dispose();
            return img;
        }
    }

    public static DataInputStream openFile(String path) {
        DataInputStream is = new DataInputStream(LibSysTem.getResourceAsStream(path));
        return is;
    }

    public static int argb(int alpha, int red, int green, int blue) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    public void getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
        if (this.texture == null) {
            throw new IllegalArgumentException("texture Image getRGB is NULL-----------.");
        } else {
            try {
                TextureData tData = this.texture.getTextureData();
                if (!tData.isPrepared()) {
                    tData.prepare();
                }

                Pixmap a = tData.consumePixmap();
                boolean isRGB888 = false;
                if (a.getFormat() == Pixmap.Format.RGB888) {
                }

                int[] r = new int[width * height];
                Color color = new Color();

                int i;
                for (i = 0; i < width; ++i) {
                    for (int j = 0; j < height; ++j) {
                        int val = a.getPixel(i + x, j + y);
                        if (isRGB888) {
                            Color.rgb888ToColor(color, val);
                        } else {
                            Color.rgba8888ToColor(color, val);
                        }

                        int R = (int) (color.r * 255.0F);
                        int G = (int) (color.g * 255.0F);
                        int B = (int) (color.b * 255.0F);
                        int A = (int) (color.a * 255.0F);
                        if (isRGB888) {
                            A = 255;
                        }

                        r[j * width + i] = argb(A, R, G, B);
                    }
                }

                for (i = 0; i < rgbData.length; ++i) {
                    rgbData[i] = r[i];
                }
            } catch (Exception var20) {
            }

        }
    }

    public static String getLink(String str) {
        return str;
    }

    public static String replaceImg(String str) {
        String tam = str.replace(".img", ".png");
        return tam;
    }

    public static Image createImagex(String url) {
        url = replaceImg(url);
        Image img = new Image();

        try {
            img = createImage("/x" + mGraphics.zoomLevel + url);
        } catch (Exception var3) {
            mSystem.println("LOI LOAD IMAGE: " + "/x" + mGraphics.zoomLevel + url + "-- " + var3.toString());
        }

        return img == null ? null : img;
    }

    public static Image createImageAll(String url) {
        Image img = new Image();

        try {
            img = createImage(url);
        } catch (Exception var3) {
        }

        return img == null ? null : img;
    }

    public static int getImageWidth(Image image) {
        return image == null ? 0 : image._getWidth() / mGraphics.zoomLevel;
    }

    public static int getImageHeight(Image image) {
        return image == null ? 0 : image._getHeight() / mGraphics.zoomLevel;
    }

    public static Image createImage(Pixmap px) {
        Image img = new Image();

        try {
            Texture t = null;
            if (mGraphics.zoomLevel == 1) {
                t = new Texture(px, Pixmap.Format.RGBA4444, false);
            } else {
                t = new Texture(px);
            }

            img.texture = t;
            img.width = img.texture.getWidth();
            img.height = img.texture.getHeight();
            img.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } catch (Exception var3) {
        }

        return img.texture == null ? null : img;
    }
}
