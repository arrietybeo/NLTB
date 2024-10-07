package lib;

import code.model.Point;
import lib.astart.AStar;
import lib.astart.AStarHeuristic;
import lib.astart.AreaMap;
import lib.astart.DiagonalHeuristic;

public class astar {
    public static AStar astar;
    public static AreaMap areamap;
    public static AStarHeuristic heuristic = new DiagonalHeuristic();
    public static int w_map;
    public static int h_map;
    public static int[] astart_map;

    public static short[] Findroad(int xchar, int ychar, int xfrom, int yform) {
        mVector shortestPath = astar.calcShortestPath(xchar, ychar, xfrom, yform);
        if (shortestPath == null) {
            return null;
        } else {
            short[] duongdi = new short[shortestPath.size()];

            for (int i = 0; i < shortestPath.size(); ++i) {
                Point dem = (Point) shortestPath.elementAt(i);
                duongdi[i] = (short) ((dem.x << 8) + dem.y);
            }

            ResetMap();
            return duongdi;
        }
    }

    public static void createMapAstar(int w, int h, int[] map) {
        w_map = w;
        h_map = h;
        astart_map = new int[map.length];

        for (int i = 0; i < map.length; ++i) {
            astart_map[i] = map[i];
        }

        int[][] obstacleMap = new int[h_map][w_map];

        for (int i = 0; i < h_map; ++i) {
            for (int j = 0; j < w_map; ++j) {
                obstacleMap[i][j] = astart_map[i * w_map + j];
            }
        }

        areamap = new AreaMap(w_map, h_map, obstacleMap);
        astar = new AStar(areamap, heuristic);
    }

    public static void ResetMap() {
        int[][] obstacleMap = new int[h_map][w_map];

        for (int i = 0; i < h_map; ++i) {
            for (int j = 0; j < w_map; ++j) {
                obstacleMap[i][j] = astart_map[i * w_map + j];
            }
        }

        areamap = new AreaMap(Tilemap.w, Tilemap.h, obstacleMap);
        astar = new AStar(areamap, heuristic);
    }
}
