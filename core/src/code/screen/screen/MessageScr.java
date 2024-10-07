package code.screen.screen;

import code.main.GameCanvas;
import code.model.ChatTab;
import code.model.IActionListener;
import code.model.mCommand;
import code.network.GameService;
import code.screen.Res;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.TField;

public class MessageScr extends ScreenTeam implements IActionListener {
   public static MessageScr me;
   protected String[] info;
   private mVector list;
   private int focusTab = 1;
   private ScreenTeam lastScr;
   private int wTab = 60;
   private int hTab = 20;
   private int du = 10;
   private int w;
   private int h;
   private int x;
   private int y;
   public static int cmtoY;
   public static int cmy;
   public static int cmdy;
   public static int cmvy;
   public static int cmyLim;
   public static int selected = 0;
   public static int lastSe;
   public static int limit;
   public static int chatDelay;
   private ChatTab focusT;
   private ChatTab publicTab;
   private TField tfInput;
   public static byte nMSG = 0;
   int a = 0;

   public static MessageScr gI() {
      return me == null ? (me = new MessageScr()) : me;
   }

   public void switchToMe() {
      this.lastScr = GameCanvas.currentScreen;
      this.changTab();
      nMSG = 0;
      chatDelay = (int)mSystem.currentTimeMillis() / 100;
      super.switchToMe();
      this.init();
   }

   public MessageScr() {
      this.left = new mCommand("Menu", this, 1);
      this.tfInput = new TField();
      this.init();
      this.tfInput.isFocus = true;
      this.list = new mVector();
      this.publicTab = new ChatTab("Tin đến", (mCommand)null, (mCommand)null, false);
      this.focusTab = 0;
      this.publicTab.text = new mVector();
      this.list.addElement(this.publicTab);
      this.changTab();
      this.setWTab();
   }

   public void init() {
      this.w = GameCanvas.w - 48;
      this.h = GameCanvas.h - 64;
      this.x = this.y = 24;
      this.tfInput.x = this.x + 4;
      this.tfInput.y = this.y + this.h - 25;
      this.tfInput.height = 20;
      this.tfInput.width = this.w - 8;
      this.setWTab();
   }

   public void setWTab() {
      if (this.list != null && this.focusT != null) {
         this.du = 20;
         this.wTab = this.w - this.du * this.list.size() + this.du - 1;
         if (this.wTab < FontTeam.borderFont.getWidth(this.focusT.name) + 15) {
            this.wTab = FontTeam.borderFont.getWidth(this.focusT.name) + 15;
            this.du = (this.w - this.wTab) / (this.list.size() - 1);
         }

         if (this.list.size() == 1) {
            this.wTab = this.w - 1;
         }
      }

   }

   public void setFocusTab(String name) {
      for(int i = 0; i < this.list.size(); ++i) {
         ChatTab tab = (ChatTab)this.list.elementAt(i);
         if (tab.name.equals(name)) {
            tab.isOpen = false;
            this.focusT = tab;
            this.focusTab = i;
         }
      }

   }

   public void addTab(String te, String name, int type) {
      ChatTab tab = null;
      if (name != null && !name.equals(this.publicTab.name)) {
         tab = this.getTab(name);
         if (this != GameCanvas.currentScreen) {
            nMSG = 1;
         }
      } else {
         tab = this.publicTab;
      }

      if (tab == null) {
         tab = new ChatTab(name, new mCommand("Chat", this, 4), this.tfInput.cmdClear, true);
         tab.text = new mVector();
         this.list.addElement(tab);
         this.setWTab();
      }

      if (!te.equals("")) {
         String[] tex = FontTeam.arialFontW.splitFontBStrInLine(te, this.w - 20);

         for(int i = 0; i < tex.length; ++i) {
            tab.text.addElement(tex[i]);
            if (tab.text.size() > 50) {
               tab.text.removeElementAt(0);
            }
         }
      }

      if (tab == this.focusT) {
         if (selected + 1 == this.focusT.text.size() - lastSe) {
            selected = this.focusT.text.size() - lastSe;
            cmtoY = selected * 14 - limit / 2;
         }

         cmyLim = this.focusT.text.size() * 14 - limit + 50;
         if (cmyLim < 0) {
            cmyLim = 0;
         }
      }

   }

   protected void doChat() {
      if (!this.tfInput.getText().equals("")) {
         if ((int)(mSystem.currentTimeMillis() / 100L) - chatDelay < 10) {
            GameCanvas.startOKDlg("Không được chat quá nhanh.");
         } else {
            chatDelay = (int)(mSystem.currentTimeMillis() / 100L);
            if (this.focusT.name.equals("Bang hội")) {
               GameService.gI().chatToClan(this.tfInput.getText());
            } else {
               this.addTab(GameScr.mainChar.name + ": " + this.tfInput.getText(), this.focusT.name, 0);
               GameService.gI().sendMsgPrivate(this.focusT.name, this.tfInput.getText());
            }

            this.tfInput.setText("");
         }
      }
   }

   private ChatTab getTab(String name) {
      for(int i = 0; i < this.list.size(); ++i) {
         ChatTab tab = (ChatTab)this.list.elementAt(i);
         if (tab.name.equals(name)) {
            return tab;
         }
      }

      return null;
   }

   private void changTab() {
      this.focusT = (ChatTab)this.list.elementAt(this.focusTab);
      this.focusT.isOpen = false;
      limit = this.h - this.hTab;
      if (this.focusT.isInput) {
         limit -= 21;
      }

      cmyLim = this.focusT.text.size() * 14 - limit + 50;
      if (cmyLim < 0) {
         cmyLim = 0;
      }

      cmtoY = 0;
      cmy = 0;
      lastSe = (limit - 10) / 2 / 14;
      selected = lastSe;
      this.center = this.focusT.center;
      this.right = this.focusT.right;
   }

   public void paint(mGraphics g) {
      g.translate(-g.getTranslateX(), -g.getTranslateY());
      this.lastScr.paint(g);
      GameCanvas.resetTrans(g);
      super.paint(g);
      this.paintTab(g, this.x, this.y, this.w, this.h);
      this.paintText(g);
   }

   private void paintText(mGraphics g) {
      FontTeam.borderFont.drawString(g, this.focusT.name, this.x + this.wTab / 2 + this.focusTab * this.du, this.y + this.hTab / 2 - 5, 2, false);
      if (this.focusT.isInput && !GameCanvas.menu.showMenu) {
         this.tfInput.paint(g);
      }

      g.setClip(this.x, this.y + this.hTab + 5, this.w - 3, limit - 9);
      g.ClipRec(this.x, this.y + this.hTab + 5, this.w - 3, limit - 9);
      g.translate(0, -cmy);
      if (this.focusT.text.size() > 0) {
         int aa = cmy / 14;
         int bb = aa + limit / 14 + 1;
         if (bb >= this.focusT.text.size()) {
            bb = this.focusT.text.size();
         }

         for(int i = aa; i < bb; ++i) {
            String str = (String)this.focusT.text.elementAt(i);
            FontTeam.arialFontW.drawString(g, str, this.x + 8, this.y + i * 14 + this.hTab + 3, 0, false);
         }
      }

      mGraphics.resetTransAndroid(g);
      g.restoreCanvas();
   }

   private void paintTab(mGraphics g, int x, int y, int w, int h) {
      g.setColor(277044);
      g.fillRect(x, y + this.hTab, w, h - this.hTab, false);

      int a;
      for(a = 0; a < 3; ++a) {
         g.setColor(Res.color[a]);
         g.drawRect(x + a, y + a + this.hTab, w - a * 2 - 1, h - a * 2 - this.hTab - 1, false);
      }

      ChatTab tab;
      int b;
      for(a = this.list.size() - 1; a > this.focusTab; --a) {
         tab = (ChatTab)this.list.elementAt(a);

         for(b = 0; b < 3; ++b) {
            g.setColor(Res.color[b]);
            g.drawRect(x + b + a * this.du, y + b, this.wTab - b * 2, this.hTab - b * 2, false);
         }

         ++tab.count;
         if (tab.count > 10) {
            tab.count = 0;
         }

         if (tab.isOpen && tab.count >= 5) {
            g.setColor(-16204118);
            g.fillRect(x + 3 + this.du * a, y + 3, this.wTab - 5, this.hTab - 3, false);
         } else {
            g.setClip(x + this.du * a + this.wTab - this.du, y, this.du - 2, this.hTab);
            g.ClipRec(x + this.du * a + this.wTab - this.du, y, this.du - 2, this.hTab);
            g.drawImage(Res.imgInv[2], x + this.du * a + 3 + this.wTab - 70, y + 3, 0, false);
            g.restoreCanvas();
            g.setClip(x, y, w, h);
         }
      }

      int i;
      for(a = 0; a <= this.focusTab; ++a) {
         for(i = 0; i < 3; ++i) {
            g.setColor(Res.color[i]);
            g.drawRect(x + i + a * this.du, y + i, this.wTab - i * 2, this.hTab - i * 2, false);
         }

         tab = (ChatTab)this.list.elementAt(a);
         ++tab.count;
         if (tab.count > 10) {
            tab.count = 0;
         }

         if (tab.isOpen && tab.count >= 5) {
            g.setColor(-16204118);
            g.fillRect(x + 3 + this.du * a, y + 3, this.wTab - 5, this.hTab - 3 + (a == this.focusTab ? 4 : 0), false);
         } else if (a != this.focusTab) {
            g.setClip(x + this.du * a, y, this.du, this.hTab);
            g.ClipRec(x + this.du * a, y, this.du, this.hTab);
            g.drawImage(Res.imgInv[2], x + this.du * a + 3, y + 3, 0, false);
            g.restoreCanvas();
         } else {
            g.setClip(x + this.du * a + 3, y, this.wTab - 5, this.hTab + 3);
            g.ClipRec(x + this.du * a + 3, y, this.wTab - 5, this.hTab + 3);

            for(b = 0; b < this.wTab / 70 + 1; ++b) {
               g.drawImage(Res.imgInv[2], x + 3 + this.du * a + 70 * b, y + 3, 0, false);
            }

            g.restoreCanvas();
         }

         g.setClip(x - 4, y - 4, w + 8, h + 9);
      }

      g.drawImage(Res.imgInv[0], x - 2 + this.du * this.focusTab, y - 2, 0, false);
      g.drawRegion(Res.imgInv[0], 0, 0, 18, 19, 2, x + 3 + this.wTab + this.du * this.focusTab, y - 2, mGraphics.TOP | mGraphics.RIGHT, false);

      for(a = 0; a < 2; ++a) {
         for(i = 0; i < 2; ++i) {
            g.setColor(Res.color[a == 0 ? 1 + i : 2 - i]);
            g.fillRect(x + this.focusTab * this.du + i + 1 + (this.wTab - 3) * a, y + this.hTab - 8 + 6 + (a == 0 ? i : 1 - i), 1, 3, false);
         }
      }

      g.setClip(0, 0, GameCanvas.w, GameCanvas.h);
      g.drawRegion(Res.imgInv[0], 0, 0, 18, 19, 6, x - 2, y + h + 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
      g.drawRegion(Res.imgInv[0], 0, 0, 18, 19, 3, x + w + 2, y + h + 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
   }

   public boolean keyPress(int keyCode) {
      if (this.focusT.isInput && this.tfInput.isFocus && !GameCanvas.menu.showMenu) {
         this.tfInput.keyPressed(keyCode);
      }

      return super.keyPress(keyCode);
   }

   public void update() {
      this.lastScr.update();
      if (cmy != cmtoY) {
         cmvy = cmtoY - cmy << 2;
         cmdy += cmvy;
         cmy += cmdy >> 4;
         cmdy &= 15;
      }

   }

   public void updateKey() {
      if (this.focusT.isInput && !GameCanvas.menu.showMenu) {
         this.tfInput.update();
      }

      boolean changeFocus = false;
      if (GameCanvas.isKeyPressed(4)) {
         --this.focusTab;
         if (this.focusTab < 0) {
            this.focusTab = this.list.size() - 1;
         }

         this.changTab();
      } else if (GameCanvas.isKeyPressed(6)) {
         ++this.focusTab;
         if (this.focusTab >= this.list.size()) {
            this.focusTab = 0;
         }

         this.changTab();
      }

      if (GameCanvas.keyHold[2]) {
         --selected;
         if (selected < lastSe) {
            selected = lastSe;
         }

         changeFocus = true;
      } else if (GameCanvas.keyHold[8]) {
         if (cmy < cmyLim) {
            ++selected;
         }

         if (selected > this.focusT.text.size() - lastSe) {
            selected = this.focusT.text.size() - lastSe;
         }

         changeFocus = true;
      }

      if (changeFocus) {
         cmtoY = selected * 14 - limit / 2;
         if (cmtoY < 0) {
            cmtoY = 0;
         }

         if (cmtoY > cmyLim) {
            cmtoY = cmyLim;
         }
      }

      super.updateKey();
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 1:
         mVector menu = new mVector();
         if (this.focusTab != 0) {
            menu.addElement(new mCommand("Đóng tab", this, 2));
         }

         menu.addElement(new mCommand("Đóng", this, 3));
         GameCanvas.menu.startAt(menu, 0);
         break;
      case 2:
         this.list.removeElementAt(this.focusTab);
         if (this.focusTab >= this.list.size()) {
            this.focusTab = this.list.size() - 1;
         }

         this.setWTab();
         this.changTab();
         break;
      case 3:
         this.lastScr.switchToMe();
         break;
      case 4:
         this.doChat();
      }

   }
}
