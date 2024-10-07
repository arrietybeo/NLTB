package code.model;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

public class WeaponInfo {
   public Image img;
   public byte[] x0;
   public byte[] y0;
   public byte[] w0;
   public byte[] h0;
   public byte[] id;
   public WeaponInfo.DirrectInfo[] dirInfo = new WeaponInfo.DirrectInfo[4];

   public void readData(InputStream ip) throws IOException {
      byte count = (byte)ip.read();
      this.id = new byte[count];
      this.x0 = new byte[count];
      this.y0 = new byte[count];
      this.w0 = new byte[count];
      this.h0 = new byte[count];

      int i;
      for(i = 0; i < count; ++i) {
         this.id[i] = (byte)ip.read();
         this.x0[i] = (byte)ip.read();
         this.y0[i] = (byte)ip.read();
         this.w0[i] = (byte)ip.read();
         this.h0[i] = (byte)ip.read();
      }

      for(i = 0; i < 4; ++i) {
         this.dirInfo[i] = new WeaponInfo.DirrectInfo();
         this.dirInfo[i].idImg = (byte)ip.read();
         this.dirInfo[i].dx = (byte)ip.read();
         this.dirInfo[i].dy = (byte)ip.read();
         this.dirInfo[i].iFlip = (byte)ip.read();
      }

   }

   public byte getID(int idI) {
      for(byte i = 0; i < this.id.length; ++i) {
         if (this.id[i] == idI) {
            return i;
         }
      }

      return -1;
   }

   public class DirrectInfo {
      byte idImg;
      byte dx;
      byte dy;
      byte iFlip;
   }
}
