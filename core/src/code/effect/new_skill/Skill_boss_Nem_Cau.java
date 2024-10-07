package code.effect.new_skill;

import code.model.Actor;
import code.model.DataSkillEff;
import code.model.Effect;
import code.model.LiveActor;
import code.screen.Util;
import code.screen.Utils;
import lib.mGraphics;

public class Skill_boss_Nem_Cau extends Effect {
   public byte frame;
   DataSkillEff eff;
   int va;
   int vx;
   int vy;
   int angle;
   int x;
   int y;
   int type;
   int dx;
   int dy;
   int xTo;
   int yTo;
   int himg;

   public Skill_boss_Nem_Cau(short x, short y, Actor target) {
      this.target = target;
      this.x = x;
      this.y = y;
      this.xTo = target.x;
      this.yTo = target.y;
      this.angle = Util.angle(this.x - target.x, target.y - (target.getHeight() >> 1) - this.y);
      this.va = 24;
      this.vx = this.va * Util.cos(this.angle) >> 10;
      this.vy = this.va * Util.sin(this.angle) >> 10;
      this.himg = target.getHeight();
      this.eff = new DataSkillEff(232, x, y, -1L);
      this.eff.isEffAuto = true;
   }

   public void paint(mGraphics g) {
      if (this.eff != null) {
         this.eff.paint2(g);
      }

      g.setColor(16777215);
      g.fillRect(this.x, this.y, 32, 32, false);
   }

   public void update() {
      try {
         if (this.target != null) {
            this.xTo = this.target.x;
            this.yTo = this.target.y;
         } else {
            this.wantDestroy = true;
         }

         this.dx = (short)(this.xTo - this.x);
         this.dy = (short)(this.yTo - this.y);
         this.angle = (short)Util.angle(this.dx, this.dy);
         short vx = (short)(this.va * Util.cos(this.angle) >> 10);
         short vy = (short)(this.va * Util.sin(this.angle) >> 10);
         this.x += vx;
         this.y += vy;
         short dx1 = (short)Math.abs(this.x - this.xTo);
         short dy1 = (short)Math.abs(this.y - this.yTo);
         boolean isMatchX = false;
         boolean isMatchY = false;
         if (this.target.catagory == 1 && (this.target.getState() == 1 || this.target.getState() == 5)) {
            isMatchX = true;
            isMatchY = true;
         }

         int disx = Utils.getDistance(this.x, this.y, this.xTo, this.yTo);
         if (dx1 <= vx) {
            this.x = (short)this.xTo;
            isMatchX = true;
         }

         if (dy1 <= vy) {
            this.y = (short)this.yTo;
            isMatchY = true;
         }

         if (isMatchX && isMatchY || disx < 20) {
            if (this.target.getHp() <= 0) {
               LiveActor.startDeadFly(this.target, this.Owner.ID);
            }

            this.wantDestroy = true;
            if (this.eff != null) {
               this.eff = null;
            }

            if (this.target != null) {
               EffectSkill.createHiEfect(this.x, this.y, 24);
               this.target.jum();
               DataSkillEff eff = new DataSkillEff(233, this.x, this.y, 0L);
               eff.x = this.target.x;
               eff.y = this.target.y;
               this.target.addEffectSkill(eff, this.target.x, this.target.y);
            }
         }

         if (this.eff != null) {
            this.eff.x = (short)this.x;
            this.eff.y = (short)this.y;
            this.eff.update();
         }

         if (this.wantDestroy) {
            if (this.dame == 0) {
               this.isDame0 = true;
            }

            this.startFlyText(this.dame, 0, this.xTo, this.yTo, 0, 0, this.Owner.x > this.xTo ? -1 : 1);
         }
      } catch (Exception var9) {
         this.wantDestroy = true;
         if (this.eff != null) {
            this.eff = null;
         }
      }

   }
}
