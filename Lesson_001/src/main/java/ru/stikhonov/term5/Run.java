package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class Run {
    public static void main(String[] args) {
        int[] array1X = ArrayGenerator.generate1X(6);
        int[][] array2X = ArrayGenerator.generate2X(40);

        ArrayActions.show(array1X);
        ArrayActions.bubleSort(array1X);
        ArrayActions.show(array1X);

        ArrayActions.show(array2X);
        ArrayActions.rotate(array2X);
        ArrayActions.show(array2X);
    }
}
