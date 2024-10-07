package code.effect;

import code.effect.new_skill.EffectSkill;
import code.model.Effect;
import code.model.Point;
import code.screen.Util;
import lib.mVector;

public class EffectEclip extends Effect {
   int vMax;
   public mVector VecEff = new mVector();
   int time;
   byte[] mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};

   public EffectEclip(int x, int y) {
      this.x = x;
      this.y = y;
      this.time = 7;
      this.create_Nova();
   }

   private void create_Nova() {
      this.vMax = 10;

      for(int i = 0; i < 16; ++i) {
         Point p = new Point();
         p.x = this.x * 1000;
         p.y = this.y * 1000;
         p.vx = 2 * Util.cos(225 * i / 10) * this.vMax;
         p.vy = Util.sin(225 * i / 10) * this.vMax;
         p.f = 0;
         this.VecEff.addElement(p);
      }

   }

   public int setFrameAngle(int goc) {
      byte fra;
      if (goc > 15 && goc <= 345) {
         int t = (goc - 15) / 15 + 1;
         if (t > 24) {
            t = 24;
         }

         fra = this.mpaintone_Bullet[t];
      } else {
         fra = 12;
      }

      return fra;
   }

   public void update() {
      this.update_Nova_Effect();
   }

   private void update_Nova_Effect() {
      if (this.time >= 0) {
         --this.time;
      }

      if (this.time <= 0) {
         this.wantDestroy = true;
      }

      for(int i = 0; i < this.VecEff.size(); ++i) {
         Point p = (Point)this.VecEff.elementAt(i);
         EffectSkill.createHiEfect(p.x / 1000, p.y / 1000, 23);
         p.x += p.vx;
         p.y += p.vy;
      }

   }
}
