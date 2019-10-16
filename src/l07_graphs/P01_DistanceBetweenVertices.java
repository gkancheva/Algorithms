package l07_graphs;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P01_DistanceBetweenVertices {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int verticesCount = Integer.parseInt(reader.readLine());
        int pairCount = Integer.parseInt(reader.readLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < verticesCount; i++) {
            String[] tokens = reader.readLine().split(":");
            int vertice = Integer.parseInt(tokens[0]);
            graph.putIfAbsent(vertice, new ArrayList<>());
            if (tokens.length == 2) {
                String[] connections = tokens[1].split(" ");
                for (String connection : connections) {
                    graph.get(vertice).add(Integer.parseInt(connection));
                }
            }
        }

        for (int i = 0; i < pairCount; i++) {
            String[] tokens = reader.readLine().split("-");

            int result = bfs(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), graph);

            String output = String.format("{%s, %s} -> %d", tokens[0], tokens[1], result);
            System.out.println(output);
        }


    }

    private static int bfs(int start, int end, Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        Deque<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(start, 0));
        visited.add(start);
        while(!queue.isEmpty()) {
            Pair<Integer, Integer> node = queue.poll();
            if(node.getKey() == end) {
                return node.getValue();
            }
            for (Integer child : graph.get(node.getKey())) {
                if(!visited.contains(child)) {
                    queue.add(new Pair<>(child, node.getValue() + 1));
                    visited.add(child);
                }
            }
        }
        return -1;
    }
}