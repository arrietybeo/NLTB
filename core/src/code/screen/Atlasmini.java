package code.screen;

import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Atlasmini {
   public int mapId;
   public Image img;
   public String name;
   public int x;
   public int y;
   public int minx;
   public int miny;
   public int maxx;
   public int maxy;
   public int yPaint;

   public int getMinx() {
      return this.x - Utils.getWidth(this.img) / 2;
   }

   public int getMaxx() {
      return this.x + Utils.getWidth(this.img) / 2;
   }

   public int getMiny() {
      return this.y - Utils.getHeight(this.img) / 2;
   }

   public int getMaxy() {
      return this.y + Utils.getHeight(this.img) / 2;
   }

   public void paint(mGraphics g) {
      if (this.img != null) {
         g.drawImage(this.img, this.x, this.y, mGraphics.HCENTER | mGraphics.VCENTER, false);
      }

   }
}
