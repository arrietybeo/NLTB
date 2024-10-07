package code.model;

import code.screen.Res;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class ImageObj {
   private Image[] img = new Image[1];
   public byte[] index;
   public byte[] x0;
   public byte[] y0;
   public byte[] w;
   public byte[] h;
   public short[] dx;
   public short[] dy;

   public ImageObj() {
   }

   public void paint(mGraphics g, int x, int y, int index, int trans, int dirX, int dirY) {
   }

   public ImageObj(byte[] array) {
      try {
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(array));
         int len1 = 1;
         this.img = new Image[len1];

         for(int i = 0; i < this.img.length; ++i) {
            int len = dis.available();
            byte[] bimg = new byte[len];
            dis.read(bimg);
            this.img[i] = Image.createImage((byte[])bimg, 0, len);
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   public ImageObj(int type, int palate) {
      try {
         byte[] header = FilePack.instance.loadFile(palate + "_h");
         byte[] data = FilePack.instance.loadFile("data");
         this.img[0] = Res.createImgByHeader(header, data);
         byte[] pos = FilePack.instance.loadFile("pos");
         ByteArrayInputStream bi = new ByteArrayInputStream(pos);
         DataInputStream dis = new DataInputStream(bi);
         int size = dis.readByte();
         this.index = new byte[size];
         this.x0 = new byte[size];
         this.y0 = new byte[size];
         this.w = new byte[size];
         this.h = new byte[size];
         this.dx = new short[size];
         this.dy = new short[size];

         for(int i = 0; i < size; ++i) {
            this.index[i] = dis.readByte();
            this.x0[i] = dis.readByte();
            this.y0[i] = dis.readByte();
            this.w[i] = dis.readByte();
            this.h[i] = dis.readByte();
            this.dx[i] = dis.readByte();
            this.dy[i] = dis.readByte();
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public int getWith(int index) {
      return this.img == null ? 0 : this.img[index].getWidth();
   }

   public Image getImage(int indexImg) {
      return this.img == null ? null : this.img[indexImg];
   }

   public void initImage(int nImg) {
      this.img = new Image[nImg];
   }
}
