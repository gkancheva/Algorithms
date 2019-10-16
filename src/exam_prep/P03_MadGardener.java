package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P03_MadGardener {
    static int[] RESULT;
    static int BEST_LENGTH = 0;
    static int PEEK = 0;
    static int BEST_SUM = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] plants = new int[input.length + 1];
        RESULT = new int[plants.length];
        Sequence[] firstMaxSequence = new Sequence[plants.length];
        Sequence[] secondMaxSequence = new Sequence[plants.length];

        for (int i = 0; i < plants.length; i++) {
            firstMaxSequence[i] = new Sequence();
            secondMaxSequence[i] = new Sequence();
        }
        int[] reversedPlants = new int[plants.length];

        for (int i = 0; i < input.length; i++) {
            plants[i + 1] = input[i];
        }

        solution(firstMaxSequence, secondMaxSequence, reversedPlants, plants);
        buildSequence(firstMaxSequence, secondMaxSequence, reversedPlants, plants);
        printSolution();
    }

    private static void printSolution() {
        for (int i = 1; i < BEST_LENGTH; i++) {
            System.out.print(RESULT[i] + " ");
        }
        System.out.println();

        System.out.println(String.format("%.2f", BEST_SUM / ((BEST_LENGTH - 1) * 1.00)));
        System.out.println(BEST_LENGTH - 1);
    }

    private static void buildSequence(Sequence[] firstMaxSequence, Sequence[] secondMaxSequence, int[] reversedPlants, int[] plants) {
        int length = 0;
        int index = PEEK;
        int element = firstMaxSequence[index].length;
        while (index != 0) {
            RESULT[element - length++] = plants[index];
            index = firstMaxSequence[index].prevElement;
        }

        index = secondMaxSequence[plants.length - 1 - PEEK + 1].prevElement;
        while (index != 0) {
            RESULT[++length] = reversedPlants[index];
            index = secondMaxSequence[index].prevElement;
        }

    }

    private static void solution(Sequence[] firstSequence, Sequence[] secondSequence, int[] reversedPlants, int[] plants) {
        findLis(firstSequence, plants);
        reverse(reversedPlants, plants);
        findLis(secondSequence, reversedPlants);
        int n = plants.length - 1;

        for (int i = 1; i <= n; i++) {
            if(((firstSequence[i].length + secondSequence[n-i+1].length> BEST_LENGTH)) || ((firstSequence[i].length + secondSequence[n-i+1].length == BEST_LENGTH) && (firstSequence[i].sum + secondSequence[n-i+1].sum > BEST_SUM))){
                BEST_LENGTH = firstSequence[i].length + secondSequence[n - i + 1].length;
                BEST_SUM = firstSequence[i].sum + secondSequence[n - i + 1].sum - plants[i];
                PEEK = i;
            }
        }
    }

    private static void reverse(int[] reversedPlants, int[] plants) {
        for (int i = 1; i <= plants.length - 1; i++) {
            reversedPlants[i] = plants[(plants.length - 1) - i + 1];
        }
    }


    private static void findLis(Sequence[] maxSequence, int[] plants) {
        for (int i = 1; i <= plants.length - 1; i++) {
            maxSequence[i].length  = maxSequence[i].sum = 0;
            for (int j = 0; j < i; j++) {
                if(plants[j] <= plants[i]) {
                    if (maxSequence[j].length + 1 > maxSequence[i].length || ((maxSequence[j].length + 1 == maxSequence[i].length) && (maxSequence[j].sum + plants[i] > maxSequence[i].sum))){
                        maxSequence[i].prevElement = j;
                        maxSequence[i].length = maxSequence[j].length + 1;
                        maxSequence[i].sum = maxSequence[j].sum + plants[i];
                    }
                }
            }
        }
    }

    private static class Sequence{
        int length;
        int prevElement;
        int sum;
    }
}
