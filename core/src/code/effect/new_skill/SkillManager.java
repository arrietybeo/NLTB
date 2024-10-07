package code.effect.new_skill;

import code.model.mSkillManager;
import lib.mHashtable;

public class SkillManager extends mHashtable {
   public static SkillManager instance = new SkillManager();
   private static final String strTancong = "Tấn công tăng: ";
   private static final String strPhongThu = "Phòng thủ tăng: ";
   public static String[][] infoString = new String[][]{{"Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Phòng thủ tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: "}, {"Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Phòng thủ tăng: ", "Tăng sức công: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: "}, {"Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "HP+MP tăng: ", "MP tăng: ", "Hồi sinh: ", "Chia sẻ thiệt hại: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: "}, {"Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tốc độ tăng: ", "Phòng thủ tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: "}, {"Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tốc độ tăng: ", "HP tăng: ", "Tấn công tăng: ", "Tấn công tăng: ", "Tấn công tăng: "}};
   public static byte[][] LEVEL_ADD_SKILL;
   public static final byte[][] EFF_BUFF_SKILL = new byte[][]{{20, 24}, {20, 21}, {25, 30, 27, 23}, {19, 20}, {22, 19}};
   public static final byte[][] SKILL_CAN_BUFF_TO_USER = new byte[][]{{-1, 0}, {0, -1}, {0, -1, 1, 0}, {0, -1}, {0, -1}};
   public static byte[][] SKILL_AEO;
   public static final byte[][][] RANGE_SKILL_AEO = new byte[][][]{{{0, 64, 64, 80, 80, 96, 96, 102, 118, 124, 124}, {0, 64, 64, 80, 80, 96, 96, 102, 118, 124, 124}, {0, 64, 64, 80, 80, 96, 96, 102, 118, 124, 124}}};
   public static short[][][] SKILL_DAM_PERCENT;
   public static short[][] TIME_LIFE_BUFF_SKILL;
   public static int[][][] SKILL_COOLDOWN;
   public static short[][][] SKILL_MP;
   public static short[][] SKILL_RANGE;
   public static final String[][] SKILL_NAME = new String[][]{{"Chém", "Kim tinh pháp", "Lôi điện pháp", "Kinh lôi bát thủ", "Hộ sát tiến", "Dĩ lực đáo công", "Thiên lôi điện trảm", "Sấm động dương gian", "Kiếm phi kinh thiên"}, {"Chém", "Hỏa kinh thiên", "Nhất hỏa long", "Bát đại hỏa long", "Cường thân giáp", "Hộ công tiến", "Thiên long bạo kích", "Liệt hỏa bạo kích", "Sao băng giáng thế"}, {"Đánh", "Thủy giáng minh", "Thần long thủy", "Bát đại hải long", "Hồi công lực đan", "Hồi lực tiến", "Hồi sinh", "Song hộ công thủ", "Hải long xuất thế", "Song long thị uy", "Hàn băng vũ"}, {"Đập", "Thổ Tú", "Kim sơn thủy", "Khổng kình bát vĩ", "Bất di biến", "Hộ thủ tiến", "Kinh thiên động địa", "Sơn Tinh bộ thiên", "Thạch nhũ công tâm"}, {"Bắn", "Nhất hồn tiễn", "Phi thiên tiễn", "Bát kim tễn đáo", "Độc lưu tiễn", "Hộ độc tiễn", "Thập diện tâm tiễn", "Thăng thiên loạn tiễn", "Vạn tiễn quy tâm"}};
   public static final mSkillManager[][] SKILL_ANIMATE = new mSkillManager[][]{{new mSkillManager((byte)2, 0), new mSkillManager((byte)2, 1), new mSkillManager((byte)2, 2), new mSkillManager((byte)2, 3), new mSkillManager((byte)2, 4), new mSkillManager((byte)2, 5), new mSkillManager((byte)2, 6)}, {new mSkillManager((byte)4, 7), new mSkillManager((byte)4, 8), new mSkillManager((byte)4, 9), new mSkillManager((byte)4, 10), new mSkillManager((byte)4, 11), new mSkillManager((byte)4, 12), new mSkillManager((byte)4, 13)}, {new mSkillManager((byte)0, 14), new mSkillManager((byte)0, 15), new mSkillManager((byte)0, 16), new mSkillManager((byte)0, 17), new mSkillManager((byte)0, 18), new mSkillManager((byte)0, 19), new mSkillManager((byte)0, 20)}, {new mSkillManager((byte)1, 21), new mSkillManager((byte)1, 22), new mSkillManager((byte)1, 23), new mSkillManager((byte)1, 24), new mSkillManager((byte)1, 25), new mSkillManager((byte)1, 26), new mSkillManager((byte)1, 27)}, {new mSkillManager((byte)3, 28), new mSkillManager((byte)3, 29), new mSkillManager((byte)3, 30), new mSkillManager((byte)3, 31), new mSkillManager((byte)3, 32), new mSkillManager((byte)3, 33), new mSkillManager((byte)3, 34)}};

   public static int getSkillDamPercent(int skillType, int skillLevel, int clazz) {
      return skillLevel > -1 ? SKILL_DAM_PERCENT[0][skillType][skillLevel] : 0;
   }

   public static int getSkillMP(int skillType, int skillLevel, int clazz) {
      return skillLevel > -1 ? SKILL_MP[0][skillType][skillLevel] : 0;
   }

   public static long getSkillCooldown(byte skillType, byte clazz, int level) {
      return (long)(level > -1 ? SKILL_COOLDOWN[0][skillType][level] : 0);
   }

   public static int getSkillRange(byte skillType, byte clazz) {
      return SKILL_RANGE[0][skillType];
   }

   public static short getLvAddSkill(int clazz, int idSkill, int lvSkill) {
      return lvSkill > -1 ? LEVEL_ADD_SKILL[idSkill][lvSkill] : 0;
   }

   public static byte getBuffToUser(int clazz, int id) {
      return SKILL_CAN_BUFF_TO_USER[clazz][id];
   }

   public static boolean isSkillAeo(int cClass, int skill) {
      for(int i = 0; i < SKILL_AEO[cClass].length; ++i) {
         if (skill == SKILL_AEO[cClass][i]) {
            return true;
         }
      }

      return false;
   }

   public static int getRangeSkillAeo(int charClass, int skill) {
      return 1984;
   }

   public static mSkillManager getSkillAnimate(int skillType, int clazz) {
      return SKILL_ANIMATE[clazz][skillType];
   }
}
