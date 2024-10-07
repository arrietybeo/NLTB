package code.model;

import code.main.GameCanvas;
import code.network.GameService;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;

public class Quest implements IActionListener {
   public static final byte TYPE_KILL_MONS = 0;
   public static final byte TYPE_TRANSFORM = 1;
   public static final byte TYPE_GET_ITEM = 2;
   public static final byte TYPE_TALK = 3;
   public static final byte TYPE_KILL_MONSTER_LEVEL = 4;
   public static final byte RECEIVE = 0;
   public static final byte DEL = 2;
   public static final byte RESPONSE = 1;
   public byte type = 0;
   public short idQuest;
   public short idNpcReceive = 32000;
   public short idNpcResponse = 32000;
   public byte main_sub = 0;
   public mVector content = new mVector();
   public mVector rescontent = new mVector();
   public String scontent = "";
   public String decript = "";
   public String name = "";
   public byte stateQuest = 0;
   public boolean isShow = false;
   public short deltaLv = 0;
   byte index = 0;

   public Quest(int id) {
      this.index = 0;
      this.stateQuest = -1;
      this.idQuest = (short)id;
   }

   public String getInfoPaintScr() {
      String info = "";
      return info;
   }

   public byte[] getIdColor() {
      byte[] id = new byte[1];
      return id;
   }

   public boolean isTalk() {
      return this.type == 3;
   }

   public boolean isGetItem() {
      return this.type == 2;
   }

   public boolean isKillMons() {
      return this.type == 0;
   }

   public boolean isKillMonsByLv() {
      return this.type == 4;
   }

   public boolean isTransform() {
      return this.type == 1;
   }

   public boolean isFinish() {
      return this.stateQuest == 1;
   }

   public boolean isReceive() {
      return this.stateQuest == 0;
   }

   public boolean isWorking() {
      return this.stateQuest == 2;
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         GameService.gI().onSendInfoQuest(0, this.idQuest, this.main_sub);
         this.isShow = false;
         GameCanvas.endDlg();
         break;
      case 1:
         this.isShow = false;
         this.index = 0;
         mSystem.println("2");
         GameCanvas.endDlg();
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
