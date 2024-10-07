package code.model;

public class Position {
   public int x;
   public int y;
   public int anchor;
   public byte follow;
   public byte count = 0;
   public byte dir = 1;
   public short index = -1;
   public short indexColor;
   public int id = -1000;

   public Position(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public Position(int x, int y, int fol) {
      this.x = x;
      this.y = y;
      this.follow = (byte)fol;
   }

   public Position() {
   }
}
