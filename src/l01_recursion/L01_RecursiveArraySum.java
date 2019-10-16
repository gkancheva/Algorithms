package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L01_RecursiveArraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(sum(arr, 0, 0));
    }

    private static int sum(int[] array, int index, int sum) {
        if(index >= array.length) {
            return sum;
        }
        sum += array[index];
        return sum(array, index + 1, sum);
    }
}