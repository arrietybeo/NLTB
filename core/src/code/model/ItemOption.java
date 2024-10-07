package code.model;

import code.screen.Util;
import code.screen.screen.InfoTextShow;
import lib.mVector;

public class ItemOption {
   public int value;
   public int value2;
   public short id;
   public byte idColor = 0;

   public short getColorPaint(boolean kick) {
      return ((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).getColorPaint(kick);
   }

   public String getName(int clazz) {
      String name = ((NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id)).name;
      return name;
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

   public short getID() {
      return this.id;
   }

   public boolean isNormal() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 0;
   }

   public boolean isPercent() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 1;
   }

   public boolean isTime() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 2;
   }

   public boolean isPercentSecond() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 3;
   }

   public boolean isPointSecond() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 4;
   }

   public boolean isOptionMinMax() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 5;
   }

   public boolean isOptionPercentPercent() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.typeOption == 7;
   }

   public short[] getColorPaint() {
      short[] info = new short[1];
      if (this.value2 > 0) {
         info = new short[2];
      }

      for(int i = 0; i < info.length; ++i) {
         info[i] = this.getColorPaint(true);
      }

      return info;
   }

   public String[] getInfoShow(int level) {
      String[] info = new String[1];
      if (this.value2 > 0) {
         info = new String[2];
      }

      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      if (!name.isNormal() && !name.isPercent() && !name.isTime() && !name.isOptionMinMax()) {
         if (name.isOptionPercentPercent()) {
            info[0] = Util.replaceString(name.name, "#", Util.getDotPercent(this.value));
            info[0] = Util.replaceString(info[0], "#", Util.getDotPercent(this.value2));
         } else {
            info[0] = Util.replaceString(name.name, "#", name.isPercentSecond() ? Util.getDotPercent(this.value) : String.valueOf(this.value));
            info[0] = Util.replaceString(info[0], "#", String.valueOf(this.value2));
         }
      } else {
         if (name.isOptionMinMax()) {
            info = new String[1];
         }

         String vltime = !name.isPercent() && !name.isPercentSecond() ? (name.isTime() ? Util.convertMilis2S(this.value) : String.valueOf(this.value)) : Util.getDotPercent(this.value);
         info[0] = Util.replaceString(name.name, "#", vltime);
         if (this.value2 > 0) {
            String vltemp = String.valueOf(this.value2);
            if (name.isPercent()) {
               vltemp = Util.getDotPercent(this.value2);
            }

            if (name.isOptionMinMax()) {
               info[0] = Util.replaceString(info[0], "#", vltemp);
            } else {
               info[1] = Util.replaceString(name.name + " " + T.dongdoi, "#", name.isPercent() ? Util.getDotPercent(this.value) : String.valueOf(this.value));
            }
         }
      }

      return info;
   }

   public mVector getInfoTextShow(int level) {
      String[] info = new String[1];
      mVector minfo = new mVector();
      if (this.value2 > 0) {
         info = new String[2];
      }

      InfoTextShow in = null;
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      if (!name.isNormal() && !name.isPercent() && !name.isTime() && !name.isOptionMinMax()) {
         if (name.isOptionPercentPercent()) {
            info[0] = Util.replaceString(name.name, "#", Util.getDotPercent(this.value));
            info[0] = Util.replaceString(info[0], "#", Util.getDotPercent(this.value2));
            in = new InfoTextShow(new String[]{info[0]}, name.idColor);
            minfo.addElement(in);
         } else {
            info[0] = Util.replaceString(name.name, "#", name.isPercentSecond() ? Util.getDotPercent(this.value) : String.valueOf(this.value));
            info[0] = Util.replaceString(info[0], "#", String.valueOf(this.value2));
            in = new InfoTextShow(new String[]{info[0]}, name.idColor);
            minfo.addElement(in);
         }
      } else {
         if (name.isOptionMinMax()) {
            info = new String[1];
         }

         String vltime = !name.isPercent() && !name.isPercentSecond() ? (name.isTime() ? Util.convertMilis2S(this.value) : String.valueOf(this.value)) : Util.getDotPercent(this.value);
         info[0] = Util.replaceString(name.name, "#", vltime);
         if (this.value2 > 0) {
            String vltemp = String.valueOf(this.value2);
            if (name.isPercent()) {
               vltemp = Util.getDotPercent(this.value2);
            }

            if (name.isOptionMinMax()) {
               info[0] = Util.replaceString(info[0], "#", vltemp);
               in = new InfoTextShow(new String[]{info[0]}, name.idColor);
               minfo.addElement(in);
            } else {
               info[1] = Util.replaceString(name.name + " " + T.dongdoi, "#", name.isPercent() ? Util.getDotPercent(this.value) : String.valueOf(this.value));
               in = new InfoTextShow(new String[]{info[1]}, name.idColor);
               minfo.addElement(in);
            }
         } else {
            in = new InfoTextShow(new String[]{info[0]}, name.idColor);
            minfo.addElement(in);
         }
      }

      return minfo;
   }
}
