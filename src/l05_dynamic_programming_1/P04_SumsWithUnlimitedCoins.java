package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P04_SumsWithUnlimitedCoins {
    private static int[] COINS;
    private static int SUM;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        COINS = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        SUM = Integer.parseInt(reader.readLine());

        int count = getCombWithRepetition();

        System.out.println(count);

    }

    private static int getCombWithRepetition() {
        int[][] maxCount = new int[COINS.length + 1][SUM + 1];
        for (int i = 0; i < maxCount.length; i++) {
            maxCount[i][0] = 1;
        }

        for (int coinIndex = 1; coinIndex <= COINS.length; coinIndex++) {
            int currentCoin = COINS[coinIndex - 1];
            for (int currSum = 1; currSum <= SUM ; currSum++) {
                if(currentCoin <= currSum) {
                    maxCount[coinIndex][currSum] = maxCount[coinIndex - 1][currSum] + maxCount[coinIndex][currSum - currentCoin];
                } else {
                    maxCount[coinIndex][currSum] = maxCount[coinIndex -1][currSum];
                }
            }
        }
        return maxCount[COINS.length][SUM];
    }
}