package code.screen;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.Actor;
import code.model.EffectManager;
import code.model.IActionListener;
import code.model.T;
import code.model.mCommand;
import code.screen.event.EventScreen;
import code.screen.screen.ChangScr;
import code.screen.screen.FontTeam;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;
import code.screen.screen.ServerListScr;
import lib.MyStream;
import lib.Rms;
import lib.Session_ME;
import lib.Tilemap;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class MenuLogin extends ScreenTeam implements IActionListener {
   public static MenuLogin instance;
   private int goc;
   private int xTo;
   private int yTo;
   private int radius = 360;
   private int w = 135;
   private int h = 91;
   public static int cmtoY;
   public static int cmy;
   public static int cmdy;
   public static int cmvy;
   public static int cmyLim;
   public static int index;
   public String[] Text = new String[]{"Chơi mới", "Có tài khoản", "Kinh môn"};
   public int wTF;
   public int hTF;
   public int x;
   public int y;
   public int time;
   public int xC;
   public int wC;
   public boolean isLogo;
   public boolean isPaintHaveAccount;
   public boolean isMainmenu;
   public boolean isPerform;
   public static MenuLogin me;
   public byte selectMenu;
   public byte row;
   public byte Screen;
   public byte focus = 0;
   public final byte RegAcount = 0;
   public final byte HaveAcount = 1;
   public final byte Sever = 2;
   public final byte Mainmenu = 4;
   public static int indexServer = 1;
   public static boolean isLoadQL;
   mCommand cmdSelect;
   public short spx = 1;
   public short spy;

   public static MenuLogin gI() {
      return me == null ? (me = new MenuLogin()) : me;
   }

   public MenuLogin() {
      instance = this;
      this.right = new mCommand("Thoát", this, 1);
   }

   public void switchToMe(boolean isReset) {
      super.switchToMe();
      if (Tilemap.lv != 7 || mSystem.isAndroid) {
         GameData.removeAllImgTree();
         Tilemap.loadMap(7, (byte[])null);
      }

      String nameChar = Rms.loadCharName();
      String str;
      if (!nameChar.equals("")) {
         str = nameChar;
         if (nameChar.length() > 8) {
            str = nameChar.substring(0, 8) + "...";
         }

         this.Text[0] = "Chơi Tiếp: " + str;
         this.Text[1] = "Tài khoản khác";
      } else if (!GameCanvas.loginScr.tfUser.getText().trim().equals("")) {
         str = GameCanvas.loginScr.tfUser.getText();
         if (str.length() > 8) {
            str = str.substring(0, 8) + "...";
         }

         this.Text[0] = "Chơi Tiếp: " + str;
         this.Text[1] = "Tài khoản khác";
      } else {
         str = "";
         str = Rms.loadRMSString(Rms.User_Quick_Play);
         if (str != null && !str.equals("")) {
            this.Text[0] = "Chơi tiếp";
         } else {
            this.Text[0] = "Chơi mới";
         }

         this.Text[1] = "Có tài khoản";
      }

      this.init();
      if (GameScr.mainChar != null) {
         GameScr.mainChar.typePK = -1;
         GameScr.mainChar.idNhom = -1;
      }

      isLoadQL = false;
      GameCanvas.menuSelectitem.idSkillBuff = new int[4][];

      for(int i = 0; i < GameCanvas.menuSelectitem.idSkillBuff.length; ++i) {
         GameCanvas.menuSelectitem.idSkillBuff[i] = new int[4];
         GameCanvas.menuSelectitem.idSkillBuff[i][0] = -1;
         GameCanvas.menuSelectitem.idSkillBuff[i][1] = -1;
         GameCanvas.menuSelectitem.idSkillBuff[i][2] = 0;
         GameCanvas.menuSelectitem.idSkillBuff[i][3] = 0;
      }

      GameScr.VecTime.removeAllElements();
      EventScreen.vecListEvent.removeAllElements();
      EventScreen.vecEventShow.removeAllElements();
      mSound.playMus(0, mSound.volumeMusic, true);
   }

   public void switchToMe() {
      super.switchToMe();
      if (Tilemap.lv != 7 || mSystem.isAndroid) {
         GameData.removeAllImgTree();
         Tilemap.loadMap(7, (byte[])null);
      }

      String nameChar = Rms.loadCharName();
      String str;
      if (!nameChar.equals("")) {
         str = nameChar;
         if (nameChar.length() > 8) {
            str = nameChar.substring(0, 8) + "...";
         }

         this.Text[0] = "Chơi Tiếp: " + str;
         this.Text[1] = "Tài khoản khác";
      } else if (!GameCanvas.loginScr.tfUser.getText().trim().equals("")) {
         str = GameCanvas.loginScr.tfUser.getText();
         if (str.length() > 8) {
            str = str.substring(0, 8) + "...";
         }

         this.Text[0] = "Chơi Tiếp: " + str;
         this.Text[1] = "Tài khoản khác";
      } else {
         str = "";
         str = Rms.loadRMSString(Rms.User_Quick_Play);
         if (str != null && !str.equals("")) {
            this.Text[0] = "Chơi tiếp";
         } else {
            this.Text[0] = "Chơi mới";
         }

         this.Text[1] = "Có tài khoản";
      }

      this.init();
      if (GameScr.mainChar != null) {
         GameScr.mainChar.typePK = -1;
         GameScr.mainChar.idNhom = -1;
      }

      isLoadQL = false;
      GameCanvas.menuSelectitem.idSkillBuff = new int[4][];

      for(int i = 0; i < GameCanvas.menuSelectitem.idSkillBuff.length; ++i) {
         GameCanvas.menuSelectitem.idSkillBuff[i] = new int[4];
         GameCanvas.menuSelectitem.idSkillBuff[i][0] = -1;
         GameCanvas.menuSelectitem.idSkillBuff[i][1] = -1;
         GameCanvas.menuSelectitem.idSkillBuff[i][2] = 0;
         GameCanvas.menuSelectitem.idSkillBuff[i][3] = 0;
      }

      GameScr.VecTime.removeAllElements();
      EventScreen.vecListEvent.removeAllElements();
      EventScreen.vecEventShow.removeAllElements();
      mSound.playMus(0, mSound.volumeMusic, true);
      this.xTo = 0;
      this.yTo = 0;
      GameScr.cmtoX = this.xTo;
      GameScr.cmtoY = this.yTo;
      GameScr.cmx = this.xTo;
      GameScr.cmy = this.yTo;
   }

   public void init() {
      if (!ServerListScr.currentNameServer.equals("")) {
         this.Text[2] = ServerListScr.currentNameServer;
      }

      cmtoY = 0;
      cmy = 0;
      index = 0;
      this.wTF = mGraphics.getImageWidth(GameScr.imgTexFeil);
      this.hTF = mGraphics.getImageHeight(GameScr.imgTexFeil) / 2;
      this.x = GameCanvas.hw;
      this.y = GameCanvas.hh;
      this.isLogo = true;
      this.isMainmenu = true;
      this.time = 30;
      this.row = 2;
      this.left = new mCommand("Chọn", this, 0);
      this.right = new mCommand("Thoát", this, 1);
      if (!mSystem.isj2me) {
         GameCanvas.gameScr.createCharLogin();
      }

   }

   public void paint(mGraphics g) {
      ServerListScr.gI().paintBg(g);
      if (!mSystem.isj2me) {
         for(int i = 0; i < GameCanvas.gameScr.actors.size(); ++i) {
            Actor ac = (Actor)GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null) {
               ac.paint(g);
            }
         }
      }

      GameCanvas.resetTrans(g);
      g.drawImage(GameCanvas.getLogo(), GameCanvas.hw, GameCanvas.hh - 82 + (GameCanvas.isSmallScreen ? 10 : 2), 3, true);
      Res.paintDlgDragonFullNew(g, GameCanvas.hw - 80, GameCanvas.hh - (GameCanvas.isSmallScreen ? 70 : 70) + 30, 160, (GameCanvas.h > 200 ? 130 : 100) - 8 + 23, 60, 60, GameScr.imgBk[2], true);
      int i;
      if (GameScr.isDisConect && GameScr.timeReconnect - System.currentTimeMillis() > 0L) {
         long time = (GameScr.timeReconnect - System.currentTimeMillis()) / 1000L;
         i = mFont.tahoma_7_white.getHeight();
         mFont.tahoma_7_white.drawString(g, "Đăng nhập lại sau: " + time, GameCanvas.hw, GameCanvas.hh - (GameCanvas.isSmallScreen ? 70 : 70) + 30 - i + 18, 2, true);
      }

      int xs = 2;
      int ys = 15;

      for(i = 0; i < 3; ++i) {
         g.drawRegion(GameScr.imgTexFeil, 0, 0, this.wTF, this.hTF, 0, this.x - this.wTF / 2 - this.wTF / 3 - xs, this.y - 10 + i * (this.hTF + 5) - 20 + ys, 0, false);
         g.drawRegion(GameScr.imgTexFeil, 0, 0, this.wTF, this.hTF, 0, this.x - 10 - xs, this.y - 10 + i * (this.hTF + 5) - 20 + ys, 0, false);
         if (this.selectMenu == i && !GameCanvas.isTouch) {
            g.drawRegion(GameScr.imgTexFeil, 0, this.hTF, this.wTF, this.hTF, 0, this.x - this.wTF / 2 - this.wTF / 3 - xs, this.y - 10 + i * (this.hTF + 5) - 20 + ys, 0, false);
            g.drawRegion(GameScr.imgTexFeil, 0, this.hTF, this.wTF, this.hTF, 0, this.x - 10 - xs, this.y - 10 + i * (this.hTF + 5) - 20 + ys, 0, false);
         }
      }

      if (this.isPerform) {
         g.drawRegion(GameScr.imgTexFeil, 0, this.hTF, this.wTF, this.hTF, 0, this.x - this.wTF / 2 - this.wTF / 3 - xs, this.y - 10 + this.selectMenu * (this.hTF + 5) - 20 + ys, 0, false);
         g.drawRegion(GameScr.imgTexFeil, 0, this.hTF, this.wTF, this.hTF, 0, this.x - 10 - xs, this.y - 10 + this.selectMenu * (this.hTF + 5) - 20 + ys, 0, false);
      }

      g.setColor(16777215);

      for(i = 0; i < this.Text.length; ++i) {
         if (i == 2) {
            FontTeam.normalFont[0].drawString(g, T.serverName[indexServer], GameCanvas.hw, GameCanvas.hh + i * (this.hTF + 5) - this.hTF / 2 + 3 - 8 + ys, 2, false);
         } else {
            FontTeam.normalFont[0].drawString(g, this.Text[i], GameCanvas.hw, GameCanvas.hh + i * (this.hTF + 5) - this.hTF / 2 + 3 - 8 + ys, 2, false);
         }
      }

      GameCanvas.resetTrans(g);
      FontTeam.numberSmall_yeallow.drawString(g, GameScr.version, GameCanvas.hw + 65, GameCanvas.hh - (GameCanvas.isSmallScreen ? 70 : 70) + 30 - 10, 0, false);
   }

   public void update() {
      if (GameScr.isDisConect && GameScr.timeReconnect - System.currentTimeMillis() <= 0L) {
         String user = GameCanvas.loginScr.tfUser.getText();
         String pass = GameCanvas.loginScr.tfPass.getText();
         String add = ServerListScr.address[ServerListScr.index];
         String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
         if (!user.equals("") && !pass.equals("")) {
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
            GameCanvas.loginScr.doLogin(user, pass, add, port);
         }
      }

      super.update();
      this.updateKey();
      if (!mSystem.isj2me) {
         for(int i = 0; i < GameCanvas.gameScr.actors.size(); ++i) {
            Actor ac = (Actor)GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null) {
               ac.update();
            }
         }
      }

      if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(GameCanvas.w / 2 - 75, GameCanvas.hh - 82 + (GameCanvas.isSmallScreen ? 10 : 2) - 35, 150, 70, 0)) {
         mSystem.requestLink(GameMidlet.linkWeb);
         GameCanvas.isPointerClick[0] = false;
      }

      this.updateCamera();
   }

   public void updateCamera() {
      ++this.goc;
      if (this.goc > 360) {
         this.goc = 0;
      }

      if (GameScr.cmx == GameScr.cmxLim && this.spx > 0) {
         this.spx = 0;
         this.spy = 1;
      }

      if (GameScr.cmy == GameScr.cmyLim && this.spy > 0) {
         this.spx = -1;
         this.spy = 0;
      }

      if (GameScr.cmx == 0 && this.spx < 0) {
         this.spx = 0;
         this.spy = -1;
      }

      if (GameScr.cmy == 0 && this.spy < 0) {
         this.spx = 1;
         this.spy = 0;
      }

      this.xTo += this.spx;
      this.yTo += this.spy;
      GameScr.cmtoX = this.xTo;
      GameScr.cmtoY = this.yTo;
      GameCanvas.gameScr.updateCamera();
   }

   public void updateKey() {
      if (GameCanvas.currentDialog == null) {
         if (GameCanvas.isKeyPressed(2)) {
            --this.selectMenu;
            if (this.selectMenu < 0) {
               this.selectMenu = this.row;
            }
         }

         if (GameCanvas.isKeyPressed(8)) {
            ++this.selectMenu;
            if (this.selectMenu > this.row) {
               this.selectMenu = 0;
            }
         }

         for(int i = 0; i < 3; ++i) {
            int xs = 2;
            int ys = 15;
            if (!GameCanvas.menu.showMenu && GameCanvas.isPointerHoldIn(this.x - this.wTF / 2 - this.wTF / 3 - xs, this.y - 10 + i * (this.hTF + 5) - 20 + ys, this.wTF + this.wTF / 2 + 15, this.hTF, 0) && GameCanvas.isPointerDown[0]) {
               this.isPerform = true;
               this.selectMenu = (byte)i;
            }
         }

         if (GameCanvas.isKeyPressed(5)) {
            this.left.performAction();
         }

         if (GameCanvas.isPointerJustRelease[0]) {
            if (GameCanvas.isPointer(this.x - this.wTF / 2 - this.wTF / 3 - 2, this.y - 10 + this.selectMenu * (this.hTF + 5) - 20 + 15, this.wTF + this.wTF / 2 + 15, this.hTF, 0)) {
               if (this.isPerform) {
                  this.left.performAction();
                  GameCanvas.isPointerJustRelease[0] = false;
                  this.isPerform = false;
               }
            } else {
               this.isPerform = false;
            }
         }

      }
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void docmdcenter() {
      switch(this.selectMenu) {
      case 0:
         GameCanvas.gameScr.actors.removeAllElements();
         GameScr.vecCharintro.removeAllElements();
         GameCanvas.gameScr.charlogin.removeAllElements();
         EffectManager.lowEffects.removeAllElements();
         EffectManager.hiEffects.removeAllElements();
         GameScr.arrowsUp.removeAllElements();
         String user = GameCanvas.loginScr.tfUser.getText().toLowerCase().trim();
         String pass = GameCanvas.loginScr.tfPass.getText();
         String add = ServerListScr.address[ServerListScr.index];
         String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
         if (user.equals("")) {
            user = Rms.loadRMSString(Rms.User_Quick_Play);
            pass = Rms.loadRMSString(Rms.Pass_Quick_Play);
         }

         if (user == null) {
            user = "";
         }

         if (pass == null) {
            pass = "";
         }

         if (user.equals("")) {
            GameCanvas.gameScr.onMapOffline(39, 0, 0);
            GameScr.Next = 0;
            GameScr.CountIntro = 0;
            GameScr.CountMoveFirst = 0;
            GameScr.finishTalk = false;
            GameScr.co = 0;
            GameScr.timepopup = 0;
            GameScr.SteepCount = 0;
            GameScr.chatcount = 0;
            GameCanvas.gameScr.startIntro();
            GameScr.clazz = 0;
            GameScr.ismovefirst = false;
            GameScr.fireIntro = false;
            GameScr.tam.removeAllElements();
            user = "-1";
            pass = "-1";
            GameCanvas.loginScr.doLogin(user, pass, add, port);
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
         } else {
            GameCanvas.loginScr.savePass();
            GameScr.isIntro = false;
            GameCanvas.loginScr.doLogin(user, pass, add, port);
            ChangScr.gI().switchToMe();
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
         }
         break;
      case 1:
         this.CheckAccount();
         break;
      case 2:
         mVector vec = new mVector();

         for(int i = 0; i < T.serverName.length; ++i) {
            mCommand cmd = new mCommand(T.serverName[i], this, 5);
            vec.addElement(cmd);
         }

         GameCanvas.menu.startAt(vec, 2);
      }

   }

   public void CheckAccount() {
      if (GameCanvas.loginScr.tfUser != null && !GameCanvas.loginScr.tfUser.getText().equals("")) {
         GameCanvas.loginScr.switchToMe();
      }

      byte[] data = Rms.loadRMS(Rms.User_Pass);
      if (data == null) {
         GameCanvas.loginScr.switchToMe();
      } else {
         MyStream dis = new MyStream(data, false);

         try {
            boolean isRememPass = dis.readBoolean();
            if (isRememPass) {
               String user = dis.readUTF();
               String strauto = "";
               if (user.length() > 11) {
                  strauto = user.substring(0, 11);
               }

               if (strauto.equals("ngulongauto")) {
                  if (!Rms.loadCharName().equals("")) {
                     String t = T.nhanvat + " " + Rms.loadCharName() + T.textXacThuc;
                     GameCanvas.startYesNoDlg(t, new mCommand(T.yes, this, 6));
                  } else {
                     GameCanvas.startYesNoDlg("Tài khoản" + T.textXacThuc, new mCommand(T.yes, this, 6));
                  }
               } else {
                  GameCanvas.loginScr.switchToMe();
               }
            }

            dis.close();
         } catch (Exception var7) {
            var7.printStackTrace();
         }

      }
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         this.docmdcenter();
         break;
      case 1:
         GameMidlet var10000 = GameMidlet.instance;
         GameMidlet.notifyDestroyed();
         break;
      case 2:
         GameCanvas.gameScr.actors.removeAllElements();
         GameScr.vecCharintro.removeAllElements();
         GameCanvas.gameScr.charlogin.removeAllElements();
         EffectManager.lowEffects.removeAllElements();
         GameScr.arrowsUp.removeAllElements();
         EffectManager.hiEffects.removeAllElements();
         String user = GameCanvas.loginScr.tfUser.getText().toLowerCase().trim();
         String pass = GameCanvas.loginScr.tfPass.getText();
         String add = ServerListScr.address[ServerListScr.index];
         String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
         if (user.equals("")) {
            user = Rms.loadRMSString(Rms.User_Quick_Play);
            pass = Rms.loadRMSString(Rms.Pass_Quick_Play);
         }

         if (user == null) {
            user = "";
         }

         if (pass == null) {
            pass = "";
         }

         if (user.equals("")) {
            GameCanvas.gameScr.onMapOffline(39, 0, 0);
            GameCanvas.gameScr.startIntro();
            user = "-1";
            pass = "-1";
            Rms.deleteAllRMS();
            GameCanvas.loginScr.doLogin(user, pass, add, port);
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
         } else {
            GameCanvas.loginScr.savePass();
            GameScr.isIntro = false;
            GameCanvas.loginScr.doLogin(user, pass, add, port);
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
         }
         break;
      case 3:
         GameCanvas.loginScr.switchToMe();
      case 4:
      default:
         break;
      case 5:
         indexServer = GameCanvas.menu.menuSelectedItem;
         Session_ME.gI().close();
         Rms.saveIndexSV();
         break;
      case 6:
         GameCanvas.loginScr.switchToMe();
         GameCanvas.endDlg();
         break;
      case 7:
         GameCanvas.endDlg();
      }

   }
}
