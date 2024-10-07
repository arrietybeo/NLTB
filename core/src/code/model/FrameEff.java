package code.model;

import lib.mVector;

public class FrameEff {
   public mVector listPartTop = new mVector();
   public mVector listPartBottom = new mVector();
   public byte xShadow = 0;
   public byte yShadow = 0;

   public FrameEff(mVector listtop, mVector listbottom) {
      this.listPartTop = listtop;
      this.listPartBottom = listbottom;
   }

   public mVector getListPartPaint() {
      mVector a = new mVector();

      int i;
      for(i = 0; i < this.listPartBottom.size(); ++i) {
         a.addElement(this.listPartBottom.elementAt(i));
      }

      for(i = 0; i < this.listPartTop.size(); ++i) {
         a.addElement(this.listPartTop.elementAt(i));
      }

      return a;
   }
}
