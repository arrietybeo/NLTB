package code.model;

import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Potion {
   public int number;
   public int numTrade;
   public long delay;
   public short id;
   public short index;
   public boolean isTrade;
   public String name;
   public String name2;

   public void paint(mGraphics g, Image img, int x, int y, int align) {
      g.drawImage(img, x, y, align, false);
   }
}
