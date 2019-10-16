package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E02_NestedLoops {
    private static int[] ARRAY;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ARRAY = new int[n];
        recursiveLoops(0);
    }

    private static void recursiveLoops(int start) {
        if(start == ARRAY.length) {
            for (int i1 : ARRAY) {
                System.out.print(i1 + " ");
            }
            System.out.println();
        } else {
            for (int i = 0; i < ARRAY.length; i++) {
                ARRAY[start] = i + 1;
                recursiveLoops(start + 1);
            }
        }
    }
}