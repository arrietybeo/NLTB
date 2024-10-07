package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Effect;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_Sao_chop extends Effect {
   public int vx;
   public int vy;
   public int t;
   public int y_changspeed;
   public int f;
   public int tt;
   public int frame;
   public int loai;
   public int id;
   public int count;
   int[] arry = new int[]{-10, 12, 0};
   public int[] time = new int[]{0, 1};
   short index = 0;

   public Effect_Sao_chop(int x, int y, int loai) {
      this.x = x;
      this.y = y;
      this.vx = GameCanvas.random(-1, 3);
      this.t = GameCanvas.random(10, 20);
      this.y_changspeed = y + this.arry[Math.abs(GameCanvas.r.nextInt() % this.arry.length)];
      this.tt = this.time[Math.abs(GameCanvas.r.nextInt() % this.time.length)];
      this.loai = loai;
      if (loai == 72) {
         this.t = 3;
      }

   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.loai);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.loai), EffectSkill.getWidth(this.loai), EffectSkill.getHight(this.loai), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void update() {
      if (this.loai != 72) {
         ++this.frame;
         if (this.frame >= EffectSkill.getFrame(this.loai) - 1) {
            this.frame = 0;
         }
      } else {
         this.frame = EffectSkill.frame[72][this.index];
         ++this.index;
         if (this.index >= EffectSkill.frame[72].length - 1) {
            this.index = 0;
            this.wantDestroy = true;
         }
      }

      if (this.tt >= 0) {
         --this.tt;
      }

      if (this.tt < 0) {
         this.x += this.vx;
         this.y += this.vy;
         if (this.vy < 10) {
            this.vy += 2;
         }

         if (this.y >= this.y_changspeed) {
            this.vy = -this.t;
            this.t /= 3;
         }

         if (this.t == 0) {
            ++this.count;
            this.vx = 0;
         }

         if (this.count > 10) {
            this.wantDestroy = true;
         }
      }

   }
}
