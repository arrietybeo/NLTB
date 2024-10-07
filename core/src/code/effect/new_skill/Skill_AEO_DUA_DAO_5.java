package code.effect.new_skill;

import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.model.MagicLogic;
import code.model.Position;
import code.screen.Res;
import code.screen.Util;
import lib.mGraphics;
import lib.mVector;

public class Skill_AEO_DUA_DAO_5 extends Effect {
   private MagicLogic magic;
   private int countDau = 7;
   private int dir = -1;
   private int xDau_1;
   private int yDau_1;
   private int xDau_2;
   private int yDau_2;
   private int xlast;
   private int yLast;
   private int xDau_3;
   private int yDau_3;
   private int xDau_4;
   private int yDau_4;
   private mVector listPos = new mVector();
   public String[] textAttack;
   private Actor to;
   boolean isDoubleDragon;

   public Skill_AEO_DUA_DAO_5(int xFrom, int yFrom, Actor from, Actor to, byte type) {
      this.type = type;
      this.to = to;
      this.magic = new MagicLogic();
      this.xlast = xFrom;
      this.yLast = yFrom;
      int angle1;
      if (type == 5) {
         this.xlast = to.x;
         this.yLast = to.y;
         to.x = (short)this.xlast;
         to.y = (short)(this.yLast - 200);
         this.to = to;
         this.magic.set(type, this.xlast, this.yLast, to.getDir(), to);
         angle1 = Util.angle(this.xlast - this.x, -(this.yLast - this.y));
         this.magic.setAngle(Util.fixangle(angle1 - 45));
      } else {
         this.magic.set(type, xFrom, yFrom, from.getDir(), to);
         angle1 = Util.angle(xFrom - this.x, -(yFrom - this.y));
         this.magic.setAngle(Util.fixangle(angle1 - 180));
      }

      this.textAttack = new String[2];
   }

   public void update() {
      if (this.magic != null) {
         this.magic.updateAngle();
         if (this.magic.isEnd) {
            if (this.type != 5) {
               EffectSkill.createHiEfect(this.magic.x, this.magic.y, this.type == 4 ? 51 : 21);
            }

            EffectManager.hiEffects.removeElement(this);
         } else {
            this.x = this.magic.x;
            this.y = this.magic.y;
            EffectSkill.createHiEfect(this.magic.x, this.magic.y, this.type == 4 ? 29 : 49);
            int angM = Util.angle(this.xlast - this.x, -(this.yLast - this.y));
            int ang = Util.fixangle(angM + 90);
            int xa = this.countDau * Util.cos(ang) >> 10;
            int ya = -(this.countDau * Util.sin(ang)) >> 10;
            this.xDau_1 = this.x + xa;
            this.yDau_1 = this.y + ya;
            int ang1 = Util.fixangle(angM - 90);
            int xa1 = this.countDau * Util.cos(ang1) >> 10;
            int ya1 = -(this.countDau * Util.sin(ang1)) >> 10;
            this.xDau_2 = this.x + xa1;
            this.yDau_2 = this.y + ya1;
            if (this.type == 5 && this.isDoubleDragon) {
               int ang2 = Util.fixangle(angM + 135);
               int xa2 = this.countDau * Util.cos(ang2) >> 10;
               int ya2 = -(this.countDau * Util.sin(ang2)) >> 10;
               this.xDau_3 = this.x + xa2 + 10;
               this.yDau_3 = this.y + ya2 - 10;
               int ang3 = Util.fixangle(angM - 135);
               int xa3 = this.countDau * Util.cos(ang3) >> 10;
               int ya3 = -(this.countDau * Util.sin(ang3)) >> 10;
               this.xDau_4 = this.x + xa3 + 10;
               this.yDau_4 = this.y + ya3 - 10;
            }

            this.countDau += this.dir * 3;
            if (Util.abs(this.countDau) > 9) {
               this.dir *= -1;
            }

            Position p = new Position(this.xDau_1, this.yDau_1, 0);
            this.listPos.addElement(p);
            Position p1 = new Position(this.xDau_2, this.yDau_2, 0);
            this.listPos.addElement(p1);
            if (this.type == 5 && this.isDoubleDragon) {
               Position p2 = new Position(this.xDau_3, this.yDau_3, 0);
               this.listPos.addElement(p2);
               Position p3 = new Position(this.xDau_4, this.yDau_4, 0);
               this.listPos.addElement(p3);
            }

            this.xlast = this.x;
            this.yLast = this.y;
         }
      }

   }

   public void paint(mGraphics g) {
      int i;
      Position p;
      if (this.type == 4) {
         for(i = 0; i < this.listPos.size(); ++i) {
            p = (Position)this.listPos.elementAt(i);
            Res.paintImgEff(g, 29, 0, (17 - p.follow) * 11, 11, 11, p.x, p.y, 3);
            ++p.follow;
            if (p.follow >= 18) {
               this.listPos.removeElement(p);
            }
         }
      } else {
         for(i = 0; i < this.listPos.size(); ++i) {
            p = (Position)this.listPos.elementAt(i);
            Res.paintImgEff(g, 8, 0, p.follow * 10, 10, 10, p.x, p.y, 3);
            ++p.follow;
            if (p.follow >= 10) {
               this.listPos.removeElement(p);
            }
         }
      }

      if (Res.getImgArrow(this.type) != null && !this.magic.isEnd) {
         if (this.type == 5) {
            g.drawRegion(Res.imgArrow[3], 0, this.magic.headArrowFrame * 24, 24, 24, this.magic.headTransform, this.xDau_1, this.yDau_1, 3, false);
            g.drawRegion(Res.imgArrow[3], 0, this.magic.headArrowFrame * 24, 24, 24, this.magic.headTransform, this.xDau_2, this.yDau_2, 3, false);
            if (this.isDoubleDragon) {
               g.drawRegion(Res.imgArrow[3], 0, this.magic.headArrowFrame * 24, 24, 24, this.magic.headTransform, this.xDau_3, this.yDau_3, 3, false);
               g.drawRegion(Res.imgArrow[3], 0, this.magic.headArrowFrame * 24, 24, 24, this.magic.headTransform, this.xDau_4, this.yDau_4, 3, false);
            }
         } else {
            g.drawRegion(Res.imgArrow[this.type], 0, this.magic.headArrowFrame * 24, 24, 24, this.magic.headTransform, this.xDau_1, this.yDau_1, 3, false);
            g.drawRegion(Res.imgArrow[this.type], 0, this.magic.headArrowFrame * 24, 24, 24, this.magic.headTransform, this.xDau_2, this.yDau_2, 3, false);
         }
      }

   }
}
