package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.screen.screen.GameScr;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Bua_T2 extends Effect {
   public int xto;
   public int yto;
   public int frame;
   public int vy;
   public int y2;
   public int count;
   public int id;
   public short[] time = new short[]{5, 10, 15, 20};
   public short[] ra = new short[]{60, 120, 180, 240, 300, 360};
   public short[] ra2 = new short[]{30, 90, 150, 210, 270, 330};
   short T;
   public boolean roi;
   public short R;
   public short r1;
   public short r2;
   byte f;
   byte loop;
   public short xef;
   public short yef;

   public Skill_Bua_T2(short x, short y, Actor target) {
      this.xef = x;
      this.yef = (short)(y - 10);
      this.R = 40;
      this.r1 = (short)(this.R - 10);
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.x = target.x;
      this.y = target.y;
      this.vy = -25;
      this.id = 15;
      EffectSkill.createLowEfect(x, y - 10, 59);
      this.T = this.time[Math.abs(GameScr.r.nextInt() % this.time.length)];
   }

   public void paint(mGraphics g) {
      if (this.T < 0) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawRegion(img, 0, 0, EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void update() {
      if (this.T >= 0) {
         --this.T;
      }

      if (this.T < 0) {
         this.y += this.vy;
         if (this.y < this.yto - 250) {
            this.vy = 20;
            this.roi = true;
         }

         if (this.roi) {
            this.vy += 8;
         }

         if (this.y >= this.yto) {
            this.y = this.yto - EffectSkill.getHight(this.id) / 2 - 5;
            ++this.count;
            if (this.count == 1) {
               EffectSkill.createLowEfect(this.xto, this.yto, 55);
               EffectSkill.createLowEfect(this.xto, this.yto - 5, 59);
            }

            if (this.count == 2) {
               EffectSkill.createHiEfect(this.xto, this.yto, 30);
               this.wantDestroy = true;
            }
         }
      }

   }
}
