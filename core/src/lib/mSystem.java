package lib;

import code.main.GameMidlet;
import code.model.mCommand;
import code.screen.screen.FontTeam;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.io.DataInputStream;
import javax.microedition.lcdui.Image;
import lib2.Message;

public class mSystem {
   public static String ww = "";
   public static mRandom r = new mRandom();
   public static boolean isIos = false;
   public static boolean isAndroid = false;
   public static boolean isIP = true;
   public static boolean isPC = true;
   public static boolean isj2me = false;
   public static final String[] google_productIds = new String[]{"nlgem10", "nlgem30", "nlgem70", "nlgem180", "nlgem380", "nlgem800"};
   public static String[] google_listGems = new String[]{"Nạp 25 lượng", "Nạp 85 lượng", "Nạp 150 lượng", "Nạp 350 lượng", "Nạp 1,100 lượng", "Nạp 2,500 lượng"};

   public static String connectHTTP(String url) {
      if (!ww.equals("")) {
         return ww;
      } else {
         Net.HttpRequest httpGet = new Net.HttpRequest("GET");
         httpGet.setUrl(url);
         Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
               mSystem.ww = httpResponse.getResultAsString();
            }

            public void failed(Throwable t) {
            }

            public void cancelled() {
            }
         });

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var3) {
         }

         return ww;
      }
   }

   public static Pixmap createPixmap(String url) {
      try {
         url = "/x" + mGraphics.zoomLevel + url;
         Pixmap p = new Pixmap(Gdx.files.internal(LibSysTem.res + url));
         return p;
      } catch (Exception var2) {
         System.out.println("KHONG LOAD DC PIXMAP:" + url);
         return null;
      }
   }

   public static Pixmap createPixmap(Image img) {
      try {
         Texture texture = img.texture;
         if (texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
         }

         Pixmap p = texture.getTextureData().consumePixmap();
         return p;
      } catch (Exception var3) {
         System.out.println("KHONG LOAD DC PIXMAP image");
         return null;
      }
   }

   public static Pixmap createPixmap(Image img, int size) {
      try {
         Texture texture = img.texture;
         if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
         }

         Pixmap pixmap = texture.getTextureData().consumePixmap();
         Pixmap p = createPixmap(img.getWidth() / size, img.getHeight() / size);
         System.out.println(img.tRegion.getRegionWidth() + " " + img.tRegion.getRegionHeight() + " kich thuoc minimap: " + p.getWidth() + " >> " + p.getHeight());

         for(int x = 0; x < img.tRegion.getRegionWidth(); x += 16) {
            int count = 0;

            for(int y = 0; y < img.tRegion.getRegionWidth(); y += 16) {
               int colorInt = pixmap.getPixel(img.tRegion.getRegionX() + x, img.tRegion.getRegionY() + y);
               Pixmap c = createPixmap(1, 1);
               c.setColor(colorInt);
               c.fill();
               p.drawPixmap(c, x / (16 * mGraphics.zoomLevel), y / (16 * mGraphics.zoomLevel));
               ++count;
            }

            System.out.println("so lan cot y: " + count);
         }

         return p;
      } catch (Exception var10) {
         System.out.println("loi load pixmap " + img.texture);
         var10.printStackTrace();
         return null;
      }
   }

   public static Pixmap createPixmap(int w, int h) {
      w *= mGraphics.zoomLevel;
      h *= mGraphics.zoomLevel;
      return new Pixmap(w, h, Pixmap.Format.RGBA4444);
   }

   public static void getServerLink(boolean isTryGetIPFromWap) {
   }

   public static String getLink(String str) {
      return str;
   }

   public static String getPathRMS(String str) {
      return str;
   }

   public static void println(String str) {
   }

   public static DataInputStream getResourceAsStream(String path) {
      DataInputStream is = null;

      try {
         is = new DataInputStream(LibSysTem.getResourceAsStream(path));
      } catch (Exception var3) {
         is = null;
      }

      return is;
   }

   public static Color setColor(int rgb) {
      float R = (float)(rgb >> 16 & 255);
      float G = (float)(rgb >> 8 & 255);
      float B = (float)(rgb & 255);
      float b = B / 256.0F;
      float g = G / 256.0F;
      float r = R / 256.0F;
      return new Color(r, g, b, 1.0F);
   }

   public static void gcc() {
      System.gc();
   }

   public static long currentTimeMillis() {
      return System.currentTimeMillis();
   }

   public static int abs(int i) {
      return i > 0 ? i : -i;
   }

   public static int random(int a, int b) {
      return a == b ? a : a + r.nextInt(b - a);
   }

   public static int[][] mIntArray(int value1, int value2) {
      int[][] m = new int[value1][];

      for(int i = 0; i < m.length; ++i) {
         m[i] = new int[value2];
      }

      return m;
   }

   public static int getIntByString(String a) {
      String temp = "";

      for(int i = 0; i < a.length(); ++i) {
         String t = String.valueOf(a.charAt(i));
         if (!t.equals(".")) {
            temp = temp + t;
         }
      }

      return Integer.parseInt(temp);
   }

   public static void outloi(String string) {
   }

   public static void sendSMS(String data, String to, mCommand successAction, final mCommand failAction) {
      (new Thread(new Runnable() {
         public void run() {
         }
      })).start();
   }

   public static void reQuetURL(String url) {
      GameMidlet var10000;
      try {
         var10000 = GameMidlet.instance;
         GameMidlet.platformRequest(url);
         Thread.sleep(500L);
      } catch (Exception var2) {
      }

      var10000 = GameMidlet.instance;
      GameMidlet.notifyDestroyed();
   }

   public static String[] getStringArray(String info) {
      return FontTeam.splitString(info, ":");
   }

   public static void requestLink(String link) {
      try {
         Gdx.net.openURI(link);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static boolean isAnNaptien() {
      return false;
   }

   public static String getLong() {
      return "";
   }

   public static String getLat() {
      return "";
   }

   public static boolean isAndroidStore() {
      return false;
   }

   public static void handleAllMessage() {
   }

   public static void makePurchase(String string) {
   }

   public static void Debug(String productId) {
   }

   public static void handleMessage(Message msg) {
   }

   public static boolean isIosAppStore() {
      return false;
   }
}
