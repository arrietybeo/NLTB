package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.screen.Res;
import code.screen.Util;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Bua_T6 extends Effect {
   public int angle;
   public int xto;
   public int yto;
   public int frame = 0;
   public int pos;
   public int idimg;
   private int[] xw;
   private int[] yw;
   public int dx;
   public int dy;
   public int t;
   public int[] radian = new int[]{0, 45, 90, 135, 180, 225, 270, 315};
   public int[] radian2 = new int[]{0, 90, 180, 270};
   public int[] f = new int[]{0, 1, 2, 3};
   short[] posx = new short[]{-20, 0, 20};
   short[] posy = new short[]{0, 20, 0};

   public Skill_Bua_T6(short x, short y, Actor target) {
      this.target = target;
      this.x = x;
      this.y = y - 40;
      this.xto = target.x;
      this.yto = target.y;
      this.setspeed();
      this.t = 10;
      this.idimg = 47;
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void setspeed() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 35;
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
      this.target.setState(0);
      ++this.frame;
      if (this.frame >= EffectSkill.getFrame(this.idimg)) {
         this.frame = 0;
      }

      if (this.t >= 0) {
         --this.t;
      }

      if (this.t < 0) {
         if (this.pos < this.xw.length) {
            ++this.pos;
         }

         if (this.pos >= this.xw.length) {
            this.pos = this.xw.length - 1;
            this.xw[this.pos] = this.xto;
            this.yw[this.pos] = this.yto;
            this.wantDestroy = true;
            GameCanvas.gameScr.StartNewEffectEnd(2, this.xto, this.yto);
            GameScr.timeVibrateScreen = Res.random(1, 5);
            EffectSkill.createLowEfect(this.xto, this.yto - 5, 68);
            EffectSkill.createLowEfect(this.xto, this.yto, 69);
         }
      }

   }
}
