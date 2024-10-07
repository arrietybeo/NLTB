package code.screen;

import code.model.Point;

public class PluginPaint {
   public static PluginPaint[] arr;
   public int id;
   public int index;
   public int type;
   public PluginInfo[] infos;
   public Point point;
   public int totalFrame = 0;
   public int loop = 1;
   public boolean isEnd;

   public PluginPaint clone() {
      PluginPaint pluginPaint = new PluginPaint();
      pluginPaint.id = this.id;
      pluginPaint.loop = this.loop;
      pluginPaint.type = this.type;
      pluginPaint.infos = this.infos;
      return pluginPaint;
   }

   public void update() {
      this.getTotalFraMe();
      if (this.totalFrame <= 0 || this.index <= this.totalFrame) {
         ++this.index;
      }
   }

   public int getTotalFraMe() {
      if (this.totalFrame == 0) {
         try {
            for(int i = 0; i < this.infos.length; ++i) {
               if (this.infos[i].frame + this.infos[i].effectPaint.infos.length > this.totalFrame) {
                  this.totalFrame = this.infos[i].frame + this.infos[i].effectPaint.infos.length;
               }
            }
         } catch (Exception var2) {
         }
      }

      return this.totalFrame;
   }
}
