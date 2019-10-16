package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Graphs -> bridges of articulations points
public class P04_RoadReconstruction_Graphs {
    private static int BUILDINGS_COUNT = 0;
    private static List<Integer>[] GRAPH;
    private static int TIME = 0;
    private static StringBuilder RESULT = new StringBuilder("Important streets:");

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BUILDINGS_COUNT = Integer.parseInt(reader.readLine());
        int streetsCount = Integer.parseInt(reader.readLine());

        GRAPH = new ArrayList[BUILDINGS_COUNT];

        for (int i = 0; i < BUILDINGS_COUNT; i++) {
            GRAPH[i] = new ArrayList<>();
            GRAPH[i] = new ArrayList<>();
        }

        for (int i = 0; i < streetsCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" - ")).mapToInt(Integer::parseInt).toArray();
            int firstBuild = tokens[0];
            int secondBuild = tokens[1];
            GRAPH[firstBuild].add(secondBuild);
            GRAPH[secondBuild].add(firstBuild);
        }

        RESULT.append(System.lineSeparator());

        findBridges();

        System.out.println(RESULT.toString().trim());

    }

    private static void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {
        visited[u] = true;
        disc[u] = low[u] = ++TIME;

        for (int vertex : GRAPH[u]) {
            if (!visited[vertex]) {
                parent[vertex] = u;
                bridgeUtil(vertex, visited, disc, low, parent);
                low[u] = Math.min(low[u], low[vertex]);
                if (low[vertex] > disc[u]) {
                    String output = u + " " + vertex;
                    RESULT.append(output).append(System.lineSeparator());
                }
            } else if (vertex != parent[u])
                low[u] = Math.min(low[u], disc[vertex]);
        }
    }


    private static void findBridges() {
        boolean[] visited = new boolean[BUILDINGS_COUNT];
        int[] disc = new int[BUILDINGS_COUNT];
        int[] low = new int[BUILDINGS_COUNT];
        int[] parent = new int[BUILDINGS_COUNT];


        for (int i = 0; i < BUILDINGS_COUNT; i++) {
            parent[i] = -1;
            visited[i] = false;
        }
        for (int i = 0; i < BUILDINGS_COUNT; i++)
            if (!visited[i])
                bridgeUtil(i, visited, disc, low, parent);
    }
}