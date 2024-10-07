package code.model;

import code.main.GameCanvas;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mVector;
import lib2.mFont;

public abstract class MainTeam {
   public boolean showMenu;
   public mCommand left;
   public mCommand center;
   public mCommand right;
   public static int hTab = 35;
   public static int cmdW = 80;
   public static int cmdH = 30;
   public static int xLCmd;
   public static int xCCmd;
   public static int xRCmd;
   public static int yLCmd;
   public static int yCCmd;
   public static int yRCmd;
   public static int ITEM_HEIGHT = 30;
   public static int selected;
   public static int keyTouch = -1;
   boolean isQwerty = false;

   public void startAt(mVector vt, int pos) {
   }

   public void setIndex() {
   }

   public void paint(mGraphics g) {
      GameCanvas.resetTrans(g);
      if (!GameCanvas.isTouch) {
         if (this.left != null) {
            mFont.tahoma_7b_black.drawString(g, this.left.caption, this.left.x + 5 + 1, GameCanvas.h - 15 + 1, 0, false);
            FontTeam.fontTile.drawString(g, this.left.caption, this.left.x + 5, GameCanvas.h - 15, 0, false);
         }

         if (this.center != null) {
            mFont.tahoma_7b_black.drawString(g, this.center.caption, GameCanvas.hw + 1, GameCanvas.h - 15 + 1, 2, false);
            FontTeam.fontTile.drawString(g, this.center.caption, GameCanvas.hw, GameCanvas.h - 15, 2, false);
         }

         if (this.right != null) {
            mFont.tahoma_7b_black.drawString(g, this.right.caption, GameCanvas.w - 5 + 1, GameCanvas.h - 15 + 1, 1, false);
            FontTeam.fontTile.drawString(g, this.right.caption, GameCanvas.w - 5, GameCanvas.h - 15, 1, false);
         }
      } else {
         this.paintCmdBar(g, this.left, this.center, this.right);
      }

   }

   public void paintCmdBar(mGraphics g, mCommand left, mCommand center, mCommand right) {
      FontTeam f = FontTeam.fontTile;
      int d = 5;
      int w0;
      int h0;
      if (left != null && !left.caption.equals("")) {
         if (left.x > 0 && left.y > 0) {
            left.paint(g);
         } else {
            w0 = mGraphics.getImageWidth(GameScr.imgButton[0]);
            h0 = mGraphics.getImageHeight(GameScr.imgButton[0]) / 2;
            g.drawRegion(GameScr.imgButton[0], 0, h0 * (keyTouch == 0 ? 1 : 0), w0, h0, 0, xLCmd, yLCmd, 0, false);
            f.drawString(g, left.caption, 39, GameCanvas.h - cmdH + d, 2, false);
         }
      }

      if (center != null && !center.caption.equals("")) {
         if (center.x > 0 && center.y > 0) {
            center.paint(g);
         } else {
            w0 = mGraphics.getImageWidth(GameScr.imgButton[0]);
            h0 = mGraphics.getImageHeight(GameScr.imgButton[0]) / 2;
            g.drawRegion(GameScr.imgButton[0], 0, h0 * (keyTouch == 0 ? 1 : 0), w0, h0, 0, xCCmd, yCCmd, 0, false);
            f.drawString(g, center.caption, GameCanvas.hw, GameCanvas.h - cmdH + d, 2, false);
         }
      }

      if (right != null && !right.caption.equals("")) {
         if (right.x > 0 && right.y > 0) {
            right.paint(g);
         } else {
            w0 = mGraphics.getImageWidth(GameScr.imgButton[0]);
            h0 = mGraphics.getImageHeight(GameScr.imgButton[0]) / 2;
            g.drawRegion(GameScr.imgButton[0], 0, h0 * (keyTouch == 0 ? 1 : 0), w0, h0, 0, xRCmd, yRCmd, 0, false);
            f.drawString(g, right.caption, GameCanvas.w - 39, GameCanvas.h - cmdH + d, 2, false);
         }
      }

   }

   public boolean getCmdPointerLast(mCommand cmd) {
      if (cmd == null) {
         return false;
      } else {
         if (GameCanvas.isTouch) {
            if (cmd == this.left) {
               cmd.x = xLCmd;
               cmd.y = yLCmd;
            }

            if (cmd == this.right) {
               cmd.x = xRCmd;
               cmd.y = yRCmd;
            }

            if (cmd == this.center) {
               cmd.x = xCCmd + (this.left != null && GameCanvas.isTouch ? 38 : 0);
               cmd.y = yCCmd;
            }
         }

         return cmd.isPointerPressInside();
      }
   }

   public void setpostCmd() {
      if (GameCanvas.isTouch) {
         if (this.left != null) {
            this.left.x = xLCmd;
            this.left.y = yLCmd;
         }

         if (this.right != null) {
            this.right.x = xRCmd;
            this.right.y = yRCmd;
         }

         if (this.center != null) {
            this.center.x = xCCmd + (this.left != null && GameCanvas.isTouch ? 38 : 0);
            this.center.y = yCCmd;
         }
      }

   }

   public void updateKey() {
      if (!GameCanvas.keyPressedz[5] && !this.getCmdPointerLast(this.center)) {
         if (!GameCanvas.keyPressedz[12] && !this.getCmdPointerLast(this.left)) {
            if (GameCanvas.isBB) {
               if ((GameCanvas.keyReleasedz[13] || this.getCmdPointerLast(this.right)) && this.right != null) {
                  GameCanvas.keyReleasedz[13] = false;
                  keyTouch = -1;
                  GameCanvas.isPointerJustRelease[0] = false;
                  if (this.right != null) {
                     this.right.performAction();
                  }
               }
            } else if ((GameCanvas.keyPressedz[13] || this.getCmdPointerLast(this.right)) && this.right != null) {
               GameCanvas.keyPressedz[13] = false;
               keyTouch = -1;
               GameCanvas.isPointerJustRelease[0] = false;
               if (this.right != null) {
                  this.right.performAction();
               }
            }
         } else if (this.left != null) {
            GameCanvas.keyPressedz[12] = false;
            keyTouch = -1;
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.left != null) {
               this.left.performAction();
            }
         }
      } else if (this.center != null) {
         GameCanvas.keyPressedz[5] = false;
         keyTouch = -1;
         GameCanvas.isPointerJustRelease[0] = false;
         if (this.center != null) {
            this.center.performAction();
         }
      }

   }
}
