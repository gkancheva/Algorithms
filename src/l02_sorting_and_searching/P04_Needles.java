package l02_sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P04_Needles {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] needles = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        for (int needle : needles) {
            sb.append(binaryS(arr, needle, 0, arr.length - 1)).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static int binaryS(int[] arr, int needle, int startIndex, int endIndex) {
        if(startIndex < endIndex) {
            int midIndex = startIndex + (endIndex - startIndex) / 2;
            while(midIndex > 0 && arr[midIndex] == 0) {
                midIndex--;
            }

            if(midIndex == 0 && (arr[midIndex] == 0 || arr[midIndex] >= needle)) {
                return midIndex;
            }

            if(midIndex == arr.length - 1 && arr[midIndex] < needle) {
                return midIndex + 1;
            }

            if(needle < arr[midIndex]) {
                if(midIndex >= 1 && arr[midIndex - 1] != 0 && arr[midIndex - 1] < needle) {
                    return midIndex;
                }
                return binaryS(arr, needle, startIndex, midIndex);
            } else if(needle > arr[midIndex]) {
                int temp = midIndex;
                while(midIndex + 2 < arr.length && arr[midIndex + 1] == 0) {
                    midIndex++;
                }
                if(arr[midIndex + 1] >= needle) {
                    return temp + 1;
                }
                return binaryS(arr, needle, midIndex + 1, endIndex);
            } else {
                while(midIndex >= 0 && (arr[midIndex] == needle || arr[midIndex] == 0)) {
                    midIndex--;
                }
                return midIndex + 1;
            }
        }

        if(startIndex == endIndex && arr[startIndex] == needle) {
            while(startIndex >= 0 && (arr[startIndex] == needle || arr[startIndex] == 0)) {
                startIndex--;
            }
            return startIndex + 1;
        }

        if(startIndex == endIndex && arr[startIndex] > needle) {
            return startIndex;
        }
        return startIndex + 1;
    }

}