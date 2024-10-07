package code.model;

import lib.mHashtable;
import lib.mVector;

public class ItemTemplate {
   public String name = "";
   public String decript = "";
   public short type;
   public short style;
   public short index;
   public short he;
   public short gender;
   public short level;
   public short durable;
   public short idIcon;
   public short ndayLoan = 0;
   public int price;
   public short[] attb = new short[10];
   public byte clazz;
   public short id;
   public byte plus = 0;
   public byte colorItem;
   public static mHashtable ALL_ITEM_TEMPLATE = new mHashtable();
   public static mVector ALL_NAME_ATTRIBUTE_ITEM = new mVector();
   public mVector allAttribute = new mVector();
   public byte cat;
   public String namemoney;

   public static String getName(int id) {
      ItemTemplate it = (ItemTemplate)ALL_ITEM_TEMPLATE.get(String.valueOf(id));
      return it != null ? it.name : "";
   }
}
