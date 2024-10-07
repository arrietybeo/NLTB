package code.effect.new_skill;

import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.model.arrow.Arrow;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Cung_T6 extends Effect {
   public int vy;
   public int y_target;
   public int vy_target;
   public int angle;
   public int time;
   public int dx;
   public int dy;
   public int pos = 0;
   public int effect;
   private int[] xw;
   private int[] yw;
   int frame;
   int xto;
   int yto;
   int idimg;
   int count;
   int transform;

   public Skill_Cung_T6(int x, int y, int xto, int yto, int time, int effect) {
      this.x = x;
      this.y = y;
      this.xto = xto;
      this.yto = yto;
      this.time = time;
      this.y_target = (short)(yto - 100);
      this.vy_target = 10;
      this.setspeed();
      this.idimg = 2;
      this.effect = effect;
   }

   public void setspeed() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 30;
      if (nPosition < 2) {
         nPosition = 2;
      }

      this.xw = new int[nPosition];
      this.yw = new int[nPosition];

      for(int i = 0; i < nPosition; ++i) {
         this.xw[i] = this.x + i * this.dx / nPosition;
         this.yw[i] = this.y + i * this.dy / nPosition;
         int d = Arrow.findDirIndexFromAngle(Util.angle(this.dx, -this.dy));
         this.frame = Arrow.FRAME[d];
         this.transform = Arrow.TRANSFORM[d];
      }

   }

   public void paint(mGraphics g) {
      if (this.time < 0) {
         Image img = EffectSkill.getImage(this.idimg);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), this.transform, this.xw[this.pos], this.yw[this.pos], 3, false);
         }
      }

   }

   public void update() {
      if (this.time >= 0) {
         --this.time;
      }

      if (this.time < 0) {
         ++this.pos;
         if (this.pos >= this.xw.length) {
            this.pos = this.xw.length - 1;
            this.wantDestroy = true;
            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            } else {
               this.target.jumpVang(this.Owner);
            }

            if (this.effect == -1) {
               if (this.effect == -1) {
                  Effect_dien ed = new Effect_dien(this.xto, this.yto, 24, 20);
                  EffectManager.addLowEffect(ed);
                  EffectSkill.createLowEfect(this.xto, this.yto, 56);
               } else {
                  EffectSkill.createLowEfect(this.xto, this.yto, this.effect);
               }
            }
         }
      }

   }
}
