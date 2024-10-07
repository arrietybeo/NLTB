package code.screen.screen;

import lib2.mFont;

public class InfoTextShow {
   public String[] info;
   public byte color = 0;
   public byte idCompare = -1;
   public short id;
   public mFont f = null;

   public InfoTextShow(String[] info, int idColor) {
      this.info = info;
      if (info != null) {
         this.color = (byte)idColor;
      }

   }

   public int getHeight() {
      return this.info != null && this.f != null ? this.f.getHeight() * this.info.length : 0;
   }

   public void setInfo(String[] arr, mFont f) {
      this.info = arr;
      this.f = f;
   }

   public int getMaxWidth() {
      int max = 0;
      if (this.info != null) {
         for(int i = 0; i < this.info.length; ++i) {
            if (this.info[i] != null) {
               int w = this.f.getWidth(this.info[i]);
               max = max > w ? max : w;
            }
         }
      }

      return max;
   }
}
