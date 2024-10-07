package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_TrucLam_2019 extends Effect {
   int vy;
   int delay;
   int yto;

   public Skill_TrucLam_2019(int x, int y, int delay) {
      this.x = x;
      this.yto = y;
      this.y = y - 250;
      this.type = 129;
      this.delay = delay;
      this.vy = 10;
   }

   public void paint(mGraphics g) {
      if (this.delay <= 0) {
         Image img = EffectSkill.getImage(this.type);
         if (img != null) {
            g.drawRegion(img, 0, this.f * EffectSkill.getHight(this.type), EffectSkill.getWidth(this.type), EffectSkill.getHight(this.type), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }

      }
   }

   public void update() {
      if (this.delay >= 0) {
         --this.delay;
      } else {
         ++this.f;
         if (this.f > 2) {
            this.f = 0;
         }

         this.y += this.vy;
         ++this.vy;
         if (this.y + this.vy >= this.yto) {
            this.y = this.yto;
            GameScr.timeVibrateScreen = Res.random(1, 5);
            EffectManager.addHiEffect(this.x, this.y, 32);
            GameCanvas.gameScr.StartNewEffectEnd_Low(4, this.x, this.y);
            EffectManager.addHiEffect(this.x, this.y, 32);
            this.wantDestroy = true;
         }

      }
   }
}
