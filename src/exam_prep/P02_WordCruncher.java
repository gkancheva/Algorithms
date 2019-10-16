package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P02_WordCruncher {
    private static List<String> WORDS;
    private static String TARGET;
    private static List<String> BUFFER = new ArrayList<>();
    private static Set<String> RESULT = new HashSet<>();
    private static StringBuilder OUTPUT = new StringBuilder();
    private static Map<Integer, ArrayList<String>> tree = new HashMap<>();
    private static Map<String, Integer> RESOURCES = new HashMap<>();
    private static Map<String, Integer> COUNTER = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        WORDS = Arrays.stream(reader.readLine().split(", ")).collect(Collectors.toList());
        TARGET = reader.readLine();

        ArrayList<String> toRemove = new ArrayList<>();
        for (String word : WORDS) {
            if (!TARGET.contains(word)) {
                toRemove.add(word);
            } else if(!RESOURCES.containsKey(word)){
                RESOURCES.put(word, 1);
                COUNTER.put(word, 0);
            } else {
                RESOURCES.put(word, RESOURCES.get(word) + 1);
            }
        }
        WORDS.removeAll(toRemove);

        for (int i = 0; i < TARGET.length(); i++) {
            tree.put(i, new ArrayList<>());
        }

        for (String word : WORDS) {
            int index = TARGET.indexOf(word);
            while (index != -1) {
                tree.get(index).add(word);
                index = TARGET.indexOf(word, index + 1);
            }
        }

        treeTraverse(0);

        System.out.print(OUTPUT.toString());


    }

    private static void treeTraverse(int index) {
        if (index == TARGET.length()) {
            printCombination();
        } else {
            for (int i = 0; i < tree.get(index).size(); i++) {
                String element = tree.get(index).get(i);
                if(RESOURCES.get(element) > COUNTER.get(element)) {
                    COUNTER.put(element, COUNTER.get(element) + 1);
                    BUFFER.add(element);
                    treeTraverse(index + element.length());
                    BUFFER.remove(BUFFER.lastIndexOf(element));
                    COUNTER.put(element, COUNTER.get(element) - 1);
                }
            }
        }
    }

    private static void printCombination() {
        String text = String.join("", BUFFER);
        if (text.equals(TARGET)) {
            String output = String.join(" ", BUFFER);
            if (RESULT.add(output)) {
                OUTPUT.append(output).append(System.lineSeparator());
            }
        }
   }
}