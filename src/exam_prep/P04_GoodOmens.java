package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P04_GoodOmens {
    private static int[] PARENTS;
    private static List<Path> PATHS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        for (int i = 0; i < m; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = tokens[0];
            int to = tokens[1];
            int expense = tokens[2];
            Path path = new Path(from, to, expense);
            PATHS.add(path);
        }

        PARENTS = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            PARENTS[i] = i;
        }
        PATHS.sort(Path::compareTo);

        List<Path> mst = new ArrayList<>();
        for (Path path : PATHS) {
            int rootStartNode = findRoot(path.from, PARENTS);
            int rootEndNode = findRoot(path.to, PARENTS);
            if(rootStartNode != rootEndNode) {
                mst.add(path);
                PARENTS[rootStartNode] = rootEndNode;
            }
        }

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mst.size(); i++) {
            String output = String.format("[%d <=> %d] ", mst.get(i).from, mst.get(i).to);
            sb.append(output);
            sum += mst.get(i).expense;
        }
        sb.trimToSize();
        sb.append(System.lineSeparator());
        sb.append(sum);
        System.out.println(sb.toString());

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

    private static class Path implements Comparable<Path> {
        private int from;
        private int to;
        private int expense;

        public Path(int from, int to, int expense) {
            this.from = from;
            this.to = to;
            this.expense = expense;
        }

        @Override
        public int compareTo(Path o) {
            return Integer.compare(this.expense, o.expense);
        }
    }
}