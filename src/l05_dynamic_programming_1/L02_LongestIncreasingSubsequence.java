package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L02_LongestIncreasingSubsequence {
    private static int[] COUNT;
    private static int[] INDEXES;
    private static int MAX_COUNT = 0;
    private static int START_MAX_INDEX = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        COUNT = new int[array.length];
        INDEXES = new int[array.length];


        findLIS(array, array.length - 1);

        StringBuilder sb = new StringBuilder();

        while(START_MAX_INDEX != -1) {
            sb.append(array[START_MAX_INDEX]).append(" ");
            START_MAX_INDEX = INDEXES[START_MAX_INDEX];
        }

        System.out.println(sb.toString().trim());

    }

    private static void findLIS(int[] array, int index) {
        if(index < 0) {
            return;
        }
        if(array.length - 1 == index) {
            COUNT[index] = 1;
            findLIS(array, index - 1);
        }

        int max = 1;
        int maxIndex = -1;
        for (int i = index; i < array.length - 1; i++) {
            if(array[index] < array[i + 1] && max < COUNT[i + 1] + 1) {
                max = COUNT[i + 1] + 1;
                maxIndex = i + 1;
            }
        }

        COUNT[index] = max;
        INDEXES[index] = maxIndex;

        if(COUNT[index] >= MAX_COUNT) {
             MAX_COUNT = COUNT[index];
             START_MAX_INDEX = index;
        }
        findLIS(array, index - 1);
    }
}