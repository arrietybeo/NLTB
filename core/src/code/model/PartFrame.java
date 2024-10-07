package code.model;

public class PartFrame {
   public short idSmallImg;
   public short dx;
   public short dy;
   public byte flip;
   public byte onTop = 0;

   public PartFrame(int dx, int dy, int idSmall) {
      this.idSmallImg = (short)idSmall;
      this.dx = (short)dx;
      this.dy = (short)dy;
   }
}
