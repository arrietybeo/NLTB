package code.effect.new_skill;

import code.effect.EffectStarSkill;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.arrow.Arrow;
import code.screen.Util;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Cung_T1 extends Effect {
   public int angle;
   public int xto;
   public int yto;
   public int frame = 0;
   public int pos;
   public int thongsolathinh = 0;
   public int count;
   private int[] xw;
   private int[] yw;
   public int dx;
   public int dy;
   public int idimg;
   public int transform;
   public int[] arr = new int[]{8, -8, 5, -5, 0};
   public Actor target;
   public int[] arref = new int[]{1, 24};

   public Skill_Cung_T1(short x, short y, Actor target) {
      this.target = target;
      this.x = x;
      this.y = y;
      this.xto = target.x;
      this.yto = target.y;
      this.type = 4;
      this.setspeed();
      this.delay = 5;
      this.idimg = 71;
      EffectStarSkill eff = new EffectStarSkill(x, (short)(y - 10), (short)61, (short)1, (short)20);
      EffectManager.addLowEffect(eff);
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
      if (this.count <= 0) {
         Image img = EffectSkill.getImage(this.idimg);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), this.transform, this.xw[this.pos], this.yw[this.pos], 3, false);
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
            if (this.count <= 0) {
               this.target.settimevang((short)1);
               this.target.jum();
               EffectSkill.createHiEfect(this.xto, this.yto, 1);
            }

            ++this.count;
         }

         if (this.count % 2 != 0) {
            EffectSkill.createHiEfect(this.target.x + this.arr[Math.abs(GameCanvas.r.nextInt() % this.arr.length)], this.target.y + this.arr[Math.abs(GameCanvas.r.nextInt() % this.arr.length)], this.arref[Math.abs(GameScr.r.nextInt() % this.arref.length)]);
         }

         if (this.count > 16) {
            this.wantDestroy = true;
         }
      }

   }
}
