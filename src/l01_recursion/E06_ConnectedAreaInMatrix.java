package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.TreeSet;

public class E06_ConnectedAreaInMatrix {
    private static char[][] MATRIX;
    private static boolean[][] VISITED;
    private static SortedSet<Area> FOUND_AREAS = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        MATRIX = new char[rows][cols];
        VISITED = new boolean[rows][cols];
        readMatrix(reader, rows, cols);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                findAreas(row, col);
            }
        }

        System.out.println("Total areas found: " + FOUND_AREAS.size());

        int num = 1;
        for (Area area : FOUND_AREAS) {
            String output = String.format(
                    "Area #%d at (%d, %d), size: %d", num++,
                    area.getRow(), area.getCol(), area.getSize());
            System.out.println(output);
        }
    }

    private static void findAreas(int row, int col) {
        if(MATRIX[row][col] == '*' || VISITED[row][col]) {
            return;
        }
        Area area = new Area(row, col);
        fillArea(row, col, area);
        FOUND_AREAS.add(area);
    }

    private static void fillArea(int row, int col, Area area) {
        if(!isInBounds(row, col) || VISITED[row][col] || MATRIX[row][col] == '*') {
            return;
        }
        VISITED[row][col] = true;
        area.incrementSize();

        fillArea(row + 1, col, area);
        fillArea(row, col + 1, area);
        fillArea(row - 1, col, area);
        fillArea(row, col - 1, area);
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && col >= 0
                && row < MATRIX.length
                && col < MATRIX[row].length;
    }

    private static void readMatrix(BufferedReader reader, int rows, int cols) throws IOException {
        for (int row = 0; row < rows; row++) {
            String input = reader.readLine();
            for (int col = 0; col < input.length(); col++) {
                MATRIX[row][col] = input.charAt(col);
            }
        }
    }

    public static class Area implements Comparable<Area> {

        private int row;
        private int col;
        private int size;

        public Area(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 0;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getSize() {
            return size;
        }

        public void incrementSize() {
            this.size++;
        }

        @Override
        public int compareTo(Area o) {
            int compare = Integer.compare(o.getSize(), this.size);
            if(compare == 0) {
                compare = Integer.compare(this.getRow(), o.getRow());
            }
            if(compare == 0) {
                compare = Integer.compare(this.getCol(), o.getCol());
            }
            return compare;
        }
    }
}