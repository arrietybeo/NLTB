package code.model;

import code.screen.Res;
import code.screen.screen.FontTeam;
import lib.mGraphics;

public class PopupName {
   short x;
   short y;
   public String name;
   public byte num;
   public byte iPrivate = 0;
   public static FrameImage imgArrow;

   public PopupName(String name, int x, int y) {
      this.x = (short)x;
      this.y = (short)y;
      this.name = name;
      this.num = (byte)Res.rnd(8);
   }

   public void update() {
   }

   public void paint(mGraphics g) {
      ++this.num;
      if (this.num >= 8) {
         this.num = 0;
      }

      if (imgArrow != null) {
         imgArrow.drawFrame(0, this.x, this.y - 10 + this.num / 2, 0, mGraphics.BOTTOM | mGraphics.HCENTER, g);
      }

      FontTeam.normalFontColor.drawString(g, this.name, this.x, this.y - 32 + this.num / 2, 2, false);
   }
}
