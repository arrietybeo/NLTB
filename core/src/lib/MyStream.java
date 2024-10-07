package lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

public class MyStream {
   DataInputStream data;
   DataOutputStream Out;
   boolean isOutput;
   public ByteArrayOutputStream bo;

   public static InputStream readFile(String path) {
      DataInputStream is = new DataInputStream(LibSysTem.getResourceAsStream(path));
      return is;
   }

   public static InputStream readFileZoomLevel(String path) {
      String x = Main.isAndroid ? "x" : "/x";
      path = x + mGraphics.zoomLevel + path;

      try {
         return Image.openFile(path);
      } catch (Exception var3) {
         Cout.println("LOI readFileZoomLevel MYSTREAM : " + path + " : " + var3.toString());
         return null;
      }
   }

   public MyStream(byte[] arr, boolean isOutput) {
      this.isOutput = isOutput;
      if (isOutput) {
         this.bo = new ByteArrayOutputStream();
         this.Out = new DataOutputStream(this.bo);
      } else {
         this.data = new DataInputStream(new ByteArrayInputStream(arr));
      }

   }

   public DataInputStream reader() {
      return this.data;
   }

   public int readUnsignedShort() throws IOException {
      return this.data.readUnsignedShort();
   }

   public boolean readBoolean() throws IOException {
      return this.data.readBoolean();
   }

   public String readUTF() throws IOException {
      return this.data.readUTF();
   }

   public void readFully(byte[] a) throws IOException {
      this.data.readFully(a);
   }

   public byte[] toByteArray() {
      return this.bo.toByteArray();
   }

   public void writeByte(int a) throws IOException {
      this.Out.writeByte(a);
   }

   public void writeShort(int v) throws IOException {
      this.Out.writeShort(v);
   }

   public void write(byte[] v) throws IOException {
      this.Out.write(v);
   }

   public MyStream(String path) {
      this.data = new DataInputStream(readFile(path));
   }

   public short readShort() throws IOException {
      return this.data.readShort();
   }

   public void read(byte[] b, int off, int len) throws IOException {
      this.data.read(b, off, len);
   }

   public void read(byte[] b) throws IOException {
      this.data.read(b);
   }

   public int read() throws IOException {
      return this.data.read();
   }

   public int readUnsignedByte() throws IOException {
      return this.data.readUnsignedByte();
   }

   public void close() throws IOException {
      if (this.isOutput) {
         this.Out.close();
      } else {
         this.data.close();
      }

   }

   public byte readByte() throws IOException {
      return this.data.readByte();
   }
}
