package exam_26_09_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P01_SchoolTeams {
    private static String[] RESULT = new String[5];
    private static List<String> NAMES = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] girls = reader.readLine().split(", ");
        String[] boys = reader.readLine().split(", ");

        NAMES.addAll(Arrays.asList(girls));
        NAMES.addAll(Arrays.asList(boys));

        generate(0, 0);

    }


    private static void generate(int index, int start) {
        if(index >= RESULT.length) {
            StringBuilder result = new StringBuilder();
            for (String name : RESULT) {
                result.append(name).append(", ");
            }
            System.out.println(result.delete(result.length() - 2, result.length()).toString().trim());
        } else {
            for (int i = start; i < NAMES.size(); i++) {
                RESULT[index] = NAMES.get(i);
                generate(index + 1, i + 1);
            }
        }
    }
}