package code.effect.new_skill;

import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Dao_T1 extends Effect {
   public int angle;
   public int xto;
   public int yto;
   public int pos;
   public int thongsolathinh = 0;
   private int[] xw;
   private int[] yw;
   public int dx;
   public int dy;
   byte frame;
   private byte typeEnd = 0;

   public void setTypeEnd(int type) {
      this.typeEnd = (byte)type;
   }

   public Skill_Dao_T1(short x, short y, short xto, short yto, int idarrow) {
      this.x = x;
      this.y = y;
      this.xto = xto;
      this.yto = yto;
      this.type = 4;
      this.setspeed();
      this.setangle();
      this.idarrow = (short)idarrow;
   }

   public void setspeed() {
      this.dx = this.xto - this.x;
      this.dy = this.yto - 0 - this.y;
      this.angle = Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 30;
      if (nPosition < 2) {
         nPosition = 2;
      }

      this.xw = new int[nPosition];
      this.yw = new int[nPosition];

      for(int i = 0; i < nPosition; ++i) {
         this.xw[i] = this.x + i * this.dx / nPosition;
         this.yw[i] = this.y + i * this.dy / nPosition;
      }

   }

   public void setangle() {
      if (this.angle >= 0 && this.angle < 30 || this.angle >= 330 && this.angle <= 360) {
         this.thongsolathinh = 0;
         this.frame = 0;
      }

      if (this.angle >= 30 && this.angle < 60) {
         this.thongsolathinh = 0;
         this.frame = 1;
      }

      if (this.angle >= 60 && this.angle < 120) {
         this.thongsolathinh = 5;
         this.frame = 0;
      }

      if (this.angle >= 120 && this.angle < 150) {
         this.thongsolathinh = 5;
         this.frame = 1;
      }

      if (this.angle >= 150 && this.angle < 210) {
         this.thongsolathinh = 3;
         this.frame = 0;
      }

      if (this.angle >= 210 && this.angle < 240) {
         this.thongsolathinh = 3;
         this.frame = 1;
      }

      if (this.angle >= 240 && this.angle < 300) {
         this.thongsolathinh = 6;
         this.frame = 0;
      }

      if (this.angle >= 300 && this.angle < 330) {
         this.thongsolathinh = 6;
         this.frame = 1;
      }

   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idarrow);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * mGraphics.getImageHeight(img) / 2, mGraphics.getImageWidth(img), mGraphics.getImageHeight(img) / 2, this.thongsolathinh, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void update() {
      if (this.pos < this.xw.length) {
         ++this.pos;
      }

      if (this.pos >= this.xw.length) {
         this.pos = this.xw.length - 1;
         this.xw[this.pos] = this.xto;
         this.yw[this.pos] = this.yto;
         this.wantDestroy = true;
         if (this.dame == 0) {
            this.isDame0 = true;
         }

         this.startFlyText(this.dame, 0, this.xto, this.yto, 0, 0, this.Owner.x > this.xto ? -1 : 1);
         if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
         } else {
            this.target.jumpVang(this.Owner);
         }

         if (this.typeEnd == 0) {
            EffectManager.addHiDataeffectSkill(59, this.xto, this.yto, 1);
         } else {
            EffectManager.addHiDataeffectSkill(137, this.xto, this.yto, 1);
         }
      }

   }
}
