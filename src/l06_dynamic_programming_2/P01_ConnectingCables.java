package l06_dynamic_programming_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class P01_ConnectingCables {
    private static int[] COUNT;
    private static int MAX_COUNT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> cables = new TreeMap<>();

        COUNT = new int[numbers.length + 1];

        int index = 1;
        for (int number : numbers) {
            cables.put(number, index++);
        }

        findLIS(cables, cables.size());

        System.out.println("Maximum pairs connected: " + MAX_COUNT);
    }

    private static void findLIS(Map<Integer, Integer> cables, int index) {
        if(index <= 0) {
            return;
        }
        if(cables.size() - 1 == index) {
            COUNT[index] = 1;
            findLIS(cables, index - 1);
        }

        int max = 1;
        for (int i = index; i < cables.size(); i++) {
            if(cables.get(i) < cables.get(i + 1) && max < COUNT[i + 1] + 1) {
                max = COUNT[i + 1] + 1;
            }
        }

        COUNT[index] = max;

        if(COUNT[index] >= MAX_COUNT) {
            MAX_COUNT = COUNT[index];
        }
        findLIS(cables, index - 1);
    }
}