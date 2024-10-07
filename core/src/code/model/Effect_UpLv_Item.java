package code.model;

import code.main.GameCanvas;
import lib.mGraphics;

public class Effect_UpLv_Item {
   int wnew = 1;
   int[][] colorBorder = new int[][]{{-1, -1, -1052689, -1052689, -4079167, -4079167, -6250336}, {-14097665, -14097665, -14104585, -14104585, -14114080, -14114080, -14249063}, {-1280, -1280, -1251840, -1251840, -3949821, -3949821, -5594621}, {-423937, -423937, -1473281, -1473281, -3768833, -3768833, -5934642}, {-20158, -20158, -26313, -26313, -34787, -34787, -3179218}};
   int[][] colorBorder1 = new int[][]{{-1, -1, -1052689, -1052689, -4079167, -4079167, -6250336}, {-14097665, -14097665, -14104585, -14104585, -14114080, -14114080, -14249063}, {-1280, -1280, -1251840, -1251840, -3949821, -3949821, -5594621}, {-423937, -423937, -1473281, -1473281, -3768833, -3768833, -5934642}, {-20158, -20158, -26313, -26313, -34787, -34787, -3179218}};
   int[][] colorBorder2 = new int[][]{{-13501949, -13501949, -8586657, -8586657, -7013508, -7013508, -10894272}, {-5741531, -5741531, -2848188, -2848188, -1330821, -1330821, -5220346}, {-291845, -291845, -233476, -233476, -303365, -303365, -130818}, {-8650753, -8650753, -9111561, -9111561, -9898764, -9898764, -14373459}, {-262102, -262102, -323016, -323016, -319677, -319677, -4189649}};
   int[] size = new int[]{1, 1, 1, 1, 1};

   public void paintUpgradeEffect(int x, int y, int upgrade, int w, mGraphics g, int lech) {
      if (upgrade > 0) {
         this.colorBorder = this.colorBorder1;
         if (upgrade > 15) {
            upgrade -= 15;
            this.colorBorder = this.colorBorder2;
         }

         int indexSize = w;
         int start = 0;
         int cl = (upgrade - 1) % 5;
         g.setColor(this.colorBorder[cl][6]);
         g.drawRect(x - w / 2 - lech, y - w / 2 - lech, w, w, true);

         int i;
         int xPos;
         int yPos;
         for(i = start; i < this.size.length; ++i) {
            xPos = x - indexSize / 2 + this.upgradeEffectX(GameCanvas.gameTick * 1 - i * this.wnew, w);
            yPos = y - indexSize / 2 + this.upgradeEffectY(GameCanvas.gameTick * 1 - i * this.wnew, w);
            g.setColor(this.colorBorder[cl][i]);
            g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech, this.size[i], this.size[i], true);
         }

         if (upgrade >= 6 && upgrade < 11) {
            for(i = start; i < this.size.length; ++i) {
               xPos = x - indexSize / 2 + this.upgradeEffectX((GameCanvas.gameTick - indexSize * 2) * 1 - i * this.wnew, w);
               yPos = y - indexSize / 2 + this.upgradeEffectY((GameCanvas.gameTick - indexSize * 2) * 1 - i * this.wnew, w);
               g.setColor(this.colorBorder[cl][i]);
               g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech, this.size[i], this.size[i], true);
            }
         }

         if (upgrade >= 11) {
            for(i = start; i < this.size.length; ++i) {
               xPos = x - indexSize / 2 + this.upgradeEffectX((GameCanvas.gameTick - indexSize * 13 / 10) * 1 - i * this.wnew, w);
               yPos = y - indexSize / 2 + this.upgradeEffectY((GameCanvas.gameTick - indexSize * 13 / 10) * 1 - i * this.wnew, w);
               g.setColor(this.colorBorder[cl][i]);
               g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech, this.size[i], this.size[i], true);
            }

            for(i = start; i < this.size.length; ++i) {
               xPos = x - indexSize / 2 + this.upgradeEffectX((GameCanvas.gameTick - indexSize * 13 / 5) * 1 - i * this.wnew, w);
               yPos = y - indexSize / 2 + this.upgradeEffectY((GameCanvas.gameTick - indexSize * 13 / 5) * 1 - i * this.wnew, w);
               g.setColor(this.colorBorder[cl][i]);
               g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech, this.size[i], this.size[i], true);
            }
         }

      }
   }

   public void paintUpgradeEffectItemColor(int x, int y, int upgrade, int w, mGraphics g, int lech) {
      if (upgrade > 0) {
         this.colorBorder = this.colorBorder1;
         if (upgrade > 15) {
            upgrade -= 15;
            this.colorBorder = this.colorBorder2;
         }

         int indexSize = w;
         int start = 0;
         int cl = (upgrade - 1) % 5;
         g.setColor(this.colorBorder[cl][6]);
         g.drawRect(x - w / 2 - lech, y - w / 2 - lech, w, w + 1, true);

         int i;
         int xPos;
         int yPos;
         byte ysai;
         for(i = start; i < this.size.length; ++i) {
            xPos = x - indexSize / 2 + this.upgradeEffectX(GameCanvas.gameTick * 1 - i * this.wnew, w);
            yPos = y - indexSize / 2 + this.upgradeEffectY(GameCanvas.gameTick * 1 - i * this.wnew, w);
            ysai = 0;
            if (this.upgradeEffectY(GameCanvas.gameTick * 1 - i * this.wnew, w) == w) {
               ysai = 1;
            }

            g.setColor(this.colorBorder[cl][i]);
            g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech + ysai, this.size[i], this.size[i], true);
         }

         if (upgrade >= 6 && upgrade < 11) {
            for(i = start; i < this.size.length; ++i) {
               xPos = x - indexSize / 2 + this.upgradeEffectX((GameCanvas.gameTick - indexSize * 2) * 1 - i * this.wnew, w);
               yPos = y - indexSize / 2 + this.upgradeEffectY((GameCanvas.gameTick - indexSize * 2) * 1 - i * this.wnew, w);
               ysai = 0;
               if (this.upgradeEffectY((GameCanvas.gameTick - indexSize * 2) * 1 - i * this.wnew, w) == w) {
                  ysai = 1;
               }

               g.setColor(this.colorBorder[cl][i]);
               g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech + ysai, this.size[i], this.size[i], true);
            }
         }

         if (upgrade >= 11) {
            for(i = start; i < this.size.length; ++i) {
               xPos = x - indexSize / 2 + this.upgradeEffectX((GameCanvas.gameTick - indexSize * 13 / 10) * 1 - i * this.wnew, w);
               yPos = y - indexSize / 2 + this.upgradeEffectY((GameCanvas.gameTick - indexSize * 13 / 10) * 1 - i * this.wnew, w);
               ysai = 0;
               if (this.upgradeEffectY((GameCanvas.gameTick - indexSize * 13 / 10) * 1 - i * this.wnew, w) == w) {
                  ysai = 1;
               }

               g.setColor(this.colorBorder[cl][i]);
               g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech + ysai, this.size[i], this.size[i], true);
            }

            for(i = start; i < this.size.length; ++i) {
               xPos = x - indexSize / 2 + this.upgradeEffectX((GameCanvas.gameTick - indexSize * 13 / 5) * 1 - i * this.wnew, w);
               yPos = y - indexSize / 2 + this.upgradeEffectY((GameCanvas.gameTick - indexSize * 13 / 5) * 1 - i * this.wnew, w);
               ysai = 0;
               if (this.upgradeEffectY((GameCanvas.gameTick - indexSize * 13 / 5) * 1 - i * this.wnew, w) == w) {
                  ysai = 1;
               }

               g.setColor(this.colorBorder[cl][i]);
               g.fillRect(xPos - this.size[i] / 2 - lech, yPos - this.size[i] / 2 - lech + ysai, this.size[i], this.size[i], true);
            }
         }

      }
   }

   public int upgradeEffectY(int tick, int w) {
      int k = tick % (4 * w);
      if (k >= 0 && k < w) {
         return 0;
      } else if (w <= k && k < w * 2) {
         return k % w;
      } else {
         return w * 2 <= k && k < w * 3 ? w : w - k % w;
      }
   }

   public int upgradeEffectX(int tick, int w) {
      int k = tick % (4 * w);
      if (k >= 0 && k < w) {
         return k % w;
      } else if (w <= k && k < w * 2) {
         return w;
      } else {
         return w * 2 <= k && k < w * 3 ? w - k % w : 0;
      }
   }
}
