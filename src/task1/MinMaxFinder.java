package task1;

import java.util.Arrays;
import java.util.Scanner;

public class MinMaxFinder {
    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        Thread minThread = new Thread(() -> {
            int min = Arrays.stream(array).min().getAsInt();
            System.out.println("Минимальное значение: " + min);
        });
        Thread maxThread = new Thread(() -> {
            int max = Arrays.stream(array).max().getAsInt();
            System.out.println("Максимальное значение: " + max);
        });

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}