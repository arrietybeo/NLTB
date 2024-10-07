package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Boss_Noel_1 extends Effect {
   int yto;
   int vy;
   int f;
   int xFrom;
   int idimg;
   int delay;

   public Skill_Boss_Noel_1(Actor target, int dame, int xFrom) {
      this.x = target.x;
      this.y = target.y - 170;
      this.yto = target.y;
      this.dame = dame;
      this.vy = 5;
      this.target = target;
      this.xFrom = xFrom;
      this.idimg = 120;
      this.delay = Res.random(5);
   }

   public Skill_Boss_Noel_1(Actor target, int dame) {
      this.x = target.x;
      this.y = GameScr.cmy - 5;
      this.yto = target.y;
      this.dame = dame;
      this.vy = 5;
      this.target = target;
      this.xFrom = target.x;
      this.idimg = 47;
      this.delay = Res.random(5);
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null && this.delay <= 0) {
         g.drawRegion(img, 0, this.f * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void update() {
      if (this.delay > 0) {
         --this.delay;
      } else {
         this.y += this.vy;
         this.vy += 4;
         if (this.y + this.vy >= this.yto) {
            this.y = this.yto;
         }

         if (this.y >= this.yto) {
            if (this.idimg == 120) {
               GameCanvas.gameScr.StartNewEffectEnd(3, this.target.x, this.target.y + 10);
               EffectManager.addLowEffect(this.x, this.yto, 14);
               EffectManager.addHiEffect(this.x, this.yto, 54);
               EffectManager.addLowEffect(this.x, this.yto - 10, 120);
               GameScr.timeVibrateScreen = Res.random(1, 5);
               this.startFlyText(this.dame, 0, this.target.x, this.target.y, 0, 0, this.target.x > this.xFrom ? -1 : 1);
            }

            if (this.idimg == 47) {
               GameCanvas.gameScr.StartNewEffectEnd(2, this.target.x, this.target.y + 10);
               EffectManager.addLowEffect(this.x, this.yto, 55);
               EffectManager.addHiEffect(this.x, this.yto, 50);
               GameScr.timeVibrateScreen = Res.random(1, 5);
               GameCanvas.gameScr.startFlyText("- " + this.dame, 2, this.x, this.y, 1, -2, 1);
            }

            this.wantDestroy = true;
         }

      }
   }
}
