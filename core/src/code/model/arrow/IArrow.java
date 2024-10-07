package code.model.arrow;

import code.model.Actor;
import lib.mGraphics;

public abstract class IArrow {
   public boolean wantDestroy;

   public abstract void set(int var1, int var2, int var3, int var4, byte var5, Actor var6, Actor var7);

   public abstract void setAngle(int var1);

   public abstract void set(int var1, int var2, int var3, int var4, Actor var5, Actor var6, int var7);

   public abstract void update();

   public abstract void setIDHEAD(int var1);

   public abstract void paint(mGraphics var1);

   public abstract void onArrowTouchTarget();
}
