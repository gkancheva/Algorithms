package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// dynamic programming
// 100/100
public class BL02_Guitar {
    private static boolean[][] MATRIX;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] volumes = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int initialVolume = Integer.parseInt(reader.readLine());
        int maxVolume = Integer.parseInt(reader.readLine());

        MATRIX = new boolean[volumes.length + 1][maxVolume + 1];
        MATRIX[0][initialVolume] = true;
        int possibleMax = findMaxVolume(volumes, maxVolume);

        System.out.println(possibleMax);
    }

    private static int findMaxVolume(int[] volumes, int maxVolume) {
        for (int row = 1; row <= volumes.length; row++) {
            for (int col = 0; col <= maxVolume; col++) {
                boolean prev = MATRIX[row - 1][col];
                if (!MATRIX[row - 1][col]) {
                    continue;
                }
                int current = volumes[row - 1];
                if(col - current >= 0) {
                    MATRIX[row][col - current] = true;
                }
                if(col + current <= maxVolume) {
                    MATRIX[row][col + current] = true;
                }
            }
        }
        for (int i = maxVolume; i >= 0; i--) {
            if(MATRIX[volumes.length][i]) {
                return i;
            }
        }
        return -1;
    }
}