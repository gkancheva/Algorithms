package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L02_RecursiveFactoriel {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        System.out.println(findFactoriel(num));
    }

    private static long findFactoriel(int num) {
        if(num == 1) {
            return 1;
        }
        return num * findFactoriel(num - 1);
    }
}