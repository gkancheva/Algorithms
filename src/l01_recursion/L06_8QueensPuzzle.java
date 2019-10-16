package l01_recursion;

import java.util.HashSet;
import java.util.Set;

public class L06_8QueensPuzzle {
    private static final int SIZE = 8;
    private static boolean[][] chessboard = new boolean[SIZE][SIZE];

    private static Set<Integer> attackedRows = new HashSet<>();
    private static Set<Integer> attackedColumns = new HashSet<>();
    private static Set<Integer> attackedLeftDiagonals = new HashSet<>();
    private static Set<Integer> attackedRightDiagonals = new HashSet<>();

    public static void main(String[] args) {
        putQueens(0);
    }

    private static void putQueens(int row){
        if(row == SIZE) {
            printSolution();
        } else {
            for (int col = 0; col < SIZE; col++) {
                if(canPlaceQueen(row, col)) {
                    markAllAttackedPositions(row, col, true);
                    putQueens(row + 1);
                    markAllAttackedPositions(row, col, false);
                }
            }
        }
    }

    private static void markAllAttackedPositions(int row, int col, boolean mark) {
        if(mark) {
            attackedColumns.add(col);
            attackedRows.add(row);
            attackedLeftDiagonals.add(col - row);
            attackedRightDiagonals.add(row + col);
        } else {
            attackedColumns.remove(col);
            attackedRows.remove(row);
            attackedLeftDiagonals.remove(col - row);
            attackedRightDiagonals.remove(row + col);
        }
        chessboard[row][col] = mark;
    }

    private static boolean canPlaceQueen(int row, int col) {
        boolean occupied = attackedRows.contains(row) ||
                attackedColumns.contains(col) ||
                attackedLeftDiagonals.contains(col - row) ||
                attackedRightDiagonals.contains(row + col);
        return !occupied;
    }

    private static void printSolution() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char result = '-';
                if(chessboard[row][col]) {
                    result = '*';
                }
                sb.append(result).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

}