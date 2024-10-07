package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Kiem_T6 extends Effect {
   public short[] arr_radian = new short[]{0, 90, 180, 270};
   public int count;
   public int h = 0;
   public int frame;
   public int id;
   Actor actor;

   public Skill_Kiem_T6(Actor ac) {
      this.actor = ac;
      this.x = this.actor.x - 10;
      this.y = this.actor.y + 10;
      this.count = 0;
      this.id = 34;
   }

   public void paint(mGraphics g) {
      for(int i = 0; i < this.arr_radian.length; ++i) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), this.h, 0, Util.cos(this.arr_radian[i]) * 25 / 1024 + this.x, Util.sin(this.arr_radian[i]) * 25 / 1024 + this.y, mGraphics.BOTTOM | mGraphics.LEFT, false);
         }
      }

   }

   public void update() {
      if (this.actor != null) {
         this.x = this.actor.x - 10;
         this.y = this.actor.y + 10;
      }

      if (this.h < EffectSkill.getHight(this.id) && this.count == 0) {
         this.h += 10;
      }

      if (this.h == EffectSkill.getHight(this.id)) {
         ++this.count;
      }

      if (this.count > 50) {
         this.h -= 5;
      }

      if (this.h <= 0) {
         this.wantDestroy = true;
      }

      for(int i = 0; i < this.arr_radian.length; ++i) {
         short[] var10000 = this.arr_radian;
         var10000[i] = (short)(var10000[i] + 15);
         if (this.arr_radian[i] > 360) {
            var10000 = this.arr_radian;
            var10000[i] = (short)(var10000[i] - 360);
         }
      }

   }
}
