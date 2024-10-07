package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Res;
import lib.mVector;

public class Skill_doc_2019 extends Effect {
   mVector vectarget = new mVector();
   int f;

   public Skill_doc_2019(mVector mst) {
      this.vectarget = mst;

      for(int i = 0; i < this.vectarget.size(); ++i) {
         Actor ac = (Actor)this.vectarget.elementAt(i);
         if (ac != null) {
            EffectManager.addHiDataeffectSkill_delay(394, ac.x, ac.y, 1, i * Res.random(5));
         }
      }

      Actor ac = (Actor)this.vectarget.elementAt(0);
      if (ac != null) {
         this.x = ac.x;
         this.y = ac.y;
      }

   }

   public void update() {
      ++this.f;
      int rd;
      if (this.f == 5) {
         for(rd = 0; rd < this.vectarget.size(); ++rd) {
            Actor ac = (Actor)this.vectarget.elementAt(rd);
            if (ac != null) {
               EffectManager.addHiDataeffectSkill_delay(394, ac.x + Res.random_Am(10, 30), ac.y + Res.random_Am(10, 30), 1, rd * Res.random(5));
            }
         }
      }

      int i;
      if (this.f == 10) {
         rd = Res.random(4, 7);

         for(i = 0; i < rd; ++i) {
            EffectManager.addHiDataeffectSkill_delay(394, this.x + Res.random_Am(30, 50), this.y + Res.random_Am(20, 40), 1, i * Res.random(5));
         }
      }

      if (this.f == 15) {
         rd = Res.random(5, 10);

         for(i = 0; i < rd; ++i) {
            EffectManager.addHiDataeffectSkill_delay(394, this.x + Res.random_Am_0_2(50), this.y + Res.random_Am_0_2(100), 1, i * Res.random(5));
         }
      }

      if (this.f >= 20) {
         rd = Res.random(7, 12);

         for(i = 0; i < rd; ++i) {
            EffectManager.addHiDataeffectSkill_delay(394, this.x + Res.random_Am_0_2(100), this.y + Res.random_Am_0_2(150), 1, i * Res.random(5));
         }

         this.wantDestroy = true;
      }

   }
}
