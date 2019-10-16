package l09_graph_advanced_part2.max_flow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

//Finding the maximum flow that can be passed though from A to B node in a graph
//
public class EdmondsKarp {
    private static int[][] GRAPH;
    private static int[] PARENTS;

    public static int findMaxFlow(int[][]targetGraph) {
        GRAPH = targetGraph;
        PARENTS = new int[targetGraph.length];

        for (int i = 0; i < PARENTS.length; i++) {
            PARENTS[i] = -1;
        }

        int maxFlow = 0;
        int start = 0;
        int end = GRAPH.length - 1;

        while (bfs(start, end)) {
            int pathFlow = Integer.MAX_VALUE;
            int currentNode = end;
            while(currentNode != start) {
                int prevNode = PARENTS[currentNode];
                int currentFlow = GRAPH[prevNode][currentNode];
                if(currentFlow > 0 && currentFlow < pathFlow) {
                    pathFlow = currentFlow;
                }
                currentNode = prevNode;
            }
            maxFlow += pathFlow;

            currentNode = end;
            while (currentNode != start) {
                int prevNode = PARENTS[currentNode];
                GRAPH[prevNode][currentNode] -= pathFlow;
                GRAPH[currentNode][prevNode] += pathFlow;
                currentNode = prevNode;
            }
        }
        return maxFlow;
    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[GRAPH.length];
        Deque<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()) {
            int node = queue.pop();

            for (int child = 0; child < GRAPH[node].length; child++) {
                if(GRAPH[node][child] > 0 && !visited[child]) {
                    queue.add(child);
                    PARENTS[child] = node;
                    visited[child] = true;
                }
            }
        }
        return visited[end];
    }
}
