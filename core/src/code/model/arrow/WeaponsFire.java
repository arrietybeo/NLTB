package code.model.arrow;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.Util;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class WeaponsFire extends IArrow {
   int va;
   int vx;
   int vy;
   int angle;
   int x;
   int y;
   int type;
   int dx;
   int dy;
   int xTo;
   int yTo;
   int himg;
   Actor live_To;
   int frame;
   int transform;
   int frameEff;
   int saveDir;
   boolean isStartEff;

   public WeaponsFire(Actor live_From, Actor live_To, int type) {
      this.type = type;
      this.live_To = live_To;
      this.x = live_From.x;
      this.y = live_From.y - 54;
      this.xTo = live_To.x;
      this.yTo = live_To.y;
      this.angle = Util.angle(this.x - live_To.x, live_To.y - (live_To.getHeight() >> 1) - this.y);
      this.va = 24;
      this.vx = this.va * Util.cos(this.angle) >> 10;
      this.vy = this.va * Util.sin(this.angle) >> 10;
      this.himg = live_To.getHeight();
      this.y = live_From.y - 31;
      if (live_From.getDir() == 2) {
         this.x = live_From.x - 30;
      } else if (live_From.getDir() == 3) {
         this.x = live_From.x + 30;
      }

      int d = Arrow.findDirIndexFromAngle(Util.angle(live_To.x - this.x, -(live_To.y + (live_To.getHeight() >> 1) - this.y)));
      this.frame = Arrow.FRAME[d];
      this.transform = Arrow.TRANSFORM[d];
   }

   public void onArrowTouchTarget() {
   }

   public void paint(mGraphics g) {
      if (!this.isStartEff) {
         if (Res.getImgArrow(this.type) != null && this.type != 6 && this.type != 7) {
            g.drawRegion(Res.imgArrow[this.type], 0, this.frame * Arrow.ARROWSIZE[1][this.type], Arrow.ARROWSIZE[0][this.type], Arrow.ARROWSIZE[1][this.type], this.transform, this.x, this.y, 3, false);
         }
      } else if (Res.imgEffect[38] != null) {
         g.drawRegion(Res.imgEffect[38], 0, this.frameEff * 27, 27, 27, 0, this.xTo, this.yTo, 33, false);
      }

   }

   public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
   }

   public void setAngle(int angle) {
   }

   public void update() {
      if (this.live_To != null) {
         this.xTo = this.live_To.x;
         this.yTo = this.live_To.y;
      }

      this.dx = this.xTo - this.x;
      this.dy = this.yTo - (this.himg >> 1) - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      this.vx = this.va * Util.cos(this.angle) >> 10;
      this.vy = this.va * Util.sin(this.angle) >> 10;
      this.x += this.vx;
      this.y += this.vy;
      boolean isMathX = false;
      boolean isMathY = false;
      if (!this.isStartEff) {
         if (this.type < 6) {
            EffectManager.addHiEffect(this.x, this.y, this.type == 3 ? 8 : 6);
         } else if (this.type == 6) {
            EffectManager.addHiEffect(this.x, this.y, 46);
         }
      }

      if (Res.isDestroy(this.x - 20, this.x + 20, this.xTo - this.himg / 2, this.xTo + this.himg / 2, this.y - 20, this.y + 20, this.yTo - this.himg / 2, this.yTo + this.himg / 2)) {
         if (!this.isStartEff) {
            if (this.type == 3) {
               this.creatMyEff(this.live_To, 0, 0, 18);
               this.creatMyEff(this.live_To, 1, 0, 24);
               this.creatMyEff(this.live_To, 2, 0, 28);
               this.creatMyEff(this.live_To, 3, 0, 32);
            } else if (this.type < 6) {
               EffectManager.addHiEffect(this.xTo, this.yTo - 10, 30);
               if (GameCanvas.gameScr.arrowsDown.contains(this)) {
                  GameCanvas.gameScr.arrowsDown.removeElement(this);
               }

               if (GameScr.arrowsUp.contains(this)) {
                  GameScr.arrowsUp.removeElement(this);
               }

               this.creatMyEff(this.live_To, 0, 1, 40);
               this.creatMyEff(this.live_To, 1, 1, 40);
               this.creatMyEff(this.live_To, 2, 1, 40);
               this.creatMyEff(this.live_To, 3, 1, 40);
            } else if (this.type == 6) {
               if (GameCanvas.gameScr.arrowsDown.contains(this)) {
                  GameCanvas.gameScr.arrowsDown.removeElement(this);
               }

               if (GameScr.arrowsUp.contains(this)) {
                  GameScr.arrowsUp.removeElement(this);
               }
            }
         }

         this.isStartEff = true;
      }

      if (this.isStartEff && GameCanvas.gameTick % 2 == 0) {
         ++this.frameEff;
         if (this.frameEff > 4) {
            if (GameCanvas.gameScr.arrowsDown.contains(this)) {
               GameCanvas.gameScr.arrowsDown.removeElement(this);
            }

            if (GameScr.arrowsUp.contains(this)) {
               GameScr.arrowsUp.removeElement(this);
            }

            this.isStartEff = false;
            this.frameEff = 0;
         }
      }

   }

   public void creatMyEff(Actor l_To, int dir, int type, int va) {
   }

   public void set(int type, int x, int y, int power, Actor owner, Actor target) {
   }

   public void set(int type, int x, int y, int power, Actor owner, Actor target, int eff) {
   }

   public void setIDHEAD(int idHead) {
   }
}
