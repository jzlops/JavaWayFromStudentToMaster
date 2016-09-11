package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class Run {
    public static void main(String[] args) {
        int[] array1X = ArrayGenerator.intGenerate1X(10);
        int[][] array2X = ArrayGenerator.intGenerate2X(5);
        String[] strings = ArrayGenerator.stringGenerate();

        ArrayActions.show(array1X);
        ArrayActions.bubbleSort(array1X);
        ArrayActions.show(array1X);

        ArrayActions.show(array2X);
        ArrayActions.rotate(array2X);
        ArrayActions.show(array2X);

        ArrayActions.show(strings);
        strings = ArrayActions.duplicateStringKill(strings);
        ArrayActions.show(strings);

    }
}
