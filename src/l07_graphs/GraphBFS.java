package l07_graphs;

import java.util.*;

//if we change the queue with stack, it will turn to depth first search
public class GraphBFS {
    private static List<Integer>[] graph = new ArrayList[7];
    private static boolean[] visited = new boolean[graph.length];

    public static void main(String[] args) {
        fillGraph();

        for (int i = 0; i < graph.length; i++) {
            bfs(i);
        }

    }

    private static void bfs(int node) {
        if(visited[node]) {
            return;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(node);
        visited[node]= true;
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");
            for (int childNode : graph[currentNode]) {
                if(!visited[childNode]) {
                    queue.add(childNode);
                    visited[childNode] = true;
                }
            }
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
    }
}
