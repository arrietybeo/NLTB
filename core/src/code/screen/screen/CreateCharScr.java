package code.screen.screen;

import code.model.IActionListener;
import lib.mGraphics;

public class CreateCharScr extends ScreenTeam implements IActionListener {
   public static CreateCharScr me;

   public static CreateCharScr gI() {
      return me == null ? (me = new CreateCharScr()) : me;
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
   }
}
