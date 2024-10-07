package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Effect;
import code.model.EffectManager;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Effect_luaroi extends Effect {
   public int vy;
   public int xto;
   public int yto;
   public int tung;
   public int count;
   public int id;
   public boolean b;
   private byte frame;

   public Effect_luaroi(int xto, int yto, int dame) {
      this.x = xto;
      this.y = 0;
      this.vy = 20;
      this.xto = xto;
      this.yto = yto;
      this.type = 3;
      this.tung = 10;
      this.id = 32;
      this.dame = dame;
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.id);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void update() {
      ++this.frame;
      if (this.frame > 2) {
         this.frame = 0;
      }

      if (this.vy < 30) {
         this.vy += 2;
      }

      this.y += this.vy;
      if (!this.b) {
         EffectSkill.createLowEfect(this.x, this.y - 10, 10);
         EffectSkill.createLowEfect(this.x, this.y - 10, 49);
      }

      if (this.y >= this.yto) {
         this.y = this.yto;
         this.wantDestroy = true;
         GameCanvas.gameScr.startFlyText("-" + this.dame, 2, this.xto, this.yto - 35, -1, -2);
         EffectManager.addHiDataeffectSkill(112, this.xto, this.yto, 1);
         EffectSkill.createLowEfect(this.xto, this.yto, 30);
      }

   }
}
