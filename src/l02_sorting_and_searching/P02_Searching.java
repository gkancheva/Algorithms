package l02_sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P02_Searching {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int num = Integer.parseInt(reader.readLine());

        //linearSearch(arr, num);
        System.out.println(binarySearch(arr, 0, arr.length - 1, num));
        //fibonacciSearch(arr, num);
    }

    private static void fibonacciSearch(int[] arr, int num) {

    }

    private static int binarySearch(int[] arr, int startIndex, int endIndex, int num) {
        if (endIndex >= startIndex) {
            int midIndex = startIndex + (endIndex - startIndex) / 2;

            if (arr[midIndex] == num) {
                return midIndex;
            }

            if (arr[midIndex] > num) {
                return binarySearch(arr, startIndex, midIndex - 1, num);
            }
            return binarySearch(arr, midIndex + 1, endIndex, num);
        }
        return -1;
    }

    private static void linearSearch(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == num) {
                System.out.println(i);
            }
        }
        System.out.println(-1);
    }
}