package l07_graphs.l02_topological_sorting;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<String>> graph = new HashMap<>();
        String input = reader.readLine();
        while(!input.equals("")) {
            String[] tokens = input.split(" ->");
            String vertex = tokens[0].replace("\"", "");
            graph.putIfAbsent(vertex, new ArrayList<>());
            if(tokens.length > 1) {
                tokens = tokens[1].split(",");
                for (String token : tokens) {
                    String current = token.replaceAll("[\" ]", "");
                    graph.get(vertex).add(current);
                }
            }
            input = reader.readLine();
        }

        topSort(graph);

    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        for (String node : graph.keySet()) {
            topSortDfs(graph, node, visited, cycles, result);
        }
        return result;
    }

    private static void topSortDfs(Map<String, List<String>> graph, String node, Set<String> visited, Set<String> cycles, List<String> result) {
        if(cycles.contains(node)) {
            throw new IllegalArgumentException();
        }
        if(!visited.contains(node)) {
            visited.add(node);
            cycles.add(node);
            for (String child : graph.get(node)) {
                topSortDfs(graph, child, visited, cycles, result);
            }
            cycles.remove(node);
            result.add(0, node);
        }
    }
}
