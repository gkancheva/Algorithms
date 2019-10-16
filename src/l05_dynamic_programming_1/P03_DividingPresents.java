package l05_dynamic_programming_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P03_DividingPresents {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] presents = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int totalSum = Arrays.stream(presents).sum();

        int[] sums = new int[totalSum + 1];

        for (int i = 1; i < sums.length; i++) {
            sums[i] = -1;
        }

        for (int currentPres = 0; currentPres < presents.length; currentPres++) {
            int presentValue = presents[currentPres];
            for (int prevSumIndex = totalSum - presentValue; prevSumIndex >= 0; prevSumIndex--) {
                if(sums[prevSumIndex] != -1 &&
                        sums[prevSumIndex + presentValue] == -1) {
                    sums[prevSumIndex + presentValue] = currentPres;
                }
            }
        }

        int halfSum = totalSum / 2;

        StringBuilder output = new StringBuilder();

        for (int i = halfSum; i >= 0; i--) {
            if(sums[i] == -1) {
                continue;
            }
            output.append("Difference: ").append(totalSum - i - i).append(System.lineSeparator());
            output.append(String.format("Alan:%d Bob:%d", i, totalSum - i)).append(System.lineSeparator());
            output.append("Alan takes: ");
            while(i != 0) {
                output.append(presents[sums[i]]).append(" ");
                i -= presents[sums[i]];
            }
            output.append(System.lineSeparator()).append("Bob takes the rest.");
        }

        System.out.println(output.toString());

    }
}