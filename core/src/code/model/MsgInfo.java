package code.model;

import code.main.GameCanvas;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mSystem;

public class MsgInfo {
   public String message;
   public String[] arr;
   int x;
   int w;
   long timeRemove = -1L;

   public MsgInfo(String message) {
      this.message = message;
      this.arr = FontTeam.arialFontW.splitFontBStrInLine(message, GameCanvas.w - 8);
      this.w = FontTeam.arialFontW.getWidth(this.arr[0]);
      this.x = GameCanvas.w + this.w / 2;
   }

   public void paint(mGraphics g) {
   }

   public void update() {
      if (this.x != GameCanvas.w / 2) {
         if (GameCanvas.w / 2 - this.x >> 1 == 0) {
            this.x = GameCanvas.w / 2;
         } else {
            this.x += GameCanvas.w / 2 - this.x >> 1;
         }
      }

      if (this.x == GameCanvas.w / 2) {
         if (this.timeRemove == -1L) {
            this.timeRemove = mSystem.currentTimeMillis() / 1000L + 6L;
         }

         if (this.timeRemove - mSystem.currentTimeMillis() / 1000L <= 0L) {
            GameScr.msgWorld.removeElement(this);
         }
      }

   }
}
