package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;
import javax.microedition.lcdui.Image;
import lib.mGraphics;
import lib.mVector;

public class Skill_Bua_T5 extends Effect {
   public int[] arr_radian = new int[]{0, 180};
   public int[] eff = new int[]{9};
   public byte frame;
   public int count;
   public int xto;
   public int yto;
   public int effect;
   public int id1;
   public int id2;
   mVector mvector = new mVector();

   public Skill_Bua_T5(short x, short y, mVector mts) {
      this.x = x;
      this.y = y - 15;
      this.setMonster(mts);
      EffectSkill.createLowEfect(x, y, 17);
      this.id1 = 25;
      this.id2 = 26;
   }

   public Skill_Bua_T5(short x, short y, mVector mts, int[] arrdame) {
      this.x = x;
      this.y = y - 15;
      this.setMonster(mts);
      EffectSkill.createLowEfect(x, y, 17);
      this.id1 = 25;
      this.id2 = 26;
      this.arrDame = arrdame;
   }

   public void setMonster(mVector mst) {
      this.add(mst);
   }

   private void add(mVector mst) {
      this.mvector.removeAllElements();

      for(int i = 0; i < mst.size(); ++i) {
         Actor live = (Actor)mst.elementAt(i);
         if (live != null) {
            this.mvector.addElement(live);
         }
      }

   }

   public void paint(mGraphics g) {
      for(int i = 0; i < this.arr_radian.length; ++i) {
         Image img = EffectSkill.getImage(this.id1);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id1), EffectSkill.getWidth(this.id1), EffectSkill.getHight(this.id1), 0, Util.cos(this.arr_radian[i]) * 40 / 1024 + this.x, Util.sin(this.arr_radian[i]) * 40 / 1024 + this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }

         Image img2 = EffectSkill.getImage(this.id2);
         if (img2 != null) {
            g.drawRegion(img2, 0, this.frame * EffectSkill.getHight(this.id2), EffectSkill.getWidth(this.id2), EffectSkill.getHight(this.id2), 0, Util.cos(this.arr_radian[i]) * 40 / 1024 + this.x, Util.sin(this.arr_radian[i]) * 40 / 1024 + this.y + EffectSkill.getHight(this.id1) / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

   }

   public void create(short x, short y, short xto, short yto, byte type, byte speed, Actor tg, int dame) {
      Me_Arrow b = new Me_Arrow(x, y, xto, yto, type, (byte)68, (byte)0, speed);
      b.setOwner(this.Owner);
      b.setDame(dame);
      b.settarget(tg);
      EffectManager.addLowEffect(b);
   }

   public void update() {
      ++this.frame;
      if (this.frame >= 3) {
         this.frame = 0;
      }

      int i;
      if (GameCanvas.gameTick % 15 == 0 && this.mvector != null) {
         ++this.count;

         for(i = 0; i < this.mvector.size(); ++i) {
            Actor t = (Actor)this.mvector.elementAt(i);
            if (t != null) {
               int damee = 0;
               if (this.arrDame != null && i < this.arrDame.length - 1) {
                  damee = this.arrDame[i] / 2;
               }

               if (damee == 0) {
                  this.isDame0 = true;
               }

               this.create((short)(Util.cos(this.arr_radian[0]) * 40 / 1024 + this.x), (short)(Util.sin(this.arr_radian[0]) * 40 / 1024 + this.y), t.x, t.y, (byte)this.id1, (byte)20, t, damee);
               this.create((short)(Util.cos(this.arr_radian[0]) * 40 / 1024 + this.x), (short)(Util.sin(this.arr_radian[0]) * 40 / 1024 + this.y + EffectSkill.getHight(this.id1) / 2), t.x, (short)(t.y - EffectSkill.getHight(this.id1) / 4), (byte)this.id2, (byte)20, t, damee);
               this.effect = this.eff[Math.abs(GameCanvas.r.nextInt() % this.eff.length)];
               this.create((short)(Util.cos(this.arr_radian[1]) * 40 / 1024 + this.x), (short)(Util.sin(this.arr_radian[1]) * 40 / 1024 + this.y), t.x, t.y, (byte)this.id1, (byte)20, t, damee);
               this.create((short)(Util.cos(this.arr_radian[1]) * 40 / 1024 + this.x), (short)(Util.sin(this.arr_radian[1]) * 40 / 1024 + this.y + EffectSkill.getHight(this.id1) / 2), t.x, (short)(t.y - EffectSkill.getHight(this.id1) / 4), (byte)this.id2, (byte)20, t, damee);
            }
         }
      }

      if (this.count == 2) {
         this.wantDestroy = true;
      }

      for(i = 0; i < this.arr_radian.length; ++i) {
         int[] var10000 = this.arr_radian;
         var10000[i] += 30;
         if (this.arr_radian[i] > 360) {
            this.arr_radian[i] = 0;
         }
      }

   }
}
