package code.model.arrow;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.EffectManager;
import code.screen.Util;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class WeaponsAngleBoss extends IArrow {
   public int type;
   public int x;
   public int y;
   public int angle;
   public int typesize;
   public int power;
   public int xbg;
   public int ybg;
   public int upPower;
   public int angleTo;
   public int[] FLIP = new int[]{0, 2, 1, 3, 7, 4};
   int timelive;
   int index;

   public WeaponsAngleBoss(int angle, int power, Actor acFr, Actor acTo, int type, int addw, int addh, int xbg, int ybg, boolean lastActo) {
      this.angle = angle;
      this.power = power;
      if (acFr != null) {
         this.xbg = acFr.x - addw;
         this.ybg = acFr.y - addh;
      } else {
         this.xbg = xbg;
         this.ybg = ybg;
      }

      this.type = type;
      this.angleTo = angle;
      this.timelive = 10;
   }

   public void onArrowTouchTarget() {
   }

   public void paint(mGraphics g) {
   }

   public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
   }

   public void setAngle(int angle) {
   }

   public void update() {
      this.x = this.xbg + Util.cos(this.angle) * this.power / 1024;
      this.y = this.ybg + Util.sin(this.angle) * this.power / 1024;
      if (this.type == 0) {
         this.angleTo += 30;
         if (this.angleTo > 360) {
            this.angleTo -= 360;
         }

         if (this.angle != this.angleTo) {
            this.angle += 10;
            if (this.angle > 360) {
               this.angle -= 360;
            }
         }

         if (this.upPower < 15) {
            this.upPower += 2;
         }

         EffectManager.addHiEffect(this.x, this.y, 21);
      } else {
         if (this.upPower < 24) {
            this.upPower += 8;
         }

         if (GameCanvas.gameTick % 4 == 0) {
            if (this.index < 1) {
               ++this.index;
            } else {
               this.index = 0;
            }
         }
      }

      --this.timelive;
      this.power += this.upPower;
      if (this.x < GameScr.cmx - GameCanvas.w / 2 || this.x > GameScr.cmx + GameCanvas.w + GameCanvas.w / 2 || this.y < GameScr.cmy - GameCanvas.h / 2 || this.y > GameScr.cmy + GameCanvas.h + GameCanvas.h / 2 || this.timelive <= 0) {
         if (GameCanvas.gameScr.arrowsDown.contains(this)) {
            GameCanvas.gameScr.arrowsDown.removeElement(this);
         }

         if (GameScr.arrowsUp.contains(this)) {
            GameScr.arrowsUp.removeElement(this);
         }
      }

   }

   public void set(int type, int x, int y, int power, Actor owner, Actor target) {
   }

   public void set(int type, int x, int y, int power, Actor owner, Actor target, int eff) {
   }

   public void setIDHEAD(int idHead) {
   }
}
