package l03_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinomialCoeff_NChooseKCount {
    private static long[][] OPTIMIZED_MATRIX;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        OPTIMIZED_MATRIX = new long[n + 1][k + 1];

        long count = getCountOfNChooseK(n, k);

        System.out.println(count);
    }

    private static long getCountOfNChooseK(int n, int k) {
        if(k > n) {
            return 0;
        }
        if(k == 0 || k == n) {
            return 1;
        }
        if(OPTIMIZED_MATRIX[n][k] != 0) {
            return OPTIMIZED_MATRIX[n][k];
        }
        long num = getCountOfNChooseK(n - 1, k - 1);
        long secNum = getCountOfNChooseK(n - 1, k);
        OPTIMIZED_MATRIX[n][k] = num + secNum;
        return OPTIMIZED_MATRIX[n][k];
    }
}