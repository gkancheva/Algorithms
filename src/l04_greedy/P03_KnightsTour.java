package l04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P03_KnightsTour {
    private static int[][] CHESSBOARD;

    private static final int[] MOVE_ROW = new int[]{1, -1, 1, -1, 2, 2, -2, -2};
    private static final int[] MOVE_COL = new int[]{2, +2, -2, -2, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        CHESSBOARD = new int[num][num];

        int i = 1;
        int row = 0;
        int col = 0;

        while(i <= num * num){
            CHESSBOARD[row][col] = i;
            int[] nextStepCoordinates = findNextStep(row, col);
            row = nextStepCoordinates[0];
            col = nextStepCoordinates[1];
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for (int[] rows : CHESSBOARD) {
            for (int c : rows) {
                sb.append(String.format("%3d ", c));
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n ");
        }
        System.out.println(sb.toString().trim());
    }

    private static int[] findNextStep(int row, int col) {
        int minSteps = Integer.MAX_VALUE;
        int[] coordinates = new int[]{row, col};

        for (int i = 0; i < 8; i++) {
            if(isInBounds(row, col, i) && isNotVisited(row, col, i)) {
                int steps = getCountOfPossibleSteps(row + MOVE_ROW[i], col + MOVE_COL[i]);
                if(steps < minSteps) {
                    minSteps = steps;
                    coordinates[0] = row + MOVE_ROW[i];
                    coordinates[1] = col + MOVE_COL[i];
                }
            }
        }
        return coordinates;
    }

    private static int getCountOfPossibleSteps(int row, int col) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if(isInBounds(row, col, i) && isNotVisited(row, col, i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isNotVisited(int row, int col, int i) {
        return CHESSBOARD[row + MOVE_ROW[i]][col + MOVE_COL[i]] == 0;
    }

    private static boolean isInBounds(int row, int col, int i) {
        return row + MOVE_ROW[i] < CHESSBOARD.length && row + MOVE_ROW[i] >= 0 &&
                col + MOVE_COL[i] < CHESSBOARD.length && col + MOVE_COL[i] >= 0;
    }


}