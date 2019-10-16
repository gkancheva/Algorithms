package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L03_MoveDownRight {
    private static long[][] MATRIX;
    private static long[][] SUMS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());

        MATRIX = new long[rows][cols];
        SUMS = new long[rows][cols];

        readMatrix(reader);

        fillSums(rows, cols);

        printPath(rows, cols);

    }

    private static void printPath(int rows, int cols) {
        int row = rows - 1;
        int col = cols - 1;
        StringBuilder sb = new StringBuilder(String.format("[%d, %d] ", row, col));
        while (row != 0 || col != 0) {
            long top = -1;
            long left = -1;
            if(row - 1 >= 0) {
                top = SUMS[row - 1][col];
            }
            if(col - 1 >= 0) {
                left = SUMS[row][col - 1];
            }
            if(top > left) {
                sb.insert(0, String.format("[%d, %d] ", row - 1, col));
                row -= 1;
            } else {
                sb.insert(0, String.format("[%d, %d] ", row, col - 1));
                col -= 1;
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static void fillSums(int rows, int cols) {
        SUMS[0][0] = MATRIX[0][0];
        for (int row = 1; row < rows; row++) {
            SUMS[row][0] = SUMS[row - 1][0] + MATRIX[row][0];
        }

        for (int col = 1; col < cols; col++) {
            SUMS[0][col] = SUMS[0][col - 1] + MATRIX[0][col];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                long result = MATRIX[row][col];
                result += Math.max(SUMS[row][col - 1], SUMS[row - 1][col]);
                SUMS[row][col] = result;
            }
        }
    }

    private static void readMatrix(BufferedReader reader) throws IOException {
        for (int row = 0; row < MATRIX.length; row++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int col = 0; col < tokens.length; col++) {
                MATRIX[row][col] = tokens[col];
            }
        }
    }
}