package code.effect.new_skill;

import code.model.Effect;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_dien extends Effect {
   public int idimg;
   public int frame;
   public int R;
   public int va;
   public int angle;
   public int r;
   public int[] arr_R = new int[]{9, 4, 3, 1, 3, 4, 9, 4, 3, 1, 3, 4};
   public int[] arr_radian = new int[]{0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};

   public Effect_dien(int x, int y, int idimg, int r) {
      this.x = x;
      this.y = y;
      this.va = 1;
      this.idimg = idimg;
      this.R = 10;
      this.r = r;
   }

   public void paint(mGraphics g) {
      if (this.idimg == 11) {
         for(int i = 0; i < this.arr_radian.length; ++i) {
            Image img = EffectSkill.getImage(this.idimg);
            if (img != null) {
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
            }
         }
      }

   }

   public void update() {
      ++this.frame;
      if (this.frame >= EffectSkill.getFrame(this.idimg) - 1) {
         this.frame = 0;
      }

      int[] var10000;
      int i;
      switch(this.idimg) {
      case 6:
         for(i = 0; i < this.arr_R.length; ++i) {
            var10000 = this.arr_R;
            var10000[i] += this.r / 2;
            EffectSkill.createLowEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, 64);
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 11:
         for(i = 0; i < this.arr_R.length; ++i) {
            var10000 = this.arr_R;
            var10000[i] += this.r / 2;
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 24:
         for(i = 0; i < this.arr_R.length; ++i) {
            if (i % 3 != 0) {
               var10000 = this.arr_R;
               var10000[i] += this.r / 2;
               EffectSkill.createLowEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, this.idimg);
            }
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 28:
         for(i = 0; i < this.arr_R.length; ++i) {
            var10000 = this.arr_R;
            var10000[i] += this.r / 2;
            EffectSkill.createHiEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, 28);
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 50:
         for(i = 0; i < this.arr_R.length; ++i) {
            if (i % 3 != 0) {
               var10000 = this.arr_R;
               var10000[i] += this.r / 2;
               EffectSkill.createLowEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, this.idimg);
            }
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 53:
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 54:
         for(i = 0; i < this.arr_R.length; ++i) {
            if (i % 3 == 0) {
               var10000 = this.arr_R;
               var10000[i] += this.r / 2;
               EffectSkill.createLowEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, this.idimg);
            }
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 58:
         for(i = 0; i < this.arr_R.length; ++i) {
            var10000 = this.arr_R;
            var10000[i] += this.r / 2;
            EffectSkill.createLowEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, 57);
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
         break;
      case 61:
         for(i = 0; i < this.arr_R.length; ++i) {
            var10000 = this.arr_R;
            var10000[i] += this.r / 2;
            if (i % 2 == 0) {
               EffectSkill.createLowEfect(Util.cos(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.x, Util.sin(this.arr_radian[i]) * this.arr_R[i] / 1024 + this.y, this.idimg);
            }
         }

         this.r /= 2;
         if (this.r == 0) {
            this.wantDestroy = true;
         }
      }

   }
}
