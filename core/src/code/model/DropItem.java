package code.model;

import code.screen.Res;
import code.screen.screen.GameData;
import lib.mGraphics;

public class DropItem extends Dropable {
   public void paint(mGraphics g) {
      ItemTemplate template = ((ItemTemplate[])Res.itemTemplates.elementAt(this.itemClass))[this.item_template_id];
      GameData.paintIcon(g, template.idIcon, this.x, this.y - this.deltaH);
   }

   public String getDisplayName() {
      return ((ItemTemplate[])Res.itemTemplates.elementAt(this.itemClass))[this.item_template_id].name;
   }

   public boolean isItemCanGet() {
      return true;
   }
}
