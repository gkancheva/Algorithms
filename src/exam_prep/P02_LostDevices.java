package exam_prep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P02_LostDevices {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] positions = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] destinations = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(positions);
        Arrays.sort(destinations);

        int time = Integer.MIN_VALUE;

        for (int i = 0; i < positions.length; i++) {
            int currentTime = Math.abs(positions[i] - destinations[i]);
            if(time < currentTime) {
                time = currentTime;
            }
        }

        System.out.println("Job done in " + time + " hours");
    }
}