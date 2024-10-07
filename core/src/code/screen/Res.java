package code.screen;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.CharPartInfo;
import code.model.Chunk;
import code.model.ChunkData;
import code.model.ChunkDemo;
import code.model.ChunkTemplate;
import code.model.FrameImage;
import code.model.GemTemp;
import code.model.ItemTemplate;
import code.model.MonsterTemplate;
import code.model.QuickSlot;
import code.model.SmallImage;
import code.model.WPSplashInfo;
import code.model.mCommand;
import code.network.GameService;
import code.screen.screen.CreateCharScr;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Image;

import lib.MyStream;
import lib.Rms;
import lib.Tilemap;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Res {
    public static int ID_QUAI = 0;
    public static int ID_ITEM = 1000;
    public static int ID_NPC = 10000;
    public static int ID_CHAR = 10100;
    public static int ID_ITEM_MAP = 20000;
    public static int ID_START_SKILL = 23000;
    public static int ID_ICON_NPC = 25000;
    public static int ID_ICON_SKILL = 27000;
    public static int ID_EFFECT_SKILL = 27500;
    public static int ID_ICON_CLAN = 28500;
    public static final int MAX_DAM = 2000000;
    public static mVector itemTemplates = new mVector();
    public static mVector shopTemplate = new mVector();
    public static byte VERSION_CHUNK = -1;
    static Random rd = new Random(mSystem.currentTimeMillis());
    public static Image imgTile;
    public static Image[] imgEffect = new Image[51];
    public static Image imgExplosion;
    public static Image[] imgArrow = new Image[17];
    public static Image[] imgInv;
    public static Image imgCmdBar;
    public static Image[] imgBox = new Image[2];
    public static Image imgMSG;
    public static Image backround;
    public static FrameImage imgCheck;
    public static Image imgtileSmall;
    public static Image imgWayPoint;
    public static int wKhung;
    public static mHashtable listGetChunk = new mHashtable();
    public static int[][] arr;
    public static WPSplashInfo[][] wpSplashInfos = new WPSplashInfo[5][3];
    public static int maxHTree = 0;
    public static Res.LoadData load = new Res.LoadData();
    public static final short MAX_MONSTER_TEMPLATE = 114;
    public static MonsterTemplate[] monsterTemplates = new MonsterTemplate[114];
    public static final byte HEAD = 0;
    public static final byte BODY = 1;
    public static final byte LEG = 2;
    public static final byte WP = 3;
    public static mVector quanao = new mVector();
    static int time;
    public static int[] color;
    public static int[] nColor;
    static int wh;
    static int num;
    static Random r;
    public static int maxWTree;

    static {
        quanao.addElement(new mHashtable());
        quanao.addElement(new mHashtable());
        quanao.addElement(new mHashtable());
        quanao.addElement(new mHashtable());
        quanao.addElement(new mHashtable());
        color = new int[]{-8431096, -924406, -2249714, -2181525, -5535422, -11918336, -14595766};
        nColor = new int[]{5514752, 12742927, 5514752};
        wh = 6;
        r = new Random(mSystem.currentTimeMillis());
        maxWTree = 0;
    }

    public static void load() {
        loadChunk();
        Char.load();
        QuickSlot.getImg();
        mFont.loadmFont();
        FontTeam.init();
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

    public static byte getRotateLeftFromRight(int rotate) {
        switch (rotate) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            case 3:
                return 1;
            case 4:
                return 5;
            case 5:
                return 4;
            case 6:
                return 7;
            case 7:
                return 6;
            default:
                return 2;
        }
    }

    public static int getHeightRotate(int rotate, int w, int h) {
        switch (rotate) {
            case 0:
                return h;
            case 1:
                return h;
            case 2:
                return h;
            case 3:
                return h;
            case 4:
                return w;
            case 5:
                return w;
            case 6:
                return w;
            case 7:
                return w;
            default:
                return h;
        }
    }

    public static int getHeightRotateEffVuKhi(int rotate, int w, int h) {
        switch (rotate) {
            case 0:
                return h;
            case 1:
                return h;
            case 2:
                return h;
            case 3:
                return h;
            case 4:
                return w;
            case 5:
                return w;
            case 6:
                return w;
            case 7:
                return w;
            default:
                return h;
        }
    }

    public static int getWidthRotate(int rotate, int w, int h) {
        switch (rotate) {
            case 0:
                return w;
            case 1:
                return w;
            case 2:
                return w;
            case 3:
                return w;
            case 4:
                return h;
            case 5:
                return h;
            case 6:
                return h;
            case 7:
                return h;
            default:
                return w;
        }
    }

    public static int getWidthRotateEffVuKhi(int rotate, int w, int h) {
        switch (rotate) {
            case 0:
                return w;
            case 1:
                return w;
            case 2:
                return w;
            case 3:
                return w;
            case 4:
                return h;
            case 5:
                return h;
            case 6:
                return h;
            case 7:
                return h;
            default:
                return w;
        }
    }

    public static void loadChunkPrivate(byte[] data, int chunkID) {
        if (chunkID == -1)
            return;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(data));
            Chunk chunk = new Chunk();
            chunk.type = dis.readByte();
            chunk.idBig = dis.readShort();
            for (int k = 0; k < 52; k++)
                chunk.listIDChunkPaint[k] = dis.readShort();
            chunk.chunkData = new ChunkData[dis.readByte()];
            for (int j = 0; j < chunk.chunkData.length; j++) {
                chunk.chunkData[j] = new ChunkData();
                (chunk.chunkData[j]).index = dis.readByte();
                (chunk.chunkData[j]).dx = dis.readByte();
                (chunk.chunkData[j]).dy = dis.readByte();
            }
            int allsmallimg = dis.readByte();
            chunk.allSmallImg = new SmallImage[allsmallimg];
            for (int i = 0; i < allsmallimg; i++) {
                int id = dis.readUnsignedByte();
                chunk.allSmallImg[id] = new SmallImage(id, dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
            }
            Chunk.arr.put((new StringBuilder(String.valueOf(chunkID))).toString(), chunk);
            try {
                dis.close();
            } catch (Exception exception) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("KO TIM THAY CHUNK: " + chunkID);
        }
    }

    public static boolean loadChunkPrivateRms(int chunkID) {
        try {
            byte[] data = Rms.loadRMS("ck" + chunkID);
            if (data != null) {
                DataInputStream dis = null;
                try {
                    dis = new DataInputStream(new ByteArrayInputStream(data));
                    Chunk chunk = new Chunk();
                    short idBig = dis.readShort();
                    chunk.type = dis.readByte();
                    chunk.idBig = dis.readShort();
                    chunk.idBig = idBig;
                    for (int k = 0; k < 52; k++)
                        chunk.listIDChunkPaint[k] = dis.readShort();
                    chunk.chunkData = new ChunkData[dis.readByte()];
                    for (int j = 0; j < chunk.chunkData.length; j++) {
                        chunk.chunkData[j] = new ChunkData();
                        (chunk.chunkData[j]).index = dis.readByte();
                        (chunk.chunkData[j]).dx = dis.readByte();
                        (chunk.chunkData[j]).dy = dis.readByte();
                    }
                    int allsmallimg = dis.readByte();
                    chunk.allSmallImg = new SmallImage[allsmallimg];
                    for (int i = 0; i < allsmallimg; i++) {
                        int id = dis.readUnsignedByte();
                        chunk.allSmallImg[id] = new SmallImage(id, dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
                    }
                    Chunk.arr.put((new StringBuilder(String.valueOf(chunkID))).toString(), chunk);
                    try {
                        dis.close();
                    } catch (Exception exception) {
                    }
                    return true;
                } catch (Exception exception) {
                    try {
                        dis.close();
                    } catch (Exception exception1) {
                    }
                } finally {
                    try {
                        dis.close();
                    } catch (Exception exception) {
                    }
                }
            }
        } catch (Exception exception) {
        }
        return false;
    }


    public static void loadChunkPrivate(int chunkID) {
        if (chunkID == -1)
            return;
        if (loadChunkPrivateRms(chunkID))
            return;
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(mSystem.getResourceAsStream("/datahd/chunkdata/chunk" + chunkID));
            Chunk chunk = new Chunk();
            chunk.type = dis.readByte();
            chunk.idBig = dis.readShort();
            for (int k = 0; k < 52; k++)
                chunk.listIDChunkPaint[k] = dis.readShort();
            chunk.chunkData = new ChunkData[dis.readByte()];
            for (int j = 0; j < chunk.chunkData.length; j++) {
                chunk.chunkData[j] = new ChunkData();
                (chunk.chunkData[j]).index = dis.readByte();
                (chunk.chunkData[j]).dx = dis.readByte();
                (chunk.chunkData[j]).dy = dis.readByte();
            }
            int allsmallimg = dis.readByte();
            chunk.allSmallImg = new SmallImage[allsmallimg];
            for (int i = 0; i < allsmallimg; i++) {
                int id = dis.readUnsignedByte();
                chunk.allSmallImg[id] = new SmallImage(id, dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
            }
            Chunk.arr.put((new StringBuilder(String.valueOf(chunkID))).toString(), chunk);
            try {
                dis.close();
            } catch (Exception exception) {
            }
        } catch (Exception e) {
            if (mSystem.isj2me) {
                DataGetChunkChar data = (DataGetChunkChar) listGetChunk.get((new StringBuilder(String.valueOf(chunkID))).toString());
                if (data == null) {
                    listGetChunk.put((new StringBuilder(String.valueOf(chunkID))).toString(), new DataGetChunkChar());
                    GameService.gI().doRequestDataChar(VERSION_CHUNK, chunkID);
                } else if (data.isTimeOut()) {
                    GameService.gI().doRequestDataChar(VERSION_CHUNK, chunkID);
                    data.time = mSystem.currentTimeMillis();
                }
            }
        }
    }

    public static void loadChunk() {
        DataInputStream dis = null;
        boolean var1 = false;

        try {
            Chunk.demo = new ChunkDemo[5];

            for (int i = 0; i < 5; ++i) {
                try {
                    dis = new DataInputStream(mSystem.getResourceAsStream("/datahd/chunkdata/chunkdemo" + i));
                    Chunk.demo[i] = new ChunkDemo();
                    Chunk.demo[i].iconBegin = dis.readShort();
                    Chunk.demo[i].templates = new ChunkTemplate[52];

                    for (int j = 0; j < Chunk.demo[i].templates.length; ++j) {
                        Chunk.demo[i].templates[j] = new ChunkTemplate();
                        Chunk.demo[i].templates[j].id = dis.readShort();
                        if (Chunk.demo[i].templates[j].id != 0) {
                            Chunk.demo[i].templates[j].rotate = changeRotate(dis.readByte());
                            Chunk.demo[i].templates[j].paintId = dis.readByte();
                            Chunk.demo[i].templates[j].dx = dis.readByte();
                            Chunk.demo[i].templates[j].dy = dis.readByte();
                        }
                    }

                    dis.close();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
        } catch (Exception var4) {
        }

    }

    public static void loadPart2() {
    }

    public static int random(int a, int b) {
        return a + r.nextInt(b - a);
    }

    public static int random(int a) {
        return r.nextInt(a);
    }

    public static boolean isDestroy(int x1, int xw1, int x2, int xw2, int y1, int yh1, int y2, int yh2) {
        return x1 <= xw2 && xw1 >= x2 && y1 <= yh2 && yh1 >= y2;
    }

    public static ItemTemplate getItem(int clazz, int id) {
        ItemTemplate[] item = (ItemTemplate[]) itemTemplates.elementAt(0);

        for (int i = 0; i < item.length; ++i) {
            if (item[i] != null && item[i].id == id) {
                return item[i];
            }
        }

        return null;
    }

    public static GemTemp getShopByID(short id) {
        for (int i = 0; i < shopTemplate.size(); ++i) {
            GemTemp g = (GemTemp) shopTemplate.elementAt(i);
            if (g.id == id) {
                return g;
            }
        }

        return null;
    }

    public static Image createImgByHeader(byte[] header, byte[] data) {
        byte[] total = new byte[header.length + data.length];
        System.arraycopy(header, 0, total, 0, header.length);
        System.arraycopy(data, 0, total, header.length, data.length);
        return Image.createImage((byte[]) total, 0, total.length);
    }

    public static void removeWeaPone() {
        if (Char.imgWeapone != null) {
            int i;
            int j;
            for (i = 0; i < Char.imgWeapone.length; ++i) {
                for (j = 0; j < Char.imgWeapone[i].length; ++j) {
                    for (int a = 0; a < Char.imgWeapone[i][j].length; ++a) {
                        Char.imgWeapone[i][j][a] = null;
                    }
                }
            }

            for (i = 0; i < 5; ++i) {
                for (j = 0; j < 3; ++j) {
                    wpSplashInfos[i][j] = null;
                }
            }
        }

    }

    public static Image getImgArrow(int i) {
        if (imgArrow[i] == null && load.indexArrow == -1) {
            load.indexArrow = i;
        }

        return imgArrow[i];
    }

    public static void loadImgArrow(int i) {
    }

    public static void paintBackTile(mGraphics g, int x, int y, int size, mFont font, String tile) {
        for (int i = 0; i < 7; ++i) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, x - 42 + i * 12, y, mGraphics.VCENTER | mGraphics.LEFT, false);
        }

        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, x - 44, y, mGraphics.VCENTER | mGraphics.LEFT, false);
        int ys = 0;
        if (!mSystem.isj2me) {
            ys = -1;
        }

        g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, x + 44 + 1, y + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
        font.drawString(g, tile, x, y - 6, 2, false);
    }

    public static void paintImgEff(mGraphics g, int type, int x0, int y0, int w, int h, int x, int y, int anthor) {
        if (imgEffect[type] == null) {
            load.loadImgEffect(type);
        } else {
            g.drawRegion(imgEffect[type], x0, y0, w, h, 0, x, y, anthor, false);
        }

    }

    public static void paintFocus(mGraphics g, int x, int y, int w) {
        int numW = (w - mGraphics.getImageWidth(GameScr.backFocus) * 2) / mGraphics.getImageWidth(GameScr.backFocus);

        for (int i = 0; i < numW + 1; ++i) {
            int xpaint = x + mGraphics.getImageWidth(GameScr.backFocus) + i * mGraphics.getImageWidth(GameScr.backFocus);
            if (xpaint > x + w - mGraphics.getImageWidth(GameScr.backFocus)) {
                xpaint = x + w - mGraphics.getImageWidth(GameScr.backFocus);
            }

            g.drawRegion(GameScr.backFocus, 0, mGraphics.getImageHeight(GameScr.backFocus) / 2, mGraphics.getImageWidth(GameScr.backFocus), mGraphics.getImageHeight(GameScr.backFocus) / 2, 2, xpaint, y, 0, false);
        }

        g.drawRegion(GameScr.backFocus, 0, 0, mGraphics.getImageWidth(GameScr.backFocus), mGraphics.getImageHeight(GameScr.backFocus) / 2, 0, x, y, 0, false);
        g.drawRegion(GameScr.backFocus, 0, 0, mGraphics.getImageWidth(GameScr.backFocus), mGraphics.getImageHeight(GameScr.backFocus) / 2, 2, x + w - mGraphics.getImageWidth(GameScr.backFocus), y, 0, false);
    }

    public static void removeImgEffect() {
        for (int i = 0; i < 25; ++i) {
            if (i != 8) {
                imgEffect[i] = null;
            }
        }

        imgWayPoint = null;
    }

    public static void resetImgMonsTemp() {
    }

    public static void removeMonsterTemplet() {
        resetImgMonsTemp();
    }

    public static void loadMonster() {
    }

    public static void removePartInfo() {
    }

    public static CharPartInfo getCharPartInfo(int type, int id) {
        CharPartInfo cp = null;
        mHashtable has = (mHashtable) quanao.elementAt(type);
        cp = (CharPartInfo) has.get(String.valueOf(id));
        if (cp == null) {
            cp = new CharPartInfo(type, id);
            cp.time = (int) (mSystem.currentTimeMillis() / 1000L);
            has.put(String.valueOf(id), cp);
        }

        cp.timeRemove = (int) (mSystem.currentTimeMillis() / 1000L);
        return cp;
    }

    public static void resetTrangBi() {
    }

    public static final void loadTreeImage() {
    }

    public static final void removeTreeImage(int i) {
    }

    public static final void loadTileImage(int lv) {
        if (imgTile == null) {
            try {
                mSystem.println("load ");
                imgTile = GameCanvas.loadImage("/tile" + lv + ".png");
            } catch (Exception var3) {
                System.out.println("loi load tile: " + lv);
                var3.printStackTrace();
            }

            InputStream is = null;
            is = MyStream.readFile("/x1/tile" + lv + ".type");

            try {
                Tilemap.typeOfTile = null;
                Tilemap.typeOfTile = new int[is.available()];

                for (int i = 0; i < Tilemap.typeOfTile.length; ++i) {
                    Tilemap.typeOfTile[i] = is.read();
                }
            } catch (Exception var4) {
                System.out.println("Error Load Back Tile");
            }
        }

    }

    public static void paintDlgFull(mGraphics g, int x, int y) {
        x -= 10;
        int yy = y + 14;
        g.drawImage(imgInv[2], x + 15, yy, 20, false);
        g.drawImage(imgInv[2], x + 85, yy, 20, false);
        g.drawImage(imgInv[2], x + 15, yy + 46, 20, false);
        g.drawImage(imgInv[2], x + 85, yy + 46, 20, false);
        if (GameCanvas.currentScreen == CreateCharScr.gI()) {
            g.drawImage(imgInv[2], x + 15, yy + 92 - 10, 20, false);
            g.drawImage(imgInv[2], x + 85, yy + 92 - 10, 20, false);
        } else {
            g.drawImage(imgInv[2], x + 15, yy + 92, 20, false);
            g.drawImage(imgInv[2], x + 85, yy + 92, 20, false);
            g.drawImage(imgInv[2], x + 15, yy + 92 + 5, 20, false);
            g.drawImage(imgInv[2], x + 85, yy + 92 + 5, 20, false);
        }

        int xc = x + 12;
        int yc = y + 11;
        int w = 144;
        int h = 144;
        if (GameCanvas.currentScreen == CreateCharScr.gI()) {
            h = 129;
        }

        for (int i = 0; i < 3; ++i) {
            g.setColor(color[i]);
            g.drawRect(xc + i, yc + i, w - i * 2, h - i * 2 + 5, false);
        }

        g.drawImage(imgInv[0], x + 10, y + 9, 20, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 2, x + 159, y + 9, 24, false);
        if (GameCanvas.currentScreen == CreateCharScr.gI()) {
            g.drawRegion(imgInv[0], 0, 0, 18, 19, 6, x + 10, y + 148, 36, false);
            g.drawRegion(imgInv[0], 0, 0, 18, 19, 3, x + 159, y + 148, 40, false);
        } else {
            g.drawRegion(imgInv[0], 0, 0, 18, 19, 6, x + 10, y + 163, 36, false);
            g.drawRegion(imgInv[0], 0, 0, 18, 19, 3, x + 159, y + 163, 40, false);
        }

    }

    public static void paintSelected(mGraphics g, int x, int y, int w, int h) {
        g.setColor(-16742267);
        g.fillRect(x, y, w, h, false);
        g.setColor(-4868426);
        g.drawRect(x, y, w, h, false);
    }

    public static void paintDlgDragonFull(mGraphics g, int x, int y, int w, int h) {
        int nW = w / 70 + 1;
        int nH = h / 47 + 1;
        g.setClip(x, y, w, h);
        g.ClipRec(x, y, w, h);

        int i;
        for (i = 0; i < nW; ++i) {
            for (int j = 0; j < nH; ++j) {
                g.drawImage(imgInv[2], x + i * 70, y + j * 47, 0, false);
            }
        }

        g.restoreCanvas();
        g.setClip(0, 0, GameCanvas.w, GameCanvas.h);

        for (i = 0; i < 3; ++i) {
            g.setColor(color[i]);
            g.drawRect(x + i, y + i, w - i * 2, h - i * 2, false);
        }

        g.drawImage(imgInv[0], x - 2, y - 2, 0, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 2, x + w - 15, y - 2, 0, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 6, x - 2, y + h - 15, 0, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 3, x + w - 15, y + h - 16, 0, false);
        g.drawImage(imgInv[1], x + w / 2, y - 4, 3, false);
    }

    public static void paintDlgDragonFullNew(mGraphics g, int x, int y, int w, int h, int wImg, int hImg, Image img, boolean isboder) {
        int nW = w / wImg + 1;
        int nH = h / hImg + 1;
        if (mSystem.isPC) {
            g.drawRect(0, 0, 0, 0, true);
        }

        GameCanvas.resetTrans(g);
        g.setClip(x, y, w, h);
        g.ClipRec(x, y, w, h);

        int i;
        for (i = 0; i < nW; ++i) {
            for (int j = 0; j < nH; ++j) {
                g.drawImage(img, x + i * wImg, y + j * hImg, 0, true);
            }
        }

        g.restoreCanvas();
        g.setClip(0, 0, GameCanvas.w, GameCanvas.h);

        for (i = 0; i < 3; ++i) {
            g.setColor(nColor[i]);
            g.drawRect(x + i, y + i, w - i * 2, h - i * 2, true);
        }

        if (isboder) {
            g.drawRegion(GameScr.imgBoder[8], 0, 0, 18, 18, 1, x - 1, y - 1, 0, false);
            g.drawRegion(GameScr.imgBoder[8], 0, 0, 18, 18, 4, x + w - 16, y - 1, 0, false);
            g.drawRegion(GameScr.imgBoder[9], 0, 0, 11, 10, 7, x, y + h - 10, 0, false);
            g.drawRegion(GameScr.imgBoder[9], 0, 0, 11, 10, 2, x + w - 10, y + h - 9, 0, false);
            g.drawImage(GameScr.imgBoder[7], x + w / 2, y - 4, 3, false);
        }

    }

    public static void paintRectColor(mGraphics g, int x, int y, int w, int h, int count, int color1, int color2) {
        g.setColor(color1);
        g.drawRect(x, y, w, h, false);
        g.setColor(color2);
        int aa;
        if (count < w) {
            aa = wh;
            if (aa + count >= w) {
                aa = w - count;
            }

            g.fillRect(x + count, y, aa, 1, false);
        } else if (count < w + h) {
            aa = wh;
            if (aa + (count - w) >= h) {
                aa = h - (count - w);
            }

            g.fillRect(x + w, y + (count - w), 1, aa, false);
        } else if (count < w * 2 + h) {
            aa = wh;
            if (aa + (count - w - h) >= w) {
                aa = w - (count - w - h);
            }

            g.fillRect(x + (w - (count - w - h)) - aa, y + h, aa, 1, false);
        } else if (count < w * h * 2) {
            aa = wh;
            if (aa + (count - w * 2 - h) >= h) {
                aa = h - (count - w * 2 - h);
            }

            g.fillRect(x, y + (h - (count - w * 2 - h)) - aa, 1, aa, false);
        }

        ++num;
        if (num >= 4) {
            num = 0;
        }

    }

    public static void paintDlgFull(mGraphics g, int x, int y, int w, int h) {
        int aa = w / 70 + 1;
        g.setClip(x, y, w, h);
        g.ClipRec(x, y, w, h);

        int i;
        for (i = 0; i < aa; ++i) {
            g.drawImage(imgInv[2], x + 70 * i, y, 0, false);
        }

        g.setColor(-16500172);
        g.fillRect(x, y + 25, w, h, false);
        g.restoreCanvas();
        g.setClip(0, 0, GameCanvas.w, GameCanvas.h);

        for (i = 0; i < 3; ++i) {
            g.setColor(color[i]);
            g.drawRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1, false);
            g.fillRect(x + 3, y + 25, w - 6, 1, false);
        }

        g.drawImage(imgInv[0], x - 2, y - 2, 0, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 2, x + w + 2, y - 2, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 6, x - 2, y + h + 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 3, x + w + 2, y + h + 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
    }

    public static void paintDlgFull(mGraphics g, int x, int y, int w, int h, int y1, String tile, boolean isDD, int h0) {
        int aa = w / 70 + 1;
        g.setClip(x, y, w, h);
        g.ClipRec(x, y, w, h);

        int i;
        for (i = 0; i < aa; ++i) {
            g.drawImage(imgInv[2], x + 70 * i, y, 0, false);
        }

        g.setColor(-16500172);
        g.fillRect(x, y + 25, w, h, false);
        g.restoreCanvas();
        g.setClip(0, 0, GameCanvas.w, GameCanvas.h);

        for (i = 0; i < 3; ++i) {
            g.setColor(color[i]);
            g.drawRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1, false);
        }

        g.fillRect(x + 3, y + 25, w - 6, 1, false);
        if (!isDD) {
            g.fillRect(x + 3, y + 25 + y1 + h0, w - 6, 1, false);
        } else {
            g.fillRect(x + 3, y + 25 + y1 + h0, w - 6, 1, false);
            g.fillRect(x + 3, y + y1 + h0 - 2, w - 6, 1, false);
        }

        FontTeam.normalFont[0].drawString(g, tile, x + w / 2, y + 8, 2, false);
        g.drawImage(imgInv[0], x - 2, y - 2, 0, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 2, x + w + 2, y - 2, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 6, x - 2, y + h + 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(imgInv[0], 0, 0, 18, 19, 3, x + w + 2, y + h + 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
    }

    public static void drawRect(mGraphics g, int x, int y, int w, int h) {
        for (int i = 0; i < 3; ++i) {
            g.setColor(color[i]);
            g.drawRect(x + i, y + i, w - i * 2, h - i * 2, false);
        }

    }

    public static int[] trimArray(int[] array) {
        int[] result = null;
        mVector temp = new mVector();
        int i;
        for (i = 0; i < array.length; i++) {
            if (temp.size() == 0) {
                temp.addElement((new StringBuilder(String.valueOf(array[i]))).toString());
            } else {
                boolean dup = false;
                for (int j = 0; j < temp.size(); j++) {
                    int value = Integer.parseInt((String) temp.elementAt(j));
                    if (value == array[i]) {
                        dup = true;
                        break;
                    }
                }
                if (!dup)
                    temp.addElement((new StringBuilder(String.valueOf(array[i]))).toString());
            }
        }
        result = new int[temp.size()];
        for (i = 0; i < temp.size(); i++)
            result[i] = Integer.parseInt((String) temp.elementAt(i));
        return result;
    }


    public static int rnd(int a) {
        return r.nextInt(a);
    }

    public static int rnd(int a, int b) {
        int aa = r.nextInt(2);
        return aa == 0 ? a : b;
    }

    public static byte[] intToByteArray(int value) {
        byte[] b = new byte[4];

        for (int i = 0; i < 4; ++i) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) (value >>> offset & 255);
        }

        return b;
    }

    public static int random_Am_0(int a) {
        int t;
        for (t = 0; t == 0; t = r.nextInt() % a) {
        }

        return t;
    }

    public static int random_Am_0_2(int a) {
        if (a <= 1) {
            a = 1;
        }

        int t = 0;

        while (t == 0) {
            t = r.nextInt(a);
            if (random(2) == 0) {
                t = -t;
            }
        }

        return t;
    }

    public static int random_Am(int a, int b) {
        int t = a + r.nextInt(b - a);
        if (random(2) == 0) {
            t = -t;
        }

        return t;
    }

    public static int byteToInit(byte[] bArr) {
        return (255 & bArr[0]) << 24 | (255 & bArr[1]) << 16 | (255 & bArr[2]) << 8 | 255 & bArr[3];
    }

    public static String getDotNumber(long m) {
        String str = "" + m;
        int len = str.length() / 3;
        if (str.length() % 3 == 0) {
            --len;
        }

        for (int i = 0; i < len; ++i) {
            int index = str.length() - (i + 1) * 3 - i;
            str = str.substring(0, index) + "." + str.substring(index, str.length());
        }

        return str;
    }

    public static boolean inRangeActor(Actor p1, Actor p2, int range) {
        return Math.abs(p1.x - p2.x) <= range && Math.abs(p1.y - p2.y) <= range;
    }

    public static boolean inRangeActor(int x, int y, int x2, int y2, int range) {
        return Math.abs(x - x2) <= range && Math.abs(y - y2) <= range;
    }

    public static int getRange(Actor ac1, Actor ac2) {
        return Math.abs(ac1.x - ac2.x) + Math.abs(ac1.y - ac2.y);
    }

    public static int getRange(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void loadChunk(byte[] chunk, byte[] bitmap) {
        DataInputStream dis = null;
        boolean var3 = false;

        try {
            dis = new DataInputStream(new ByteArrayInputStream(chunk));
            Chunk.demo = new ChunkDemo[5];

            for (int i = 0; i < Chunk.demo.length; ++i) {
                Chunk.demo[i] = new ChunkDemo();
                Chunk.demo[i].iconBegin = dis.readShort();
                Chunk.demo[i].templates = new ChunkTemplate[52];

                for (int j = 0; j < Chunk.demo[i].templates.length; ++j) {
                    Chunk.demo[i].templates[j] = new ChunkTemplate();
                    Chunk.demo[i].templates[j].id = dis.readShort();
                    if (Chunk.demo[i].templates[j].id != 0) {
                        Chunk.demo[i].templates[j].rotate = changeRotate(dis.readByte());
                        Chunk.demo[i].templates[j].paintId = dis.readByte();
                        Chunk.demo[i].templates[j].dx = dis.readByte();
                        Chunk.demo[i].templates[j].dy = dis.readByte();
                    }
                }
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        readBitmap(bitmap);
    }

    public static void readBitmap(byte[] bitmap) {
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new ByteArrayInputStream(bitmap));
            int size = dis.readByte();
            arr = new int[dis.readShort()][size];

            for (int i = 0; i < arr.length; ++i) {
                arr[i][0] = dis.readByte();
                if (arr[i][0] < 0) {
                    arr[i][0] = 0;
                    arr[i][1] = 256;
                    arr[i][2] = 256;
                    arr[i][3] = 1;
                    arr[i][4] = 1;
                } else {
                    arr[i][1] = dis.readShort();
                    arr[i][2] = dis.readShort();
                    arr[i][3] = dis.readShort();
                    arr[i][4] = dis.readShort();
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

    }

    public static final Image loadTileImage2(int lv) {
        Image imgTile = null;
        if (imgTile == null) {
            try {
                mSystem.println("load ");
                imgTile = GameCanvas.loadImage("/tile" + lv + ".png");
            } catch (Exception var3) {
                System.out.println("loi load tile: " + lv);
                var3.printStackTrace();
            }
        }

        return imgTile;
    }

    public static class LoadData implements Runnable {
        int iWpsPlash = -1;
        int jWpsPlash = -1;
        int iImgWeaPone = -1;
        int jImgWeaPone = -1;
        int indexWeapone = -1;
        int indexImgEffect = -1;
        int indexTreeImg = -1;
        int indexMap = -1;
        int indexArrow = -1;
        mCommand iaLoadMap;
        public boolean isInitGame = false;
        public byte[] byteMap = null;

        public void loadMap(int index, mCommand ia, byte[] byteMap) {
            this.indexMap = index;
            this.iaLoadMap = ia;
            this.byteMap = byteMap;
            Tilemap.reset();
        }

        public void loadWpsPlash(int i, int j) {
        }

        public void loadImgWeaPone(int i, int j, int index) {
            if (this.iImgWeaPone == -1 && this.jImgWeaPone == -1) {
                this.iImgWeaPone = i;
                this.jImgWeaPone = j;
                this.indexWeapone = index;
            }

        }

        public void loadImgEffect(int index) {
            if (this.indexImgEffect == -1) {
                this.indexImgEffect = index;
            }

        }

        public void run() {
        }
    }
}
