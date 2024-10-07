package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.MsgInfo;
import code.model.mCommand;
import code.screen.Res;
import lib.mGraphics;
import lib.mVector;

public class MsgWorld extends ScreenTeam implements IActionListener {
   public static MsgWorld me;
   public int cmtoY;
   public int cmy;
   public int cmdy;
   public int cmvy;
   public int cmyLim;
   public int selected;
   public int limSelect;
   public mVector data = new mVector();
   boolean trans;
   int pa;

   public static MsgWorld gI() {
      return me == null ? (me = new MsgWorld()) : me;
   }

   public void switchToMe() {
      super.switchToMe();
      this.init();
   }

   public void init() {
      this.data = new mVector();

      for(int i = 0; i < GameScr.savemsgWorld.size(); ++i) {
         MsgInfo ms = (MsgInfo)GameScr.savemsgWorld.elementAt(i);
         ms.arr = FontTeam.arialFontW.splitFontBStrInLine(ms.message, GameCanvas.w - 44);
         this.data.addElement(ms);
         this.limSelect += ms.arr.length;
      }

      this.cmyLim = this.limSelect * 15 - (GameCanvas.h - 60) + 35;
      if (this.cmyLim < 0) {
         this.cmyLim = 0;
      }

      this.cmy = this.cmtoY = 0;
   }

   public MsgWorld() {
      this.right = new mCommand("Đóng", this, 1);
   }

   public void paint(mGraphics g) {
      GameCanvas.gameScr.paint(g);
      Res.paintDlgFull(g, 20, 20, GameCanvas.w - 40, GameCanvas.h - 60);
      FontTeam.borderFont.drawString(g, "Kênh thế giới", GameCanvas.w / 2, 26, 2, false);
      g.setClip(20, 46, GameCanvas.w - 40, GameCanvas.h - 90);
      g.ClipRec(20, 46, GameCanvas.w - 40, GameCanvas.h - 90);
      g.translate(20, 47);
      g.translate(0, -this.cmy);
      if (this.data.size() > 0) {
         Res.paintSelected(g, 4, this.selected * 15 + 2, GameCanvas.w - 49, 15);
      }

      int yy = 2;

      for(int i = 0; i < this.data.size(); ++i) {
         MsgInfo ms = (MsgInfo)this.data.elementAt(i);

         for(int j = 0; j < ms.arr.length; ++j) {
            FontTeam.arialFontW.drawString(g, ms.arr[j], 8, yy, 0, false);
            yy += 15;
         }
      }

      g.restoreCanvas();
      GameCanvas.resetTrans(g);
      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      super.paint(g);
   }

   public void updateKey() {
      boolean isChange = false;
      if (GameCanvas.isPointer(20, 20, GameCanvas.w - 40, GameCanvas.h - 60, 0)) {
         if (GameCanvas.isPointerDown[0]) {
            if (!this.trans) {
               this.pa = this.cmy;
               this.trans = true;
            }

            if (Math.abs(GameCanvas.pyLast[0] - GameCanvas.py[0]) != 0) {
               this.cmtoY = this.pa + (GameCanvas.pyLast[0] - GameCanvas.py[0]);
               if (this.cmtoY < 0) {
                  this.cmtoY = 0;
               }

               if (this.cmtoY > this.cmyLim) {
                  this.cmtoY = this.cmyLim;
               }
            }
         }

         if (GameCanvas.isPointerClick[0]) {
            GameCanvas.isPointerClick[0] = false;
            this.selected = (this.cmtoY + GameCanvas.py[0] - 47) / 15;
            if (this.selected > this.limSelect - 1) {
               this.selected = this.limSelect - 1;
            }

            if (this.selected < 0) {
               this.selected = 0;
            }

            this.trans = false;
         }
      }

      if (GameCanvas.keyPressedz[8]) {
         GameCanvas.keyPressedz[8] = false;
         ++this.selected;
         if (this.selected > this.limSelect - 1) {
            this.selected = this.limSelect - 1;
         }

         isChange = true;
      } else if (GameCanvas.keyPressedz[2]) {
         GameCanvas.keyPressedz[2] = false;
         --this.selected;
         if (this.selected < 0) {
            this.selected = 0;
         }

         isChange = true;
      }

      if (this.cmy != this.cmtoY) {
         this.cmvy = this.cmtoY - this.cmy << 2;
         this.cmdy += this.cmvy;
         this.cmy += this.cmdy >> 4;
         this.cmdy &= 15;
         if (this.cmy < 0) {
            this.cmy = 0;
         }

         if (this.cmy > this.cmyLim) {
            this.cmy = this.cmyLim;
         }
      }

      if (isChange) {
         this.cmtoY = this.selected * 15 - (GameCanvas.h - 60) / 5;
      }

      super.updateKey();
   }

   public void update() {
      GameCanvas.gameScr.update();
      super.update();
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 1:
         GameCanvas.gameScr.switchToMe();
         this.selected = this.limSelect = 0;
      default:
      }
   }
}
