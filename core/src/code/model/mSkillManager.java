package code.model;

import code.effect.new_skill.SkillAnimate;
import code.effect.new_skill.Skill_Cung_T6;
import lib.mVector;

public class mSkillManager extends SkillAnimate {
   protected static final byte[] splashCungX = new byte[]{-2, 2, -14, 14};
   protected static final byte[] splashCungY = new byte[]{-2, -28, -10, -10};
   public mVector mst = new mVector();
   public static final byte BUA = 0;
   public static final byte CUNG = 1;
   public static final byte DAO = 2;
   public static final byte KIEM = 3;
   public static final byte PHAPSU = 4;
   public byte idSkill = -1;
   public static mSkillManager me = new mSkillManager();
   public short range = 60;
   public Skill_Cung_T6 t;

   public void addSkill(byte id) {
      this.idSkill = id;
   }

   public void addTarget(mVector Listmst) {
      this.mst = Listmst;
   }

   public mSkillManager() {
   }

   public mSkillManager(byte charClass, int id) {
      this.idSkill = (byte)id;
   }

   public void update(Char c) {
   }
}
