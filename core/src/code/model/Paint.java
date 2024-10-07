package code.model;

import code.main.GameCanvas;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Paint {
   public static int COLORBACKGROUND = 15787715;
   public static int COLORLIGHT = 16383818;
   public static int COLORDARK = 3937280;
   public static int COLORBORDER = 15224576;
   public static int COLORFOCUS = 16777215;
   public static Image imgBg;
   public static Image imgLogo;
   public static Image imgLB;
   public static Image imgLT;
   public static Image imgRB;
   public static Image imgRT;
   public static Image imgChuong;
   public static Image imgSelectBoard;
   public static Image imgtoiSmall;
   public static Image imgTayTren;
   public static Image imgTayDuoi;
   public static Image[] imgTick = new Image[2];
   public static Image[] imgMsg = new Image[2];
   public static Image[] goc = new Image[6];
   public static Image[] imgPaper = new Image[9];
   public static int hTab;
   public static int lenCaption;
   public int[] color = new int[]{15970400, -844109861, 2250052, 16374659, 15906669, 12931125, 3108954};

   static {
      for(int i = 0; i < imgPaper.length; ++i) {
         imgPaper[i] = GameCanvas.loadImage("/interface/paper" + i + ".png");
      }

      hTab = 24;
      lenCaption = 0;
   }

   public void paintDefaultBg(mGraphics g) {
      g.setColor(8916494);
      g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
      g.drawImage(imgBg, GameCanvas.w / 2, GameCanvas.h / 2 - hTab / 2 - 1, 3, false);
      g.drawImage(imgLT, 0, 0, 0, false);
      g.drawImage(imgRT, GameCanvas.w, 0, mGraphics.TOP | mGraphics.RIGHT, false);
      g.drawImage(imgLB, 0, GameCanvas.h - hTab - 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
      g.drawImage(imgRB, GameCanvas.w, GameCanvas.h - hTab - 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
      g.setColor(16774843);
      g.drawRect(0, 0, GameCanvas.w, 0, false);
      g.drawRect(0, GameCanvas.h - hTab - 2, GameCanvas.w, 0, false);
      g.drawRect(0, 0, 0, GameCanvas.h - hTab, false);
      g.drawRect(GameCanvas.w - 1, 0, 0, GameCanvas.h - hTab, false);
   }

   public void paintfillDefaultBg(mGraphics g) {
      g.setColor(205314);
      g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
   }

   public void repaintCircleBg() {
   }

   public void paintSolidBg(mGraphics g) {
   }

   public void paintDefaultPopup(mGraphics g, int x, int y, int w, int h) {
      g.setColor(-8366078);
      g.fillRect(x, y, w, h, false);
      g.setColor(-3170504);
      g.drawRect(x, y, w, h, false);
   }

   public void paintWhitePopup(mGraphics g, int y, int x, int width, int height) {
      g.setColor(16776363);
      g.fillRect(x, y, width, height, false);
      g.setColor(0);
      g.drawRect(x - 1, y - 1, width + 1, height + 1, false);
   }

   public void paintDefaultPopupH(mGraphics g, int h) {
      g.setColor(14279153);
      g.fillRect(8, GameCanvas.h - (h + 37), GameCanvas.w - 16, h + 4, false);
      g.setColor(4682453);
      g.fillRect(10, GameCanvas.h - (h + 35), GameCanvas.w - 20, h, false);
   }

   public void paintCmdBar(mGraphics g, mCommand left, mCommand center, mCommand right) {
      FontTeam f = GameCanvas.isTouch ? FontTeam.arialFont : FontTeam.arialFont;
      int d = 3;
      if (left != null) {
         lenCaption = f.getWidth(left.caption);
         if (lenCaption > 0) {
            if (left.x >= 0 && left.y > 0) {
               left.paint(g);
            } else {
               g.drawImage(ScreenTeam.keyTouch == 0 ? GameScr.imgLbtnFocus : GameScr.imgLbtn, 1, GameCanvas.h - ScreenTeam.cmdH - 1, 0, false);
               f.drawString(g, left.caption, 35, GameCanvas.h - ScreenTeam.cmdH + 3 + d, 2, false);
            }
         }
      }

      if (center != null) {
         lenCaption = f.getWidth(center.caption);
         if (lenCaption > 0) {
            if (center.x > 0 && center.y > 0) {
               center.paint(g);
            } else {
               g.drawImage(ScreenTeam.keyTouch == 1 ? GameScr.imgLbtnFocus : GameScr.imgLbtn, GameCanvas.hw - 35, GameCanvas.h - ScreenTeam.cmdH - 1, 0, false);
               f.drawString(g, center.caption, GameCanvas.hw, GameCanvas.h - ScreenTeam.cmdH + 3 + d, 2, false);
            }
         }
      }

      if (right != null) {
         lenCaption = f.getWidth(right.caption);
         if (lenCaption > 0) {
            if (right.x > 0 && right.y > 0) {
               right.paint(g);
            } else {
               g.drawImage(ScreenTeam.keyTouch == 2 ? GameScr.imgLbtnFocus : GameScr.imgLbtn, GameCanvas.w - 71, GameCanvas.h - ScreenTeam.cmdH - 1, 0, false);
               f.drawString(g, right.caption, GameCanvas.w - 35, GameCanvas.h - ScreenTeam.cmdH + 3 + d, 2, false);
            }
         }
      }

   }

   public void paintTabSoft(mGraphics g) {
   }

   public void paintSelect(mGraphics g, int x, int y, int w, int h) {
      g.setColor(16774843);
      g.fillRect(x, y, w, h, false);
   }

   public void paintLogo(mGraphics g, int x, int y) {
      g.drawImage(imgLogo, x, y, 3, false);
   }

   public void paintBackMenu(mGraphics g, int x, int y, int w, int h, boolean is) {
      if (is) {
         g.setColor(16646144);
         g.fillRoundRect(x, y, w, h, 10, 10, false);
         g.setColor(16770612);
      } else {
         g.setColor(16775097);
         g.fillRoundRect(x, y, w, h, 10, 10, false);
         g.setColor(16775097);
      }

      g.fillRoundRect(x + 3, y + 3, w - 6, h - 6, 10, 10, false);
   }

   public void paintPaper(mGraphics g, int x, int y, int w, int h, int maxw) {
      if (h % 2 == 0) {
         ++h;
      }

      g.setColor(16777215);
      g.fillRect(x, y + 5, w, h - 10, false);
      int maxi = 15;
      int lim = w / 2 - 15;
      if (lim < 0) {
         lim = 0;
      }

      int t;
      for(t = 0; t <= lim; t += 30) {
         g.drawImage(imgPaper[5], x + w / 2 + t - 15, y + 5, 0, false);
         g.drawImage(imgPaper[6], x + w / 2 + t - 15, y + h - 5 - 8, 0, false);
         if (t != 0) {
            g.drawImage(imgPaper[5], x + w / 2 - t - 15, y + 5, 0, false);
            g.drawImage(imgPaper[6], x + w / 2 - t - 15, y + h - 5 - 8, 0, false);
         }

         maxi = t + 15;
      }

      t = (w / 2 - 15) % 30;
      if (t != 0 && t > 0) {
         g.drawRegion(imgPaper[5], 0, 0, t, 8, 0, x + w / 2 + maxi, y + 5, 0, false);
         g.drawRegion(imgPaper[6], 0, 0, t, 8, 0, x + w / 2 + maxi, y + h - 5 - 8, 0, false);
         g.drawRegion(imgPaper[5], 0, 0, t, 8, 0, x + w / 2 - maxi - t, y + 5, 0, false);
         g.drawRegion(imgPaper[6], 0, 0, t, 8, 0, x + w / 2 - maxi - t, y + h - 5 - 8, 0, false);
      }

      int i;
      if (w >= 20) {
         for(i = 0; i < h - 38; i += 10) {
            g.drawRegion(imgPaper[7], 0, 0, 8, 10, 2, x + w, y + 19 + i, mGraphics.RIGHT | mGraphics.TOP, false);
         }

         g.drawRegion(imgPaper[3], 0, 0, 8, 16, 2, x + w, y + 5, mGraphics.RIGHT | mGraphics.TOP, false);
         g.drawRegion(imgPaper[4], 0, 0, 8, 17, 2, x + w, y + h - 6 - 16, mGraphics.RIGHT | mGraphics.TOP, false);
      }

      if (w >= 20) {
         for(i = 0; i < h - 37; i += 10) {
            g.drawImage(imgPaper[7], x, y + 19 + i, 0, false);
         }

         g.drawImage(imgPaper[3], x, y + 5, 0, false);
         g.drawImage(imgPaper[4], x, y + h - 6 - 16, 0, false);
      }

      for(i = 0; i < h - 37; i += 10) {
         g.drawRegion(imgPaper[1], 0, 0, 14, 10, 2, x + w + 14, y + 19 + i, mGraphics.RIGHT | mGraphics.TOP, false);
      }

      g.drawRegion(imgPaper[0], 0, 0, 14, 19, 2, x + w + 14, y, mGraphics.RIGHT | mGraphics.TOP, false);
      g.drawRegion(imgPaper[2], 0, 0, 14, 19, 2, x + w + 14, y + h - 19, mGraphics.RIGHT | mGraphics.TOP, false);

      for(i = 0; i < h - 38; i += 10) {
         g.drawImage(imgPaper[1], x - 14, y + 19 + i, 0, false);
      }

      g.drawImage(imgPaper[0], x - 14, y, 0, false);
      g.drawImage(imgPaper[2], x - 14, y + h - 19, 0, false);
      g.drawRegion(imgPaper[8], 0, 0, 16, 16, 2, x + w, y + 9, mGraphics.RIGHT | mGraphics.TOP, false);
      g.drawImage(imgPaper[8], x, y + 9, 0, false);
      g.drawRegion(imgPaper[8], 0, 0, 16, 16, 7, x + w, y + h - 25, mGraphics.RIGHT | mGraphics.TOP, false);
      g.drawRegion(imgPaper[8], 0, 0, 16, 16, 1, x, y + h - 25, mGraphics.TOP | mGraphics.LEFT, false);
   }

   public void paintFrame(int x, int y, int w, int h, mGraphics g) {
      g.setColor(13524492);
      g.drawRect(x + 6, y, w - 12, h, false);
      g.drawRect(x, y + 6, w, h - 12, false);
      g.drawRect(x + 7, y + 1, w - 14, h - 2, false);
      g.drawRect(x + 1, y + 7, w - 2, h - 14, false);
      g.setColor(14338484);
      g.fillRect(x + 8, y + 2, w - 16, h - 3, false);
      g.fillRect(x + 2, y + 8, w - 3, h - 14, false);
      g.drawImage(GameCanvas.imgBorder[2], x, y, mGraphics.TOP | mGraphics.LEFT, false);
      g.drawRegion(GameCanvas.imgBorder[2], 0, 0, 16, 16, 2, x + w + 1, y, mGraphics.TOP | mGraphics.RIGHT, false);
      g.drawRegion(GameCanvas.imgBorder[2], 0, 0, 16, 16, 1, x, y + h + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
      g.drawRegion(GameCanvas.imgBorder[2], 0, 0, 16, 16, 3, x + w + 1, y + h + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
   }

   public void paintFrameSimple(int x, int y, int w, int h, mGraphics g) {
      g.setColor(6702080);
      g.fillRect(x, y, w, h, false);
      g.setColor(14338484);
      g.fillRect(x + 1, y + 1, w - 2, h - 2, false);
   }

   public void paintFrameBorder(int x, int y, int w, int h, mGraphics g) {
      this.paintFrame(x, y, w, h, g);
   }

   public void paintFrameInside(int x, int y, int w, int h, mGraphics g) {
      g.setColor(COLORBACKGROUND);
      g.fillRect(x, y, w, h, false);
   }

   public void paintFrameInsideSelected(int x, int y, int w, int h, mGraphics g) {
      g.setColor(COLORLIGHT);
      g.fillRect(x, y, w, h, false);
   }
}
