package lib.astart;

import code.model.Point;
import lib.mVector;

public class Path {
   private mVector waypoints = new mVector();

   public int getLength() {
      return this.waypoints.size();
   }

   public Node getWayPointNode(int index) {
      return (Node)this.waypoints.elementAt(index);
   }

   public Point getWayPoint(int index) {
      return new Point(((Node)this.waypoints.elementAt(index)).getX(), ((Node)this.waypoints.elementAt(index)).getY());
   }

   public int getX(int index) {
      return this.getWayPointNode(index).getX();
   }

   public int getY(int index) {
      return this.getWayPointNode(index).getY();
   }

   public void appendWayPoint(Node n) {
      this.waypoints.addElement(n);
   }

   public void prependWayPoint(Node n) {
      this.waypoints.insertElementAt(n, 0);
   }

   public boolean contains(int x, int y) {
      for(int i = 0; i < this.waypoints.size(); ++i) {
         Node node = (Node)this.waypoints.elementAt(i);
         if (node.getX() == x && node.getY() == y) {
            return true;
         }
      }

      return false;
   }
}
