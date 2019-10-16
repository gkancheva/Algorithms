package l02_sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P03_InversionCount {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(mergeSort(arr, new int[arr.length], 0, arr.length - 1));

    }

    private static int mergeSort(int[] nums, int[] aux, int leftIndex, int rightIndex) {
        int mid = 0;
        int inversionCount = 0;
        if (rightIndex > leftIndex) {
            mid = (rightIndex + leftIndex) / 2;
            inversionCount = mergeSort(nums, aux, leftIndex, mid);
            inversionCount += mergeSort(nums, aux, mid + 1, rightIndex);
            inversionCount += merge(nums, aux, leftIndex, mid + 1, rightIndex);
        }
        return inversionCount;
    }

    private static int merge(int[] nums, int[] aux, int leftIndex, int midIndex, int rightIndex) {
        int  i = leftIndex;
        int j = midIndex;
        int k = leftIndex;

        int inversionCount = 0;

        while((i <= midIndex - 1) && (j <= rightIndex)) {
            if(nums[i] <= nums[j]) {
                aux[k++] = nums[i++];
            } else {
                aux[k++] = nums[j++];
                inversionCount = inversionCount + (midIndex - i);
            }
        }

        while(i <= midIndex - 1) {
            aux[k++] = nums[i++];
        }

        while(j <= rightIndex) {
            aux[k++] = nums[j++];
        }

        for (i = leftIndex; i <= rightIndex; i++) {
            nums[i] = aux[i];
        }
        return inversionCount;

    }
}