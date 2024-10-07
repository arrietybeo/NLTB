package code.effect.new_skill;

import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Dao_T4 extends Effect {
   public int frame;
   int time;
   int vy;
   int xto;
   int yto;
   int id;
   byte f;
   public short R;
   public short count;
   boolean ispaint;

   public Skill_Dao_T4(int x, int y, int xto, int yto, int time, int dame) {
      this.x = x;
      this.y = y;
      this.time = time;
      this.vy = 40;
      this.xto = xto;
      this.yto = yto;
      this.id = 32;
      this.R = 20;
      this.dame = dame;
   }

   public void paint(mGraphics g) {
      if (!this.ispaint) {
         for(int i = 0; i < 360; i += 30) {
            Image img = EffectSkill.getImage(73);
            if (img != null) {
               g.drawRegion(img, 0, this.f * EffectSkill.getHight(73), EffectSkill.getWidth(73), EffectSkill.getHight(73), 0, Util.cos(i) * this.R / 1024 + this.x, Util.sin(i) * this.R / 1024 + this.y - 20, 3, false);
            }
         }
      }

      if (this.time < 0) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawRegion(img, 0, 0, EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void update() {
      ++this.f;
      if (this.f >= 3) {
         this.f = 0;
         this.R = (short)(this.R + 10);
         ++this.count;
      }

      if (this.count >= 3) {
         this.ispaint = true;
         this.R = (short)(this.R - 20);
         if (this.time >= -16) {
            --this.time;
         }

         if (this.time < 0) {
            this.y -= this.vy;
            ++this.vy;
         }

         if (this.time < -15) {
            this.wantDestroy = true;
            Effect_luaroi r = new Effect_luaroi(this.xto, this.yto, this.dame);
            EffectManager.addHiEffect(r);
         }
      }

   }
}
