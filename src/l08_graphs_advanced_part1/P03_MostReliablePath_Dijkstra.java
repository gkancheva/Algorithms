package l08_graphs_advanced_part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P03_MostReliablePath_Dijkstra {
    private static int[][] GRAPH;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        String[] path = reader.readLine().split(" ");
        int start = Integer.parseInt(path[1]);
        int end = Integer.parseInt(path[3]);
        int edgesCount = Integer.parseInt(reader.readLine().split("\\s+")[1]);

        GRAPH = new int[nodesCount][nodesCount];

        for (int i = 0; i < edgesCount; i++) {
            String[] edge = reader.readLine().split("\\s+");
            GRAPH[Integer.parseInt(edge[0])][Integer.parseInt(edge[1])] = (-1) * Integer.parseInt(edge[2]);
            GRAPH[Integer.parseInt(edge[1])][Integer.parseInt(edge[0])] = (-1) * Integer.parseInt(edge[2]);
        }

        List<Integer> shortestPath = dijstra(start, end);

        if(shortestPath == null){
            throw new IllegalArgumentException();
        }

        double reliability = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(shortestPath.get(0)).append(" -> ");
        int previousCity = start;
        for (int i = 1; i < shortestPath.size(); i++) {
            int city = shortestPath.get(i);
            reliability *= ((-1) * GRAPH[previousCity][city]) * 0.01;
            sb.append(city).append(" -> ");
            previousCity = city;
        }

        sb.setLength(sb.length() - 4);
        System.out.printf("Most reliable path reliability: %.2f%%%n", reliability * 100);
        System.out.println(sb.toString());

    }

    private static List<Integer> dijstra(int start, int end) {
        List<Integer> shortestPath = new ArrayList<>();
        Integer[] distances = new Integer[GRAPH.length];
        Integer[] prev = new Integer[GRAPH.length];
        boolean[] visited = new boolean[GRAPH.length];

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparing(i -> distances[i]));

        for (int i = 0; i < distances.length; i++) {
            if(i == start) {
                distances[i] = 0;
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        queue.add(start);

        while(!queue.isEmpty()) {
            int midNode = queue.poll();
            visited[midNode] = true;
            if(distances[midNode] == Integer.MAX_VALUE) {
                break;
            }

            for (int i = 0; i < GRAPH.length; i++) {
                if(GRAPH[midNode][i] != 0 && !visited[i]) {
                    queue.add(i);
                    int newDistance = distances[midNode] + GRAPH[midNode][i];
                    distances[i] = Math.min(distances[i], newDistance);
                    if(newDistance == distances[i]) {
                        prev[i] = midNode;
                        queue.remove(i);
                        queue.add(i);
                    }
                }
            }
        }

        if(distances[end] == Integer.MAX_VALUE) {
            return null;
        }

        while(prev[end] != null) {
            shortestPath.add(0, end);
            end = prev[end];
        }
        shortestPath.add(0, start);
        return shortestPath;
    }
}