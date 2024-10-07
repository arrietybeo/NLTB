package code.effect;

public class Line {
   public int x0;
   public int y0;
   public int x1;
   public int y1;
   public int vx;
   public int vy;
   public int f;
   public int fRe;
   public int idColor;
   public boolean is2Line = false;

   public void setLine(int x0, int y0, int x1, int y1, int vx, int vy, boolean is2Line) {
      this.x0 = x0;
      this.y0 = y0;
      this.x1 = x1;
      this.y1 = y1;
      this.vx = vx;
      this.vy = vy;
      this.is2Line = is2Line;
   }

   public void update() {
      this.x0 += this.vx;
      this.x1 += this.vx;
      this.y0 += this.vy;
      this.y1 += this.vy;
      ++this.f;
   }
}
