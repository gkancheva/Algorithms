package l09_graph_advanced_part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaxTasksAssignment {

    private static int[][] graph;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine().split(" ")[1]);
        int tasks = Integer.parseInt(br.readLine().split(" ")[1]);

        int nodes = people + tasks + 2;

        initializeGraph(nodes, people, tasks);

        readInput(people, tasks, br);

        parents = new int[graph.length];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }

        int start = 0;
        int end = graph.length - 1;

        while(bfs(start, end)) {
            int currentNode = end;
            while (currentNode != start) {
                int prevNode = parents[currentNode];
                graph[prevNode][currentNode] = 0;
                graph[currentNode][prevNode] = 1;
                currentNode = prevNode;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        SortedSet<String> result = new TreeSet<>();
        boolean[] visited = new boolean[graph.length];
        queue.add(end);
        visited[end] = true;
        while(!queue.isEmpty()) {
            int node = queue.pop();
            for (int child = 0; child < graph.length; child++) {
                if(graph[node][child] > 0 && !visited[child]) {
                    queue.add(child);
                    visited[child] = true;
                    if(node != end && node != start &&
                        child != end && child != start) {
                        result.add(((char)(child - 1 + 'A')) + "-" + (node - people));
                    }
                }
            }
        }

        for (String s : result) {
            System.out.println(s);
        }

    }

    private static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[graph.length];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int node = queue.pop();
            for (int child = 0; child < graph.length; child++) {
                if(!visited[child] && graph[node][child] > 0) {
                    queue.add(child);
                    visited[child] = true;
                    parents[child] = node;
                }
            }
        }
        return visited[end];
    }

    private static void initializeGraph(int nodes, int people, int tasks) {
        graph = new int[nodes][nodes];

        for (int i = 1; i < people + 1; i++) {
            graph[0][i] = 1;
        }
        for (int i = 1 + people; i < people + tasks + 1; i++) {
            graph[i][people + tasks + 1] = 1;
        }
    }

    private static void readInput(int people, int tasks, BufferedReader br) throws IOException {
        for (int i = 1; i < 1 + people; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 1 + people; j < people + tasks + 1; j++) {
                if (line[j - 1 - people] == 'Y') {
                    graph[i][j] = 1;
                }
            }
        }
    }

}