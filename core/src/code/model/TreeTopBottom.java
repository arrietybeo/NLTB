package code.model;

import lib.Tree;
import lib.mGraphics;
import lib.mVector;

public class TreeTopBottom {
   public short xGrid;
   public short yGrid;
   public mVector treeTop = new mVector();
   public mVector treeBottom = new mVector();

   public void addTree(Tree tree, int top_bottom) {
      if (top_bottom == 0) {
         this.treeTop.addElement(tree);
      } else {
         this.treeBottom.addElement(tree);
      }

   }

   public void paintTop(mGraphics g, int posy) {
      for(int i = 0; i < this.treeTop.size(); ++i) {
         Tree tree = (Tree)this.treeTop.elementAt(i);
         tree.paint(g);
      }

   }

   public void paintBottom(mGraphics g) {
      for(int i = 0; i < this.treeBottom.size(); ++i) {
         Tree tree = (Tree)this.treeBottom.elementAt(i);
         tree.paint(g);
      }

   }
}
