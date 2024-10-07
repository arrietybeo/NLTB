package code.screen;

public class Phai {
   public static Phai[] arr;
   public int classId;
   public int sysId;
   public int iconId;
   public String name;
   public SkillTemplate[] skillTemplates;

   public SkillTemplate getSkillTemplate(int skillTemplateId) {
      for(int i = 0; i < this.skillTemplates.length; ++i) {
         if (this.skillTemplates[i].id == skillTemplateId) {
            return this.skillTemplates[i];
         }
      }

      return null;
   }
}
