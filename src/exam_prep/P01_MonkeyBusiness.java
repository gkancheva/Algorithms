package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P01_MonkeyBusiness {
    private static int[] NUMBERS;
    private static int TARGET_SUM = 0;
    private static int[] SUMS;
    private static StringBuilder SB = new StringBuilder();
    private static int COUNT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        NUMBERS = new int[n];
        SUMS = new int[n];

        for (int i = 0; i < n; i++) {
            NUMBERS[i] = i + 1;
        }

        findSolutions(0);
        SB.append("Total Solutions: ").append(COUNT);
        System.out.println(SB.toString());

    }

    private static void findSolutions(int index) {
        if(index >= NUMBERS.length) {
            if(SUMS[NUMBERS.length - 1] == TARGET_SUM) {
                COUNT++;
                printSolutions();
            }
        } else {
            int sum = NUMBERS[index];
            if(index > 0) {
                sum += SUMS[index - 1];
            }
            SUMS[index] = sum;
            findSolutions(index + 1);
            NUMBERS[index] *= -1;
            SUMS[index] += (NUMBERS[index] * 2);
            findSolutions(index + 1);
        }
    }

    private static void printSolutions() {
        for (int i = 0; i < NUMBERS.length; i++) {
            if(NUMBERS[i] > 0){
                SB.append(String.format("+%d ", NUMBERS[i]));
            }else{
                SB.append(String.format("%d ", NUMBERS[i]));
            }
        }
        SB.append(System.lineSeparator());
    }
}