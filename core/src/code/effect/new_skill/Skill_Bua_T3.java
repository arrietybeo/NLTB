package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Bua_T3 extends Effect {
   public int dx;
   public int dy;
   public int angle;
   public int pos;
   public int frame = 3;
   public int xTo;
   public int yTo;
   public int idimg;
   public int count;
   public boolean ispaint;
   public short va;
   public short himg;
   public short vx;
   public short vy;
   boolean isMatchX;
   boolean isMatchY;
   int dx1;
   int dy1;

   public Skill_Bua_T3(short x, short y, Actor target) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xTo = target.x;
      this.yTo = target.y;
      this.idimg = 16;
      this.va = 15;
      this.yTo += 5;
   }

   public void createEfect(int x, int y, int type) {
      EffectSkill u = new EffectSkill(x, y, type, 4);
      EffectManager.addLowEffect(u);
   }

   public void paint(mGraphics g) {
      if (this.ispaint) {
         Image img = EffectSkill.getImage(this.idimg);
         if (img != null) {
            try {
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xTo + 15, this.yTo - 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xTo - 15, this.yTo - 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xTo, this.yTo + 10 - 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.xTo, this.yTo - 10 - 5, mGraphics.VCENTER | mGraphics.HCENTER, false);
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }
      }

   }

   public void update() {
      if (this.target != null) {
         this.xTo = this.target.x;
         this.yTo = this.target.y;
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
         this.x = (short)this.xTo;
         this.isMatchX = true;
      }

      if (this.dy1 < this.vy) {
         this.y = (short)this.yTo;
         this.isMatchY = true;
      }

      if (this.target.catagory == 1 && (this.target.getState() == 1 || this.target.getState() == 5)) {
         this.isMatchX = true;
         this.isMatchY = true;
      }

      if (this.isMatchX && this.isMatchY) {
         this.x = this.xTo;
         this.y = this.yTo;
         if (this.count < 1) {
            this.createEfect(this.x, this.y, 54);
         }

         ++this.count;
         this.ispaint = true;
      }

      if (this.count == 5) {
         this.wantDestroy = true;
         if (this.dame == 0) {
            this.isDame0 = true;
         }

         this.startFlyText(this.dame, 0, this.target.x, this.target.y, 0, 0, this.Owner.x > this.target.x ? -1 : 1);
         if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
         } else {
            this.target.jumpVang(this.Owner);
         }
      }

      if (this.x != this.xTo && this.y != this.yTo) {
         this.createEfect(this.x, this.y, 19);
         this.createEfect(this.x, this.y, 16);
      }

   }
}
