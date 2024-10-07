package code.model;

import lib.mGraphics;
import lib.mHashtable;

public class ImageManager extends mHashtable {
   public static ImageManager mList = new ImageManager();

   public static void addNewImg(String path, int id, long time) {
      mList.put("" + id, new mImage(path, id, time));
   }

   public void drawImage(mGraphics g, int id, int x, int y, int archor, boolean isSetclip) {
      mImage img = (mImage)mList.get("" + id);
      if (img != null) {
         img.drawImage(g, x, y, archor, isSetclip);
      }

   }

   public void drawRegion(mGraphics g, int id, int x0, int y0, int w0, int h0, int translate, int x, int y, int archor, boolean isSetclip) {
      mImage img = (mImage)mList.get("" + id);
      if (img != null) {
         img.drawRegion(g, x0, y0, w0, h0, translate, x, y, archor, isSetclip);
      }

   }
}
