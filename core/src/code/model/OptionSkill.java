package code.model;

import code.screen.Util;
import code.screen.screen.InfoTextShow;
import lib.mVector;

public class OptionSkill {
   public short id;
   public int[][] value;

   public int getIDColor() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name != null ? name.idColor : 0;
   }

   public String[] getInfoShow(int level) {
      String[] info = new String[this.value.length];
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      String vl1;
      if (!name.isNormal() && !name.isPercent() && !name.isTime() && !name.isOptionMinMax()) {
         if (name.isOptionPercentPercent()) {
            info[0] = Util.replaceString(name.name, "#", Util.getDotPercent(this.value[0][level]));
            info[0] = Util.replaceString(info[0], "#", Util.getDotPercent(this.value[1][level]));
         } else {
            vl1 = String.valueOf(this.value[1][level]);
            if (name.isPercentSecond()) {
               vl1 = Util.convertMilis2S(this.value[1][level]);
            }

            info[0] = Util.replaceString(name.name, "#", name.isPercentSecond() ? Util.getDotPercent(this.value[0][level]) : String.valueOf(this.value[0][level]));
            info[0] = Util.replaceString(info[0], "#", vl1);
         }
      } else {
         if (name.isOptionMinMax()) {
            info = new String[1];
         }

         vl1 = String.valueOf(this.value[0][level]);
         if (name.isTime()) {
            vl1 = Util.convertMilis2S(this.value[0][level]);
         } else if (name.isPercent() || name.isPercentSecond()) {
            vl1 = Util.getDotPercent(this.value[0][level]);
         }

         info[0] = Util.replaceString(name.name, "#", vl1);
         if (this.value.length > 1) {
            String vltemp = String.valueOf(this.value[1][level]);
            if (name.isPercent()) {
               vltemp = Util.getDotPercent(this.value[1][level]);
            }

            if (name.isPercentSecond() || name.isPointSecond() || name.isTime()) {
               vltemp = Util.convertMilis2S(this.value[1][level]);
            }

            if (name.isOptionMinMax()) {
               info[0] = Util.replaceString(info[0], "#", vltemp);
            } else {
               info[1] = Util.replaceString(name.name + " " + T.dongdoi, "#", vltemp);
            }
         }
      }

      return info;
   }

   public mVector getInfoTextShow(int level) {
      String[] info = new String[this.value.length];
      mVector minfo = new mVector();
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      String vl1;
      if (!name.isNormal() && !name.isPercent() && !name.isTime() && !name.isOptionMinMax()) {
         if (name.isOptionPercentPercent()) {
            info[0] = Util.replaceString(name.name, "#", Util.getDotPercent(this.value[0][level]));
            info[0] = Util.replaceString(info[0], "#", Util.getDotPercent(this.value[1][level]));
         } else {
            vl1 = String.valueOf(this.value[1][level]);
            if (name.isPercentSecond()) {
               vl1 = Util.convertMilis2S(this.value[1][level]);
            }

            info[0] = Util.replaceString(name.name, "#", name.isPercentSecond() ? Util.getDotPercent(this.value[0][level]) : String.valueOf(this.value[0][level]));
            info[0] = Util.replaceString(info[0], "#", vl1);
            minfo.addElement(new InfoTextShow(new String[]{info[0]}, name.idColor));
         }
      } else {
         if (name.isOptionMinMax()) {
            info = new String[1];
         }

         vl1 = String.valueOf(this.value[0][level]);
         if (name.isTime()) {
            vl1 = Util.convertMilis2S(this.value[0][level]);
         } else if (name.isPercent() || name.isPercentSecond()) {
            vl1 = Util.getDotPercent(this.value[0][level]);
         }

         info[0] = Util.replaceString(name.name, "#", vl1);
         if (this.value.length > 1) {
            String vltemp = String.valueOf(this.value[1][level]);
            if (name.isPercent()) {
               vltemp = Util.getDotPercent(this.value[1][level]);
            }

            if (name.isPercentSecond() || name.isPointSecond() || name.isTime()) {
               vltemp = Util.convertMilis2S(this.value[1][level]);
            }

            if (name.isOptionMinMax()) {
               info[0] = Util.replaceString(info[0], "#", vltemp);
               minfo.addElement(new InfoTextShow(new String[]{info[0]}, name.idColor));
            } else {
               minfo.addElement(new InfoTextShow(new String[]{info[0]}, name.idColor));
               info[1] = Util.replaceString(name.name + " " + T.dongdoi, "#", vltemp);
               minfo.addElement(new InfoTextShow(new String[]{info[1]}, name.idColor));
            }
         } else {
            minfo.addElement(new InfoTextShow(new String[]{info[0]}, name.idColor));
         }
      }

      return minfo;
   }

   public int getIdColorPaint() {
      NameAttributeItem name = (NameAttributeItem)ItemTemplate.ALL_NAME_ATTRIBUTE_ITEM.elementAt(this.id);
      return name.idColor;
   }
}
