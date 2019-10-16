package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//combinatorics -> permutations
public class BL03_ShortestPath {
    private static char[] DIRECTIONS = new char[]{'L', 'R', 'S'};
    private static List<Integer> INDEXES = new ArrayList<>();
    private static List<String> RESULT = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] input = reader.readLine().toCharArray();
        for (int i = 0; i < input.length; i++) {
            if(input[i] == '*') {
                INDEXES.add(i);
            }
        }

        findSolutions(0, input);

        System.out.println(RESULT.size());
        for (String s : RESULT) {
            System.out.println(s);
        }
    }

    private static void findSolutions(int currentIndex, char[] arr) {
        if(currentIndex == INDEXES.size()) {
            RESULT.add(Arrays.toString(arr).replaceAll("[\\[\\], ]", ""));
            return;
        }
        for (char direction : DIRECTIONS) {
            arr[INDEXES.get(currentIndex)] = direction;
            findSolutions(currentIndex + 1, arr);
        }
    }
}