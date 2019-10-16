package l02_sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P05_Words {
    private static char[] LETTERS;
    private static int COUNT;
    private static boolean HAS_REPEATING_LETTERS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        LETTERS = reader.readLine().toCharArray();

        String test = Arrays.toString(LETTERS).replaceAll("[\\[\\], ]", "");
        long distinctCount = test.chars().distinct().count();
        HAS_REPEATING_LETTERS = distinctCount != LETTERS.length;

        if(!HAS_REPEATING_LETTERS) {
            System.out.println(factorial(LETTERS.length));
        } else {
            permutations(0);
            System.out.println(COUNT);
        }
    }

    private static int factorial(int n) {
        int factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    private static void permutations(int index) {
        if(index >= LETTERS.length && !hasNoRepeatingLetters()) {
            COUNT++;
        } else {
            Set<Character> swappedElements = new HashSet<>();
            for (int current = index; current < LETTERS.length; current++) {
                char currentElement = LETTERS[current];
                if(!swappedElements.contains(currentElement)) {
                    swappedElements.add(currentElement);
                    swap(index, current);
                    permutations(index + 1);
                    swap(index, current);
                }
            }
        }
    }

    private static boolean hasNoRepeatingLetters() {
        for (int i = 1; i < LETTERS.length; i++) {
            if(LETTERS[i] == LETTERS[i - 1]) {
                return true;
            }
        }
        return false;
    }

    private static void swap(int first, int second) {
        char temp = LETTERS[first];
        LETTERS[first] = LETTERS[second];
        LETTERS[second] = temp;
    }
}