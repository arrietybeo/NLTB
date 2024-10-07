package code.model;

import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class ChatPopup {
   public int timeOut;
   public int xc;
   public int yc;
   public int arrowType;
   public int h;
   public int w;
   public int isAr;
   String[] chats;
   public static Image imgPopup;

   public ChatPopup() {
   }

   public ChatPopup(int time, String chat, int isAr) {
      this.prepareData(time, chat);
      this.isAr = isAr;
   }

   public void setPos(int x, int y) {
      this.xc = x;
      this.yc = y;
   }

   public boolean setOut() {
      if (this.timeOut > 0) {
         --this.timeOut;
      }

      return this.timeOut == 0;
   }

   public void prepareData(int time, String chat) {
      this.chats = FontTeam.blackFont.splitFontBStrInLine(chat, 100);
      GameScr.timeDelayTask = this.chats.length * 8;
      this.h = 14 * this.chats.length + 4 + 4;
      this.w = 30;

      for(int i = 0; i < this.chats.length; ++i) {
         int aa = FontTeam.blackFont.getWidth(this.chats[i]) + 5;
         if (aa > this.w) {
            this.w = aa;
         }
      }

      this.timeOut = time;
   }

   public void paintAnimal(mGraphics g) {
      paintRoundRect(g, this.xc - this.w / 2, this.yc - this.h - 4, this.w, this.h, -1, -16777216);
      int yy = this.yc - this.h;

      for(int i = 0; i < this.chats.length; ++i) {
         FontTeam.blackFont.drawString(g, this.chats[i], this.xc, yy + 2, 2, false);
         yy += 14;
      }

   }

   public static void paintRoundRect(mGraphics g, int x, int y, int w, int h, int color1, int color2) {
      g.drawRegion(imgPopup, 0, 0, 8, 8, 0, x, y, 0, false);
      g.drawRegion(imgPopup, 0, 8, 8, 8, 0, x + w - 8, y, 0, false);
      g.drawRegion(imgPopup, 0, 24, 8, 8, 0, x, y + h - 8, 0, false);
      g.drawRegion(imgPopup, 0, 16, 8, 8, 0, x + w - 8, y + h - 8, 0, false);
      g.setColor(color1);
      g.fillRect(x + 8, y, w - 16, 8, false);
      g.fillRect(x + 8, y + h - 8, w - 16, 7, false);
      g.fillRect(x, y + 8, w, h - 16, false);
      g.setColor(color2);
      g.fillRect(x + 8, y, w - 16, 1, false);
      g.fillRect(x + 8, y + h - 1, w - 16, 1, false);
      g.fillRect(x, y + 8, 1, h - 16, false);
      g.fillRect(x + w - 1, y + 8, 1, h - 16, false);
   }
}
