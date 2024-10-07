package code.model;

public class ImageSaveRms {
   public String id = "";
   public byte[] data;

   public ImageSaveRms(int id, byte[] data) {
      this.id = String.valueOf(id);
      this.data = data;
   }
}
