package code.model;

import code.main.GameCanvas;
import lib.mGraphics;

public class Npc_Server extends NPC {
   public short idImg;
   public byte nFrame;
   public byte index;

   public Npc_Server() {
      this.npcType = 1;
      this.catagory = 2;
   }

   public void paint(mGraphics g) {
   }

   public void paintCorner(mGraphics g, int xCorner, int yCorner) {
   }

   public void update() {
      if (this.typeLimit != 1) {
         super.update();
      } else {
         if (GameCanvas.gameTick % 4 == 0) {
            ++this.index;
         }

         if (this.index >= 2) {
            this.index = 0;
         }
      }

   }

   public void setPosTo(short x, short y) {
   }

   public int getLimxL() {
      return this.x - this.width / 2 + 10;
   }

   public int getLimxR() {
      return this.x + this.width / 2 - 10;
   }

   public int getLimyU() {
      return this.y - this.height * 3 / 4 - 30;
   }

   public int getLimyD() {
      return this.y - 40;
   }

   public boolean isNPC() {
      return true;
   }

   public int getNpcType() {
      return this.npcType;
   }
}
