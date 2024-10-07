package code.model;

public class NameAttributeItem {
   public short id;
   public short typeOption = 0;
   public short idColor = 0;
   public String name = "";

   public NameAttributeItem(int id, String name, byte ispercent) {
      this.id = (short)id;
      this.name = name;
      this.typeOption = ispercent;
   }

   public short getColorPaint(boolean kick) {
      return this.idColor;
   }

   public boolean isNormal() {
      return this.typeOption == 0;
   }

   public boolean isPercent() {
      return this.typeOption == 1;
   }

   public boolean isTime() {
      return this.typeOption == 2;
   }

   public boolean isPercentSecond() {
      return this.typeOption == 3;
   }

   public boolean isPointSecond() {
      return this.typeOption == 4;
   }

   public boolean isOptionMinMax() {
      return this.typeOption == 5;
   }

   public boolean isOptionPercentPercent() {
      return this.typeOption == 7;
   }
}
