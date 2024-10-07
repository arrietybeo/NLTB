package code.effect.new_skill;

import code.effect.EffectStarSkill;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import javax.microedition.lcdui.Image;
import lib.mGraphics;
import lib.mVector;

public class Skill_Kiem_T3 extends Effect {
   public short vx;
   public short vy;
   public short x2;
   public short tx;
   public short ty;
   public short xto;
   public short yto;
   public boolean b;
   public byte frame;
   public byte frame2;
   int xPos;
   int yPos;
   int type;
   int count;
   int id;
   mVector mvector = new mVector();
   boolean isMonster = false;

   public void setActorter(mVector mst) {
      this.add(mst);
      this.isMonster = false;
   }

   public void setMonster(mVector mst) {
      this.add(mst);
   }

   private void add(mVector mst) {
      this.mvector.removeAllElements();

      for(int i = 0; i < mst.size(); ++i) {
         Actor live = (Actor)mst.elementAt(i);
         Actor ac = GameCanvas.gameScr.findActorByID(live.ID, live.catagory);
         if (ac != null) {
            this.mvector.addElement(ac);
         }
      }

   }

   public Skill_Kiem_T3(short xc, short yc, short x, short y, mVector mst) {
      this.tx = x;
      this.id = 34;
      this.ty = y;
      this.x = x - 30 - 2 * EffectSkill.getWidth(this.id);
      this.x2 = (short)(x + 30 + 2 * EffectSkill.getWidth(this.id));
      this.y = y - 100;
      this.vx = 7;
      this.xPos = x;
      this.vy = 10;
      this.yPos = y - 70;
      this.type = 1;
      this.setMonster(mst);
      EffectStarSkill eff = new EffectStarSkill(xc, (short)(yc - 10), (short)64, (short)4, (short)25);
      EffectManager.addLowEffect(eff);
   }

   public void paint(mGraphics g) {
      for(int i = 0; i < 2; ++i) {
         Image img = EffectSkill.getImage(this.id);
         if (img != null) {
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.x + i * 10, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.x2 - i * 10, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
            g.drawRegion(img, 0, this.frame * EffectSkill.getHight(this.id), EffectSkill.getWidth(this.id), EffectSkill.getHight(this.id), 0, this.tx, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
         }
      }

      Image img = EffectSkill.getImage(0);
      if (img != null) {
         g.drawRegion(img, 0, this.frame2 * EffectSkill.getHight(0), EffectSkill.getWidth(0), EffectSkill.getHight(0), 0, this.tx, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
      }

   }

   public void createffbefore(int type) {
      EffectSkill m = null;

      for(int i = 0; i < this.mvector.size(); ++i) {
         Actor t = (Actor)this.mvector.elementAt(i);
         m = new EffectSkill(t.x, t.y, 64, 0);
         EffectManager.addLowEffect(m);
         m = null;
      }

   }

   public void createeff(short x, short y) {
      Me_Arrow b = null;

      for(int i = 0; i < this.mvector.size(); ++i) {
         Actor t = (Actor)this.mvector.elementAt(i);
         b = new Me_Arrow(x, y, t, (byte)62, (byte)9, (byte)0, (byte)20);
         EffectManager.addLowEffect(b);
         b = null;
      }

   }

   public void createEfect(int x, int y, int type) {
      EffectSkill u = new EffectSkill(x, y, type, 4);
      EffectManager.addLowEffect(u);
   }

   public void update() {
      this.x += this.vx;
      this.x2 -= this.vx;
      this.frame2 = (byte)((this.frame2 + 1) % 3);
      if (this.x >= this.tx) {
         this.x = this.tx;
         this.vx = 0;
      }

      if (this.x == this.tx && this.x2 == this.tx) {
         ++this.count;
      }

      if (this.count > 15) {
         this.y += this.vy;
         this.vy = (short)(this.vy + 15);
      }

      if (this.count >= 2 && GameCanvas.gameTick % 3 == 0) {
         this.createffbefore(6);
      }

      if (this.x2 <= this.tx) {
         this.x2 = this.tx;
         this.vx = 0;
      }

      if (this.y + this.vy > this.ty + 10) {
         this.wantDestroy = true;
         EffectSkill.createLowEfect(this.x, this.y, 4);
         EffectSkill.createLowEfect(this.x, this.y, 27);
         this.createeff((short)this.x, (short)this.y);
      }

   }
}
