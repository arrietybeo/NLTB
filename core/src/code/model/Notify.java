package code.model;

import code.main.GameCanvas;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib2.mFont;

public class Notify {
   public String text;
   public String strInfoCharServer;
   public String strInfoServer;
   public int time;
   public int yshow;
   public int yBeginInfo = 0;
   public int speedInfoChar = 4;
   public int xPaintInfoChar;
   public int xmaxInfoChar;
   public int wsai;
   public int wInfoServer;
   public int timePaintInfoChar;
   public int hImgInfo = 22;
   public int speedInfo;
   public int xPaintInfo;
   public int xmaxInfo;
   public int timepaintServer;

   public Notify() {
      this.wInfoServer = GameCanvas.w * 2 / 3;
      this.xmaxInfoChar = 0;
      this.wsai = GameCanvas.w - (GameCanvas.w / 2 - this.wInfoServer / 2) - this.wInfoServer;
      if (GameCanvas.isTouch) {
         this.wInfoServer = GameCanvas.w - 122 - 60;
      }

   }

   public void paint(mGraphics g) {
      GameCanvas.resetTrans(g);
      int xp;
      if (this.strInfoServer != null) {
         int ypaint = GameCanvas.h - 20;

         for(xp = 0; xp < GameCanvas.w / 140 + 1; ++xp) {
            g.drawImage(GameScr.imgBack, xp * 140, ypaint + 1, 0, false);
         }

         mFont.tahoma_7_yellow.drawString(g, this.strInfoServer, GameCanvas.hw + this.wInfoServer / 2 - this.xPaintInfo + this.wsai, ypaint + 4, 0, true);
      }

      byte ypaint;
      if (this.strInfoCharServer != null) {
         ypaint = 0;
         int xss = 117;
         if (!GameCanvas.isTouch) {
            xss = 0;
         }

         for(int i = 0; i < (GameCanvas.w - xss) / 140 + 1; ++i) {
            g.drawImage(GameScr.imgBack, xss + i * 140, ypaint, 0, false);
         }

         g.setClip(117, 0, GameCanvas.w - xss, 20);
         g.ClipRec(117, 0, GameCanvas.w - xss, 20);
         mFont.tahoma_7b_white.drawString(g, this.strInfoCharServer, GameCanvas.hw + this.wInfoServer / 2 - this.xPaintInfoChar + this.wsai, ypaint + 4, 0, true);
         int var10000 = ypaint + this.hImgInfo;
         g.restoreCanvas();
         GameCanvas.resetTrans(g);
      }

      if (this.text != null) {
         if (!GameCanvas.isTouch) {
            ypaint = 0;
            GameCanvas.resetTrans(g);
            g.drawImage(GameScr.imgBack, GameCanvas.w - 140, ypaint + this.yshow, 0, false);
            mFont.tahoma_7_white.drawString(g, this.text, GameCanvas.w - 70, ypaint + 4 + this.yshow, 2, true);
         } else {
            ypaint = 0;
            xp = GameCanvas.w / 2;
            GameCanvas.resetTrans(g);
            g.setClip(xp - 85, ypaint + this.yshow + 40, 170, 20);
            g.ClipRec(xp - 85, ypaint + this.yshow + 40, 170, 20);
            g.drawImage(GameScr.imgBack, xp - 85, ypaint + this.yshow + 40, 0, false);
            g.drawImage(GameScr.imgBack, xp - 85 + 140, ypaint + this.yshow + 40, 0, false);
            mFont.tahoma_7_white.drawString(g, this.text, xp, ypaint + 4 + this.yshow + 40, 2, true);
            g.restoreCanvas();
         }
      }

      GameCanvas.resetTrans(g);
   }

   public void update() {
      int t;
      if (GameScr.VecInfoServer.size() > 0) {
         if (this.strInfoServer == null) {
            this.strInfoServer = (String)GameScr.VecInfoServer.elementAt(0);
            t = GameScr.VecInfoServer.size();
            if (t < 2) {
               this.speedInfo = 4;
            } else if (t < 5) {
               this.speedInfo = 5;
            } else {
               this.speedInfo = 6;
            }

            this.xPaintInfo = 0;
            this.xmaxInfo = mFont.tahoma_7_white.getWidth(this.strInfoServer) + GameCanvas.w;
         } else {
            if (this.xPaintInfo >= this.xmaxInfo) {
               ++this.timepaintServer;
               this.timepaintServer = 0;
               this.strInfoServer = null;
               GameScr.VecInfoServer.removeElementAt(0);
            }

            this.xPaintInfo += this.speedInfo;
         }
      }

      if (GameScr.VecInfoChar.size() > 0) {
         if (this.strInfoCharServer == null) {
            this.strInfoCharServer = (String)GameScr.VecInfoChar.elementAt(0);
            t = GameScr.VecInfoChar.size();
            this.yBeginInfo = 0;
            if (t < 2) {
               this.speedInfoChar = 4;
            } else if (t < 5) {
               this.speedInfoChar = 5;
            } else {
               this.speedInfoChar = 6;
            }

            this.xPaintInfoChar = 0;
            this.xmaxInfoChar = mFont.tahoma_7b_white.getWidth(this.strInfoCharServer) + GameCanvas.w - 50;
         } else {
            if (this.xPaintInfoChar >= this.xmaxInfoChar) {
               this.timePaintInfoChar = 0;
               this.strInfoCharServer = null;
               GameScr.VecInfoChar.removeElementAt(0);
            }

            this.xPaintInfoChar += this.speedInfoChar;
         }
      }

      if (this.text != null) {
         if (this.yshow > 0) {
            this.yshow -= 2;
         }

         ++this.time;
         if (this.time > 120) {
            this.time = 0;
            this.text = null;
         }
      }

   }
}
