package code.model;

import code.main.GameCanvas;
import code.network.GameService;
import code.screen.MsgChat;
import code.screen.Res;
import code.screen.screen.GameScr;
import lib.mVector;
import lib2.TField;
import lib2.mFont;

public class ChatDetail {
   public static final byte COLOR_WHITE = 0;
   public static final byte COLOR_BLUE = 1;
   public static final byte COLOR_YELLOW = 2;
   public static final byte COLOR_VIOLET = 3;
   public static final byte COLOR_ORANGE = 4;
   public static final byte COLOR_GREEN = 5;
   public static final byte COLOR_RED = 6;
   public static final byte COLOR_BLACK = 7;
   public mVector vecDetail = new mVector();
   public String name;
   public String friend;
   public byte timeNew = -1;
   public boolean isNew = false;
   public TField tfchat;
   public byte typeChat = 0;
   public static final byte TYPE_CHAT = 0;
   public static final byte TYPE_SERVER = 1;
   public static final byte TYPE_ADDFRIEND = 2;
   public int limY = 0;
   int indexColor = 0;
   byte typeColorChat = 0;
   public static final byte TYPE_TROCHUYEN = 0;
   public static final byte TYPE_BANGHOI_NHOM = 1;

   public ChatDetail(String name, byte type) {
      this.name = name;
      this.typeChat = type;
      if (this.typeChat == 0) {
         this.tfchat = new TField(GameCanvas.msgchat.xDia, GameCanvas.msgchat.yDia + GameCanvas.msgchat.hDia - (TField.getHeight() + (GameCanvas.isTouch ? 3 : 10)), GameCanvas.msgchat.wDia, 30);
         this.tfchat.ischat = true;
      } else if (this.typeChat == 2) {
         this.friend = name;
      }

      this.typeColorChat = 0;
   }

   public void addString(String str, String nametext) {
      if (str.length() > 0) {
         String[] mstr = mFont.tahoma_7_white.splitFontArray(str, GameCanvas.msgchat.wDia - GameCanvas.msgchat.wOne5 * 2 - 8);
         MainTextChat[] main = this.addChatNew(mstr, this.setColorText(nametext));
         if (main != null) {
            for(int i = 0; i < main.length; ++i) {
               this.vecDetail.addElement(main[i]);
            }
         }

         this.setLim();
         if (this.limY > 0 && MsgChat.curentfocus != null && MsgChat.curentfocus == this) {
            GameCanvas.msgchat.updateCameraNew(mstr.length);
         }

         if (MsgChat.curentfocus != null && MsgChat.curentfocus != this && this.name.compareTo(T.tinden) != 0) {
            this.isNew = true;
            this.timeNew = (byte)Res.random(1, 11);
         }
      }

   }

   public void addStartChat(String nametext) {
      String str = "";
      if (this.tfchat != null) {
         str = this.tfchat.getText();
      }

      if (str.length() > 0) {
         String[] mstr = mFont.tahoma_7_white.splitFontArray(str, GameCanvas.msgchat.wDia - GameCanvas.msgchat.wOne5 * 2 - 8);
         MainTextChat[] main = this.addChatNew(mstr, (byte)1);
         if (main != null) {
            for(int i = 0; i < main.length; ++i) {
               this.vecDetail.addElement(main[i]);
            }
         }

         this.setLim();
         if (MsgChat.curentfocus != null && MsgChat.curentfocus == this) {
            GameCanvas.msgchat.updateCameraNew(mstr.length);
         }

         GameService.gI().doChat(this.name, str);
      }

      if (this.tfchat != null) {
         this.tfchat.setText("");
      }

   }

   public void setLim() {
      this.limY = this.vecDetail.size() * GameCanvas.hText - (GameCanvas.msgchat.hDia - GameCanvas.wOneItem - 10 - (this.typeChat == 0 ? TField.getHeight() + 2 : 0));
      if (this.limY < 0) {
         this.limY = 0;
      }

   }

   public MainTextChat[] addChatNew(String[] mstr, byte color) {
      if (mstr != null && mstr.length != 0) {
         MainTextChat[] main = new MainTextChat[mstr.length];

         for(int i = 0; i < mstr.length; ++i) {
            main[i] = new MainTextChat(mstr[i], color);
         }

         return main;
      } else {
         return null;
      }
   }

   private byte setColorText(String name) {
      byte num = 0;
      if (name.compareTo(GameScr.mainChar.name) == 0) {
         num = 1;
      }

      return num;
   }
}
