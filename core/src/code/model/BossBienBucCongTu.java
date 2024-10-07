package code.model;

import code.effect.EffectSkillMonster;
import code.screen.screen.GameScr;
import lib.Cout;

public class BossBienBucCongTu extends BossGame {
   int timeDelay;

   public BossBienBucCongTu() {
   }

   public BossBienBucCongTu(int idTemplate) {
      super(idTemplate);
   }

   public void update() {
      super.update();
   }

   public void UpdateAttack() {
      super.UpdateAttack();
      EffectSkillMonster skill;
      if (this.getIDSkillBoss() == 8) {
         this.timeDelay = 0;
         if (!this.isCreateEffAtMe && this.indexFrame == 1 && this.currentFrame == 1) {
            skill = new EffectSkillMonster(this.getIDSkillBoss(), this, this.ntarget, this.mDame);
            EffectManager.addHiEffect(skill);
            this.isCreateEffAtMe = true;
         }

         if (!this.isCreateWeapon && this.indexFrame == 2 && this.currentFrame == 9) {
            this.isCreateWeapon = true;
         }
      } else if (this.getIDSkillBoss() == 10) {
         this.timeDelay = 0;
         if (this.indexFrame == 1 && this.currentFrame == 7) {
            skill = new EffectSkillMonster(this.getIDSkillBoss(), this, this.ntarget, this.mDame);
            EffectManager.addHiEffect(skill);
         }
      } else if (this.getIDSkillBoss() == 9) {
         if (!this.isCreateEffAtMe) {
            this.isCreateEffAtMe = true;
            this.dataEff = new DataSkillEff(231, this.x, this.y, -1L);
            this.dataEff.timelive = System.currentTimeMillis() + 1000000L;
            this.addEffectSkill(this.dataEff, this.x, this.y);
            this.timeDelay = 20;
            Cout.Debug("Tao eff con doi " + this.isCreateWeapon);
         }

         if (!this.isCreateWeapon) {
            if (this.timeDelay <= 0) {
               this.isCreateWeapon = true;
               skill = new EffectSkillMonster(this.getIDSkillBoss(), this, this.ntarget, this.mDame);
               EffectManager.addHiEffect(skill);
               Cout.Debug("Time delay :" + this.timeDelay);
            }

            --this.timeDelay;
         }
      }

   }

   public void doChangeFrameAttack() {
      int idSkill = this.getIDSkillBoss();
      Object obj = GameScr.ALL_SKILL_TEMPLATE_BOSS.get(String.valueOf(idSkill));
      if (obj != null) {
         SkillBossTemplate sk = (SkillBossTemplate)obj;
         byte[][] arrayFrame = sk.arrayAnimAttackUp;
         if (this.huongY == 1) {
            arrayFrame = sk.arrayAnimAttackDown;
         }

         byte[] tem = arrayFrame[this.indexFrame];
         this.frame = tem[this.currentFrame];
         ++this.currentFrame;
         if (this.currentFrame > tem.length - 1) {
            ++this.indexFrame;
            this.currentFrame = 0;
            if (this.indexFrame > arrayFrame.length - 1) {
               if (idSkill == 9) {
                  this.indexFrame = arrayFrame.length - 1;
                  this.frame = tem[this.currentFrame];
               } else {
                  this.indexFrame = 0;
                  this.frame = 0;
                  this.state = 0;
                  if (this.dataEff != null) {
                     this.dataEff.doDestroy();
                  }
               }
            }
         }
      } else {
         this.state = 0;
      }

   }

   public void startAttack(int idSkill) {
      super.startAttack(idSkill);
      this.timeDelay = 0;
   }
}
