package l07_graphs.l01_connected_components;

import java.util.*;

public class Main {
    private static List<List<Integer>> GRAPH = new ArrayList<>();
    private static boolean[] VISITED;

    public static void main(String[] args) {
        GRAPH = readGraph();
        VISITED = new boolean[GRAPH.size()];

        List<Deque<Integer>> connectedComponents = getConnectedComponents(GRAPH);
        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.println(connectedComponent);
        }
    }

    private static List<List<Integer>> readGraph() {
        Scanner in = new Scanner(System.in);

        List<List<Integer>> graph = new ArrayList<>();
        int n = Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            List<Integer> connectedComponents = new ArrayList<>();

            String line = in.nextLine();
            if (line.equals("")) {
                graph.add(connectedComponents);
                continue;
            }
            String[] nodes = line.split(" ");

            for (String node : nodes) {
                connectedComponents.add(Integer.parseInt(node));
            }

            graph.add(connectedComponents);
        }
        return graph;
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> result = new ArrayList<>();
        for (int startNode = 0; startNode < GRAPH.size(); startNode++) {
            if(!VISITED[startNode]){
                Deque<Integer> currentComponent = new ArrayDeque<>();
                dfs(startNode, currentComponent);
                result.add(currentComponent);
            }
        }
        return result;
    }

    private static void dfs(int vertex, Deque<Integer> component) {
        if(!VISITED[vertex]) {
            VISITED[vertex] = true;
            for (int child : GRAPH.get(vertex)) {
                dfs(child, component);
            }
            component.add(vertex);
        }
    }
}
