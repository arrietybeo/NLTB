package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Scroll;
import code.model.ScrollResult;
import code.model.mCommand;
import code.screen.MenuLogin;
import code.screen.Res;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lib.MyStream;
import lib.Rms;
import lib.Tilemap;
import lib.mGraphics;

public class ServerListScr extends ScreenTeam implements IActionListener {
   private static ServerListScr me;
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
   public static String currentNameServer = "";
   public static String[] nameServer = new String[]{"test", "test", "test", "test"};
   public static String[] address = new String[]{"192.168.1.90", "192.168.1.90", "192.168.1.90", "192.168.1.90"};
   public static short[] port = new short[]{19155, 19155, 19155, 19155};
   public static final String linkGetHost = "http://teamobi.com/srvips/NQSH2.txt";
   public static String addressSave = null;
   public static short portSave = -1;
   Scroll cr = new Scroll();
   boolean trans;
   int pa;
   public short spx = 1;
   public short spy;

   public static ServerListScr gI() {
      return me == null ? (me = new ServerListScr()) : me;
   }

   public void switchToMe() {
      super.switchToMe();
      this.updateCamera();
      this.init();
      this.cr.clear();
      index = 0;
      if (index >= 0 && index <= nameServer.length - 1) {
         currentNameServer = nameServer[index];
      }

   }

   public ServerListScr() {
      loadIP();
      this.center = new mCommand(GameCanvas.isTouch ? "" : "Chọn", this, 0, xCCmd, yCCmd);
      addressSave = null;
      portSave = -1;
   }

   public void init() {
      GameScr.cmx = GameScr.cmtoX;
      GameScr.cmy = GameScr.cmtoY;
      cmtoY = 0;
      cmy = 0;
      index = 0;
      cmyLim = nameServer.length * 16 - (this.h - 10);
      if (cmyLim < 0) {
         cmyLim = 0;
      }

      this.spx = 1;
      this.spy = 0;
   }

   public void doUpdateServer() {
   }

   public static void saveIP() {
      ByteArrayOutputStream bo = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(bo);

      try {
         dos.writeByte(nameServer.length);

         for(int i = 0; i < nameServer.length; ++i) {
            dos.writeUTF(nameServer[i]);
            dos.writeUTF(address[i]);
            dos.writeShort(port[i]);
         }

         Rms.saveRMS("ipnqsh", bo.toByteArray());
         dos.close();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static void loadIP() {
      byte[] bData = Rms.loadRMS("ipnqsh");
      if (bData != null) {
         MyStream dis = new MyStream(bData, false);
         if (dis != null) {
            try {
               byte len = dis.readByte();
               nameServer = new String[len];
               address = new String[len];
               port = new short[len];

               for(int i = 0; i < len; ++i) {
                  nameServer[i] = dis.readUTF();
                  address[i] = dis.readUTF();
                  port[i] = dis.readShort();
               }

               dis.close();
            } catch (IOException var4) {
               var4.printStackTrace();
            }

         }
      }
   }

   public void update() {
      if (cmy != cmtoY) {
         cmvy = cmtoY - cmy << 2;
         cmdy += cmvy;
         cmy += cmdy >> 4;
         cmdy &= 15;
      }

      this.updateCamera();
   }

   public void updateKey() {
      boolean changeFocus = false;
      if (GameCanvas.isKeyPressed(2)) {
         --index;
         if (index < 0) {
            index = nameServer.length - 1;
         }

         currentNameServer = nameServer[index];
         changeFocus = true;
      } else if (GameCanvas.isKeyPressed(8)) {
         ++index;
         if (index >= nameServer.length) {
            index = 0;
         }

         currentNameServer = nameServer[index];
         changeFocus = true;
      }

      if (changeFocus) {
         cmtoY = index * 16 - (this.h - 10) / 2;
         if (cmtoY < 0) {
            cmtoY = 0;
         }

         if (cmtoY > cmyLim) {
            cmtoY = cmyLim;
         }
      }

      if (changeFocus) {
         this.cr.cmtoY = index * 30 - (this.h - 10) / 2;
         if (this.cr.cmtoY < 0) {
            this.cr.cmtoY = 0;
         }

         if (this.cr.cmtoY > this.cr.cmyLim) {
            this.cr.cmtoY = this.cr.cmyLim;
         }
      }

      super.updateKey();
      ScrollResult r = this.cr.updateKey();
      if (r.isDowning || r.isFinish) {
         index = r.selected;
         if (index >= 0 && index <= nameServer.length) {
            currentNameServer = nameServer[index];
         }
      }

      if (r.isFinish && this.center != null) {
         this.center.performAction();
      }

      this.cr.updatecm();
   }

   public boolean isPointer(int x, int y, int w, int h) {
      return GameCanvas.px[0] >= x && GameCanvas.px[0] <= x + w && GameCanvas.py[0] >= y && GameCanvas.py[0] <= y + h;
   }

   public void resetCMR() {
      this.xTo = 0;
      this.yTo = 0;
      GameScr.cmtoX = this.xTo;
      GameScr.cmtoY = this.yTo;
      GameScr.cmx = this.xTo;
      GameScr.cmy = this.yTo;
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
      GameScr.cmtoX = this.xTo + Tilemap.w;
      GameScr.cmtoY = this.yTo + Tilemap.h;
      GameCanvas.gameScr.updateCamera();
   }

   public void paintBg(mGraphics g) {
      GameCanvas.resetTrans(g);
      g.translate(-GameScr.cmx, -GameScr.cmy);
      Tilemap.paintTile(g);
      Tilemap.paintTileTop(g);
   }

   public void paint(mGraphics g) {
      g.setColor(-16777216);
      g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
      this.paintBg(g);
      GameCanvas.resetTrans(g);
      g.drawImage(GameCanvas.getLogo(), GameCanvas.hw, GameCanvas.hh - 70, 3, false);
      this.paintPanel(g);
      if (!GameCanvas.isTouch) {
         super.paint(g);
      }

      FontTeam.numberSmall_yeallow.drawString(g, GameScr.version, GameCanvas.w - 4, 1, 1, false);
   }

   private void paintPanel(mGraphics g) {
      Res.paintDlgDragonFullNew(g, GameCanvas.hw - 80, GameCanvas.hh - 80 + 40, 160, GameCanvas.h > 200 ? 130 : 100, 60, 60, GameScr.imgBk[2], true);
      g.setClip(GameCanvas.hw - 100, GameCanvas.hh - 36, this.w, this.h - 7);
      g.ClipRec(GameCanvas.hw - 100, GameCanvas.hh - 36, this.w, this.h - 7);
      g.translate(0, -cmy);
      GameCanvas.resetTrans(g);
      this.cr.setStyle(nameServer.length + 1, 30, GameCanvas.hw - 100, GameCanvas.hh - 30, 200, GameCanvas.h > 200 ? 140 : 110, true, 1);
      this.cr.setClip(g, GameCanvas.hw - 100, GameCanvas.hh - 33, 200, GameCanvas.h > 200 ? 120 : 90);

      for(int i = 0; i < nameServer.length; ++i) {
         if (index == i) {
            g.setColor(3812871);
            g.fillRect(GameCanvas.hw - 75, GameCanvas.hh - 38 + index * 30 + 5, 151, 30, false);
            g.setColor(12742927);
            g.fillRect(GameCanvas.hw - 73, GameCanvas.hh - 36 + index * 30 + 5, 147, 26, false);
         }

         FontTeam.normalFont[0].drawString(g, nameServer[i], GameCanvas.hw, GameCanvas.hh - 26 + i * 30, 2, false);
      }

      if (!GameCanvas.isTouch) {
         if (this.left != null) {
            FontTeam.fontTile.drawString(g, this.left.caption, 5, GameCanvas.h - 13, 0, false);
         }

         if (this.center != null) {
            FontTeam.fontTile.drawString(g, this.center.caption, GameCanvas.hw, GameCanvas.h - 13, 2, false);
         }

         if (this.right != null) {
            FontTeam.fontTile.drawString(g, this.right.caption, GameCanvas.w - 5, GameCanvas.h - 13, 1, false);
         }
      } else {
         this.paintCmdBar(g, this.left, this.center, this.right);
      }

      mGraphics.resetTransAndroid(g);
      g.restoreCanvas();
   }

   public static void close() {
      me = null;
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         MenuLogin.gI().switchToMe();
      default:
      }
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
