package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Skill_Dao_T6 extends Effect {
   public int dx;
   public int dy;
   public int angle;
   public int pos;
   public int frame = 3;
   public int xTo;
   public int yTo;
   public int idimg;
   public short va;
   public short himg;
   public short vx;
   public short vy;
   boolean isMatchX;
   boolean isMatchY;
   int dx1;
   int dy1;

   public Skill_Dao_T6(short x, short y, Actor target) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xTo = target.x;
      this.yTo = target.y;
      this.idimg = 70;
      this.va = 10;
      this.yTo += 5;
      EffectSkill.createLowEfect(x, y, 66);
   }

   public Skill_Dao_T6(short x, short y, Actor c, Actor target) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xTo = target.x;
      this.yTo = target.y;
      this.idimg = 70;
      this.va = 10;
      this.yTo += 5;
      EffectSkill.createLowEfect(x, y, 66);
      this.Owner = c;
   }

   public void paint(mGraphics g) {
      Image img = EffectSkill.getImage(this.idimg);
      if (img != null) {
         g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.idimg), EffectSkill.getWidth(this.idimg), EffectSkill.getHight(this.idimg), 0, this.x, this.y, 3, false);
      }

   }

   public void update() {
      ++this.frame;
      if (this.frame >= 3) {
         this.frame = 0;
      }

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

      if (this.dy1 <= this.vy) {
         this.y = (short)this.yTo;
         this.isMatchY = true;
      }

      if (this.isMatchX && this.isMatchY) {
         EffectManager.addHiDataeffectSkill(75, this.x, this.y, 1);
         EffectManager.addHiDataeffectSkill(238, this.x, this.y, 1);
         if (this.dame > 0) {
            this.startFlyText(this.dame, 0, this.x, this.y, 0, 0, this.Owner.x > this.target.x ? -1 : 1);
         }

         if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
         }

         this.wantDestroy = true;
      }

      if (this.x != this.xTo && this.y != this.yTo && GameCanvas.gameTick % 2 == 0) {
         EffectSkill.createLowEfect(this.x, this.y - 5, 73);
      }

   }
}
