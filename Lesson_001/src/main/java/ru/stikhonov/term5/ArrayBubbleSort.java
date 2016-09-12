package ru.stikhonov.term5;

/**
 * @author Sergey Tikhonov
 */
public class ArrayBubbleSort {
    /**
     * Метод сортировки одномерного массива типа int[]
     *
     * @param array вхоодной параметр - массив int[]
     */
    public void bubbleSort(int[] array) {
        for (int i = array.length - 1; i >= 0; i--)
            for (int j = 0; j < i; j++)
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
        System.out.printf("Сортировка массива выполнена %n%n");
    }
}
