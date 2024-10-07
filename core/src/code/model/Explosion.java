package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class Explosion extends Actor {
   int f = 0;
   int p1;

   public Explosion(short x, short y) {
      this.catagory = 126;
      this.x = x;
      this.y = y;
      this.p1 = 0;
   }

   public boolean isPaint() {
      if (this.x < GameScr.cmx) {
         return false;
      } else if (this.x > GameScr.cmx + GameCanvas.w) {
         return false;
      } else if (this.y < GameScr.cmy) {
         return false;
      } else {
         return this.y <= GameScr.cmy + GameCanvas.h + 30;
      }
   }

   public void paint(mGraphics g) {
      if (this.isPaint()) {
         g.drawRegion(Res.imgExplosion, 0, this.f * 24, 24, 24, 0, this.x - 12, this.y - 24, 0, false);
      }
   }

   public void setPosTo(short x, short y) {
      this.x = x;
      this.y = y;
   }

   public void update() {
      ++this.p1;
      if (this.p1 > 8) {
         this.p1 = 0;
         this.wantDestroy = true;
      }

      this.f = this.p1 >> 1;
   }
}
