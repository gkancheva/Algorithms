package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P01_BinomialCoefficients {
    private static long[][] COEFFICIENTS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        COEFFICIENTS = new long[n + 1][k + 1];

        long result = findBinomialCoeff(n, k);
        System.out.println(result);

    }

    private static long findBinomialCoeff(int n, int k) {
        if(k == 0 || k == n) {
             return 1;
        }
        if(COEFFICIENTS[n][k] != 0) {
            return COEFFICIENTS[n][k];
        }
        long result = findBinomialCoeff(n - 1, k - 1) + findBinomialCoeff(n - 1, k);
        COEFFICIENTS[n][k] = result;
        return COEFFICIENTS[n][k];
    }
}