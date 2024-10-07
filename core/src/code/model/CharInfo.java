package code.model;

public class CharInfo {
   public short id;
   public String name;
   public short x;
   public short y;
   public byte headStyle;
   public byte idBoss;
   public byte clazz;
   public byte pk;
   public byte lv;
   public byte nskill;
   public byte speed = 3;
   public byte rideHorse = -1;
   public int hp;
   public int maxhp;
   public int mp;
   public int maxmp;
   public int he;
   public short killer;
   public short defend;
   public short idClan = -1;
   public boolean isKiller;
   public boolean isMaster;
   public byte[] buffType = new byte[]{-1, -1, -1, -1, -1, -1, -1};
   public short[] countDown = new short[]{-1, -1, -1, -1, -1, -1, -1};
}
