package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;

public class Skill_Cung_T5 extends Effect {
   public Skill_Cung_T5(short x, short y, Actor target, Actor owner, int dame) {
      this.x = x;
      this.y = y;
      this.target = target;
      Me_Arrow d = new Me_Arrow(x, y, target, (byte)71, (byte)-1, (byte)0, (byte)24, dame, owner);
      EffectManager.addHiEffect(d);
      this.wantDestroy = true;
   }
}
