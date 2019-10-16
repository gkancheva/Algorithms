package l07_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class P02_AreasInMatrix {
    private static BufferedReader READER;
    private static char[][] MATRIX;
    private static boolean[][] VISITED;
    private static Map<Character, Integer> AREAS = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        READER = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(READER.readLine());

        MATRIX = new char[size][];
        VISITED = new boolean[size][];

        readMatrix();

        System.out.println("Areas: " + getAreasCount());

        for (Character ch : AREAS.keySet()) {
            System.out.printf("Letter '%c' -> %d\n", ch, AREAS.get(ch));
        }

    }

    private static int getAreasCount() {
        int count = 0;
        for (int i = 0; i < MATRIX.length; i++) {
            for (int j = 0; j < MATRIX[0].length; j++) {
                if(!VISITED[i][j]) {
                    char ch = MATRIX[i][j];
                    AREAS.putIfAbsent(ch, 0);
                    AREAS.put(ch, AREAS.get(ch) + 1);
                    count++;
                    markCellVisited(i, j, ch);
                }
            }
        }
        return count;
    }

    private static void markCellVisited(int row, int col, char ch) {
        VISITED[row][col] = true;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(Math.abs(i) == Math.abs(j)
                        || row + i < 0 || row + i >= MATRIX.length
                        || col + j < 0 || col + j >= MATRIX[0].length) {
                    continue;
                }
                if(MATRIX[row + i][col + j] == ch && !VISITED[row + i][col + j]) {
                    markCellVisited(row + i, col + j, ch);
                }
            }
        }
    }


    private static void readMatrix() throws IOException {
        for (int row = 0; row < MATRIX.length; row++) {
            String input = READER.readLine();
            MATRIX[row] = input.toCharArray();
            VISITED[row] = new boolean[input.length()];
        }
    }
}