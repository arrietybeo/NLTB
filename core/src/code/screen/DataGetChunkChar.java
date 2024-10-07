package code.screen;

import lib.mSystem;

public class DataGetChunkChar {
   public byte[] data = null;
   public boolean isLoad = true;
   public long time = mSystem.currentTimeMillis();

   public boolean isTimeOut() {
      return mSystem.currentTimeMillis() - this.time >= 10000L;
   }
}
