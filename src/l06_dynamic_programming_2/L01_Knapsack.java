package l06_dynamic_programming_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class L01_Knapsack {
    private static int MAX_CAPACITY;
    private static int[][] VALUES;
    private static List<Item> ITEMS = new ArrayList<>();
    private static boolean[][] INCLUDED_ITEMS;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        MAX_CAPACITY = Integer.parseInt(reader.readLine());

        String input = reader.readLine();
        while(!input.equals("end")) {
            String[] tokens = input.split(" ");
            Item item = new Item(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
            ITEMS.add(item);
            input = reader.readLine();
        }

        VALUES = new int[ITEMS.size() + 1][MAX_CAPACITY + 1];
        INCLUDED_ITEMS = new boolean[ITEMS.size() + 1][MAX_CAPACITY + 1];
        fillTheMatrix();

        int capacity = MAX_CAPACITY;

        List<Item> result = new ArrayList<>();
        int weight = 0;

        for (int i = ITEMS.size() - 1; i >= 0; i--) {
            if(capacity <= 0) {
                 break;
            }
            Item currentItem = ITEMS.get(i);
            if(INCLUDED_ITEMS[i + 1][capacity]) {
                result.add(currentItem);
                weight += currentItem.weight;
                capacity -= currentItem.weight;
            }

        }

        result.sort(Item::compareTo);

        System.out.println("Total Weight: " + weight);
        System.out.println("Total Value: " + VALUES[ITEMS.size()][MAX_CAPACITY]);

        for (Item item : result) {
            System.out.println(item.name);
        }

    }

    private static void fillTheMatrix() {
        for (int itemInd = 0; itemInd < ITEMS.size(); itemInd++) {
            Item item = ITEMS.get(itemInd);
            int rowIndex = itemInd + 1;
            for (int currentCapacity = 0; currentCapacity <= MAX_CAPACITY; currentCapacity++) {
                if(item.weight > currentCapacity) {
                    continue;
                }
                int excludingValue = VALUES[rowIndex - 1][currentCapacity];
                int includingValue = item.value + VALUES[rowIndex - 1][currentCapacity - item.weight];
                if(includingValue > excludingValue) {
                    VALUES[rowIndex][currentCapacity] = includingValue;
                    INCLUDED_ITEMS[rowIndex][currentCapacity] = true;
                } else {
                    VALUES[rowIndex][currentCapacity] = excludingValue;
                }
            }
        }

    }

    private static class Item implements Comparable<Item> {
        private String name;
        private int weight;
        private int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Item o) {
            return this.name.compareTo(o.name);
        }
    }
}