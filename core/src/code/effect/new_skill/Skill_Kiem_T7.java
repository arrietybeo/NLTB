package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Char;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Kiem_T7 extends Effect {
   public short yfirst;
   public short vy;
   public short id1;
   public short id2;
   public byte frame;
   public byte[] arr_frame = new byte[]{0, 1, 2, 3, 4, 5};
   public short[] arr_radian = new short[]{0, 90, 180, 270};
   public boolean star;
   Me_Arrow e = null;

   public Skill_Kiem_T7(short xto, short yto) {
      this.x = xto;
      this.y = yto;
      this.yfirst = yto;
      this.vy = 30;
      this.id1 = 36;
      this.id2 = 34;
   }

   public Skill_Kiem_T7(short xto, short yto, Char c) {
      this.x = xto;
      this.y = yto;
      this.yfirst = yto;
      this.vy = 30;
      this.id1 = 36;
      this.id2 = 34;
      this.Owner = c;
   }

   public void paint(mGraphics g) {
      if (this.star) {
         Image img = EffectSkill.getImage(this.id2);
         if (img != null) {
            for(int i = 0; i < this.arr_radian.length; ++i) {
               g.drawRegion(img, 0, 0, EffectSkill.getWidth(this.id2), EffectSkill.getHight(this.id2), 3, Util.cos(this.arr_radian[i]) * 30 / 1024 + this.x, Util.sin(this.arr_radian[i]) * 30 / 1024 + this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
            }

            g.drawRegion(img, 0, 0, EffectSkill.getWidth(this.id2), EffectSkill.getHight(this.id2), 3, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void update() {
      ++this.frame;
      if (GameCanvas.gameTick % 10 == 0) {
         this.star = true;
         this.frame = 0;
         EffectSkill.createLowEfect(this.x, this.yfirst, 0);
      }

      if (this.star) {
         this.y -= this.vy;
      }

      ++this.vy;
      if (this.y < GameScr.cmy - EffectSkill.getHight(this.id2) * 4) {
         this.star = false;
         this.vy = 0;
         this.e = new Me_Arrow((short)this.x, (short)this.y, (short)this.x, this.yfirst, (byte)this.id2, (byte)115, (byte)0, (byte)30);
         this.e.setOwner(this.Owner);
         this.e.setDame(this.dame);
         EffectManager.addHiEffect(this.e);

         for(int i = 0; i < this.arr_radian.length; ++i) {
            this.e = new Me_Arrow((short)(Util.cos(this.arr_radian[i]) * 20 / 1024 + this.x), (short)(Util.sin(this.arr_radian[i]) * 20 / 1024 + this.y - 60 * i), (short)(Util.cos(this.arr_radian[i]) * 20 / 1024 + this.x), (short)(Util.sin(this.arr_radian[i]) * 20 / 1024 + this.yfirst), (byte)this.id2, (byte)27, (byte)0, (byte)35);
            this.e.setOwner(this.Owner);
            this.e.setDame(this.dame);
            EffectManager.addHiEffect(this.e);
            this.e = null;
         }

         this.wantDestroy = true;
      }

   }
}
