package code.model;

public class PartyInfo {
   public int id = -1;
   public String name = "";
   public int lv;
   public int clazz;

   public PartyInfo(int id, String name, int lv, int clazz) {
      this.id = id;
      this.name = name;
      this.lv = lv;
      this.clazz = clazz;
   }
}
