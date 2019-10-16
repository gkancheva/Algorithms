package l04_greedy.p02_set_cover;

import java.util.*;

public class p02 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(in.nextLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = in.nextLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[i] = Integer.parseInt(setElements[i]);
            }
        }

        List<int[]> choosenSets = chooseSets(sets, universe);
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        List<int[]> result = new ArrayList<>();
        Set<Integer> elements = new HashSet<>();
        for (int element : universe) {
            elements.add(element);
        }

        while (elements.size() > 0) {
            int numberOfUnchoosenElements = 0;
            int[] choosenSet = sets.get(0);

            for (int[] set : sets) {
                int count = 0;
                for (int element : set) {
                    if (elements.contains(element)) {
                        count++;
                    }

                    if (numberOfUnchoosenElements < count) {
                        numberOfUnchoosenElements = count;
                        choosenSet = set;
                    }
                }
            }
            result.add(choosenSet);
            for (int e : choosenSet) {
                elements.remove(e);
            }
        }
        return result;
    }
}
