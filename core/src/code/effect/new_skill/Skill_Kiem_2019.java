package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Kiem_2019 extends Effect {
   short count;
   short vh;
   short vam;

   public Skill_Kiem_2019(int x, int y, int delay) {
      this.x = x;
      this.y = y;
      this.delay = (short)delay;
      this.count = 0;
      this.vh = 5;
      this.vam = 2;
   }

   public void paint(mGraphics g) {
      if (this.delay < 0) {
         Image img = EffectSkill.getImage(131);
         if (img != null) {
            g.drawRegion(img, 0, 0, EffectSkill.getWidth(131), this.h, 0, this.x, this.y, mGraphics.BOTTOM | mGraphics.HCENTER, false);
         }

      }
   }

   public void update() {
      if (this.delay >= 0) {
         --this.delay;
      } else {
         if (this.delay == -1) {
            GameScr.timeVibrateScreen = Res.random(1, 5);
            EffectManager.addLowEffect(this.x, this.y, 14);
            GameCanvas.gameScr.StartNewEffectEnd(1, this.x, this.y + 10);
            GameCanvas.gameScr.StartNewEffectEnd(1, this.x + 10, this.y + 10);
            GameCanvas.gameScr.StartNewEffectEnd(1, this.x - 10, this.y + 10);
            EffectManager.addHiDataeffectSkill(57, this.x, this.y, Res.random(10) >= 5 ? -1 : 1);
            this.startFlyText(this.dame, 0, this.x, this.y, 0, 0, 1);
            this.delay = -2;
         }

         if (this.h < EffectSkill.getHight(131) && this.count == 0) {
            this.h += this.vh;
         }

         if (this.h == EffectSkill.getHight(131)) {
            ++this.count;
         }

         if (this.vh < 15) {
            this.vh = (short)(this.vh + 8);
         }

         if (this.h >= EffectSkill.getHight(131)) {
            this.h = EffectSkill.getHight(131);
         }

         if (this.count > 20) {
            this.h -= this.vam;
            ++this.vam;
         }

         if (this.h <= 0) {
            this.wantDestroy = true;
         }

      }
   }
}
