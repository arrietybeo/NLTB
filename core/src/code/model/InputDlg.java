package code.model;

import code.main.GameCanvas;
import code.network.GameService;
import code.screen.Res;
import code.screen.screen.Dialog;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;
import lib.mGraphics;
import lib.mSystem;
import lib2.TField;
import lib2.mFont;

public class InputDlg extends Dialog {
   protected String title;
   public TField tfInput = new TField();
   public mCommand okAction;
   public mCommand backAction;
   private int w;
   private int h;
   private int x;
   private int y;
   private int hTouch;
   private int istouchMore;
   private int sizeBack;
   private int size;
   public boolean isMax = false;
   public boolean iscroll;
   public boolean isTexBoxSV;
   short typedialog;
   short IDNPC;
   Scroll scroll = new Scroll();
   public String tileTB;
   public String drec;
   TField[] mTFInput;
   String[] info;
   public mCommand cmdOK;
   public mCommand cmdClose;
   private byte index;

   public InputDlg() {
      this.tfInput.height = 25;
      this.tfInput.y = GameCanvas.h - 76;
      this.tfInput.isFocus = true;
   }

   public void init() {
      this.w = FontTeam.borderFont.getWidth(this.title) + 100;
      if (this.w > GameCanvas.w) {
         String str = "";

         for(int i = 0; i < this.title.length() - 1; ++i) {
            str = str + this.title.substring(i, i + 1);
            if (FontTeam.borderFont.getWidth(str) >= GameCanvas.w - 50) {
               this.title = str + "...";
               this.w = FontTeam.borderFont.getWidth(this.title) + 30;
               break;
            }
         }
      } else if (!this.isMax) {
         this.w = GameCanvas.w - 30;
      }

      if (this.tfInput != null) {
         this.tfInput.y = GameCanvas.h - 76;
         this.tfInput.isFocus = true;
         if (this.w < 110) {
            this.w = 110;
         }

         this.tfInput.width = this.w - 40;
         this.tfInput.x = GameCanvas.hw - this.tfInput.width / 2;
      }

      this.setpostCmd();
   }

   public void setInfo(String title, mCommand ok, int type, int num, boolean isMaxW) {
      this.isMax = isMaxW;
      this.left = null;
      this.tfInput.setText("");
      this.tfInput.setIputType(type);
      this.title = title;
      this.okAction = ok;
      this.okAction.caption = "OK";
      if (!GameCanvas.isTouch) {
         this.left = new mCommand("Đóng", this, 0);
      }

      this.center = this.okAction;
      this.right = this.tfInput.cmdClear;
      this.isTexBoxSV = false;
      this.init();
   }

   public void setInfo(String[] minfo, String cmdname, byte[] typeinput, int num, boolean isMaxW, int typedialog, int idnpc, String tiletb, String drec) {
      this.tfInput = null;
      this.mTFInput = null;
      this.tileTB = tiletb;
      this.typedialog = (short)typedialog;
      this.IDNPC = (short)idnpc;
      this.isMax = isMaxW;
      this.istouchMore = 15;
      if (GameCanvas.isTouch) {
         this.hTouch = mCommand.hButtonCmd + 5;
      }

      this.w = GameCanvas.w - 30;
      if (this.w > 140) {
         this.w = 140;
      }

      this.w = 170;
      this.info = minfo;
      this.mTFInput = new TField[minfo.length];
      if (this.mTFInput.length > 3) {
         this.iscroll = true;
      }

      this.isTexBoxSV = true;
      if (this.iscroll) {
         this.w = GameCanvas.w / 4 * 3;
         this.h = GameCanvas.h / 5 * 4;
         this.w = GameCanvas.w - 30;
         if (this.w > 140) {
            this.w = 140;
         }

         if (this.h < 210) {
            this.h = 210;
         } else if (this.h > 280) {
            this.h = 280;
         }

         if (this.h < 210) {
            this.h = 210;
         } else if (this.h > 280) {
            this.h = 280;
         }

         this.w = 170;
         this.x = GameCanvas.w / 2 - this.w / 2;
         this.y = GameCanvas.h / 2 - this.h / 2;
         if (this.y < 20) {
            this.y = 20;
         }

         if (GameCanvas.isSmallScreen) {
            this.y = 0;
         }
      } else {
         this.h = (TField.getHeight() + (GameCanvas.isTouch ? 18 : 25)) * minfo.length + GameCanvas.hCommand;
         this.h += this.hTouch;
         this.x = GameCanvas.hw - this.w / 2;
         this.y = GameCanvas.h / 2 - this.h / 2 - mCommand.hButtonCmd / 2;
      }

      for(int i = 0; i < this.mTFInput.length; ++i) {
         this.mTFInput[i] = new TField(this.x + 10, this.y + 8 + (TField.getHeight() + 18) * i + this.istouchMore + GameCanvas.hCommand, this.w - 20, ScreenTeam.ITEM_HEIGHT + (GameCanvas.isTouch ? 8 : 2));
         this.mTFInput[i].setText("");
         if (mSystem.isPC || mSystem.isIP) {
            this.mTFInput[i].setStringNull(minfo[i]);
         }

         this.mTFInput[i].setIputType(typeinput[i]);
      }

      this.cmdOK = new mCommand(cmdname, this, 1);
      if (GameCanvas.isTouch) {
         this.cmdOK.setPos(this.x, this.y + this.h + 2);
      } else {
         this.center = this.cmdOK;
      }

      this.cmdClose = new mCommand("", this, 0);
      if (GameCanvas.isTouch) {
         this.cmdClose.caption = T.close;
         this.cmdClose.setPos(this.x + this.w - mCommand.wButtonCmd, this.y + this.h + 2);
      } else {
         this.cmdClose.setXY(0, GameCanvas.h - 20);
      }

      this.index = 0;
      this.sizeBack = mFont.tahoma_7b_white.getWidth(this.tileTB);
      this.size = 50;
      if (!GameCanvas.isTouch) {
         this.size = 40;
         this.left = this.cmdClose;
         this.left.caption = T.close;
         this.mTFInput[this.index].setFocus(true);
         this.right = this.mTFInput[this.index].cmdClear;
      }

      this.drec = drec;
   }

   public void paint(mGraphics g) {
      if (!this.isTexBoxSV) {
         GameCanvas.resetTrans(g);
         Res.paintDlgDragonFullNew(g, GameCanvas.hw - this.w / 2, GameCanvas.h - 110, this.w, 69, 60, 60, GameScr.imgBk[2], true);
         FontTeam.borderFont.drawString(g, this.title, GameCanvas.hw, GameCanvas.h - 100, 2, false);
         this.tfInput.paint(g);
         super.paint(g);
      } else {
         if (mSystem.isPC) {
            g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
         }

         Res.paintDlgDragonFullNew(g, this.x, this.y, this.w, this.h, 60, 60, GameScr.imgBk[0], false);
         Res.paintBackTile(g, this.x + this.w / 2, this.y + 15, this.sizeBack, mFont.tahoma_7b_white, this.tileTB);
         int i;
         if (!this.iscroll) {
            for(i = 0; i < this.mTFInput.length; ++i) {
               this.mTFInput[i].paint(g);
            }
         } else {
            GameCanvas.resetTrans(g);
            this.scroll.setStyle(this.mTFInput.length, this.size, this.x, this.y + 30, this.w, this.h - 60, true, 1);
            this.scroll.setClip(g, this.x, this.y + 30, this.w, this.h - 60);
            GameCanvas.resetTrans(g);
         }

         if (GameCanvas.isTouch) {
            this.cmdOK.paint(g);
            this.cmdClose.paint(g);
         } else {
            super.paint(g);
         }

         for(i = 0; i < this.mTFInput.length; ++i) {
            GameScr.Font3d(g, this.info[i], this.mTFInput[i].x + this.mTFInput[i].width / 2, this.mTFInput[i].y - 12, 2, mFont.tahoma_7b_white, true);
            this.mTFInput[i].paint(g, true);
         }
      }

   }

   public void keyPress(int keyCode) {
      boolean swallow;
      if (!this.isTexBoxSV) {
         swallow = this.tfInput.keyPressed(keyCode);
         if (swallow) {
            GameCanvas.keyPressedz[5] = false;
         }
      } else {
         swallow = this.mTFInput[this.index].keyPressed(keyCode);
         if (swallow) {
            GameCanvas.keyPressedz[5] = false;
         }
      }

   }

   public void update() {
      if (!this.isTexBoxSV) {
         this.tfInput.update();
      } else {
         if (GameCanvas.isTouch) {
            if (this.getCmdPointerLast(this.cmdClose)) {
               GameCanvas.isPointerJustRelease[0] = false;
               if (this.cmdClose != null) {
                  this.cmdClose.performAction();
               }
            }

            if (this.getCmdPointerLast(this.cmdOK)) {
               GameCanvas.isPointerJustRelease[0] = false;
               if (this.cmdOK != null) {
                  this.cmdOK.performAction();
               }
            }
         }

         ScrollResult r = this.scroll.updateKey();
         if (this.scroll.selectedItem != -1 && this.iscroll) {
            this.index = (byte)this.scroll.selectedItem;
            if (GameCanvas.isPointerClick[0] && GameCanvas.isPointer(this.x, this.y + 30, this.w, this.h - 60, 0)) {
               this.mTFInput[this.scroll.selectedItem].updateList();
               GameCanvas.isPointerClick[0] = false;
            }
         }

         if (GameCanvas.isKeyPressed(2)) {
            --this.index;
            if (this.index < 0) {
               this.index = (byte)(this.mTFInput.length - 1);
            }

            this.scroll.moveTo(this.index * this.size);
            this.right = this.mTFInput[this.index].cmdClear;
         }

         if (GameCanvas.isKeyPressed(8)) {
            ++this.index;
            if (this.index > this.mTFInput.length - 1) {
               this.index = 0;
            }

            this.scroll.moveTo(this.index * this.size);
            this.right = this.mTFInput[this.index].cmdClear;
         }

         int i;
         if (this.iscroll || !GameCanvas.isTouch) {
            for(i = 0; i < this.mTFInput.length; ++i) {
               if (i == this.index) {
                  this.mTFInput[i].isFocus = true;
                  this.mTFInput[i].update();
               } else {
                  this.mTFInput[i].isFocus = false;
               }
            }
         }

         if (!this.iscroll && GameCanvas.isTouch) {
            for(i = 0; i < this.mTFInput.length; ++i) {
               this.mTFInput[i].update();
               if (this.mTFInput[i].isFocus) {
                  this.index = (byte)i;
               }
            }
         }

         this.scroll.updatecm();
         if (!GameCanvas.isTouch) {
            super.update();
         }
      }

      if (!this.isTexBoxSV) {
         super.update();
         if (GameCanvas.isTouch && GameCanvas.isPointerClick[0] && !GameCanvas.isPointer(GameCanvas.hw - this.w / 2, GameCanvas.h - 110, this.w - 30, 69, 0)) {
            GameCanvas.endDlg();
            GameCanvas.isPointerClick[0] = false;
         }
      }

   }

   public void updateKey() {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 0:
         GameCanvas.endDlg();
         break;
      case 1:
         String[] infoArr = new String[this.mTFInput.length];

         for(int i = 0; i < infoArr.length; ++i) {
            infoArr[i] = "";
            if (this.mTFInput[i].getText().length() > 0) {
               infoArr[i] = this.mTFInput[i].getText();
            }
         }

         GameService.gI().TexBox(this.IDNPC, this.typedialog, infoArr);
         GameCanvas.endDlg();
      case 2:
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
