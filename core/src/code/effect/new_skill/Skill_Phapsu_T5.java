package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.Effect;
import code.model.Monster;

public class Skill_Phapsu_T5 extends Effect {
   Char c;
   int pmonster = 0;
   int lvSkill;
   int ID;
   protected byte[] splashDuaX = new byte[]{-2, 2, -8, 8};
   protected byte[] splashDuaY = new byte[]{-10, -30, -10, -10};
   byte loop;
   byte lap;
   byte nDragon = 1;

   public Skill_Phapsu_T5(Char c, Actor target) {
      this.c = c;
      this.target = target;
   }

   public void setLvSkill(int lv) {
      this.lvSkill = lv;
   }

   public void update() {
      short[][] goc = new short[][]{{90, 315, 225, 270, 315, 225, 90, 315, 225, 315, 225}, {270, 135, 45, 90, 135, 45, 270, 135, 45, 135, 45}, {180, 45, 315, 180, 45, 315, 180, 45, 315, 45, 315}, {0, 135, 225, 0, 135, 225, 0, 135, 225, 135, 225}};
      GameCanvas.gameScr.startNewMagicBeam(3, this.c, this.target, this.c.x + this.splashDuaX[this.c.dir], this.c.y + this.splashDuaY[this.c.dir], this.c.attkPower != 2000000 ? this.c.attkPower / this.nDragon : this.c.attkPower, (byte)0, goc[this.c.dir][this.loop]);
      ++this.loop;
      if (this.loop < this.nDragon) {
         this.c.p1 = 0;
      }

      if (this.ID == 1) {
         ++this.lap;
      }

      this.wantDestroy = true;
   }

   public void updateSkill(Monster c) {
   }
}
