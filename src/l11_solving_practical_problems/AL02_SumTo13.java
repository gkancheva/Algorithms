package l11_solving_practical_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//combinations
public class AL02_SumTo13 {
    private static int TARGET = 13;
    private static boolean IS_TARGET;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        calculateSum(0, 0, nums);

        System.out.println(IS_TARGET ? "Yes" : "No");

    }

    private static void calculateSum(int sum, int index, int[] nums) {
        if(index >= nums.length) {
            if(sum == TARGET) {
                 IS_TARGET = true;
            }
            return;
        }
        calculateSum(sum + nums[index], index + 1, nums);
        calculateSum(sum - nums[index], index + 1, nums);
    }

}