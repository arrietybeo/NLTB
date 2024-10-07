package lib.astart;

import code.model.Point;

public class DiagonalHeuristic implements AStarHeuristic {
   public float getEstimatedDistanceToGoal(Point start, Point goal) {
      float h_diagonal = (float)Math.min(Math.abs(start.x - goal.x), Math.abs(start.y - goal.y));
      float h_straight = (float)(Math.abs(start.x - goal.x) + Math.abs(start.y - goal.y));
      float h_result = (float)(Math.sqrt(2.0D) * (double)h_diagonal + (double)(h_straight - 2.0F * h_diagonal));
      float p = 0.0F;
      h_result = (float)((double)h_result * (1.0D + (double)p));
      return h_result;
   }
}
