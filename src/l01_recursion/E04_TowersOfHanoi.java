package l01_recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class E04_TowersOfHanoi {
    private static Deque<Integer> SOURCE = new ArrayDeque<>();
    private static Deque<Integer> SPARE = new ArrayDeque<>();
    private static Deque<Integer> DESTINATION= new ArrayDeque<>();
    private static int STEPS = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        for (int i = n; i > 0; i--) {
            SOURCE.push(i);
        }

        printRods();
        moveDisks(n, SOURCE, DESTINATION, SPARE);
    }

    private static void moveDisks(int disk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if(disk == 1) {
            STEPS++;
            destination.push(source.pop());
            System.out.println("Step #" + STEPS + ": Moved disk");
            printRods();
        } else {
            moveDisks(disk - 1, source, spare, destination);
            destination.push(source.pop());
            STEPS++;
            System.out.println("Step #" + STEPS + ": Moved disk");
            printRods();
            moveDisks(disk - 1, spare, destination, source);
        }
    }

    private static void printRods() {
        System.out.print("Source: ");
        printDeque(SOURCE);
        System.out.println();
        System.out.print("Destination: ");
        printDeque(DESTINATION);
        System.out.println();
        System.out.print("Spare: ");
        printDeque(SPARE);
        System.out.println("\n");
    }

    private static void printDeque(Deque<Integer> deque) {
        for (Iterator iterator = deque.descendingIterator(); iterator.hasNext();) {
            System.out.print(iterator.next());
            if(iterator.hasNext()) {
                System.out.print(", ");
            }
        }
    }
}