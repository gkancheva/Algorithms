package l08_graphs_advanced_part1.Dijkstra_ShortestPath;

import java.util.*;

//Shortest path in graph
public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        List<Integer> shortestPath = new ArrayList<>();
        Integer[] distances = new Integer[graph.length];
        Integer[] prev = new Integer[graph.length];
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distances[i]));

        for (int i = 0; i < distances.length; i++) {
            if(i == sourceNode) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        queue.add(sourceNode);

        while(!queue.isEmpty()) {
            int midNode = queue.poll();
            visited[midNode] = true;
            if(distances[midNode] == Integer.MAX_VALUE) {
                break;
            }

            for (int i = 0; i < graph.length; i++) {
                if(graph[midNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDistance = distances[midNode] + graph[midNode][i];
                    distances[i] = Math.min(distances[i], newDistance);
                    if(newDistance == distances[i]) {
                        prev[i] = midNode;
                        queue.remove(i);
                        queue.add(i);
                    }
                }
            }
        }

        if(distances[destinationNode] == Integer.MAX_VALUE) {
            return null;
        }

        while(prev[destinationNode] != null) {
            shortestPath.add(0, destinationNode);
            destinationNode = prev[destinationNode];
        }
        shortestPath.add(0, sourceNode);
        return shortestPath;
    }
}