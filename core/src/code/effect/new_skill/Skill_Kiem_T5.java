package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Kiem_T5 extends Effect {
   public short vy;
   public short vx;
   public int[] time = new int[]{15, 6, 9};
   public int tam;
   public int t;
   public int id;
   public short[] arr_radian = new short[]{0, 90, 180, 270};

   public Skill_Kiem_T5(short x, short y, int dame, Actor ac, Actor tg) {
      this.x = x;
      this.y = y - 100;
      this.tam = y;
      this.vy = 20;
      this.type = 11;
      this.t = this.time[Math.abs(GameCanvas.r.nextInt() % this.time.length)];
      EffectSkill.createLowEfect(x, y, 26);
      this.id = 34;
      this.dame = dame;
      this.Owner = ac;
      this.target = tg;
   }

   public void paint(mGraphics g) {
      for(int i = 0; i < this.arr_radian.length; ++i) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawImage(img, Util.cos(this.arr_radian[i]) * 15 / 1024 + this.x, Util.sin(this.arr_radian[i]) * 15 / 1024 + this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void update() {
      if (this.t >= 0) {
         --this.t;
      }

      for(int i = 0; i < this.arr_radian.length; ++i) {
         short[] var10000 = this.arr_radian;
         var10000[i] = (short)(var10000[i] + 36);
         if (this.arr_radian[i] > 360) {
            this.arr_radian[i] = 0;
         }
      }

      if (this.t < 0) {
         this.y += this.vy;
         this.vy = (short)(this.vy + 5);
      }

      if (this.y + this.vy > this.tam) {
         this.y = this.tam;
         this.wantDestroy = true;
         EffectManager.addHiDataeffectSkill(330, this.x, this.y, 1);
         EffectManager.addHiEffect(this.x, this.y, 115);
         if (this.dame > 0) {
            this.startFlyText(this.dame, 0, this.x, this.y, 0, 0, this.Owner.x > this.x ? -1 : 1);
         }

         if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
         }
      }

   }
}
