package l07_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P03_CyclesInGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> graph = new HashMap<>();

        String input = reader.readLine();
        while(input != null && !input.equals("")) {
            String[] tokens = input.split("–");
            graph.putIfAbsent(tokens[0], new ArrayList<>());
            graph.putIfAbsent(tokens[1], new ArrayList<>());
            graph.get(tokens[0]).add(tokens[1]);
            graph.get(tokens[1]).add(tokens[0]);

            input = reader.readLine();
        }

        Set<String> visited = new HashSet<>();
        for (String node : graph.keySet()) {
            Set<String> cycle = new HashSet<>();
            boolean cyclic = checkIfCyclic(node, graph, visited, cycle, null);
            if(cyclic) {
                System.out.println("Acyclic: No");
                return;
            }
        }
        System.out.println("Acyclic: Yes");
    }

    private static boolean checkIfCyclic(String node, Map<String, List<String>> graph, Set<String> visited, Set<String> cycle, String parent) {
        boolean isCyclic = false;
        if(cycle.contains(node)) {
            return true;
        }
        if(!visited.contains(node)) {
            visited.add(node);
            cycle.add(node);
            if(graph.containsKey(node)) {
                for (String child : graph.get(node)) {
                    if(!child.equals(parent)) {
                        isCyclic = isCyclic || checkIfCyclic(child, graph, visited, cycle, node);
                    }
                }
                cycle.remove(node);
            }
        }
        return isCyclic;
    }
}