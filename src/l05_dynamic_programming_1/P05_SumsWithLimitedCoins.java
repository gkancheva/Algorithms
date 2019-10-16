package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P05_SumsWithLimitedCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int sum = Integer.parseInt(reader.readLine());

        int count = findCountOfSums(numbers, sum);

        System.out.println(count);

    }

    private static int findCountOfSums(int[] coins, int sum) {
        int[][] maxCount = new int[coins.length + 1][sum + 1];
        for (int i = 0; i <= coins.length; i++) {
            maxCount[i][0] = 1;
        }

        for (int coinIndex = 1; coinIndex <= coins.length; coinIndex++) {
            for (int currSum = sum; currSum >= 0; currSum--) {
                if (coins[coinIndex - 1] <= currSum && maxCount[coinIndex - 1][currSum - coins[coinIndex - 1]] != 0){
                    maxCount[coinIndex][currSum]++;
                } else {
                    maxCount[coinIndex][currSum] = maxCount[coinIndex - 1][currSum];
                }
            }
        }

        int count = 0;
        for (int i = 0; i <= coins.length; i++) {
            if (maxCount[i][sum] != 0) {
                count++;
            }
        }

        return count;
    }
}