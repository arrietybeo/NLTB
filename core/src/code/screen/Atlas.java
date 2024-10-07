package code.screen;

import code.main.GameCanvas;
import code.model.Point;
import code.model.mImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import lib.Cout;
import lib.MyStream;
import lib.Tree;
import lib.mGraphics;
import lib.mVector;

public class Atlas {
   public static final int T_EMPTY = 0;
   public static final int T_COMPACT_1 = 4;
   public static final int T_COMPACT_2 = 8;
   public static final int T_COMPACT_3 = 16;
   public static final int T_COMPACT_4 = 32;
   public static final int T_WATER_1 = 64;
   public static final int T_WATER_2 = 128;
   public static final int T_WATER_3 = 256;
   public static final int T_WATER_4 = 512;
   public static final int[] T_TILES = new int[]{0, 4, 8, 16, 32, 64, 128, 256, 512};
   public byte size = 24;
   public int mapId;
   public int tileId;
   public int zoneId;
   public int type;
   public int tw;
   public int th;
   public short[] data;
   public DataInputStream dis = null;
   public boolean isLoading = true;
   public Tree[] trees;
   public int[] types;
   public int cmtoYmini;
   public int cmyMini;
   public int cmdyMini;
   public int cmvyMini;
   public int cmtoXMini;
   public int cmxMini;
   public int cmdxMini;
   public int cmvxMini;
   public int wMiniMap;
   public int hMiniMap;
   public int posMiniMapX;
   public int posMiniMapY;
   public long timeTranMini;
   public Image imgMiniMap = null;
   public mVector itemGames;
   public mVector persons;
   public mVector dartPoints;
   public mVector effectPoints;
   public mVector pluginPaints;
   public mVector questOrders;
   public mVector pts;
   public mVector listPts;
   public mVector friends;
   public mVector friendWaits;
   public mVector enemies;
   public int[] zones;
   public int[] numPts;
   public mVector messWait = new mVector();
   private static Atlas me;
   public Atlasmini[] templates;
   public mVector treeTemplates = new mVector();
   public mVector monTemplates = new mVector();
   public int timeLengthMap;
   public int timeStartMap;
   public int timeRun = -1;
   public int frameMon = 0;

   public static Atlas Me() {
      if (me == null) {
         me = new Atlas();
      }

      return me;
   }

   public Atlasmini getTemplate() {
      return this.templates[this.mapId];
   }

   public Atlasmini getTemplate(int mapId) {
      return this.templates[mapId];
   }

   public void clearMap() {
      me = null;
   }

   public void resetDataInMap() {
      this.itemGames = new mVector();
      this.persons = new mVector();
      this.dartPoints = new mVector();
      this.effectPoints = new mVector();
      this.pluginPaints = new mVector();
   }

   public void resetmVector() {
      this.resetDataInMap();
      this.questOrders = new mVector();
      this.pts = new mVector();
      this.listPts = new mVector();
      this.friends = new mVector();
      this.enemies = new mVector();
      this.friendWaits = new mVector();
      Utils.gc();
   }

   public void viewMap() {
   }

   public void reloadData() {
   }

   public void loadTile(mGraphics g, Image imgTile, int tileId, int m, int n, mVector miniImages) {
   }

   private mImage getImage(int id) {
      return null;
   }

   public int getIndex(int id) {
      return -1;
   }

   public void randomMap() {
      InputStream in = null;

      try {
         int mapId = Utils.random(1, 3);
         in = MyStream.readFile("/map/" + mapId);
         this.dis = new DataInputStream(in);
         this.setData(this.dis);
      } catch (Exception var3) {
      }

   }

   public boolean doLoad() {
      Object in = null;

      try {
         Cout.println("MAPID: " + this.mapId);
         in = MyStream.readFile("/map/" + this.mapId);
         this.dis = new DataInputStream((InputStream)in);
         this.setData(this.dis);
         return true;
      } catch (Exception var40) {
      } finally {
         try {
            ((InputStream)in).close();
         } catch (Exception var35) {
         }

         try {
            if (this.dis != null) {
               this.dis.close();
            }

            this.dis = null;
         } catch (Exception var34) {
         }

      }

      byte[] data = Utils.loadRMS("map" + this.mapId);
      if (data == null) {
         return false;
      } else {
         try {
            in = new ByteArrayInputStream(data);
            this.dis = new DataInputStream((InputStream)in);
            this.setData(this.dis);
//            new Integer(this.mapId);
            return true;
         } catch (Exception var38) {
         } finally {
            try {
               ((InputStream)in).close();
            } catch (Exception var37) {
            }

            try {
               if (this.dis != null) {
                  this.dis.close();
               }

               this.dis = null;
            } catch (Exception var36) {
            }

         }

         return false;
      }
   }

   public void loadType() {
      DataInputStream dis = null;

      try {
         dis = Utils.openFile("array/type" + this.tileId);
         byte[][] arr = new byte[dis.readUnsignedByte()][];

         int i;
         int j;
         for(i = 0; i < arr.length; ++i) {
            arr[i] = new byte[dis.readUnsignedByte()];

            for(j = 0; j < arr[i].length; ++j) {
               arr[i][j] = dis.readByte();
            }
         }

         this.types = new int[arr.length];

         for(i = 0; i < arr.length; ++i) {
            for(j = 0; j < arr[i].length; ++j) {
               int[] var10000 = this.types;
               var10000[i] |= T_TILES[arr[i][j]];
            }
         }
      } catch (Exception var13) {
         Cout.println(">>>>>>>>>>>>>>>>");
         var13.printStackTrace();
      } finally {
         try {
            dis.close();
         } catch (IOException var12) {
         }

      }

   }

   public void setData(DataInputStream dis) throws IOException {
   }

   public void setPosMiniMap() {
      int w = GameCanvas.w / 5;
      if (w > 60) {
         w = 60;
      }

      int h = GameCanvas.h / 5;
      if (h > 40) {
         h = 40;
      }

      if (w < h) {
         h = w;
      } else {
         h = w;
      }

      int x = GameCanvas.w - w;
      int y = 0;
      this.wMiniMap = w;
      this.hMiniMap = h;
      this.posMiniMapX = x;
      this.posMiniMapY = y;
   }

   public void updateMiniMap() {
   }

   public void updateCmMiniMap() {
   }

   public final void paint(mGraphics g) {
   }

   public void paintMiniMap(mGraphics g) {
   }

   public int getWidth() {
      return this.tw * this.size;
   }

   public int getHeight() {
      return this.th * this.size;
   }

   public boolean tileTypeAt(int px, int py, int t) {
      try {
         int tileIndex = this.TileIndex(px, py);
         return this.isTypeAt(tileIndex, t);
      } catch (Exception var5) {
         return false;
      }
   }

   public boolean isTypeAt(int tileIndex, int t) {
      short tileId = this.data[tileIndex];
      if (tileId == -1) {
         return true;
      } else {
         return (this.types[tileId] & t) == t;
      }
   }

   public boolean isInStairUDTile(int px, int py) {
      try {
         int tileIndex = this.TileIndex(px, py);
         short tileId = this.data[tileIndex];
         if (this.tileId == 0) {
            if (tileId == 24 || tileId == 26 || tileId == 27) {
               return true;
            }
         } else if (this.tileId == 1) {
            if (tileId == 14 || tileId == 16 || tileId == 92 || tileId == 94) {
               return true;
            }
         } else if (this.tileId == 3 && (tileId == 35 || tileId == 36 || tileId == 37 || tileId == 125 || tileId == 126 || tileId == 127)) {
            return true;
         }
      } catch (Exception var5) {
      }

      return false;
   }

   public boolean isInStairLRTile(int px, int py) {
      try {
         int tileIndex = this.TileIndex(px, py);
         short tileId = this.data[tileIndex];
         if (this.tileId == 0) {
            if (tileId == 166 || tileId == 175) {
               return true;
            }
         } else if (this.tileId == 1 && (tileId == 63 || tileId == 70 || tileId == 141 || tileId == 148)) {
            return true;
         }
      } catch (Exception var5) {
      }

      return false;
   }

   public int TileIndex(int x, int y) {
      return y / this.size * this.tw + x / this.size;
   }

   public void addPluginPaint(PluginPaint pluginPaint, Point point) {
      if (pluginPaint.id > 0) {
         PluginPaint plugin = pluginPaint.clone();
         plugin.isEnd = true;
         plugin.point = point;
         Me().pluginPaints.addElement(plugin);
      }

   }

   public void sortList(mVector list) {
   }

   public boolean isInWater(int x, int y) {
      int xx = x % Me().size;
      int yy = y % Me().size;
      if (xx < 10 && yy < 10 && Me().tileTypeAt(x, y, 64)) {
         return true;
      } else {
         xx = (x + this.size / 2) % Me().size;
         yy = y % Me().size;
         if (xx >= 10 && yy < 10 && Me().tileTypeAt(x, y, 128)) {
            return true;
         } else {
            xx = x % Me().size;
            yy = (y + this.size / 2) % Me().size;
            if (xx < 10 && yy >= 10 && Me().tileTypeAt(x, y, 256)) {
               return true;
            } else {
               xx = (x + this.size / 2) % Me().size;
               yy = (y + this.size / 2) % Me().size;
               return xx >= 10 && yy >= 10 && Me().tileTypeAt(x, y, 512);
            }
         }
      }
   }

   public boolean isInTile(int x, int y) {
      if (x > 0 && y > 0 && x < this.getWidth() && y < this.getHeight()) {
         int xx = x % Me().size;
         int yy = y % Me().size;
         if (xx < 10 && yy < 10) {
            if (Me().tileTypeAt(x, y, 4)) {
               return true;
            }
         } else if (xx >= 10 && yy < 10) {
            if (Me().tileTypeAt(x, y, 8)) {
               return true;
            }
         } else if (xx < 10 && yy >= 10) {
            if (Me().tileTypeAt(x, y, 16)) {
               return true;
            }
         } else if (xx >= 10 && yy >= 10 && Me().tileTypeAt(x, y, 32)) {
            return true;
         }

         return false;
      } else {
         return true;
      }
   }

   public boolean isCheckNotMove(int newX, int newY) {
      return false;
   }

   public boolean isInLink(int x, int y) {
      return false;
   }

   public boolean isInItemGame(int x, int y) {
      return false;
   }

   public boolean isInTree(int x, int y) {
      return false;
   }

   public boolean isInBridge(int x, int y) {
      return false;
   }

   public boolean isMoveInBridge(int x, int y) {
      return false;
   }

   public boolean isInNpc(int x, int y) {
      return false;
   }

   public mVector findStreet(int idCurrent, int idNeed) {
      return null;
   }
}
