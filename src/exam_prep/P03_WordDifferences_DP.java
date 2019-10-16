package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Longest common subsequence
public class P03_WordDifferences_DP {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String first = reader.readLine();
        String second = reader.readLine();

        int length = getLengthOfLongestCommonSubsequence(first, second);

        int count = (first.length() - length) + (second.length() - length);

        System.out.println("Deletions and Insertions: " + count);

    }

    private static int getLengthOfLongestCommonSubsequence(String str1, String str2) {
        int[][] lcs = new int[str1.length() + 1][str2.length() + 1];
        for (int row = 0; row <= str1.length(); row++) {
            for (int col = 0; col <= str2.length(); col++) {
                if(row == 0 || col == 0) {
                    lcs[row][col] = 0;
                } else if(str1.charAt(row - 1) == str2.charAt(col - 1)) {
                    lcs[row][col] = lcs[row - 1][col - 1] + 1;
                } else {
                    lcs[row][col] = Math.max(lcs[row - 1][col], lcs[row][col - 1]);
                }
            }
        }
        return lcs[str1.length()][str2.length()];
    }
}