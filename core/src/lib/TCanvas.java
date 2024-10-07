package lib;

import code.main.GameMidlet;
import com.team.ngulong.MyGdxGame;

public abstract class TCanvas implements Runnable {
   public static final byte JAVA = 0;
   public static final byte ANDROID = 1;
   public static final byte IPHONE_JB = 2;
   public static final byte IPHONE_STOTE = 3;
   public static final byte PC = 4;
   public static final byte WINDOW_PHONE = 5;
   public static final byte NOKIA_OVI = 6;
   public static final byte NOKIA_ANDROID = 7;
   public static final byte ANDROID_STORE = 8;
   public static final byte WINDOW_PHONE_STORE = 9;
   public static byte ClientType;
   public static byte ScreenSize = 2;
   public static boolean bRun;

   private void checkZoomLevel(int w, int h) {
      if (!Main.isJava) {
         if (mSystem.isPC) {
            mGraphics.zoomLevel = ScreenSize;
         } else if (!MyGdxGame.isAndroid) {
            if (w * h >= 2073600) {
               mGraphics.zoomLevel = 4;
            } else if (w * h > 153600) {
               mGraphics.zoomLevel = 3;
            }
         } else if (w * h >= 2073600) {
            mGraphics.zoomLevel = 4;
         } else if (w * h > 153600) {
            mGraphics.zoomLevel = 3;
         } else {
            mGraphics.zoomLevel = 1;
         }

      }
   }

   public void displayMe(GameMidlet m) {
   }

   public TCanvas(Context context) {
   }

   public TCanvas() {
      this.checkZoomLevel(MyGdxGame.getWidth(), MyGdxGame.getHeight());
      ClientType = 4;
      mSound.init(7, 35);
      mSound.volumeSound = 0.6F;
      mSound.volumeMusic = 1.0F;
   }

   public void start() {
   }

   public static String getPlatformName() {
      return System.getProperty("microedition.platform");
   }

   public int getWidthz() {
      int realWidth = MyGdxGame.getWidth();
      return realWidth / mGraphics.zoomLevel + (realWidth % mGraphics.zoomLevel == 0 ? 0 : 1);
   }

   public int getHeightz() {
      int realHeight = MyGdxGame.getHeight();
      return realHeight / mGraphics.zoomLevel + (realHeight % mGraphics.zoomLevel == 0 ? 0 : 1);
   }

   public final void pointerDragged(int x, int y, int index) {
      x /= mGraphics.zoomLevel;
      y /= mGraphics.zoomLevel;
      this.onPointerDragged(x, y, index);
   }

   public final void pointerPressed(int x, int y, int index) {
      x /= mGraphics.zoomLevel;
      y /= mGraphics.zoomLevel;
      this.onPointerPressed(x, y, index);
   }

   public final void pointerReleased(int x, int y, int index) {
      x /= mGraphics.zoomLevel;
      y /= mGraphics.zoomLevel;
      this.onPointerReleased(x, y, index);
   }

   protected abstract void onPointerDragged(int var1, int var2, int var3);

   protected abstract void onPointerPressed(int var1, int var2, int var3);

   protected abstract void onPointerReleased(int var1, int var2, int var3);

   protected abstract void update();

   public void run() {
   }
}
