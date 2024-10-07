package code.model;

import code.main.GameCanvas;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class mImage {
   public long timeUse;
   public boolean isLoad;
   public Image img;
   public int ID_IMAGE;
   public String path;

   public mImage(String path, int id, long time) {
      this.path = path;
      this.ID_IMAGE = id;
      this.timeUse = time;
      this.load(path);
   }

   public mImage() {
   }

   public void load(String path) {
      if (!this.isLoad) {
         if (this.img == null) {
            this.img = GameCanvas.loadImage(path);
         }

         this.isLoad = true;
      }

   }

   public void drawImage(mGraphics g, int x, int y, int archor, boolean isSetclip) {
      g.drawImage(this.img, x, y, archor, isSetclip);
   }

   public void drawRegion(mGraphics g, int x0, int y0, int w0, int h0, int translate, int x, int y, int archor, boolean isSetclip) {
      g.drawRegion(this.img, x0, y0, w0, h0, translate, x, y, archor, isSetclip);
   }
}
