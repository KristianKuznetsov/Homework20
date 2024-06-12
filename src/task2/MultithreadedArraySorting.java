package task2;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultithreadedArraySorting {
    private static final int ARRAY_SIZE = 10;

    public static void solve() {
        int[] array = generateRandomArray();
        System.out.println("Первоночальный массив: " + Arrays.toString(array));

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            int[] insertionSortedArray = Arrays.copyOf(array, array.length);
            insertionSort(insertionSortedArray);
            System.out.println("Отсортированный массив (сортировка вставками): " + Arrays.toString(insertionSortedArray));
        });

        executor.submit(() -> {
            int[] selectionSortedArray = Arrays.copyOf(array, array.length);
            selectionSort(selectionSortedArray);
            System.out.println("Отсортированный массив (сортировка выбором): " + Arrays.toString(selectionSortedArray));
        });

        executor.submit(() -> {
            int[] bubbleSortedArray = Arrays.copyOf(array, array.length);
            bubbleSort(bubbleSortedArray);
            System.out.println("Отсортированный массив (сортировка пузырьком): " + Arrays.toString(bubbleSortedArray));
        });

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateRandomArray() {
        int[] array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
