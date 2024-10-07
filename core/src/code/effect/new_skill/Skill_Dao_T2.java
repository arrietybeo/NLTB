package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;
import lib.mVector;

public class Skill_Dao_T2 extends Effect {
   Char c;
   mVector mst = new mVector();
   short radian;
   short[] xx = new short[]{-10, 10, 0};
   short[] yy = new short[]{-10, 10, 0};

   public Skill_Dao_T2(Char c, mVector mst) {
      this.c = c;
      this.mst = mst;
   }

   public void update() {
      EffectSkill.createLowEfect(Util.cos(this.radian) * 30 / 1024 + this.c.x, Util.sin(this.radian) * 30 / 1024 + this.c.y - 15, 73);
      this.radian = (short)(this.radian + 45);
      if (this.radian >= 360) {
         this.radian = 0;

         for(int i = 0; i < this.mst.size(); ++i) {
            Actor mt = (Actor)this.mst.elementAt(i);
            Skill_AEO_DUA_DAO_5 skill = new Skill_AEO_DUA_DAO_5(this.c.x + this.xx[GameCanvas.random(0, this.xx.length)], this.c.y + this.yy[GameCanvas.random(0, this.yy.length)], this.c, mt, (byte)3);
            EffectManager.addHiEffect(skill);
         }

         this.wantDestroy = true;
      }

   }
}
