package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.Util;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class NPC extends LiveActor {
   public short type;
   public short idLinhGac;
   public byte typeLimit = -1;
   public String name;
   public byte npcType = 0;
   public short imgid;
   public short height = 0;
   public short width = 0;
   public String stringTalk;
   public String cmdName;
   public short idIcon;
   public byte[] mdata;
   DataEffect mdataeff;
   public byte STAND = 0;
   public byte RUN = 2;
   public byte action;
   public long timechangeAction;
   public byte timeWait;
   int dy = 0;
   int fra = 0;
   byte huongY = 0;
   byte flip = 0;
   byte frame;
   byte countFrame;

   public NPC() {
   }

   public NPC(int id, int x, int y, String name, int imgid, byte[] data, short idicon) {
      this.ID = (short)id;
      this.x = (short)x;
      this.y = (short)y;
      this.name = name;
      this.catagory = 2;
      this.imgid = (short)imgid;
      this.mdata = data;
      this.idIcon = idicon;
      this.width = 30;
      this.height = -1;
      this.action = 0;
      this.timeWait = (byte)Res.random(5, 10);
      this.timechangeAction = mSystem.currentTimeMillis() + (long)(this.timeWait * 1000);
   }

   public void setinfoNPC(int id, int x, int y, String name, int imgid, byte[] data, short idicon) {
      this.mdataeff = null;
      this.mdata = null;
      this.ID = (short)id;
      this.x = (short)x;
      this.y = (short)y;
      this.name = name;
      this.catagory = 2;
      this.imgid = (short)imgid;
      this.mdata = data;
      this.idIcon = idicon;
      this.width = 30;
      this.height = -1;
      this.action = 0;
      this.timeWait = (byte)Res.random(5, 10);
      this.timechangeAction = mSystem.currentTimeMillis() + (long)(this.timeWait * 1000);
   }

   public void setText(String strTalk, String cmdname) {
      this.stringTalk = strTalk;
      this.cmdName = cmdname;
   }

   public String getDisplayName() {
      return this.name;
   }

   public String getStrTalk() {
      return this.stringTalk;
   }

   public String getCmdName() {
      return this.cmdName;
   }

   public short getIDicon() {
      return this.idIcon;
   }

   public int getIDNpc() {
      return this.ID;
   }

   public void paint(mGraphics g) {
      mVector data = new mVector();
      if (this.mdataeff == null) {
         this.mdataeff = new DataEffect(this.mdata, this.imgid, true);
         this.mdata = null;
      } else {
         data.addElement(this.mdataeff);
      }

      if (data.size() != 0) {
         DataEffect deff = (DataEffect)data.elementAt(0);
         mFont.tahoma_7_black.drawString(g, this.name, this.x + 1, 1 + this.y - deff.getHeight() - 13, 2, false);
         mFont.tahoma_7_yellow.drawString(g, this.name, this.x, this.y - deff.getHeight() - 13, 2, false);
         if (this.height == -1) {
            this.height = deff.getHeight();
         }

         int frameQuest = GameScr.getIdImgQuest(this.ID);
         if (frameQuest != -1) {
            int ystart = 0;
            if (frameQuest == 1) {
               ystart = 28;
            }

            if (frameQuest == 2) {
               ystart = 56;
            }

            g.drawRegion(GameScr.imgquest, 0, ystart + (GameCanvas.gameTick % 5 == 0 ? 1 : 0) * 14, 12, 14, 0, this.x, this.y - deff.getHeight() - 40 + GameCanvas.gameTick % 8, 3, false);
         }

         if (deff != null) {
            if (this.width == 0 || this.height == 0) {
               this.width = deff.getWith();
               this.height = deff.getHeight();
            }

            ImageIcon imgNPC = GameData.getImgIcon(this.imgid);
            if (imgNPC != null && imgNPC.img != null) {
               deff.paint(g, deff.getFrame(this.frame, this.action, 0), this.x, this.y, this.flip, imgNPC.img);
            }
         }

         super.paint(g);
      }
   }

   public void paint(mGraphics g, int x, int y) {
   }

   public boolean isNpcChar() {
      return true;
   }

   public void setPosTo(short x, short y) {
      this.x = x;
      this.y = y;
   }

   public void update() {
      if (this.timechangeAction - mSystem.currentTimeMillis() <= 0L && this.action == this.STAND) {
         this.action = this.RUN;
         if (this.imgid == 10003) {
            mSound.playSound(16, mSound.volumeSound);
         }
      }

      DataEffect deff = null;
      mVector data = new mVector();
      if (this.mdataeff != null) {
         data.addElement(this.mdataeff);
      }

      if (data.size() > 0) {
         deff = (DataEffect)data.elementAt(0);
         ++this.countFrame;
         Animation anim = deff.getAnim(this.action, 0);
         if (anim == null) {
            anim = deff.getAnim(0, 0);
            this.action = this.STAND;
         }

         if (this.countFrame > anim.frame.length) {
            this.countFrame = 0;
            if (this.action == this.RUN) {
               this.action = this.STAND;
               this.timechangeAction = mSystem.currentTimeMillis() + (long)(this.timeWait * 1000);
            }
         }

         this.frame = (byte)(this.countFrame % anim.frame.length);
      }

      if (this.chat != null) {
         this.chat.setPos(this.x, this.y - this.height - 10);
         if (this.chat.setOut()) {
            this.chat = null;
         }
      }

      if (GameCanvas.gameScr.posNpcRequest != null && Util.abs(this.x / 16 - GameCanvas.gameScr.posNpcRequest.x / 16) <= 1 && Util.abs(this.y / 16 - GameCanvas.gameScr.posNpcRequest.y / 16) <= 1) {
         if (this.dy > -2) {
            --this.dy;
         } else {
            this.dy = 0;
         }
      }

   }

   public int getLimxL() {
      return 0;
   }

   public int getLimxR() {
      return 0;
   }

   public int getLimyU() {
      return 0;
   }

   public int getLimyD() {
      return 0;
   }

   public boolean isNPC() {
      return true;
   }

   public int isNpcQuest() {
      return GameScr.isNpcQuest(this.type);
   }

   public int getNpcType() {
      return this.npcType;
   }

   public int getTypeNpc() {
      return this.type;
   }

   public int getStateQuest() {
      int state = GameScr.getIdImgQuest(this.ID);
      if (state == 0) {
         return 0;
      } else if (state == 1) {
         return 2;
      } else {
         return state == 2 ? 1 : -1;
      }
   }

   public int getHeight() {
      return this.height;
   }

   public int getWidth() {
      return this.width;
   }

   public String getName() {
      return this.name;
   }

   public boolean isTruRong() {
      return this.ID == 53;
   }

   public boolean isBangKhu() {
      return this.ID == 54;
   }

   public int getColorMiniMap() {
      int[] colorPaint = new int[]{16520709, 16499718, 396543, 14812156};
      int frameQuest = GameScr.getIdImgQuest(this.ID);
      return frameQuest == -1 ? colorPaint[3] : colorPaint[frameQuest];
   }
}
