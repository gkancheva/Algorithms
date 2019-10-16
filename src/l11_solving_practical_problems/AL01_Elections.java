package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

//dynamic programming -> subset sums
public class AL01_Elections {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }

        BigInteger[] sums = new BigInteger[Arrays.stream(numbers).sum() + 1];

        sums[0] = new BigInteger("1");

        for (int number : numbers) {
            for (int i = sums.length - 1; i >= 0; i--) {
                if(sums[i] != null) {
                    if(sums[i + number] == null) {
                        sums[i + number] = new BigInteger("0");
                    }
                    sums[i + number] = sums[i + number].add(sums[i]);
                }
            }
        }

        BigInteger count = new BigInteger("0");
        for (int i = k; i < sums.length; i++) {
            if(sums[i] == null) {
                count = count.add(new BigInteger("0"));
            } else {
                count = count.add(sums[i]);
            }
        }
        System.out.println(count);
    }
}