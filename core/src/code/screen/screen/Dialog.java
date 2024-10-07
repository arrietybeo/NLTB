package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.MainTeam;
import lib.mGraphics;

public abstract class Dialog extends MainTeam implements IActionListener {
   public void paint(mGraphics g) {
      super.paint(g);
   }

   public void keyPress(int keyCode) {
   }

   public void update() {
      super.updateKey();
   }

   public void show() {
      GameCanvas.currentDialog = this;
   }
}
