package code.model;

import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class CharPartInfo {
   public int type;
   public int id;
   public int timeRemove = 0;
   public static int[][][] x;
   public static int[][][] y;
   public static int[][][] w;
   public static int[][][] h;
   public static int[][][] dx;
   public static int[][][] dy;
   public Image image;
   long timePaint;
   public int time;
   public static final byte[][] PART_OF_FRAME = new byte[][]{{0, 0, 1, 2, 3, 4}, {0, 0, 1, 2, 3, 4}, new byte[6], new byte[6], {0, 1, 0, 1, 0, 1}};
   static int[][] xhd = new int[][]{{1, 1, 0}, {1, 1, 0}};
   static int[][] yhd = new int[][]{{3, 2, 2}, {3, 2, 2}};
   static int wFrame = 17;
   static int[] indexHeadFrame = new int[]{0, 2, 1};

   public static void loadDataCharPart() {
   }

   public void load(byte[] arr, int type, int Id) {
   }

   public void loadCoat(int type, int id) {
   }

   public void load(int type, int id) {
   }

   public CharPartInfo(int type, int id) {
      this.type = (short)type;
      this.id = (short)id;
   }

   public void paint(mGraphics g, int xp, int yp, int dir, int frame) {
      if (this.type >= 0) {
         byte rota = 0;
         int DIR = dir;
         if (dir > 2) {
            rota = 2;
            DIR = 2;
         }

         if (this.image != null && (this.type == 1 || this.type == 2 || this.type == 0)) {
            if (this.type != 0) {
               g.drawRegion(this.image, x[this.type][DIR][PART_OF_FRAME[this.type][frame]], y[this.type][DIR][PART_OF_FRAME[this.type][frame]], w[this.type][DIR][PART_OF_FRAME[this.type][frame]], h[this.type][DIR][PART_OF_FRAME[this.type][frame]], rota, xp + (dir != 3 ? dx[this.type][DIR][frame] : -(dx[this.type][DIR][frame] + w[this.type][DIR][PART_OF_FRAME[this.type][frame]])), yp + dy[this.type][DIR][frame], 0, false);
            } else {
               g.drawRegion(this.image, indexHeadFrame[DIR] * wFrame, 0, wFrame, this.image.getHeight(), rota, xp + (dir != 3 ? dx[this.type][DIR][frame] + xhd[0][DIR] : -(dx[this.type][DIR][frame] + xhd[0][DIR] + wFrame)), yp + dy[this.type][DIR][frame] + yhd[0][DIR], 0, false);
            }
         }

         this.timePaint = System.currentTimeMillis();
      }
   }

   public void paintStatic(mGraphics g, short xp, short yp, int dir, int frame) {
      if (this.image != null) {
         g.drawRegion(this.image, x[this.type][dir][PART_OF_FRAME[this.type][frame]], y[this.type][dir][PART_OF_FRAME[this.type][frame]], w[this.type][dir][PART_OF_FRAME[this.type][frame]], h[this.type][dir][PART_OF_FRAME[this.type][frame]], 0, xp, yp, 3, false);
      }

   }

   public void paintAvatar(mGraphics g, short xp, short yp, int frame) {
   }

   public void paintImage(mGraphics g, int x, int y) {
   }
}
