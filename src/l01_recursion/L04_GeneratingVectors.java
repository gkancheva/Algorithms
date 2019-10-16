package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L04_GeneratingVectors {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        generate01(new int[num], 0);

    }

    private static void generate01(int[] arr, int index) {
        if(index > arr.length - 1) {
            System.out.println(Arrays.toString(arr)
                    .replaceAll("[\\[\\], ]", ""));
            return;
        }
        for (int i = 0; i <= 1; i++) {
            arr[index] = i;
            generate01(arr, index + 1);
        }
    }
}