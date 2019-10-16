package l03_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** All possible to order X elements in K places **/
public class Variations {
    private static long[] FACTORIALS;
    private static char[] ELEMENTS;
    private static char[] VARIATIONS;
    private static boolean[] USED;
    private static int K_NUMBER;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ELEMENTS = reader.readLine().replace(" ", "").toCharArray();
        K_NUMBER = Integer.parseInt(reader.readLine());
        VARIATIONS = new char[K_NUMBER];
        FACTORIALS = new long[ELEMENTS.length];
        USED = new boolean[ELEMENTS.length];

        //variationsWithoutRepetion(0);

        /** count = n!/(n - k)! **/
        //long countOfVariationsWithoutRepetition = getCountOfVariationsWithoutRepetion();
        //System.out.println(countOfVariationsWithoutRepetition);

        variationsWithRepetition(0);

        /** count = n in grade k **/
        long countVariationsWithRepetions = getCountOfVariationsWithRepetition();
        System.out.println(countVariationsWithRepetions);
    }

    private static long getCountOfVariationsWithRepetition() {
        int result = ELEMENTS.length;
        for (int i = 1; i < K_NUMBER; i++) {
            result *= ELEMENTS.length;
        }
        return result;
    }

    private static long getCountOfVariationsWithoutRepetion() {
        /** count = n!/(n - k)! **/
        long firstNum = factorial(ELEMENTS.length);
        long secondNum = factorial(ELEMENTS.length - K_NUMBER);
        return firstNum / secondNum;
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

    private static void variationsWithRepetition(int index) {
        if(index >= K_NUMBER){
            printSolution(VARIATIONS);
        } else {
            for (int i = 0; i < ELEMENTS.length; i++) {
                VARIATIONS[index] = ELEMENTS[i];
                variationsWithRepetition(index + 1);
            }
        }
    }

    private static void variationsWithoutRepetion(int index) {
        if(index >= K_NUMBER) {
            printSolution(VARIATIONS);
        } else {
            for (int i = 0; i < ELEMENTS.length; i++) {
                if(!USED[i]) {
                    USED[i] = true;
                    VARIATIONS[index] = ELEMENTS[i];
                    variationsWithoutRepetion(index + 1);
                    USED[i] = false;
                }
            }
        }
    }

    private static void printSolution(char[] variations) {
        StringBuilder sb = new StringBuilder();
        for (char ch : VARIATIONS) {
            sb.append(ch).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}