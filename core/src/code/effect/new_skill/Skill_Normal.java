package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import code.screen.screen.GameScr;

public class Skill_Normal extends Effect {
   private int dx;
   private int dy;
   private int range;
   private int IdFire;
   private Char mychar;
   private boolean canFire;
   private boolean isAddkill;
   private int vx;
   private int vy;
   private int angle;
   private int va;
   private boolean isAttack;

   public Skill_Normal(Char c, Actor target) {
      this.x = c.x;
      this.y = c.y;
      this.mychar = c;
      this.target = target;
      if (c.clazz == Char.NGU_DOC) {
         this.range = 150;
      } else {
         this.range = 60;
      }

      this.va = 15;
      this.mychar.dir = Util.findDirection(this.mychar, target);
      this.isAttack = true;
      if (GameScr.isIntro) {
         this.isAttack = false;
      }

      if (target != null && GameScr.isIntro && this.mychar.state != 3 && this.mychar.state != 6) {
         this.mychar.p1 = 0;
         this.mychar.state = 6;
      }

      if (!GameScr.isIntro) {
         this.mychar.p1 = 0;
         this.mychar.state = 7;
      }

   }

   private int getIDEffect(int clazz, int frame) {
      if (clazz == Char.VO_DANG && frame == 13) {
         return 136;
      } else if (clazz == Char.NGA_MI && frame == 3) {
         return 134;
      } else if (clazz == Char.NGU_DOC && frame == 1) {
         return 2;
      } else if (clazz == Char.CAI_BANG && frame == 4) {
         return 135;
      } else {
         return clazz == Char.THIEU_LAM && frame == 10 ? 59 : -1;
      }
   }

   private void updateva() {
      if (this.mychar.state == 3) {
         this.wantDestroy = true;
         this.target = null;
      } else if (GameScr.isIntro && this.mychar.isresetSkill) {
         this.wantDestroy = true;
         this.target = null;
      } else {
         if (this.target != null) {
            if (!GameScr.isIntro) {
               if (this.canFire) {
                  if (this.IdFire == 2) {
                     if (this.mychar.p1 == 3) {
                        GameScr.addEffectByDir(this.mychar.getDir(), this.mychar.clazz, this.mychar);
                        this.wantDestroy = true;
                     }

                     if (!this.isAddkill) {
                        if (this.dame == 0) {
                           this.isDame0 = true;
                        }

                        GameCanvas.gameScr.StartNewBaburan(80, this.mychar, this.target, this.mychar.x, this.mychar.y - 40, this.dame, (byte)0, 80);
                        this.isAddkill = true;
                     }
                  } else {
                     GameScr.addEffectByDir(this.mychar.getDir(), this.mychar.clazz, this.mychar);
                     this.target.setMove2Target(this.mychar);
                     EffectManager.addHiDataeffectSkill(this.IdFire, this.target.x, this.target.y - this.target.getHeight() / 2, 1);
                     this.wantDestroy = true;
                     this.startFlyText(this.dame, 0, this.target.x, this.target.y - this.target.getHeight() / 2, 0, 0, this.mychar.x > this.target.x ? -1 : 1);
                     if (this.dame == 0) {
                        this.isDame0 = true;
                     }

                     this.mychar.resetSkill();
                     if (this.target.getHp() <= 0) {
                        LiveActor.startDeadFly(this.target, this.mychar.ID);
                     } else {
                        this.target.jumpVang(this.mychar);
                     }
                  }
               }

               if (this.getIDEffect(this.mychar.clazz, this.mychar.p1) != -1) {
                  this.IdFire = this.getIDEffect(this.mychar.clazz, this.mychar.p1);
                  this.canFire = true;
               }
            } else {
               if (Util.distance(this.mychar.x, this.mychar.y, this.target.x, this.target.y) < this.range || this.isAttack) {
                  this.isAttack = true;
                  this.mychar.xTo = this.mychar.x;
                  this.mychar.yTo = this.mychar.y;
                  this.vx = 0;
                  this.vy = 0;
                  if (this.mychar.state != 7) {
                     this.mychar.p1 = 0;
                     this.mychar.state = 7;
                     this.mychar.xTo = this.target.x;
                     this.mychar.yTo = this.target.y;
                  }

                  if (this.canFire) {
                     this.target.setMove(true);
                     if (this.IdFire == 2) {
                        if (this.mychar.p1 == 3) {
                           GameScr.addEffectByDir(this.mychar.getDir(), this.mychar.clazz, this.mychar);
                           this.wantDestroy = true;
                        }

                        if (!this.isAddkill) {
                           GameCanvas.gameScr.StartNewBaburan(80, this.mychar, this.target, this.mychar.x, this.mychar.y - 40, this.dame, (byte)0, 80);
                           this.isAddkill = true;
                        }
                     } else {
                        GameScr.addEffectByDir(this.mychar.getDir(), this.mychar.clazz, this.mychar);
                        this.target.setMove2Target(this.mychar);
                        this.target.jumpVang(this.mychar);
                        EffectManager.addHiDataeffectSkill(this.IdFire, this.target.x, this.target.y - this.target.getHeight() / 2, 1);
                        this.wantDestroy = true;
                        this.startFlyText(this.dame, 0, this.target.x, this.target.y - 40, 0, 0);
                        this.mychar.resetSkill();
                        if (this.target.getHp() <= 0) {
                           LiveActor.startDeadFly(this.target, this.mychar.ID);
                        }
                     }
                  }

                  if (this.getIDEffect(this.mychar.clazz, this.mychar.p1) != -1) {
                     this.IdFire = this.getIDEffect(this.mychar.clazz, this.mychar.p1);
                     this.canFire = true;
                  }
               }

               if (!this.isAttack) {
                  this.dx = this.target.x - this.x;
                  this.dy = this.target.y - this.y;
                  this.angle = Util.angle(this.dx, this.dy);
                  this.vx = this.va * Util.cos(this.angle) >> 10;
                  this.vy = this.va * Util.sin(this.angle) >> 10;
                  this.mychar.dir = Util.findDirection(this.mychar, this.target);
                  this.mychar.p1 = 0;
                  this.mychar.state = 6;
                  this.mychar.xTo = this.target.x;
                  this.mychar.yTo = this.target.y;
                  Char var10000 = this.mychar;
                  var10000.x = (short)(var10000.x + this.vx);
                  var10000 = this.mychar;
                  var10000.y = (short)(var10000.y + this.vy);
                  this.mychar.addEffectWhenCharRun();
               }
            }
         } else {
            if (GameScr.isIntro) {
               this.vx = 0;
               this.vy = 0;
               this.mychar.xTo = this.mychar.x;
               this.mychar.yTo = this.mychar.y;
            }

            this.mychar.state = 0;
            this.wantDestroy = true;
         }

      }
   }

   public void update() {
      this.updateva();
   }
}
