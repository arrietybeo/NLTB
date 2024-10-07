package code.model;

import code.effect.new_skill.Skill_Boss_Noel_1;
import code.main.GameCanvas;
import code.screen.screen.GameScr;
import lib.mSystem;

public class Boss_Nguoi_Tuyet extends BossGame {
   int timeDelay;

   public Boss_Nguoi_Tuyet() {
   }

   public Boss_Nguoi_Tuyet(int idTemplate) {
      super(idTemplate);
      mSystem.println("tao boss ng tuyet");
   }

   public void update() {
      super.update();
   }

   public void UpdateAttack() {
      super.UpdateAttack();
      int i;
      Actor ac;
      if (this.getIDSkillBoss() == 17) {
         if (this.indexFrame == 0 && this.currentFrame == 0) {
            for(i = 0; i < this.ntarget.size(); ++i) {
               ac = (Actor)this.ntarget.elementAt(i);
               if (ac != null) {
                  EffectManager.addHiEffect(ac.x, ac.y - 35, 54);
                  GameCanvas.gameScr.startFlyText("-" + this.mDame[i], 2, ac.x, ac.y - 35, -1, -2);
               }
            }
         }
      } else if (this.getIDSkillBoss() == 18) {
         this.timeDelay = 0;
         if (this.indexFrame == 0 && this.currentFrame == 0) {
            for(i = 0; i < this.ntarget.size(); ++i) {
               ac = (Actor)this.ntarget.elementAt(i);
               if (ac != null) {
                  Skill_Boss_Noel_1 sn = new Skill_Boss_Noel_1(ac, this.mDame[i], this.x);
                  EffectManager.addHiEffect(sn);
               }
            }
         }
      } else if (this.getIDSkillBoss() == 19 && this.indexFrame == 0 && this.currentFrame == 0) {
         for(i = 0; i < this.ntarget.size(); ++i) {
            ac = (Actor)this.ntarget.elementAt(i);
            if (ac != null) {
               GameCanvas.gameScr.startNewMagicBeam(19, this, ac, this.x, this.y, this.mDame[i], (int)120);
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
