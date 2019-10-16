package l09_graph_advanced_part2.strongly_connected_components;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*  Kosaraju-Sharir Algorithm
    We can reach each point in the graph from no matter which point.
    Finding the components. A directed graph is strongly connected when
    every two vertices are connected by path
*/
public class StronglyConnectedComponents {
    private static List<Integer>[] GRAPH;
    private static List<Integer>[] REVERSED_GRAPH;
    private static Deque<Integer> STACK = new ArrayDeque<>();
    private static boolean[] VISITED;

    private static List<List<Integer>> stronglyConnectedComponents;

    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        List<List<Integer>> result = new ArrayList<>();
        GRAPH = targetGraph;
        VISITED = new boolean[GRAPH.length];
        buildReverseGraph();

        //fill the stack
        for (int node = 0; node < GRAPH.length; node++) {
            if(!VISITED[node]) {
                dfs(node);
            }
        }

        VISITED = new boolean[GRAPH.length];
        while(!STACK.isEmpty()) {
            int node = STACK.pop();
            if(!VISITED[node]) {
                List<Integer> connectedComponents = new ArrayList<>();
                reverseDfs(node, connectedComponents);
                result.add(connectedComponents);
            }

        }

        return result;
    }

    private static void buildReverseGraph() {
        REVERSED_GRAPH = new ArrayList[GRAPH.length];
        for (int node = 0; node < REVERSED_GRAPH.length; node++) {
            REVERSED_GRAPH[node] = new ArrayList<>();
        }
        for (int node = 0; node < GRAPH.length; node++) {
            for (int child : GRAPH[node]) {
                REVERSED_GRAPH[child].add(node);
            }
        }
    }

    private static void reverseDfs(int node, List<Integer> components) {
        if(!VISITED[node]) {
            VISITED[node] = true;
            components.add(node);
            for (int child : REVERSED_GRAPH[node]) {
                reverseDfs(child, components);
            }
        }
    }

    private static void dfs(int node) {
        if(!VISITED[node]) {
            VISITED[node] = true;
            for (int child : GRAPH[node]) {
                dfs(child);
            }
            STACK.push(node);
        }
    }
}
