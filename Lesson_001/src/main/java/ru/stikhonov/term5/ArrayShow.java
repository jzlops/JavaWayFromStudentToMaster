package ru.stikhonov.term5;

/**
 * @author Sergey Tikhonov
 */
public class ArrayShow {
    /**
     * Метод выводит в консоль содержимое массива int[]
     *
     * @param array вхоодной параметр - одномерный массив int[]
     */
    public void show(int[] array) {
        System.out.printf("Одномерный массив из - %1$d элементов: %n %n", array.length);
        for (int i : array)
            System.out.printf("%1$d ", i);
        System.out.printf("%n %n");
    }

    /**
     * Метод выводит в консоль содержимое массива int[][]
     *
     * @param array вхоодной параметр - квадратный массив int[][]
     */
    public void show(int[][] array) {
        System.out.printf("Двухмерный квадратный массив размерностью - %1$d x %2$d элементов %n%n",array.length,array.length);
        for (int[] pointer : array) {
            for (int element : pointer)
                System.out.printf(" %1$d  ",element);
            System.out.printf("%n");
        }
        System.out.printf("%n");
        System.out.printf("%n");
    }

    /**
     * Метод выводит в консоль массив строк String[]
     *
     * @param array вхоодной параметр - массив String[]
     */
    public void show(String[] array) {
        System.out.printf("Строковый массив из - %1$s элементов %n%n",array.length);
        for (String s : array)
            System.out.printf(" %1$s  ",s);
        System.out.printf("%n%n");
    }

}
