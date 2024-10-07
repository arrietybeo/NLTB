package code.model;

public class CharClassInfo {
   public byte classID;
   public String name;
   public String sex;
   public String weapon;
   public String area;
   public String element;
   public short bodyID;
   public short legID;
   public short headID;
   public short weaponType;
   public short weaponImageID;

   public CharClassInfo(byte classID, String name, String sex, String weapon, String area, String element, int weaponType, int weaponImageID) {
      this.classID = classID;
      this.name = name;
      this.sex = sex;
      this.weapon = weapon;
      this.area = area;
      this.element = element;
      if (sex.equals("Nam")) {
         this.bodyID = 2;
         this.legID = 2;
         this.headID = 2;
      } else {
         this.bodyID = 3;
         this.legID = 3;
         this.headID = 3;
      }

      this.weaponType = (short)weaponType;
      this.weaponImageID = (short)weaponImageID;
   }
}
