package code.effect;

import code.model.Actor;
import code.model.DataEffect;
import code.model.ImageIcon;
import code.screen.screen.GameData;
import lib.mGraphics;
import lib.mHashtable;

public class DynamicEffect extends Actor {
   public static mHashtable ALL_DATA_DYNAMIC_EFFECT = new mHashtable();
   public short id;
   public long timeExist = -1L;
   byte index = 0;
   public byte stop = 0;
   public int dame;

   public DynamicEffect(int id) {
      this.id = (short)id;
   }

   public void paint(mGraphics g, int x, int y) {
      DataEffect def = (DataEffect)ALL_DATA_DYNAMIC_EFFECT.get(String.valueOf(this.id));
      if (def != null) {
         ImageIcon img = GameData.getImgIcon((short)(this.id + 8700));
         if (img != null && img.img != null) {
            byte[] sequen = getSequence(this.id);
            def.paint(g, sequen[this.index], x, y, 0, img.img);
         }
      }

   }

   public static void setDataEffect(byte[][] array) {
      for(int i = 0; i < array.length; ++i) {
         DataEffect def = new DataEffect(array[i], -1, false);
         ALL_DATA_DYNAMIC_EFFECT.put(String.valueOf(i), def);
      }

   }

   public static byte[] getSequence(int id) {
      DataEffect def = (DataEffect)ALL_DATA_DYNAMIC_EFFECT.get(String.valueOf(id));
      return def != null ? def.sequence : null;
   }

   public boolean canDestroy() {
      if (this.timeExist == -1L) {
         return false;
      } else {
         return System.currentTimeMillis() - this.timeExist >= 0L && this.index == 0;
      }
   }

   public void update() {
      try {
         byte[] sequen = getSequence(this.id);
         if (sequen != null) {
            this.index = (byte)((this.index + 1) % sequen.length);
         }
      } catch (Exception var2) {
      }

      if (this.canDestroy()) {
         this.wantDestroy = true;
      }

   }

   public void paint(mGraphics g) {
      DataEffect def = (DataEffect)ALL_DATA_DYNAMIC_EFFECT.get(String.valueOf(this.id));
      if (def != null) {
         ImageIcon img = GameData.getImgIcon((short)(this.id + 8700));
         if (img != null && img.img != null) {
            byte[] sequen = getSequence(this.id);
            def.paint(g, sequen[this.index], this.x, this.y, 0, img.img);
         }
      }

   }

   public void setPosTo(short x, short y) {
   }
}
