package code.screen.screen;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.Actor;
import code.model.IActionListener;
import code.model.T;
import code.model.mCommand;
import code.network.GameService;
import code.screen.MenuLogin;
import code.screen.Res;
import code.screen.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import lib.MyStream;
import lib.Rms;
import lib.Session_ME;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.TField;

public class LoginScr extends ScreenTeam implements IActionListener {
   public TField tfUser = new TField();
   public TField tfPass;
   public String linkgetPass = "http://5ltb.com/forum/app/?for=event&do=resetpass&lang=store";
   int focus;
   int xC;
   int wC;
   int yL;
   int defYL;
   private boolean isRememPass = true;
   private mCommand cmdLogin;
   public static LoginScr me;
   public mCommand cmdQuaylai;
   public mCommand cmdgetpass;
   public long timetoNextLogin;
   int xTo;
   int yTo;
   int centerX = 160;
   int centerY = 160;
   int radius = 128;
   int goc = 0;
   int index = 0;
   int tick = 0;
   public short spx = 1;
   public short spy;

   public static LoginScr gI() {
      return me == null ? (me = new LoginScr()) : me;
   }

   public void init() {
      this.wC = 110;
      this.xC = GameCanvas.hw - this.wC / 2;
      this.tfUser.width = this.wC + 40;
      this.tfUser.y = GameCanvas.hh - 20;
      this.tfPass.y = GameCanvas.hh + 30;
      this.tfPass.width = this.wC + 40;
      this.tfUser.x = this.xC - 20;
      this.tfPass.x = this.xC - 20;
      this.cmdgetpass = new mCommand("", this, 12);
      this.cmdgetpass.setindexImage(1);
      this.cmdgetpass.x = this.tfPass.x + this.tfPass.width - 20;
      this.cmdgetpass.y = this.tfPass.y + 6;
   }

   public LoginScr() {
      this.tfUser.height = ScreenTeam.ITEM_HEIGHT + (GameCanvas.isTouch ? 8 : 2) + 4;
      this.tfUser.setText("");
      if (!GameCanvas.isTouch) {
         this.tfUser.isFocus = true;
      }

      this.tfPass = new TField();
      this.tfPass.height = ScreenTeam.ITEM_HEIGHT + (GameCanvas.isTouch ? 8 : 2) + 4;
      this.tfPass.isFocus = false;
      this.tfPass.setIputType(2);
      this.tfPass.setText("");
      if (mSystem.isPC || mSystem.isIP) {
         this.tfUser.setStringNull(T.taikhoan);
         this.tfPass.setStringNull(T.matkhau);
      }

      this.loadPass();
      this.init();
      this.cmdLogin = new mCommand(T.OK, this, 0, xCCmd, yCCmd);
      this.cmdQuaylai = new mCommand(T.choimoi, this, 8, xLCmd, yLCmd);
      if (!GameCanvas.isTouch) {
         this.center = this.cmdLogin;
         this.left = this.cmdQuaylai;
      } else {
         this.cmdLogin.setXY(GameCanvas.w / 2 - 80, GameCanvas.h / 2 + 63);
         this.cmdQuaylai.setXY(GameCanvas.w / 2, GameCanvas.h / 2 + 63);
      }

      this.right = this.tfUser.cmdClear;
   }

   public boolean doShowAgent(String user) {
      return user.equals("showagentpro");
   }

   public void doLogin(String user, String pass, String add, String port) {
      if (this.timetoNextLogin - mSystem.currentTimeMillis() <= 0L) {
         this.timetoNextLogin = mSystem.currentTimeMillis() + 1000L;
         if (pass.equals("")) {
            this.focus = 1;
            this.tfUser.isFocus = false;
            this.tfPass.isFocus = true;
            this.right = this.tfPass.cmdClear;
         } else {
            GameCanvas.connect(add, port);
            GameService.gI().setClientType();
            GameService.gI().login(user, pass, GameScr.version);
            ChangScr.gI().switchToMe();
         }
      }
   }

   public void doLogin2(String user, String pass, String add, String port) {
      if (pass.equals("")) {
         this.focus = 1;
         this.tfUser.isFocus = false;
         this.tfPass.isFocus = true;
         this.right = this.tfPass.cmdClear;
      } else {
         GameCanvas.connect(add, port);
         GameService.gI().setClientType();
         GameService.gI().login(user, pass, GameScr.version);
      }
   }

   public void reloadData() {
      this.tfUser.y = GameCanvas.hh - 5;
      this.tfPass.y = GameCanvas.hh + 33;
   }

   protected void doRegister() {
   }

   protected void doSendRegisterInfo(String add, String port) {
      if (!Session_ME.gI().connected) {
         GameCanvas.startWaitDlg("Đang kết nối", true);
      } else {
         GameCanvas.startWaitDlg("Đang đăng ký", true);
      }

   }

   public boolean keyPress(int keyCode) {
      boolean swallow;
      if (this.tfUser.isFocus) {
         swallow = this.tfUser.keyPressed(keyCode);
         if (swallow) {
            return true;
         }
      } else if (this.tfPass.isFocus) {
         swallow = this.tfPass.keyPressed(keyCode);
         if (swallow) {
            return true;
         }
      }

      return super.keyPress(keyCode);
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
      this.paintPanel(g);
      GameCanvas.resetTrans(g);
      g.drawImage(GameCanvas.imgLogo, GameCanvas.hw, GameCanvas.hh - 80 + (GameCanvas.isSmallScreen ? 10 : 0), 3, false);
      FontTeam.numberSmall_yeallow.drawString(g, GameScr.version, GameCanvas.hw + 75, GameCanvas.hh - (GameCanvas.isSmallScreen ? 70 : 70) + 30 - 13, 0, false);
      if (GameCanvas.isTouch) {
         this.cmdgetpass.paint(g);
      }

   }

   void paintPanel(mGraphics g) {
      Res.paintDlgDragonFullNew(g, GameCanvas.hw - 90, GameCanvas.hh - 58 + 15, 180, GameCanvas.isTouch ? 140 : 120, 60, 60, GameScr.imgBk[2], true);
      FontTeam.normalFont[0].drawString(g, T.taikhoan, this.xC + 5 - 20, GameCanvas.hh - 32, 0, false);
      FontTeam.normalFont[0].drawString(g, T.matkhau, this.xC + 5 - 20, GameCanvas.hh + 18, 0, false);
      this.tfUser.paint(g);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      this.tfPass.paint(g);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      super.paint(g);
      if (GameCanvas.isTouch && this.cmdLogin != null) {
         this.cmdLogin.paint(g);
         this.cmdQuaylai.paint(g);
      }

   }

   public void switchToMe() {
      super.switchToMe();
      this.init();
      GameCanvas.gameScr.createCharLogin();
   }

   public void updatefire() {
      Util.quickSort(GameCanvas.gameScr.actors);

      for(int i = 0; i < GameCanvas.gameScr.actors.size(); ++i) {
         Actor ac = (Actor)GameCanvas.gameScr.actors.elementAt(i);
         if (ac != null) {
            ac.update();
         }
      }

   }

   public void updateCamera() {
      MenuLogin.gI().updateCamera();
   }

   public void update() {
      this.updateCamera();
      if (GameCanvas.isTouch && this.getCmdPointerLast(this.cmdgetpass)) {
         GameCanvas.isPointerJustRelease[0] = false;
         if (this.cmdgetpass != null) {
            this.cmdgetpass.performAction();
         }
      }

      if (!mSystem.isj2me) {
         this.updatefire();
      }

      this.tfUser.update();
      this.tfPass.update();
      if (this.tick < 20) {
         ++this.tick;
         if (this.tick == 20) {
            GameCanvas.gameScr.tfChat.x = 2;
            GameCanvas.gameScr.tfChat.y = GameCanvas.h - 40;
         }
      }

      if (GameCanvas.keyPressedz[2]) {
         ++this.index;
         if (this.index >= 52) {
            this.index = 0;
         }
      }

      boolean is = false;
      if (GameCanvas.isKeyPressed(2)) {
         --this.focus;
         if (this.focus < 0) {
            this.focus = 1;
         }

         is = true;
      } else if (GameCanvas.isKeyPressed(8)) {
         ++this.focus;
         if (this.focus > 1) {
            this.focus = 0;
         }

         is = true;
      }

      if (is) {
         if (this.focus == 1) {
            this.tfUser.isFocus = false;
            this.tfPass.isFocus = true;
            if (!GameCanvas.isTouch) {
               this.center = this.cmdLogin;
            }
         } else if (this.focus == 0) {
            this.tfUser.isFocus = true;
            this.tfPass.isFocus = false;
            if (!GameCanvas.isTouch) {
               this.center = this.cmdLogin;
            }
         }
      }

      if (!GameCanvas.isTouch) {
         if (this.tfUser.isFocus) {
            this.right = this.tfUser.cmdClear;
         } else if (this.tfPass.isFocus) {
            this.right = this.tfPass.cmdClear;
         }
      }

      if (GameCanvas.isTouch) {
         if (this.getCmdPointerLast(this.cmdLogin)) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdLogin != null) {
               this.cmdLogin.performAction();
            }
         }

         if (this.getCmdPointerLast(this.cmdQuaylai)) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdQuaylai != null) {
               this.cmdQuaylai.performAction();
            }
         }
      }

      if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(GameCanvas.w / 2 - 75, GameCanvas.hh - 82 + (GameCanvas.isSmallScreen ? 10 : 2) - 35, 150, 70, 0)) {
         mSystem.requestLink(GameMidlet.linkWeb);
         GameCanvas.isPointerClick[0] = false;
      }

      super.update();
   }

   public void savePass() {
      if (this.tfUser.getText() == null || !this.tfUser.getText().equals("")) {
         ByteArrayOutputStream bo = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(bo);

         try {
            dos.writeBoolean(this.isRememPass);
            if (this.isRememPass) {
               dos.writeUTF(this.tfUser.getText());
               dos.writeUTF(this.tfPass.getText());
            }

            Rms.saveRMS(Rms.User_Pass, bo.toByteArray());
            dos.close();
         } catch (Exception var16) {
            var16.printStackTrace();
         } finally {
            try {
               dos.close();
            } catch (Exception var15) {
            }

            try {
               bo.close();
            } catch (Exception var14) {
            }

         }

      }
   }

   public void savePassPlayNew() {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bo);

      try {
         dos.writeBoolean(this.isRememPass);
         if (this.isRememPass) {
            dos.writeUTF("");
            dos.writeUTF("");
         }

         Rms.saveRMS(Rms.User_Pass, bo.toByteArray());
         dos.close();
      } catch (Exception var16) {
         var16.printStackTrace();
      } finally {
         try {
            dos.close();
         } catch (Exception var15) {
         }

         try {
            bo.close();
         } catch (Exception var14) {
         }

      }

   }

   public void loadPass() {
      byte[] data = Rms.loadRMS(Rms.User_Pass);
      if (data != null) {
         MyStream dis = new MyStream(data, false);

         try {
            this.isRememPass = dis.readBoolean();
            if (this.isRememPass) {
               String user = dis.readUTF();
               String pass = dis.readUTF();
               String strauto = "";
               if (user.length() > 11) {
                  strauto = user.substring(0, 11);
               }

               if (!strauto.equals("ngulongauto")) {
                  this.tfUser.setTextPC(user);
                  this.tfUser.setOffsetPC();
                  this.tfPass.setTextPC(pass);
                  this.tfPass.setOffsetPC();
               }
            }

            dis.close();
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         if (this.tfUser != null && !this.tfUser.getText().equals("")) {
            GameCanvas.loginScr.savePass();
            GameScr.isIntro = false;
            String user = this.tfUser.getText();
            String pass = this.tfPass.getText();
            String add = ServerListScr.address[ServerListScr.index];
            String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
            GameCanvas.loginScr.doLogin(user, pass, add, port);
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
         } else {
            MenuLogin.gI().switchToMe(false);
         }
      case 1:
      case 7:
      default:
         break;
      case 2:
         mVector menu = new mVector();
         mCommand co = new mCommand("Trạng thái kết nối", this, 3);
         menu.addElement(co);
         GameCanvas.menu.startAt(menu, 0);
         break;
      case 3:
         mVector me = new mVector();
         me.addElement(new mCommand("GPRS", this, 4));
         me.addElement(new mCommand("Wifi", this, 5));
         GameCanvas.menu.startAt(me, 0);
         break;
      case 4:
         GameCanvas.isWifi = false;
         Rms.saveRMSInt("wifi", 0);
         break;
      case 5:
         GameCanvas.isWifi = true;
         Rms.saveRMSInt("wifi", 1);
         break;
      case 6:
         mVector menu0 = new mVector();
         menu0.addElement(new mCommand("Chọn máy chủ", this, 8));
         GameCanvas.menu.startAt(menu0, 0);
         break;
      case 8:
         GameScr.playNew();
         this.savePassPlayNew();
         this.tfUser.setText("");
         this.tfPass.setText("");
         Rms.deleteAllRMS();
         break;
      case 9:
         this.focus = 1;
         this.tfUser.isFocus = false;
         this.tfPass.isFocus = true;
         this.right = this.tfPass.cmdClear;
         GameCanvas.endDlg();
         break;
      case 10:
         String add10 = ServerListScr.address[ServerListScr.index];
         String port10 = String.valueOf(ServerListScr.port[ServerListScr.index]);
         this.doSendRegisterInfo(add10, port10);
         break;
      case 11:
         GameCanvas.endDlg();
         break;
      case 12:
         GameCanvas.startYesNoDlg(T.tgetPass, new mCommand("", this, 13));
         break;
      case 13:
         mSystem.requestLink(this.linkgetPass);
         GameCanvas.endDlg();
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
