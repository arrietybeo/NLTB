package code.model;

public class QuestInfo {
   public static final byte DA_HOAN_THANH = 0;
   public static final byte CO_THE_NHAN = 1;
   public static final byte DA_NHAN = 2;
   public static final byte KHONG_THE_NHAN = 3;
   public static final byte DA_HOAN_THANH_CHUA_TRA = 4;
   public int idMap;
   public int status;
   public String name;
   public String info;
   public byte mainsub = 0;
   public short idQuest = 0;
}
