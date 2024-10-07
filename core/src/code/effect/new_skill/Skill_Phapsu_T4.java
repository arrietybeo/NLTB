package code.effect.new_skill;

import code.effect.EffectStarSkill;
import code.model.Actor;
import code.model.Char;
import code.model.Effect;
import code.model.EffectManager;
import lib.mVector;

public class Skill_Phapsu_T4 extends Effect {
   public int frame = 0;
   public int count;
   public int id;
   mVector mst = new mVector();
   Char c;

   public Skill_Phapsu_T4(mVector target, Char c) {
      this.mst = target;
      this.c = c;
      this.delay = 15;
      EffectStarSkill eff = new EffectStarSkill(c.x, (short)(c.y - 25), (short)40, (short)0, (short)30);
      EffectManager.addHiEffect(eff);
      EffectSkill.createHiEfect(c.x, (short)(c.y - 25), 57);
   }

   public void update() {
      --this.delay;
      if (this.delay <= 0) {
         for(int i = 0; i < this.mst.size(); ++i) {
            Actor mt = (Actor)this.mst.elementAt(i);
            Skill_AEO_DUA_DAO_5 skill = new Skill_AEO_DUA_DAO_5(this.c.x, this.c.y, this.c, mt, (byte)4);
            EffectManager.addHiEffect(skill);
         }

         this.wantDestroy = true;
      }

   }
}
