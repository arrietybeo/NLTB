package code.screen;

import code.model.Char;
import code.model.Point;
import lib.Cout;
import lib.mVector;

public class SkillRun extends Point {
   public int index = 0;
   public int maxFight;
   public int dir;
   public PluginPaint pluginStart = null;
   public PluginPaint pluginEnd = null;
   public SkillInfo[] infos;
   public Point point;
   public Char c;
   public boolean isSend;
   public boolean isDontSend;
   public mVector list = new mVector();
   public mVector darts = new mVector();
   public int skillTemplateId;
   public static int spaceLan = 50;
   public boolean isUse;

   public void setDart() {
      byte tt = 0;

      try {
         for(int i = 0; i < this.infos.length; ++i) {
            if (this.infos[i].dartPaintId > 0) {
               this.darts.addElement((this.infos[i].dartPaintId));
            }
         }

         int var4 = tt + 1;
         SkillTemplate skill = Phai.arr[this.c.clazz].getSkillTemplate(this.skillTemplateId);
         ++var4;
      } catch (Exception var3) {
         Cout.println("" + tt);
      }

   }

   public void update() {
   }

   public boolean isEnd() {
      return this.index >= this.infos.length;
   }
}
