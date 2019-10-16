package l08_graphs_advanced_part1.Kruskal_MinimumSpanningTree;

import java.util.ArrayList;
import java.util.List;

public class KruskalAlgorithm {
    private static int[] PARENTS;

    public static List<Edge> kruskal(int verticesCount, List<Edge> edges) {
        PARENTS = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            PARENTS[i] = i;
        }
        edges.sort(Edge::compareTo);

        List<Edge> minimumSpanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int rootStartNode = findRoot(edge.getStartNode(), PARENTS);
            int rootEndNode = findRoot(edge.getEndNode(), PARENTS);
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
