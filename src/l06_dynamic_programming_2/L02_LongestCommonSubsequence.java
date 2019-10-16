package l06_dynamic_programming_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L02_LongestCommonSubsequence {
    private static int[][] LCS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input1 = reader.readLine();
        String input2 = reader.readLine();

        LCS = new int[input1.length() + 1][input2.length() + 1];

        fillTheMatrix(input1, input2);

//        int currentRow = input1.length();
//        int currentCol = input2.length();
//
//        StringBuilder sb = new StringBuilder();
//
//        while(currentRow > 0 && currentCol > 0) {
//            if(input1.charAt(currentRow - 1) == input2.charAt(currentCol - 1)) {
//                sb.insert(0, input1.charAt(currentRow - 1));
//                currentRow--;
//                currentCol--;
//            } else if(LCS[currentRow - 1][currentCol] == LCS[currentRow][currentCol - 1]) {
//                currentRow--;
//            } else {
//                currentCol--;
//            }
//        }
//
//        System.out.println(sb.toString());

    }

    private static void fillTheMatrix(String first, String second) {
        for (int row = 1; row <= first.length(); row++) {
            for (int col = 1; col <= second.length(); col++) {
                int up = LCS[row - 1][col];
                int left = LCS[row][col - 1];
                int result = Math.max(up, left);
                if(first.charAt(row - 1) == second.charAt(col - 1)) {
                    result = Math.max(1 + LCS[row - 1][col - 1], result);
                }
                LCS[row][col] = result;
            }
        }
        System.out.println(LCS[first.length()][second.length()]);
    }
}
