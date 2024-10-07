package code.model;

import code.main.GameCanvas;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Thac {
   public byte frame;
   public byte countFrame;
   public int x;
   public int y;
   public static byte[] arrFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
   public byte[] height = new byte[0];
   public static Image[] img = new Image[6];

   public static void load() {
      for(int i = 0; i < img.length; ++i) {
         img[i] = GameCanvas.loadImage("/tilethac/" + i + ".png");
      }

   }

   public void paint(mGraphics g, int type, int x, int y) {
      g.drawRegion(img[type], 0, this.frame * 16, 16, 16, 0, x, y, 0, false);
   }

   public void update() {
      this.frame = arrFrame[this.countFrame];
      ++this.countFrame;
      if (this.countFrame > arrFrame.length - 1) {
         this.countFrame = 0;
      }

   }
}
