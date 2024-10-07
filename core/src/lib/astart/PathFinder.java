package lib.astart;

import code.model.Point;
import lib.Cout;
import lib.mVector;

public class PathFinder {
    AreaMap map;

    public mVector getWaypoints(AreaMap map) {
        this.map = map;
        AStarHeuristic heuristic = new DiagonalHeuristic();
        AStar aStar = new AStar(map, heuristic);
        mVector shortestPath = aStar.calcShortestPath(map.getStartLocationX(), map.getStartLocationY(), map.getGoalLocationX(), map.getGoalLocationY());
        Cout.println("Calculating optimized waypoints...");
        mVector waypoints = this.calculateWayPoints(shortestPath);
        Cout.println("Time to calculate waypoints: ");
        return waypoints;
    }

    private mVector calculateWayPoints(mVector shortestPath) {
        mVector waypoints = new mVector();
        shortestPath.insertElementAt(this.map.getStartNode().getPoint(), 0);
        shortestPath.addElement(this.map.getGoalNode().getPoint());
        Point p1 = (Point) shortestPath.elementAt(0);
        int p1Number = 0;
        waypoints.addElement(p1);
        Point p2 = (Point) shortestPath.elementAt(1);
        int p2Number = 1;
        while (!p2.equals(shortestPath.elementAt(shortestPath.size() - 1))) {
            if (lineClear(p1, p2)) {
                p2Number++;
                p2 = (Point) shortestPath.elementAt(p2Number);
                continue;
            }
            p1Number = p2Number - 1;
            p1 = (Point) shortestPath.elementAt(p1Number);
            waypoints.addElement(p1);
            p2Number++;
            p2 = (Point) shortestPath.elementAt(p2Number);
        }
        waypoints.addElement(p2);
        return waypoints;
    }

    private boolean lineClear(Point a, Point b) {
        mVector pointsOnLine = BresenhamsLine.getPointsOnLine(a, b);

        for (int i = 0; i < pointsOnLine.size(); ++i) {
            Point p = (Point) pointsOnLine.elementAt(i);
            if (this.map.getNode(p.x, p.y).isObstacle) {
                return false;
            }
        }

        return true;
    }
}
