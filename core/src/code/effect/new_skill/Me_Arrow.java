package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.model.arrow.Arrow;
import code.screen.Util;
import code.screen.Utils;
import javax.microedition.lcdui.Image;
import lib.mGraphics;

public class Me_Arrow extends Effect {
   public int transform;
   public int[] xw;
   public int[] yw;
   public byte frame;
   public byte frame1;
   public byte type;
   public byte effect;
   public byte Position;
   public short dx;
   public short dy;
   public short angle;
   public short xto;
   public short yto;
   public short pos;
   public short thongsolathinh;
   public short yadd;
   private byte typeEffect;

   public Me_Arrow() {
   }

   public Me_Arrow(short x, short y, Actor target, byte type, byte effect, byte lathinh, byte Position) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.Position = Position;
      this.type = type;
      this.effect = effect;
      this.thongsolathinh = lathinh;
      if (type == 46) {
         this.transform = lathinh;
      }

      this.setspeed(Position);
   }

   public Me_Arrow(short x, short y, Actor target, byte type, byte effect, byte lathinh, byte Position, int dame, Actor owner) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.Position = Position;
      this.type = type;
      this.effect = effect;
      this.thongsolathinh = lathinh;
      if (type == 46) {
         this.transform = lathinh;
      }

      this.setspeed(Position);
      this.dame = dame;
      this.Owner = owner;
   }

   public Me_Arrow(short x, short y, Actor target, byte type, byte effect, byte lathinh, byte Position, int yadd) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.xto = target.x;
      this.yto = target.y;
      this.Position = Position;
      this.type = type;
      this.effect = effect;
      this.thongsolathinh = lathinh;
      if (type == 46) {
         this.transform = lathinh;
      }

      this.yadd = (short)yadd;
      this.setspeed(Position);
   }

   public Me_Arrow(short x, short y, short xto, short yto, byte type, byte effect, byte lathinh, byte Position) {
      this.x = x;
      this.y = y;
      this.xto = xto;
      this.yto = yto;
      this.Position = Position;
      this.type = type;
      this.effect = effect;
      this.thongsolathinh = lathinh;
      if (type == 46) {
         this.transform = lathinh;
      }

      this.setspeed(Position);
   }

   public Me_Arrow(short x, short y, Actor target, byte type, byte typeEffect) {
      this.x = x;
      this.y = y;
      this.target = target;
      this.type = type;
      this.effect = -10;
      this.typeEffect = typeEffect;
   }

   public void setspeed(byte Positions) {
      this.dx = (short)(this.xto - this.x);
      this.dy = (short)(this.yto - 0 - this.y);
      this.angle = (short)Util.angle(this.dx, this.dy);
      int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / this.Position;
      if (nPosition < 2) {
         nPosition = 2;
      }

      this.xw = new int[nPosition];
      this.yw = new int[nPosition];

      int d;
      for(d = 0; d < nPosition; ++d) {
         this.xw[d] = this.x + d * this.dx / nPosition;
         this.yw[d] = this.y + d * this.dy / nPosition;
      }

      if (this.type == 2 || this.type == 71) {
         d = Arrow.findDirIndexFromAngle(Util.angle(this.dx, -this.dy));
         this.frame1 = Arrow.FRAME[d];
         this.transform = Arrow.TRANSFORM[d];
      }

   }

   public void paint(mGraphics g) {
      if (this.type != 100) {
         Image img;
         if (this.type != 71 && this.type != 2) {
            img = EffectSkill.getImage(this.type);
            if (img != null) {
               g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.type), EffectSkill.getWidth(this.type), EffectSkill.getHight(this.type), this.thongsolathinh, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
            }
         } else {
            img = EffectSkill.getImage(this.type);
            if (img != null) {
               g.drawRegion(img, 0, this.frame1 * EffectSkill.getHight(this.type), EffectSkill.getWidth(this.type), EffectSkill.getHight(this.type), this.transform, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
            }
         }
      }

   }

   public void createEfect(int x, int y, int type) {
      EffectSkill u = new EffectSkill(x, y, type, 4);
      EffectManager.addLowEffect(u);
   }

   public void update() {
      try {
         if (this.type == 100) {
            ++this.frame;
            if (this.frame >= 4) {
               this.frame = 0;
            }

            if (this.target != null) {
               this.xto = this.target.x;
               this.yto = (short)(this.target.y - 20);
            } else {
               this.wantDestroy = true;
            }

            this.dx = (short)(this.xto - this.x);
            this.dy = (short)(this.yto - 0 - this.y);
            this.angle = (short)Util.angle(this.dx, this.dy);
            short vx = (short)(12 * Util.cos(this.angle) >> 10);
            short vy = (short)(12 * Util.sin(this.angle) >> 10);
            this.x += vx;
            this.y += vy;
            short dx1 = (short)Math.abs(this.x - this.xto);
            short dy1 = (short)Math.abs(this.y - this.yto);
            boolean isMatchX = false;
            boolean isMatchY = false;
            if (this.target.catagory == 1 && (this.target.getState() == 1 || this.target.getState() == 5)) {
               isMatchX = true;
               isMatchY = true;
            }

            int disx = Utils.getDistance(this.x, this.y, this.xto, this.yto);
            if (dx1 <= vx) {
               this.x = this.xto;
               isMatchX = true;
            }

            if (dy1 <= vy) {
               this.y = this.yto;
               isMatchY = true;
            }

            if (isMatchX && isMatchY || disx < 20) {
               if (this.target.getHp() <= 0) {
                  LiveActor.startDeadFly(this.target, this.Owner.ID);
               }

               this.wantDestroy = true;
               if (this.target != null) {
                  EffectSkill.createHiEfect(this.x, this.y, 24);
                  this.target.jum();
               }
            }

            EffectSkill.createHiEfect(this.x, this.y, this.typeEffect);
         } else {
            ++this.frame;
            if (this.frame >= EffectSkill.getFrame(this.type)) {
               this.frame = 0;
            }

            if (this.pos < this.xw.length) {
               ++this.pos;
            }

            if (this.pos >= this.xw.length) {
               this.pos = (short)(this.xw.length - 1);
               this.xw[this.pos] = this.xto;
               this.yw[this.pos] = this.yto;
               this.wantDestroy = true;
               if (this.effect == -1) {
                  Skill_cung2 c = new Skill_cung2(this.xto, this.yto, this.target, this.Owner, this.dame);
                  EffectManager.addHiEffect(c);
                  EffectSkill.createLowEfect(this.xto, this.yto, 42);
               }

               if (this.wantDestroy) {
                  if (this.type == 32) {
                     EffectManager.addHiEffect(this.xto, this.yto, 50);
                  }

                  if (this.dame == 0) {
                     this.isDame0 = true;
                  }

                  if (this.effect != -1) {
                     this.startFlyText(this.dame, 0, this.xto, this.yto, 0, 0, this.Owner.x > this.xto ? -1 : 1);
                  }
               }

               switch(this.type) {
               case 0:
               default:
                  break;
               case 34:
                  EffectSkill.createLowEfect(this.xto, this.yto, 27);
                  EffectSkill.createLowEfect(this.xto, this.yto, 115);
                  break;
               case 53:
                  EffectSkill.createLowEfect(this.xto, this.yto, this.effect);
                  EffectSkill.createLowEfect(this.xto, this.yto + EffectSkill.getHight(this.type) / 3, 84);
               }

               if (this.effect > 0) {
                  if (this.effect == 68) {
                     EffectManager.addHiDataeffectSkill(136, this.xto, this.yto, 1);
                  } else {
                     EffectSkill.createLowEfect(this.xto, this.yto, this.effect);
                  }
               }

               switch(this.effect) {
               case -3:
                  EffectManager.addHiDataeffectSkill(134, this.xto, this.yto, 1);
                  break;
               case -2:
                  EffectManager.addHiDataeffectSkill(133, this.xto, this.yto, 1);
               case -1:
               }

               if (this.target.getHp() <= 0) {
                  LiveActor.startDeadFly(this.target, this.Owner.ID);
               } else {
                  this.target.jumpVang(this.Owner);
               }
            }
         }
      } catch (Exception var8) {
         this.wantDestroy = true;
      }

   }
}
