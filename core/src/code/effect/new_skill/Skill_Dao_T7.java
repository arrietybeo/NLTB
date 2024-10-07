package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.Point;
import code.screen.Res;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mVector;

public class Skill_Dao_T7 extends Effect {
   int x1000;
   int y1000;
   int vX1000;
   int vY1000;
   int xEff;
   int yEff;
   int angle;
   int R;
   int size1;
   int xto;
   int yto;
   Actor target;
   mVector VecEff = new mVector();
   int timeDelay;
   boolean isdelay;
   boolean flydame;
   byte f;
   byte fRemove = 16;
   int toX;
   int toY;

   public Skill_Dao_T7(Actor target, int timedelay, int dame) {
      this.target = target;
      this.x = target.x;
      this.y = GameScr.cmy;
      this.xto = target.x;
      this.yto = target.y + 10;
      this.timeDelay = timedelay;
      this.isdelay = false;
      this.x1000 = this.xto;
      this.y1000 = this.yto;
      this.toX = target.x;
      this.toY = target.y;
      this.dame = dame;
   }

   public void paint(mGraphics g) {
      if (this.isdelay && this.f > 8) {
         int size = this.fRemove - this.f;
         if (size < 1) {
            size = 1;
         }

         for(int i = 0; i < size; ++i) {
            if (i == size - 1) {
               g.setColor(-16452075);
            } else {
               g.setColor(-1);
            }

            g.drawLine(this.x + i * this.vX1000, this.y + i * this.vY1000, this.x1000 + i * this.vX1000, this.y1000 + i * this.vY1000, false);
            g.drawLine(this.x - i * this.vX1000, this.y - i * this.vY1000, this.x1000 - i * this.vX1000, this.y1000 - i * this.vY1000, false);
         }
      }

   }

   public void update() {
      if (this.timeDelay >= 0) {
         --this.timeDelay;
      }

      if (this.timeDelay < 0) {
         this.isdelay = true;
         if (!this.flydame) {
            GameCanvas.gameScr.startFlyText("-" + this.dame, 2, this.target.x, this.target.y - 35, -1, -2);
            this.flydame = true;
            EffectManager.addHiEffect(this.target.x, this.target.y - 35, 13);
         }
      }

      if (this.isdelay) {
         this.updateLazer();
      }

   }

   public void updateLazer() {
      if (this.f == 5) {
         EffectSkill.createLowEfect(this.target.x, this.target.y, 55);
         EffectSkill.createLowEfect(this.target.x, this.target.y, 30);
      }

      if (this.f < this.fRemove) {
         if (this.f == 8) {
            this.vY1000 = 0;
            this.vX1000 = 1;
         }

         if (this.f > 8 && this.f % 3 == 2) {
            Point p = new Point();
            p.x = this.toX + Res.random_Am_0(5);
            p.y = this.toY + Res.random_Am_0(5);
            this.VecEff.addElement(p);
         }
      }

      for(int i = 0; i < this.VecEff.size(); ++i) {
         Point p = (Point)this.VecEff.elementAt(i);
         ++p.f;
         if (p.f >= 4) {
            this.VecEff.removeElement(p);
            --i;
         }
      }

      ++this.f;
      if (this.f > this.fRemove) {
         this.wantDestroy = true;
      }

   }
}
