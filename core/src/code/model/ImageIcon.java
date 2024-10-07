package code.model;

import code.main.GameCanvas;
import javax.microedition.lcdui.Image;

public class ImageIcon {
   public Image img;
   public short id;
   public long timeRemove;
   public boolean isLoad;
   public long timeGetBack;

   public void loadImage() {
      try {
         this.img = GameCanvas.createImage("pathhinhtuong ung");
      } catch (Exception var2) {
      }

   }

   public void reset() {
      this.img = null;
   }

   public int getHeight() {
      return this.img != null ? this.img.getHeight() : 0;
   }

   public int getWidth() {
      return this.img != null ? this.img.getWidth() : 0;
   }
}
