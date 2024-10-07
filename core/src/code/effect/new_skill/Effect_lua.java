package code.effect.new_skill;

import code.model.Effect;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_lua extends Effect {
   public int radian;
   public int time;
   public int R;
   public int vx;
   public int vy;
   public int dx;
   public int dy;
   public int angle;
   public int pos;
   public int xto;
   public int yto;
   public int idimg;
   public int frame;
   private int[] xw;
   private int[] yw;
   boolean fight;

   public Effect_lua(int x, int y, int xto, int yto, int radian) {
      this.x = x;
      this.radian = radian;
      this.y = y;
      this.R = 30;
      this.time = 10;
      this.xto = xto;
      this.yto = yto;
      this.setspeed();
      this.idimg = 50;
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, Util.cos(this.radian) * this.R / 1024 + this.xw[this.pos], Util.sin(this.radian) * this.R / 1024 + this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void setspeed() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 20;
      if (nPosition < 2) {
         nPosition = 2;
      }

      this.xw = new int[nPosition];
      this.yw = new int[nPosition];

      for(int i = 0; i < nPosition; ++i) {
         this.xw[i] = this.x + i * this.dx / nPosition;
         this.yw[i] = this.y + i * this.dy / nPosition;
      }

   }

   public void update() {
      if (!this.fight) {
         this.radian += 36;
      }

      if (this.radian > 360) {
         this.radian -= 360;
      }

      if (this.time >= 0) {
         --this.time;
      }

      if (this.time < 0 && this.radian >= this.angle - 36 && this.radian <= this.angle + 36) {
         this.fight = true;
         this.radian = this.angle;
         if (this.pos < this.xw.length) {
            ++this.pos;
         }

         if (this.pos >= this.xw.length) {
            this.pos = this.xw.length - 1;
            this.xw[this.pos] = this.xto;
            this.yw[this.pos] = this.yto;
            this.wantDestroy = true;
            EffectSkill.createHiEfect(this.xto, this.yto, 21);
         }
      }

   }
}
