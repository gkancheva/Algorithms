package exam_26_09_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P04_CheapTownTrip {
    private static int[][] GRAPH;
    private static int[] PARENTS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int townsCount = Integer.parseInt(reader.readLine());
        int streetsCount = Integer.parseInt(reader.readLine());

        GRAPH = new int[townsCount][townsCount];
        PARENTS = new int[GRAPH.length];

        for (int i = 0; i < GRAPH.length; i++) {
            for (int j = 0; j < GRAPH.length; j++) {
                GRAPH[i][j] = -1;
            }
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < streetsCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" - ")).mapToInt(Integer::parseInt).toArray();
            int firstTown = tokens[0];
            int secondTown = tokens[1];
            int cost = tokens[2];
            //GRAPH[firstTown][secondTown] = cost;

            edges.add(new Edge(firstTown, secondTown, cost));
        }

        List<Edge> mst = kruskal(townsCount, edges);

        int cost = 0;
        for (Edge edge : mst) {
            cost += edge.weight;
        }

        System.out.println("Total cost: " + cost);

    }

    private static class Edge implements Comparable<Edge> {
        private int startNode;
        private int endNode;
        private int weight;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static List<Edge> kruskal(int verticesCount, List<Edge> edges) {
        PARENTS = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            PARENTS[i] = i;
        }
        edges.sort(Edge::compareTo);

        List<Edge> minimumSpanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int rootStartNode = findRoot(edge.startNode, PARENTS);
            int rootEndNode = findRoot(edge.endNode, PARENTS);
            if(rootStartNode != rootEndNode) {
                minimumSpanningTree.add(edge);
                PARENTS[rootStartNode] = rootEndNode;
            }
        }
        return minimumSpanningTree;
    }

    public static int findRoot(int node, int[] parent) {
        int root = node;
        while(parent[root] != root) {
            root = parent[root];
        }
        /* Path compression: */
        while (node != root) {
            int currentParent = parent[root];
            parent[node] = root;
            node = currentParent;
        }

        return root;
    }
}