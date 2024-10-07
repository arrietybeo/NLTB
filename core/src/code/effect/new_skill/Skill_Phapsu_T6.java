package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Phapsu_T6 extends Effect {
   public int posx;
   public int posy;
   public int xto;
   public int yto;
   public int dx;
   public int dy;
   public int vx;
   public int vy;
   public int angle;
   public int time;
   public int pos = 0;
   public int frame;
   public int frame1;
   public int id1;
   public int id2;
   private int[] xw;
   private int[] yw;

   public Skill_Phapsu_T6(short x, short y, Actor target) {
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.x = x;
      this.y = y;
      this.time = 1;
      this.setspeed();
      this.id1 = 37;
      this.id2 = 53;
      EffectSkill.createLowEfect(x, y, 54);
   }

   public void setspeed() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 15;
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

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.id1);
      if (img != null) {
         g.drawRegion(img, 0, this.frame1 * EffectSkill.getHight(this.id1), EffectSkill.getWidth(this.id1), EffectSkill.getHight(this.id1), 0, this.x, this.y - 40, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

      Image img2 = EffectSkill.getImage(this.id2);
      if (img2 != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id2), EffectSkill.getWidth(this.id2), EffectSkill.getHight(this.id2), 0, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void update() {
      this.frame = (this.frame + 1) % 4;
      this.frame1 = (this.frame1 + 1) % 3;
      if (this.pos < this.xw.length) {
         ++this.pos;
      }

      if (this.pos >= this.xw.length) {
         this.pos = this.xw.length - 1;
         this.wantDestroy = true;
         this.xw[this.pos] = this.xto;
         this.yw[this.pos] = this.yto;
         EffectSkill.createHiEfect(this.xw[this.pos], this.yw[this.pos] - 10, 51);

         for(int i = 0; i < 3; ++i) {
            Effect_Sao_chop ef = new Effect_Sao_chop(this.xw[this.pos], this.yw[this.pos], 72);
            EffectManager.addHiEffect(ef);
         }
      }

      if (GameCanvas.gameTick % 3 == 0) {
         EffectSkill.createLowEfect(this.xw[this.pos], this.yw[this.pos] - EffectSkill.getHight(7) / 2, 7);
      }

   }
}
