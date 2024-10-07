package code.effect;

import code.effect.new_skill.EffectSkill;
import code.model.Actor;
import code.model.Effect;
import code.screen.Util;

public class EffectStarSkill extends Effect {
   public short R;
   public short count;
   public short id;
   byte f;
   byte loop;
   public short[] radian1 = new short[]{0, 90, 180, 270};
   public short[] radian2 = new short[]{45, 135, 225, 315};
   Actor target;
   short[] posx = new short[]{-20, 0, 20};
   short[] posy = new short[]{0, 20, 0};
   short TT = 10;

   public EffectStarSkill(short x, short y, short id, short type, short r) {
      this.x = x;
      this.y = y;
      this.count = 0;
      this.R = r;
      this.id = id;
      this.type = type;
   }

   public void update() {
      short[] var10000;
      int i;
      switch(this.type) {
      case 0:
         for(i = 0; i < this.radian1.length; ++i) {
            EffectSkill.createHiEfect(Util.cos(this.radian1[i]) * this.R / 1024 + this.x, Util.sin(this.radian1[i]) * this.R / 1024 + this.y, this.id);
            var10000 = this.radian1;
            var10000[i] = (short)(var10000[i] + 15);
            if (this.radian1[i] >= 359) {
               this.radian1[i] = 0;
            }
         }

         this.R = (short)(this.R - 5);
         if (this.R <= 0) {
            this.wantDestroy = true;
         }

         return;
      case 1:
         for(i = 0; i < this.radian1.length; ++i) {
            EffectSkill.createHiEfect(Util.cos(this.radian1[i]) * this.R / 1024 + this.x, Util.sin(this.radian1[i]) * this.R / 1024 + this.y, this.id);
         }

         this.wantDestroy = true;
      case 2:
         for(i = 0; i < this.radian1.length; ++i) {
            EffectSkill.createHiEfect(Util.cos(this.radian1[i]) * this.R / 1024 + this.x, Util.sin(this.radian1[i]) * this.R / 1024 + this.y, this.id);
         }

         this.R = (short)(this.R - 8);
         if (this.R <= 0) {
            this.wantDestroy = true;
         }
      case 3:
         break;
      case 4:
         for(i = 0; i < this.radian1.length; ++i) {
            EffectSkill.createHiEfect(Util.cos(this.radian1[i]) * this.R / 1024 + this.x, Util.sin(this.radian1[i]) * this.R / 1024 + this.y, this.id);
            var10000 = this.radian1;
            var10000[i] = (short)(var10000[i] + 15);
            if (this.radian1[i] >= 359) {
               this.radian1[i] = 0;
            }
         }

         --this.TT;
         if (this.TT <= 0) {
            this.wantDestroy = true;
         }

         return;
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      default:
         return;
      }

      for(i = 0; i < this.radian2.length; ++i) {
         EffectSkill.createHiEfect(Util.cos(this.radian2[i]) * this.R / 1024 + this.x, Util.sin(this.radian2[i]) * this.R / 1024 + this.y, this.id);
      }

      this.R = (short)(this.R - 8);
      if (this.R <= 0) {
         this.wantDestroy = true;
      }

   }
}
