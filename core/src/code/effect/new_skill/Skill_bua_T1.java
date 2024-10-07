package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_bua_T1 extends Effect {
   public int angle;
   public int frame = 0;
   public int pos;
   public int id1;
   public int id2;
   public int f;
   public short xTo;
   public short yTo;
   public short dx;
   public short dy;
   public short vx;
   public short vy;
   public short va;
   public short himg;
   private byte time;
   boolean isMatchX;
   boolean isMatchY;
   int dx1;
   int dy1;

   public Skill_bua_T1(short x, short y, Actor target, int time) {
      this.time = (byte)time;
      this.x = x;
      this.y = y;
      this.target = target;
      this.va = 15;
      this.id1 = 25;
      this.id2 = 26;
      EffectSkill.createLowEfect(x, y, 67);
   }

   public void paint(mGraphics g) {
      if (this.time < 0) {
         Image img = EffectSkill.getImage(this.id1);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id1), EffectSkill.getWidth(this.id1), EffectSkill.getHight(this.id1), 0, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }

         Image img2 = EffectSkill.getImage(this.id2);
         if (img2 != null) {
            g.drawRegion(img2, 0, this.f * EffectSkill.getHight(this.id2), EffectSkill.getWidth(this.id2), EffectSkill.getHight(this.id2), 0, this.x, this.y + EffectSkill.getHight(this.id1) / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }

      }
   }

   public void update() {
      if (this.time >= 0) {
         --this.time;
      }

      if (this.time < 0) {
         this.f = (this.f + 1) % 3;
         this.frame = (this.frame + 1) % 4;
         if (this.target != null) {
            this.xTo = this.target.x;
            this.yTo = this.target.y;
         } else {
            this.wantDestroy = true;
         }

         this.dx = (short)(this.xTo - this.x);
         this.dy = (short)(this.yTo - (this.himg >> 1) - this.y);
         this.angle = Util.angle(this.dx, this.dy);
         this.vx = (short)(this.va * Util.cos(this.angle) >> 10);
         this.vy = (short)(this.va * Util.sin(this.angle) >> 10);
         this.x += this.vx;
         this.y += this.vy;
         this.dx1 = Math.abs(this.x - this.xTo);
         this.dy1 = Math.abs(this.y - this.yTo);
         if (this.dx1 <= this.vx) {
            this.x = this.xTo;
            this.isMatchX = true;
         }

         if (this.dy1 <= this.vy) {
            this.y = this.yTo;
            this.isMatchY = true;
         }

         if (this.target.catagory == 1 && (this.target.getState() == 1 || this.target.getState() == 5)) {
            this.isMatchX = true;
            this.isMatchY = true;
         }

         if (this.isMatchX && this.isMatchY) {
            this.x = this.xTo;
            this.y = this.yTo;
            this.wantDestroy = true;
            if (this.dame == 0) {
               this.isDame0 = true;
            }

            this.startFlyText(this.dame, 0, this.xTo, this.yTo, 0, 0, this.Owner.x > this.xTo ? -1 : 1);
            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            } else {
               this.target.jumpVang(this.Owner);
            }

            EffectManager.addHiDataeffectSkill(136, this.xTo, this.yTo, 1);
         }

      }
   }
}
