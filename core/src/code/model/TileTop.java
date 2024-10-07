package code.model;

import code.screen.Res;
import lib.mGraphics;

public class TileTop {
   public short x;
   public short y;
   public short index = 0;

   public void paint(mGraphics g) {
      int x0 = this.index % 16;
      int y0 = this.index / 16;
      int dx = x0 << 4;
      int dy = y0 << 4;
      g.drawRegion(Res.imgTile, dx, dy, 16, 16, 0, this.x << 4, this.y << 4, 0, false);
   }

   public void paintSmall(mGraphics g) {
      g.drawRegion(Res.imgtileSmall, 0, this.index, 1, 1, 0, this.x, this.y, 0, false);
   }
}
