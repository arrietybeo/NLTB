package code.effect.new_skill;

import code.model.Actor;
import code.model.Char;
import code.model.Effect;
import code.model.EffectManager;
import lib.mGraphics;
import lib.mVector;

public class Skill_Cung_T4 extends Effect {
   public int angle;
   public int xto;
   public int yto;
   public int frame = 0;
   public int pos;
   public int thongsolathinh = 0;
   public int count;
   private int[] xw;
   private int[] yw;
   public int dx;
   public int dy;
   public int idimg;
   public int transform;
   public byte ideff;
   mVector mst = new mVector();

   public Skill_Cung_T4(int x, int y, int xto, int yto, mVector mst, Char c) {
      this.x = x;
      this.y = y;
      this.xto = xto;
      this.yto = yto;
      this.type = 4;
      this.idimg = 71;
      this.ideff = 13;
      this.Owner = c;

      for(int i = 0; i < mst.size(); ++i) {
         Actor ac = (Actor)mst.elementAt(i);
         if (ac != null) {
            ac.jumpVang(this.Owner);
         }

         for(int j = 0; j < 5; ++j) {
            Effect_Cung_T4 s = new Effect_Cung_T4(x, y - 25, ac.x, ac.y, j * 3, this.ideff);
            EffectManager.addHiEffect(s);
         }
      }

      this.wantDestroy = true;
   }

   public void paint(mGraphics g) {
   }

   public void update() {
   }
}
