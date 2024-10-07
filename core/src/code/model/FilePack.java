package code.model;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import lib.Cout;
import lib.MyStream;

public class FilePack {
   public static FilePack instance;
   public String[] fname;
   private int[] fpos;
   private int[] flen;
   private byte[] fullData;
   private int nFile;
   private int hSize;
   private String name;
   private byte[] code = new byte[]{78, 103, 117, 121, 101, 110, 86, 97, 110, 77, 105, 110, 104};
   private int codeLen;
   public static final String[] charAvatar = new String[]{"/c/leg/", "/c/body/", "/c/head/", "/c/hat/", "/c/coat/"};
   public static final String cBody = "/body.c";
   public static final String cHat = "/hat.c";
   public static final String cLeg = "/leg.c";
   public static final String cHead = "/head.c";
   public static final String main = "/main.sh";
   public static final String font = "/font.sh";
   public static final String npc = "/npc.sh";
   public static final String eff = "/eff.sh";
   public static final String tree = "/tree/";
   public static final String type = "/type.sh";
   public static final String wps = "/wpsplash/";
   public static final String arrow = "/arrow.sh";
   public static final String effPublic = "/skillpublic.sh";
   public static final String ground = "/g.sh";
   public static final String nation = "/nation";
   public static final String box = "/box.sh";
   private DataInputStream file;

   public FilePack() {
      this.codeLen = this.code.length;
   }

   public static void reset() {
      if (instance != null) {
         instance.close();
      }

      instance = null;
      System.gc();
   }

   public FilePack(String name, byte[] array) {
      this.codeLen = this.code.length;
   }

   public static Image getImg(String path) {
      return instance.loadImage(path + ".png");
   }

   public static void init(String path) {
      instance = new FilePack(path, (byte[])null);
   }

   public static void initByArray(byte[] array) {
      instance = new FilePack("", array);
   }

   private int encode(int i) {
      return i;
   }

   private void encode(byte[] bytes) {
      int len = bytes.length;

      for(int i = 0; i < len; ++i) {
         bytes[i] ^= this.code[i % this.codeLen];
      }

   }

   private void open() {
      InputStream min = MyStream.readFile(this.name);
      this.file = new DataInputStream(min);
   }

   private void openbyArray(byte[] array) {
      this.file = new DataInputStream(new ByteArrayInputStream(array));
   }

   public void close() {
      try {
         if (this.file != null) {
            this.file.close();
         }
      } catch (IOException var2) {
      }

   }

   public byte[] loadFile(String fileName) throws Exception {
      for(int i = 0; i < this.nFile; ++i) {
         if (this.fname[i].compareTo(fileName) == 0) {
            byte[] bytes = new byte[this.flen[i]];
            System.arraycopy(this.fullData, this.fpos[i], bytes, 0, this.flen[i]);
            return bytes;
         }
      }

      throw new Exception("File '" + fileName + "' not found!");
   }

   public Image loadImage(String fileName) {
      for(int i = 0; i < this.nFile; ++i) {
         if (this.fname[i].compareTo(fileName) == 0) {
            return Image.createImage(this.fullData, this.fpos[i], this.flen[i]);
         }
      }

      return null;
   }

   public MyStream loadData(String name) {
      for(int i = 0; i < this.nFile; ++i) {
         if (this.fname[i].compareTo(name) == 0) {
            byte[] aa = new byte[this.flen[i]];
            System.arraycopy(this.fullData, this.fpos[i], aa, 0, this.flen[i]);
            return new MyStream(aa, false);
         }
      }

      return null;
   }

   public byte[] getBinaryFile(String name) {
      for(int i = 0; i < this.nFile; ++i) {
         if (this.fname[i].compareTo(name) == 0) {
            byte[] array = new byte[this.flen[i]];
            System.arraycopy(this.fullData, this.fpos[i], array, 0, this.flen[i]);
            return array;
         }
      }

      Cout.println("File '" + name + "' not found!");
      return null;
   }
}
