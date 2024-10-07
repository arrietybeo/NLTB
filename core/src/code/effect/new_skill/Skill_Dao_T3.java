package code.effect.new_skill;

import code.model.Effect;
import code.model.EffectManager;

public class Skill_Dao_T3 extends Effect {
   public int vx;
   public int vy;
   public int dx;
   public int dy;
   public int angle;
   public int xto;
   public int yto;
   public short[] arr_radian = new short[]{45, 135, 225, 315};

   public void create() {
      for(int i = 0; i < this.arr_radian.length; ++i) {
         Effect_lua t = new Effect_lua(this.x, this.y, this.xto, this.yto, this.arr_radian[i]);
         EffectManager.addLowEffect(t);
      }

   }

   public Skill_Dao_T3(int x, int y, int xto, int yto) {
      this.x = x;
      this.y = y;
      this.xto = xto;
      this.yto = yto;
      this.create();
      this.wantDestroy = true;
   }
}
