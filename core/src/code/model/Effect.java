package code.model;

import code.main.GameCanvas;
import lib.mGraphics;

public class Effect extends Actor {
   public Actor target;
   public Actor Owner;
   public static final byte TYPE_ICE = 10;
   public static final byte TYPE_LIGHT = 11;
   public static final byte TYPE_POISON = 12;
   protected static final byte[] splashBuaY = new byte[]{-30, -26, -30, -30};
   public int dame;
   public boolean isDame0;
   public int[] arrDame;
   public boolean isEffAuto = false;
   public short delay;
   public short idarrow;
   public int x;
   public int y;
   public int w;
   public int h;
   public short type;
   protected int f;
   protected int fRemove;
   public byte loop = 0;
   public byte yadd;

   public void doDestroy() {
      this.wantDestroy = true;
   }

   public Effect(int s) {
   }

   public Effect() {
   }

   public void setDame(int dame) {
      this.dame = dame;
   }

   public int getDame() {
      return this.dame;
   }

   public Effect(int x, int y, int type) {
   }

   public void settarget(Actor target) {
      this.target = target;
   }

   public void startFlyText(int dame, int color, int x, int y, int dx, int dy) {
      if (dame > 0) {
         GameCanvas.gameScr.startFlyText("- " + dame, color, x, y, 1, -2);
      }
   }

   public void startFlyText(int dame, int color, int x, int y, int dx, int dy, int dir) {
      if (dame > 0) {
         GameCanvas.gameScr.startFlyText("- " + dame, color, x, y, 1, -2, dir);
      }
   }

   public void setOwner(Actor Owner) {
      this.Owner = Owner;
   }

   public void paint(mGraphics g) {
   }

   public void update() {
   }

   public boolean isFlydame() {
      return false;
   }

   public int gettype() {
      return 0;
   }
}
