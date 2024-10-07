package code.model;

public class mCamera {
   public static mCamera instance;
   public int xCam;
   public int yCam;
   public int xTo;
   public int yTo;
   public int xLimit;
   public int yLimit;
   public long timeDelay;
   int cmvx;
   int cmdx;
   int cmvy;
   int cmdy;

   public void setAll(int xLimit, int yLimit, int xCam, int yCam) {
      this.xLimit = xLimit;
      this.yLimit = yLimit;
      if (this.yLimit < 0) {
         this.yLimit = 0;
      }

      if (this.xLimit < 0) {
         this.xLimit = 0;
      }

      if (xCam > xLimit) {
         xCam = xLimit;
      }

      if (xCam < 0) {
         xCam = 0;
      }

      if (yCam > yLimit) {
         yCam = yLimit;
      }

      if (yCam < 0) {
         yCam = 0;
      }

      this.xCam = xCam;
      this.yCam = yCam;
      this.xTo = xCam;
      this.yTo = yCam;
   }

   public void setAllTo(int xLimit, int yLimit, int xCam, int yCam) {
      this.xLimit = xLimit;
      this.yLimit = yLimit;
      if (this.yLimit < 0) {
         this.yLimit = 0;
      }

      if (this.xLimit < 0) {
         this.xLimit = 0;
      }

      this.xTo = xCam;
      this.yTo = yCam;
   }

   public void setCamera(int xCam, int yCam) {
      this.xCam = xCam;
      this.yCam = yCam;
      this.xTo = xCam;
      this.yTo = yCam;
   }

   public void setCameraWithLim(int xCam, int yCam) {
      if (xCam < 0) {
         xCam = 0;
      }

      if (xCam > this.xLimit) {
         xCam = this.xLimit;
      }

      if (yCam < 0) {
         yCam = 0;
      }

      if (yCam > this.yLimit) {
         yCam = this.yLimit;
      }

      this.xCam = xCam;
      this.yCam = yCam;
      this.xTo = xCam;
      this.yTo = yCam;
   }

   public void moveCamera(int xTo, int yTo) {
      this.xTo = xTo;
      this.yTo = yTo;
   }

   public void UpdateCamera() {
      if (this.xCam != this.xTo) {
         this.cmvx = this.xTo - this.xCam << 1;
         this.cmdx += this.cmvx;
         this.xCam += this.cmdx >> 4;
         this.cmdx &= 15;
         if (this.xCam < 0) {
            this.xCam = 0;
         }

         if (this.xCam > this.xLimit) {
            this.xCam = this.xLimit;
         }
      }

      if (this.yCam != this.yTo) {
         this.cmvy = this.yTo - this.yCam << 1;
         this.cmdy += this.cmvy;
         this.yCam += this.cmdy >> 4;
         this.cmdy &= 15;
         if (this.yCam < 0) {
            this.yCam = 0;
         }

         if (this.yCam > this.yLimit) {
            this.yCam = this.yLimit;
         }
      }

   }

   public void UpdateCameraCreate() {
      if (this.xCam != this.xTo) {
         this.cmvx = this.xTo - this.xCam << 1;
         this.cmdx += this.cmvx;
         this.xCam += this.cmdx >> 4;
         this.cmdx &= 15;
      }

      if (this.yCam != this.yTo) {
         this.cmvy = this.yTo - this.yCam << 1;
         this.cmdy += this.cmvy;
         this.yCam += this.cmdy >> 4;
         this.cmdy &= 15;
         if (this.yCam < 0) {
            this.yCam = 0;
         }

         if (this.yCam > this.yLimit) {
            this.yCam = this.yLimit;
         }
      }

   }
}
