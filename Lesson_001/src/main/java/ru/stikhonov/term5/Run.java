package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class Run {
    public static void main(String[] args) {
        ArrayGenerator arrayGenerator = new ArrayGenerator();
        int[] array1X = arrayGenerator.intGenerate1X(10);
        int[][] array2X = arrayGenerator.intGenerate2X(5);
        String[] strings = arrayGenerator.stringGenerate();

        ArrayBubbleSort arrayBubbleSort=new ArrayBubbleSort();
        ArrayRotate arrayRotate =new ArrayRotate();
        ArrayShow arrayShow=new ArrayShow();
        ArrayStringDuplicateTruncate arrayStringDuplicateTruncate = new ArrayStringDuplicateTruncate();

        arrayShow.show(array1X);
        arrayBubbleSort.bubbleSort(array1X);
        arrayShow.show(array1X);

        arrayShow.show(array2X);
        arrayRotate.rotate(array2X);
        arrayShow.show(array2X);

        arrayShow.show(strings);
        strings = arrayStringDuplicateTruncate.duplicateStringKill(strings);
        arrayShow.show(strings);
    }
}
