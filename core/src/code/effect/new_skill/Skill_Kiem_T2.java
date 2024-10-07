package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.Effect;

public class Skill_Kiem_T2 extends Effect {
   Char c;
   protected static final byte[] splashKiemX = new byte[]{-2, 2, 20, -20};
   protected static final byte[] splashKiemY = new byte[]{-30, -30, -30, -30};
   private static short[][][] angle = new short[][][]{{{90, 315, 225}, {90, 315, 225}, {90, 315, 225}, {90, 315, 225, 270}, {90, 315, 225, 270}, {90, 315, 225, 270, 45}, {90, 315, 225, 270, 45, 135}, {90, 315, 225, 270, 45, 135, 180}, {90, 315, 225, 270, 45, 135, 0}, {90, 315, 225, 270, 45, 135, 0}, {90, 315, 225, 270, 45, 135, 0}}, {{270, 135, 45}, {270, 135, 45}, {270, 135, 45}, {270, 135, 45, 90}, {270, 135, 45, 90}, {270, 135, 45, 90, 315}, {270, 135, 45, 90, 315, 235}, {270, 135, 45, 90, 315, 235, 180}, {270, 135, 45, 90, 315, 235, 0}, {90, 315, 225, 270, 45, 135, 0}, {90, 315, 225, 270, 45, 135, 0}}, {{180, 45, 315}, {180, 45, 315}, {180, 45, 315}, {180, 45, 315, 0}, {180, 45, 315, 0}, {180, 45, 315, 0, 135}, {180, 45, 315, 0, 135, 225}, {180, 45, 315, 0, 135, 225, 270}, {180, 45, 315, 0, 135, 225, 270, 90}, {90, 315, 225, 270, 45, 135, 0}, {90, 315, 225, 270, 45, 135, 0}}, {{0, 135, 225}, {0, 135, 225}, {0, 135, 225}, {0, 135, 225, 180}, {0, 135, 225, 180}, {0, 135, 225, 180, 45}, {0, 135, 225, 180, 45, 90}, {0, 135, 225, 180, 45, 90, 270}, {0, 135, 225, 180, 45, 90, 270, 315}, {90, 315, 225, 270, 45, 135, 0}, {90, 315, 225, 270, 45, 135, 0}}};
   int lvSkill = 3;
   int ID;
   byte nDragon = 1;
   int loop = 0;

   public Skill_Kiem_T2(Char c, Actor target, int dame) {
      this.c = c;
      this.target = target;
      this.dame = dame;
   }

   public void setLvSkill(int lv) {
      this.lvSkill = lv;
   }

   public void update() {
      try {
         for(int i = 0; i < angle[this.c.dir][this.lvSkill - 1].length; ++i) {
            GameCanvas.gameScr.startNewMagicBeam(1, this.c, this.target, this.c.x, this.c.y, this.dame / this.nDragon, (byte)0, angle[this.c.dir][this.lvSkill - 1][i]);
         }

         this.wantDestroy = true;
      } catch (Exception var2) {
      }

   }
}
