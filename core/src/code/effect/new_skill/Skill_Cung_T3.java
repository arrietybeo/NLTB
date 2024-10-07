package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import lib.mVector;

public class Skill_Cung_T3 extends Effect {
   public byte count1;
   public mVector effskill = new mVector();

   public Skill_Cung_T3(short x, short y, Actor target, Actor owner) {
      this.target = target;
      this.x = x;
      this.y = y;
      this.Owner = owner;
      Me_Arrow b = new Me_Arrow(x, y, target, (byte)71, (byte)-2, (byte)0, (byte)30);
      b.setOwner(owner);
      this.effskill.addElement(b);
      EffectManager.addHiEffect(b);
   }

   public Skill_Cung_T3(short x, short y, Actor target, Actor owner, int dame) {
      this.target = target;
      this.dame = dame;
      this.x = x;
      this.y = y;
      this.Owner = owner;
      Me_Arrow b = new Me_Arrow(x, y, target, (byte)71, (byte)-2, (byte)0, (byte)30);
      if (dame <= 0) {
         this.isDame0 = true;
      }

      b.setOwner(owner);
      b.setDame(dame);
      this.effskill.addElement(b);
      EffectManager.addHiEffect(b);
   }

   public void update() {
      ++this.count1;
      if (this.count1 % 3 == 0) {
         Me_Arrow b = new Me_Arrow((short)this.x, (short)this.y, this.target, (byte)71, (byte)-2, (byte)0, (byte)30);
         if (this.dame <= 0) {
            this.isDame0 = true;
         }

         b.setDame(this.dame);
         this.effskill.addElement(b);
         EffectManager.addHiEffect(b);
      }

      if (this.count1 >= 6) {
         this.wantDestroy = true;
      }

   }

   public void setDame(int dame) {
      for(int i = 0; i < this.effskill.size(); ++i) {
         Me_Arrow arr = (Me_Arrow)this.effskill.elementAt(i);
         if (arr != null && this.arrDame != null) {
            arr.setDame(this.arrDame[i] / 3);
         }
      }

   }
}
