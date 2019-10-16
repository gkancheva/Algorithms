package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// greedy algorithm
// 60/100
public class AP04_ShopKeeper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inStock = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] orders = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        HashSet<Integer> stock = new HashSet<>();
        Map<Integer, Integer> typesCount = new HashMap<>();
        for (int item : inStock) {
            stock.add(item);
        }

        for (int order : orders) {
            typesCount.putIfAbsent(order, 0);
            typesCount.put(order, typesCount.get(order) + 1);
        }
        boolean solution = true;
        int changes = 0;

        for (int i = 0; i < orders.length - 1; i++) {
            if (!stock.contains(orders[i])) {
                solution = false;
                break;
            }

            if (!stock.contains(orders[i + 1])) {
                int item = findNext(orders, stock, i);
                stock.remove(item);
                stock.add(orders[i + 1]);
                changes++;
            }
        }

        if (!stock.contains(orders[orders.length - 1])) {
            solution = false;
        }

        System.out.println(solution ? changes : "impossible");
    }

    private static int findNext(int[] orders, Set<Integer> stock, int position) {
        Set<Integer> usedTypes = new HashSet<>();
        List<Integer> typesByPosition = new ArrayList<>();
        for (int i = position + 1; i < orders.length; i++) {
            int currentOrder = orders[i];
            if(stock.contains(currentOrder) && !usedTypes.contains(currentOrder)) {
                typesByPosition.add(currentOrder);
                usedTypes.add(currentOrder);
            }
        }

        for (int item : stock) {
            if(!usedTypes.contains(item)) {
                typesByPosition.add(item);
            }
        }
        return typesByPosition.get(typesByPosition.size() - 1);
    }
}