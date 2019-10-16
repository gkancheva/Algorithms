package l09_graph_advanced_part2.articulation_points;

import java.util.ArrayList;
import java.util.List;

// In a connected undirected graph and articulation point is node that when removed,
// splits the graph into several components.
// Bi-connected components are such components that left
// after the removing the articulation point
public class ArticulationPoints {
    private static List<Integer>[] GRAPH;
    private static boolean[] VISITED;
    private static int[] DEPTHS;
    private static int[] LOW_POINTS;
    private static int[] PARENTS;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        List<Integer> artPoints = new ArrayList<>();

        GRAPH = targetGraph;
        VISITED = new boolean[GRAPH.length];
        DEPTHS = new int[GRAPH.length];
        LOW_POINTS = new int[GRAPH.length];
        PARENTS = new int[GRAPH.length];

        for (int i = 0; i < PARENTS.length; i++) {
            PARENTS[i] = -1;
        }

        for (int node = 0; node < GRAPH.length; node++) {
            if(!VISITED[node]) {
                findPoints(node, 1, artPoints);
            }
        }

        return artPoints;
    }

    //dfs
    private static void findPoints(int node, int depth, List<Integer> artPoints) {
        VISITED[node] = true;
        DEPTHS[node] = depth;
        LOW_POINTS[node] = depth;

        int childCount = 0;
        boolean isArticulationPoint = false;

        for (int child : GRAPH[node]) {
            if(!VISITED[child]) {
                PARENTS[child] = node;
                findPoints(child, depth + 1, artPoints);
                childCount++;
                if(LOW_POINTS[child] >= DEPTHS[node]) {
                    isArticulationPoint = true;
                }
                LOW_POINTS[node] = Math.min(LOW_POINTS[node], LOW_POINTS[child]);
            } else if(child != PARENTS[node]) {
                int newLowPoint = Math.min(LOW_POINTS[node], DEPTHS[child]);
                LOW_POINTS[node] = newLowPoint;
            }
        }
        if((PARENTS[node] == -1 && childCount > 1) ||
                (PARENTS[node] != -1 && isArticulationPoint)) {
            artPoints.add(node);
        }


    }
}
