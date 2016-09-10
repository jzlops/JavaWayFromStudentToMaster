package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class Run {
    public static void main(String[] args) {
        int[] array1X = ArrayGenerator.generate1X(10);
        int[][] array2X = ArrayGenerator.generate2X(10);
        ArrayActions.bubleSort(array1X);
        array2X = ArrayActions.rotate(array2X);

    }
}
