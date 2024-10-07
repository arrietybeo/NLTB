package code.screen;

public class SkillOption {
   public int param;
   public SkillOptionTemplate optionTemplate;
   public String optionString;

   public String getOptionString() {
      if (this.optionString == null) {
         if (this.optionTemplate.type == 1) {
            this.optionString = Utils.replace(this.optionTemplate.name, this.optionTemplate.strTimeReplay(this.param));
         } else {
            this.optionString = Utils.replace(this.optionTemplate.name, this.param);
         }
      }

      return this.optionString;
   }
}
