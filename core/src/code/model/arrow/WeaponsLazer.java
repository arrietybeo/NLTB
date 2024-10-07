package code.model.arrow;

import code.effect.new_skill.EffectSkill;
import code.model.Actor;
import code.model.Effect;
import code.screen.Util;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class WeaponsLazer extends Effect {
   int angle0;
   int lim;
   int x;
   int y;
   int xlim;
   int ylim;
   int xkc;
   int ykc;
   int power;
   int time;
   int maxlim;
   private Actor from;
   int[] color = new int[]{-35398, -8388747, -9066753, -65536};
   int idColor = 0;

   public WeaponsLazer(Actor from, Actor to, int xadd, int yadd, int power, int idColor) {
      this.x = from.x + xadd;
      this.y = from.y + yadd;
      this.xlim = to.x;
      this.ylim = to.y - 28;
      this.xkc = this.xlim - this.x;
      this.ykc = this.ylim - this.y;
      this.angle0 = Util.angle(this.xkc, this.ykc);
      this.lim = 6;
      this.power = power;
      this.idColor = idColor;
   }

   public void onArrowTouchTarget() {
   }

   public WeaponsLazer(int x, int y, int xTo, int yTo, Actor from, Actor acto) {
      this.x = x;
      this.y = y;
      this.xlim = xTo;
      this.ylim = yTo;
      this.xkc = this.xlim - x;
      this.ykc = this.ylim - y;
      this.angle0 = Util.angle(this.xkc, this.ykc);
      this.lim = 4;
      this.from = from;
      this.target = acto;
      EffectSkill.createHiEfect(this.xlim, this.ylim, 50);
      this.target.setMove2Target(from);
      this.type = 0;
   }

   public WeaponsLazer(Actor target, int idcolor) {
      this.x = target.x;
      this.y = GameScr.cmy - 5;
      this.xlim = target.x;
      this.ylim = target.y;
      this.xkc = this.xlim - this.x;
      this.ykc = this.ylim - this.y;
      this.angle0 = Util.angle(this.xkc, this.ykc);
      this.lim = 10;
      EffectSkill.createHiEfect(this.xlim, this.ylim, 50);
      target.setMove2Target(this.from);
      this.type = 0;
      this.idColor = idcolor;
      EffectSkill.createLowEfect(target.x, target.y, 55);
   }

   public WeaponsLazer(int x, int y, int xTo, int yTo, int time) {
      this.x = x;
      this.y = y;
      this.xlim = xTo;
      this.ylim = yTo;
      this.xkc = this.xlim - x;
      this.ykc = this.ylim - y;
      this.angle0 = Util.angle(this.xkc, this.ykc);
      this.lim = 4;
      this.time = time;
      this.type = 0;
   }

   public WeaponsLazer(int x, int y, int xTo, int yTo, int time, int type) {
      this.x = x;
      this.y = y;
      this.xlim = xTo;
      this.ylim = yTo;
      this.xkc = this.xlim - x;
      this.ykc = this.ylim - y;
      this.angle0 = Util.angle(this.xkc, this.ykc);
      this.lim = 4;
      this.time = time;
      this.type = (short)type;
      this.idColor = 1;
   }

   public WeaponsLazer(int x, int y, int xTo, int yTo, int time, int type, int size) {
      this.x = x;
      this.y = y;
      this.xlim = xTo;
      this.ylim = yTo;
      this.xkc = this.xlim - x;
      this.ykc = this.ylim - y;
      this.angle0 = Util.angle(this.xkc, this.ykc);
      this.lim = size;
      this.maxlim = this.lim;
      this.time = time;
      this.type = (short)type;
      this.idColor = 1;
   }

   public void paint(mGraphics g) {
      int i;
      if (this.angle0 > 60 && this.angle0 < 120 || this.angle0 > 240 && this.angle0 < 300) {
         g.setColor(this.color[this.idColor]);

         for(i = 0; i < this.lim; ++i) {
            g.drawLine(this.x + i, this.y, this.x + i + this.xkc, this.y + this.ykc, false);
            g.drawLine(this.x - i, this.y, this.x - i + this.xkc, this.y + this.ykc, false);
         }

         g.setColor(-1);

         for(i = 0; i < this.lim / 2; ++i) {
            g.drawLine(this.x + i, this.y, this.x + i + this.xkc, this.y + this.ykc, false);
            g.drawLine(this.x - i, this.y, this.x - i + this.xkc, this.y + this.ykc, false);
         }
      } else {
         g.setColor(this.color[this.idColor]);

         for(i = 0; i < this.lim; ++i) {
            g.drawLine(this.x, this.y + i, this.x + this.xkc, this.y + this.ykc + i, false);
            g.drawLine(this.x, this.y - i, this.x + this.xkc, this.y + this.ykc - i, false);
         }

         g.setColor(-1);

         for(i = 0; i < this.lim / 2; ++i) {
            g.drawLine(this.x, this.y + i, this.x + this.xkc, this.y + this.ykc + i, false);
            g.drawLine(this.x, this.y - i, this.x + this.xkc, this.y + this.ykc - i, false);
         }
      }

   }

   public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
   }

   public void setAngle(int angle) {
   }

   public void update2() {
      if (this.time >= 0) {
         --this.time;
      }

      if (this.time <= 0) {
         this.wantDestroy = true;
      }

      --this.lim;
      if (this.lim <= 0) {
         this.lim = this.maxlim;
      }

   }

   public void update() {
      if (this.type == 0) {
         --this.lim;
         if (this.lim <= 0) {
            this.wantDestroy = true;
         }
      }

      if (this.type == 1) {
         this.update2();
      }

   }
}
