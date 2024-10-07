package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Phapsu_T2 extends Effect {
   public int angle;
   public int xto;
   public int yto;
   public int frame = 0;
   public int pos;
   public int thongsolathinh = 0;
   private int[] xw;
   private int[] yw;
   public int dx;
   public int dy;
   public int idimg;

   public Skill_Phapsu_T2(short x, short y, Actor target) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.type = 4;
      this.setspeed();
      this.idimg = 62;
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

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void createEfect(int x, int y, int type) {
      EffectSkill u = new EffectSkill(x, y, type, 4);
      EffectManager.addLowEffect(u);
   }

   public void update() {
      ++this.frame;
      if (this.frame >= EffectSkill.getFrame(this.idimg)) {
         this.frame = 0;
      }

      if (this.pos < this.xw.length) {
         ++this.pos;
      }

      if (this.pos >= this.xw.length) {
         this.pos = this.xw.length - 1;
         this.xw[this.pos] = this.xto;
         this.yw[this.pos] = this.yto;
         this.wantDestroy = true;
         EffectSkill.createLowEfect(this.xto, this.yto, 53);
         EffectSkill.createLowEfect(this.xto, this.yto, 54);
      }

   }
}
