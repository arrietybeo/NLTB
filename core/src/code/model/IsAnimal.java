package code.model;

import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class IsAnimal {
   public static byte[][] frameRun = new byte[][]{new byte[1], new byte[1], new byte[1], new byte[1]};
   public static byte[][] frameStand = new byte[][]{new byte[1], new byte[1], new byte[1], new byte[1]};
   public static byte[][] frameAt = new byte[][]{new byte[1], new byte[1], new byte[1], new byte[1]};
   public static Image img;
   public static int wimg;
   public static int hImg;
   public static int addx;
   public static int addy;
   public int status;
   public int idFrame;
   public int p1;
   public int dir;
   public int type;

   public void paint(mGraphics g, int xx, int yy) {
      if (img != null) {
         g.drawRegion(img, 0, this.idFrame * hImg, wimg, hImg, 0, xx + addx, yy + addy, 33, false);
      }
   }

   public void update() {
      switch(this.status) {
      case 0:
         ++this.p1;
         if (this.p1 > frameStand[this.dir].length - 1) {
            this.p1 = 0;
         }

         this.idFrame = frameStand[this.dir][this.p1];
         break;
      case 1:
         ++this.p1;
         if (this.p1 > frameRun[this.dir].length - 1) {
            this.p1 = 0;
         }

         this.idFrame = frameRun[this.dir][this.p1];
         break;
      case 2:
         ++this.p1;
         if (this.p1 > frameAt[this.dir].length - 1) {
            this.p1 = frameAt[this.dir].length - 1;
         }

         this.idFrame = frameAt[this.dir][this.p1];
      }

   }
}
