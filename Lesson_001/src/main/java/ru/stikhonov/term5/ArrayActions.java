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
        System.out.println("Сортировка массива выполнена");
        System.out.println();
    }

    public static void rotate(int[][] array) {
        int size = array.length;
        int[][] tempArray = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                tempArray[i][j] = array[i][j];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                array[i][j] = tempArray[size - j - 1][i];

        System.out.println("Переворот массива выполнен");
        System.out.println();
    }

    public static void show(int[] array) {
        System.out.println("Одномерный массив из - " + array.length + " элементов:");
        System.out.println();
        for (int i : array) {
            System.out.print(i + "  ");
        }
        System.out.println();
        System.out.println();
    }

    public static void show(int[][] array) {
        System.out.println("Двухмерный квадратный массив размерностью - " + array.length + "х" + array.length + " элементов:");
        System.out.println();
        for (int[] pointer : array) {
            for (int element : pointer) {
                System.out.print(element + "  ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

}
