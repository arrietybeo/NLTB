package code.screen;

public class SkillOptionTemplate {
   public int id;
   public String name;
   public int type;

   public String strTimeReplay(int param) {
      if (param % 1000 == 0) {
         return String.valueOf(param / 1000);
      } else {
         int time = param % 1000;
         return param / 1000 + "." + (time % 100 == 0 ? time / 100 : time / 10);
      }
   }
}
