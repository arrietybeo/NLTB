package code.effect.new_skill;

import code.model.Actor;
import code.model.BossBienBucCongTu;
import code.model.EffectManager;
import lib.mVector;

public class Skill_Boss_BBCT_Nem_Cau extends Me_Arrow {
   public BossBienBucCongTu boss;
   public mVector allTarget = new mVector();

   public Skill_Boss_BBCT_Nem_Cau() {
      this.xw = new int[1];
      this.yw = new int[1];
   }

   public Skill_Boss_BBCT_Nem_Cau(short x, short y, Actor target, byte type, byte effect, byte lathinh, byte Position, int yadd) {
      super(x, y, target, type, effect, lathinh, Position, yadd);
   }

   public void setspeed(byte Positions) {
   }

   public void setPosition(int x, int y) {
      super.setPosition(x, y);
   }

   public void update() {
      ++this.frame;
      if (this.frame >= EffectSkill.getFrame(this.type)) {
         this.frame = 0;
      }

      if (this.boss != null) {
         this.xw[0] = this.boss.x;
         this.yw[0] = this.boss.y - this.boss.getHeight();
         if (this.boss.state != 3) {
            this.doDestroy();
            return;
         }

         if (this.boss.isCreateWeapon) {
            this.doDestroy();
            int size = this.allTarget.size();

            for(int i = 0; i < size; ++i) {
               Actor ac = (Actor)this.allTarget.elementAt(i);
               Skill_DefaultBoss_BBCT e = new Skill_DefaultBoss_BBCT(this.boss.x, (short)(this.boss.y - this.boss.getHeight()), ac, (byte)119, (byte)45, (byte)-2, (byte)32, -ac.getHeight() * 2 / 3);
               e.dame = this.arrDame[i];
               e.setOwner(this.boss);
               e.idEffectAuto = 233;
               EffectManager.addHiEffect(e);
            }
         }
      }

   }
}
