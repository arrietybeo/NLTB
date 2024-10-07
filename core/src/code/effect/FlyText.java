package code.effect;

import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mSystem;
import lib2.mFont;

public class FlyText {
   int x;
   int y;
   int vy;
   int vx;
   int indexcolor;
   public static final byte COLOR_WHITE = 0;
   public static final byte COLOR_RED = 1;
   public static final byte COLOR_YELLOW = 2;
   public static final byte COLOR_GREEN = 3;
   public static final byte COLOR_ORANGE = 4;
   public static final byte COLOR_BLUE = 5;
   public static final byte COLOR_BLUE_BIG = 6;
   public static final byte COLOR_GREEN_BIG = 7;
   public static final byte CHI_MANG = 8;
   public static final byte NE = 9;
   public static final byte PHAN_DON = 10;
   public static final byte XUYEN_GIAP = 11;
   public boolean wantDestroy;
   String text;
   int fRemove;
   int f;
   int va;
   int rm;
   int yremove;
   public boolean isFont3d;

   public FlyText(String t, int x, int y, int colorText, boolean isFont3d, int fremove) {
      this.x = x;
      this.text = t;
      this.indexcolor = colorText;
      this.fRemove = fremove;
      this.y = y;
      this.isFont3d = isFont3d;
      this.yremove = y - fremove - 30;
      this.rm = 8;
      this.va = 2;
   }

   public void paint(mGraphics g) {
      switch(this.indexcolor) {
      case 0:
         if (this.isFont3d) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_white);
         } else {
            mFont.tahoma_7_white.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 1:
         if (this.isFont3d) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_red);
         } else {
            mFont.tahoma_7_red.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 2:
         if (this.isFont3d) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_yellow);
         } else {
            mFont.tahoma_7_yellow.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 3:
         if (this.isFont3d) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_green);
         } else {
            mFont.tahoma_7_green.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 4:
         if (this.isFont3d) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_orange);
         } else {
            mFont.tahoma_7_orange.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 5:
         if (this.isFont3d) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_blue);
         } else {
            mFont.tahoma_7_blue.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 6:
         if (mSystem.isAndroid) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_blue);
         } else {
            FontTeam.number_Blue.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 7:
         if (mSystem.isAndroid) {
            GameScr.Font3d(g, this.text, this.x, this.y, 2, mFont.tahoma_7b_green);
         } else {
            FontTeam.number_Green.drawString(g, this.text, this.x, this.y, 2, false);
         }
         break;
      case 8:
         g.drawImage(GameScr.imgFly[0], this.x, this.y, 3, false);
         break;
      case 9:
         g.drawImage(GameScr.imgFly[1], this.x, this.y, 3, false);
         break;
      case 10:
         g.drawImage(GameScr.imgFly[2], this.x, this.y, 3, false);
         break;
      case 11:
         g.drawImage(GameScr.imgFly[3], this.x, this.y, 3, false);
      }

   }

   public void update() {
      this.va += 2;
      this.y -= this.va;
      if (this.y - this.va <= this.yremove) {
         this.y = this.yremove;
         --this.rm;
      }

      if (this.rm <= 0) {
         this.wantDestroy = true;
      }

   }
}
