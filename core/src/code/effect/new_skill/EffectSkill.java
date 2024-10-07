package code.effect.new_skill;

import code.model.Effect;
import code.model.EffectManager;
import code.model.ImageIcon;
import code.screen.Res;
import code.screen.screen.GameData;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class EffectSkill extends Effect {
   public int time;
   public int frame1;
   public int type;
   public byte transfrom = 0;
   public static int[] w = new int[]{32, 32, 16, 36, 50, 50, 30, 23, 64, 88, 14, 14, 42, 32, 50, 19, 30, 88, 14, 24, 70, 32, 30, 7, 16, 46, 42, 36, 38, 11, 32, 50, 26, 50, 20, 19, 70, 55, 10, 7, 7, 10, 32, 24, 10, 27, 27, 31, 53, 10, 32, 50, 27, 32, 47, 41, 38, 32, 32, 58, 12, 32, 42, 12, 24, 70, 56, 50, 47, 70, 34, 31, 7, 17, 25, 18, 134, 35, 158, 32, 27, 35, 16, 24, 12, 14, 15, 15, 16, 18, 12, 16, 34, 18, 21, 19, 55, 22, 28, 9, 16, 27, 16, 35, 62, 22, 27, 22, 12, 28, 17, 12, 7, 12, 7, 50, 37, 37, 32, 53, 31, 10, 16, 7, 10, 10, 49, 14, 12, 26, 40, 19, 53};
   public static int[] h = new int[]{32, 32, 14, 32, 50, 48, 30, 40, 46, 60, 15, 14, 72, 32, 26, 45, 40, 60, 14, 14, 95, 32, 30, 7, 16, 50, 27, 30, 38, 11, 32, 50, 34, 50, 40, 36, 70, 55, 10, 7, 7, 10, 32, 24, 40, 23, 27, 31, 38, 10, 32, 48, 32, 32, 48, 17, 38, 32, 32, 38, 23, 28, 41, 12, 35, 70, 20, 23, 47, 20, 29, 22, 7, 36, 56, 14, 35, 73, 72, 32, 18, 32, 16, 24, 12, 12, 15, 12, 10, 18, 12, 16, 32, 18, 15, 19, 35, 18, 18, 9, 16, 20, 16, 43, 60, 16, 25, 18, 12, 18, 17, 20, 7, 11, 7, 48, 39, 38, 30, 60, 31, 10, 10, 7, 10, 10, 21, 15, 23, 24, 31, 45, 33};
   public static final byte[][] frame = new byte[][]{{0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 1, 2, 1, 2, 3}, {0, 1, 2}, new byte[47], {0, 1, 2, 3, 4, 5, 3, 4, 5}, {2, 3, 4}, {0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2}, {0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 1, 2}, {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3, 4}, {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[19], {0, 1, 2, 3, 4}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 2, 3}, {2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, new byte[1], {0, 0, 1, 2, 3, 3, 4, 4, 4, 4}, {0, 1, 2, 3, 4, 5}, {0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5}, new byte[11], {0, 1, 2}, {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, {0, 1, 2, 3, 4}, {0, 1, 2, 3, 4}, {0, 1, 2}, {0, 1, 2, 3}, new byte[1], new byte[1], {0, 1, 2, 0, 1, 2}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2}, {0, 1, 2, 1, 2, 1, 2, 0, 1, 2, 1, 2, 0, 1, 2, 1, 2}, {0, 1, 2, 3, 4}, {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3}, {1, 1, 1, 1, 1, 1}, {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 0, 1, 2, 3, 3, 4, 4, 4, 4}, new byte[1], {0, 0, 1, 1, 2, 3, 2, 3, 2, 3}, new byte[1], {0, 1}, {0, 1, 2}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 0, 1, 1, 2, 2, 3}, {0, 1, 2, 3, 4}, {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, {0, 1, 2, 0, 1, 2}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 0, 1, 0, 1, 0, 1, 0, 1}, {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2, 0, 1, 2}, new byte[0], {0, 1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 8}, {0, 1, 2, 0, 1, 2}, {0, 1}, {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2, 3, 0, 1, 2, 3}, {0, 0, 1, 1, 2, 2, 3, 3}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {0, 1, 2}, {0, 1, 2}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3}, {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2}, {0, 0, 1, 1, 2, 2}, {0, 1, 2, 0, 1, 2}, new byte[3], new byte[3], new byte[3], {0, 1, 2, 3, 4, 5}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3, 4}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3, 4}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4}, {0, 1, 2}, {0, 1, 2}, {0, 0, 1, 1, 2, 2, 3, 3}, new byte[25], {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3, 4, 5}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, {0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2, 3, 0, 1, 2, 3}, {0, 1, 2}, {0, 1, 2}, new byte[3], {0, 1}};
   public static int[] frame2 = new int[]{3, 4, 3, 1, 6, 5, 3, 3, 3, 3, 4, 4, 5, 5, 3, 1, 5, 2, 4, 3, 1, 5, 6, 4, 4, 4, 6, 1, 3, 18, 5, 5, 3, 4, 1, 1, 3, 3, 4, 4, 4, 1, 3, 5, 1, 4, 1, 2, 3, 10, 4, 5, 3, 4, 3, 3, 3, 2, 2, 3, 4, 3, 4, 9, 3, 2, 4, 4, 4, 3, 3, 3, 4, 3, 3, 3, 1, 1, 1, 6, 3, 4, 4, 4, 4, 3, 4, 3, 3, 4, 4, 4, 4, 4, 3, 4, 4, 3, 3, 3, 4, 3, 4, 4, 5, 3, 3, 3, 4, 3, 4, 3, 4, 4, 4, 5, 5, 3, 3, 4, 2, 4, 4, 6, 4, 4, 4, 4, 4, 3, 3, 1, 2};

   public static void load() {
   }

   public static int getFrame(int type) {
      return type < 0 ? 0 : frame2[type];
   }

   public static int getWidth(int index) {
      return w[index];
   }

   public static int getHight(int index) {
      return h[index];
   }

   public static Image getImage(int index) {
      if (index < 0) {
         return null;
      } else {
         ImageIcon imgicon = GameData.getImgIcon((short)(index + Res.ID_EFFECT_SKILL));
         return imgicon != null && imgicon.img != null ? imgicon.img : null;
      }
   }

   public EffectSkill(int x, int y, int type, int loop) {
      this.x = x;
      this.y = y;
      this.type = type;
      this.time = loop;
      this.transfrom = 0;
   }

   public EffectSkill(int x, int y, int type) {
      this.x = x;
      this.y = y;
      this.type = type;
      this.transfrom = 0;
   }

   public EffectSkill(int x, int y, int type, int loop, int transform) {
      this.x = x;
      this.y = y;
      this.type = type;
      this.time = loop;
      this.transfrom = (byte)transform;
   }

   public static void createLowEfect(int x, int y, int type, int transfrom) {
      EffectSkill u = new EffectSkill(x, y, type, 4, transfrom);
      EffectManager.addLowEffect(u);
   }

   public static void createLowEfect(int x, int y, int type) {
      EffectSkill u = new EffectSkill(x, y, type, 4);
      EffectManager.addLowEffect(u);
   }

   public static void createHiEfect(int x, int y, int type) {
      EffectSkill u = new EffectSkill(x, y, type, 4);
      EffectManager.addHiEffect(u);
   }

   public static void createHiEfectWithTransform(int x, int y, int type, int transform) {
      EffectSkill u = new EffectSkill(x, y, type, 4, transform);
      EffectManager.addHiEffect(u);
   }

   public void paint(mGraphics g) {
      Image img = getImage(this.type);

      try {
         if (img != null) {
            g.drawRegion(img, 0, frame[this.type][this.frame1] * h[this.type], w[this.type], h[this.type], this.transfrom, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public void update() {
      ++this.frame1;
      if (this.frame1 >= frame[this.type].length - 1) {
         this.frame1 = frame[this.type].length - 1;
         this.wantDestroy = true;
      }

   }

   public int gettype() {
      return this.type;
   }
}
