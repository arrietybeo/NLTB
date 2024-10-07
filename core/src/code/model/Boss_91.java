package code.model;

import code.effect.EffectSkillMonster;

public class Boss_91 extends BossGame {
   public Boss_91() {
   }

   public Boss_91(int idTemplate) {
      super(idTemplate);
   }

   public void update() {
      super.update();
   }

   public void UpdateAttack() {
      super.UpdateAttack();
      int skillID = this.getIDSkillBoss();
      EffectSkillMonster skill;
      if (skillID == 11) {
         if (!this.isCreateEffAtMe && this.currentFrame == 6) {
            skill = new EffectSkillMonster(this.getIDSkillBoss(), this, this.ntarget, this.mDame);
            EffectManager.addHiEffect(skill);
            this.isCreateEffAtMe = true;
         }
      } else if (skillID != 12 && skillID == 13 && !this.isCreateEffAtMe && this.currentFrame == 10) {
         skill = new EffectSkillMonster(this.getIDSkillBoss(), this, this.ntarget, this.mDame);
         EffectManager.addHiEffect(skill);
         this.isCreateEffAtMe = true;
      }

   }
}
