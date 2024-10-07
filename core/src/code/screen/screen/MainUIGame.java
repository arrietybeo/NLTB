package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import lib.mGraphics;
import lib.mVector;

public class MainUIGame extends ScreenTeam implements IActionListener {
   public mVector shopItemBuyNpc = new mVector();
   public byte mainIndexUI = 0;
   public byte subIndexUi = -1;
   boolean isMe;

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
   }

   public void switchToMe() {
      super.switchToMe();
   }

   public void switchToMe(boolean isReset) {
      super.switchToMe(isReset);
   }

   public void switchToMe(ScreenTeam lastSCR) {
      this.isMe = false;
      if (GameCanvas.currentScreen == this) {
         this.isMe = true;
      } else {
         super.switchToMe(lastSCR);
      }
   }
}
