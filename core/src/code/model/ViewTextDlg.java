package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.screen.Dialog;
import code.screen.screen.FontTeam;
import code.screen.screen.ScreenTeam;
import lib.mGraphics;

public class ViewTextDlg extends Dialog {
   private static ViewTextDlg me;
   private String[] list;
   private String title;
   private int x = 50;
   private int y = 20;
   private int w;
   private int h;
   public static int cmtoY;
   public static int cmy;
   public static int cmdy;
   public static int cmvy;
   public static int cmyLim;
   public static int selected = 0;
   public static int lastSe = 0;

   public static ViewTextDlg gI() {
      return me == null ? (me = new ViewTextDlg()) : me;
   }

   public ViewTextDlg() {
      this.w = GameCanvas.w - 100;
      this.h = GameCanvas.h - 40 - ScreenTeam.deltaY;
      this.right = new mCommand("Đóng", this, 1);
      lastSe = (this.h - 30) / 2 / 14;
   }

   public void setInfo(String list, String title) {
      this.list = FontTeam.arialFontW.splitFontBStrInLine(list, this.w - 15);
      this.title = title;
      cmyLim = this.list.length * 14 - (this.h - 25) + 15;
      if (cmyLim < 0) {
         cmyLim = 0;
      }

      cmtoY = 0;
      cmy = 0;
      selected = lastSe;
      this.show();
   }

   public void paint(mGraphics g) {
      GameCanvas.resetTrans(g);
      Res.paintDlgFull(g, this.x, this.y, this.w, this.h);
      FontTeam.bigFont.drawString(g, this.title, this.x + this.w / 2, this.y + 2, 2, false);
      g.translate(this.x, this.y + 25);
      g.setClip(0, 0, this.w, this.h - 30);
      g.translate(0, -cmy);
      int aa = cmy / 14;
      int bb = aa + this.h / 14 + 1;
      if (bb >= this.list.length) {
         bb = this.list.length;
      }

      for(int i = aa; i < bb; ++i) {
         FontTeam.arialFontW.drawString(g, this.list[i], 7, 5 + i * 14, 0, false);
      }

      super.paint(g);
   }

   public void update() {
      boolean changeFocus = false;
      if (GameCanvas.keyHold[2]) {
         --selected;
         if (selected < lastSe) {
            selected = lastSe;
         }

         changeFocus = true;
      } else if (GameCanvas.keyHold[8]) {
         changeFocus = true;
         if (cmy < cmyLim) {
            ++selected;
         }

         if (selected > this.list.length - lastSe + 1) {
            selected = this.list.length - lastSe + 1;
         }
      }

      if (changeFocus) {
         cmtoY = selected * 14 - (this.h - 25) / 2;
         if (cmtoY < 0) {
            cmtoY = 0;
         }

         if (cmtoY > cmyLim) {
            cmtoY = cmyLim;
         }
      }

      if (cmy != cmtoY) {
         cmvy = cmtoY - cmy << 2;
         cmdy += cmvy;
         cmy += cmdy >> 4;
         cmdy &= 15;
      }

      super.updateKey();
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 1:
         GameCanvas.currentDialog = null;
      default:
      }
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
