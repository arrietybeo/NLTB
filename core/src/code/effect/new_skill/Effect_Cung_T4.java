package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_Cung_T4 extends Effect {
   public int xto;
   public int yto;
   public int R;
   public int dx;
   public int dy;
   public int angle;
   public int nPosition;
   public int xO = 0;
   public int yO = 0;
   public int dem;
   public int radian;
   public int time;
   public int frame;
   public int idimg;
   public int effect;
   public int[] xw;
   public int[] yw;

   public Effect_Cung_T4(int x, int y, int xto, int yto, int time, int effect) {
      this.x = xto;
      this.y = yto;
      this.xto = x;
      this.time = time;
      this.yto = y;
      this.radian = 15;
      this.set();
      this.idimg = 18;
      this.effect = effect;
   }

   public Effect_Cung_T4(int x, int y, int xto, int yto, int time, int effect, Actor target, Actor owner) {
      this.x = xto;
      this.y = yto;
      this.xto = x;
      this.time = time;
      this.yto = y;
      this.radian = 15;
      this.set();
      this.idimg = 18;
      this.effect = effect;
      this.target = target;
      this.Owner = owner;
   }

   public void setDame(int dame) {
      super.setDame(dame);
   }

   public void set() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      this.nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 20;
      if (this.nPosition % 2 != 0) {
         ++this.nPosition;
      }

      if (this.nPosition < 2) {
         this.nPosition = 2;
      }

      this.R = (Math.abs(this.dx) + Math.abs(this.dy)) / 2;
      if (this.R > 90) {
         this.R -= 20;
      }

      if (this.R < 90 && this.R > 40) {
         this.R -= 13;
      }

      this.xw = new int[this.nPosition];
      this.yw = new int[this.nPosition];

      for(int i = 0; i < this.nPosition; ++i) {
         this.xw[i] = this.x + i * this.dx / this.nPosition;
         this.yw[i] = this.y + i * this.dy / this.nPosition;
      }

      if (this.nPosition >= 3) {
         this.xO = this.xw[this.nPosition / 2];
         this.yO = this.yw[this.nPosition / 2];
      } else {
         this.R = 20;
         this.xO = this.xw[this.nPosition / 2];
         this.yO = this.yw[this.nPosition / 2];
         this.R += 3;
         this.radian = 30;
      }

      if (this.angle <= 70 && this.angle >= 0 || this.angle > 270 && this.angle < 360) {
         this.radian *= -1;
      }

   }

   public void paint(mGraphics g) {
      if (this.time < 0) {
         Image img = EffectSkill.getImage(this.idimg);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xO + this.R * Util.cos(this.angle) / 1024, this.yO + this.R * Util.sin(this.angle) / 1024, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void update() {
      ++this.frame;
      if (this.frame >= EffectSkill.getFrame(this.idimg)) {
         this.frame = 0;
      }

      if (this.time >= 0) {
         --this.time;
      }

      if (this.time < 0) {
         if (this.dem <= 180) {
            this.dem += Math.abs(this.radian);
            this.angle += this.radian;
            if (this.angle <= 0) {
               this.angle = 360 - this.angle * -1;
            }

            if (this.angle > 360) {
               this.angle -= 360;
            }
         }

         if (this.dem > 180 && Math.abs(this.radian) <= 20) {
            this.wantDestroy = true;
            EffectSkill.createLowEfect(this.x, this.y, 1);
            EffectSkill.createLowEfect(this.x, this.y, 57);
            if (this.effect != 0) {
               Effect_no.createHiEffect(this.x, this.y, this.effect);
            }

            if (this.dame > 0) {
               this.startFlyText(this.dame, 0, this.x, this.y, 0, 0, this.Owner.x > this.target.x ? -1 : 1);
            }

            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            }
         }
      }

      if (this.dem > 130 && Math.abs(this.radian) > 20) {
         if (this.dame > 0) {
            this.startFlyText(this.dame, 0, this.x, this.y, 0, 0, this.Owner.x > this.target.x ? -1 : 1);
         }

         if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
         }

         this.wantDestroy = true;
         EffectSkill.createLowEfect(this.x, this.y, 1);
         EffectSkill.createLowEfect(this.x, this.y, 57);
         if (this.effect != 0) {
            Effect_no.createHiEffect(this.x, this.y, this.effect);
         }
      }

   }
}
