package code.real;

import code.main.GameCanvas;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.microedition.lcdui.Image;
import lib.mRandom;
import lib.mVector;
import lib2.Message;

public class GameUtil {
   public static void onLoadMapComplete() {
      GameCanvas.endDlg();
   }

   public static int randomNumber(int max) {
      mRandom random = new mRandom();
      return random.nextInt(max);
   }

   public static byte[] get_Byte_Array(Image img) {
      int[] imgRgbData = new int[img.getWidth() * img.getHeight()];
      byte[] imageData = null;

      try {
         img.getRGB(imgRgbData, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());
      } catch (Exception var6) {
      }

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(baos);

      try {
         for(int i = 0; i < imgRgbData.length; ++i) {
            dos.writeInt(imgRgbData[i]);
         }

         imageData = baos.toByteArray();
         baos.close();
         dos.close();
      } catch (Exception var7) {
      }

      return imageData;
   }

   public static byte[] readByteArray(Message msg) {
      try {
         int lengh = msg.reader().readInt();
         byte[] data = new byte[lengh];
         msg.reader().read(data);
         return data;
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static byte[] readByteArray(DataInputStream dos) {
      try {
         int lengh = dos.readInt();
         byte[] data = new byte[lengh];
         dos.read(data);
         return data;
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static String replace(String text, String regex, String replacement) {
      StringBuffer sBuffer = new StringBuffer();

      int pos;
      for(boolean var4 = false; (pos = text.indexOf(regex)) != -1; text = text.substring(pos + regex.length())) {
         sBuffer.append(text.substring(0, pos) + replacement);
      }

      sBuffer.append(text);
      return sBuffer.toString();
   }

   public static String numberToString(String number) {
      String value = "";
      String value1 = "";
      if (number.equals("")) {
         return value;
      } else {
         if (number.charAt(0) == '-') {
            value1 = "-";
            number = number.substring(1);
         }

         for(int i = number.length() - 1; i >= 0; --i) {
            if ((number.length() - 1 - i) % 3 == 0 && number.length() - 1 - i > 0) {
               value = number.charAt(i) + "." + value;
            } else {
               value = number.charAt(i) + value;
            }
         }

         return value1 + value;
      }
   }

   public static String getDate(int second) {
      int tam = second * 1000;
      Calendar cal = Calendar.getInstance();
      cal.setTimeZone(TimeZone.getTimeZone("GMT+7"));
      cal.setTime(new Date((long)tam));
      int h = cal.get(11);
      int m = cal.get(12);
      int day = cal.get(5);
      int month = cal.get(2) + 1;
      int year = cal.get(1);
      return day + "/" + month + "/" + year + " " + h + "h";
   }

   public static String getTime(int timeRemainS) {
      int timeRemainM = 0;
      if (timeRemainS > 60) {
         timeRemainM = timeRemainS / 60;
         timeRemainS %= 60;
      }

      int timeRemainH = 0;
      if (timeRemainM > 60) {
         timeRemainH = timeRemainM / 60;
         timeRemainM %= 60;
      }

      int timeRemainD = 0;
      if (timeRemainH > 24) {
         timeRemainD = timeRemainH / 24;
         timeRemainH %= 24;
      }

      String s = "";
      if (timeRemainD > 0) {
         s = s + timeRemainD;
         s = s + "d";
         s = s + timeRemainH + "h";
      } else if (timeRemainH > 0) {
         s = s + timeRemainH;
         s = s + "h";
         s = s + timeRemainM + "'";
      } else {
         if (timeRemainM > 9) {
            s = s + timeRemainM;
         } else {
            s = s + "0" + timeRemainM;
         }

         s = s + ":";
         if (timeRemainS > 9) {
            s = s + timeRemainS;
         } else {
            s = s + "0" + timeRemainS;
         }
      }

      return s;
   }

   public static String getMoneys(long m) {
      String str = "";
      long mm = m / 1000L + 1L;

      for(int i = 0; (long)i < mm; ++i) {
         if (m < 1000L) {
            str = m + str;
            break;
         }

         long a = m % 1000L;
         if (a == 0L) {
            str = ".000" + str;
         } else if (a < 10L) {
            str = ".00" + a + str;
         } else if (a < 100L) {
            str = ".0" + a + str;
         } else {
            str = "." + a + str;
         }

         m /= 1000L;
      }

      return str;
   }

   public static String getTimeAgo(int timeRemainS) {
      int timeRemainM = 0;
      if (timeRemainS > 60) {
         timeRemainM = timeRemainS / 60;
         timeRemainS %= 60;
      }

      int timeRemainH = 0;
      if (timeRemainM > 60) {
         timeRemainH = timeRemainM / 60;
         timeRemainM %= 60;
      }

      int timeRemainD = 0;
      if (timeRemainH > 24) {
         timeRemainD = timeRemainH / 24;
         timeRemainH %= 24;
      }

      String s = "";
      if (timeRemainD > 0) {
         s = s + timeRemainD;
         s = s + "d";
         s = s + timeRemainH + "h";
      } else if (timeRemainH > 0) {
         s = s + timeRemainH;
         s = s + "h";
         s = s + timeRemainM + "'";
      } else {
         if (timeRemainM == 0) {
            timeRemainM = 1;
         }

         s = s + timeRemainM;
         s = s + "ph";
      }

      return s;
   }

   public static void sleep(int time) {
      try {
         Thread.sleep((long)time);
      } catch (InterruptedException var2) {
      }

   }

   public static String[] split(String original, String separator) {
      mVector nodes = new mVector();

      for(int index = original.indexOf(separator); index >= 0; index = original.indexOf(separator)) {
         nodes.addElement(original.substring(0, index));
         original = original.substring(index + separator.length());
      }

      nodes.addElement(original);
      String[] result = new String[nodes.size()];
      if (nodes.size() > 0) {
         for(int loop = 0; loop < nodes.size(); ++loop) {
            result[loop] = (String)nodes.elementAt(loop);
         }
      }

      return result;
   }
}
