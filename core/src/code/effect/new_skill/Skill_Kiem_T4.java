package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Kiem_T4 extends Effect {
   public int angle;
   public int dx;
   public int dy;
   public int time;
   public int pos;
   public int xto;
   public int yto;
   public int id;
   public int[] yyy = new int[]{50, 80, 100, 70, 40, 60};
   public int[] xxx = new int[]{0, 50, 40, 20, 10, 30, 60};
   public short R;
   public short count;
   public short r1;
   public short r2;
   byte f;
   byte loop;
   private int[] xw;
   private int[] yw;
   short[] posx = new short[]{-20, 0, 20};
   short[] posy = new short[]{0, 20, 0};
   boolean ispaint;
   short xef;
   short yef;

   public Skill_Kiem_T4(short x, short y, short xto, short yto) {
      this.type = 8;
      this.xto = xto;
      this.yto = yto;
      this.xef = x;
      this.yef = (short)(y - 25);
      this.x = xto - 60;
      this.y = yto - 100 - this.yyy[Math.abs(GameCanvas.r.nextInt() % this.yyy.length)];
      this.setspeed();
      EffectSkill.createLowEfect(xto, yto, 45);
      this.time = 5;
      this.id = 35;
   }

   public Skill_Kiem_T4(short x, short y, short xto, short yto, Actor c, Actor target) {
      this.type = 8;
      this.xto = xto;
      this.yto = yto;
      this.xef = x;
      this.yef = (short)(y - 25);
      this.x = xto - 60;
      this.y = yto - 100 - this.yyy[Math.abs(GameCanvas.r.nextInt() % this.yyy.length)];
      this.setspeed();
      EffectSkill.createLowEfect(xto, yto, 45);
      this.time = 5;
      this.id = 35;
      this.Owner = c;
      this.target = target;
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

   public void paint(mGraphics g) {
      Image imgk;
      if (!this.ispaint) {
         imgk = EffectSkill.getImage(45);
         if (imgk != null) {
            g.drawRegion(imgk, 0, this.f * EffectSkill.getHight(45), EffectSkill.getWidth(45), EffectSkill.getHight(45), 0, this.xef, this.yef, 3, false);
         }

         for(int i = 0; i < 3; ++i) {
            Image img = EffectSkill.getImage(64);
            if (img != null) {
               g.drawRegion(img, 0, this.f * EffectSkill.getHight(64), EffectSkill.getWidth(64), EffectSkill.getHight(64), 0, this.xef + this.posx[i], this.yef + this.posy[i], 3, false);
            }
         }
      } else {
         imgk = EffectSkill.getImage(this.id);
         if (imgk != null) {
            g.drawImage(imgk, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void createsa0(int x, int y) {
      Effect_Sao_chop sc = new Effect_Sao_chop(x, y, 23);
      EffectManager.addHiEffect(sc);
   }

   public void update() {
      ++this.f;
      if (this.f >= 3) {
         this.f = 0;
         ++this.count;
      }

      if (this.count >= 3) {
         this.ispaint = true;
         if (this.pos < this.xw.length) {
            ++this.pos;
            if (this.pos < this.xw.length && this.pos > 1) {
               EffectSkill.createHiEfect(this.xw[this.pos], this.yw[this.pos], 64);
            }
         }

         if (this.pos >= this.xw.length) {
            this.pos = this.xw.length - 1;
            this.xw[this.pos] = this.xto;
            this.yw[this.pos] = this.yto;
            this.wantDestroy = true;

            for(int i = 0; i < 5; ++i) {
               this.createsa0(this.xto, this.yto);
            }

            EffectSkill.createLowEfect(this.xw[this.pos] + 7, this.yw[this.pos] + 10, 4);
            EffectSkill.createLowEfect(this.xw[this.pos], this.yw[this.pos], 3);
            Effect_dien e = new Effect_dien(this.xw[this.pos], this.yw[this.pos], 6, 50);
            EffectManager.addLowEffect(e);
            if (this.dame > 0) {
               this.startFlyText(this.dame, 0, this.target.x, this.target.y, 0, 0, this.Owner.x > this.target.x ? -1 : 1);
            }

            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            }
         }
      }

   }
}
