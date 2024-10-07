package code.model;

import code.screen.Atlas;
import code.screen.ChatBox;
import code.screen.Soar;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import lib.Cout;
import lib.mGraphics;
import lib.mVector;

public class Point {
   public int x;
   public int y;
   public int g;
   public int v;
   public int w;
   public int h;
   public int color = 0;
   public int limitY;
   public int f;
   public int vx;
   public int vy;
   public int toX;
   public int toY;
   public int fRe;
   public int sx;
   public int sy;
   public byte dis;
   public int yCenter = -1;
   public int dir;
   public int dyChange;
   public int speed;
   public int speedTemp;
   public byte status;
   public boolean isRemove;
   public int pWidth;
   public int pHeight;
   public mVector soars = new mVector();
   public ChatBox chatPopup;
   public boolean isWater = false;
   public int frame;
   public byte id;
   public byte player;
   public static final byte STATUS_STAND = 0;
   public static final byte STATUS_MOVE = 1;
   public static final byte STATUS_INJURE = 2;
   public static final byte STATUS_ATTACK = 3;
   public static final byte STATUS_DIE = 4;
   public static final byte STATUS_HIDE = 5;
   public static final byte STATUS_WAIT_DIE = 6;
   public static final byte DIR_DOWN = 0;
   public static final byte DIR_UP = 1;
   public static final byte DIR_LEFT = 2;
   public static final byte DIR_RIGHT = 3;
   public static final byte DIR_NONE = 4;
   public static final byte DIR_UP_LEFT = 4;
   public static final byte DIR_UP_RIGHT = 5;
   public static final byte DIR_DOWN_LEFT = 6;
   public static final byte DIR_DOWN_RIGHT = 7;
   private static int spleft = 4;
   private static int spright = 3;
   public static int spbottom = 1;
   public static int sptop = 1;

   public Point() {
   }

   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public Point(int x, int y, int speed, int id) {
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.id = (byte)id;
      this.sx = x;
      this.sy = y;
      this.player = (byte)GameScr.r.nextInt(2);
   }

   public void createSoar(String str, FontTeam font) {
      this.soars.addElement(new Soar(str, font, this));
   }

   public void createSoar(String str, FontTeam font, int indexMax) {
      this.soars.addElement(new Soar(str, font, this, indexMax));
   }

   public void update() {
      ++this.f;
      this.x += this.vx;
      this.y += this.vy;

      for(int i = 0; i < this.soars.size(); ++i) {
         Soar soar = (Soar)this.soars.elementAt(i);
         soar.update();
      }

      if (this.chatPopup != null && this.chatPopup.isClear()) {
         this.chatPopup = null;
      }

   }

   public void changeStatusStand() {
      Cout.println("Code Rỗng");
   }

   public int checkTile() {
      if (Atlas.Me().isMoveInBridge(this.x, this.y)) {
         return 1;
      } else {
         int xx = this.x % Atlas.Me().size;
         int yy = this.y % Atlas.Me().size;
         int hsize = Atlas.Me().size / 2;
         if (xx < 10 && yy < 10) {
            if (Atlas.Me().tileTypeAt(this.x, this.y, 4)) {
               if (this.dir == 3) {
                  this.x = this.x / hsize * hsize - this.getSpLeft();
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 2) {
                  this.x = this.x / hsize * hsize + hsize + this.getSpRight();
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 0) {
                  this.y = this.y / hsize * hsize - sptop;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 1) {
                  this.y = this.y / hsize * hsize + hsize + spbottom;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 6) {
                  this.x = this.x + this.speedTemp / 2 + 1;
                  this.y = this.y - this.speedTemp / 2 - 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 7) {
                  this.x = this.x - this.speedTemp / 2 - 1;
                  this.y = this.y - this.speedTemp / 2 - 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 4) {
                  this.x = this.x + this.speedTemp / 2 + 1;
                  this.y = this.y + this.speedTemp / 2 + 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 5) {
                  this.x = this.x - this.speedTemp / 2 - 1;
                  this.y = this.y + this.speedTemp / 2 + 1;
                  this.changeStatusStand();
                  return 1;
               }
            }
         } else if (xx >= 10 && yy < 10) {
            if (Atlas.Me().tileTypeAt(this.x, this.y, 8)) {
               if (this.dir == 3) {
                  this.x = this.x / hsize * hsize - this.getSpLeft();
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 2) {
                  this.x = this.x / hsize * hsize + hsize + this.getSpRight();
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 0) {
                  this.y = this.y / hsize * hsize - sptop;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 1) {
                  this.y = this.y / hsize * hsize + hsize + spbottom;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 6) {
                  this.x = this.x + this.speedTemp / 2 + 1;
                  this.y = this.y - this.speedTemp / 2 - 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 7) {
                  this.x = this.x - this.speedTemp / 2 - 1;
                  this.y = this.y - this.speedTemp / 2 - 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 4) {
                  this.x = this.x + this.speedTemp / 2 + 1;
                  this.y = this.y + this.speedTemp / 2 + 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 5) {
                  this.x = this.x - this.speedTemp / 2 - 1;
                  this.y = this.y + this.speedTemp / 2 + 1;
                  this.changeStatusStand();
                  return 1;
               }
            }
         } else if (xx < 10 && yy >= 10) {
            if (Atlas.Me().tileTypeAt(this.x, this.y, 16)) {
               if (this.dir == 3) {
                  this.x = this.x / hsize * hsize - this.getSpLeft();
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 2) {
                  this.x = this.x / hsize * hsize + hsize + this.getSpRight();
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 0) {
                  this.y = this.y / hsize * hsize - sptop;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 1) {
                  this.y = this.y / hsize * hsize + hsize + spbottom;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 6) {
                  this.x = this.x + this.speedTemp / 2 + 1;
                  this.y = this.y - this.speedTemp / 2 - 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 7) {
                  this.x = this.x - this.speedTemp / 2 - 1;
                  this.y = this.y - this.speedTemp / 2 - 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 4) {
                  this.x = this.x + this.speedTemp / 2 + 1;
                  this.y = this.y + this.speedTemp / 2 + 1;
                  this.changeStatusStand();
                  return 1;
               }

               if (this.dir == 5) {
                  this.x = this.x - this.speedTemp / 2 - 1;
                  this.y = this.y + this.speedTemp / 2 + 1;
                  this.changeStatusStand();
                  return 1;
               }
            }
         } else if (xx >= 10 && yy >= 10 && Atlas.Me().tileTypeAt(this.x, this.y, 32)) {
            if (this.dir == 3) {
               this.x = this.x / hsize * hsize - this.getSpLeft();
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 2) {
               this.x = this.x / hsize * hsize + hsize + this.getSpRight();
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 0) {
               this.y = this.y / hsize * hsize - sptop;
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 1) {
               this.y = this.y / hsize * hsize + hsize + spbottom;
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 6) {
               this.x = this.x + this.speedTemp / 2 + 1;
               this.y = this.y - this.speedTemp / 2 - 1;
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 7) {
               this.x = this.x - this.speedTemp / 2 - 1;
               this.y = this.y - this.speedTemp / 2 - 1;
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 4) {
               this.x = this.x + this.speedTemp / 2 + 1;
               this.y = this.y + this.speedTemp / 2 + 1;
               this.changeStatusStand();
               return 1;
            }

            if (this.dir == 5) {
               this.x = this.x - this.speedTemp / 2 - 1;
               this.y = this.y + this.speedTemp / 2 + 1;
               this.changeStatusStand();
               return 1;
            }
         }

         return 0;
      }
   }

   public int getSpLeft() {
      return this.speedTemp < spleft ? this.speedTemp : spleft;
   }

   public int getSpLeft1() {
      return this.speedTemp / 2 < spleft ? this.speedTemp / 2 - 1 : spleft;
   }

   public int getSpRight() {
      return this.speedTemp <= spright ? this.speedTemp : spright;
   }

   public int getSpRight1() {
      return this.speedTemp / 2 <= spright ? this.speedTemp / 2 + 1 : spright;
   }

   public int checkLimit() {
      int minx = 0;
      int miny = 10;
      int maxx = Atlas.Me().getWidth();
      int maxy = Atlas.Me().getHeight();
      if (this.x < minx + this.getSpLeft()) {
         this.x = minx + this.getSpLeft();
         this.changeStatusStand();
         return 1;
      } else if (this.y < miny + spbottom) {
         this.y = miny + spbottom;
         this.changeStatusStand();
         return 1;
      } else if (this.x > maxx - this.getSpRight()) {
         this.x = maxx - this.getSpRight();
         this.changeStatusStand();
         return 1;
      } else if (this.y > maxy - sptop) {
         this.y = maxy - sptop;
         this.changeStatusStand();
         return 1;
      } else {
         return 0;
      }
   }

   public int checkItem() {
      return 0;
   }

   public int checkTree() {
      return 0;
   }

   public int checkNpc() {
      return 0;
   }

   public void checWater() {
      this.isWater = false;

      try {
         if (Atlas.Me().isInBridge(this.x, this.y)) {
            return;
         }
      } catch (Exception var3) {
      }

      int xx = this.x % Atlas.Me().size;
      int yy = this.y % Atlas.Me().size;
      if (xx < 10 && yy < 10) {
         if (Atlas.Me().tileTypeAt(this.x, this.y, 64)) {
            this.isWater = true;
         }
      } else if (xx >= 10 && yy < 10) {
         if (Atlas.Me().tileTypeAt(this.x, this.y, 128)) {
            this.isWater = true;
         }
      } else if (xx < 10 && yy >= 10) {
         if (Atlas.Me().tileTypeAt(this.x, this.y, 256)) {
            this.isWater = true;
         }
      } else if (xx >= 10 && yy >= 10 && Atlas.Me().tileTypeAt(this.x, this.y, 512)) {
         this.isWater = true;
      }

   }

   public void paint(mGraphics g) {
   }

   public void paintSteep(mGraphics g) {
      g.setColor(16777215);
      g.fillRect(this.x, this.y, 10, 10, false);
   }

   public int xCenter() {
      return this.x;
   }

   public int yCenter() {
      return this.y - this.pHeight / 2;
   }

   public int minx() {
      return this.x - this.pWidth / 2;
   }

   public int maxx() {
      return this.x + this.pWidth / 2;
   }

   public int miny() {
      return this.y - this.pHeight / 2;
   }

   public int maxy() {
      return this.y + this.pHeight / 2;
   }

   public int minxx() {
      return this.x - this.pWidth / 4;
   }

   public int maxxx() {
      return this.x + this.pWidth / 4;
   }

   public int minyy() {
      return this.y - this.pHeight / 4;
   }

   public int maxyy() {
      return this.y + this.pHeight / 4;
   }

   public void changeInjure() {
      this.status = 2;
   }
}
