package code.model;

import code.screen.Res;
import code.screen.screen.GameData;
import lib.mGraphics;
import lib2.mFont;

public class TimecountDown {
   long mysecond;
   public String tile;
   public boolean wantdestroy;
   public short id;
   public short idIcon;
   public byte typeCount;

   public TimecountDown(short id, long second, String tile) {
      this.mysecond = System.currentTimeMillis() + second * 1000L;
      this.tile = tile;
      this.id = id;
      this.idIcon = -1;
   }

   public TimecountDown(short id, short idIcon, long second, String tile, byte type) {
      this.mysecond = System.currentTimeMillis() + second * 1000L;
      this.tile = tile;
      this.id = id;
      this.idIcon = idIcon;
      this.typeCount = type;
      if (this.typeCount == 0) {
         this.mysecond = second;
      }

   }

   public void paint(mGraphics g, int x, int y) {
      if (this.idIcon == -1) {
         if (this.typeCount == 1) {
            int time = (int)((this.mysecond - System.currentTimeMillis()) / 1000L);
            if (time > 0) {
               mFont.tahoma_7_black.drawString(g, this.tile + " : " + converSecon2hours(time), x - 5, y + 1, 1, false);
               mFont.tahoma_7_white.drawString(g, this.tile + " : " + converSecon2hours(time), x - 4, y, 1, false);
            }
         } else {
            mFont.tahoma_7_black.drawString(g, this.tile, x + 1, y + 1, 1, false);
            mFont.tahoma_7_white.drawString(g, this.tile, x, y, 1, false);
         }
      } else {
         ImageIcon img = GameData.getImgIcon((short)(this.idIcon + Res.ID_ITEM));
         if (img != null && img.img != null) {
            int time;
            if (this.typeCount == 0) {
               time = mFont.tahoma_7_black.getWidth(this.tile + " : ");
               g.drawImage(img.img, x - time - img.img.getWidth() * 2, y + mGraphics.getImageHeight(img.img) / 4, 0, false);
               mFont.tahoma_7_black.drawString(g, this.tile, x - time - img.img.getWidth() * 2 + 1 + mGraphics.getImageWidth(img.img), y + 1 + mGraphics.getImageHeight(img.img) / 4, 0, false);
               mFont.tahoma_7_white.drawString(g, this.tile, x - time - img.img.getWidth() * 2 + mGraphics.getImageWidth(img.img), y + mGraphics.getImageHeight(img.img) / 4, 0, false);
            } else if (this.typeCount == 1) {
               time = (int)((this.mysecond - System.currentTimeMillis()) / 1000L);
               int ws = mFont.tahoma_7_black.getWidth(converSecon2hours(time) + ":");
               g.drawImage(img.img, x - ws - img.img.getWidth() * 2, y + mGraphics.getImageHeight(img.img) / 4, 0, false);
               mFont.tahoma_7_black.drawString(g, " : " + converSecon2hours(time), x - ws - img.img.getWidth() * 2 + 1 + mGraphics.getImageWidth(img.img), y + 1 + mGraphics.getImageHeight(img.img) / 4, 0, false);
               mFont.tahoma_7_white.drawString(g, " : " + converSecon2hours(time), x - ws - img.img.getWidth() * 2 + mGraphics.getImageWidth(img.img), y + mGraphics.getImageHeight(img.img) / 4, 0, false);
            }
         }
      }

   }

   public void setsecond(long second) {
      this.mysecond = System.currentTimeMillis() + second * 1000L;
   }

   public void update() {
      if (this.typeCount == 1 && System.currentTimeMillis() - this.mysecond >= 0L) {
         this.wantdestroy = true;
      }

   }

   public static String converSecon2hours(int totalSeconds) {
      int seconds = totalSeconds % 60;
      int totalMinutes = totalSeconds / 60;
      int minutes = totalMinutes % 60;
      int hours = totalMinutes / 60;
      if (hours > 0) {
         return hours + ":" + minutes;
      } else if (minutes > 0) {
         return minutes + ":" + seconds;
      } else {
         return seconds < 0 ? "0:" + seconds : String.valueOf(seconds);
      }
   }
}
