package exam_26_09_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P03_RoadTrip {
    private static BigInteger[][] VALUES_MATRIX;
    private static boolean[][] INCLUDED_ITEMS;
    private static int MAX_CAPACITY;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long[] values = Arrays.stream(reader.readLine().split(", ")).mapToLong(Long::parseLong).toArray();
        int[] spaces = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        MAX_CAPACITY = Integer.parseInt(reader.readLine());
        VALUES_MATRIX = new BigInteger[values.length + 1][MAX_CAPACITY + 1];
        INCLUDED_ITEMS = new boolean[values.length + 1][MAX_CAPACITY + 1];

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            BigInteger currentValue = new BigInteger(String.valueOf(values[i]));
            Item item = new Item(currentValue, spaces[i]);
            items.add(item);
        }

        //fillTheMatrix(items);
//        BigInteger value = new BigInteger("0");
//        int capacity = MAX_CAPACITY;
//
//        for (int i = items.size() - 1; i >= 0; i--) {
//            if(capacity <= 0) {
//                break;
//            }
//            Item currentItem = items.get(i);
//            if(INCLUDED_ITEMS[i + 1][capacity]) {
//                value = value.add(currentItem.value);
//                capacity -= currentItem.weight;
//            }
//        }

        BigInteger value = new BigInteger("0");

        items.sort(Item::compareTo);

        for (Item item : items) {
            if(MAX_CAPACITY - item.weight >= 0) {
                MAX_CAPACITY -= item.weight;
                value = value.add(item.value);
            }
        }

        System.out.println("Maximum value: " + value);

    }

    private static void fillTheMatrix(List<Item> items) {
        for (int itemInd = 0; itemInd < items.size(); itemInd++) {
            Item item = items.get(itemInd);
            int rowIndex = itemInd + 1;
            for (int currentCapacity = 0; currentCapacity <= MAX_CAPACITY; currentCapacity++) {
                if(item.weight > currentCapacity) {
                    continue;
                }
                BigInteger excludingValue = VALUES_MATRIX[rowIndex - 1][currentCapacity] == null ? new BigInteger("0") : VALUES_MATRIX[rowIndex - 1][currentCapacity];
                BigInteger includingValue = item.value.add(VALUES_MATRIX[rowIndex - 1][currentCapacity - item.weight] == null ?
                        new BigInteger("0") : VALUES_MATRIX[rowIndex - 1][currentCapacity - item.weight]);
                if(includingValue.compareTo(excludingValue) > 0) {
                    VALUES_MATRIX[rowIndex][currentCapacity] = includingValue;
                    INCLUDED_ITEMS[rowIndex][currentCapacity] = true;
                } else {
                    VALUES_MATRIX[rowIndex][currentCapacity] = excludingValue;
                }
            }
        }

    }

    private static class Item implements Comparable<Item> {
        private BigInteger value;
        private int weight;

        public Item(BigInteger value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        @Override
        public int compareTo(Item o) {
            if(o.value.compareTo(this.value) == 0) {
                return Integer.compare(this.weight, o.weight);
            }
            return o.value.compareTo(this.value);
        }
    }
}