package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Effect;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class skill_dien_roi_Ps extends Effect {
   public int yto;
   public int frame = 0;
   public int vy;
   public int time;
   public int effect;
   public int id;
   public short[] t = new short[]{2, 4, 6, 8, 10};
   short t1;
   short count;
   boolean ispaint;

   public skill_dien_roi_Ps(int xto, int yto, int effect) {
      this.y = 0;
      this.yto = yto;
      this.x = xto;
      this.vy = 15 + this.t[Math.abs(GameCanvas.r.nextInt() % this.t.length)];
      this.effect = effect;
      this.id = 29;
      this.t1 = this.t[Math.abs(GameCanvas.r.nextInt() % this.t.length)];
   }

   public void paint(mGraphics g) {
      if (this.ispaint) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void update() {
      if (this.t1 >= 0) {
         --this.t1;
      }

      if (this.t1 <= 0) {
         this.ispaint = true;
         this.frame = (this.frame + 1) % 4;
         this.y += this.vy;
         if (this.y >= this.yto) {
            ++this.count;
            this.y = this.yto;
         }

         if (this.count > 5) {
            this.wantDestroy = true;
            EffectSkill.createLowEfect(this.x, this.y, this.effect);
         }
      }

   }
}
