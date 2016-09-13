package ru.stikhonov.term5;

/**
 * @author Sergey Tikhonov
 */
public class ArrayRotate {
    /**
     * Метод "переворачивает" квадратный массив на 90 градусов
     *
     * @param array вхоодной параметр - массив int[][]
     */
    public void rotate(int[][] array) {
        int size = array.length;
        int[][] tempArray = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tempArray[i][j] = array[i][j];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = tempArray[size - j - 1][i];
            }
        }

    }

}
