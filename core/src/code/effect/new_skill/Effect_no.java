package code.effect.new_skill;

import code.model.Effect;
import code.model.EffectManager;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_no extends Effect {
   public int idimg;
   public int frame = 0;

   public Effect_no(int x, int y, int idimg) {
      this.x = x;
      this.y = y;
      this.idimg = idimg;
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null) {
         g.drawRegion(img, 0, EffectSkill.frame[this.idimg][this.frame] * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public static void createlowEffect(int x, int y, int id) {
      Effect_no s = new Effect_no(x, y, id);
      EffectManager.addLowEffect(s);
   }

   public static void createHiEffect(int x, int y, int id) {
      Effect_no s = new Effect_no(x, y, id);
      EffectManager.addHiEffect(s);
   }

   public void update() {
      ++this.frame;
      this.y -= 5;
      if (this.frame >= EffectSkill.frame[this.idimg].length - 1) {
         this.wantDestroy = true;
      }

   }
}
