package l06_dynamic_programming_2;

import java.util.*;

public class L03_SubsetSum {
    private static int[] NUMBERS = new int[]{3, 5, 1, 4, 2};
    private static int TARGET_SUM = 9;

    public static void main(String[] args) {

        boolean[] possibleSums = calcPossibleSumsWithRepetition();

        List<Integer> subSet = new ArrayList<>();
        int target = TARGET_SUM;
        while(target > 0) {
            for (int number : NUMBERS) {
                int newSum = target - number;
                if (newSum >= 0 && possibleSums[newSum]) {
                    target = newSum;
                    subSet.add(number);
                }
            }
        }

        StringBuilder sb = new StringBuilder(TARGET_SUM + "");
        sb.append(" = ");
        subSet.forEach(i -> sb.append(i).append(" + "));
        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb.toString());

        //Map<Integer, Integer> sums = calcPossibleSumsWithoutRepetition();

//        if(sums.containsKey(TARGET_SUM)) {
//            System.out.println("Yes");
//            while(TARGET_SUM != 0) {
//                int number = sums.get(TARGET_SUM);
//                System.out.print(number + " ");
//                TARGET_SUM -= number;
//            }
//        } else {
//            System.out.println("No");
//        }
    }

    private static boolean[] calcPossibleSumsWithRepetition() {
        boolean[] possible = new boolean[TARGET_SUM + 1];
        possible[0] = true;
        for (int sum = 0; sum < possible.length; sum++) {
            if(possible[sum]) {
                for (int i = 0; i < NUMBERS.length; i++) {
                    int newSum = sum + NUMBERS[i];
                    if(newSum <= TARGET_SUM) {
                        possible[newSum] = true;
                    }
                }
            }
        }
        return possible;
    }

    private static Map<Integer, Integer> calcPossibleSumsWithoutRepetition() {
        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 0);
        for (int i = 0; i < NUMBERS.length; i++) {
            int currentNum = NUMBERS[i];
            List<Integer> resultKeySet = new ArrayList<>(result.keySet());
            for (int num : resultKeySet) {
                int newSum = num + currentNum;
                result.putIfAbsent(newSum, currentNum);
            }
        }
        return result;
    }
}