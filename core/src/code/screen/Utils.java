package code.screen;

import code.main.GameMidlet;
import code.model.Actor;
import code.model.Char;
import code.model.Monster;
import code.screen.screen.FontTeam;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.MyStream;
import lib.mGraphics;
import lib.mVector;

public class Utils {
    public static boolean isQwerty;
    public static int[] goca;
    public static int[] gocb;
    public static int[] gocc;
    public static int[] angles;
    public static int[] flips;
    public static int[] imgIds;
    public static int[][] arr1 = new int[][]{{48}, {46, 44, 64, 63, 33, 95, 49, 92, 34, 47, 36, 45, 40, 41, 58, 42, 43, 60, 61, 62, 59, 37, 38, 126, 35, 37, 94, 38, 42, 123, 125, 91, 93, 59, 92, 39, 47, 49}, {97, 98, 99, 50, 225, 224, 7843, 227, 7841, 226, 7845, 7847, 7849, 7851, 7853, 259, 7855, 7857, 7859, 7861, 7863}, {100, 101, 102, 51, 273, 233, 232, 7867, 7869, 7865, 234, 7871, 7873, 7875, 7877, 7879, 51}, {103, 104, 105, 52, 237, 236, 7881, 297, 7883, 52}, {106, 107, 108, 53}, {109, 110, 111, 54, 243, 242, 7887, 245, 7885, 244, 7889, 7891, 7893, 7895, 7897, 417, 7899, 7901, 7903, 7905, 7907, 54}, {112, 113, 114, 115, 55}, {116, 117, 118, 56, 250, 249, 7911, 361, 7909, 432, 7913, 7915, 7917, 7919, 7921, 56}, {119, 120, 121, 122, 57, 253, 7923, 7927, 7929, 7925, 57}, {42}, {35}};
    public static int[][] arr2 = new int[][]{{48}, {49}, {97, 98, 99, 50}, {100, 101, 102, 51}, {103, 104, 105, 52}, {106, 107, 108, 53}, {109, 110, 111, 54}, {112, 113, 114, 115, 55}, {116, 117, 118, 56}, {119, 120, 121, 122, 57}, {48}, {48}};
    public static Random random = new Random();
    public static String FONT_7_WHITE = "c00";
    public static String FONT_7_BLACK = "c01";
    public static String FONT_7_BLUE = "c02";
    public static String FONT_7_GREEN = "c03";
    public static String FONT_7_GREEN1 = "c04";
    public static String FONT_7_GREY = "c05";
    public static String FONT_7_RED = "c06";
    public static String FONT_7_YELLOW = "c07";
    public static String FONT_7_ORANGE1 = "c08";
    public static String FONT_7B_WHITE = "c30";
    public static String FONT_7B_BLUE = "c31";
    public static String FONT_7B_GREEN = "c32";
    public static String FONT_7B_PURPLE = "c33";
    public static String FONT_7B_RED = "c34";
    public static String FONT_7B_YELLOW = "c35";
    public static String FONT_7B_ORANGE = "c36";
    public static final byte VUKHI_KIEM = 1;
    public static final byte VUKHI_TRIENTHU = 2;
    public static final byte VUKHI_DAO = 3;
    public static final int SYS_NORMAL = 0;
    public static final int SYS_KIM = 1;
    public static final int SYS_MOC = 2;
    public static final int SYS_THUY = 3;
    public static final int SYS_HOA = 4;
    public static final int SYS_THO = 5;
    public static final int TYPE_NON = 0;
    public static final int TYPE_VUKHI = 1;
    public static final int TYPE_AO = 2;
    public static final int TYPE_LIEN = 3;
    public static final int TYPE_THAT_LUNG = 4;
    public static final int TYPE_NHAN = 5;
    public static final int TYPE_QUAN = 6;
    public static final int TYPE_NGOCBOI = 7;
    public static final int TYPE_GIAY = 8;
    public static final int TYPE_PHU = 9;
    public static final int TYPE_THU_CUOI = 10;
    public static final int TYPE_MAT_NA = 11;
    public static final int TYPE_PHI_PHONG = 12;
    public static final int TYPE_AN_GIAP = 13;
    public static final int TYPE_BI_KIP = 14;
    public static final int TYPE_THU_NUOI = 15;
    public static final int TYPE_HP = 16;
    public static final int TYPE_MP = 17;
    public static final int TYPE_EAT = 18;
    public static final int TYPE_MONEY = 19;
    public static final int TYPE_PROTECT = 20;
    public static final int TYPE_CONVERT = 21;
    public static final int TYPE_WAIT = 25;
    public static final int TYPE_CRYSTAL = 26;
    public static final int TYPE_THO_DIA_PHU = 27;
    public static final int TYPE_BACH_BIEN_PHU = 28;
    public static final int TYPE_LENH_BAI = 29;
    public static final int TYPE_NGUYEN_LIEU = 30;
    public static final int TYPE_TASK = 31;
    public static final int TYPE_TASK_WAIT = 32;
    public static final int TYPE_NOT_FOCUS = 33;
    public static final int TYPE_NHAT_WAIT = 34;
    public static final int TYPE_ORDER = 100;
    public static final int TYPE_BODY_MIN = 0;
    public static final int TYPE_BODY_MAX = 15;
    public static final byte UI_MINI_HANHTRANG = 0;
    public static final byte UI_MINI_THONGTIN = 1;
    public static final byte UI_MINI_TIEMNANG = 2;
    public static final byte UI_MINI_KYNANG = 3;
    public static final byte UI_MINI_KYNANG_CHETAO = 8;
    public static final byte UI_MINI_NHIEMVU = 4;
    public static final byte UI_MINI_CHUCNANG = 5;
    public static final byte UI_MINI_THAOTAC = 6;
    public static final byte UI_BAG = 0;
    public static final byte UI_BOX = 1;
    public static final byte UI_BODY = 2;
    public static final byte UI_CUONGHOA = 3;
    public static final byte UI_CUONGHOA_LUONG = 4;
    public static final byte UI_LUYENDA = 5;
    public static final byte UI_LUYENDA_KHOA = 6;
    public static final byte UI_TACH = 7;
    public static final byte UI_CHUYENHOA = 8;
    public static final byte UI_TRAODOI = 9;
    public static final byte UI_RESERVE = 10;
    public static final byte UI_BANGHOI = 11;
    public static final byte UI_TEAM = 12;
    public static final byte UI_AUTO = 13;
    public static final byte UI_PK = 14;
    public static final byte UI_FRIEND = 16;
    public static final byte UI_ENEMY = 17;
    public static final byte UI_DANSO = 18;
    public static final byte UI_CHAT = 19;
    public static final byte UI_POPUP = 20;
    public static final byte UI_ZONE = 21;
    public static final byte UI_CONFIG = 22;
    public static final byte UI_CHANGE_NHHT = 23;
    public static final byte UI_NANG_AN_UP = 24;
    public static final byte UI_NANG_AN_DOWN = 25;
    public static final byte UI_CHUCPHUC = 26;
    public static final byte UI_BACH_BAO_RUONG = 27;
    public static final byte UI_TRADE_NPC = 28;
    public static final byte UI_GIAN_HANG_BAN = 29;
    public static final byte UI_GIAN_HANG_MUA = 30;
    public static final byte UI_GHM_MENU = 31;
    public static final byte UI_PRIVATE = 32;
    public static final int UI_SHOP_MIN = 50;
    public static final int UI_SHOP_MAX = 75;
    public static final byte UI_SHOP_DUOCPHAM = 50;
    public static final byte UI_SHOP_DUOCPHAM_KHOA = 51;
    public static final byte UI_SHOP_TAPHOA = 52;
    public static final byte UI_SHOP_TAPHOA_KHOA = 53;
    public static final byte UI_SHOP_CUAHANG = 54;
    public static final byte UI_SHOP_THUCUNG = 55;
    public static final byte UI_SHOP_THOITRANG = 56;
    public static final byte UI_SHOP_VUKHI = 57;
    public static final byte UI_SHOP_LIEN = 58;
    public static final byte UI_SHOP_NHAN = 59;
    public static final byte UI_SHOP_NGOCBOI = 60;
    public static final byte UI_SHOP_PHU = 61;
    public static final byte UI_SHOP_NONNAM = 62;
    public static final byte UI_SHOP_NONNU = 63;
    public static final byte UI_SHOP_AONAM = 64;
    public static final byte UI_SHOP_AONU = 65;
    public static final byte UI_SHOP_THATLUNGNAM = 66;
    public static final byte UI_SHOP_THATLUNGNU = 67;
    public static final byte UI_SHOP_QUANNAM = 68;
    public static final byte UI_SHOP_QUANNU = 69;
    public static final byte UI_SHOP_GIAYNAM = 70;
    public static final byte UI_SHOP_GIAYNU = 71;
    public static final byte UI_SHOP_TUULAU = 72;
    public static final byte UI_SHOP_TUULAU_KHOA = 73;
    public static final byte UI_SHOP_HONTHACH = 74;
    public static final byte UI_SHOP_CUAHANG_KHOA = 75;
    public static Random r = new Random();

    public static int getDir(int angle) {
        for (int i = 0; i < angles.length - 1; ++i) {
            if (angle >= angles[i] && angle <= angles[i + 1]) {
                if (i >= 16) {
                    return 0;
                }

                return i;
            }
        }

        return 0;
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException var3) {
        }

    }

    public static Image createImage(byte[] arr) {
        try {
            return Image.createImage((byte[]) arr, 0, arr.length);
        } catch (Exception var2) {
            return null;
        }
    }

    public static Image createImage(String path, boolean isStackTrace) throws IOException {
        Image img = null;
        img = Image.createImage(path);
        return img;
    }

    public static Image createImage(String path) throws IOException {
        Image img = null;
        img = Image.createImage(path);
        return img;
    }

    public static Image loadImage(String path) {
        return loadImage(path, true);
    }

    public static Image loadImage(String path, boolean isStackTrace) {
        path = "/img/x" + mGraphics.zoomLevel + path;
        Image img = null;

        try {
            img = Image.createImage(path);
        } catch (Exception var4) {
            if (isStackTrace) {
                Cout.println("path: " + path + "         " + img);
                var4.printStackTrace();
            }
        }

        return img;
    }

    public static void openUrl(String url) {
        GameMidlet var10000;
        try {
            var10000 = GameMidlet.instance;
            GameMidlet.platformRequest(url);
        } catch (Exception var5) {
            var5.printStackTrace();
        } finally {
            var10000 = GameMidlet.instance;
            GameMidlet.notifyDestroyed();
        }

    }

    public static int randomNegative(int max) {
        return random.nextInt(max) * (randomBoolean() ? 1 : -1);
    }

    public static int randomNumber(int max) {
        return random.nextInt(max);
    }

    public static boolean randomBoolean() {
        return random.nextInt(2) != 0;
    }

    public static int random(int a, int b) {
        return a + random.nextInt(b - a);
    }

    public static int randomId(int[] ids) {
        return ids[randomNumber(ids.length)];
    }

    public static String replace(String text, String regex, String replacement) {
        StringBuffer sBuffer = new StringBuffer();

        int pos;
        for (boolean var4 = false; (pos = text.indexOf(regex)) != -1; text = text.substring(pos + regex.length())) {
            sBuffer.append(text.substring(0, pos) + replacement);
        }

        sBuffer.append(text);
        return sBuffer.toString();
    }

    public static int distance(int p1, int p2) {
        return abs(p1 - p2);
    }

    public static int distance(int x1, int y1, int x2, int y2) {
        int dX = abs(x1 - x2);
        int dY = abs(y1 - y2);
        return dX > dY ? dX : dY;
    }

    public static int distanceSum(int x1, int y1, int x2, int y2) {
        int dX = abs(x1 - x2);
        int dY = abs(y1 - y2);
        return dX + dY;
    }

    public static String numberToString(String number) {
        String value = "";
        String value1 = "";
        if (number.equals("")) {
            return value;
        } else {
            if (number.charAt(0) == '-') {
                value1 = "-";
                number = number.substring(1);
            }

            for (int i = number.length() - 1; i >= 0; --i) {
                if ((number.length() - 1 - i) % 3 == 0 && number.length() - 1 - i > 0) {
                    value = number.charAt(i) + "." + value;
                } else {
                    value = number.charAt(i) + value;
                }
            }

            return value1 + value;
        }
    }

    public static String numberToString(int num) {
        String value = "";
        String value1 = "";
        String number = parseString(num);
        if (number.equals("")) {
            return value;
        } else {
            if (number.charAt(0) == '-') {
                value1 = "-";
                number = number.substring(1);
            }

            for (int i = number.length() - 1; i >= 0; --i) {
                if ((number.length() - 1 - i) % 3 == 0 && number.length() - 1 - i > 0) {
                    value = number.charAt(i) + "." + value;
                } else {
                    value = number.charAt(i) + value;
                }
            }

            return value1 + value;
        }
    }

    public static String replace(String str, String rep) {
        return replace(str, "#", rep);
    }

    public static String replace(String str, int rep) {
        return replace(str, "#", String.valueOf(rep));
    }

    public static Image transparentBG(Image img, int color) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] rgb = new int[w * h];
        img.getRGB(rgb, 0, w, 0, 0, w, h);

        for (int i = 0; i < rgb.length; ++i) {
            if (rgb[i] == -65315) {
                rgb[i] = 16777215;
            } else if (rgb[i] == -1) {
                rgb[i] = color;
            }
        }

        return Image.createRGBImage(rgb, w, h, true);
    }

    public static Image transparentBG(Image img, int color, int colorBorder) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] rgb = new int[w * h];
        img.getRGB(rgb, 0, w, 0, 0, w, h);

        for (int i = 0; i < rgb.length; ++i) {
            if (rgb[i] == -65315) {
                rgb[i] = 16777215;
            } else if (rgb[i] == -1) {
                rgb[i] = color;
            } else if (rgb[i] == -3407617 || rgb[i] == -11184811) {
                rgb[i] = colorBorder;
            }
        }

        return Image.createRGBImage(rgb, w, h, true);
    }

    public static Image transparentBG(Image img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] rgb = new int[w * h];
        img.getRGB(rgb, 0, w, 0, 0, w, h);

        for (int i = 0; i < rgb.length; ++i) {
            if (rgb[i] == -65315) {
                rgb[i] = 16777215;
            }
        }

        return Image.createRGBImage(rgb, w, h, true);
    }

    public static void init() {
        angles = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
        flips = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
        imgIds = new int[28];

        for (int i = 0; i < imgIds.length; i += 4) {
            imgIds[i + 0] = 0;
            imgIds[i + 1] = imgIds[i + 3] = 1;
            imgIds[i + 2] = 2;
        }

        int length = 91;
        goca = new int[length];
        gocb = new int[length];
        gocc = new int[length];

        for (int i = 0; i < length; ++i) {
            goca[i] = (int) (Math.sin((double) i * 3.141592653589793D / 180.0D) * 1000.0D);
            gocb[i] = (int) (Math.cos((double) i * 3.141592653589793D / 180.0D) * 1000.0D);
            gocc[i] = (int) (Math.tan((double) i * 3.141592653589793D / 180.0D) * 1000.0D);
        }

    }

    public static int tinhgoc(int[] goca, int angle, int delta) {
        angle = fixgoc(angle);
        if (angle >= 0 && angle < 90) {
            return goca[angle];
        } else if (angle >= 90 && angle < 180) {
            return delta * goca[180 - angle];
        } else {
            return angle >= 180 && angle < 270 ? -goca[angle - 180] : delta * -goca[360 - angle];
        }
    }

    public static int tinhgoc(int angel) {
        for (int i = 0; i < gocc.length; ++i) {
            if (gocc[i] >= angel) {
                return i;
            }
        }

        return 0;
    }

    public static int timgoc(int dx, int dy) {
        int angle = 0;
        if (dx != 0) {
            angle = tinhgoc(abs((dy << 10) / dx));
            if (dy >= 0 && dx < 0)
                angle = 180 - angle;
            if (dy < 0 && dx < 0)
                angle += 180;
            if (dy < 0 && dx >= 0)
                angle = 360 - angle;
        } else {
            angle = (dy > 0) ? 90 : 270;
        }
        return angle;
    }

    public static int fixgoc(int angle) {
        if (angle >= 360) {
            angle -= 360;
        }

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

    public static int laygoc(int dx, int dy, int angle) {
        int a = 0;
        if (dx != 0) {
            a = tinhgoc(abs((dy << 10) / dx));
            if (dy >= 0 && dx < 0)
                a = 180 - a;
            if (dy < 0 && dx < 0)
                a += 180;
            if (dy < 0 && dx >= 0)
                a = 360 - a;
        } else {
            a = (dy > 0) ? 90 : 270;
        }
        if (abs(a - angle) < 90 || dx * dx + dy * dy > 4096) {
            if (abs(a - angle) < 15)
                return a;
            if ((a - angle >= 0 && a - angle < 180) || a - angle < -180)
                return fixgoc(angle + 15);
            return fixgoc(angle - 15);
        }
        return a;
    }

    public static int abs(int value) {
        return Math.abs(value);
    }

    public static boolean isExposure(int minx1, int maxx1, int miny1, int maxy1, int minx2, int maxx2, int miny2, int maxy2) {
        return minx2 <= minx1 && minx1 <= maxx2 && miny2 <= miny1 && miny1 <= maxy2 || minx2 <= minx1 && minx1 <= maxx2 && miny2 <= maxy1 && maxy1 <= maxy2 || minx2 <= maxx1 && maxx1 <= maxx2 && miny2 <= miny1 && miny1 <= maxy2 || minx2 <= maxx1 && maxx1 <= maxx2 && miny2 <= maxy1 && maxy1 <= maxy2 || minx1 <= minx2 && minx2 <= maxx1 && miny1 <= miny2 && miny2 <= maxy1 || minx1 <= minx2 && minx2 <= maxx1 && miny1 <= maxy2 && maxy2 <= maxy1 || minx1 <= maxx2 && maxx2 <= maxx1 && miny1 <= miny2 && miny2 <= maxy1 || minx1 <= maxx2 && maxx2 <= maxx1 && miny1 <= maxy2 && maxy2 <= maxy1;
    }

    public static boolean isExposure(int minx, int maxx, int miny, int maxy, int x, int y) {
        return minx <= x && x <= maxx && miny <= y && y <= maxy;
    }

    public static Image resizeImage(Image image, int resizedWidth, int resizedHeight) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (resizedWidth == width && resizedHeight == height) {
            return image;
        } else {
            int[] in = new int[width];
            int[] out = new int[resizedWidth * resizedHeight];

            for (int y = 0; y < resizedHeight; ++y) {
                int dy = y * height / resizedHeight;
                image.getRGB(in, 0, width, 0, dy, width, 1);

                for (int x = 0; x < resizedWidth; ++x) {
                    int dx = x * width / resizedWidth;
                    out[resizedWidth * y + x] = in[dx];
                }
            }

            Image resized = Image.createRGBImage(out, resizedWidth, resizedHeight, true);
            return resized;
        }
    }

    public static Image resizeImage1(Image src, int screenWidth, int screenHeight) {
        return null;
    }

    public static byte[] getByteArray(Image img) {
        int[] imgRgbData = new int[img.getWidth() * img.getHeight()];
        byte[] imageData = null;

        try {
            img.getRGB(imgRgbData, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());
        } catch (Exception var6) {
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        try {
            for (int i = 0; i < imgRgbData.length; ++i) {
                dos.writeInt(imgRgbData[i]);
            }

            imageData = baos.toByteArray();
            baos.close();
            dos.close();
        } catch (Exception var7) {
        }

        return imageData;
    }

    public static Image rotateImage(Image imgSource, int cx, int cy, double angle) {
        int w1 = imgSource.getWidth();
        int h1 = imgSource.getHeight();
        int[] srcMap = new int[w1 * h1];
        imgSource.getRGB(srcMap, 0, w1, 0, 0, w1, h1);
        double dr = Math.sqrt((double) (cx * cx + cy * cy));
        int wh2 = (int) (2.0D * dr + 1.0D);
        int[] destMap = new int[wh2 * wh2];
        double radian = angle * 3.141592653589793D / 180.0D;

        for (int i = 0; i < w1; ++i) {
            for (int j = 0; j < h1; ++j) {
                int destX = (int) (dr + (double) (i - cx) * Math.cos(radian) + (double) (j - cy) * Math.sin(radian));
                int destY = (int) (dr + (double) (j - cy) * Math.cos(radian) - (double) (i - cx) * Math.sin(radian));
                destMap[wh2 * destY + destX] = srcMap[j * w1 + i];
                destMap[wh2 * destY + destX + 1] = srcMap[j * w1 + i];
            }
        }

        return Image.createRGBImage(destMap, wh2, wh2, true);
    }

    public static String[] split(String original, String separator) {
        mVector nodes = new mVector();

        for (int index = original.indexOf(separator); index >= 0; index = original.indexOf(separator)) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
        }

        nodes.addElement(original);
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); ++loop) {
                result[loop] = (String) nodes.elementAt(loop);
            }
        }

        return result;
    }

    public static String[] splitWidth(FontTeam f, String original, int w) {
        String[] strTemp = split(original, " ");
        mVector nodes = new mVector();
        int widthTemp = 0;
        String str = "";

        for (int i = 0; i < strTemp.length; ++i) {
            widthTemp += f.getWidth(strTemp[i]) + 1;
            if (widthTemp < w) {
                str = str + " " + strTemp[i];
            } else {
                nodes.addElement(str);
                widthTemp = f.getWidth(strTemp[i]);
                str = strTemp[i];
            }
        }

        nodes.addElement(str);
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); ++loop) {
                result[loop] = (String) nodes.elementAt(loop);
            }
        }

        return result;
    }

    public static FontTeam getFont(String s) {
        FontTeam f = FontTeam.arialFont;
        return f;
    }

    public static void callEffectHoanThanh(Char c) {
    }

    public static void callEffectLevelUp(Char c) {
    }

    public static void callEffectPhaohoa(Char c) {
    }

    public static void callEffectHoitu(int dx, int dy) {
    }

    public static void callEffectMonBien(Monster m) {
    }

    public static void print(String str) {
        Cout.println(str);
    }

    public static String parseString(int value) {
        return String.valueOf(value);
    }

    public static String dataSend() {
        return "0";
    }

    public static String dataReceive() {
        return "0";
    }

    public static String dataInternet() {
        return "0";
    }

    public static FontTeam getFont(int sys) {
        return FontTeam.arialFont;
    }

    public static int getHPColor(int sys) {
        return 0;
    }

    public static String getTime(int timeRemainS) {
        int timeRemainM = 0;
        if (timeRemainS > 60) {
            timeRemainM = timeRemainS / 60;
            timeRemainS %= 60;
        }

        int timeRemainH = 0;
        if (timeRemainM > 60) {
            timeRemainH = timeRemainM / 60;
            timeRemainM %= 60;
        }

        int timeRemainD = 0;
        if (timeRemainH > 24) {
            timeRemainD = timeRemainH / 24;
            timeRemainH %= 24;
        }

        String s = "";
        if (timeRemainD > 0) {
            s = s + timeRemainD;
            s = s + "d";
            s = s + timeRemainH + "h";
        } else if (timeRemainH > 0) {
            s = s + timeRemainH;
            s = s + "h";
            s = s + timeRemainM + "'";
        } else {
            if (timeRemainM > 9) {
                s = s + timeRemainM;
            } else {
                s = s + "0" + timeRemainM;
            }

            s = s + ":";
            if (timeRemainS > 9) {
                s = s + timeRemainS;
            } else {
                s = s + "0" + timeRemainS;
            }
        }

        return s;
    }

    public static void printStackTrace(Exception e) {
        e.printStackTrace();
    }

    public static FontTeam getFontByPhiPhong(int phiphong) {
        return FontTeam.arialFont;
    }

    public static FontTeam getFontBySys(int sys) {
        return FontTeam.arialFont;
    }

    public static void loadData() {
    }

    public static void gc() {
        System.gc();
    }

    public static void defaultServer() {
    }

    public static DataInputStream openFile(String path) {
        return new DataInputStream(MyStream.readFile("/" + path));
    }

    public static boolean waitConnect() {
        return true;
    }

    public static void deleteRecord(String filename) {
    }

    public static int loadRMSInt(String filename) {
        try {
            return Integer.parseInt(loadRMSString(filename));
        } catch (Exception var2) {
            return -1;
        }
    }

    public static boolean loadRMSBoolean(String filename) {
        byte[] b = loadRMS(filename);
        if (b == null) {
            return false;
        } else {
            return b[0] != 0;
        }
    }

    public static String loadRMSString(String filename) {
        byte[] data = loadRMS(filename);
        if (data == null) {
            return null;
        } else {
            try {
                String s = new String(data, "UTF-8");
                return s;
            } catch (Exception var3) {
                return new String(data);
            }
        }
    }

    public static void saveRMSString(String filename, String s) {
        try {
            saveRMS(filename, s.getBytes("UTF-8"));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static void saveRMSInt(String filename, int s) {
        try {
            saveRMS(filename, String.valueOf(s).getBytes("UTF-8"));
        } catch (Exception var3) {
        }

    }

    public static void saveRMSBoolean(String file, boolean value) {
        try {
            int x = value ? 1 : 0;
            saveRMS(file, new byte[]{(byte) x});
        } catch (Exception var3) {
        }

    }

    public static byte[] loadRMS(String filename) {
        return null;
    }

    public static void saveRMS(String filename, byte[] data) {
    }

    public static String getHttp(String url) {
        return "";
    }

    public static void addAllmVector(mVector v1, mVector v2) {
        for (int i = 0; i < v1.size(); ++i) {
            v2.addElement(v1.elementAt(i));
        }

    }

    public static int getWidth(Image img) {
        return img == null ? 0 : img.getWidth() / mGraphics.zoomLevel;
    }

    public static int getHeight(Image img) {
        return img == null ? 0 : img.getHeight() / mGraphics.zoomLevel;
    }

    public static byte changeRotate(byte rotate) {
        switch (rotate) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 4;
            case 3:
                return 7;
            case 4:
                return 3;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 2;
            default:
                return 0;
        }
    }

    public static byte changeMirror(byte rotate) {
        switch (rotate) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 1;
            case 5:
                return 4;
            case 6:
                return 7;
            case 7:
                return 0;
            default:
                return 2;
        }
    }

    public static byte[] readBytes(String filePath) {
        DataInputStream dis = null;
        byte[] data = null;

        try {
            dis = openFile(filePath);
            data = new byte[dis.available()];
            dis.read(data);
        } catch (Exception var12) {
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception var11) {
            }

        }

        return data;
    }

    public static String readFile(String filePath) {
        DataInputStream dis = null;
        String text = "";

        try {
            dis = openFile(filePath);

            for (String line = null; (line = readLine(dis)) != null; text = text + line + "\n") {
            }
        } catch (Exception var12) {
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception var11) {
            }

        }

        return text;
    }

    private static String readLine(DataInputStream reader) throws IOException {
        int readChar = reader.read();
        if (readChar == -1) {
            return null;
        } else {
            StringBuffer string;
            for (string = new StringBuffer(""); readChar != -1 && readChar != 10; readChar = reader.read()) {
                if (readChar != 13) {
                    string.append((char) readChar);
                }
            }

            return string.toString();
        }
    }

    public static byte[] readZipBytes(String filePath) {
        DataInputStream dis = null;
        byte[] data = null;

        try {
            dis = openFile(filePath);
            data = new byte[dis.available()];
            int i;
            if (data.length <= 50) {
                for (i = data.length - 1; i >= 0; --i) {
                    data[i] = dis.readByte();
                }
            } else {
                for (i = 50; i >= 0; --i) {
                    data[i] = dis.readByte();
                }

                for (i = 51; i < data.length; ++i) {
                    data[i] = dis.readByte();
                }
            }
        } catch (Exception var12) {
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception var11) {
            }

        }

        return data;
    }

    public static mVector copymVector(mVector mVector) {
        mVector v = new mVector();

        for (int i = 0; i < mVector.size(); ++i) {
            v.addElement(mVector.elementAt(i));
        }

        return v;
    }

    public static int getDistance(int x, int y, int x2, int y2) {
        return Res.getRange(x, x2, y, y2);
    }

    public static int getDistance(Actor obj1, Actor obj2) {
        return getDistance(obj1.x - obj2.x, obj1.y - obj2.y);
    }

    public static int sqrt(int a) {
        if (a <= 0) {
            return 0;
        } else {
            int x = (a + 1) / 2;

            int x1;
            do {
                x1 = x;
                x = x / 2 + a / (2 * x);
            } while (Math.abs(x1 - x) > 1);

            return x;
        }
    }

    public static int random_Am_0(int a) {
        int t;
        for (t = 0; t == 0; t = r.nextInt() % a) {
        }

        return t;
    }

    public static int random(int a) {
        return r.nextInt(a);
    }

    public static int getDistance(int x, int y) {
        return sqrt(x * x + y * y);
    }

    public static long currentTimeMillis_() {
        return System.currentTimeMillis();
    }
}
