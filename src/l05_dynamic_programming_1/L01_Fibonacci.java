package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L01_Fibonacci {
    private static long[] ARRAY;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        ARRAY = new long[n + 1];

        long result = findFibonacci(n);

        System.out.println(result);
    }

    private static long findFibonacci(int number) {
        if(number == 0) {
            return 0;
        }
        if(number == 1) {
            return 1;
        }
        if(ARRAY[number] != 0) {
            return ARRAY[number];
        }
        long result = findFibonacci(number - 1) + findFibonacci(number - 2);
        ARRAY[number] = result;
        return result;
    }
}