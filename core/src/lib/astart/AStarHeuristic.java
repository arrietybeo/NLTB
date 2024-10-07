package lib.astart;

import code.model.Point;

public interface AStarHeuristic {
   float getEstimatedDistanceToGoal(Point var1, Point var2);
}
