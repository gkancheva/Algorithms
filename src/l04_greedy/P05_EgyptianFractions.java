package l04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P05_EgyptianFractions {
    private static List<Integer> RESULT = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split("/")).mapToInt(Integer::parseInt).toArray();

        getEgyptianFractions(numbers[0], numbers[1]);

        if(RESULT.isEmpty()) {
            return;
        }

        StringBuilder sb = new StringBuilder(String.format("%d/%d = ", numbers[0], numbers[1]));
        for (int i = 0; i < RESULT.size(); i++) {
            sb.append("1/").append(RESULT.get(i));
            if(i != RESULT.size() - 1) {
                sb.append(" + ");
            }
        }
        System.out.println(sb.toString());
    }

    private static void getEgyptianFractions(int numerator, int denominator) {
        if(denominator < numerator) {
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }
        if(numerator == 1) {
            RESULT.add(denominator);
        } else {
            int n = (int) Math.ceil(denominator / (numerator * 1.0));
            RESULT.add(n);
            int gcd = getGreatestCommonDivider((numerator * n) - denominator, (denominator * n));
            if(gcd == 0) {
                return;
            }
            getEgyptianFractions(((numerator * n) - denominator) / gcd, (denominator * n) / gcd);
        }
    }

    private static int getGreatestCommonDivider(int num1, int num2) {
        if(num1 == 0 || num2 == 0){
            return 0;
        }
        while(num1 != num2) {
            if(num1 > num2) {
                num1 = num1 - num2;
            } else {
                num2 = num2 - num1;
            }
        }
        return num2;
    }
}