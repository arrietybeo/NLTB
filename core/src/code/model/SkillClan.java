package code.model;

import lib.mSystem;

public class SkillClan {
   public short time;
   public int timeCur = (int)(mSystem.currentTimeMillis() / 1000L);
   public String info;
   public byte idIcon;
   public byte lv;

   public void updateTime() {
      if (mSystem.currentTimeMillis() / 1000L - (long)this.timeCur >= (long)this.time) {
         this.time = 0;
      }

   }
}
