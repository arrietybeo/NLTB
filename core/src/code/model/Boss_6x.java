package code.model;

import code.effect.EffectSkillMonster;
import code.effect.new_skill.Skill_Dao_T7;
import code.main.GameCanvas;
import code.screen.screen.GameScr;

public class Boss_6x extends BossGame {
   int timeDelay;

   public Boss_6x() {
   }

   public Boss_6x(int idTemplate) {
      super(idTemplate);
   }

   public void update() {
      super.update();
   }

   public void UpdateAttack() {
      super.UpdateAttack();
      if (this.getIDSkillBoss() == 15) {
         if (this.indexFrame == 0 && this.currentFrame == 0) {
            EffectSkillMonster skill = new EffectSkillMonster(14, this, this.ntarget, this.mDame);
            EffectManager.addHiEffect(skill);
         }
      } else {
         Actor ac;
         int i;
         if (this.getIDSkillBoss() == 14) {
            this.timeDelay = 0;
            if (this.indexFrame == 0 && this.currentFrame == 0) {
               for(i = 0; i < this.ntarget.size(); ++i) {
                  ac = (Actor)this.ntarget.elementAt(i);
                  if (ac != null) {
                     GameCanvas.gameScr.startFlyText("-" + this.mDame[i], 2, ac.x, ac.y - 35, -1, -2);
                  }
               }
            }
         } else if (this.getIDSkillBoss() == 16 && this.indexFrame == 0 && this.currentFrame == 0) {
            for(i = 0; i < this.ntarget.size(); ++i) {
               ac = (Actor)this.ntarget.elementAt(i);
               if (ac != null) {
                  Skill_Dao_T7 sd7 = new Skill_Dao_T7(ac, i + 5, this.mDame[i]);
                  EffectManager.addHiEffect(sd7);
               }
            }
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
