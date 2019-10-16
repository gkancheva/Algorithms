package l02_sorting_and_searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P01_Sorting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] arr = new int[1000];

        for (int i = 0; i < 1000; i++) {
            arr[i] = arr.length - 1 - i;
        }

        long start = System.currentTimeMillis();
        insertionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("Insertion Sort: " + (end - start));

        start = System.currentTimeMillis();
        bubbleSort(arr);
        end = System.currentTimeMillis();
        System.out.println("Bubble sort: " + (end - start));

        start = System.currentTimeMillis();
        shellSort(arr);
        end = System.currentTimeMillis();
        System.out.println("Shell sort: " + (end - start));

        start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("Merge sort: " + (end - start));

        start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();
        System.out.println("Quick sort: " + (end - start));

        start = System.currentTimeMillis();
        bucketSort(arr);
        end = System.currentTimeMillis();
        System.out.println("Bucket sort sort: " + (end - start));

        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static void bucketSort(int[] array) {
        int maxElement = array[0];
        int minElement = array[0];

        for (int element : array) {
            if (maxElement < element) {
                maxElement = element;
            }
            if (minElement > element) {
                minElement = element;
            }
        }

        int bucketCount = (maxElement - minElement) / 30 + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int i1 : array) {
            buckets.get((i1 - minElement) / 30).add(i1);
        }

        int currentIndex = 0;
        for (List<Integer> bucketArr : buckets) {
            int[] arr = new int[bucketArr.size()];
            for (int j = 0; j < bucketArr.size(); j++) {
                arr[j] = bucketArr.get(j);
            }
            insertionSort(arr);
            for (int element : arr) {
                array[currentIndex++] = element;
            }
        }
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        if(startIndex >= endIndex) {
            return;
        }
        int pivotIndex = getPartition(arr, startIndex, endIndex);
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    private static int getPartition(int[] array, int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex + 1;
        while(true) {
            while (array[++i] < array[startIndex]) {
                if (i == endIndex) {
                    break;
                }
            }
            while (array[startIndex] < array[--j]) {
                if (j == startIndex) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        int temp = array[j];
        array[j] = array[startIndex];
        array[startIndex] = temp;
        return j;
    }

    private static void mergeSort(int[] arr, int startIndex, int endIndex) {
        int[] aux = new int[arr.length];
        if(startIndex >= endIndex) {
            return;
        }
        int midIndex = (startIndex + endIndex) / 2;
        mergeSort(arr, startIndex, midIndex); // left part
        mergeSort(arr, midIndex + 1, endIndex); // right part
        if(midIndex < 0
                || midIndex >= arr.length
                || arr[midIndex] <= arr[midIndex + 1]) {
            return;
        }
        for (int index = startIndex; index <= endIndex; index++) {
            aux[index] = arr[index];
        }

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        for (int i = startIndex; i <= endIndex; i++) {
            if(leftIndex > midIndex) {
                arr[i] = aux[rightIndex++];
            } else if(rightIndex > endIndex) {
                arr[i] = aux[leftIndex++];
            } else if(aux[leftIndex] <= aux[rightIndex]) {
                arr[i] = aux[leftIndex++];
            } else {
                arr[i] = aux[rightIndex++];
            }
        }
    }

    private static void shellSort(int[] array) {
        for (int i = array.length / 2; i > 0; i /= 2) {
            for (int j = i; j < array.length; j++) {
                for (int k = j - i; k >= 0 && array[k] > array[k + i]; k -= i) {
                    int temp = array[k];
                    array[k] = array[k + i];
                    array[k + i] = temp;
                }
            }
        }
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            int current = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = current;
        }
    }
}