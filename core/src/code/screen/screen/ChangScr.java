package code.screen.screen;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.IActionListener;
import code.model.ReadMessenge;
import code.model.T;
import code.model.mCommand;
import code.screen.MenuLogin;
import javax.microedition.lcdui.Image;
import lib.Session_ME;
import lib.Tilemap;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib2.mFont;

public class ChangScr extends ScreenTeam implements IActionListener {
   private static ChangScr me;
   public boolean isnextmap;
   private int f;
   private int f1;
   private int f2;
   private int f3;
   private int f4;
   private int f5;
   private int fr1;
   private int fr2;
   private int fr3;
   private int fr4;
   private int fr5;
   public static int wimg;
   public static int himg;
   mCommand cmdback;
   long time;
   long timewaitChangeMap;
   boolean isChangemap;
   public boolean isOkdialog;
   public boolean isLoadImage = false;
   public static long timenextLogin;
   public long timeWait;
   public static Image[] loading1;
   public static Image[] loading2;
   public static Image[] loading3;
   public static Image[] loading4;
   public static Image[] loading5;
   public static byte[] frame1 = new byte[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 0, 0, 0};
   public static byte[] frame2 = new byte[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
   public static byte[] frame3 = new byte[]{2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 0, 0, 0, 1, 1, 1};
   public static byte[] frame4 = new byte[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
   public static byte[] frame5 = new byte[]{4, 4, 5, 5, 6, 6, 0, 0, 1, 1, 2, 2, 3, 3};

   public static ChangScr gI() {
      return me == null ? (me = new ChangScr()) : me;
   }

   public static void load() {
      loading1 = new Image[6];

      int i;
      for(i = 0; i < loading1.length; ++i) {
         loading1[i] = GameCanvas.loadImage("/loading/1" + i + ".png");
      }

      loading2 = new Image[6];

      for(i = 0; i < loading2.length; ++i) {
         loading2[i] = GameCanvas.loadImage("/loading/2" + i + ".png");
      }

      loading3 = new Image[6];

      for(i = 0; i < loading3.length; ++i) {
         loading3[i] = GameCanvas.loadImage("/loading/3" + i + ".png");
      }

      loading4 = new Image[6];

      for(i = 0; i < loading4.length; ++i) {
         loading4[i] = GameCanvas.loadImage("/loading/4" + i + ".png");
      }

      loading5 = new Image[7];

      for(i = 0; i < loading5.length; ++i) {
         loading5[i] = GameCanvas.loadImage("/loading/5" + i + ".png");
      }

   }

   public void switchToMe(ScreenTeam lastSCR) {
      this.time = mSystem.currentTimeMillis() + 20000L;
      this.timewaitChangeMap = mSystem.currentTimeMillis() + 1000L;
      this.lastSCR = lastSCR;
      this.isChangemap = true;
      super.switchToMe(lastSCR);
   }

   public void switchToMe() {
      super.switchToMe();
      this.time = mSystem.currentTimeMillis() + 20000L;
      this.timewaitChangeMap = mSystem.currentTimeMillis() + 1000L;
      this.isChangemap = false;
      if (GameCanvas.currentDialog != null) {
         GameCanvas.currentDialog = null;
      }

      this.isOkdialog = false;
   }

   public ChangScr() {
      if (GameScr.imgloading != null) {
         wimg = mGraphics.getImageWidth(GameScr.imgloading);
         himg = mGraphics.getImageHeight(GameScr.imgloading) / 12;
      }

      this.cmdback = new mCommand(T.back, this, 0);
   }

   public void update() {
      if (!this.isLoadImage && GameCanvas.currentDialog != null) {
         this.lastSCR = null;
         MenuLogin.gI().switchToMe();
      } else {
         this.fr1 = (this.fr1 + 1) % frame1.length;
         this.fr2 = (this.fr2 + 1) % frame2.length;
         this.fr3 = (this.fr3 + 1) % frame3.length;
         this.fr4 = (this.fr4 + 1) % frame4.length;
         this.fr5 = (this.fr5 + 1) % frame5.length;
         this.f1 = frame1[this.fr1];
         this.f2 = frame2[this.fr2];
         this.f3 = frame3[this.fr3];
         this.f4 = frame4[this.fr4];
         this.f5 = frame5[this.fr5];
         ++this.f;
         if (this.f > 11) {
            this.f = 0;
         }

         if (this.isChangemap && this.isnextmap && this.timewaitChangeMap - mSystem.currentTimeMillis() < 0L) {
            if (!this.isLoadImage) {
               GameCanvas.gameScr.gameService.sendChangeMapOK();
            }

            this.lastSCR.switchToMe();
            if (Tilemap.lv == 0 && !this.isLoadImage) {
               mSound.playMus(0, mSound.volumeMusic, true);
            }

         } else if (!this.isLoadImage && !this.isOkdialog && this.time - mSystem.currentTimeMillis() < 0L) {
            this.isOkdialog = true;
            GameCanvas.startOKDlg(T.connectFail, this.cmdback);
         } else {
            boolean relogin = false;
            if (timenextLogin - mSystem.currentTimeMillis() > 0L) {
               this.timeWait = (timenextLogin - mSystem.currentTimeMillis()) / 1000L;
            }

            if (timenextLogin - mSystem.currentTimeMillis() <= 0L && timenextLogin > 0L) {
               relogin = true;
            }

            if (relogin) {
               this.timeWait = 0L;
               timenextLogin = 0L;
               GameMidlet.instance.checkLogin();
            }

         }
      }
   }

   public void paint(mGraphics g) {
      g.setColor(0);
      g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
      g.drawImage(GameCanvas.getLogo(), GameCanvas.hw, GameCanvas.hh - 25, 3, false);
      FontTeam.numberSmall_yeallow.drawString(g, GameScr.version, GameCanvas.hw + mGraphics.getImageWidth(GameCanvas.getLogo()) / 2, GameCanvas.hh, 0, false);
      g.drawImage(loading1[this.f1], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(loading2[this.f2], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(loading3[this.f3], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(loading4[this.f4], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(loading5[this.f5], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      if (this.isLoadImage) {
         mFont.tahoma_7_white.drawString(g, ReadMessenge.totalImg * 100 / ReadMessenge.allImage + "%", GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 40, 0, false);
      }

      if (this.timeWait > 0L) {
         mFont.tahoma_7_white.drawString(g, String.valueOf(this.timeWait), GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 40, 0, false);
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         this.lastSCR = null;
         MenuLogin.gI().switchToMe();
         GameCanvas.endDlg();
         Session_ME.gI().close();
      default:
      }
   }
}
