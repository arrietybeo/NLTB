package code.model;

import code.screen.screen.GameData;
import lib.mGraphics;

public class ItemQuest extends Dropable {
   public static String[] NAME_ITEM = new String[]{""};
   public static byte[] ICON_IMG = new byte[1];

   public void paint(mGraphics g) {
      GameData.paintIcon(g, (short)(ICON_IMG[this.item_template_id] + 8000), this.x, this.y - this.deltaH);
   }

   public String getDisplayName() {
      return NAME_ITEM[this.item_template_id];
   }

   public boolean isItemCanGet() {
      return true;
   }
}
