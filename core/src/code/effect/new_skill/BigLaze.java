package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class BigLaze extends Effect {
   int y1000;
   int tick;
   int delay;
   boolean isadd;
   short frame1;
   public short[] radian = new short[]{0, 30, 90, 120, 180, 220, 270};

   public BigLaze(int x, int y, Actor target, int delay) {
      this.x = x;
      this.y = y + 4;
      this.target = target;
      this.delay = delay;
      EffectManager.addLowEffect(x, y, 126);
      GameScr.timeVibrateScreen = Res.random(1, 5);
   }

   public void paint(mGraphics g) {
      if (this.delay <= 0) {
         int valueplus = -4;
         int valueAction = GameCanvas.gameTick % 2 * 2;
         g.setColor(-54784);
         g.fillRect(this.x - 20 - valueplus, this.y - this.y1000, 40 + valueplus * 2, this.y1000, false);
         g.setColor(-31744);
         g.fillRect(this.x - 18 - valueplus, this.y - this.y1000, 36 + valueplus * 2, this.y1000, false);
         g.setColor(-12032);
         g.fillRect(this.x - 16 - valueplus, this.y - this.y1000, 32 + valueplus * 2, this.y1000, false);
         g.setColor(-31744);
         g.fillRect(this.x - 14 - valueplus + valueAction, this.y - this.y1000, 28 + valueplus * 2 - valueAction * 2, this.y1000, false);
         g.setColor(-12032);
         g.fillRect(this.x - 12 - valueplus + valueAction, this.y - this.y1000, 24 + valueplus * 2 - valueAction * 2, this.y1000, false);
         g.setColor(-131);
         g.fillRect(this.x - 10 - valueplus + valueAction, this.y - this.y1000, 20 + valueplus * 2 - valueAction * 2, this.y1000, false);
         Image img = EffectSkill.getImage(59);
         if (img != null) {
            g.drawRegion(img, 0, this.f * EffectSkill.getHight(59), EffectSkill.getWidth(59), EffectSkill.getHight(59), 0, this.x - 1, this.y - 17, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }

      }
   }

   public void update() {
      if (this.delay > 0) {
         --this.delay;
      } else {
         if (GameCanvas.gameTick % 2 == 0) {
            GameCanvas.gameScr.StartNewEffectEnd(2, Util.cos(this.radian[Utils.random(this.radian.length - 1)]) * Res.random_Am(-100, 100) / 1024 + this.x, Util.sin(this.radian[Utils.random(this.radian.length - 1)]) * Res.random_Am(-100, 100) / 1024 + this.y);
         }

         this.f = (this.f + 1) % 3;
         if (!this.isadd) {
            EffectManager.addLowEffect(this.x, this.y - 18, 126);

            for(int i = 0; i < this.radian.length; ++i) {
               GameCanvas.gameScr.startNewMagicBeam_New(21, this.target, Util.cos(this.radian[i]) * Res.random(80, 100) / 1024 + this.x, Util.sin(this.radian[i]) * Res.random(80, 100) / 1024 + this.y - 200, 0, -1);
            }

            this.isadd = true;
         }

         this.y1000 += 60;
         if (GameCanvas.gameTick % 3 == 0) {
            GameScr.timeVibrateScreen = Res.random(1, 5);
         }

         if (this.y1000 > 480) {
            this.y1000 = 480;
         }

         ++this.tick;
         if (this.tick > 25) {
            this.wantDestroy = true;
         }

      }
   }
}
