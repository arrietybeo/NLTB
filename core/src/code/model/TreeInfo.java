package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.screen.GameData;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Hashtable;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class TreeInfo {
   public static Hashtable ALL_BIG_TREE = new Hashtable();
   public short dx;
   public short dy;
   public short startx;
   public short starty;
   public short endx;
   public short endy;
   public short w = -1;
   public short h;
   public short xBig;
   public short yBig;
   public short idBig = -1;
   public short id;
   long timeGetImage;
   public static int[] countIDBIG = new int[15];

   public void onImage(byte[] arr, int index) {
      try {
         DataInputStream data = new DataInputStream(new ByteArrayInputStream(arr));
         byte[] img = new byte[data.readShort()];
         data.read(img, 0, img.length);
         byte[] dtt = new byte[data.readShort()];
         data.read(dtt, 0, dtt.length);
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(dtt));
         this.dx = dis.readShort();
         this.dy = dis.readShort();
         this.startx = (short)dis.readUnsignedByte();
         this.starty = (short)dis.readUnsignedByte();
         this.endx = (short)dis.readUnsignedByte();
         this.endy = (short)dis.readUnsignedByte();
         this.xBig = (short)dis.readUnsignedByte();
         this.yBig = (short)dis.readUnsignedByte();
         this.idBig = dis.readByte();
         this.w = (short)dis.readUnsignedByte();
         this.h = (short)dis.readUnsignedByte();
         data.close();
         dis.close();
      } catch (Exception var7) {
         System.out.println("LOI LOAD CAY TRONG TREEINFO " + index);
         var7.printStackTrace();
      }

   }

   public Image getImage() {
      return null;
   }

   public int getHeight() {
      return this.h;
   }

   public int getWidth() {
      return this.w;
   }

   public void paint(mGraphics g, int x, int y) {
      ImageIcon img = GameData.getImgIcon((short)(this.id + Res.ID_ITEM_MAP));

      try {
         if (img != null && img.img != null) {
            g.drawImage(img.img, x, y, 0, false);
            this.w = (short)mGraphics.getImageWidth(img.img);
            this.h = (short)mGraphics.getImageHeight(img.img);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
         System.out.println("idBig=" + this.idBig);
      }

   }

   public void setData(int id, byte[] data) {
      this.id = (short)id;

      try {
         ByteArrayInputStream is = new ByteArrayInputStream(data);
         DataInputStream dis = new DataInputStream(is);
         this.dx = dis.readShort();
         this.dy = dis.readShort();
         this.startx = (short)dis.readUnsignedByte();
         this.starty = (short)dis.readUnsignedByte();
         this.endx = (short)dis.readUnsignedByte();
         this.endy = (short)dis.readUnsignedByte();
         this.xBig = (short)dis.readUnsignedByte();
         this.yBig = (short)dis.readUnsignedByte();
         this.idBig = dis.readByte();
         this.w = (short)dis.readUnsignedByte();
         this.h = (short)dis.readUnsignedByte();
         is.close();
         dis.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   public TreeInfo(int id, byte[] data) {
      this.id = (short)id;

      try {
         ByteArrayInputStream is = new ByteArrayInputStream(data);
         DataInputStream dis = new DataInputStream(is);
         this.dx = dis.readShort();
         this.dy = dis.readShort();
         this.startx = (short)dis.readUnsignedByte();
         this.starty = (short)dis.readUnsignedByte();
         this.endx = (short)dis.readUnsignedByte();
         this.endy = (short)dis.readUnsignedByte();
         this.xBig = (short)dis.readUnsignedByte();
         this.yBig = (short)dis.readUnsignedByte();
         this.idBig = dis.readByte();
         this.w = (short)dis.readUnsignedByte();
         this.h = (short)dis.readUnsignedByte();
         is.close();
         dis.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   public TreeInfo(int id) {
      this.id = (short)id;
   }

   public void loadData() {
      try {
         if (this.idBig != -1) {
            Image img = (Image)ALL_BIG_TREE.get(String.valueOf(this.idBig));
            if (img == null) {
               img = GameCanvas.createImage("datahd/big" + this.idBig + ".png");
               ALL_BIG_TREE.put(String.valueOf(this.idBig), img);
            }
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }
}
