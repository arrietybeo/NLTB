package code.screen.screen;

import code.main.GameCanvas;
import code.model.ReadMessenge;
import code.model.T;
import lib.mGraphics;
import lib2.mFont;

public class LoadingScr extends ScreenTeam {
   private int f;
   private int f1;
   private int f2;
   private int f3;
   private int f4;
   private int f5;
   private int fr1;
   private int fr2;
   private int fr3;
   private int fr4;
   private int fr5;
   public boolean isFinish = false;

   public void paint(mGraphics g) {
      g.setColor(0);
      g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
      g.drawImage(GameCanvas.getLogo(), GameCanvas.hw, GameCanvas.hh - 25, 3, false);
      FontTeam.numberSmall_yeallow.drawString(g, GameScr.version, GameCanvas.hw + mGraphics.getImageWidth(GameCanvas.getLogo()) / 2, GameCanvas.hh, 0, false);
      g.drawImage(ChangScr.loading1[this.f1], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(ChangScr.loading2[this.f2], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(ChangScr.loading3[this.f3], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(ChangScr.loading4[this.f4], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      g.drawImage(ChangScr.loading5[this.f5], GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 10, 3, false);
      if (ReadMessenge.allImage > 0) {
         mFont.tahoma_7_white.drawString(g, T.taidata + ": " + ReadMessenge.totalImg * 100 / ReadMessenge.allImage + "%", GameCanvas.w / 2, GameCanvas.h / 2 + mGraphics.getImageHeight(GameCanvas.getLogo()) / 2 + 40, 2, false);
      }

   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void update() {
      this.fr1 = (this.fr1 + 1) % ChangScr.frame1.length;
      this.fr2 = (this.fr2 + 1) % ChangScr.frame2.length;
      this.fr3 = (this.fr3 + 1) % ChangScr.frame3.length;
      this.fr4 = (this.fr4 + 1) % ChangScr.frame4.length;
      this.fr5 = (this.fr5 + 1) % ChangScr.frame5.length;
      this.f1 = ChangScr.frame1[this.fr1];
      this.f2 = ChangScr.frame2[this.fr2];
      this.f3 = ChangScr.frame3[this.fr3];
      this.f4 = ChangScr.frame4[this.fr4];
      this.f5 = ChangScr.frame5[this.fr5];
      if (this.isFinish) {
         this.lastSCR.switchToMe();
      }

   }
}
