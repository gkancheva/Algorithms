package l07_graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P04_Salaries {
    private static List<List<Integer>> EMPLOYEES_GRAPH = new ArrayList<>();
    private static long[] SALARIES;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        SALARIES = new long[n];
        int[] managersCount = new int[n];

        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            EMPLOYEES_GRAPH.add(new ArrayList<>());
            for (int j = 0; j < line.length(); j++) {
                if(line.charAt(j) == 'Y') {
                    EMPLOYEES_GRAPH.get(i).add(j);
                    managersCount[j]++;
                }
            }
        }

        for (int i = 0; i < managersCount.length; i++) {
            if(managersCount[i] == 0) {
                calculateSalaries(i);
            }
        }

        System.out.println(Arrays.stream(SALARIES).sum());

    }

    private static long calculateSalaries(int employee) {
        long currentSum = 0;
        if(SALARIES[employee] != 0) {
                return SALARIES[employee];
        }
        if(EMPLOYEES_GRAPH.get(employee).isEmpty()) {
            SALARIES[employee] = 1;
            return 1;
        }
        for (int e : EMPLOYEES_GRAPH.get(employee)) {
            currentSum += calculateSalaries(e);
        }
        SALARIES[employee] = currentSum;
        return currentSum;
    }


}