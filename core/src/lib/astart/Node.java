package lib.astart;

import code.model.Point;
import lib.mVector;

public class Node {
   AreaMap map;
   boolean visited;
   float distanceFromStart;
   float heuristicDistanceFromGoal;
   Node previousNode;
   int x;
   int y;
   public boolean isObstacle;
   public boolean isStart;
   public boolean isGoal;
   public boolean isPath;

   Node(int x, int y, AreaMap map) {
      this.x = x;
      this.y = y;
      this.map = map;
      this.visited = false;
      this.distanceFromStart = 2.14748365E9F;
      this.isObstacle = false;
      this.isStart = false;
      this.isGoal = false;
   }

   Node(int x, int y, AreaMap map, boolean visited, int distanceFromStart, boolean isObstical, boolean isStart, boolean isGoal) {
      this.x = x;
      this.y = y;
      this.map = map;
      this.visited = visited;
      this.distanceFromStart = (float)distanceFromStart;
      this.isObstacle = isObstical;
      this.isStart = isStart;
      this.isGoal = isGoal;
   }

   public mVector getNeighborList() {
      mVector neighborList = new mVector();
      if (this.y != 0) {
         neighborList.addElement(this.map.getNode(this.x, this.y - 1));
      }

      if (this.y != 0 && this.x != this.map.getMapWith() - 1) {
         neighborList.addElement(this.map.getNode(this.x + 1, this.y - 1));
      }

      if (this.x != this.map.getMapWith() - 1) {
         neighborList.addElement(this.map.getNode(this.x + 1, this.y));
      }

      if (this.x != this.map.getMapWith() - 1 && this.y != this.map.getMapHeight() - 1) {
         neighborList.addElement(this.map.getNode(this.x + 1, this.y + 1));
      }

      if (this.y != this.map.getMapHeight() - 1) {
         neighborList.addElement(this.map.getNode(this.x, this.y + 1));
      }

      if (this.x != 0 && this.y != this.map.getMapHeight() - 1) {
         neighborList.addElement(this.map.getNode(this.x - 1, this.y + 1));
      }

      if (this.x != 0) {
         neighborList.addElement(this.map.getNode(this.x - 1, this.y));
      }

      if (this.x != 0 && this.y != 0) {
         neighborList.addElement(this.map.getNode(this.x - 1, this.y - 1));
      }

      return neighborList;
   }

   public boolean isVisited() {
      return this.visited;
   }

   public void setVisited(boolean visited) {
      this.visited = visited;
   }

   public float getDistanceFromStart() {
      return this.distanceFromStart;
   }

   public void setDistanceFromStart(float f) {
      this.distanceFromStart = f;
   }

   public Node getPreviousNode() {
      return this.previousNode;
   }

   public void setPreviousNode(Node previousNode) {
      this.previousNode = previousNode;
   }

   public float getHeuristicDistanceFromGoal() {
      return this.heuristicDistanceFromGoal;
   }

   public void setHeuristicDistanceFromGoal(float heuristicDistanceFromGoal) {
      this.heuristicDistanceFromGoal = heuristicDistanceFromGoal;
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public Point getPoint() {
      return new Point(this.x, this.y);
   }

   public boolean isObstical() {
      return this.isObstacle;
   }

   public void setObstical(boolean isObstical) {
      this.isObstacle = isObstical;
   }

   public boolean isStart() {
      return this.isStart;
   }

   public void setStart(boolean isStart) {
      this.isStart = isStart;
   }

   public boolean isGoal() {
      return this.isGoal;
   }

   public void setGoal(boolean isGoal) {
      this.isGoal = isGoal;
   }

   public boolean isPath() {
      return this.isPath;
   }

   public void setPath(boolean isPath) {
      this.isPath = isPath;
   }

   public boolean equals(Node node) {
      return node.x == this.x && node.y == this.y;
   }

   public int compareTo(Node otherNode) {
      float thisTotalDistanceFromGoal = this.heuristicDistanceFromGoal + this.distanceFromStart;
      float otherTotalDistanceFromGoal = otherNode.getHeuristicDistanceFromGoal() + otherNode.getDistanceFromStart();
      if (thisTotalDistanceFromGoal < otherTotalDistanceFromGoal) {
         return -1;
      } else {
         return thisTotalDistanceFromGoal > otherTotalDistanceFromGoal ? 1 : 0;
      }
   }
}
