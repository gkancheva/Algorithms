package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E03_CombinationsWithRepetition {
    private static int[] COMBINATIONS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());

        COMBINATIONS = new int[k];

        generateCombinations(n, 0);
    }

    private static void generateCombinations(int n, int index) {
        if(index == COMBINATIONS.length) {
            for (int combination : COMBINATIONS) {
                System.out.print(combination + " ");
            }
            System.out.println();
        } else {
            int i = index - 1 >= 0 ? COMBINATIONS[index - 1] : 1;
            for (; i <= n; i++) {
                COMBINATIONS[index] = i;
                generateCombinations(n, index + 1);
            }
        }
    }
}