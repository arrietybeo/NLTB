package code.model;

import code.screen.screen.GameData;
import lib.mGraphics;

public class MyTree extends Actor {
   public int himg = 1;
   public byte idImg = 0;
   public short idTree = 0;

   public MyTree(int id, int x, int y, int idImg) {
      this.idTree = (short)id;
      this.x = (short)x;
      this.y = (short)y;
      this.idImg = (byte)idImg;
      this.catagory = 11;
      this.himg = 1;
   }

   public void paint(mGraphics g) {
      if (this.idTree > -1) {
         ImageIcon img = GameData.getImgIcon((short)(this.idImg + 3200));
         if (img != null && img.img != null && this.himg == 1) {
            this.himg = mGraphics.getImageHeight(img.img);
         }

         g.drawImage(img.img, this.x, this.y, 33, false);
      }

   }

   public void setPosTo(short x, short y) {
   }
}
