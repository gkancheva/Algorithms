package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// brut force method
public class BL01_StarsInCube {
    private static char[][][] CUBE;
    private static Map<Character, Integer> STARS_COUNT = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());

        CUBE = new char[size][size][size];

        readAndFillTheCube(size, reader);
        int starCount = brutForce();
        System.out.println(starCount);
        for (char star : STARS_COUNT.keySet()) {
            System.out.println(star + " -> " + STARS_COUNT.get(star));
        }
    }

    private static int brutForce() {
        int starsCount = 0;
        for (int x = 1; x < CUBE.length - 1; x++) {
            for (int y = 1; y < CUBE[x].length - 1; y++) {
                for (int z = 1; z < CUBE[x][y].length - 1; z++) {
                    char currentLetter = CUBE[x][y][z];
                    if(isAStar(x, y, z)) {
                        starsCount++;
                        STARS_COUNT.putIfAbsent(currentLetter, 0);
                        int count = STARS_COUNT.get(currentLetter) + 1;
                        STARS_COUNT.put(currentLetter, count);
                    }
                }
            }
        }
        return starsCount;
    }

    private static boolean isAStar(int x, int y, int z) {
        char letter = CUBE[x][y][z];
        return letter == CUBE[x + 1][y][z] && letter == CUBE[x - 1][y][z] &&
                letter == CUBE[x][y + 1][z] && letter == CUBE[x][y - 1][z] &&
                letter == CUBE[x][y][z + 1] && letter == CUBE[x][y][z - 1];
    }

    private static void readAndFillTheCube(int size, BufferedReader reader) throws IOException {
        for (int x = 0; x < size; x++) {
            String[] tokens = reader.readLine().split("\\|");
            for (int y = 0; y < tokens.length; y++) {
                String layer = tokens[y].replace(" ", "");
                for (int z = 0; z < layer.length(); z++) {
                    CUBE[x][y][z] = layer.charAt(z);
                }
            }
        }
    }
}