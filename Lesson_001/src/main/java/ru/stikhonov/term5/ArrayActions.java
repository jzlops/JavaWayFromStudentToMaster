package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class ArrayActions {
    public static void bubleSort(int[] array) {
        for (int i = array.length - 1; i >= 0; i--)
            for (int j = 0; j < i; j++)
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }

    public static void rotate(int[][] array) {

    }
}
