package l03_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Choose k elements from x elements **/
public class Combinations {
    private static long[] FACTORIALS;
    private static char[] ELEMENTS;
    private static char[] COMBINATIONS;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ELEMENTS = reader.readLine().replace(" ", "").toCharArray();
        K = Integer.parseInt(reader.readLine());
        COMBINATIONS = new char[K];
        FACTORIALS = new long[ELEMENTS.length + 1];

        combinationsWithoutRepetion(0, 0);

        /** count = n! / k!(n-k)! **/
        //long countWithoutRepetition = getCountOfCombWithoutRep();
        //System.out.println(countWithoutRepetition);

        //combinationsWithRepetition(0, 0);
    }

    private static long getCountOfCombWithoutRep() {
        /** count = n! / k!(n-k)! **/
        long firstNum = factorial(ELEMENTS.length);
        long secondNum = factorial(ELEMENTS.length - K);
        long kFact = factorial(K);
        //TODO!
        return firstNum/kFact * secondNum;
    }

    private static long factorial(int num) {
        if(num == 1) {
            FACTORIALS[1] = 1;
            return 1;
        }
        if(FACTORIALS[num] != 0) {
            return FACTORIALS[num];
        }
        long result = num * factorial(num - 1);
        FACTORIALS[num] = result;
        return result;
    }

    private static void combinationsWithRepetition(int index, int start) {
        if(index >= K) {
            printCombinations(COMBINATIONS);
        } else {
            for (int i = start; i < ELEMENTS.length; i++) {
                COMBINATIONS[index] = ELEMENTS[i];
                combinationsWithRepetition(index + 1, i);
            }
        }
    }

    private static void combinationsWithoutRepetion(int index, int start) {
        if(index >= K) {
            printCombinations(COMBINATIONS);
        } else {
            for (int i = start; i < ELEMENTS.length; i++) {
                COMBINATIONS[index] = ELEMENTS[i];
                combinationsWithoutRepetion(index + 1, i + 1);
            }
        }
    }

    private static void printCombinations(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (char ch : arr) {
            sb.append(ch).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}