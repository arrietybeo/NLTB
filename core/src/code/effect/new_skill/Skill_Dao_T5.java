package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import lib.mGraphics;

public class Skill_Dao_T5 extends Effect {
   public byte frame;
   public byte f;
   public short xto;
   public short yto;
   public short id1;
   public short id2;
   int count;
   boolean star;

   public Skill_Dao_T5(short x, short y, Actor target) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.id1 = 31;
      this.id2 = 33;
      this.star = true;
   }

   public void paint(mGraphics g) {
   }

   public void create(short x, short y, Actor target, byte type, byte effect) {
      Me_Arrow D = new Me_Arrow(x, y, target, type, effect, (byte)0, (byte)20);
      if (this.dame == 0) {
         this.isDame0 = true;
      }

      D.setDame(this.dame);
      EffectManager.addHiEffect(D);
   }

   public void update() {
      if (this.star) {
         ++this.f;
         if (this.f % 2 != 0) {
            this.create((short)this.x, (short)(this.y + 5), this.target, (byte)32, (byte)50);
            ++this.count;
         }

         if (this.f > 3) {
            this.f = 0;
         }
      }

      if (this.count == 19) {
         this.wantDestroy = true;
      }

   }
}
