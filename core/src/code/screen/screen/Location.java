package code.screen.screen;

import code.main.GameCanvas;
import lib.mGraphics;
import lib2.mFont;

public class Location {
   public int x = 0;
   public int y = 0;
   public int vx;
   public int vy;
   public int x2;
   public int y2;
   public int dis;
   public int f;
   public String nameMapOut = "";
   public byte rotate;
   public static int[] mTranPointrotate = new int[]{5, 6, 1, 0};

   public void paint(mGraphics g, byte Typepaint) {
      g.drawRegion(GameScr.imghand, 0, 0, mGraphics.getImageWidth(GameScr.imghand), mGraphics.getImageHeight(GameScr.imghand), mTranPointrotate[this.dis], this.x + GameCanvas.gameTick % 6 * this.vx, this.y + GameCanvas.gameTick % 6 * this.vy, 3, false);
      GameScr.Font3d(g, this.nameMapOut, this.x2 + GameCanvas.gameTick % 6 * this.vx, this.y2 + GameCanvas.gameTick % 6 * this.vy, this.f, mFont.tahoma_7b_white);
   }
}
