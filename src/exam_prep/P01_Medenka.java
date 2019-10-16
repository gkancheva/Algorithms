package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P01_Medenka {
    private static StringBuilder OUTPUT = new StringBuilder();
    private static Deque<Integer> INDICES = new ArrayDeque<>();
    private static String MEDENKA;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MEDENKA = reader.readLine().replaceAll(" ", "");
        int startIndex = MEDENKA.indexOf("1");
        generateCuts(startIndex);
        System.out.print(OUTPUT);
    }

    private static void generateCuts(int startIndex) {
        int nextIndex = MEDENKA.indexOf("1", startIndex + 1);
        if (nextIndex == -1){
            addMedenka();
            return;
        }
        for (int i = startIndex + 1; i <= nextIndex; i++) {
            INDICES.push(i);
            generateCuts(nextIndex);
            INDICES.pop();
        }
    }

    private static void addMedenka() {
        StringBuilder sb = new StringBuilder(MEDENKA);
        for (Integer index : INDICES) {
            sb.insert(index, "|");
        }
        OUTPUT.append(sb).append(System.lineSeparator());
    }

}