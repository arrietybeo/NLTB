package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;
import lib.mVector;

public class Skill_Phapsu_T7 extends Effect {
   mVector mst = new mVector();

   public void settarget(mVector mst) {
      this.mst = mst;
   }

   public void update() {
      for(int i = 0; i < this.mst.size(); ++i) {
         Actor ac = (Actor)this.mst.elementAt(i);

         for(int a = 0; a < 6; ++a) {
            Skill_Phapsu_T7.Skill_Dua_4 skill = new Skill_Phapsu_T7.Skill_Dua_4(ac.x - 25 + Res.rnd(50), ac.y - 25 + Res.rnd(50));
            EffectManager.hiEffects.addElement(skill);
         }
      }

      this.wantDestroy = true;
   }

   class Skill_Dua_4 extends Effect {
      int angle = 0;
      int xGo;
      int yGo;
      byte index = 0;
      byte g = 10;
      boolean isExplo;
      public byte frame;

      public Skill_Dua_4(int x, int y) {
         y -= 10;
         this.xGo = x;
         this.yGo = y;
         this.x = x + 50;
         this.y = y - 150;
         this.g = (byte)(5 + Res.rnd(10));
         this.angle = Util.angle(x - this.x, -(y - this.y));
      }

      public void update() {
         ++this.frame;
         if (this.frame > 2) {
            this.frame = 0;
         }

         if (!this.isExplo) {
            int xa = this.g * Util.cos(this.angle) >> 10;
            int ya = -(this.g * Util.sin(this.angle)) >> 10;
            this.g = (byte)(this.g + 2);
            this.x += xa;
            this.y += ya;
            if (Util.distance(this.x, this.y, this.xGo, this.yGo) <= 20) {
               this.y = this.yGo;
               this.index = 4;
               this.isExplo = true;
               EffectManager.lowEffects.addElement(this);
               EffectManager.hiEffects.removeElement(this);
               if (Res.random(1, 10) % 2 == 0) {
                  EffectSkill.createLowEfect(this.xGo, this.yGo, 24);
               } else {
                  Effect_Sao_chop ef = new Effect_Sao_chop(this.xGo, this.yGo, 24);
                  EffectManager.addHiEffect(ef);
               }
            }
         } else {
            ++this.index;
            if (this.index >= 12) {
               EffectManager.lowEffects.removeElement(this);
            }
         }

      }

      public void paint(mGraphics g) {
         Image img = EffectSkill.getImage(111);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(111), EffectSkill.getWidth(111), EffectSkill.getHight(111), 0, this.x, this.y, 0, false);
         }

      }
   }
}
