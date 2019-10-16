package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P02_LongestZigZagSubsequence {
    private static int[] NUMBERS;
    private static int[][] MATRIX;
    private static int[][] PREV_RESULT;
    private static int MAX_RESULT = 0;
    private static int MAX_INDEX_ROW = 0;
    private static int MAX_INDEX_COL = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        NUMBERS = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        MATRIX = new int[2][NUMBERS.length];
        MATRIX[0][0] = MATRIX[1][0] = 1;
        PREV_RESULT = new int[2][NUMBERS.length];
        PREV_RESULT[0][0] = PREV_RESULT[1][0] = -1;

        findLZigZagSec();

        String result = findResult();

        System.out.println(result.trim());

    }

    private static String findResult() {
        StringBuilder result = new StringBuilder();
        while(MAX_INDEX_COL >= 0) {
            result.insert(0, NUMBERS[MAX_INDEX_COL] + " ");
            MAX_INDEX_COL = PREV_RESULT[MAX_INDEX_ROW][MAX_INDEX_COL];
            if(MAX_INDEX_ROW == 1) {
                MAX_INDEX_ROW = 0;
            } else {
                MAX_INDEX_ROW = 1;
            }
        }
        return result.toString();
    }

    private static void findLZigZagSec() {
        for (int currentIndex = 1; currentIndex < NUMBERS.length; currentIndex++) {
            for (int prevIndex = 0; prevIndex < currentIndex; prevIndex++) {
                int currentNumber = NUMBERS[currentIndex];
                int prevNumber = NUMBERS[prevIndex];
                if(currentNumber > prevNumber &&
                        MATRIX[0][currentIndex] < MATRIX[1][prevIndex] + 1) {
                    MATRIX[0][currentIndex] = MATRIX[1][prevIndex] + 1;
                    PREV_RESULT[0][currentIndex] = prevIndex;
                }
                if(currentNumber < prevNumber &&
                        MATRIX[1][currentIndex] < MATRIX[0][prevIndex] + 1){
                    MATRIX[1][currentIndex] = MATRIX[0][prevIndex] + 1;
                    PREV_RESULT[1][currentIndex] = prevIndex;
                }
            }

            if(MATRIX[0][currentIndex] > MAX_RESULT) {
                MAX_RESULT = MATRIX[0][currentIndex];
                MAX_INDEX_ROW = 0;
                MAX_INDEX_COL = currentIndex;
            }
            if(MATRIX[1][currentIndex] > MAX_RESULT) {
                MAX_RESULT = MATRIX[1][currentIndex];
                MAX_INDEX_ROW = 1;
                MAX_INDEX_COL = currentIndex;
            }
        }
    }
}