package lib;

import java.util.Random;

public class mRandom {
   public Random r;

   public mRandom() {
      this.r = new Random();
   }

   public mRandom(long c) {
      this.r = new Random(c);
   }

   public int nextInt() {
      return this.r.nextInt();
   }

   public int nextInt(int n) {
      return this.r.nextInt(n);
   }

   public int random(int a, int b) {
      return a + this.r.nextInt(b - a);
   }
}
