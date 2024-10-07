package code.effect.new_skill;

import code.effect.EffectStarSkill;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.arrow.Arrow;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Kiem_T1 extends Effect {
   int imginddex;
   int dx;
   int dy;
   int angle;
   int pos;
   int xto;
   int yto;
   int R;
   int frame;
   int transform;
   public int[] xw;
   public int[] yw;
   public int[] radian = new int[]{0, 180, 270};

   public Skill_Kiem_T1(short x, short y, Actor target) {
      this.target = target;
      this.x = x;
      this.y = y;
      this.xto = target.x;
      this.yto = target.y;
      this.imginddex = 48;
      this.setspeed();
      this.R = 10;
      this.delay = 8;
      EffectStarSkill eff = new EffectStarSkill(x, (short)(y - 25), (short)23, (short)3, (short)50);
      EffectManager.addLowEffect(eff);
      EffectSkill.createHiEfect(x, y - 25, 58);
   }

   public void setspeed() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 15;
      if (nPosition < 2) {
         nPosition = 2;
      }

      this.xw = new int[nPosition];
      this.yw = new int[nPosition];

      int d;
      for(d = 0; d < nPosition; ++d) {
         this.xw[d] = this.x + d * this.dx / nPosition;
         this.yw[d] = this.y + d * this.dy / nPosition;
      }

      d = Arrow.findDirIndexFromAngle(Util.angle(this.dx, -this.dy));
      this.frame = Arrow.FRAME[d];
      this.transform = Arrow.TRANSFORM[d];
   }

   public void paint(mGraphics g) {
      if (this.delay <= 0) {
         for(int i = 0; i < this.radian.length; ++i) {
            Image img = EffectSkill.getImage(this.imginddex);
            if (img != null) {
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.imginddex), EffectSkill.getWidth(this.imginddex), EffectSkill.getHight(this.imginddex), this.transform, Util.cos(this.radian[i]) * this.R / 1024 + this.xw[this.pos], Util.sin(this.radian[i]) * this.R / 1024 + this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
            }
         }
      }

   }

   public void update() {
      --this.delay;
      if (this.delay <= 0) {
         if (this.pos < this.xw.length) {
            ++this.pos;
         }

         if (this.pos >= this.xw.length) {
            this.pos = this.xw.length - 1;
            this.xw[this.pos] = this.xto;
            this.yw[this.pos] = this.yto;
            this.wantDestroy = true;
            EffectSkill.createLowEfect(this.xto, this.yto, 30);
            EffectSkill.createHiEfect(this.xto, this.yto, 36);
         }

         for(int i = 0; i < this.radian.length; ++i) {
            int[] var10000 = this.radian;
            var10000[i] += 45;
            if (this.radian[i] > 360) {
               var10000 = this.radian;
               var10000[i] -= 360;
            }
         }

         EffectSkill.createHiEfect(this.xw[this.pos], this.yw[this.pos], 23);
      }

   }
}
