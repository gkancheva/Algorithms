package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//graph -> connected components (undirected, unweighted)
public class AL03_Lumber {
    private static int COUNT = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" ");
        int rectanglesCount = Integer.parseInt(tokens[0]); //дънер
        int queriesCount = Integer.parseInt(tokens[1]);

        List<Rectangle> rectangles = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[rectanglesCount + 1]; //rectangles that intersects

        readInputAndFillGraph(reader, rectanglesCount, rectangles, graph);

        //find connected components
        boolean[] visited = new boolean[rectanglesCount + 1];
        int[] componentId = new int[rectanglesCount + 1];
        for (int i = 1; i <= rectanglesCount; i++) {
            if(!visited[i]) {
                dfs(i, visited, graph, componentId);
                COUNT++;
            }
        }

        //check if two rectangles has the same component id (are from the same connected component)
        for (int i = 0; i < queriesCount; i++) {
            tokens = reader.readLine().split(" ");
            int fromRectangle = Integer.parseInt(tokens[0]);
            int toRectangle = Integer.parseInt(tokens[1]);

            System.out.println(componentId[fromRectangle] == componentId[toRectangle] ? "YES" : "NO");
        }

    }

    private static void dfs(int node, boolean[] visited, List<Integer>[] graph, int[] componentIds) {
        visited[node] = true;
        componentIds[node] = COUNT;
        for (int child : graph[node]) {
            if(!visited[child]) {
                dfs(child, visited, graph, componentIds);
            }
        }
    }

    private static void readInputAndFillGraph(BufferedReader br, int count, List<Rectangle> rectangles, List<Integer>[] graph) throws IOException {
        for (int i = 1; i <= count; i++) {
            int[] coord = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Rectangle currentRectangle = new Rectangle(i, coord[0], coord[1], coord[2], coord[3]);

            graph[i] = new ArrayList<>();
            for (Rectangle rectangle : rectangles) {
                if(rectangle.intersects(currentRectangle)) {
                    graph[rectangle.id].add(i);
                    graph[i].add(rectangle.id);
                }
            }

            rectangles.add(currentRectangle);
        }
    }

    private static class Rectangle {
        private int id;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Rectangle(int id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        private boolean intersects(Rectangle other) {
            boolean horizontalIntersect =
                    this.x1 <= other.x2 && this.x2 >= other.x1;
            boolean verticalIntersect =
                    this.y1 >= other.y2 && this.y2 <= other.y1; //mathematical coordinate system
            return horizontalIntersect && verticalIntersect;
        }
    }
}