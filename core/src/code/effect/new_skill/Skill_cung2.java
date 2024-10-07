package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_cung2 extends Effect {
   public static Image[] img;
   public int xto;
   public int yto;
   public int f;
   public int t;
   public int id;
   public int frame;
   private int[] xw;
   private int[] yw;
   public int[] time = new int[]{5, 10, 15, 8, 20, 18};
   public int dx;
   public int dy;
   public int angle;
   public int pos;

   public Skill_cung2(int xto, int yto, Actor target, Actor owner, int dame) {
      this.x = xto + 40;
      this.y = yto - 100;
      this.xto = xto;
      this.yto = yto;
      this.setspeed();
      this.id = 22;
      this.t = this.time[Math.abs(GameCanvas.r.nextInt() % this.time.length)];
      this.Owner = owner;
      this.dame = dame;
      this.target = target;
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

      for(int i = 0; i < nPosition; ++i) {
         this.xw[i] = this.x + i * this.dx / nPosition;
         this.yw[i] = this.y + i * this.dy / nPosition;
      }

   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.id);
      if (img != null) {
         g.drawRegion(img, 0, this.f * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void createsa0(int x, int y) {
      Effect_Sao_chop sc = new Effect_Sao_chop(this.xto, this.yto, 39);
      EffectManager.addLowEffect(sc);
   }

   public void update() {
      if (this.t >= 0) {
         --this.t;
      }

      if (GameCanvas.gameTick % 2 == 0 && this.t >= 0) {
         this.f = (this.f + 1) % EffectSkill.getFrame(this.id);
      }

      if (this.t < 0) {
         if (this.f == 0) {
            this.f = 2;
         }

         this.f = (this.f + 1) % EffectSkill.getFrame(this.id);
         if (this.pos < this.xw.length) {
            if (this.pos >= this.pos / 2) {
               this.pos += 2;
            } else {
               ++this.pos;
            }
         }

         if (this.pos >= this.xw.length) {
            this.pos = this.xw.length - 1;
            this.xw[this.pos] = this.xto;
            this.yw[this.pos] = this.yto;
            this.wantDestroy = true;
            EffectSkill.createLowEfect(this.xw[this.pos], this.yw[this.pos], 5);
            EffectSkill.createLowEfect(this.xw[this.pos], this.yw[this.pos], 8);
            Effect_dien e = new Effect_dien(this.xto, this.yto + EffectSkill.getHight(18) / 2, 61, 20);
            EffectManager.addLowEffect(e);
            if (this.dame > 0) {
               this.startFlyText(this.dame, 0, this.x, this.y, 0, 0, this.Owner.x > this.x ? -1 : 1);
            }

            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            }

            for(int i = 0; i < 3; ++i) {
               this.createsa0(this.xto, this.yto);
            }
         }
      }

   }
}
