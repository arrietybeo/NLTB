package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Layer;
import code.model.mCommand;
import code.screen.Res;
import lib.mGraphics;

public class PageScr extends ScreenTeam implements IActionListener {
   public static PageScr me;
   public Layer layer;
   private int x;
   private int y;
   private int w;
   private int h;
   public int indexFont;
   private String title;

   public static PageScr gI() {
      return me == null ? (me = new PageScr()) : me;
   }

   public void switchToMe() {
      if (GameCanvas.currentScreen != this) {
         this.lastSCR = GameCanvas.currentScreen;
      }

      super.switchToMe();
   }

   public void setInfo(int x, int y, int w, int h, String title, mCommand cmd) {
      this.indexFont = 0;
      this.x = x;
      this.y = y - 10;
      this.w = w;
      this.h = h;
      this.title = title;
      this.center = cmd;
      this.left = new mCommand("Đóng", this, 1);
   }

   public void update() {
      if (this.lastSCR != GameCanvas.shop) {
         this.lastSCR.update();
      }

      if (this.layer != null) {
         this.layer.update();
      }

   }

   public boolean keyPress(int keyCode) {
      this.layer.keyPress(keyCode);
      return false;
   }

   public void paint(mGraphics g) {
      this.lastSCR.paint(g);
      GameCanvas.resetTrans(g);
      g.translate(this.x, this.y);
      Res.paintDlgFull(g, 0, 0, this.w, this.h);
      if (this.indexFont == 0) {
         FontTeam.bigFont.drawString(g, this.title, this.w / 2, 8, 2, false);
      } else {
         FontTeam.normalFont[0].drawString(g, this.title, this.w / 2, 6, 2, false);
      }

      if (this.layer != null) {
         this.layer.paint(g, 0, 35);
      }

      super.paint(g);
   }

   public void switchToMe(ScreenTeam lastSCR) {
      super.switchToMe(lastSCR);
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
      switch(idAction) {
      case 1:
         this.lastSCR.switchToMe();
      default:
      }
   }
}
