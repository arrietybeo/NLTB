package code.model;

import code.screen.Res;
import lib.mVector;

public class ChatTab {
   public mCommand center;
   public mCommand right;
   public int count;
   public String name;
   public mVector text;
   public boolean isInput;
   public boolean isOpen;

   public ChatTab(String name, mCommand cen, mCommand right, boolean isInput) {
      this.name = name;
      this.center = cen;
      this.right = right;
      this.isInput = isInput;
      this.isOpen = true;
      this.count = Res.rnd(10);
   }
}
