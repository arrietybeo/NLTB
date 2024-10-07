package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Effect;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_Fall extends Effect {
   byte frame;
   byte count;
   byte[] arr_frame = new byte[]{0, 1, 2, 3};
   public int idimg;

   public Effect_Fall(int x, int y, int idimg) {
      this.x = x;
      this.y = y;
      this.idimg = idimg;
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void update() {
      this.frame = this.arr_frame[Math.abs(GameCanvas.r.nextInt() % this.arr_frame.length)];
      ++this.y;
      ++this.count;
      if (this.count > 5) {
         this.wantDestroy = true;
      }

   }
}
