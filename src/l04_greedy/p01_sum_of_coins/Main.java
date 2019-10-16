package l04_greedy.p01_sum_of_coins;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));

        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
        // fancy printing
    }

    static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {

        Map<Integer, Integer> result = new HashMap<>();

        int[] sorted = IntStream.of(coins)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i)
                .toArray();

        int coinIndex = 0;
        int currentSum = 0;

        while (coinIndex < sorted.length && currentSum < targetSum) {
            int currentCoin = sorted[coinIndex];

            int numberOfCoins = (targetSum - currentSum) / currentCoin;
            if(numberOfCoins == 0) {
                coinIndex++;
                continue;
            }

            if(currentSum + (currentCoin * numberOfCoins) <= targetSum) {
                result.put(currentCoin, numberOfCoins);
                currentSum += currentCoin * numberOfCoins;
                coinIndex++;
            }
        }
        if(currentSum != targetSum) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
