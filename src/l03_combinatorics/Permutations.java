package l03_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** All possible ways to order X elements **/
public class Permutations {
    private static long[] FACTORIALS;
    private static char[] ARR;
    private static boolean[] USED;
    private static char[] RESULT;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ARR = reader.readLine().replace(" ", "").toCharArray();
        USED = new boolean[ARR.length];
        RESULT = new char[ARR.length];
        FACTORIALS = new long[ARR.length + 1];

        /** count of Permutations without repetitive elements = n! **/
        //long permutationCount = factorial(ARR.length);
        //System.out.println("Permutations count: " + permutationCount);

        /** count of Permutations with repetitve elements = n! / s1!s2!..sk! **/
        long permutationsCountWithRepetitiveElemnents = getCountOfElements(ARR);

        /** using extra memory for additional arrays **/
        //permuteWithoutRepetitions(0);

        /** altering the existing array without using extra memory **/
        //permuteWithoutRepetitionsSwapAlgo(0);

        /** Permutations with repetitive elements **/
        permuteWithRepetion(0);

        /** Permutations with repetitive elements, more effective than the previous **/
        Arrays.sort(ARR);
        permuteWithRepetions(0, ARR.length - 1);
    }

    private static long getCountOfElements(char[] arr) {
        long n = factorial(arr.length);
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < ARR.length; i++) {
            map.putIfAbsent(ARR[i], 0);
            map.put(ARR[i], map.get(ARR[i]) + 1);
        }

        long result = n / map.size();
        return result;
    }

    private static void permuteWithRepetions(int startIndex, int endIndex) {
        printPermutations(ARR);
        for (int left = endIndex - 1; left >= startIndex; left--) {
            for (int right = left + 1; right <= endIndex; right++) {
                if(ARR[left] != ARR[right]) {
                    swap(left, right);
                    permuteWithRepetions(left + 1, endIndex);
                }
                char firstElement = ARR[left];
                for (int i = left; i <= endIndex - 1; i++) {
                    ARR[i] = ARR[i + 1];
                }
                ARR[endIndex] = firstElement;
            }
        }
    }

    private static void permuteWithRepetion(int index) {
        if(index >= ARR.length) {
            printPermutations(ARR);
        } else {
            Set<Character> swapped = new HashSet<>();
            for (int i = index; i < ARR.length; i++) {
                if(!swapped.contains(ARR[i])) {
                    swap(index, i);
                    permuteWithRepetion(index + 1);
                    swap(index, i);
                    swapped.add(ARR[i]);
                }
            }
        }
    }

    private static void permuteWithoutRepetitionsSwapAlgo(int index) {
        if(index >= ARR.length) {
            printPermutations(ARR);
        } else {
            permuteWithoutRepetitionsSwapAlgo(index + 1);
            for (int i = index + 1; i < ARR.length; i++) {
                swap(index, i );
                permuteWithoutRepetitionsSwapAlgo(index + 1);
                swap(index, i);
            }
        }
    }

    private static void swap(int index, int i) {
        char temp = ARR[index];
        ARR[index] = ARR[i];
        ARR[i] = temp;
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

    private static void permuteWithoutRepetitions(int index) {
        if(index >= ARR.length) {
            printPermutations(RESULT);
        } else {
            for (int i = 0; i < ARR.length; i++) {
                if (!USED[i]) {
                    USED[i] = true;
                    RESULT[index] = ARR[i];
                    permuteWithoutRepetitions(index + 1);
                    USED[i] = false;
                }
            }
        }
    }

    private static void printPermutations(char[] result) {
        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            sb.append(c).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}