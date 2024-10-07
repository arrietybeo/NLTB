package code.screen.screen;

import code.main.GameCanvas;
import code.model.Char;
import code.model.IActionListener;
import code.model.Item;
import code.model.ItemOption;
import code.model.Scroll;
import code.model.ScrollResult;
import code.model.T;
import code.model.mCommand;
import lib.Cout;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class InfoOtherCharScr extends ScreenTeam implements IActionListener {
   public static InfoOtherCharScr me;
   int xInfoWearing;
   int yInfoWearing;
   int wInfoWearing;
   int hInfoWearing;
   int size;
   int x;
   int y;
   int w;
   int h;
   int colum;
   int xbg;
   int sizeH;
   int xShowText;
   int yShowText;
   int wShowText;
   int hShowText;
   public int[] xWearing;
   public int[] yWearing;
   Scroll cmrShowInfoMainChar = new Scroll();
   Scroll cmrShowText = new Scroll();
   int xItemFill;
   int yItemFill;
   int wItemFill;
   int hItemFill;
   int indexShowInfo;
   int timeAuToShowText;
   mVector showText = new mVector();
   String[] tile = new String[2];
   public boolean isShowFill;
   public boolean isShowText;
   public boolean isUseCmr;
   public boolean beGinShowText;
   public boolean isFocusClose;
   public boolean isShowInFoChar;
   public boolean isFocusCharWearing;
   public boolean isFocusDetailMenu;
   public boolean isChangeSubTab;
   public boolean isCharWearing;
   public int[] maptopaintIconTrangBi = new int[]{5, 9, 3, 11, 2, 0, 7, 8, 1, 6, 10, 14, 12, 13, -1};
   public static Char charFocus;
   mCommand cmdShowText;
   mCommand cmdClose;
   mCommand cmdTB1;
   mCommand cmdTB2;
   int indexShadow;
   int numItemStart;
   int speedStart;
   int numItemStart2;
   int laststar2;
   int speedStart2;
   int indexWearing;
   boolean isHalfstart2;
   boolean runStart2;
   boolean isShowSkillInfo;
   boolean isHalfstart;
   boolean runStart;
   int coutFc;
   int disString = 12;
   int countPop;
   int lastSelect = -1;
   public mVector list = new mVector();
   mVector infochar = new mVector();
   int laststar;
   byte totalInfoshow = 0;

   public InfoOtherCharScr() {
      this.init();
      this.cmdTB1 = new mCommand(T.trangbi1, this, 1);
      this.cmdTB2 = new mCommand(T.trangbi2, this, 2);
      this.cmdTB1.setPos(GameCanvas.w / 2 - mCommand.wButtonCmd / 2, this.y + this.h + 4);
      this.cmdTB2.setPos(GameCanvas.w / 2 - mCommand.wButtonCmd / 2, this.y + this.h + 4);
      this.cmdClose = this.cmdTB2;
   }

   public static InfoOtherCharScr gI() {
      return me == null ? (me = new InfoOtherCharScr()) : me;
   }

   public void init() {
      this.size = 26;
      this.colum = 5;
      if (!GameCanvas.isTouch) {
         this.size = 24;
         if (GameCanvas.isScreenSize200) {
            this.size = 22;
         }

         this.w = this.size * this.colum + 12;
         this.x = GameCanvas.w / 2 - this.w / 2;
         if (this.x < 2) {
            this.x = 2;
         }
      } else {
         this.size = 22;
         this.w = this.size * this.colum + 12;
         this.x = GameCanvas.w / 2 - this.w;
      }

      if (this.x < 2) {
         this.x = 2;
      }

      if (GameCanvas.isTouch) {
         this.xShowText = this.x + this.w + 2;
         this.wShowText = this.w;
         if (this.xShowText + this.wShowText > GameCanvas.w - 2) {
            this.wShowText = GameCanvas.w - this.xShowText;
            this.w = GameCanvas.w - this.wShowText - 2;
         }

         --this.wShowText;
      }

      this.h = this.size * (this.colum + 3);
      if (this.h > GameCanvas.h - hTab + 2) {
         this.h = GameCanvas.h - hTab + (GameCanvas.isTouch ? 8 : 0);
      }

      this.y = GameCanvas.h / 2 - this.h / 2 - 12;
      this.xbg = this.x;
      int ybg = this.y + this.h - 2 * this.size - 3;
      this.xWearing = new int[15];
      this.yWearing = new int[15];
      int xbgg = this.xbg - (GameCanvas.isTouch ? 0 : 2);
      this.sizeH = this.size;
      if (GameCanvas.h > 200) {
         this.sizeH = 35;
      }

      if (GameCanvas.isTouch) {
         this.yShowText = this.y + this.sizeH;
         this.hShowText = this.h - this.sizeH + 2;
      }

      this.xWearing[0] = xbgg;
      this.yWearing[0] = ybg - this.size;
      this.xWearing[1] = xbgg + this.size;
      this.yWearing[1] = ybg - this.size;

      for(int i = 0; i < this.xWearing.length; ++i) {
         this.xWearing[i] = xbgg + i % 5 * this.size + 13 + (GameCanvas.isTouch ? 0 : 2) + 2 * (i % 5 + 1);
         this.yWearing[i] = ybg + 1 + i / 5 * this.size - this.size;
      }

      this.xInfoWearing = xbgg + 2 * this.size + 2;
      this.yInfoWearing = this.y + this.sizeH;
      this.wInfoWearing = this.w - 2 * this.size - 4;
      this.hInfoWearing = this.h - this.sizeH - 2 * this.size;
      this.h += 2;
   }

   public void switchToMe() {
      super.switchToMe();
      this.setinfo();
   }

   public void setinfo() {
      this.list.removeAllElements();
      this.setTextCharInfo();
      selected = -1;
      this.isFocusCharWearing = false;
      this.isChangeSubTab = false;
      this.isFocusDetailMenu = false;
      this.isCharWearing = true;
      int count = 0;
      if (charFocus != null && charFocus.equip != null) {
         int size5 = charFocus.equip.length;

         for(int i = 0; i < size5; ++i) {
            Item item = charFocus.equip[i];
            if (item != null) {
               SetInfoData dt45 = new SetInfoData();
               dt45.itemInven1 = item;
               dt45.xx = count;
               this.list.addElement(new mCommand("", this, 45, dt45, dt45));
            }

            ++count;
         }
      }

      this.setInfo(this.list);
      this.left = new mCommand("Chọn", this, 2);
      if (GameCanvas.isTouch) {
         this.left.caption = "";
         this.left.idAction = 1000;
      }

   }

   public void setInfo(mVector list) {
      this.list = list;
      this.setCmdCenter();
   }

   public void switchToMe(ScreenTeam lastSCR) {
      super.switchToMe(lastSCR);
      this.setinfo();
   }

   public void update() {
      if (this.lastSCR != null) {
         this.lastSCR.update();
      }

      this.isFocusClose = false;
      int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
      if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 7 - 33, wc, wc, 0)) {
         this.isFocusClose = true;
         if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
            this.doClose();
            GameCanvas.isPointerClick[0] = false;
         }
      }

      if (GameCanvas.isTouch && this.cmdClose != null && this.getCmdPointerLast(this.cmdClose)) {
         GameCanvas.isPointerJustRelease[0] = false;
         if (this.cmdClose != null) {
            this.cmdClose.performAction();
         }
      }

      if (charFocus != null) {
         charFocus.updateCharFrame();
      }

      if (this.list.size() == 0 && GameCanvas.gameTick % 33 == 0) {
         int count = 0;
         if (charFocus != null && charFocus.equip != null) {
            int size5 = charFocus.equip.length;

            for(int i = 0; i < size5; ++i) {
               Item item = charFocus.equip[i];
               if (item != null) {
                  SetInfoData dt45 = new SetInfoData();
                  dt45.itemInven1 = item;
                  dt45.xx = count;
                  this.list.addElement(new mCommand("", this, 45, dt45, dt45));
               }

               ++count;
            }
         }

         this.setInfo(this.list);
      }

      if (GameCanvas.gameTick % 3 == 0) {
         ++this.coutFc;
         if (this.coutFc >= 2) {
            this.coutFc = 0;
         }
      }

      if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) {
         this.runStart = true;
      }

      if (this.runStart && GameCanvas.gameTick % 2 == 0) {
         ++this.speedStart;
         if (this.speedStart > this.numItemStart + 2) {
            this.speedStart = 0;
            this.runStart = false;
         }
      }

      if (GameCanvas.gameTick % 80 == 0 && this.laststar2 > 1) {
         this.runStart2 = true;
      }

      if (this.runStart2 && GameCanvas.gameTick % 2 == 0) {
         ++this.speedStart2;
         if (this.speedStart2 > this.numItemStart2 + 2) {
            this.speedStart2 = 0;
            this.runStart2 = false;
         }
      }

      if (!GameCanvas.isTouch && GameCanvas.isKeyPressed(13)) {
         GameCanvas.keyPressedz[13] = false;
         this.right.performAction();
      } else {
         if (GameCanvas.isKeyPressed(13) || GameCanvas.isKeyPressed(5)) {
            this.cmdClose.performAction();
         }

         super.update();
      }
   }

   public void updateTouch() {
      if (GameCanvas.currentDialog == null) {
         int hh = 21;

         for(int i = 0; i < this.xWearing.length; ++i) {
            if (GameCanvas.isPointer(this.xWearing[i] - this.size / 2, this.yWearing[i], hh, hh, 0) && GameCanvas.canTouch()) {
               selected = i;
               this.isFocusCharWearing = false;
               if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && this.lastSelect != selected) {
                  int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                  int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                  Item sItem1 = charFocus.equip[selected + this.indexWearing];
                  if (sItem1 != null) {
                     this.showItemInventoryInfo(sItem1, false, x01, y01);
                  }

                  this.lastSelect = selected;
               }
               break;
            }
         }

      }
   }

   private void doClose() {
      charFocus = null;
      this.closeText();
      this.lastSCR.switchToMe();
   }

   public void updateKey() {
      if (GameCanvas.currentDialog == null) {
         if (GameCanvas.isTouch) {
            this.updateTouch();
         }

         ScrollResult r;
         if (this.isShowInFoChar && (!GameCanvas.isTouch || !this.isShowFill) && this.cmrShowInfoMainChar != null) {
            r = this.cmrShowInfoMainChar.updateKey();
            this.cmrShowInfoMainChar.updatecm();
         }

         if (this.cmrShowText != null) {
            r = this.cmrShowText.updateKey();
            this.cmrShowText.updatecm();
         }

         if (!this.isFocusDetailMenu) {
            this.timeAuToShowText = 0;
         }

         --this.timeAuToShowText;
         if (this.timeAuToShowText <= 0) {
            this.timeAuToShowText = 0;
         }

         this.setAutoActionCmd(this.cmdShowText);
         if (!this.isShowFill && !GameCanvas.keyPressedz[6] && !GameCanvas.keyPressedz[4] && !GameCanvas.keyPressedz[8]) {
            boolean var10000 = GameCanvas.keyPressedz[2];
         }

         if (!GameCanvas.isTouch && this.isShowInFoChar) {
            this.cmrShowInfoMainChar.moveTo(this.indexShowInfo * 12);
         }

         super.updateKey();
      }
   }

   public void paint(mGraphics g) {
      if (this.lastSCR != null) {
         this.lastSCR.paint(g);
      }

      GameCanvas.resetTrans(g);
      g.setClip(this.x, this.y + 3, this.w + this.wShowText + 3, this.h - 3);
      g.ClipRec(this.x, this.y + 3, this.w + this.wShowText + 3, this.h - 3);

      int wwz;
      int www;
      for(wwz = 0; wwz < (this.w + this.wShowText) / 32 + 1; ++wwz) {
         for(www = 0; www < this.h / 32 + 1; ++www) {
            g.drawImage(GameScr.imgBgMainMenu, this.x + wwz * 32, this.y + www * 32 - 5, 0, true);
         }
      }

      wwz = (GameCanvas.isTouch ? this.xShowText + this.wShowText - this.x : this.w) - 2;
      if (GameCanvas.w > 200) {
         g.setColor(-11262464);
         this.paintBgSub(g, this.x, this.y + this.sizeH, wwz + 2, this.h - this.sizeH - 1, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + wwz + 3, this.y + this.h, mGraphics.BOTTOM | mGraphics.RIGHT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x, this.y + this.h, mGraphics.BOTTOM | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + wwz + 3, this.y + this.sizeH, mGraphics.TOP | mGraphics.RIGHT, false);
      }

      g.restoreCanvas();
      GameCanvas.resetTrans(g);
      www = (GameCanvas.isTouch ? this.xShowText + this.wShowText - 1 : this.w + this.x) - this.x + 2;
      if (!GameCanvas.isTouch) {
         www = this.w;
      }

      g.setColor(-9751532);
      g.drawRect(this.x, this.y + 2, www - 1, this.sizeH - 5, false);
      g.setColor(-4034800);
      g.drawRect(this.x + 1, this.y + 2 + 1, www - 3, this.sizeH - 7, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + www, this.y + this.sizeH - 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x, this.y + this.sizeH - 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x, this.y + 2, mGraphics.TOP | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + www, this.y + 2, mGraphics.TOP | mGraphics.RIGHT, false);

      for(int i = 0; i < 7; ++i) {
         g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, GameCanvas.hw - 42 + i * 12, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
      }

      g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, GameCanvas.hw - 44, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
      int ys = 0;
      if (!mSystem.isj2me) {
         ys = -1;
      }

      g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, GameCanvas.hw + 44, this.y + this.sizeH / 2 + 1 + ys, mGraphics.VCENTER | mGraphics.RIGHT, false);
      FontTeam.fontTile.drawString(g, this.tile[0], GameCanvas.w / 2, this.y + this.sizeH / 2 - 6, 2, false);
      this.paintCharWearing(g, true);
      this.paintShowText(g);
      if (this.cmdClose != null) {
         this.cmdClose.paint(g);
      }

      g.fillRect(0, 0, 0, 0, false);
      int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
      int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
      if (GameCanvas.isTouch) {
         g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * hc, wc, hc, 0, this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 7 - 33, 0, false);
      }

      super.paint(g);
   }

   public void paintBgSub(mGraphics g, int x, int y, int w, int h, boolean isBoder) {
      g.setColor(-9751532);
      g.drawRect(x, y, w, h, false);
      g.drawRect(x + 2, y + 2, w - 4, h - 4, false);
      if (isBoder) {
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, x + w, y + h, mGraphics.BOTTOM | mGraphics.RIGHT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, x, y + h, mGraphics.BOTTOM | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, x, y, mGraphics.TOP | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, x + w, y, mGraphics.TOP | mGraphics.RIGHT, false);
      }

      g.setColor(-4034289);
      g.drawRect(x + 1, y + 1, w - 2, h - 2, false);
   }

   private void setTextCharInfo() {
      String[] Temp = new String[50];
      this.tile = new String[2];
      this.tile[0] = charFocus.name;
      this.infochar.addElement(new InfoTextShow(new String[]{charFocus.name}, 0));
      Cout.println("charFocus " + charFocus);
      this.tile[1] = "Lv " + charFocus.level;
      this.infochar.addElement(new InfoTextShow(new String[]{"Lv: " + charFocus.level}, 0));
      Temp[0] = "HP: " + charFocus.hp + "/" + charFocus.maxhp;
      Temp[1] = "MP: " + charFocus.mp + "/" + charFocus.maxmp;
      this.infochar.addElement(new InfoTextShow(new String[]{"HP: " + charFocus.hp + "/" + charFocus.maxhp}, 0));
      this.infochar.addElement(new InfoTextShow(new String[]{"MP: " + charFocus.mp + "/" + charFocus.maxmp}, 0));
      if (GameScr.mainChar != null) {
         for(int i = 0; i < GameScr.mainChar.options.size(); ++i) {
            ItemOption itop = (ItemOption)GameScr.mainChar.options.elementAt(i);
            mVector m = itop.getInfoTextShow(0);

            for(int j = 0; j < m.size(); ++j) {
               this.infochar.addElement(m.elementAt(j));
            }
         }
      }

   }

   public void paintFocus(mGraphics g, int x, int y, int idimage, boolean isSetclip) {
      g.drawImage(GameScr.imgfocus[idimage], x, y, 3, isSetclip);
   }

   private void paintCharWearing(mGraphics g, boolean isChar) {
      int xShowText1 = this.xInfoWearing + 12;
      int wShowText1 = this.wInfoWearing + 2 - 12;
      int yShowText1 = this.yInfoWearing + 2;
      int hShowText1 = this.hInfoWearing - 4 - this.size;
      int sizee = 2;
      g.setColor(-13232632);
      g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
      g.setColor(-1596632);
      g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
      g.setColor(-13232632);
      g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
      g.setColor(-14864849);
      g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
      g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
      g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
      g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
      if (GameCanvas.w > 200) {
         g.setColor(-9751532);
         g.fillRect(this.xbg + 3 + 2, yShowText1 + 3, sizee * this.size + 9, hShowText1 - 6, false);
         g.fillRect(this.xbg + 3, yShowText1, sizee * this.size - 3, hShowText1, false);
         g.setColor(-4891370);
         g.drawLine(this.xbg + 6 + 2, yShowText1 + 3 + hShowText1 - 6 + 1, this.xbg + sizee * this.size - 3, yShowText1 + 3 + hShowText1 - 6 + 1, false);
         g.setColor(-12246258);
         g.fillRect(this.xbg + 3 + 2, yShowText1 + 3, sizee * this.size + 6, hShowText1 - 6, false);
         g.setColor(-110);
         g.fillRect(this.xbg + 3 + 2, yShowText1 + 1, sizee * this.size + 6, 1, false);
         g.setColor(-4034289);
         g.fillRect(this.xbg + 1 + 2, yShowText1 + 16, 1, hShowText1 - 18, false);
         g.fillRect(this.xbg - 2 + sizee * this.size + 12 + 2, yShowText1 + 16, 1, hShowText1 - 18, false);
         g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, this.xbg + 2, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, this.xbg + 2 + sizee * this.size + 12, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
         int ybg = yShowText1 + hShowText1;
         g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, this.xbg + 2, ybg, mGraphics.BOTTOM | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, this.xbg + 2 + sizee * this.size + 12, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
      }

      g.drawImage(GameScr.imglv, this.xbg + 6 * this.size / 2 - 4, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) - 30, 0, false);
      if (charFocus != null) {
         g.drawRegion(GameScr.imghpmp, 0, 0, mGraphics.getImageWidth(GameScr.imghpmp), mGraphics.getImageHeight(GameScr.imghpmp) / 3, 0, this.xbg + 6 * this.size / 2 - 4, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) - 30 - 3 + 12, 0, false);
         FontTeam.numberSmall_white.drawString(g, String.valueOf(charFocus.getLevel()), this.xbg + 6 * this.size / 2 + 6, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) - 30 - 1, 0, false);
         FontTeam.numberSmall_white.drawString(g, GameScr.getTextPaintHP(charFocus.getMaxHp()), this.xbg + 6 * this.size / 2 + 6, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) - 30 - 1 + 12, 0, false);
         g.drawRegion(GameScr.imghpmp, 0, mGraphics.getImageHeight(GameScr.imghpmp) / 3, mGraphics.getImageWidth(GameScr.imghpmp), mGraphics.getImageHeight(GameScr.imghpmp) / 3, 0, this.xbg + 6 * this.size / 2 - 4, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) - 30 - 4 + 24, 0, false);
         FontTeam.numberSmall_white.drawString(g, GameScr.getTextPaintHP(charFocus.getMaxMp()), this.xbg + 6 * this.size / 2 + 6, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) - 30 - 1 + 24, 0, false);
      }

      if (isChar && charFocus != null) {
         charFocus.paint(g, this.xbg + sizee * this.size / 2 - 1 + 6, this.y + this.h - 2 * this.size - (GameCanvas.isSmallScreen ? 24 : 38), 0);
      } else if (charFocus != null) {
         charFocus.paint(g, this.xbg + sizee * this.size / 2 - 1 + 6, this.y + this.h - 3 * this.size - (GameCanvas.isSmallScreen ? 24 : 38) + this.size, 0);
      }

      int i;
      for(i = 0; i < this.xWearing.length; ++i) {
         g.drawImage(GameScr.imgBackItem, this.xWearing[i], this.yWearing[i] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

      if (isChar && charFocus != null) {
         if (this.indexWearing <= 0) {
            for(i = 0; i < charFocus.wearing.length; ++i) {
               if (charFocus == null || charFocus.equip == null || this.maptopaintIconTrangBi[i] >= charFocus.equip.length || charFocus.equip[this.maptopaintIconTrangBi[i]] == null) {
                  if (GameScr.imgW != null && i < 12) {
                     g.drawRegion(GameScr.imgW, 0, (i + 1) * 17, 17, 17, 0, this.xWearing[this.maptopaintIconTrangBi[i]], this.yWearing[this.maptopaintIconTrangBi[i]] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                  } else if (this.maptopaintIconTrangBi[i] != -1) {
                     g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xWearing[this.maptopaintIconTrangBi[i]], this.yWearing[this.maptopaintIconTrangBi[i]] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                  }
               }

               if (i == 2 && GameScr.imgW != null && i < 12) {
                  g.drawRegion(GameScr.imgW, 0, (i + 1) * 17, 17, 17, 0, this.xWearing[4], this.yWearing[4] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
               }
            }
         }

         for(i = 0; i < this.xWearing.length; ++i) {
            if (charFocus != null && charFocus.equip != null && i + this.indexWearing < charFocus.equip.length && charFocus.equip[i + this.indexWearing] != null) {
               charFocus.equip[i + this.indexWearing].paintIconWearing(g, this.xWearing[i], this.yWearing[i] + this.size / 2);
            }
         }
      }

      if (selected > -1 && selected < this.xWearing.length && (!this.isShowInFoChar && !this.isShowFill || GameCanvas.isTouch && !this.isShowFill)) {
         this.paintFocus(g, this.xWearing[selected] - this.size / 2 + this.size / 2, this.yWearing[selected] + this.size / 2, 0, true);
      }

   }

   public void paintFocus(mGraphics g, int x, int y, int w, int h) {
      g.drawImage(GameScr.imgBoder[2], x - 2 + this.coutFc, y - 2 + this.coutFc, 0, false);
      g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 2, x + w + 3 - this.coutFc, y - 2 + this.coutFc, mGraphics.TOP | mGraphics.RIGHT, false);
      g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 7, x + w + 3 - this.coutFc, y + h + 3 - this.coutFc, mGraphics.BOTTOM | mGraphics.RIGHT, false);
      g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 6, x - 2 + this.coutFc, y + h + 3 - this.coutFc, mGraphics.BOTTOM | mGraphics.LEFT, false);
   }

   public static boolean isDegit(char c) {
      return c >= '0' && c <= '9';
   }

   public void closeText() {
      this.cmrShowText.clear();
      this.isShowText = false;
      if (!GameCanvas.isTouch) {
         this.wShowText = this.hShowText = 0;
         this.xShowText = this.xbg;
      }

      this.isUseCmr = false;
   }

   private void setShowText(mVector text, int x0, int y0, String[] arr, boolean isTile) {
      if (text != null || arr != null) {
         this.closeText();
         this.countPop = 0;
         this.numItemStart = 0;
         this.numItemStart2 = 0;
         this.isShowText = true;
         this.isHalfstart = false;
         if (!GameCanvas.isTouch) {
            this.xShowText = x0 + this.size / 2;
            this.yShowText = y0 + this.size / 2;
         } else {
            this.yShowText = this.y + this.sizeH;
            this.hShowText = this.h - this.sizeH;
         }

         if (!GameCanvas.isTouch) {
            this.wShowText = 6 * this.size;
         }

         this.totalInfoshow = 0;
         int hh;
         if (arr == null) {
            String[] data = null;
            hh = 0;
            this.showText = text;

            for(int i = 0; i < this.showText.size(); ++i) {
               InfoTextShow info = (InfoTextShow)text.elementAt(i);
               if (info.info == null) {
                  ++this.totalInfoshow;
               } else {
                  if (isTile && i == 0) {
                     data = mFont.tahoma_7b_black.splitFontArray(info.info[0], this.wShowText - 20);
                     info.setInfo(data, Item.getColorPaintName(info.color));
                  } else {
                     data = mFont.tahoma_7_white.splitFontArray(info.info[0], this.wShowText - 20);
                     info.setInfo(data, Item.getColorPaintOption(info.color));
                  }

                  this.totalInfoshow = (byte)(this.totalInfoshow + info.info.length);
                  int ww = info.getMaxWidth();
                  hh = hh > ww ? hh : ww;
               }
            }

            if (!GameCanvas.isTouch) {
               this.wShowText = hh;
            }
         }

         boolean isitemPhiPhong = false;
         if (!GameCanvas.isTouch) {
            this.hShowText = this.totalInfoshow * this.disString + 6;
            if (this.hShowText > 140) {
               this.hShowText = 140;
            }

            if (this.xShowText + this.wShowText > GameCanvas.w) {
               hh = this.xShowText + this.wShowText - GameCanvas.w;
               this.xShowText -= hh + 4;
            }

            if (this.xShowText < 4) {
               this.xShowText = 4;
            }

            if (this.yShowText + this.hShowText > GameCanvas.h - hTab) {
               hh = this.yShowText + this.hShowText - (GameCanvas.h - hTab);
               this.yShowText -= hh;
            }

            if (this.yShowText < 4) {
               this.yShowText = 4;
            }

            if (this.hShowText < this.totalInfoshow * this.disString + 6) {
               this.isUseCmr = true;
            }
         }

         if (selected > -1 && selected < charFocus.equip.length && charFocus.equip[selected + this.indexWearing] != null) {
            this.numItemStart = charFocus.equip[selected + this.indexWearing].plus;
            if (charFocus.equip[selected + this.indexWearing].getTypeItem() == 55) {
               isitemPhiPhong = true;
            }
         }

         if (isitemPhiPhong) {
            if (this.numItemStart > 16) {
               this.numItemStart2 = this.numItemStart - 16;
               this.numItemStart = 16;
            }

            this.laststar2 = this.numItemStart2;
            if (this.numItemStart2 % 2 != 0) {
               this.isHalfstart2 = true;
            }

            this.numItemStart2 /= 2;
            if (this.laststar2 == 1) {
               this.numItemStart2 = this.laststar2;
            }
         }

         if (this.numItemStart >= 15 && !isitemPhiPhong) {
            this.numItemStart = 16;
         }

         this.laststar = 0;
         this.laststar = this.numItemStart;
         if (this.numItemStart % 2 != 0) {
            this.isHalfstart = true;
         }

         this.numItemStart /= 2;
         if (this.laststar == 1) {
            this.numItemStart = 1;
         }

      }
   }

   public void paintShowText(mGraphics g) {
      GameCanvas.resetTrans(g);
      boolean isAlway = GameCanvas.isTouch;
      if (this.isShowText) {
         isAlway = true;
      }

      if (isAlway) {
         g.setColor(-9751532);
         g.fillRect(this.xShowText, this.yShowText, this.wShowText, this.hShowText, false);
         g.setColor(-4034289);
         g.fillRect(this.xShowText + 1, this.yShowText + 1, this.wShowText - 2, this.hShowText - 2, false);
         g.setColor(-9751532);
         g.fillRect(this.xShowText + 2, this.yShowText + 2, this.wShowText - 4, this.hShowText - 4, false);
         g.setColor(-16114410);
         g.fillRect(this.xShowText + 3, this.yShowText + 3, this.wShowText - 6, this.hShowText - 6, false);
         if (GameCanvas.isTouch) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xShowText + this.wShowText, this.yShowText + this.hShowText, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xShowText, this.yShowText + this.hShowText, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xShowText, this.yShowText, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xShowText + this.wShowText, this.yShowText, mGraphics.TOP | mGraphics.RIGHT, false);
         }
      }

      int yy = 0;
      int yKham = 0;
      int hCmdHanhTrang = 0;
      this.cmrShowText.setStyle(this.totalInfoshow + 2, this.disString, this.xShowText, this.yShowText, this.wShowText, this.hShowText - 8 - yKham - (GameCanvas.isTouch ? hCmdHanhTrang : 0), true, 0);
      g.ClipRec(this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width, this.cmrShowText.height + 4 + yKham);
      this.cmrShowText.setClip(g, this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width, this.cmrShowText.height + 4 + yKham);

      for(int i = 0; i < this.showText.size(); ++i) {
         InfoTextShow info = (InfoTextShow)this.showText.elementAt(i);
         if (info.info != null) {
            mFont f = info.f;
            int k;
            if (i == 1 && (this.numItemStart > 0 || this.numItemStart2 > 0)) {
               yy += this.disString;
               if (this.laststar > 1) {
                  for(k = 0; k < this.numItemStart; ++k) {
                     g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11, this.yShowText + yy + 3, 3, true);
                  }
               }

               if (this.isHalfstart) {
                  g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShowText + 15 + (this.laststar > 1 ? this.numItemStart * 11 : 0), this.yShowText + yy + 3, 3, true);
               }

               if (this.runStart) {
                  if (this.speedStart < this.numItemStart) {
                     g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0, this.xShowText + 15 + this.speedStart * 11, this.yShowText + yy + 3, 3, true);
                  }

                  if (this.speedStart >= 1 && this.speedStart < this.numItemStart + 1) {
                     g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0, this.xShowText + 15 + (this.speedStart - 1) * 11, this.yShowText + yy + 3, 3, true);
                  }

                  if (this.speedStart >= 2 && this.speedStart < this.numItemStart + 2) {
                     g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0, this.xShowText + 15 + (this.speedStart - 2) * 11, this.yShowText + yy + 3, 3, true);
                  }
               }

               if (this.numItemStart2 > 0) {
                  yy += this.disString;
                  if (this.laststar2 > 1) {
                     for(k = 0; k < this.numItemStart2; ++k) {
                        g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11, this.yShowText + yy + 3, 3, true);
                     }
                  }

                  if (this.isHalfstart2) {
                     g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShowText + 15 + (this.laststar2 > 1 ? this.numItemStart2 * 11 : 0), this.yShowText + yy + 3, 3, true);
                  }

                  if (this.runStart2) {
                     if (this.speedStart2 < this.numItemStart2) {
                        g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0, this.xShowText + 15 + this.speedStart2 * 11, this.yShowText + yy + 3, 3, true);
                     }

                     if (this.speedStart2 >= 1 && this.speedStart2 < this.numItemStart2 + 1) {
                        g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0, this.xShowText + 15 + (this.speedStart2 - 1) * 11, this.yShowText + yy + 3, 3, true);
                     }

                     if (this.speedStart2 >= 2 && this.speedStart2 < this.numItemStart2 + 2) {
                        g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0, this.xShowText + 15 + (this.speedStart2 - 2) * 11, this.yShowText + yy + 3, 3, true);
                     }
                  }
               }
            }

            if (info.info != null) {
               for(k = 0; k < info.info.length; ++k) {
                  f.drawString(g, info.info[k], this.xShowText + 10, this.yShowText + 10 + yy, 0, true);
                  yy += this.disString;
               }
            }
         }
      }

      mGraphics.resetTransAndroid(g);
      g.restoreCanvas();
      GameCanvas.resetTrans(g);
      if (this.isUseCmr) {
         g.drawImage(GameScr.imgSo[2], this.xShowText + this.wShowText - 3, this.yShowText + this.hShowText - 13, mGraphics.TOP | mGraphics.RIGHT, false);
         g.drawRegion(GameScr.imgSo[2], 0, 0, 7, 5, 3, this.xShowText + this.wShowText - 3, this.yShowText + 5, mGraphics.TOP | mGraphics.RIGHT, false);
      }

   }

   public void setAutoActionCmd(mCommand cmd) {
      if (this.timeAuToShowText == 1 && !this.beGinShowText && cmd != null) {
         cmd.performAction();
         this.beGinShowText = true;
      }

   }

   public void setCmdCenter() {
      this.cmdShowText = new mCommand("", GameCanvas.instance, -100);
      this.closeText();
      if (!this.isFocusCharWearing) {
         for(int i = 0; i < this.list.size(); ++i) {
            mCommand cmd = (mCommand)this.list.elementAt(i);
            if (cmd != null && cmd.p != null && ((SetInfoData)cmd.p).xx == selected) {
               this.cmdShowText = cmd;
            }
         }
      }

      this.timeAuToShowText = 15;
      this.beGinShowText = false;
   }

   public void setCmdShowText() {
      this.cmdShowText = new mCommand("", this, -2);
      this.timeAuToShowText = 15;
      this.beGinShowText = false;
   }

   private void showItemInventoryInfo(Item item, boolean isSell, int x, int y) {
      this.setShowText(item.getInfoItemShow((Item)null, false), x, y, (String[])null, true);
      this.isShowSkillInfo = true;
   }

   public void perform(int idAction, Object p) {
      int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
      int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
      switch(idAction) {
      case 0:
         this.doClose();
         break;
      case 1:
         this.closeText();
         this.indexWearing = 0;
         this.cmdClose = this.cmdTB2;
         break;
      case 2:
         this.closeText();
         this.indexWearing = 15;
         this.cmdClose = this.cmdTB1;
         break;
      case 45:
         if (p != null) {
            SetInfoData dt45 = (SetInfoData)p;
            this.showItemInventoryInfo(dt45.itemInven1, false, x0, y0);
         }
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
