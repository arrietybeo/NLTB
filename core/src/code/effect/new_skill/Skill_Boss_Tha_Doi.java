package code.effect.new_skill;

import code.model.Actor;
import code.model.Boss_98;
import code.model.DataSkillEff;
import code.model.Effect;
import lib.Cout;
import lib.mVector;

public class Skill_Boss_Tha_Doi extends Effect {
   mVector allTarget;
   int xTo = -1;
   int yTo = -1;
   int indexDame;
   int[] lastPositionBoss;
   Boss_98 Boss;
   int timeDelay;
   boolean isGetPosition;

   public Skill_Boss_Tha_Doi(mVector allTarget, Boss_98 Boss, int[] arrayDam) {
      this.allTarget = allTarget;
      this.Boss = Boss;
      this.arrDame = arrayDam;
      this.lastPositionBoss = new int[]{Boss.x, Boss.y};
      this.indexDame = 0;
   }

   public void update() {
      if (this.Boss == null) {
         this.doDestroy();
         Cout.Debug("Skill 11 ");
      } else {
         --this.timeDelay;
         int[] positionTo = this.getPositionTo();
         if (positionTo[0] == -100 && positionTo[1] == -100) {
            if (this.timeDelay <= 0) {
               this.Boss.state = 0;
               this.Boss.x = (short)this.lastPositionBoss[0];
               this.Boss.y = (short)this.lastPositionBoss[1];
               this.Boss.xTo = this.Boss.x;
               this.Boss.yTo = this.Boss.y;
               if (this.Boss.dataEff != null) {
                  this.Boss.dataEff.doDestroy();
               }

               this.doDestroy();
            }

         } else {
            this.xTo = positionTo[0];
            this.yTo = positionTo[1];
            if (this.Boss.x != this.xTo && this.Boss.y != this.yTo && this.timeDelay <= 0) {
               this.Boss.x = (short)this.xTo;
               this.Boss.y = (short)this.yTo;
               this.xTo = -1;
               this.yTo = -1;
               this.timeDelay = 5;
               if (this.target != null) {
                  DataSkillEff eff = new DataSkillEff(230, this.x, this.y, 0L);
                  eff.x = this.target.x;
                  eff.y = this.target.y;
                  this.target.addEffectSkill(eff, this.target.x, this.target.y);
                  if (this.dame == 0) {
                     this.isDame0 = true;
                  }

                  this.startFlyText(this.dame, 0, this.target.x, this.target.y, 0, 0, this.target.x > this.xTo ? -1 : 1);
               }
            }

         }
      }
   }

   public int[] getPositionTo() {
      int size = this.allTarget.size();
      int[] pos = new int[]{-100, -100};
      if (this.xTo != -1 && this.yTo != -1) {
         return new int[]{this.xTo, this.yTo};
      } else if (size <= 0) {
         return pos;
      } else {
         for(int i = 0; i < size; ++i) {
            Actor ac = (Actor)this.allTarget.elementAt(i);
            if (ac != null) {
               pos[0] = ac.x - 32;
               pos[1] = ac.y;
               this.target = ac;
               if (this.indexDame <= this.arrDame.length - 1) {
                  this.dame = this.arrDame[this.indexDame];
               }

               ++this.indexDame;
               Cout.Debug("Index Dame>> : " + this.indexDame);
               this.allTarget.removeElementAt(i);
               this.timeDelay = 5;
               return pos;
            }

            this.allTarget.removeElementAt(i);
            ++this.indexDame;
         }

         return pos;
      }
   }
}
