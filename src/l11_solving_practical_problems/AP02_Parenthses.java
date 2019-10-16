package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//combinatorics with if for the parentheses
// 100/100
public class AP02_Parenthses {
    private static StringBuilder RESULT = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        char[] variations = new char[n * 2];

        getVariations(0, variations, 0, 0);

        System.out.println(RESULT.toString().trim());
    }

    private static void getVariations(int index, char[] variations, int left, int right) {
        if(index >= variations.length) {
            StringBuilder currentVariation = new StringBuilder();
            for (char ch : variations) {
                currentVariation.append(ch);
            }
            RESULT.append(currentVariation.toString())
                    .append(System.lineSeparator());
        }
        if(left < variations.length / 2) {
            variations[index] = '(';
            getVariations(index + 1, variations, left + 1, right);
        }
        if(left > right) {
            variations[index] = ')';
            getVariations(index + 1, variations, left, right + 1);
        }
    }
}