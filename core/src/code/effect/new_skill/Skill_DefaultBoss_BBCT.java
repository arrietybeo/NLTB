package code.effect.new_skill;

import code.model.Actor;
import code.model.DataSkillEff;
import code.model.LiveActor;

public class Skill_DefaultBoss_BBCT extends Me_Arrow {
   public int idEffectAuto;

   public Skill_DefaultBoss_BBCT(short x, short y, Actor target, byte type, byte effect, byte lathinh, byte Position, int yadd) {
      super(x, y, target, type, effect, lathinh, Position, yadd);
   }

   public void update() {
      try {
         ++this.frame;
         if (this.frame >= EffectSkill.getFrame(this.type)) {
            this.frame = 0;
         }

         if (this.pos < this.xw.length) {
            ++this.pos;
         }

         if (this.pos >= this.xw.length) {
            this.pos = (short)(this.xw.length - 1);
            this.xw[this.pos] = this.xto;
            this.yw[this.pos] = this.yto;
            this.wantDestroy = true;
            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            } else {
               this.target.jum();
            }

            DataSkillEff eff = new DataSkillEff(this.idEffectAuto, this.x, this.y, 0L);
            eff.x = this.target.x;
            eff.y = this.target.y;
            this.target.addEffectSkill(eff, this.target.x, this.target.y);
         }

         if (this.wantDestroy) {
            if (this.dame == 0) {
               this.isDame0 = true;
            }

            this.startFlyText(this.dame, 0, this.xto, this.yto, 0, 0, this.Owner.x > this.xto ? -1 : 1);
         }
      } catch (Exception var2) {
         this.doDestroy();
      }

   }
}
