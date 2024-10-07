package code.effect.new_skill;

import code.model.Actor;
import code.model.DataSkillEff;
import code.model.Effect;

public class Skill_Default_Boss_91 extends Effect {
   public Skill_Default_Boss_91(Actor tg, int idEff) {
      this.target = tg;
      DataSkillEff eff = new DataSkillEff(idEff, this.x, this.y, 0L);
      eff.x = this.target.x;
      eff.y = (short)(this.target.y + this.target.getHeight() / 2);
      this.target.addEffectSkill(eff, this.target.x, this.target.y + this.target.getHeight() / 2);
      this.target.jum();
      this.doDestroy();
   }

   public void update() {
      super.update();
      if (this.wantDestroy && this.target != null) {
         if (this.dame == 0) {
            this.isDame0 = true;
         }

         this.startFlyText(this.dame, 0, this.target.x, this.target.y, 0, 0, 1);
      }

   }
}
