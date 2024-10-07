package lib;

import code.main.GameCanvas;
import code.model.IsAnimal;
import code.model.TileTop;
import code.model.TreeTopBottom;
import code.network.GameService;
import code.screen.Res;
import code.screen.screen.GameScr;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import javax.microedition.lcdui.Image;

public class Tilemap {
    public static int idMusicLang;
    public static boolean IsThac;
    public static int w;
    public static int h;
    public static int pxw;
    public static int pxh;
    public static int SoundBackRound;
    public static final int T_BLOCK = 2;
    public static mVector pointPop = new mVector();
    public static short[] map;
    public static int[] type;
    public static int[] typeOfTile;
    public static Image imgMap;
    public static short lv = 0;
    public static Image imgThac;
    public static byte idXaphu = -1;
    public static int idLinhGac = 0;
    public static mVector trees = new mVector();
    public static mVector trees2 = new mVector();
    public static mVector trees3 = new mVector();
    public static mVector trees11 = new mVector();
    public static mVector trees12 = new mVector();
    public static mVector treesLand = new mVector();
    public static mHashtable treeLow1 = new mHashtable();
    public static mHashtable treeLow2 = new mHashtable();
    public static mHashtable treeLow3 = new mHashtable();
    public static int WidthMiniMap;
    public static int HeightMinimap;
    public static mHashtable treeTop_bottom = new mHashtable();
    public static int idTile = -1;
    public static boolean ismapLang;
    public static byte sizeBigmap;
    public static mHashtable tileTop = new mHashtable();
    public static mHashtable tileLayer3 = new mHashtable();
    public static short size = 16;
    public static byte paintLayerTree23 = 1;
    public static mVector mapchangeLocations = new mVector();
    public static boolean isOfflineMap;
    public static String mapName = "";
    public static byte frame;
    public static byte countframe;
    public static byte[] frameThac = new byte[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2};
    public static int sip = 4;
    static int song = 0;
    static int tick = 0;
    public static int wTile = 16;
    public static byte[] All_ID_Map_Lang;
    public static Image imgMapMini;

    public static void getImg() {
    }

    public static void reset() {
        Res.removeImgEffect();
        Res.removeWeaPone();
        Res.removeMonsterTemplet();
        Res.removePartInfo();
        Res.resetTrangBi();
        if (mapchangeLocations != null) {
            mapchangeLocations.removeAllElements();
        }

        if (GameCanvas.gameScr.actors != null) {
            GameCanvas.gameScr.actors.removeAllElements();
        }

        if (GameCanvas.gameScr.npc_limit != null) {
            GameCanvas.gameScr.npc_limit.removeAllElements();
        }

        IsAnimal.img = null;
        idLinhGac = 0;
    }

    public static void loadTileMap() {
        Res.loadTileImage(idTile);
        if (!mSystem.isj2me) {
            imgThac = GameCanvas.loadImage("/thac/" + idTile + ".png");
        }

        createMiniMap();
    }

    public static void clear() {
        reset();
        paintLayerTree23 = 1;
        map = null;
        tileLayer3.clear();
        treeLow1.clear();
        treeLow2.clear();
        treeLow3.clear();
        trees.removeAllElements();
        trees2.removeAllElements();
        trees11.removeAllElements();
        trees12.removeAllElements();
        trees3.removeAllElements();
        treesLand.removeAllElements();
        treeTop_bottom.clear();
    }

    public void resetData() {
    }

    public static void loadMap(int level, byte[] byteMap) {
        try {
            idMusicLang = -1;
            IsThac = false;
            imgMap = null;
            Res.maxHTree = 0;
            Res.maxWTree = 0;
            paintLayerTree23 = 1;
            tileLayer3.clear();
            treeLow1.clear();
            treeLow2.clear();
            treeLow3.clear();
            trees.removeAllElements();
            treesLand.removeAllElements();
            trees2.removeAllElements();
            trees11.removeAllElements();
            trees12.removeAllElements();
            trees3.removeAllElements();
            treeTop_bottom.clear();
            sizeBigmap = 0;
            if (mSystem.isAndroid && level == 39) {
                sizeBigmap = 14;
                GameScr.imgBigMap = new Image[14];
            }

            if (mSystem.isAndroid && level == 7) {
                sizeBigmap = 16;
                GameScr.imgBigMap = new Image[16];
            }

            map = null;
            Tilemap.type = null;
            lv = (short) level;
            WidthMiniMap = 50;
            HeightMinimap = 50;
            InputStream iss = null;
            DataInputStream isdata = null;
            if (byteMap == null) {
                iss = mSystem.getResourceAsStream("/datahd/map/m" + level);
                isdata = new DataInputStream(iss);
            } else {
                ByteArrayInputStream ba = new ByteArrayInputStream(byteMap);
                isdata = new DataInputStream(ba);
            }

            w = isdata.read();
            h = isdata.read();
            int idTilenew = isdata.read();
            Res.imgTile = null;
            imgThac = null;
            idTile = idTilenew;
            Res.loadTileImage(idTile);
            if (!mSystem.isj2me) {
                imgThac = GameCanvas.loadImage("/thac/" + idTile + ".png");
            }

            pxw = w << 4;
            pxh = h << 4;
            map = new short[w * h];
            tileTop.clear();
            Tilemap.type = new int[w * h];

            int x;
            for (x = 0; x < w * h; ++x) {
                map[x] = (short) isdata.read();
                if (map[x] == 254) {
                    map[x] = 255;
                }

                if (map[x] != 255 && map[x] != -1) {
                    Tilemap.type[x] = typeOfTile[map[x]];
                }
            }

            label307:
            while (true) {
                x = isdata.read();
                int y;
                int totalTreeTopBottom;
                int totalTile3;
                int i;
                int id;
                short dx;
                if (x == 255) {
                    while (true) {
                        x = isdata.read();
                        if (x == 255) {
                            GameCanvas.gameScr.loadCamera();
                            trees.removeAllElements();
                            mVector tem = new mVector();
                            int ntree = isdata.readShort();

                            short value;
                            for (totalTreeTopBottom = 0; totalTreeTopBottom < ntree; ++totalTreeTopBottom) {
                                totalTile3 = isdata.read();
                                i = isdata.read();
                                value = isdata.readShort();
                                if (value > -1) {
                                    if (value != 37 && value != 375 && value != 502) {
                                        tem.addElement(new Tree(totalTile3, i, value, true));
                                    } else {
                                        treesLand.addElement(new Tree(totalTile3, i, value, true));
                                    }
                                }
                            }

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < tem.size(); ++totalTreeTopBottom) {
                                trees.addElement(tem.elementAt(totalTreeTopBottom));
                            }

                            tem.removeAllElements();
                            ntree = isdata.readShort();

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < ntree; ++totalTreeTopBottom) {
                                totalTile3 = isdata.read();
                                i = isdata.read();
                                value = isdata.readShort();
                                if (value > -1 && !MainUnity.isJava && !GameCanvas.isLowGraphics) {
                                    tem.addElement(new Tree(totalTile3, i, value, false));
                                }

                                if (MainUnity.isJava && lv == 44) {
                                    tem.addElement(new Tree(totalTile3, i, value, false));
                                }
                            }

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < tem.size(); ++totalTreeTopBottom) {
                                trees2.addElement(tem.elementAt(totalTreeTopBottom));
                            }

                            tem.removeAllElements();
                            ntree = isdata.readShort();
                            trees3.removeAllElements();

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < ntree; ++totalTreeTopBottom) {
                                totalTile3 = isdata.read();
                                i = isdata.read();
                                value = isdata.readShort();
                                if (value > -1 && !MainUnity.isJava && !GameCanvas.isLowGraphics) {
                                    tem.addElement(new Tree(totalTile3, i, value, false));
                                }

                                if (MainUnity.isJava && lv == 44) {
                                    tem.addElement(new Tree(totalTile3, i, value, false));
                                }
                            }

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < tem.size(); ++totalTreeTopBottom) {
                                trees3.addElement(tem.elementAt(totalTreeTopBottom));
                            }

                            trees11.removeAllElements();
                            tem.removeAllElements();
                            ntree = isdata.readShort();

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < ntree; ++totalTreeTopBottom) {
                                totalTile3 = isdata.read();
                                i = isdata.read();
                                value = isdata.readShort();
                                if (value > -1) {
                                    tem.addElement(new Tree(totalTile3, i, value, false));
                                }
                            }

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < tem.size(); ++totalTreeTopBottom) {
                                trees11.addElement(tem.elementAt(totalTreeTopBottom));
                            }

                            trees12.removeAllElements();
                            tem.removeAllElements();
                            ntree = isdata.readShort();

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < ntree; ++totalTreeTopBottom) {
                                totalTile3 = isdata.read();
                                i = isdata.read();
                                value = isdata.readShort();
                                if (value > -1) {
                                    tem.addElement(new Tree(totalTile3, i, value, false));
                                }
                            }

                            for (totalTreeTopBottom = 0; totalTreeTopBottom < tem.size(); ++totalTreeTopBottom) {
                                trees12.addElement(tem.elementAt(totalTreeTopBottom));
                            }

                            tem.removeAllElements();
                            totalTreeTopBottom = isdata.readUnsignedByte();

                            int j;
                            for (totalTile3 = 0; totalTile3 < totalTreeTopBottom; ++totalTile3) {
                                TreeTopBottom tree = new TreeTopBottom();
                                tree.xGrid = (short) isdata.readUnsignedByte();
                                tree.yGrid = (short) isdata.readUnsignedByte();
                                id = isdata.readUnsignedByte();

                                Tree tt;
                                for (j = 0; j < id; ++j) {
                                    tt = new Tree(isdata.read(), isdata.read(), isdata.readShort(), false);
                                    tree.addTree(tt, 0);
                                }

                                id = isdata.readUnsignedByte();

                                for (j = 0; j < id; ++j) {
                                    tt = new Tree(isdata.read(), isdata.read(), isdata.readShort(), false);
                                    tree.addTree(tt, 1);
                                }

                                treeTop_bottom.put(String.valueOf(tree.yGrid) + tree.xGrid, tree);
                            }

                            try {
                                totalTile3 = isdata.readUnsignedByte();

                                for (i = 0; i < totalTile3; ++i) {
                                    id = isdata.readUnsignedByte();
                                    j = isdata.readUnsignedByte();
                                    value = (short) isdata.readUnsignedByte();
                                    TileTop top = new TileTop();
                                    top.x = (short) id;
                                    top.y = (short) j;
                                    top.index = (short) value;
                                    tileLayer3.put(String.valueOf(j * w + id), top);
                                }
                            } catch (Exception var14) {
                                var14.printStackTrace();
                            }

                            int size = isdata.readShort();

                            for (i = 0; i < size; ++i) {
                                value = isdata.readShort();
                                treeLow1.put(String.valueOf(value), "");
                            }

                            size = isdata.readShort();

                            for (i = 0; i < size; ++i) {
                                treeLow2.put(String.valueOf(isdata.readShort()), "");
                            }

                            size = isdata.readShort();

                            for (i = 0; i < size; ++i) {
                                treeLow3.put(String.valueOf(isdata.readShort()), "");
                            }

                            paintLayerTree23 = isdata.readByte();
                            Tree.ALL_TREE_INFO.clear();
                            size = isdata.readShort();

                            for (i = 0; i < size; ++i) {
                                id = isdata.readShort() + Res.ID_ITEM_MAP;
                                dx = isdata.readShort();
                                int dy = isdata.readShort();
                                code.model.TreeInfoNew tree = new code.model.TreeInfoNew(dx, dy);
                                Tree.ALL_TREE_INFO.put(String.valueOf(id), tree);
                            }
                            break label307;
                        }

                        y = isdata.read();
                        short value = (short) isdata.read();
                        if (value != 255) {
                            TileTop tile = new TileTop();
                            tile.y = (short) y;
                            tile.x = (short) x;
                            tile.index = value;
                            tileTop.put(y * w + x, tile);
                        }
                    }
                }

                y = isdata.read();
                totalTreeTopBottom = isdata.read();
                totalTile3 = isdata.read();

                for (i = y; i < y + totalTile3; ++i) {
                    for (id = x; id < x + totalTreeTopBottom; ++id) {
                        dx = (short) isdata.read();
                        if (dx != 255) {
                            TileTop tile = new TileTop();
                            tile.y = (short) i;
                            tile.x = (short) id;
                            tile.index = dx;
                            tileTop.put(i * w + id, tile);
                        }
                    }
                }
            }
            System.out.println("load map " + level);
        } catch (Exception var15) {
            var15.printStackTrace();
            mSystem.println("loi load map " + level);
        }

        if (GameCanvas.isTouch || GameScr.isIntro) {
            GameScr.mapFind = new byte[w][h];
        }

        createMiniMap();
        astar.createMapAstar(w, h, Tilemap.type);
        if (GameCanvas.gameScr != null) {
            GameCanvas.gameScr.setPosMiniMap();
        }

    }

    public static int getMax(mVector cord) {
        int n = -10;

        for (int i = 0; i < cord.size(); ++i) {
            int vl = Integer.parseInt((String) cord.elementAt(i));
            if (n < vl) {
                n = vl;
            }
        }

        return n;
    }

    public static int getMin(mVector cord) {
        int n = 10000;

        for (int i = 0; i < cord.size(); ++i) {
            int vl = Integer.parseInt((String) cord.elementAt(i));
            if (n > vl) {
                n = vl;
            }
        }

        return n;
    }

    public static void setNameMap(int idmap) {
    }

    public static void setArialBlock(int xb, int yb, int wb, int hb) {
        for (int i = xb; i < xb + wb * 16; ++i) {
            for (int j = yb; j < yb + hb * 16; ++j) {
                int aa = (j >> sip) * w + (i >> sip);
                type[aa] = 2;
            }
        }

    }

    public static void UnBlockArial(int xb, int yb, int wb, int hb) {
        for (int i = xb; i < xb + wb * 16; ++i) {
            for (int j = yb; j < yb + hb * 16; ++j) {
                int aa = (j >> sip) * w + (i >> sip);
                type[aa] = 0;
            }
        }

    }

    public static void createMiniMap() {
        if (Res.imgTile != null) {
            try {
                Texture texture = Res.imgTile.texture;
                if (!texture.getTextureData().isPrepared()) {
                    texture.getTextureData().prepare();
                }

                Pixmap pixmap = texture.getTextureData().consumePixmap();
                Pixmap pxmap = mSystem.createPixmap(w, h);

                for (int a = 0; a < w; ++a) {
                    for (int b = 0; b < h; ++b) {
                        int t = map[b * w + a];
                        if (t != 255) {
                            int x0 = t % 16;
                            int y0 = t / 16;
                            int dx = x0 << sip;
                            int dy = y0 << sip;
                            pxmap.drawPixmap(pixmap, a * mGraphics.zoomLevel, b * mGraphics.zoomLevel, dx * mGraphics.zoomLevel, dy * mGraphics.zoomLevel, 16 * mGraphics.zoomLevel, 16 * mGraphics.zoomLevel);
                        }
                    }
                }

                imgMap = Image.createImage(pxmap);
                pixmap.dispose();
                pxmap.dispose();
            } catch (Exception var10) {
                var10.printStackTrace();
            }

        }
    }

    public static final void paintTile(mGraphics g) {
        ++countframe;
        if (countframe > frameThac.length - 1) {
            countframe = 0;
        }

        frame = frameThac[countframe];
        if (map != null) {
            int y;
            int x;
            int b;
            int dx;
            int dy;
            byte type;
            int xs;
            int ys;
            if (sizeBigmap > 0) {
                y = 0;
                x = 0;
                if (lv == 39 && GameScr.imgBigMap == null) {
                    GameScr.imgBigMap = new Image[14];
                }

                if (lv == 7 && GameScr.imgBigMap == null) {
                    GameScr.imgBigMap = new Image[16];
                }

                int i;
                if (GameScr.imgBigMap != null) {
                    try {
                        for (i = 0; i < GameScr.imgBigMap.length; ++i) {
                            Image img = null;
                            if (GameScr.imgBigMap[i] != null) {
                                img = GameScr.imgBigMap[i];
                            }

                            if (img == null) {
                                String path = "bigimgmap" + i + "@" + lv;
                                if (mSystem.isAndroid && lv == 39) {
                                    path = "/big2/" + i + ".png";
                                }

                                if (mSystem.isAndroid && lv == 7) {
                                    path = "/big/" + i + ".png";
                                }

                                img = GameCanvas.loadImage(path);
                                if (img == null) {
                                    GameService.gI().RequestImgBigmap((byte) i);
                                }

                                if (img != null && GameScr.imgBigMap != null) {
                                    GameScr.imgBigMap[i] = img;
                                }
                            }

                            if (img != null && x + img.getWidth() >= GameScr.cmx && x <= GameScr.cmx + GameCanvas.w && y + img.getHeight() >= GameScr.cmy && y <= GameScr.cmy + GameCanvas.h) {
                                g.drawImage(img, x, y, 0, false);
                            }

                            x += 256;
                            if (x >= w * size) {
                                x = 0;
                                y += 256;
                            }
                        }
                    } catch (Exception var11) {
                    }
                }

                for (i = GameCanvas.gameScr.gssx; i < GameCanvas.gameScr.gssxe; ++i) {
                    for (b = GameCanvas.gameScr.gssy; b < GameCanvas.gameScr.gssye && map != null; ++b) {
                        int t = map[b * w + i];
                        if (t != 255) {
                            dx = t % 16;
                            dy = t / 16;
                            type = getTypeThac(idTile, dx, dy);
                            if (type != -1) {
                                xs = type % 16;
                                ys = type / 16;
                                g.drawRegion(imgThac, xs * 16, frame * 16 + ys * 48, 16, 16, 0, i << sip, b << sip, 0, false);
                            }
                        }

                        TileTop top = (TileTop) tileLayer3.get(String.valueOf(b * w + i));
                        if (top != null) {
                            top.paint(g);
                        }
                    }
                }
            } else {
                y = GameCanvas.gameScr.gssx;

                while (true) {
                    if (y >= GameCanvas.gameScr.gssxe) {
                        if (MainUnity.isJava && paintLayerTree23 == 1 || !MainUnity.isJava || MainUnity.isJava && lv == 44) {
                            paintTreeLayer(g, trees2, 2);
                            paintTreeLayer(g, trees3, 3);
                        }
                        break;
                    }

                    for (x = GameCanvas.gameScr.gssy; x < GameCanvas.gameScr.gssye && map != null; ++x) {
                        int t = map[x * w + y];
                        if (t != 255) {
                            b = t % 16;
                            int y0 = t / 16;
                            dx = b << sip;
                            dy = y0 << sip;
                            type = -1;
                            if (!mSystem.isj2me) {
                                type = getTypeThac(idTile, b, y0);
                            }

                            if (type != -1) {
                                xs = type % 16;
                                ys = type / 16;
                                g.drawRegion(imgThac, xs * 16, frame * 16 + ys * 48, 16, 16, 0, y << sip, x << sip, 0, false);
                            } else if (Res.imgTile == null) {
                                loadTileMap();
                            } else {
                                g.drawRegion(Res.imgTile, dx, dy, 16, 16, 0, y << sip, x << sip, 0, false);
                            }
                        }

                        TileTop top = (TileTop) tileLayer3.get(String.valueOf(x * w + y));
                        if (top != null) {
                            top.paint(g);
                        }
                    }

                    ++y;
                }
            }

            paintTreeLayer(g, treesLand, -1);
            if (!GameCanvas.currentScreen.isGameScreen() && !mSystem.isj2me) {
                paintTreeLayer(g, trees12, 12);
                paintTreeLayer(g, trees, 1);
            }
        }

    }

    public static boolean isTileWater(int x, int y) {
        if (idTile != 3 && idTile != 7) {
            return false;
        } else {
            x /= 16;
            y /= 16;
            int index = y * w + x;
            if (index < map.length - 1 && y * w + x >= 0) {
                int t = map[y * w + x];
                if (t != 255) {
                    int dx = t % 16;
                    int dy = t / 16;
                    if (idTile == 3 && (dx == 0 && dy == 4 || dx == 7 && dy == 3 || dx == 8 && dy == 3 || dx == 9 && dy == 3 || dx == 10 && dy == 3 || dx == 11 && dy == 3 || dx == 12 && dy == 3 || dx == 13 && dy == 3 || dx == 15 && dy == 3 || dx == 15 && dy == 2)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static byte getTypeThac(int idTile, int dx, int dy) {
        if (idTile == 0) {
            if (dx == 0 && dy == 1) {
                return 0;
            }

            if (dx == 0 && dy == 12) {
                return 1;
            }

            if (dx == 1 && dy == 1) {
                return 2;
            }

            if (dx == 1 && dy == 12) {
                return 3;
            }

            if (dx == 2 && dy == 1) {
                return 4;
            }

            if (dx == 3 && dy == 1) {
                return 5;
            }

            if (dx == 4 && dy == 1) {
                return 6;
            }

            if (dx == 5 && dy == 1) {
                return 7;
            }

            if (dx == 6 && dy == 1) {
                return 8;
            }

            if (dx == 7 && dy == 1) {
                return 9;
            }

            if (dx == 8 && dy == 0) {
                return 10;
            }

            if (dx == 8 && dy == 1) {
                return 11;
            }

            if (dx == 8 && dy == 2) {
                return 12;
            }

            if (dx == 9 && dy == 0) {
                return 13;
            }

            if (dx == 9 && dy == 1) {
                return 14;
            }

            if (dx == 9 && dy == 2) {
                return 15;
            }

            if (dx == 10 && dy == 0) {
                return 16;
            }

            if (dx == 10 && dy == 1) {
                return 17;
            }

            if (dx == 11 && dy == 0) {
                return 18;
            }

            if (dx == 11 && dy == 1) {
                return 19;
            }

            if (dx == 11 && dy == 2) {
                return 20;
            }

            if (dx == 12 && dy == 1) {
                return 21;
            }

            if (dx == 13 && dy == 0) {
                return 22;
            }

            if (dx == 13 && dy == 1) {
                return 23;
            }

            if (dx == 14 && dy == 0) {
                return 24;
            }

            if (dx == 14 && dy == 1) {
                return 25;
            }

            if (dx == 15 && dy == 0) {
                return 26;
            }

            if (dx == 15 && dy == 1) {
                return 27;
            }
        } else if (idTile == 3) {
            if (dx == 0 && dy == 4) {
                return 0;
            }

            if (dx == 1 && dy == 1) {
                return 1;
            }

            if (dx == 1 && dy == 2) {
                return 2;
            }

            if (dx == 1 && dy == 5) {
                return 3;
            }

            if (dx == 2 && dy == 2) {
                return 4;
            }

            if (dx == 3 && dy == 2) {
                return 5;
            }

            if (dx == 3 && dy == 5) {
                return 6;
            }

            if (dx == 4 && dy == 1) {
                return 7;
            }

            if (dx == 4 && dy == 2) {
                return 8;
            }

            if (dx == 6 && dy == 1) {
                return 9;
            }

            if (dx == 7 && dy == 1) {
                return 10;
            }

            if (dx == 7 && dy == 5) {
                return 11;
            }

            if (dx == 8 && dy == 1) {
                return 12;
            }

            if (dx == 9 && dy == 1) {
                return 13;
            }

            if (dx == 9 && dy == 5) {
                return 14;
            }

            if (dx == 10 && dy == 1) {
                return 15;
            }

            if (dx == 10 && dy == 5) {
                return 16;
            }

            if (dx == 11 && dy == 0) {
                return 17;
            }

            if (dx == 11 && dy == 1) {
                return 18;
            }

            if (dx == 11 && dy == 5) {
                return 19;
            }

            if (dx == 12 && dy == 0) {
                return 20;
            }

            if (dx == 12 && dy == 5) {
                return 21;
            }

            if (dx == 13 && dy == 1) {
                return 22;
            }

            if (dx == 13 && dy == 2) {
                return 23;
            }

            if (dx == 14 && dy == 1) {
                return 24;
            }

            if (dx == 14 && dy == 2) {
                return 25;
            }

            if (dx == 14 && dy == 5) {
                return 26;
            }

            if (dx == 15 && dy == 1) {
                return 27;
            }

            if (dx == 15 && dy == 3) {
                return 28;
            }

            if (dx == 15 && dy == 4) {
                return 29;
            }
        } else if (idTile == 5) {
            if (dx == 0 && dy == 3) {
                return 0;
            }

            if (dx == 1 && dy == 3) {
                return 1;
            }

            if (dx == 3 && dy == 3) {
                return 2;
            }

            if (dx == 4 && dy == 3) {
                return 3;
            }

            if (dx == 5 && dy == 3) {
                return 4;
            }

            if (dx == 6 && dy == 3) {
                return 5;
            }

            if (dx == 7 && dy == 2) {
                return 6;
            }

            if (dx == 7 && dy == 3) {
                return 7;
            }

            if (dx == 8 && dy == 2) {
                return 8;
            }

            if (dx == 8 && dy == 3) {
                return 9;
            }

            if (dx == 8 && dy == 4) {
                return 10;
            }

            if (dx == 9 && dy == 2) {
                return 11;
            }

            if (dx == 9 && dy == 3) {
                return 12;
            }

            if (dx == 9 && dy == 4) {
                return 13;
            }

            if (dx == 10 && dy == 2) {
                return 14;
            }

            if (dx == 10 && dy == 3) {
                return 15;
            }

            if (dx == 10 && dy == 4) {
                return 16;
            }

            if (dx == 11 && dy == 2) {
                return 17;
            }

            if (dx == 11 && dy == 4) {
                return 18;
            }

            if (dx == 12 && dy == 2) {
                return 19;
            }

            if (dx == 13 && dy == 2) {
                return 20;
            }

            if (dx == 14 && dy == 2) {
                return 21;
            }

            if (dx == 15 && dy == 2) {
                return 22;
            }
        } else if (idTile == 6) {
            if (dx == 0 && dy == 4) {
                return 0;
            }

            if (dx == 0 && dy == 8) {
                return 1;
            }

            if (dx == 1 && dy == 4) {
                return 2;
            }

            if (dx == 1 && dy == 9) {
                return 3;
            }

            if (dx == 1 && dy == 11) {
                return 4;
            }

            if (dx == 2 && dy == 4) {
                return 5;
            }

            if (dx == 2 && dy == 9) {
                return 6;
            }

            if (dx == 2 && dy == 11) {
                return 7;
            }

            if (dx == 3 && dy == 4) {
                return 8;
            }

            if (dx == 3 && dy == 11) {
                return 9;
            }

            if (dx == 4 && dy == 4) {
                return 10;
            }

            if (dx == 5 && dy == 4) {
                return 11;
            }

            if (dx == 6 && dy == 4) {
                return 12;
            }

            if (dx == 7 && dy == 4) {
                return 13;
            }

            if (dx == 7 && dy == 11) {
                return 14;
            }

            if (dx == 8 && dy == 4) {
                return 15;
            }

            if (dx == 8 && dy == 11) {
                return 16;
            }

            if (dx == 9 && dy == 4) {
                return 17;
            }

            if (dx == 9 && dy == 11) {
                return 18;
            }

            if (dx == 10 && dy == 4) {
                return 19;
            }

            if (dx == 11 && dy == 4) {
                return 20;
            }

            if (dx == 12 && dy == 4) {
                return 21;
            }

            if (dx == 13 && dy == 4) {
                return 22;
            }

            if (dx == 14 && dy == 7) {
                return 23;
            }

            if (dx == 15 && dy == 3) {
                return 24;
            }
        }

        return -1;
    }

    public static void paintTreeLayer(mGraphics g, mVector tree, int layer) {
        for (int i = 0; i < tree.size(); ++i) {
            Tree t = (Tree) tree.elementAt(i);
            if (!t.isLow(layer) || layer == -1) {
                t.paint(g);
            }
        }

    }

    private static void paintTreeLayer(mGraphics g, mHashtable tree) {
        Enumeration k = tree.keys();

        while (k.hasMoreElements()) {
            String key = (String) k.nextElement();
            Tree t = (Tree) tree.get(key);
            t.paint(g);
        }

    }

    public static final void paintTileTop(mGraphics g) {
        if (!mSystem.isj2me) {
            paintTreeLayer(g, trees11, 13);
            if (tileTop.size() > 0) {
                Enumeration k = tileTop.keys();

                while (k.hasMoreElements()) {
                    Integer key = (Integer) k.nextElement();
                    TileTop tile = (TileTop) tileTop.get(key);
                    if (tile.y >= GameCanvas.gameScr.gssy && tile.y <= GameCanvas.gameScr.gssye && tile.x >= GameCanvas.gameScr.gssx - 1 && tile.x <= GameCanvas.gameScr.gssxe + 1) {
                        tile.paint(g);
                    }
                }
            }

        }
    }

    public static final int tileTypeAt(int x, int y) {
        return type[y * w + x];
    }

    public static final int tileTypeAtPixel(int px, int py) {
        return (py >> sip) * w + (px >> sip) < type.length ? type[(py >> sip) * w + (px >> sip)] : -1;
    }

    public static final boolean tileTypeAtPixel(int px, int py, int t) {
        if (type == null) {
            return false;
        } else {
            int aa = (py >> sip) * w + (px >> sip);
            if (aa >= 0 && aa < type.length) {
                return (type[aa] & t) == t;
            } else {
                return true;
            }
        }
    }

    public static final boolean tileTypeAt(int x, int y, int t) {
        return (type[y * w + x] & t) == t;
    }

    public static final void killTileTypeAt(int px, int py, int t) {
        int[] var10000 = type;
        int var10001 = (py >> sip) * w + (px >> sip);
        var10000[var10001] &= ~t;
    }

    public static final int tileYofPixel(int py) {
        return py >> sip << sip;
    }

    public static boolean isMapLang() {
        for (int i = 0; i < All_ID_Map_Lang.length; ++i) {
            if (lv == All_ID_Map_Lang[i]) {
                return true;
            }
        }

        return false;
    }

    public static final int tileXofPixel(int px) {
        return px >> sip << sip;
    }

    public static boolean isMapIntro() {
        return lv == 39;
    }

    public static boolean isMapPhoban() {
        return lv == 65;
    }
}
