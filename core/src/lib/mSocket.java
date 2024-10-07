package lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class mSocket {
   Socket s;

   public mSocket(String str, int port) {
      try {
         this.s = new Socket(str, port);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public void close() {
      try {
         this.s.close();
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void setKeepAlive(boolean isAlive) {
      try {
         this.s.setKeepAlive(isAlive);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public DataOutputStream getOutputStream() {
      try {
         DataOutputStream dos = new DataOutputStream(this.s.getOutputStream());
         return dos;
      } catch (IOException var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public DataInputStream getInputStream() {
      try {
         DataInputStream dis = new DataInputStream(this.s.getInputStream());
         return dis;
      } catch (IOException var2) {
         var2.printStackTrace();
         return null;
      }
   }
}
