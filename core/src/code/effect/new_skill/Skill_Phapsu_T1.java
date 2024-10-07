package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import lib.mGraphics;

public class Skill_Phapsu_T1 extends Effect {
   public short count;
   public short xto;
   public short yto;
   public short time;
   public short idimg;
   public byte frame;

   public Skill_Phapsu_T1(short x, short y, Actor target) {
      this.target = target;
      this.x = x;
      this.y = y;
      this.xto = target.x;
      this.yto = (short)(target.y - 25);
      this.idimg = 12;
   }

   public void paint(mGraphics g) {
   }

   public void update() {
      Me_Arrow b = new Me_Arrow((short)this.x, (short)this.y, this.xto, (short)(this.yto + EffectSkill.getHight(this.idimg) / 2), (byte)6, (byte)-3, (byte)0, (byte)30);
      EffectManager.addHiEffect(b);
      this.wantDestroy = true;
   }
}
