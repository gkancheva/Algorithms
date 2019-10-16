package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class L07_PathsInLabyrinth {
    private static List<Character> PATH = new ArrayList<>();
    private static char[][] MATRIX;
    private static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        MATRIX = new char[rows][cols];
        VISITED = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            String line = reader.readLine();
            for (int col = 0; col < cols; col++) {
                MATRIX[row][col] = line.charAt(col);
            }
        }

        findPaths(0, 0, 'S');
    }

    private static void findPaths(int row, int col, char direction) {
        if(!isInBounds(row, col)) {
            return;
        }
        PATH.add(direction);
        if(isExit(row, col)) {
            printPath();
        } else if(!isVisited(row, col) && isFree(row, col)) {
            mark(row, col);
            findPaths(row, col + 1, 'R');
            findPaths(row + 1, col, 'D');
            findPaths(row, col - 1, 'L');
            findPaths(row - 1, col, 'U');
            unmark(row, col);
        }
        PATH.remove(PATH.size() - 1);
    }

    private static void unmark(int row, int col) {
        VISITED[row][col] = false;
    }

    private static void mark(int row, int col) {
        VISITED[row][col] = true;
    }



    private static boolean isFree(int row, int col) {
        return MATRIX[row][col] == '-';
    }

    private static boolean isVisited(int row, int col) {
        return VISITED[row][col];
    }

    private static void printPath() {
        for (int i = 1; i < PATH.size(); i++) {
            System.out.print(PATH.get(i));
        }
        System.out.println();
    }

    private static boolean isExit(int row, int col) {
        return MATRIX[row][col] == 'e';
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < MATRIX.length
                && col >= 0 && col < MATRIX[row].length;
    }
}