package code.screen.event;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Item;
import code.network.GameService;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class MainEvent implements IActionListener {
   public int IDObj;
   public int IDCmd;
   public int isNew;
   public int numThachDau;
   public int idChar;
   public int iditem;
   public String nameEvent;
   public String contentEvent;
   public static final byte CHAT = 0;
   public static final byte KET_BAN = 1;
   public static final byte PARTY = 2;
   public static final byte BUY_SELL = 3;
   public static final byte THACH_DAU = 4;
   public static final byte MOI_VAO_CLAN = 5;
   public static final byte THACH_DAU_1 = 6;
   public static final byte THACH_DAU_2 = 7;
   public static final byte GIVE_ITEM = 8;
   public Item item;
   public String tile;
   public String der;
   public boolean isRemove = false;

   public MainEvent(int Obj, int Cmd, String name, String content) {
      this.IDObj = Obj;
      this.IDCmd = Cmd;
      this.nameEvent = name;
      this.contentEvent = content;
   }

   public MainEvent(int Obj, int Cmd, String name, String content, int iditem, Item item, String tile, String der) {
      this.IDObj = Obj;
      this.IDCmd = Cmd;
      this.nameEvent = name;
      this.contentEvent = content;
      this.item = item;
      this.tile = tile;
      this.der = der;
      this.iditem = iditem;
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 1:
         try {
            String index = (String)p;
            if (p.equals("1")) {
               GameService.gI().Friend(Byte.parseByte(index), this.nameEvent);
            } else if (p.equals("2")) {
               GameService.gI().Friend(Byte.parseByte(index), this.nameEvent);
            }
         } catch (Exception var4) {
         }

         this.isRemove = true;
         GameCanvas.endDlg();
         GameScr.numMSG = 0;
         break;
      case 2:
         this.isRemove = true;
         GameCanvas.endDlg();
         GameScr.numMSG = 0;
         break;
      case 3:
         this.isRemove = true;
         GameCanvas.endDlg();
         GameScr.numMSG = 0;
         break;
      case 4:
         this.isRemove = true;
         GameScr.numMSG = 0;
         GameService.gI().dosendThachdau((byte)1, (short)this.idChar);
         GameCanvas.endDlg();
         break;
      case 5:
         GameService.gI().OkClan((byte)2, this.nameEvent, (short)1);
         GameScr.numMSG = 0;
         this.isRemove = true;
         GameCanvas.endDlg();
         break;
      case 6:
         this.isRemove = true;
         GameScr.numMSG = 0;
         GameService.gI().dosendThachdau((byte)2, (short)this.idChar);
         GameCanvas.endDlg();
         break;
      case 7:
         GameService.gI().OkClan((byte)2, this.nameEvent, (short)0);
         GameScr.numMSG = 0;
         this.isRemove = true;
         GameCanvas.endDlg();
      }

   }
}
