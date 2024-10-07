package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.screen.FontTeam;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.PageScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;
import lib2.TField;

public class Layer {
   public boolean isOne;
   public boolean isLocation;
   public short location0;
   public short location1;
   public Image img;
   public TField tf1;
   public TField tf2;

   public void update() {
      if ((GameCanvas.isKeyPressed(2) || GameCanvas.isKeyPressed(8)) && !this.isOne) {
         if (this.tf1.isFocus) {
            this.tf1.isFocus = false;
            this.tf2.isFocus = true;
            PageScr.gI().right = this.tf2.cmdClear;
         } else {
            this.tf1.isFocus = true;
            this.tf2.isFocus = false;
            PageScr.gI().right = this.tf1.cmdClear;
         }
      }

      if (this.tf1 != null) {
         this.tf1.update();
      }

      if (this.tf2 != null) {
         this.tf2.update();
      }

   }

   public void paint(mGraphics g, int x, int y) {
      GameCanvas.resetTrans(g);
      if (this.isLocation) {
         GameData.paintIcon(g, (short)800, 98, 95);
         Res.getCharPartInfo(0, GameScr.mainChar.currentHead).paint(g, this.location0, this.location1, 0, 1);
      } else {
         if (!this.isOne) {
            FontTeam.borderFont.drawString(g, "Seri", this.tf1.x + 2, this.tf1.y - 11, 0, false);
            FontTeam.borderFont.drawString(g, "Mã thẻ", this.tf2.x + 2, this.tf2.y - 11, 0, false);
         } else {
            int w = 0;
            if (this.img != null) {
               w = mGraphics.getImageWidth(this.img) + 35;
            }

            g.drawImage(this.img, w / 2, 30, mGraphics.TOP | mGraphics.HCENTER, false);
         }

         if (this.tf1 != null) {
            this.tf1.paint(g);
         }

         if (this.tf2 != null) {
            this.tf2.paint(g);
         }
      }

   }

   public void keyPress(int keyCode) {
      if (this.tf2 != null) {
         this.tf1.keyPressed(keyCode);
      }

      if (this.tf2 != null) {
         this.tf2.keyPressed(keyCode);
      }

   }
}
