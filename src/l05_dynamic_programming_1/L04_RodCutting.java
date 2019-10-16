package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L04_RodCutting {
    private static int[] PRICES;
    private static int[] BEST_PRICES;
    private static int[] PREV_BEST;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        PRICES = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int length = Integer.parseInt(reader.readLine());
        BEST_PRICES = new int[length + 1];
        PREV_BEST = new int[length + 1];

        int result = cutRod(length);
        System.out.println(result);

        while(length - PREV_BEST[length] != 0) {
            System.out.print(PREV_BEST[length] + " ");
            length = length - PREV_BEST[length];
        }
        System.out.println(PREV_BEST[length]);
    }

    private static int cutRod(int n) {
        for (int i = 1; i <= n; i++) {
            int currentBest = BEST_PRICES[i];
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(BEST_PRICES[i], PRICES[j] + BEST_PRICES[i - j]);
                if(currentBest > BEST_PRICES[i]) {
                    BEST_PRICES[i] = currentBest;
                    PREV_BEST[i] = j;
                }
            }
        }
        return BEST_PRICES[n];
    }
}