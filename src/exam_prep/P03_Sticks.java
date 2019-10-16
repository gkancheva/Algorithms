package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P03_Sticks {

    private static Map<Integer, List<Integer>> GRAPH = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int vertexCount = Integer.parseInt(reader.readLine());
        int edgeCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < vertexCount; i++) {
            GRAPH.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int stickOnTop = tokens[0];
            int stickBellow = tokens[1];
            GRAPH.get(stickOnTop).add(stickBellow);
        }

        List<Integer> topSorted = topSort();
        StringBuilder output = new StringBuilder();
        for (Integer integer : topSorted) {
            output.append(integer).append(" ");
        }

        System.out.println(output.toString().trim());
    }

    private static List<Integer> topSort() {
    List<Integer> sortedNodes = new ArrayList<>();
    Map<Integer, List<Integer>> incomingNodes = new TreeMap<>(Comparator.reverseOrder());
    Map<Integer, Boolean> isVisited = new HashMap<>();
    int visitedCount = 0;

        for (Integer node : GRAPH.keySet()) {
        incomingNodes.putIfAbsent(node, new ArrayList<>());
        isVisited.putIfAbsent(node, false);
        for (Integer dependency : GRAPH.get(node)) {
            incomingNodes.putIfAbsent(dependency, new ArrayList<>());
            incomingNodes.get(dependency).add(node);
        }
    }

        while (visitedCount < isVisited.size()) {
        boolean hasChanged = false;
        for (Integer node : incomingNodes.keySet()) {
            if (!isVisited.get(node) && incomingNodes.get(node).isEmpty()) {
                sortedNodes.add(node);
                hasChanged = true;
                isVisited.put(node, true);
                visitedCount++;
                for (Integer dependency : GRAPH.get(node)) {
                    incomingNodes.get(dependency).remove(node);
                }
                break;
            }
        }
        if (!hasChanged) {
            System.out.println("Cannot lift all sticks");
            break;
        }
    }
        return sortedNodes;
    }
}