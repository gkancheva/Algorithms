package l07_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphDFS {
    private static List<Integer>[] graph = new ArrayList[9];
    private static boolean[] visited = new boolean[graph.length];

    public static void main(String[] args) {
        fillGraph();

        int countConnectedComponents = 0;

        for (int i = 0; i < graph.length; i++) {
            if(!visited[i]) {
                countConnectedComponents++;
                System.out.println("Connected component #" + countConnectedComponents);
                dfs(i);
                System.out.println();
            }
        }

    }

    private static void dfs(int node) {
        if(!visited[node]) {
            visited[node] = true;
            for (int childNode : graph[node]) {
                dfs(childNode);
            }
            System.out.print(node + " ");
        }
    }

    private static void fillGraph() {
        graph[0] = new ArrayList<>(Arrays.asList(3, 6));
        graph[1] = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        graph[2] = new ArrayList<>(Arrays.asList(1, 4, 5));
        graph[3] = new ArrayList<>(Arrays.asList(0, 1, 5));
        graph[4] = new ArrayList<>(Arrays.asList(1, 2, 6));
        graph[5] = new ArrayList<>(Arrays.asList(1, 2, 3));
        graph[6] = new ArrayList<>(Arrays.asList(0, 1, 4));
        graph[7] = new ArrayList<>(Arrays.asList(8));
        graph[8] = new ArrayList<>(Arrays.asList(7));
    }
}
