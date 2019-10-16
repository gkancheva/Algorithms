package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P02_CableMerchant {
    private static int[] PRICES;
    private static int[] BEST_PRICES;
    private static int[] BEST_COMBINATION;
    private static int CONNECTOR_PRICE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] pricesStringArr = reader.readLine().split("\\s+");
        PRICES = new int[pricesStringArr.length + 1];
        PRICES[0] = 0;
        for (int i = 1; i < PRICES.length; i++) {
            PRICES[i] = Integer.parseInt(pricesStringArr[i - 1]);
        }

        CONNECTOR_PRICE = Integer.parseInt(reader.readLine());

        for (int i = 1; i < PRICES.length; i++) {
            BEST_PRICES = new int[PRICES.length];
            BEST_COMBINATION = new int[PRICES.length];
            System.out.print(cutRod(i) + " ");
        }
    }

    private static int cutRod(int n) {
        int currentBest = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(BEST_PRICES[i], PRICES[j] + BEST_PRICES[i - j]);
                if (currentBest > BEST_PRICES[i]) {
                    BEST_PRICES[i] = currentBest;
                    BEST_COMBINATION[i] = j;
                }
            }
        }
        return Math.max(PRICES[n], calculateFinalPrice(n));
    }

    private static int calculateFinalPrice(int n) {
        int finalPrice = BEST_PRICES[n];
        int pieces = 0;
        while (n - BEST_COMBINATION[n] != 0) {
            pieces++;
            n = n - BEST_COMBINATION[n];
        }
        return finalPrice - 2 * pieces * CONNECTOR_PRICE;
    }
}