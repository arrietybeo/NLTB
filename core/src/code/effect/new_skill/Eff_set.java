package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.DataSkillEff;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.Util;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class Eff_set extends Effect {
   int[] radian = new int[]{0, 60, 120, 180, 240, 300};
   DataSkillEff data = new DataSkillEff(392);
   int x;
   int y;
   int R;

   public Eff_set(int x, int y) {
      this.x = x;
      this.y = y;
      this.R = 40;
   }

   public void paint(mGraphics g) {
      for(int i = 0; i < this.radian.length; ++i) {
         this.data.paintTop(g, Util.cos(this.radian[i]) * this.R / 1024 + this.x, Util.sin(this.radian[i]) * this.R / 1024 + this.y);
         this.data.paintBottomEff(g, Util.cos(this.radian[i]) * this.R / 1024 + this.x, Util.sin(this.radian[i]) * this.R / 1024 + this.y);
      }

   }

   public void update() {
      this.data.update();

      for(int i = 0; i < this.radian.length; ++i) {
         int[] var10000 = this.radian;
         var10000[i] += 3;
         if (this.radian[i] >= 360) {
            this.radian[i] -= 360;
         }

         if (GameCanvas.gameTick % 12 == 0) {
            EffectManager.addLowEffect(Util.cos(this.radian[i]) * this.R / 1024 + this.x, Util.sin(this.radian[i]) * this.R / 1024 + this.y, 14);
         }
      }

      if (GameCanvas.gameTick % 5 == 0) {
         GameScr.timeVibrateScreen = Res.random(1, 5);
      }

      this.R += 2;
      if (this.R > 140) {
         this.wantDestroy = true;
      }

   }
}
