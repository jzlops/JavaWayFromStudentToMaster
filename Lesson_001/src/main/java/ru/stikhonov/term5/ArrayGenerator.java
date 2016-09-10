package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class ArrayGenerator {

    public static int[] generate1X(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = (int) (Math.random() * 100);
        return array;
    }

    public static int[][] generate2X(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                array[i][j] = (int) (Math.random() * 100);
        return array;
    }

}
