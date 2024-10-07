package code.screen.screen;

import code.main.GameCanvas;
import code.model.MainTeam;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public abstract class ScreenTeam extends MainTeam {
   public static short deltaY = 17;
   public static final int ITEM_HEIGHT;
   public static final int HEIGHT_TF = 27;
   public static int cmtoX;
   public static int cmtoY;
   public static int cmvx;
   public static int cmvy;
   public static int cmdx;
   public static int cmdy;
   public static int cmx;
   public static int cmy;
   public static int cmxLim;
   public static int cmyLim;
   public ScreenTeam lastSCR;
   public static Image[] imgTab;
   public static Image imgPopup;
   public static int[] color;
   public static int[] colorDia;
   static int[] colorLow;

   static {
      ITEM_HEIGHT = FontTeam.normalFont[0].getHeight() + 6;
      imgTab = new Image[18];
      color = new int[]{-5727870, -4543854, -477112, -1315861, -5859456, -3689060, -4807282, -9411756, -1845826, -5267575, -460632};
      colorDia = new int[]{-8902126, -460632, -11467264, -477112, -477112};
      colorLow = new int[]{-2701394, -6517641, -9411756, -7832985, -11844807};
   }

   public void init() {
   }

   public void switchToMe() {
      GameCanvas.currentScreen = this;
   }

   public void switchToMe(boolean isReset) {
      GameCanvas.currentScreen = this;
   }

   public void switchToMe(ScreenTeam lastSCR) {
      GameCanvas.currentScreen = this;
      GameCanvas.currentScreen.lastSCR = null;
      GameCanvas.currentScreen.lastSCR = lastSCR;
   }

   public boolean keyPress(int keyCode) {
      return false;
   }

   public void paint(mGraphics g) {
      GameCanvas.resetTrans(g);
      if (GameCanvas.currentScreen == this && GameCanvas.currentDialog == null) {
         super.paint(g);
      }

   }

   public void update() {
   }

   public void updateKey() {
      if (GameCanvas.currentScreen == this) {
         super.updateKey();
      }

   }

   public void pointerPress(int dx, int dy) {
   }

   public boolean isGameScreen() {
      return false;
   }

   public static void load() {
      for(int i = 0; i < imgTab.length; ++i) {
         if ((i == 9 || i == 10 || i == 15 || i == 16 || i == 17) && imgTab[i] == null) {
            imgTab[i] = GameCanvas.loadImage("/interface/screentab" + i + ".png");
         }
      }

      if (imgPopup == null) {
         imgPopup = GameCanvas.loadImage("/interface/popup.png");
      }

   }

   public static void paintDialogNew(mGraphics g, int xDia, int yDia, int wDia, int hDia, int Indexcolor) {
      g.setColor(-13232632);
      g.fillRect(xDia, yDia, wDia, hDia, false);
      g.setColor(-4760043);
      g.fillRect(xDia + 1, yDia + 1, wDia - 2, hDia - 2, false);
      g.setColor(-13232632);
      g.fillRect(xDia + 2, yDia + 2, wDia - 4, hDia - 4, false);
      int numw = (wDia - 6) / 32;
      int numh = (hDia - 6) / 32;
      if (hDia % 2 != 0) {
         ++hDia;
      }

      int i;
      int j;
      for(i = 0; i <= numw; ++i) {
         for(j = 0; j <= numh; ++j) {
            if (i == numw) {
               if (j == numh) {
                  g.drawImage(imgTab[15], xDia - 3 + wDia - 32, yDia - 3 + hDia - 32, 0, false);
               } else {
                  g.drawImage(imgTab[15], xDia - 3 + wDia - 32, yDia + 3 + 32 * j, 0, false);
               }
            } else if (j == numh) {
               g.drawImage(imgTab[15], xDia + 3 + i * 32, yDia - 3 + hDia - 32, 0, false);
            } else {
               g.drawImage(imgTab[15], xDia + 3 + i * 32, yDia + 3 + 32 * j, 0, false);
            }
         }
      }

      GameCanvas.resetTrans(g);
      g.setClip(xDia + 3, yDia + 10 + GameCanvas.hCommand + GameCanvas.hCommand / 2, wDia - 6, hDia - 16 - GameCanvas.hCommand - GameCanvas.hCommand / 2 + 3);
      g.ClipRec(xDia + 3, yDia + 10 + GameCanvas.hCommand + GameCanvas.hCommand / 2, wDia - 6, hDia - 16 - GameCanvas.hCommand - GameCanvas.hCommand / 2 + 3);
      i = (wDia - 6) / 32;

      for(j = 1; j < i + 1; ++j) {
         g.drawImage(imgTab[16], xDia + 1 + j * 32, yDia + 13 + GameCanvas.hCommand, 0, false);
      }

      g.restoreCanvas();
      GameCanvas.resetTrans(g);
   }

   public static void paintDialog(mGraphics g, int xDia, int yDia, int wDia, int hDia, int Indexcolor) {
      if (wDia < 35) {
         wDia = 35;
      }

      int numw = (wDia - 6) / 32;
      int numh = (hDia - 6) / 32;
      if (hDia % 2 != 0) {
         ++hDia;
      }

      if (GameCanvas.lowGraphic) {
         if (Indexcolor > 2) {
            if (Indexcolor != 8 && Indexcolor != 12) {
               Indexcolor = 3;
            } else {
               Indexcolor = 4;
            }
         }

         g.setColor(colorDia[0]);
         g.fillRect(xDia, yDia, wDia, hDia, false);
         g.setColor(colorDia[4]);
         g.drawRect(xDia + 1, yDia + 1, wDia - 3, hDia - 3, false);
         paintRectLowGraphic(g, xDia + 3, yDia + 3, wDia - 6, hDia - 6, Indexcolor);
      } else {
         int i;
         int j;
         if (hDia < 32) {
            for(i = 0; i <= numw; ++i) {
               for(j = 0; j <= numh; ++j) {
                  if (i == numw) {
                     if (j == numh) {
                        g.drawRegion(imgTab[Indexcolor], 0, 0, 32, hDia, 0, xDia - 3 + wDia - 32, yDia, 0, false);
                     } else {
                        g.drawRegion(imgTab[Indexcolor], 0, 0, 32, hDia, 0, xDia - 3 + wDia - 32, yDia + 3 + 32 * j, 0, false);
                     }
                  } else if (j == numh) {
                     g.drawRegion(imgTab[Indexcolor], 0, 0, 32, hDia, 0, xDia + 3 + i * 32, yDia, 0, false);
                  } else {
                     g.drawRegion(imgTab[Indexcolor], 0, 0, 32, hDia, 0, xDia + 3 + i * 32, yDia + 3 + 32 * j, 0, false);
                  }
               }
            }
         } else {
            for(i = 0; i <= numw; ++i) {
               for(j = 0; j <= numh; ++j) {
                  if (i == numw) {
                     if (j == numh) {
                        g.drawImage(imgTab[Indexcolor], xDia - 3 + wDia - 32, yDia - 3 + hDia - 32, 0, false);
                     } else {
                        g.drawImage(imgTab[Indexcolor], xDia - 3 + wDia - 32, yDia + 3 + 32 * j, 0, false);
                     }
                  } else if (j == numh) {
                     g.drawImage(imgTab[Indexcolor], xDia + 3 + i * 32, yDia - 3 + hDia - 32, 0, false);
                  } else {
                     g.drawImage(imgTab[Indexcolor], xDia + 3 + i * 32, yDia + 3 + 32 * j, 0, false);
                  }
               }
            }
         }

         g.drawRegion(imgPopup, 0, 0, 5, 5, 0, xDia, yDia, 0, false);
         g.drawRegion(imgPopup, 0, 5, 5, 5, 0, xDia + wDia - 5, yDia, 0, false);
         g.drawRegion(imgPopup, 0, 15, 5, 5, 0, xDia, yDia + hDia - 5, 0, false);
         g.drawRegion(imgPopup, 0, 10, 5, 5, 0, xDia + wDia - 5, yDia + hDia - 5, 0, false);
         g.setColor(colorDia[0]);
         g.fillRect(xDia + 3, yDia, wDia - 6, 1, false);
         g.fillRect(xDia, yDia + 3, 1, hDia - 6, false);
         g.setColor(colorDia[1]);
         g.fillRect(xDia + 3, yDia + 1, wDia - 6, 1, false);
         g.fillRect(xDia + 1, yDia + 3, 1, hDia - 6, false);
         g.setColor(colorDia[2]);
         g.fillRect(xDia + 3, yDia + 2, wDia - 6, 1, false);
         g.fillRect(xDia + 2, yDia + 3, 1, hDia - 6, false);
         g.setColor(colorDia[2]);
         g.fillRect(xDia + 3, yDia + hDia - 1, wDia - 6, 1, false);
         g.fillRect(xDia + wDia - 1, yDia + 3, 1, hDia - 6, false);
         g.setColor(colorDia[4]);
         g.fillRect(xDia + 3, yDia + hDia - 2, wDia - 6, 1, false);
         g.fillRect(xDia + wDia - 2, yDia + 3, 1, hDia - 6, false);
         g.setColor(colorDia[0]);
         g.fillRect(xDia + 3, yDia + hDia - 3, wDia - 6, 1, false);
         g.fillRect(xDia + wDia - 3, yDia + 3, 1, hDia - 6, false);
      }

   }

   public static void paintRectLowGraphic(mGraphics g, int x, int y, int w, int h, int indexColor) {
      g.setColor(colorLow[indexColor]);
      g.fillRect(x, y, w, h, true);
   }

   public boolean isScrMainMenu() {
      return false;
   }

   public void doChangeInfo(boolean isMeTouch) {
   }

   public boolean isMapScr() {
      return false;
   }
}
