package code.model;

import code.effect.new_skill.SkillManager;

public class OptionItem {
   private byte id;
   private short value;

   public OptionItem(byte id, short value) {
      this.id = id;
      this.value = value;
   }

   public short getColorPaint(boolean kick) {
      return ((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).getColorPaint(kick);
   }

   public String getName(int clazz) {
      String name = ((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).name;
      if (this.id >= 43 && this.id <= 57) {
         name = SkillManager.SKILL_NAME[clazz][this.id - 43];
      }

      return name + ": ";
   }

   public boolean isPercent() {
      short pc = ((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).typeOption;
      return pc == 1 || pc == 2;
   }

   public String getValue() {
      String vl = String.valueOf(this.id != 33 && this.id != 34 ? this.value : this.value * 1000);
      if (((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).typeOption == 1) {
         vl = this.value / 10 + "." + this.value % 10;
      }

      return vl;
   }

   public int getValueAtt() {
      return this.value;
   }

   public String getValuePercent(int value) {
      String vl = String.valueOf(this.id != 33 && this.id != 34 ? value : value * 1000);
      if (((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).typeOption == 1) {
         vl = value / 10 + "." + value % 10;
      }

      return vl;
   }

   public byte getID() {
      return this.id;
   }
}
