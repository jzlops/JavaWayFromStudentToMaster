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

    public static int[][] rotate(int[][] array) {
        int[][] tempArray = array.clone();
        int length = array.length;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                tempArray[i][j] = array[length - j - 1][i];
        return tempArray;
    }
}
