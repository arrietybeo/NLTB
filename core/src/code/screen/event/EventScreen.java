package code.screen.event;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Item;
import code.model.Scroll;
import code.model.ScrollResult;
import code.model.T;
import code.model.mCamera;
import code.model.mCommand;
import code.network.GameService;
import code.screen.MsgChat;
import code.screen.Res;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;
import code.screen.screen.SetInfoData;
import code.screen.screen.ShowinfoItem;
import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class EventScreen extends ScreenTeam implements IActionListener {
   public int hItem;
   public int wDia;
   public int maxSizeList;
   public int hbutton;
   public int hDia;
   public int min;
   public int max;
   public int xDia;
   public int yDia;
   public int numw;
   public int numh;
   public int idSelect;
   public int idCommand;
   public int tickIcon;
   mCommand cmdSeL;
   mCommand cmdDel;
   mCommand cmdClose;
   public static mVector vecListEvent = new mVector("EventScreen vecListEvent");
   public static mVector vecEventShow = new mVector("EventScreen vecEventShow");
   public static mVector cmdList = new mVector("EventScreen cmdList");
   mCamera cameraDia = new mCamera();
   Scroll scr = new Scroll();
   ScreenTeam lastScreen;
   int xBegin;
   int w2cmd;

   public EventScreen() {
      this.cmdSeL = new mCommand(T.view, this, 2, 0, 0);
      this.cmdDel = new mCommand(T.delMess, this, 2, 0, 0);
      this.hItem = 34;
      this.wDia = GameCanvas.w / 4 * 3;
      if (this.wDia > 180) {
         this.wDia = 180;
      }

   }

   public void setCaptionCmd() {
      this.cmdSeL.caption = T.view;
      this.cmdDel.caption = T.delMess;
   }

   public void switchToMe() {
      super.switchToMe();
      GameScr.numMess = 0;
   }

   public void switchToMe(ScreenTeam last) {
      this.lastScreen = last;
      this.switchToMe();
   }

   public void init() {
      for(int i = 0; i < vecListEvent.size(); ++i) {
         MainEvent obj = (MainEvent)vecListEvent.elementAt(i);
         if (obj.isRemove) {
            vecListEvent.removeElement(obj);
            --i;
         }
      }

      this.hbutton = mCommand.hButtonCmd * 3 / 2;
      if (GameCanvas.isTouch) {
         this.hbutton = 0;
      }

      this.maxSizeList = (GameCanvas.h / 2 - (10 + GameCanvas.hCommand)) / this.hItem + 1;
      this.hDia = this.hItem * this.maxSizeList + 10 + GameCanvas.hCommand;
      this.maxSizeList += 2;
      this.min = 0;
      this.max = this.maxSizeList;
      if (this.max > vecListEvent.size()) {
         this.max = vecListEvent.size();
      }

      this.xDia = GameCanvas.hw - this.wDia / 2;
      this.yDia = GameCanvas.hh - this.hDia / 2 - GameCanvas.hCommand / 2;
      this.numw = (this.wDia - 6) / 32;
      this.numh = (this.hDia - 6) / 32;
      this.cmdClose = new mCommand("Đóng", this, 0);
      this.cmdClose.setXY(this.xDia + this.wDia / 2 - mCommand.wButtonCmd / 2, this.yDia + this.hDia + mCommand.hButtonCmd - 3);
      this.updateList();
   }

   private void updateList() {
      cmdList.removeAllElements();
      mCommand cmd = new mCommand(T.close, -1, this);
      if (GameCanvas.isTouch) {
         cmd.setXY(this.xDia + this.wDia - 12, this.yDia + 10);
         cmdList.addElement(cmd);
      } else {
         if (vecListEvent.size() > 0) {
            mCommand cmdselect = new mCommand(T.menu, 1, this);
            cmdList.addElement(cmdselect);
         }

         cmdList.addElement(cmd);
         this.setPosCmdNew(0);
         this.right = cmd;
      }

   }

   public void setPosCmdNew(int yplus) {
      this.idCommand = 0;
      if (cmdList.size() > 0) {
         int t = cmdList.size();
         if (t == 1) {
            this.xBegin = this.xDia + this.wDia / 2;
            this.w2cmd = 0;
         } else if (t == 2) {
            this.w2cmd = 20;
            this.xBegin = this.xDia + this.wDia / 2 - this.w2cmd / 2 - mCommand.wButtonCmd / 2;
         } else {
            this.w2cmd = 20;
            this.xBegin = this.xDia + this.wDia / 2 - this.w2cmd / 2 - mCommand.wButtonCmd / 2;
         }

         for(int i = 0; i < t; ++i) {
            mCommand cmd = (mCommand)cmdList.elementAt(i);
            cmd.isSelect = false;
            if (t == 3 && i == 2) {
               cmd.setXY(this.xDia + this.wDia / 2, this.yDia + this.hDia - mCommand.hButtonCmd - (t - 1) / 2 * (mCommand.hButtonCmd + 5) + 7 + i / 2 * (mCommand.hButtonCmd + 5) + yplus);
            } else {
               cmd.setXY(this.xBegin + i % 2 * (mCommand.wButtonCmd + this.w2cmd), this.yDia + this.hDia - mCommand.hButtonCmd - (t - 1) / 2 * (mCommand.hButtonCmd + 5) + 7 + i / 2 * (mCommand.hButtonCmd + 5) + yplus);
            }

            if (i == 0) {
               cmd.isSelect = true;
            }
         }
      }

   }

   public void paint(mGraphics g) {
      if (this.lastScreen != null) {
         this.lastScreen.paint(g);
      }

      if (GameCanvas.currentScreen == this) {
         GameCanvas.resetTrans(g);
         if (mSystem.isPC) {
            g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
         }

         this.paintFormList(g, this.xDia, this.yDia, this.wDia, this.hDia, T.mevent);
         this.scr.setStyle(vecListEvent.size(), 34, this.xDia, this.yDia + 22, this.wDia, this.hDia, true, 1);
         g.ClipRec(this.xDia, this.yDia + 22, this.wDia, this.hDia);
         this.scr.setClip(g, this.xDia, this.yDia + 22, this.wDia, this.hDia);
         int yp = this.yDia + GameCanvas.hCommand + 3;
         int i;
         if (vecListEvent.size() > 0) {
            if (this.idSelect >= 0) {
               Res.paintFocus(g, this.xDia + 5, yp - 2 + this.idSelect * this.hItem, this.wDia - 8);
            }

            yp += this.hItem * this.min;

            for(i = this.min; i < this.max; ++i) {
               if (i >= 0 || i < vecListEvent.size()) {
                  MainEvent obj = (MainEvent)vecListEvent.elementAt(i);
                  if (obj != null) {
                     int fStart = 0;
                     int ficon = 0;
                     if (obj.IDCmd == 0) {
                        fStart = 4;
                     } else if (obj.IDCmd == 1) {
                        fStart = 0;
                     } else if (obj.IDCmd == 4) {
                        fStart = 2;
                     }

                     if (obj.IDCmd == 2) {
                        fStart = 6;
                     }

                     if (obj.IDCmd == 5) {
                        fStart = 8;
                     }

                     if (this.tickIcon > 10) {
                        ficon = 1;
                     }

                     g.drawRegion(GameScr.imgEvent, 0, fStart * 22 + ficon * 22, 22, 22, 0, this.xDia + 7 + 15, yp + 3, 0, true);
                     mFont.tahoma_7b_white.drawString(g, obj.nameEvent, this.xDia + 35 + 15, yp + 1 + 3, 0, true);
                     mFont.tahoma_7_white.drawString(g, obj.contentEvent, this.xDia + 42 + 15, yp + 13 + 3, 0, true);
                     yp += this.hItem;
                     if (i < vecListEvent.size() - 1) {
                        g.setColor(-4100082);
                        g.fillRect(this.xDia + 5, this.yDia + GameCanvas.hCommand + (i + 1) * this.hItem, this.wDia - 8, 1, true);
                     }
                  }
               }
            }
         } else {
            mFont.tahoma_7_white.drawString(g, T.noEvent, this.xDia + this.wDia / 2, yp + 3, 2, true);
         }

         mGraphics.resetTransAndroid(g);
         g.restoreCanvas();
         GameCanvas.resetTrans(g);
         if (cmdList != null) {
            for(i = 0; i < cmdList.size(); ++i) {
               mCommand cmd = (mCommand)cmdList.elementAt(i);
               cmd.paint(g, cmd.x, cmd.y);
            }
         }

         if ((mSystem.isPC || mSystem.isIP) && vecListEvent.size() > 0) {
            FontTeam.fontTile.drawString(g, T.mevent, this.xDia + this.wDia / 2, this.yDia + 5, 2, false);
         }

         if (GameCanvas.isTouch) {
            this.cmdClose.paint(g);
         }

      }
   }

   public void update() {
      this.tickIcon = (this.tickIcon + 1) % 20;
      if (GameCanvas.currentScreen == this) {
         this.lastScreen.update();
      }

      if (GameCanvas.isKeyPressed(13)) {
         this.cmdClose.performAction();
      }

      if (this.cmdClose != null && GameCanvas.isTouch && this.getCmdPointerLast(this.cmdClose)) {
         GameCanvas.isPointerJustRelease[0] = false;
         this.cmdClose.performAction();
      }

      this.updatePointer();
      int cmylast = this.cameraDia.yCam;
      super.update();
      int size = vecListEvent.size();

      for(int i = 0; i < vecListEvent.size(); ++i) {
         MainEvent obj = (MainEvent)vecListEvent.elementAt(i);
         if (obj.isRemove) {
            vecListEvent.removeElement(obj);
            --i;
         }
      }

      if (this.idSelect < 0 || this.idSelect >= vecListEvent.size()) {
         if (GameCanvas.isTouch) {
            this.idSelect = -1;
         } else {
            this.idSelect = 0;
         }
      }

      if (this.cameraDia.yCam != cmylast || size != vecListEvent.size()) {
         this.min = this.cameraDia.yCam / this.hItem;
         this.max = this.min + this.maxSizeList;
         if (this.max > vecListEvent.size()) {
            this.max = vecListEvent.size();
            this.min = this.max - this.maxSizeList;
         }

         if (this.min < 0) {
            this.min = 0;
         }
      }

   }

   public void updateKey() {
      super.updateKey();
      if (vecListEvent.size() > 0) {
         if (GameCanvas.isKeyPressed(2)) {
            --this.idSelect;
            GameCanvas.clearKeyHold(2);
         } else if (GameCanvas.isKeyPressed(8)) {
            ++this.idSelect;
            GameCanvas.clearKeyHold(8);
         }

         this.idSelect = this.resetSelect(this.idSelect, vecListEvent.size() - 1, true);
         this.cameraDia.moveCamera(0, this.idSelect * this.hItem - (this.hDia / 2 - GameCanvas.hCommand - this.hbutton));
      } else if (cmdList.size() > 1) {
         cmdList.removeAllElements();
         cmdList.addElement(new mCommand(T.close, -1, this));
         this.setPosCmdNew(0);
         this.idSelect = 0;
      }

      if (cmdList != null) {
         int size = cmdList.size();
         if (!GameCanvas.isTouch && size > 0) {
            int t = this.idCommand;
            if (GameCanvas.isKeyPressed(4)) {
               --this.idCommand;
               GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.isKeyPressed(6)) {
               ++this.idCommand;
               GameCanvas.clearKeyHold(6);
            }

            this.idCommand = this.resetSelect(this.idCommand, size - 1, false);
            if (t != this.idCommand) {
               for(int i = 0; i < size; ++i) {
                  mCommand cmd = (mCommand)cmdList.elementAt(i);
                  if (i == this.idCommand) {
                     cmd.isSelect = true;
                  } else {
                     cmd.isSelect = false;
                  }
               }
            }
         }
      }

      if (GameCanvas.isKeyPressed(5) && cmdList != null && this.idCommand < cmdList.size()) {
         this.idSelect = this.resetSelect(this.idSelect, vecListEvent.size() - 1, false);
         boolean isre = false;
         if (GameCanvas.isPointer(this.xDia + this.wDia - 30, this.yDia + GameCanvas.hCommand, 30, this.hDia - GameCanvas.hCommand, 0) && this.idSelect > 0) {
            isre = true;
         }

         MainEvent event = (MainEvent)vecListEvent.elementAt(this.idSelect);
         this.doEvent(isre, event);
      }

   }

   public void updatePointer() {
      ScrollResult r = this.scr.updateKey();
      if (GameCanvas.isPointerClick[0] && this.scr.selectedItem != -1 && !this.scr.isDownWhenRunning) {
         if (this.idSelect != this.scr.selectedItem) {
            this.idSelect = this.scr.selectedItem;
         } else {
            MainEvent event = (MainEvent)vecListEvent.elementAt(this.idSelect);
            this.doEvent(false, event);
         }
      }

      this.scr.updatecm();
      if (cmdList != null) {
         for(int i = 0; i < cmdList.size(); ++i) {
            mCommand cmd = (mCommand)cmdList.elementAt(i);
            if (this.getCmdPointerLast(cmd)) {
               GameCanvas.isPointerJustRelease[0] = false;
               if (cmd != null) {
                  cmd.performAction();
               }
            }
         }
      }

   }

   public void doEvent(boolean isre, MainEvent event) {
      if (isre) {
         event.isRemove = true;
      } else {
         mVector vec = null;
         if (GameScr.numMess > 0) {
            --GameScr.numMess;
         }

         switch(event.IDCmd) {
         case 0:
            MsgChat.setIndexFocus(event.nameEvent);
            GameCanvas.start_Chat_Dialog();
            event.isNew = 0;
            break;
         case 1:
            GameCanvas.startYesNoDlg(event.nameEvent + T.yeucauketban, new mCommand(T.chapnhan, event, 1, "1"));
            event.isNew = 0;
            vecListEvent.removeElement(event);
            break;
         case 2:
            String t = T.hoivaonhom + " " + event.nameEvent + " " + T.khong + " ?";
            SetInfoData infoparty = new SetInfoData();
            infoparty.str = event.nameEvent;
            mCommand cmdyes = new mCommand(T.dongy, this, 1, infoparty);
            mCommand cmdno = new mCommand(T.no, this, 2, infoparty);
            GameCanvas.startYesNoDlg(t, cmdyes, cmdno);
            event.isNew = 0;
            vecListEvent.removeElement(event);
            break;
         case 3:
            vec = new mVector("EventScreen vec4");
            vec.addElement(new mCommand(T.chapnhan, event, 3, 0, 0));
            vec.addElement(new mCommand(T.tuchoi, event, 3, 0, 0));
            vec.addElement(new mCommand(T.close, -1));
            GameCanvas.start_Select_Dialog(event.nameEvent + T.hoigiaodich, vec);
            event.isNew = 0;
            break;
         case 4:
            vec = new mVector("EventScreen vec5");
            vec.addElement(new mCommand(T.chapnhan, event, 4, 1, 0));
            vec.addElement(new mCommand(T.cancel, event, 6, 0, 0));
            GameCanvas.start_Select_Dialog(event.nameEvent + T.hoithachdau + event.numThachDau + " " + T.coin + ".", vec);
            event.isNew = 0;
            vecListEvent.removeElement(event);
            break;
         case 5:
            vec = new mVector("EventScreen vec6");
            vec.addElement(new mCommand(T.chapnhan, event, 5));
            vec.addElement(new mCommand(T.close, event, 7));
            GameCanvas.start_Select_Dialog(event.nameEvent + T.moivaoclan, vec);
            event.isNew = 0;
         case 6:
         case 7:
         default:
            break;
         case 8:
            ShowinfoItem.item = event.item;
            ShowinfoItem.tile = event.tile;
            ShowinfoItem.info = event.der;
            ShowinfoItem.idItem = event.iditem;
            ShowinfoItem.gI().switchToMe(GameCanvas.gameScr);
            event.isNew = 0;
            vecListEvent.removeElement(event);
         }

      }
   }

   public static MainEvent setEvent(String name, byte type) {
      for(int i = 0; i < vecListEvent.size(); ++i) {
         MainEvent obj = (MainEvent)vecListEvent.elementAt(i);
         if (obj.isRemove) {
            vecListEvent.removeElement(obj);
            --i;
         } else if (obj.IDCmd == type && obj.nameEvent.compareTo(name) == 0) {
            return obj;
         }
      }

      return null;
   }

   public static MainEvent setEventShow(String name, byte type) {
      for(int i = 0; i < vecEventShow.size(); ++i) {
         MainEvent obj = (MainEvent)vecEventShow.elementAt(i);
         if (obj.IDCmd == type && obj.nameEvent.compareTo(name) == 0) {
            return obj;
         }
      }

      return null;
   }

   public static int setIndexEvent(String name, byte type) {
      for(int i = 0; i < vecListEvent.size(); ++i) {
         MainEvent obj = (MainEvent)vecListEvent.elementAt(i);
         if (obj.IDCmd == type && obj.nameEvent.compareTo(name) == 0) {
            return i;
         }
      }

      return -1;
   }

   public void addEvent(String namecheck, byte type, String content, int numThachdau) {
      mSound.playSound(29, mSound.volumeSound);
      MainEvent ev = setEvent(namecheck, type);
      if (ev == null) {
         ev = new MainEvent(0, type, namecheck, content);
         vecListEvent.addElement(ev);
         this.updateList();
         this.min = this.cameraDia.yCam / this.hItem;
         this.max = this.min + this.maxSizeList;
         if (this.max > vecListEvent.size()) {
            this.max = vecListEvent.size();
            this.min = this.max - this.maxSizeList;
         }

         if (this.min < 0) {
            this.min = 0;
         }
      } else if (type == 0) {
         ev.contentEvent = content;
      }

      if (namecheck.compareTo(T.tinden) != 0) {
         ++GameScr.numMess;
         ev.isNew = 1;
         ev.numThachDau = numThachdau;
         if (type != 0 || namecheck.compareTo(T.tabBangHoi) != 0 && namecheck.compareTo(T.tabThuLinh) != 0) {
            MainEvent evShow = setEventShow(namecheck, type);
            if (evShow == null) {
               MainEvent evnew = new MainEvent(ev.IDObj, ev.IDCmd, ev.nameEvent, ev.contentEvent);
               vecEventShow.addElement(evnew);
            } else if (type == 0) {
               evShow.contentEvent = ev.contentEvent;
            }
         }
      }

   }

   public void addEvent(String namecheck, byte type, String content, int numThachdau, int iditem, Item item, String tile, String der) {
      MainEvent ev = new MainEvent(0, type, namecheck, content, iditem, item, tile, der);
      vecListEvent.addElement(ev);
      this.updateList();
      this.min = this.cameraDia.yCam / this.hItem;
      this.max = this.min + this.maxSizeList;
      if (this.max > vecListEvent.size()) {
         this.max = vecListEvent.size();
         this.min = this.max - this.maxSizeList;
      }

      if (this.min < 0) {
         this.min = 0;
      }

      vecEventShow.addElement(ev);
      mSound.playSound(29, mSound.volumeSound);
   }

   public void addEvent(String namecheck, byte type, String content, int numThachdau, int idChar) {
      mSound.playSound(29, mSound.volumeSound);
      MainEvent ev = setEvent(namecheck, type);
      if (ev == null) {
         ev = new MainEvent(0, type, namecheck, content);
         ev.idChar = idChar;
         vecListEvent.addElement(ev);
         this.updateList();
         this.min = this.cameraDia.yCam / this.hItem;
         this.max = this.min + this.maxSizeList;
         if (this.max > vecListEvent.size()) {
            this.max = vecListEvent.size();
            this.min = this.max - this.maxSizeList;
         }

         if (this.min < 0) {
            this.min = 0;
         }
      } else if (type == 0) {
         ev.contentEvent = content;
      }

      if (namecheck.compareTo(T.tinden) != 0) {
         ++GameScr.numMess;
         ev.isNew = 1;
         ev.numThachDau = numThachdau;
         ev.idChar = idChar;
         if (type != 0 || namecheck.compareTo(T.tabBangHoi) != 0 && namecheck.compareTo(T.tabThuLinh) != 0) {
            MainEvent evShow = setEventShow(namecheck, type);
            if (evShow == null) {
               MainEvent evnew = new MainEvent(ev.IDObj, ev.IDCmd, ev.nameEvent, ev.contentEvent);
               evnew.idChar = idChar;
               vecEventShow.addElement(evnew);
            } else if (type == 0) {
               evShow.contentEvent = ev.contentEvent;
            }
         }
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         GameScr.numMSG = 0;
         this.lastScreen.switchToMe();
         break;
      case 1:
         SetInfoData s1 = (SetInfoData)p;
         GameService.gI().doCreateParty((byte)2, (short)0, (short)1, s1.str);
         GameCanvas.endDlg();
         break;
      case 2:
         SetInfoData s2 = (SetInfoData)p;
         GameService.gI().doCreateParty((byte)2, (short)0, (short)0, s2.str);
         GameCanvas.endDlg();
         break;
      case 3:
         GameCanvas.endDlg();
      }

   }

   public void paintFormList(mGraphics g, int xKhungAuto, int yKhungAuto, int wKhungAuto, int hKhungAuto, String event) {
      yKhungAuto += 24;
      Res.paintDlgDragonFullNew(g, xKhungAuto, yKhungAuto, wKhungAuto, hKhungAuto, 60, 60, GameScr.imgBk[0], false);
      g.setColor(-9751532);
      g.fillRect(xKhungAuto, yKhungAuto - 28, wKhungAuto, 28, false);

      int i;
      for(i = 0; i < 3; ++i) {
         g.setColor(Res.nColor[i]);
         g.drawRect(xKhungAuto + i, yKhungAuto - 28 + i, wKhungAuto - i * 2, 28 - i * 2, false);
      }

      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, xKhungAuto + wKhungAuto + 1, yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, xKhungAuto, yKhungAuto + 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, xKhungAuto, yKhungAuto - 28, mGraphics.TOP | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, xKhungAuto + wKhungAuto + 1, yKhungAuto - 28, mGraphics.TOP | mGraphics.RIGHT, false);

      for(i = 0; i < 7; ++i) {
         g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, xKhungAuto + wKhungAuto / 2 - 42 + i * 12, yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
      }

      g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, xKhungAuto + wKhungAuto / 2 - 44, yKhungAuto - 14, mGraphics.VCENTER | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, xKhungAuto + wKhungAuto / 2 + 44, yKhungAuto - 14, mGraphics.VCENTER | mGraphics.RIGHT, false);
      FontTeam.fontTile.drawString(g, event, xKhungAuto + wKhungAuto / 2, yKhungAuto - 19, 2, false);
   }

   public int resetSelect(int select, int max, boolean isreset) {
      if (select < 0) {
         if (isreset) {
            select = max;
         } else {
            select = 0;
         }
      } else if (select > max) {
         if (isreset) {
            select = 0;
         } else {
            select = max;
         }
      }

      return select;
   }
}
