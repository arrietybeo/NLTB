package code.screen.screen;

public class Khu {
   private byte trangthai;
   private byte id;
   private String title;

   public Khu(byte id, byte trangthai, String tilee) {
      this.id = id;
      this.trangthai = trangthai;
      this.title = tilee;
   }

   public byte getTrangThai() {
      return this.trangthai;
   }

   public String getTitle() {
      return this.title;
   }
}
