package lib.astart;

import code.model.Point;
import lib.mVector;

public class AreaMap {
    private int mapWith;
    private int mapHeight;
    private mVector map;
    private int startLocationX = 0;
    private int startLocationY = 0;
    private int goalLocationX = 0;
    private int goalLocationY = 0;
    private int[][] obstacleMap = new int[][]{new int[1]};

    public AreaMap(int mapWith, int mapHeight) {
        this.mapWith = mapWith;
        this.mapHeight = mapHeight;
        this.createMap();
    }

    public AreaMap(int mapWith, int mapHeight, int[][] obstacleMap) {
        this.mapWith = mapWith;
        this.mapHeight = mapHeight;
        this.obstacleMap = obstacleMap;
        this.createMap();
    }

    private void createMap() {
        this.map = new mVector();

        for (int x = 0; x < this.mapWith; ++x) {
            this.map.addElement(new mVector());

            for (int y = 0; y < this.mapHeight; ++y) {
                Node node = new Node(x, y, this);

                try {
                    if (this.obstacleMap[y][x] == 2) {
                        node.setObstical(true);
                    }
                } catch (Exception var5) {
                }

                ((mVector) this.map.elementAt(x)).addElement(node);
            }
        }

    }

    public mVector getNodes() {
        return this.map;
    }

    public void setObstacle(int x, int y, boolean isObstical) {
        ((Node) ((mVector) this.map.elementAt(x)).elementAt(y)).setObstical(isObstical);
    }

    public Node getNode(int x, int y) {
        return (Node) ((mVector) this.map.elementAt(x)).elementAt(y);
    }

    public void setStartLocation(int x, int y) {
        ((Node) ((mVector) this.map.elementAt(this.startLocationX)).elementAt(this.startLocationY)).setStart(false);
        ((Node) ((mVector) this.map.elementAt(x)).elementAt(y)).setStart(true);
        this.startLocationX = x;
        this.startLocationY = y;
    }

    public void setGoalLocation(int x, int y) {
        ((Node) ((mVector) this.map.elementAt(this.goalLocationX)).elementAt(this.goalLocationY)).setGoal(false);
        ((Node) ((mVector) this.map.elementAt(x)).elementAt(y)).setGoal(true);
        this.goalLocationX = x;
        this.goalLocationY = y;
    }

    public int getStartLocationX() {
        return this.startLocationX;
    }

    public int getStartLocationY() {
        return this.startLocationY;
    }

    public Node getStartNode() {
        return (Node) ((mVector) this.map.elementAt(this.startLocationX)).elementAt(this.startLocationY);
    }

    public int getGoalLocationX() {
        return this.goalLocationX;
    }

    public int getGoalLocationY() {
        return this.goalLocationY;
    }

    public Point getGoalPoint() {
        return new Point(this.goalLocationX, this.goalLocationY);
    }

    public Node getGoalNode() {
        return (Node) ((mVector) this.map.elementAt(this.goalLocationX)).elementAt(this.goalLocationY);
    }

    public float getDistanceBetween(Node node1, Node node2) {
        return node1.getX() != node2.getX() && node1.getY() != node2.getY() ? 1.9F : 1.0F;
    }

    public int getMapWith() {
        return this.mapWith;
    }

    public int getMapHeight() {
        return this.mapHeight;
    }

    public void clear() {
        this.startLocationX = 0;
        this.startLocationY = 0;
        this.goalLocationX = 0;
        this.goalLocationY = 0;
        this.createMap();
    }
}
