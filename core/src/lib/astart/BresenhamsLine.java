package lib.astart;

import code.model.Point;
import lib.mVector;

public class BresenhamsLine {
   public static mVector getPointsOnLine(Point p1, Point p2) {
      Point b = p2;
      mVector pointsOnLine = new mVector();
      boolean steep = Math.abs(p2.y - p1.y) > Math.abs(p2.x - p1.x);
      int deltaX;
      if (steep) {
         deltaX = p1.x;
         p1.x = p1.y;
         p1.y = deltaX;
         deltaX = p1.x;
         p2.x = p2.y;
         p2.y = deltaX;
      }

      if (p1.x > p2.x) {
         deltaX = p1.x;
         p1.x = p2.x;
         p2.x = deltaX;
         deltaX = p1.y;
         p1.y = p2.y;
         p2.y = deltaX;
      }

      deltaX = p2.x - p1.x;
      int deltaY = Math.abs(p2.y - p1.y);
      int error = deltaX / 2;
      int y = p1.y;
      byte yStep;
      if (p1.y < p2.y) {
         yStep = 1;
      } else {
         yStep = -1;
      }

      for(int x = p1.x; x <= b.x; ++x) {
         if (steep) {
            pointsOnLine.addElement(new Point(y, x));
         } else {
            pointsOnLine.addElement(new Point(x, y));
         }

         error -= deltaY;
         if (error < 0) {
            y += yStep;
            error += deltaX;
         }
      }

      return pointsOnLine;
   }
}
