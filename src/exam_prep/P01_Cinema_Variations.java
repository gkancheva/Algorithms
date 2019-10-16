package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P01_Cinema_Variations {
    private static String[] COMBINATIONS;
    private static List<String> RESULT = new ArrayList<>();
    private static List<String> FREE_NAMES = new ArrayList<>();
    private static List<Integer> FREE_PLACES = new ArrayList<>();
    private static Set<String> USED = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FREE_NAMES.addAll(Arrays.asList(reader.readLine().split(", ")));

        COMBINATIONS = new String[FREE_NAMES.size()];

        String input = reader.readLine();
        while (!input.equals("generate")) {
            String[] tokens = input.split(" - ");
            int place = Integer.parseInt(tokens[1]);
            COMBINATIONS[place - 1] = tokens[0];
            input = reader.readLine();
            FREE_NAMES.remove(tokens[0]);
        }

        for (int i = 0; i < COMBINATIONS.length; i++) {
            if(COMBINATIONS[i] == null) {
                FREE_PLACES.add(i);
            }
        }

        generateCombinations(0);

        for (String s : RESULT) {
            System.out.println(s);
        }

    }

    private static void generateCombinations(int indexFreePlace) {
        if(indexFreePlace >= FREE_PLACES.size()) {
            StringBuilder result = new StringBuilder();
            for (String name : COMBINATIONS) {
                result.append(name).append(" ");
            }
            RESULT.add(result.toString().trim());
        } else {
            for (int i = 0; i < FREE_NAMES.size(); i++) {
                if(!USED.contains(FREE_NAMES.get(i))) {
                    USED.add(FREE_NAMES.get(i));
                    COMBINATIONS[FREE_PLACES.get(indexFreePlace)] = FREE_NAMES.get(i);
                    generateCombinations(indexFreePlace + 1);
                    USED.remove(FREE_NAMES.get(i));
                }
            }
        }
    }
}