package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Bua_T4 extends Effect {
   public short[] arr_radian = new short[]{0, 60, 120, 180, 240, 300};
   public int R;
   public int count;
   public int h = 0;
   public int frame;
   public int id;
   public int time = 5;
   int vh;
   boolean isFlydame;
   boolean isvang;

   public Skill_Bua_T4(short x, short y, Actor target) {
      this.target = target;
      this.x = target.x;
      this.y = target.y;
      this.R = 13;
      this.count = 0;
      this.id = 44;
      EffectSkill.createLowEfect(target.x + 5, target.y, 14);
      GameCanvas.gameScr.StartNewEffectEnd(1, target.x, target.y + 10);
      this.vh = 5;
      this.isFlydame = false;
      this.isvang = false;
   }

   public void paint(mGraphics g) {
      for(int i = 0; i < this.arr_radian.length; ++i) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawRegion(img, 0, 0, EffectSkill.getWidth(this.id), this.h, 0, Util.cos(this.arr_radian[i]) * this.R / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.R / 1024 + this.y, mGraphics.BOTTOM | mGraphics.LEFT, false);
         }
      }

   }

   public void update() {
      if (!this.isFlydame) {
         if (this.dame == 0) {
            this.isDame0 = true;
         }

         this.startFlyText(this.dame, 0, this.x, this.y - 10, 0, 0, this.Owner.x > this.target.x ? -1 : 1);
         this.isFlydame = true;
      }

      if (!this.isvang && this.Owner != null) {
         this.isvang = true;
         if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
         } else {
            this.target.jumpVang(this.Owner);
         }
      }

      if (this.time >= 0) {
         --this.time;
      }

      if (this.time <= 0) {
         if (this.h < EffectSkill.getHight(this.id) && this.count == 0) {
            this.h += this.vh;
         }

         if (this.h == EffectSkill.getHight(this.id)) {
            ++this.count;
         }

         if (this.vh < 15) {
            this.vh += 5;
         }

         if (this.h >= EffectSkill.getHight(this.id)) {
            this.h = EffectSkill.getHight(this.id);
         }

         if (this.count > 20) {
            this.h -= 2;
         }

         if (this.h <= 0) {
            this.wantDestroy = true;
         }

         for(int i = 0; i < this.arr_radian.length; ++i) {
            short[] var10000 = this.arr_radian;
            var10000[i] = (short)(var10000[i] + 36);
            if (this.arr_radian[i] > 360) {
               var10000 = this.arr_radian;
               var10000[i] = (short)(var10000[i] - 360);
            }
         }
      }

   }
}
