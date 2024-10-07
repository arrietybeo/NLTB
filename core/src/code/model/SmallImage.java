package code.model;

import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class SmallImage {
   public short id;
   public short x;
   public short y;
   public short w;
   public short h;

   public SmallImage(int id, int x, int y, int w, int h) {
      this.id = (short)id;
      this.x = (short)x;
      this.y = (short)y;
      this.w = (short)w;
      this.h = (short)h;
   }

   public void paint(mGraphics g, Image img, int x, int y) {
   }
}
