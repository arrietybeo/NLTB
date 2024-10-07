package code.model;

import code.main.GameCanvas;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;
import javax.microedition.lcdui.Image;
import lib.mGraphics;
import lib.mVector;

public class MenuKey extends MainTeam implements IActionListener {
   public mVector menuItems;
   public int selected;
   public int menuX;
   public int menuY;
   public int menuW;
   public int menuH;
   public int menuTemY;
   public static int cmtoY;
   public static int cmy;
   public static int cmdy;
   public static int cmvy;
   public static int cmyLim;
   public static int xc;
   public static Image img = null;
   int pa = 0;
   boolean transX = false;
   boolean trans;
   int cvyb;
   int cdyb;
   int ctoyBag;
   int cyBag = 0;
   int cylim = 0;

   public MenuKey() {
      this.right = new mCommand(GameCanvas.isTouch ? "" : "Đóng", this, 0);
      this.center = new mCommand("", this, 1);
      this.left = new mCommand(GameCanvas.isTouch ? "" : "Chọn", this, 2);
   }

   public void setIndex() {
      if (this.menuItems != null) {
         this.selected = GameScr.r.nextInt(this.menuItems.size());
         this.ctoyBag = this.selected * ScreenTeam.ITEM_HEIGHT - ScreenTeam.ITEM_HEIGHT * 2;
         if (this.ctoyBag < 0) {
            this.ctoyBag = 0;
         }

         if (this.ctoyBag > this.cylim) {
            this.ctoyBag = this.cylim;
         }
      }

   }

   public void startAt(mVector menuItems, int pos) {
      if (menuItems.size() > 0) {
         img = null;
         this.menuItems = menuItems;
         this.menuW = 0;
         this.menuH = 0;

         for(int i = 0; i < menuItems.size(); ++i) {
            mCommand c = (mCommand)menuItems.elementAt(i);
            int w = FontTeam.normalFont[0].getWidth(c.caption);
            if (w > this.menuW) {
               this.menuW = w;
            }

            this.menuH += ScreenTeam.ITEM_HEIGHT;
         }

         this.menuW += 10;
         if (this.menuW < 100) {
            this.menuW = 100;
         }

         if (this.menuH > ScreenTeam.ITEM_HEIGHT * 4) {
            this.menuH = ScreenTeam.ITEM_HEIGHT * 4;
         }

         this.menuH += 4;
         this.menuY = GameCanvas.h - 27 - this.menuH;
         if (pos == 0) {
            this.menuX = 2;
         } else if (pos == 1) {
            this.menuX = GameCanvas.w - this.menuW - 2;
         } else if (pos == 2) {
            this.menuX = (GameCanvas.w >> 1) - (this.menuW >> 1);
            this.menuY = GameCanvas.h - this.menuH >> 1;
         } else {
            this.menuX = (GameCanvas.w >> 1) - (this.menuW >> 1);
         }

         if (GameCanvas.h < 200) {
            this.menuY += 10;
         }

         this.menuTemY = GameCanvas.h - ScreenTeam.ITEM_HEIGHT;
         this.showMenu = true;
         this.selected = 0;
         this.cylim = (this.menuItems.size() - 4) * ScreenTeam.ITEM_HEIGHT;
         if (this.cylim < 0) {
            this.cylim = 0;
         }

         this.selected = 0;
         this.cyBag = 0;
         this.ctoyBag = 0;
         if (GameCanvas.isBB) {
            GameCanvas.clearKeyPressed();
         }

      }
   }

   private void setKey(int dir) {
      this.selected += dir;
      if (this.selected < 0) {
         this.selected = this.menuItems.size() - 1;
      }

      if (this.selected > this.menuItems.size() - 1) {
         this.selected = 0;
      }

      this.ctoyBag = this.selected * ScreenTeam.ITEM_HEIGHT - ScreenTeam.ITEM_HEIGHT * 2;
      if (this.ctoyBag < 0) {
         this.ctoyBag = 0;
      }

      if (this.ctoyBag > this.cylim) {
         this.ctoyBag = this.cylim;
      }

   }

   public void updateKey() {
      if (GameCanvas.isKeyPressed(2)) {
         this.setKey(-1);
      } else if (GameCanvas.isKeyPressed(8)) {
         this.setKey(1);
      }

      int b;
      int a;
      if (GameCanvas.isPointerDown[0] && GameCanvas.isPointer(this.menuX, this.menuY, this.menuW, this.menuH, 0)) {
         if (!this.trans) {
            this.pa = this.cyBag;
            this.trans = true;
            this.cyBag = this.ctoyBag;
         }

         b = this.menuTemY + 2;
         a = (this.ctoyBag + GameCanvas.py[0] - b) / ScreenTeam.ITEM_HEIGHT;
         if (a < 0) {
            a = 0;
         }

         if (a > this.menuItems.size() - 1) {
            a = this.menuItems.size() - 1;
         }

         this.selected = a;
         if (Math.abs(GameCanvas.pyLast[0] - GameCanvas.py[0]) != 0) {
            this.ctoyBag = this.pa + (GameCanvas.pyLast[0] - GameCanvas.py[0]);
            if (this.ctoyBag < 0) {
               this.ctoyBag = 0;
            }

            if (this.ctoyBag > this.cylim) {
               this.ctoyBag = this.cylim;
            }

            this.selected = -1;
         }
      }

      if (GameCanvas.isPointerClick[0]) {
         this.trans = false;
         if (GameCanvas.isPointer(this.menuX, this.menuY, this.menuW, this.menuH, 0)) {
            GameCanvas.isPointerClick[0] = false;
            b = this.menuTemY + 2;
            a = (this.ctoyBag + GameCanvas.py[0] - b) / ScreenTeam.ITEM_HEIGHT;
            if (a < 0) {
               a = 0;
            }

            if (a > this.menuItems.size() - 1) {
               a = this.menuItems.size() - 1;
            }

            if (Math.abs(GameCanvas.pyLast[0] - GameCanvas.py[0]) <= 10 && this.cyBag == this.ctoyBag) {
               this.selected = a;
               if (this.selected != -1 && this.center != null) {
                  this.center.performAction();
               }
            }
         }
      }

      this.updateMenu();
      super.updateKey();
   }

   public void paint(mGraphics g) {
      GameCanvas.resetTrans(g);
      if (this.cyBag != this.ctoyBag) {
         this.cvyb = this.ctoyBag - this.cyBag << 2;
         this.cdyb += this.cvyb;
         this.cyBag += this.cdyb >> 4;
         this.cdyb &= 15;
         if (this.cyBag < 0) {
            this.cyBag = 0;
         }
      }

      g.setColor(11908533);
      g.fillRect(this.menuX - 2, this.menuY - 2, this.menuW + 4, this.menuH + 5, false);
      g.setColor(-14595766);
      g.fillRect(this.menuX, this.menuTemY, this.menuW, this.menuH, false);
      g.setClip(this.menuX - 2, this.menuY, this.menuW + 5, this.menuH + 3);
      g.ClipRec(this.menuX - 2, this.menuY, this.menuW + 5, this.menuH + 3);
      g.translate(this.menuX + 5, this.menuTemY + 2);

      for(int i = 0; i < this.menuItems.size(); ++i) {
         FontTeam font = FontTeam.arialFontW;
         if (this.selected != -1 && i == this.selected) {
            g.setColor(-5282048);
            g.fillRect(-3, i * ScreenTeam.ITEM_HEIGHT - this.cyBag, this.menuW - 4, ScreenTeam.ITEM_HEIGHT, false);
            font = FontTeam.normalFont[0];
         }

         if (GameCanvas.isBB) {
            FontTeam.borderFont.drawString(g, ((mCommand)this.menuItems.elementAt(i)).caption, GameCanvas.hw, 3 + i * ScreenTeam.ITEM_HEIGHT - this.cyBag, 2, false);
         } else {
            font.drawString(g, ((mCommand)this.menuItems.elementAt(i)).caption, 0, 3 + i * ScreenTeam.ITEM_HEIGHT - this.cyBag, 0, false);
         }
      }

      mGraphics.resetTransAndroid(g);
      g.restoreCanvas();
      super.paint(g);
   }

   public void paintMenu(mGraphics g) {
      GameCanvas.resetTrans(g);
      if (this.cyBag != this.ctoyBag) {
         this.cvyb = this.ctoyBag - this.cyBag << 2;
         this.cdyb += this.cvyb;
         this.cyBag += this.cdyb >> 4;
         this.cdyb &= 15;
         if (this.cyBag < 0) {
            this.cyBag = 0;
         }
      }

      g.setColor(11908533);
      g.fillRect(this.menuX - 2, this.menuY - 2, this.menuW + 4, this.menuH + 5, false);
      g.setColor(-14595766);
      g.fillRect(this.menuX, this.menuTemY, this.menuW, this.menuH, false);
      g.setClip(this.menuX - 2, this.menuY, this.menuW + 5, this.menuH + 3);
      g.ClipRec(this.menuX - 2, this.menuY, this.menuW + 5, this.menuH + 3);
      g.translate(this.menuX + 5, this.menuTemY + 2);

      for(int i = 0; i < this.menuItems.size(); ++i) {
         FontTeam font = FontTeam.arialFontW;
         if (this.selected != -1 && i == this.selected) {
            g.setColor(-5282048);
            g.fillRect(-3, i * ScreenTeam.ITEM_HEIGHT - this.cyBag, this.menuW - 4, ScreenTeam.ITEM_HEIGHT, false);
            font = FontTeam.normalFont[0];
         }

         if (GameCanvas.isBB) {
            FontTeam.borderFont.drawString(g, ((mCommand)this.menuItems.elementAt(i)).caption, GameCanvas.hw, 3 + i * ScreenTeam.ITEM_HEIGHT - this.cyBag, 2, false);
         } else {
            font.drawString(g, ((mCommand)this.menuItems.elementAt(i)).caption, 0, 3 + i * ScreenTeam.ITEM_HEIGHT - this.cyBag, 0, false);
         }
      }

      mGraphics.resetTransAndroid(g);
      g.restoreCanvas();
   }

   public void updateMenu() {
      if (this.menuTemY > this.menuY) {
         int delta = this.menuTemY - this.menuY >> 1;
         if (delta < 1) {
            delta = 1;
         }

         this.menuTemY -= delta;
      }

      this.menuTemY = this.menuY;
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         this.showMenu = false;
         GameCanvas.clearKeyHold();
         GameCanvas.clearKeyPressed();
         GameCanvas.isPointerDown[0] = false;
         break;
      case 1:
      case 2:
         this.showMenu = false;
         if (this.selected != -1) {
            if (GameScr.iTaskAuto == 2) {
               this.selected = 0;
            }

            if (Math.abs(GameCanvas.px[0] - GameCanvas.pxLast[0]) < 10 && Math.abs(GameCanvas.py[0] - GameCanvas.pyLast[0]) < 10) {
               mCommand c = (mCommand)this.menuItems.elementAt(this.selected);
               c.performAction();
            }
         }
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
