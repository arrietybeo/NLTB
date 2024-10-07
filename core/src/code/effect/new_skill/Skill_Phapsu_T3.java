package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;
import lib.mGraphics;
import lib.mVector;

public class Skill_Phapsu_T3 extends Effect {
   public short[] arr_radian = new short[]{0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330};
   public int R;
   public int count;
   public int radian;
   public int id;
   mVector target = new mVector();
   byte frame;
   public int[] arr = new int[]{8, -8, 5, -5, 0};
   public short[] radian1 = new short[]{0, 90, 180, 270};
   public int[] arref = new int[]{51, 54};

   public Skill_Phapsu_T3(short x, short y, mVector target) {
      this.x = x;
      this.y = y;
      this.R = 100;
      this.radian = 36;
      this.id = 29;
      this.target = target;
   }

   public void paint(mGraphics g) {
   }

   public void update() {
      this.R -= 5;
      if (this.R <= 0) {
         this.R = 100;
         ++this.count;
      }

      int i;
      for(i = 0; i < this.radian1.length; ++i) {
         EffectSkill.createHiEfect(Util.cos(this.radian1[i]) * this.R / 1024 + this.x, Util.sin(this.radian1[i]) * this.R / 1024 + this.y, 38);
         short[] var10000 = this.radian1;
         var10000[i] = (short)(var10000[i] + 15);
         if (this.radian1[i] >= 359) {
            this.radian1[i] = 0;
         }
      }

      if (GameCanvas.gameTick % 10 == 0) {
         EffectSkill.createHiEfect(this.x, this.y, 37);
      }

      if (this.target.size() > 0) {
         for(i = 0; i < this.target.size(); ++i) {
            Actor ac = (Actor)this.target.elementAt(i);
            ac.setMove(this.x, this.y);
            if (this.count > 1) {
               Effect_dien e = new Effect_dien(this.x, this.y, 28, 100);
               EffectManager.addHiEffect(e);
               this.wantDestroy = true;
            }
         }
      }

   }
}
