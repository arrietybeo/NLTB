package lib2;

import code.model.IActionListener;
import lib.mGraphics;

public class ChatTextField implements IActionListener {
   private static ChatTextField instance;
   public static boolean isShow;

   public static ChatTextField gI() {
      return instance == null ? (instance = new ChatTextField()) : instance;
   }

   public void perform(int idAction, Object p) {
   }

   public void openKeyIphone() {
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }
}
