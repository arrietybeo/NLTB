package code.screen;

import code.model.OptionSkill;
import code.screen.screen.MainMenu;
import lib.mVector;

public class SkillTemplate {
   public static byte TYPE_ATTACK = 0;
   public static byte TYPE_BUFF = 1;
   public static byte TYPE_PASSIVE = 2;
   public byte id;
   public int classId;
   public int maxPoint;
   public SkillPaint paint;
   public Skill[] skills;
   public String decription = "";
   public String name;
   public int type;
   public int iconId;
   public int range;
   public byte[] lvRequire;
   public short idSkillCode;
   public short idLastEff;
   public short idArrow;
   public short idTailMagic;
   public short idEffStartSkill;
   public short[] idArrowTool;
   public short[] mp;
   public short[] nTarget;
   public int[] timeLive;
   public int[] coolDown;
   public mVector options = new mVector();

   public mVector getInfoOptions(int level) {
      mVector info = new mVector();

      for(int i = 0; i < this.options.size(); ++i) {
         mVector m = ((OptionSkill)this.options.elementAt(i)).getInfoTextShow(level);
         m = MainMenu.setGroupOption(m);

         for(int j = 0; j < m.size(); ++j) {
            info.addElement(m.elementAt(j));
         }
      }

      return info;
   }

   public int getCoolDown(int lv) {
      if (lv < 0) {
         return 500;
      } else {
         return lv > this.coolDown.length ? 500 : this.coolDown[lv];
      }
   }

   public int getmplose(int lv) {
      if (this.mp == null) {
         return 1;
      } else {
         return lv >= this.mp.length ? this.mp[this.mp.length - 1] : this.mp[lv];
      }
   }

   public short getTarget(int lv) {
      if (this.nTarget == null) {
         return 1;
      } else {
         return lv - 1 >= this.nTarget.length ? 1 : this.nTarget[lv - 1];
      }
   }

   public String getNameTypeSkill() {
      if (this.type == 1) {
         return "Hỗ trợ";
      } else {
         return this.type == 2 ? "Bị động" : "Tấn công";
      }
   }

   public boolean isSkillBuff() {
      return this.type == 1;
   }
}
