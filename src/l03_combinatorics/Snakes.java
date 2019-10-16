package l03_combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Snakes {
    private static char[] CURRENT_SNAKE;
    private static Set<String> VISITED = new HashSet<>();
    private static List<String> RESULT_SNAKES = new ArrayList<>();
    private static Set<String> ALL_POSSIBLE_SOLUTIONS = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        CURRENT_SNAKE = new char[n];

        generateSnake(0, 0, 0, 'S');

        for (String snake : RESULT_SNAKES) {
            System.out.println(snake);
        }
        System.out.println("Snakes count = " + RESULT_SNAKES.size());

    }

    private static void generateSnake(int index, int row, int col, char direction) {
        if(index >= CURRENT_SNAKE.length) {
            addSnake();
        } else {
            String cell = row + " " + col;
            if (!VISITED.contains(cell)) {
                CURRENT_SNAKE[index] = direction;
                VISITED.add(cell);
                generateSnake(index + 1, row, col + 1, 'R');
                generateSnake(index + 1, row + 1, col, 'D');
                generateSnake(index + 1, row, col - 1, 'L');
                generateSnake(index + 1, row - 1, col, 'U');
                VISITED.remove(cell);
            }
        }
    }

    private static void addSnake() {
        StringBuilder sb = new StringBuilder();
        for (char c : CURRENT_SNAKE) {
            sb.append(c);
        }
        String currentSnake = sb.toString();
        if(ALL_POSSIBLE_SOLUTIONS.contains(currentSnake)) {
            return;
        }

        RESULT_SNAKES.add(currentSnake);

        String flippedSnake = flip(currentSnake);
        String reversedSnake = reverse(currentSnake);
        String reversedFlippedSnake = flip(reversedSnake);

        for (int i = 0; i < 4; i++) {
            ALL_POSSIBLE_SOLUTIONS.add(currentSnake);
            currentSnake = rotate(currentSnake);

            ALL_POSSIBLE_SOLUTIONS.add(flippedSnake);
            flippedSnake = rotate(flippedSnake);

            ALL_POSSIBLE_SOLUTIONS.add(reversedSnake);
            reversedSnake = rotate(reversedSnake);

            ALL_POSSIBLE_SOLUTIONS.add(reversedFlippedSnake);
            reversedFlippedSnake = rotate(reversedFlippedSnake);
        }
    }

    private static String rotate(String snake) {
        char[] newSnake = new char[snake.length()];
        for (int i = 0; i < snake.length(); i++) {
            switch (snake.charAt(i)) {
                case 'R': newSnake[i] = 'D'; break;
                case 'D': newSnake[i] = 'L'; break;
                case 'L': newSnake[i] = 'U'; break;
                case 'U': newSnake[i] = 'R'; break;
                default: newSnake[i] = snake.charAt(i); break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : newSnake) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static String reverse(String snake) {
        char[] newSnake = new char[snake.length()];
        newSnake[0] = 'S';
        for (int i = 1; i < snake.length(); i++) {
            newSnake[snake.length() - i] = snake.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : newSnake) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static String flip(String snake) {
        char[] newSnake = new char[snake.length()];
        for (int i = 0; i < newSnake.length; i++) {
            switch (snake.charAt(i)) {
                case 'D': newSnake[i] = 'U'; break;
                case 'U': newSnake[i] = 'D'; break;
                default: newSnake[i] = snake.charAt(i); break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : newSnake) {
            sb.append(c);
        }
        return sb.toString();
    }
}