package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L05_GeneratingCombinations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int num = Integer.parseInt(reader.readLine());

        genCombinations(arr, new int[num], 0, 0);

    }

    private static void genCombinations(int[] set, int[] vector, int index, int border) {
        if(index >= vector.length) {
            System.out.println(Arrays.toString(vector).replaceAll("[\\[\\],]", ""));
        } else {
            for (int i = border; i < set.length; i++) {
                vector[index] = set[i];
                genCombinations(set, vector, index + 1, i + 1);
            }
        }
    }


}