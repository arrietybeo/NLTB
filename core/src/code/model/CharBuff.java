package code.model;

import lib.mSystem;

public class CharBuff extends Effect {
   public int timeLife = -1;
   public long tick = 0L;
   int timeReceive;
   int count;

   public CharBuff(int x, int y, int type) {
      super(x, y, type);
      this.tick = 0L;
   }

   public void setTimeLive(int time) {
      this.timeLife = time;
      this.count = 0;
      this.tick = mSystem.currentTimeMillis() + (long)(this.timeLife * 1000);
      if (this.timeLife <= 0) {
         this.wantDestroy = true;
      }

   }

   public boolean isCenter() {
      return this.type == 20 || this.type == 22 || this.type == 23 || this.type == 24 || this.type == 25 || this.type == 27;
   }

   public void update() {
   }

   public void setXY(int x, int y) {
      this.x = x;
      this.y = y;
   }
}
