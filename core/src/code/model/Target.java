package code.model;

import code.main.GameCanvas;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class Target {
   public short x;
   public short y;
   public byte index;
   int hImage;
   int wImage;

   public Target() {
      if (GameScr.imgPoint != null) {
         this.hImage = mGraphics.getImageHeight(GameScr.imgPoint) / 4;
         this.wImage = mGraphics.getImageWidth(GameScr.imgPoint);
      }

      this.index = 3;
   }

   public void update() {
      if (GameCanvas.gameTick % 3 == 0) {
         --this.index;
         if (this.index < 0) {
            this.index = 3;
         }
      }

   }

   public void paint(mGraphics g) {
      try {
         if (this.x == -1 || GameScr.mainChar.state != 1 || this.y == -1) {
            return;
         }

         g.drawRegion(GameScr.imgPoint, 0, this.hImage * this.index, this.wImage, this.hImage, 0, this.x * 16, this.y * 16, mGraphics.TOP | mGraphics.LEFT, false);
      } catch (Exception var3) {
      }

   }
}
