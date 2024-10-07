package code.model;

import code.screen.screen.GameData;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class AnimalInfo extends ObjCharWearing {
   public String info = "";
   public String name = "";
   public byte idImg;
   public byte level;
   public byte typeAnimal;
   public byte typePet = -1;
   public short id;
   public static Image imgAnimal;
   private static boolean isGetImg = false;
   public long timeStart;
   public int totalTimeCon;
   public byte idPaint = 0;

   public void paint(mGraphics g, int x, int y) {
      GameData.paintIcon(g, (short)(this.idPaint + 7500), x, y);
   }

   public static void getImg() {
   }
}
