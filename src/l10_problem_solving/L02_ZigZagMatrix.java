package l10_problem_solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//dynamic programming
public class L02_ZigZagMatrix {
    private static int[][] MAX_PATHS;
    private static int[][] PREV_ROW_IND;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(br.readLine());
        int cols = Integer.parseInt(br.readLine());

        int[][] matrix = new int[rows][];
        readMatrix(br, rows, matrix);

        MAX_PATHS = new int[rows][cols];
        PREV_ROW_IND = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            MAX_PATHS[row][0] = matrix[row][0];
        }

        for(int col = 1; col < cols; col++) {
            for(int row = 0; row < rows; row++) {
                int previousMax = 0;
                if(col % 2 != 0) {
                    for(int i = row + 1; i < rows; i++) {
                        if(MAX_PATHS[i][col - 1] > previousMax) {
                            previousMax = MAX_PATHS[i][col - 1];
                            PREV_ROW_IND[row][col] = i;
                        }
                    }
                } else {
                    for (int i = 0; i < row; i++) {
                        if (MAX_PATHS[i][col - 1] > previousMax) {
                            previousMax = MAX_PATHS[i][col - 1];
                            PREV_ROW_IND[row][col] = i;
                        }
                    }
                }

                MAX_PATHS[row][col] = previousMax + matrix[row][col];
            }
        }

        int currentRowIndex = getLastRowIndex(cols);
        String path = recoverPath(cols, matrix, currentRowIndex);
        System.out.println(path);
    }

    private static String recoverPath(int cols, int[][] matrix, int row) {
        StringBuilder path = new StringBuilder();
        int col = matrix[0].length - 1;
        int sum = 0;
        while(col >= 0) {
            String str = matrix[row][col] + " + ";
            sum += matrix[row][col];
            path.insert(0, str);
            row = PREV_ROW_IND[row][col];
            col--;
        }
        path.insert(0, sum + " = ");
        return path.delete(path.length() - 3, path.length()).toString();
    }

    private static int getLastRowIndex(int cols) {
        int currentRowIndex = -1;
        int globalMax = 0;
        for(int row = 0; row < MAX_PATHS.length; row++) {
            if(MAX_PATHS[row][cols - 1] > globalMax) {
                globalMax = MAX_PATHS[row][cols - 1];
                currentRowIndex = row;
            }
        }
        return currentRowIndex;
    }

    private static void readMatrix(BufferedReader br, int rows, int[][] matrix) throws IOException {
        for (int i = 0; i < rows; i++) {
            String[] line = br.readLine().split(",");
            matrix[i] = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
    }
}