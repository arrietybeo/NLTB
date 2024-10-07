package code.screen;

import code.model.Point;
import code.screen.screen.FontTeam;
import lib.mGraphics;

public class Soar {
   public static final byte TYPE_CENTER = 0;
   public static final byte TYPE_LEFT = 1;
   public static final byte TYPE_RIGHT = 2;
   public String str;
   public FontTeam font;
   public int index;
   public int indexMax = 25;
   public Point point;
   public int type = 0;
   public int x;
   public int y;

   public Soar(String str, FontTeam font, Point point) {
      this.str = str;
      this.font = font;
      this.point = point;
      this.x = point.x;
      this.y = point.y;
      this.type = Utils.randomNumber(3);
   }

   public Soar(String str, FontTeam font, Point point, int indexMax) {
      this.str = str;
      this.font = font;
      this.point = point;
      this.x = point.x;
      this.y = point.y;
      this.type = Utils.randomNumber(3);
      this.indexMax = indexMax;
   }

   public void update() {
      ++this.index;
      if (this.index == this.indexMax) {
         this.point.soars.removeElement(this);
      }

   }

   public void paint(mGraphics g) {
      int dx = 0;
      if (this.type == 1) {
         dx = this.index / 3;
      } else if (this.type == 2) {
         dx = -this.index / 3;
      }

      this.font.drawString(g, this.str, this.x + dx, this.y - this.point.pHeight - this.index - 5, 2, false);
   }
}
