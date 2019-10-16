package l04_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.TreeSet;

public class P01_FractionalKnapsack {

    private static SortedSet<Item> ITEMS = new TreeSet<>();
    private static int CAPACITY;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CAPACITY = Integer.parseInt(reader.readLine().substring(10));
        int itemCount = Integer.parseInt(reader.readLine().substring(7));

        for (int i = 0; i < itemCount; i++) {
            String[] tokens = reader.readLine().split(" -> ");
            Item item = new Item(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
            ITEMS.add(item);
        }

        //ITEMS.sort(Item::compareTo);

        double totalPrice = 0;

        while(CAPACITY != 0 && ITEMS.size() > 0) {
            Item currentItem = ITEMS.first();
            double toTake = CAPACITY / currentItem.weight;
            if(toTake > 1) {
                toTake = 1;
            }
            CAPACITY -= (toTake * currentItem.weight);
            String percentage = "100%";
            if(toTake < 1) {
                percentage = String.format("%.2f%%", toTake * 100);
            }
            String output = String.format(
                    "Take %s of item with price %.2f and weight %.2f",
                    percentage, currentItem.price, currentItem.weight);
            System.out.println(output);
            totalPrice += (toTake * currentItem.price);
            ITEMS.remove(currentItem);
        }

        System.out.println(String.format("Total price: %.2f", totalPrice));
    }

    private static class Item implements Comparable<Item> {
        private double price;
        private double weight;

        public Item(double price, double weight) {
            this.price = price;
            this.weight = weight;
        }

        @Override
        public int compareTo(Item o) {
            int compare = Double.compare((o.price/o.weight), (this.price/this.weight));
            return compare;
        }
    }
}