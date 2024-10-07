package code.model.arrow;

import code.effect.new_skill.EffectSkill;
import code.effect.new_skill.Effect_Fall;
import code.effect.new_skill.Effect_Sao_chop;
import code.effect.new_skill.Effect_dien;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.DataSkillEff;
import code.model.EffectManager;
import code.model.LiveActor;
import code.model.MagicLogic;
import code.screen.Res;
import lib.mGraphics;

public class MagicBeam extends IArrow {
   public int power;
   public int type;
   public byte effect;
   public int hpLost;
   public int frame;
   public int w;
   public int h;
   public int x;
   public int y;
   Actor owner;
   public MagicLogic arrow = new MagicLogic();
   private DataSkillEff arrowEff;
   public static final int[] HEADINDEX = new int[]{-1, -1, -1, 4, 3, 4, 6, -1, 7, -1, -1, -1, -1, -1, 14, 16, -1, -1};
   private byte[][] frameFly = new byte[][]{{0, 0, 1, 1}, {2, 2, 3, 3}, {4, 4, 5, 5}};
   byte fra;
   boolean isDataEff;
   short idHead;

   public void setAngle(int angle) {
      this.arrow.setAngle(angle);
   }

   public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
      if (owner != null) {
         this.arrow.set(type, x, y, owner.getDir(), target);
      }

      if (owner == null) {
         this.arrow.set(type, x, y, Res.random(4), target);
      }

      this.type = type;
      this.owner = owner;
      this.effect = effect;
      if (type == 20) {
         boolean type1 = true;
      }

      this.hpLost = power;
      this.power = power;
      this.wantDestroy = false;
      this.isDataEff = false;
   }

   public void set(int type, int x, int y, int power, Actor owner, Actor target, int eff) {
      if (owner != null) {
         this.arrow.set(type, x, y, owner.getDir(), target);
      }

      this.type = type;
      this.owner = owner;
      if (type == 20) {
         boolean type1 = true;
      }

      this.hpLost = power;
      this.power = power;
      this.wantDestroy = false;
      this.isDataEff = true;
      this.arrowEff = new DataSkillEff(eff);
   }

   public void update() {
      if (this.arrowEff != null) {
         this.arrowEff.updateArrow();
      }

      if (this.isDataEff && this.type == 17) {
         EffectManager.addHiDataeffectSkill(157, this.x, this.y, 0);
      }

      if (this.type == 20) {
         EffectSkill.createHiEfect(this.x, this.y, 90);
      }

      this.arrow.updateAngle();
      this.x = this.arrow.x;
      this.y = this.arrow.y;
      if (this.arrow.isEnd) {
         this.onArrowTouchTarget();
      }

      switch(this.type) {
      case 0:
         EffectManager.addHiEffect(this.x, this.y, 1);
         break;
      case 1:
         EffectSkill.createHiEfect(this.x, this.y, 60);
         break;
      case 2:
         EffectManager.addHiEffect(this.x, this.y, 4);
         break;
      case 3:
         EffectSkill.createHiEfect(this.x, this.y, 63);
         break;
      case 4:
         EffectManager.addHiEffect(this.x, this.y, 8);
         break;
      case 5:
      case 7:
         EffectManager.addHiEffect(this.x, this.y, 29);
      case 6:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 22:
      case 23:
      case 24:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      default:
         break;
      case 14:
         Effect_Fall s = new Effect_Fall(this.x, this.y, 39);
         EffectManager.addHiEffect(s);
         break;
      case 15:
         ++this.frame;
         if (this.frame > this.frameFly[this.arrow.headArrowFrame].length - 1) {
            this.frame = 0;
         }

         this.fra = this.frameFly[this.arrow.headArrowFrame][this.frame];
         break;
      case 21:
         EffectSkill.createHiEfect(this.x, this.y, 127);
      }

   }

   public void createsa0(int x, int y) {
      Effect_Sao_chop sc = new Effect_Sao_chop(x, y, 40);
      EffectManager.addHiEffect(sc);
   }

   public void onArrowTouchTarget() {
      short x0 = this.arrow.target.x;
      short y0 = this.arrow.target.y;
      switch(this.type) {
      case 0:
      case 6:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 16:
      default:
         break;
      case 1:
         EffectSkill.createHiEfect(this.x, this.y, 4);
         if (this.power > 0) {
            this.startFlyText(this.power, 0, this.x, this.y, 0, 0, this.owner.x > this.x ? -1 : 1);
         }

         this.arrow.target.jum();
         break;
      case 2:
         EffectManager.addHiEffect(x0, y0 - 10, 5);
         break;
      case 3:
         Effect_dien e = new Effect_dien(this.x, this.y, 53, 30);
         EffectManager.addLowEffect(e);
         EffectSkill.createHiEfect(this.x, this.y, 54);
         break;
      case 4:
         EffectManager.addLowEffect(x0, y0 - 25, 15);
         EffectManager.addHiEffect(x0, y0 - 15, 15);
         EffectManager.addHiEffect(x0 - 10, y0 - 20, 15);
         EffectManager.addHiEffect(x0 + 10, y0 - 20, 15);
         break;
      case 5:
      case 7:
         EffectManager.addHiEffect(x0, y0 - 10, 30);
         break;
      case 8:
         EffectManager.addHiEffect(x0, y0 - 10, 3);
         EffectManager.addLowEffect(x0, y0 - 25, 11);
         EffectManager.addHiEffect(x0, y0 - 15, 9);
         EffectManager.addHiEffect(x0 - 10, y0 - 20, 11);
         EffectManager.addHiEffect(x0 + 10, y0 - 20, 9);
         break;
      case 14:
         for(int i = 0; i < 5; ++i) {
            this.createsa0(this.x, this.y);
         }

         EffectSkill.createLowEfect(this.x, this.y, 13);
         EffectSkill.createLowEfect(this.x, this.y, 56);
         break;
      case 15:
         EffectManager.addHiDataeffectSkill(60, this.x, this.y, 1);
         break;
      case 17:
         EffectSkill.createHiEfect(this.x, this.y, 30);
         break;
      case 18:
         if (this.power > 0) {
            this.startFlyText(this.power, 0, this.x, this.y, 0, 0, this.owner.x > this.x ? -1 : 1);
         }

         this.arrow.target.jum();
         EffectManager.addHiDataeffectSkill(329, this.x, this.y, 1);
         if (this.arrow.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.arrow.target, this.owner.ID);
         }
         break;
      case 19:
         if (this.power > 0) {
            this.startFlyText(this.power, 0, this.x, this.y, 0, 0, this.owner.x > this.x ? -1 : 1);
         }

         this.arrow.target.jum();
         GameCanvas.gameScr.StartNewEffectEnd(3, this.arrow.target.x, this.arrow.target.y + 10);
         if (this.arrow.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.arrow.target, this.owner.ID);
         }
         break;
      case 20:
         EffectSkill.createHiEfect(this.x, this.y, 54);
         this.startFlyText(this.power, 0, this.x, this.y, 0, 0, this.owner.x > this.x ? -1 : 1);
         break;
      case 21:
         EffectSkill.createHiEfect(this.x, this.y, 21);
      }

      if (this.type != 1 && this.type != 18 && this.type != 19) {
         GameCanvas.gameScr.startFlyText("-" + this.power, 2, this.x, this.y - 15, -1, -2);
      }

      this.wantDestroy = true;
   }

   public void paint(mGraphics g) {
      if (this.isDataEff) {
         if (this.arrowEff != null) {
            this.arrowEff.paintTopArrow(g, this.x, this.y);
         }
      } else if (this.type < 20 && HEADINDEX[this.type] != -1) {
         int imgIndex = HEADINDEX[this.type];
         if (this.type == 15) {
            if (EffectSkill.getImage(79) != null) {
               g.drawRegion(EffectSkill.getImage(79), 0, this.fra * Arrow.ARROWSIZE[1][imgIndex], Arrow.ARROWSIZE[0][imgIndex], Arrow.ARROWSIZE[1][imgIndex], this.arrow.headTransform, this.x, this.y, 3, false);
            }
         } else if (Res.getImgArrow(imgIndex) != null) {
            g.drawRegion(Res.imgArrow[imgIndex], 0, this.arrow.headArrowFrame * Arrow.ARROWSIZE[1][imgIndex], Arrow.ARROWSIZE[0][imgIndex], Arrow.ARROWSIZE[1][imgIndex], this.arrow.headTransform, this.x, this.y, 3, false);
         }
      }

   }

   public void startFlyText(int dame, int color, int x, int y, int dx, int dy, int dir) {
      if (dame > 0) {
         GameCanvas.gameScr.startFlyText("- " + dame, color, x, y, 1, -2, dir);
      }
   }

   public void setIDHEAD(int idHead) {
      this.idHead = (short)idHead;
   }
}
