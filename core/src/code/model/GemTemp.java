package code.model;

import code.screen.Res;

public class GemTemp {
   public static byte TYPE_IMBUE = 0;
   public static byte TYPE_KHAM = 1;
   public static byte TYPE_DUC_LO = 2;
   public static byte TYPE_CREATE_ITEM = 3;
   public static byte TYPE_TU_BINH = 4;
   public static byte TYPE_HOP_ITEM_ANIMAL = 5;
   public static byte TYPE_ADD_NEW_ATTRIBUTE = 6;
   public static byte TYPE_LOCK_ANIMAL = 7;
   public static byte TYPE_LOCK_ITEM = 8;
   public byte shopType;
   public byte type;
   public byte typeEp;
   public String name = "";
   public String decript = "";
   public short value;
   public short number;
   public short id;
   public byte idImage = 0;
   public byte typeMoney;
   public boolean isSell;
   public int price = 0;
   public byte isEff = -1;
   public int count = Res.rnd(68);
   public static int[] color = new int[]{-9109773, -13442560, -72192};
}
