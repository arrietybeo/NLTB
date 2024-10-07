package code.main;

import code.model.BangAuto;
import code.model.CharPartInfo;
import code.model.IActionListener;
import code.model.InputDlg;
import code.model.MainChar;
import code.model.MapScr;
import code.model.Menu2;
import code.model.Notify;
import code.model.Paint;
import code.model.ReadMessenge;
import code.model.ShopOnline;
import code.model.T;
import code.model.mCommand;
import code.screen.MenuLogin;
import code.screen.MsgChat;
import code.screen.Res;
import code.screen.event.EventScreen;
import code.screen.screen.ChangScr;
import code.screen.screen.CreateCharScr;
import code.screen.screen.Dialog;
import code.screen.screen.GameScr;
import code.screen.screen.LoginScr;
import code.screen.screen.Menu;
import code.screen.screen.MenuSelectItem;
import code.screen.screen.MessageScr;
import code.screen.screen.MsgDlg;
import code.screen.screen.ScreenTeam;
import com.team.ngulong.MyGdxGame;
import java.util.Random;
import javax.microedition.lcdui.Image;
import lib.Context;
import lib.Main;
import lib.MainUnity;
import lib.Rms;
import lib.Session_ME;
import lib.TCanvas;
import lib.Tilemap;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class GameCanvas extends TCanvas implements IActionListener {
   public static GameCanvas instance;
   public static boolean bRun;
   public static boolean isLoadTileServer;
   public static boolean isLowGraphics = false;
   public static int hCommand = 25;
   public static boolean[] keyPressedz = new boolean[14];
   public static boolean[] keyReleasedz = new boolean[14];
   public static boolean[] keyHold = new boolean[14];
   public static int[] px = new int[2];
   public static int[] py = new int[2];
   public static int[] pxLast = new int[2];
   public static int[] pyLast = new int[2];
   public static boolean[] isPointerDown = new boolean[2];
   public static boolean[] isPointerClick = new boolean[2];
   public static boolean[] isPointerJustRelease = new boolean[2];
   public static boolean[] isPointerJustDown = new boolean[2];
   public static int gameTick;
   public static int waitTick;
   public static int wOneItem = 20;
   public static int hMaxContent;
   public static int wOne5;
   public static int wDia;
   public static int w;
   public static int h;
   public static int hw;
   public static int hh;
   public static boolean isMoto;
   public static ScreenTeam currentScreen;
   public static GameScr gameScr;
   public static ChangScr loadingscreen;
   public static Random r = new Random(mSystem.currentTimeMillis());
   public static Menu menu;
   public static MenuSelectItem menuSelectitem;
   public static LoginScr loginScr;
   public static Dialog currentDialog;
   public static Menu2 menu2;
   public static EventScreen mevent;
   public static MapScr mapScr;
   public static MsgDlg msgdlg = new MsgDlg();
   public static InputDlg inputDlg;
   public static boolean isLoad = false;
   public static boolean isBB;
   public static boolean isWifi = false;
   public static Image imgLogo;
   public static Image imgShuriken;
   public static final Object LOCK = new Object();
   public static boolean isVirHorizontal;
   public static boolean isPointerRelease;
   public static boolean isPointerDownItem;
   public static BangAuto autoTab;
   public static ShopOnline shop;
   static boolean isStart = false;
   public static boolean isSmartPhone;
   public static boolean isTouch;
   public static boolean isScreenSize240;
   public static boolean isScreenSize200;
   int isClearAll;
   public static Image[] imgBorder = new Image[3];
   public static int hText = 20;
   public static mCommand cmdNgatKetNoi;
   public static Paint paintz;
   public static MsgChat msgchat;
   public static ReadMessenge readMessenge;
   public static Notify notify;
   public static String url = "";
   public static T t = new T();
   public static String[] nameTile = new String[]{"t_hang", "t_thanh"};
   mGraphics g = new mGraphics();
   static MsgDlg timeoutDlg = new MsgDlg();
   static int timeout = 0;
   public static boolean lowGraphic;
   public static boolean isSmallScreen;

   public GameCanvas(Context context) {
      super(context);
      this.initGame();
      isBB = true;
   }

   public GameCanvas() {
      this.initGame();
   }

   public void initGame() {
      CharPartInfo.loadDataCharPart();
      isLoad = true;
      instance = this;
      menu2 = new Menu2();
      this.isClearAll = Rms.loadRMSInt("loadClearRMS");
      if (this.isClearAll == -1) {
         Rms.deleteAllRMS();
         Rms.saveRMSInt("loadClearRMS", 1);
      }

      this.setFullScreenMode(true);
      System.gc();
      this.loadSize();
      (new Thread(Res.load)).start();
      getLogo();
      if (w * h >= 70400) {
         isSmartPhone = this.hasPointerEvents();
      }

      if (w <= 200) {
         isScreenSize200 = true;
      }

      if (w > 200 && w < 320) {
         isScreenSize240 = true;
      }

      isTouch = this.hasPointerEvents();
      if (isTouch) {
         ScreenTeam.xLCmd = w / 2 - 78 - 5;
         ScreenTeam.yLCmd = h - 40;
         ScreenTeam.xRCmd = w / 2 + 5;
         ScreenTeam.yRCmd = h - 40;
         ScreenTeam.xCCmd = w / 2 - 37;
         ScreenTeam.yCCmd = h - 40;
      }

      if (!isTouch) {
         ScreenTeam.xLCmd = 5;
         ScreenTeam.yLCmd = h - 13;
         ScreenTeam.xCCmd = w / 2;
         ScreenTeam.yCCmd = h - 13;
         ScreenTeam.xRCmd = w - 5;
         ScreenTeam.yRCmd = h - 13;
      }

      this.getTileFromRMS();
      paintz = new Paint();
      if (h <= 160) {
         Paint.hTab = 15;
      }

      Res.load();
      notify = new Notify();
      GameScr.loadBegin();
      MenuSelectItem.load();
      loadingscreen = new ChangScr();
      readMessenge = new ReadMessenge();
      msgchat = new MsgChat();
      if (this.hasPointerEvents()) {
         menu = new Menu();
      } else {
         menu = new Menu();
      }

      menuSelectitem = new MenuSelectItem();
      MainChar.loadAuto();
      if (w < 200) {
         isSmallScreen = true;
      }

      if (isTouch) {
         wOneItem = 26;
      } else if (w >= 240) {
         wOneItem = 24;
      }

      if (h < 240 && wOneItem > 24) {
         wOneItem = 24;
      }

      hMaxContent = h - hCommand * 2;
      wOne5 = (byte)(wOneItem / 5);
      mevent = new EventScreen();
      GameScr.CheckIP();
   }

   public boolean hasPointerEvents() {
      return true;
   }

   public void getTileFromRMS() {
      byte[] data = Rms.loadRMS(nameTile[0]);
      if (data == null) {
         isLoadTileServer = true;
      }

   }

   public void setFullScreenMode(boolean isb) {
   }

   public static Image createImage(String path) {
      String x = Main.isAndroid ? "" : "/";
      path = x + path;
      Image img = null;

      try {
         img = Image.createImage(path);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return img;
   }

   public static String getPath(String path) {
      String x = MainUnity.isAndroid ? "x" : "/x";
      path = x + 2 + path;
      return path;
   }

   public static Image loadImage(String path) {
      path = getPath(path);
      Image img = null;
      if (img == null) {
         byte[] data = Rms.loadRMS(mSystem.getPathRMS(path));
         if (data != null) {
            img = Image.createImage((byte[])data, 0, data.length);
            return img;
         }
      }

      img = Image.createImage(path);
      return img;
   }

   public static Image loadImage(String path, String pathtRms) {
      path = getPath(path);
      Image img = null;
      if (img == null) {
         byte[] data = Rms.loadRMS(mSystem.getPathRMS(pathtRms));
         if (data != null) {
            img = Image.createImage((byte[])data, 0, data.length);
            return img;
         }
      }

      img = Image.createImage(path);
      return img;
   }

   public static Image getLogo() {
      if (imgLogo == null) {
         imgLogo = loadImage("/logo.png");
      }

      return imgLogo;
   }

   public static void Connect() {
   }

   public static void connect(String add, String port) {
      add = GameScr.SV_IP[MenuLogin.indexServer];
      port = GameScr.PORT[MenuLogin.indexServer];
      System.out.println("ket noi sv " + add + " " + port);
      Session_ME.gI().connect(add, port);
   }

   public void init() {
      gameScr = new GameScr();
      loginScr = new LoginScr();
      inputDlg = new InputDlg();
      mapScr = new MapScr();
      ScreenTeam.load();
      cmdNgatKetNoi = new mCommand("", this, 1);
   }

   public static void addChat(String name, String info, boolean isFocus) {
      start_Chat_Dialog();
      msgchat.addNewChat(name, "", info, (byte)0, isFocus);
   }

   public static void start_Chat_Dialog() {
      msgchat.checkRemoveText();
      msgchat.init();
      msgchat.isShow = true;
      MsgChat.curentfocus = null;
      currentScreen = msgchat;
      if (msgchat.lastSCR == null) {
         msgchat.lastSCR = gameScr;
      }

   }

   public void loadSize() {
      w = this.getWidthz();
      h = this.getHeightz();
   }

   public void sizeChanged(int w0, int h0) {
      this.loadSize();
      hw = w / 2;
      hh = h / 2;
      if (currentScreen != null) {
         if (currentScreen == loginScr) {
            loginScr.init();
         }

         gameScr.init();
         if (currentScreen == MessageScr.me) {
            MessageScr.gI().init();
         }

         if (currentScreen == CreateCharScr.me) {
            CreateCharScr.gI().init();
         }

         currentScreen.init();
         if (currentDialog == inputDlg) {
            inputDlg.init();
         }
      }

   }

   public void start() {
      if (!isStart) {
         (new Thread(this)).start();
      }

      isStart = true;
   }

   public void keyPressedz(int keycode) {
   }

   public void keyPressed(int keyCode) {
      if (!isLoad) {
         if (isMoto) {
            switch(keyCode) {
            case -23:
               keyCode = -8;
               break;
            case -22:
            case 22:
               keyCode = -7;
               break;
            case -21:
            case 21:
               keyCode = -6;
               break;
            case -20:
               keyCode = -5;
               break;
            case -6:
               keyCode = -2;
               break;
            case -5:
               keyCode = -4;
               break;
            case -2:
               keyCode = -3;
            }
         } else if (isBB) {
            switch(keyCode) {
            case 1:
               keyHold[2] = keyPressedz[2] = true;
               return;
            case 2:
               keyHold[4] = keyPressedz[4] = true;
               return;
            case 3:
            case 4:
            case 7:
            default:
               if (currentScreen == gameScr) {
                  switch(keyCode) {
                  case 101:
                     keyHold[2] = keyPressedz[2] = true;
                     return;
                  case 102:
                     keyHold[6] = keyPressedz[6] = true;
                     return;
                  case 115:
                     keyHold[4] = keyPressedz[4] = true;
                     return;
                  case 120:
                     keyHold[8] = keyPressedz[8] = true;
                     return;
                  }
               }
               break;
            case 5:
               keyHold[6] = keyPressedz[6] = true;
               return;
            case 6:
               keyHold[8] = keyPressedz[8] = true;
               return;
            case 8:
               keyHold[5] = keyPressedz[5] = true;
               return;
            }
         }

         if (keyCode == 10) {
            keyCode = -5;
         }

         if (currentScreen != null && !currentScreen.keyPress(keyCode)) {
            this.mapKeyPress(keyCode);
         }

         if (currentDialog != null) {
            currentDialog.keyPress(keyCode);
         }

      }
   }

   public void mapKeyPress(int keyCode) {
      switch(keyCode) {
      case -22:
      case -7:
         keyHold[13] = keyPressedz[13] = true;
         return;
      case -21:
      case -6:
         keyHold[12] = keyPressedz[12] = true;
         return;
      case -5:
         keyHold[5] = keyPressedz[5] = true;
         return;
      case 8:
      case 32:
      case 49:
         keyPressedz[5] = true;
         return;
      case 35:
         keyHold[11] = keyPressedz[11] = true;
         return;
      case 42:
         keyHold[10] = keyPressedz[10] = true;
         return;
      case 50:
         keyPressedz[1] = true;
         return;
      case 51:
         keyPressedz[3] = true;
         return;
      case 52:
         keyPressedz[7] = true;
         return;
      case 53:
         keyPressedz[9] = true;
         return;
      case 97:
         if (currentScreen == gameScr && currentDialog == null) {
            keyHold[4] = keyPressedz[4] = true;
         }

         return;
      case 100:
         if (currentScreen == gameScr && currentDialog == null) {
            keyHold[6] = keyPressedz[6] = true;
         }

         return;
      case 115:
         if (currentScreen == gameScr && currentDialog == null) {
            keyHold[8] = keyPressedz[8] = true;
         }

         return;
      case 119:
         if (currentScreen == gameScr && currentDialog == null) {
            keyHold[2] = keyPressedz[2] = true;
         }

         return;
      default:
         if (keyCode < 65 || keyCode > 122) {
            switch(keyCode) {
            case 1:
               keyHold[2] = keyPressedz[2] = true;
               return;
            case 2:
               keyHold[4] = keyPressedz[4] = true;
               return;
            case 3:
            case 4:
            case 7:
            default:
               break;
            case 5:
               keyHold[6] = keyPressedz[6] = true;
               return;
            case 6:
               keyHold[8] = keyPressedz[8] = true;
               return;
            case 8:
               keyHold[5] = keyPressedz[5] = true;
               return;
            }
         }

      }
   }

   public void keyReleased(int keyCode) {
      if (!isLoad) {
         if (isMoto) {
            switch(keyCode) {
            case -23:
               keyCode = -8;
               break;
            case -22:
            case 22:
               keyCode = -7;
               break;
            case -21:
            case 21:
               keyCode = -6;
               break;
            case -20:
               keyCode = -5;
               break;
            case -6:
               keyCode = -2;
               break;
            case -5:
               keyCode = -4;
               break;
            case -2:
               keyCode = -3;
            }
         } else if (isBB) {
            if (keyCode == 27) {
               keyCode = -7;
            }

            if (currentScreen == gameScr) {
               switch(keyCode) {
               case 101:
                  keyHold[2] = keyPressedz[2] = false;
                  keyReleasedz[2] = true;
                  return;
               case 102:
                  keyHold[6] = keyPressedz[6] = false;
                  keyReleasedz[6] = true;
                  return;
               case 115:
                  keyHold[4] = keyPressedz[4] = false;
                  keyReleasedz[4] = true;
                  return;
               case 120:
                  keyHold[8] = keyPressedz[8] = false;
                  keyReleasedz[8] = true;
                  return;
               }
            }
         }

         this.mapKeyRelease(keyCode);
      }
   }

   public void mapKeyRelease(int keyCode) {
      switch(keyCode) {
      case -22:
      case -7:
         keyHold[13] = false;
         keyReleasedz[13] = true;
         return;
      case -21:
      case -6:
         keyHold[12] = false;
         keyReleasedz[12] = true;
         return;
      case -5:
      case 10:
         keyHold[5] = false;
         keyReleasedz[5] = true;
         return;
      case 35:
         keyHold[11] = false;
         keyReleasedz[11] = true;
         return;
      case 42:
         keyHold[10] = false;
         keyReleasedz[10] = true;
         return;
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
         keyHold[keyCode - 48] = false;
         keyReleasedz[keyCode - 48] = true;
         return;
      case 97:
         keyHold[4] = false;
         keyReleasedz[4] = true;
         return;
      case 100:
         keyHold[6] = false;
         keyReleasedz[6] = true;
         return;
      case 115:
         keyHold[8] = false;
         keyReleasedz[8] = true;
         return;
      case 119:
         keyHold[2] = false;
         keyReleasedz[2] = true;
         return;
      default:
         switch(keyCode) {
         case 1:
            keyHold[2] = false;
            keyReleasedz[2] = true;
            return;
         case 2:
            keyHold[4] = false;
            keyReleasedz[4] = true;
            return;
         case 3:
         case 4:
         case 7:
         default:
            return;
         case 5:
            keyHold[6] = false;
            keyReleasedz[6] = true;
            return;
         case 6:
            keyHold[8] = false;
            keyReleasedz[8] = true;
            return;
         case 8:
            keyHold[5] = false;
            keyReleasedz[5] = true;
         }
      }
   }

   protected void onPointerDragged(int x, int y, int index) {
      px[index] = x;
      py[index] = y;
   }

   protected void onPointerPressed(int x, int y, int index) {
      isPointerJustDown[index] = true;
      isPointerDown[index] = true;
      if (currentScreen != null && currentScreen.isMapScr()) {
         isPointerClick[index] = true;
      }

      px[index] = x;
      py[index] = y;
      pxLast[index] = x;
      pyLast[index] = y;
   }

   protected void onPointerReleased(int x, int y, int index) {
      isPointerDown[index] = false;
      isPointerJustRelease[index] = true;
      isPointerClick[index] = true;
      px[index] = x;
      py[index] = y;
   }

   public static void clearKeyHold() {
      for(int i = 0; i < 14; ++i) {
         keyHold[i] = false;
      }

   }

   public static void clearKeyHold(int keycode) {
      for(int i = 0; i < isPointerDown.length; ++i) {
         isPointerDown[i] = false;
         isPointerJustRelease[i] = false;
      }

      isPointerRelease = false;
      keyHold[keycode] = false;
   }

   public static void clearKeyPressed() {
      int i;
      for(i = 0; i < isPointerClick.length; ++i) {
         isPointerClick[i] = false;
         isPointerJustRelease[i] = false;
      }

      isPointerRelease = false;

      for(i = 0; i < 14; ++i) {
         keyPressedz[i] = false;
      }

   }

   public static void clearKeyPressed(int keycode) {
      for(int i = 0; i < isPointerDown.length; ++i) {
         isPointerDown[i] = false;
         isPointerJustRelease[i] = false;
      }

      isPointerRelease = false;
      keyPressedz[keycode] = false;
   }

   public static void resetTrans(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      g.setClip(0, 0, w, h);
   }

   public int getWidth() {
      return MyGdxGame.getWidth();
   }

   public int getHeight() {
      return MyGdxGame.getHeight();
   }

   public void paint(mGraphics gx) {
      this.g.g = gx.g;
      if (currentScreen != null) {
         currentScreen.paint(this.g);
      }

      if (menu2.isShow) {
         menu2.paint(this.g);
      }

      if (currentDialog != null) {
         currentDialog.paint(this.g);
      }

      if (menu.showMenu) {
         menu.paintMenu(this.g);
      }

      if (menuSelectitem.showMenu) {
         menuSelectitem.paintMenu(this.g);
      }

      if (isLoad) {
         resetTrans(this.g);
         this.g.drawImage(imgLogo, this.getWidth() >> 1, this.getHeight() >> 1, 3, false);
      }

      if (notify != null && currentScreen != null && GameScr.canupdatenotify()) {
         notify.paint(this.g);
      }

      if (isBB) {
         synchronized(LOCK) {
            LOCK.notify();
         }
      }

      GameScr.paintinfo18plush(this.g);
      resetTrans(this.g);
   }

   public static void hideAllDialog() {
      endDlg();
      menu.doCloseMenuPC();
      menu2.isShow = false;
      menuSelectitem.doCloseMenu();
   }

   public static int abs(int i) {
      return i > 0 ? i : -i;
   }

   public static void endDlg() {
      currentDialog = null;
   }

   public static void addInfoCharCline(String str) {
      if (str != null && str.length() > 0) {
         GameScr.VecInfoChar.addElement(str);
      }

   }

   public static void addNotify(String textNot, byte typeadd) {
      if (typeadd == 0 && textNot != null && textNot.length() > 0) {
         if (mFont.tahoma_7_black.getWidth(textNot) > 140) {
            addInfoCharCline(textNot);
         } else {
            notify.text = textNot;
            notify.time = 0;
            notify.yshow = 10;
         }
      }

      if (typeadd == 1) {
         GameScr.VecInfoServer.addElement(textNot);
      }

   }

   public static void startYesNoDlgPutArr(String info, String[] arr, mCommand yesAction, mCommand noAction, mCommand center) {
      msgdlg.show();
      msgdlg.center.caption = "";
      msgdlg.left.caption = "Có";
      msgdlg.right.caption = "Không";
   }

   public static void StartNextDiaLog(String info, mCommand nexcmd, int idicon) {
      msgdlg.setInfo("", info, nexcmd, new mCommand(T.close, instance, 0), true, idicon);
      msgdlg.show();
   }

   public static void StartHelp(String info, mCommand nexcmd, int idicon, boolean isEnd) {
      if (!isEnd) {
         msgdlg.setInfo("", info, nexcmd, new mCommand(T.close, instance, 0), true, idicon);
      } else {
         msgdlg.setInfo(info, new mCommand(T.close, instance, 0), idicon);
      }

      msgdlg.show();
   }

   public static void StartTalkWithNPC(String info, String npcName, mCommand cmdyes, mCommand cmdNo, int idicon) {
      msgdlg.setInfo(npcName, info, cmdyes, cmdNo, true, idicon);
      msgdlg.show();
   }

   public static void startOKDlg(String info) {
      msgdlg.setInfo(info, (mCommand)null, new mCommand("OK", instance, 0), (mCommand)null);
      msgdlg.show();
   }

   public static void startOKDlg(String info, String murl) {
      url = murl;
      msgdlg.setInfo(info, new mCommand("OK", instance, 2), (mCommand)null, new mCommand(T.close, instance, 0));
      msgdlg.show();
   }

   public static void startOKDlg(String info, boolean isicon) {
      msgdlg.setInfo(info, (mCommand)null, new mCommand("OK", instance, 0), (mCommand)null);
      msgdlg.show();
   }

   public static void startYesNoDlg(String info, mCommand yesAction, mCommand noAction, mCommand center) {
      msgdlg.setInfo(info, yesAction, center, noAction);
      msgdlg.show();
      msgdlg.center.caption = "";
      msgdlg.left.caption = "Có";
      msgdlg.right.caption = "Không";
   }

   public static void startYesNoDlg(String info, mCommand yesAction, mCommand noAction) {
      msgdlg.setInfo(info, yesAction, noAction);
      msgdlg.show();
      if (msgdlg.center != null) {
         msgdlg.center.caption = "";
      }

      msgdlg.left.caption = "Có";
      msgdlg.right.caption = "Không";
   }

   public static void startWaitDlg(String info, boolean isIcon) {
      msgdlg.setInfo(info, (mCommand)null, new mCommand("Cancel", instance, 0), (mCommand)null);
      msgdlg.show();
   }

   public static void startWaitDlg() {
      msgdlg.startWaitingDialog();
      msgdlg.show();
   }

   public static void startOKDlg(String info, boolean isIcon, int timeout) {
      timeoutDlg.setInfo(info, (mCommand)null, new mCommand("OK", instance, 0), (mCommand)null);
      GameCanvas.timeout = timeout;
   }

   public static void startOKDlg(String info, mCommand action) {
      action.caption = "OK";
      msgdlg.setInfo(info, (mCommand)null, action, (mCommand)null);
      msgdlg.show();
   }

   public static void startYesNoDlg(String info, mCommand yesAction) {
      mCommand cmdN = new mCommand("Không", instance, 0);
      yesAction.caption = "Có";
      msgdlg.setInfo(info, yesAction, (mCommand)null, cmdN);
      msgdlg.show();
      if (msgdlg.center != null) {
         msgdlg.center.caption = "";
      }

   }

   public static boolean isKeyPressed(int index) {
      if (keyPressedz[index]) {
         keyPressedz[index] = false;
         return true;
      } else {
         return false;
      }
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

   public static int collisionCmdBar() {
      if (isPointer(0, h - ScreenTeam.deltaY * 2, 50, ScreenTeam.deltaY * 2, 0)) {
         return 0;
      } else if (isPointer(hw - 25, h - ScreenTeam.deltaY * 2, 50, ScreenTeam.deltaY * 2, 0)) {
         return 1;
      } else {
         return isPointer(w - 50, h - ScreenTeam.deltaY * 2, 50, ScreenTeam.deltaY * 2, 0) ? 2 : -1;
      }
   }

   public static boolean isPointer(int x, int y, int w, int h, int index) {
      if (!isPointerDown[index] && !isPointerClick[index]) {
         return false;
      } else {
         return px[index] >= x && px[index] <= x + w && py[index] >= y && py[index] <= y + h;
      }
   }

   public static String getPriceMoney(long xu, int gold) {
      String price = "";
      if (xu > 0L) {
         price = price + getMoneys(xu) + "Xu";
      }

      if (gold > 0) {
         if (xu > 0L) {
            price = price + " - ";
         }

         price = price + getMoneys((long)gold) + "Luong";
      }

      return price;
   }

   public static String formatNumberDotK(int number) {
      String strNum = "";
      String strTemp = "" + number;
      if (strTemp.length() <= 3) {
         return strTemp;
      } else {
         for(int i = strTemp.length() - 1; i >= 0; --i) {
            if ((strTemp.length() - i) % 3 == 0 && i > 0) {
               strNum = strNum + strTemp.charAt(i) + ".";
            } else {
               strNum = strNum + strTemp.charAt(i);
            }
         }

         String result = (new StringBuffer(strNum)).reverse().toString();
         return result;
      }
   }

   public static boolean isPointerHoldIn(int x, int y, int w, int h, int index) {
      if (!isPointerDown[index] && !isPointerJustRelease[index]) {
         return false;
      } else {
         return px[index] >= x && px[index] <= x + w && py[index] >= y && py[index] <= y + h;
      }
   }

   public static boolean isPointerNj(int x, int y, int w, int h, int index) {
      if (!isPointerDown[index] && !isPointerJustRelease[index]) {
         return false;
      } else {
         return px[index] >= x && px[index] <= x + w && py[index] >= y && py[index] <= y + h;
      }
   }

   public static boolean isPointLast(int x, int y, int w, int h, int index) {
      return pxLast[index] >= x && pxLast[index] <= x + w && pyLast[index] >= y && pyLast[index] <= y + h;
   }

   public static int random(int a, int b) {
      return a + r.nextInt(b - a);
   }

   public static boolean canTouch() {
      return Math.abs(px[0] - pxLast[0]) < 10 && Math.abs(py[0] - pyLast[0]) < 10;
   }

   public void update() {
      try {
         if (notify != null && currentScreen != null && GameScr.canupdatenotify()) {
            notify.update();
         }

         gameScr.updateTips();
         ++gameTick;
         if (gameTick > 10000) {
            gameTick = 0;
         }

         if (menu.showMenu) {
            menu.updateMenu();
            menu.updateMenuKey();
         }

         if (currentDialog != null) {
            currentDialog.update();
         }

         if (menu2.isShow) {
            menu2.update();
         }

         if (menuSelectitem.showMenu) {
            menuSelectitem.updateMenu();
            menuSelectitem.updateMenuKey();
         }

         if (currentScreen != null) {
            currentScreen.update();
            currentScreen.updateKey();
         }

         clearKeyPressed();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static void clearAllPointerEvent() {
      for(int i = 0; i < isPointerClick.length; ++i) {
         isPointerClick[i] = false;
         isPointerDown[i] = false;
         isPointerJustDown[i] = false;
         isPointerJustRelease[i] = false;
      }

   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         endDlg();
         break;
      case 1:
         endDlg();
         Session_ME.gI().close();
         Tilemap.loadMap(7, (byte[])null);
         MenuLogin.gI().switchToMe();
         break;
      case 2:
         if (!url.equals("")) {
            mSystem.requestLink(url);
         }

         endDlg();
      }

   }

   public static void paintShukiren(int x, int y, mGraphics g) {
      int f = waitTick % 9;
      ++waitTick;
      if (waitTick == 1000) {
         waitTick = 0;
      }

      g.drawRegion(imgShuriken, 0, f * 16, 16, 16, 0, x, y, mGraphics.HCENTER | mGraphics.VCENTER, false);
   }

   public static void start_Select_Dialog(String str, mVector cmd) {
      msgdlg.setinfo(str, cmd);
      msgdlg.show();
   }

   public static void starBoxDialog(String tile, mVector vecItem) {
      msgdlg.setInfoBox(tile, vecItem);
      msgdlg.show();
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
