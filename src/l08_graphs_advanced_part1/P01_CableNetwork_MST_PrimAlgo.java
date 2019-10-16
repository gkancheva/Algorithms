package l08_graphs_advanced_part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//minimum spanning tree - Prim algorithm
public class P01_CableNetwork_MST_PrimAlgo {
    private static Map<Integer, List<Edge>> GRAPH = new HashMap<>();
    private static Set<Integer> SPANNING_TREE = new HashSet<>();
    private static int TOTAL_BUDGET = 0;
    private static int USED_BUDGET = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TOTAL_BUDGET = Integer.parseInt(reader.readLine().split(" ")[1]);
        int edges = Integer.parseInt(reader.readLine().split(" ")[1]);

        for (int i = 0; i < edges; i++) {
            String[] tokens = reader.readLine().split(" ");
            int firstNode = Integer.parseInt(tokens[0]);
            int secondNode = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);
            boolean isConnected = tokens.length > 3;

            Edge edge = new Edge(firstNode, secondNode, weight, isConnected);

            GRAPH.putIfAbsent(firstNode, new ArrayList<>());
            GRAPH.get(firstNode).add(edge);
            GRAPH.putIfAbsent(secondNode, new ArrayList<>());
            GRAPH.get(secondNode).add(edge);

            if(isConnected) {
                SPANNING_TREE.add(edge.firstNode);
                SPANNING_TREE.add(edge.secondNode);
            }
        }

        prim();

        System.out.println("Budget used: " + USED_BUDGET);
    }


    private static void prim() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for (int node : SPANNING_TREE) {
            queue.addAll(GRAPH.get(node));
        }

        while(!queue.isEmpty()) {
            Edge min = queue.remove();
            int nonTreeNode = -1;
            if(SPANNING_TREE.contains(min.firstNode)
                    && !SPANNING_TREE.contains(min.secondNode)) {
                nonTreeNode = min.secondNode;
            }
            if(!SPANNING_TREE.contains(min.firstNode)
                    && SPANNING_TREE.contains(min.secondNode)) {
                nonTreeNode = min.firstNode;
            }

            if(nonTreeNode == -1) {
                continue;
            }
            if(TOTAL_BUDGET >= min.weight) {
                TOTAL_BUDGET -= min.weight;
                USED_BUDGET += min.weight;
            } else {
                break;
            }

            SPANNING_TREE.add(nonTreeNode);
            queue.addAll(GRAPH.get(nonTreeNode));
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int firstNode;
        private int secondNode;
        private int weight;
        private boolean isConnected;

        public Edge(int first, int second, int weight, boolean isConnected) {
            this.firstNode = first;
            this.secondNode = second;
            this.weight = weight;
            this.isConnected = isConnected;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}