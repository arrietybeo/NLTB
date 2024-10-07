package code.effect.new_skill;

import code.effect.EffectStarSkill;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;

public class Skill_Cung_T2 extends Effect {
   public byte frame;

   public Skill_Cung_T2(short x, short y, Actor target) {
      this.target = target;
      this.x = x;
      this.y = y;
      this.delay = 5;
      EffectStarSkill eff = new EffectStarSkill(x, y, (short)39, (short)2, (short)50);
      EffectManager.addLowEffect(eff);
      EffectSkill.createHiEfect(x, y, 37);
   }

   public void update() {
      --this.delay;
      if (this.delay <= 0) {
         Me_Arrow e = new Me_Arrow((short)this.x, (short)this.y, this.target, (byte)2, (byte)-2, (byte)0, (byte)30);
         EffectManager.addHiEffect(e);
         this.wantDestroy = true;
      }

   }
}
