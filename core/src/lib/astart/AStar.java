package lib.astart;

import lib.mVector;

public class AStar {
    private AreaMap map;
    private AStarHeuristic heuristic;
    private mVector closedList;
    private AStar.SortedNodeList openList;
    private mVector shortestPath;

    public AStar(AreaMap map, AStarHeuristic heuristic) {
        this.map = map;
        this.heuristic = heuristic;
        this.closedList = new mVector();
        this.openList = new AStar.SortedNodeList((AStar.SortedNodeList) null);
    }

    public mVector calcShortestPath(int startX, int startY, int goalX, int goalY) {
        this.map.setStartLocation(startX, startY);
        this.map.setGoalLocation(goalX, goalY);
        if (this.map.getNode(goalX, goalY).isObstacle) {
            return null;
        } else {
            this.map.getStartNode().setDistanceFromStart(0.0F);
            this.closedList.removeAllElements();
            this.openList.clear();
            this.openList.add(this.map.getStartNode());

            while (this.openList.size() != 0) {
                Node current = this.openList.getFirst();
                if (current.getX() == this.map.getGoalLocationX() && current.getY() == this.map.getGoalLocationY()) {
                    return this.reconstructPath(current);
                }

                this.openList.remove(current);
                this.closedList.addElement(current);

                for (int i = 0; i < current.getNeighborList().size(); ++i) {
                    Node neighbor = (Node) current.getNeighborList().elementAt(i);
                    if (!this.closedList.contains(neighbor) && !neighbor.isObstacle) {
                        float neighborDistanceFromStart = current.getDistanceFromStart() + this.map.getDistanceBetween(current, neighbor);
                        boolean neighborIsBetter;
                        if (!this.openList.contains(neighbor)) {
                            this.openList.add(neighbor);
                            neighborIsBetter = true;
                        } else if (neighborDistanceFromStart < current.getDistanceFromStart()) {
                            neighborIsBetter = true;
                        } else {
                            neighborIsBetter = false;
                        }

                        if (neighborIsBetter) {
                            neighbor.setPreviousNode(current);
                            neighbor.setDistanceFromStart(neighborDistanceFromStart);
                            neighbor.setHeuristicDistanceFromGoal(this.heuristic.getEstimatedDistanceToGoal(neighbor.getPoint(), this.map.getGoalPoint()));
                        }
                    }
                }
            }

            return null;
        }
    }

    private mVector reconstructPath(Node node) {
        mVector path;
        for (path = new mVector(); node.getPreviousNode() != null; node = node.getPreviousNode()) {
            path.insertElementAt(node.getPoint(), 0);
        }

        this.shortestPath = path;
        return path;
    }

    private class SortedNodeList {
        private mVector list;

        private SortedNodeList() {
            this.list = new mVector();
        }

        public Node getFirst() {
            return (Node) this.list.elementAt(0);
        }

        public void clear() {
            this.list.removeAllElements();
        }


        public void add(Node node) {
            this.list.addElement(node);
            int min = 0;
            for (int i = 0; i < this.list.size() - 1; i++) {
                min = i;
                for (int j = i + 1; j < this.list.size(); j++) {
                    if (((Node) this.list.elementAt(j)).compareTo((Node) this.list.elementAt(i)) < 0)
                        min = j;
                }
                swap(this.list, min, i);
            }
        }

        private void swap(mVector actors, int dex1, int dex2) {
            Object temp = actors.elementAt(dex2);
            if (((Node) actors.elementAt(dex2)).getY() != ((Node) actors.elementAt(dex1)).getY()) {
                actors.setElementAt(actors.elementAt(dex1), dex2);
                actors.setElementAt(temp, dex1);
            }

        }

        public void remove(Node n) {
            this.list.removeElement(n);
        }

        public int size() {
            return this.list.size();
        }

        public boolean contains(Node n) {
            return this.list.contains(n);
        }

        // $FF: synthetic method
        SortedNodeList(AStar.SortedNodeList var2) {
            this();
        }
    }
}
