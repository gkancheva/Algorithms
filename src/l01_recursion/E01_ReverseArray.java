package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class E01_ReverseArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        reverseArr(arr, 0);
        for (int num : arr) {
            System.out.print(num + " ");
        }

    }

    private static void reverseArr(int[] arr, int index) {
        if(index > arr.length / 2 - 1) {
            return;
        }
        int current = arr[index];
        arr[index] = arr[arr.length - index - 1];
        arr[arr.length - index - 1] = current;
        reverseArr(arr, index + 1);
    }
}