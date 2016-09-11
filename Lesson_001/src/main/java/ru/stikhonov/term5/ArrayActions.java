package ru.stikhonov.term5;

/**
 * Класс содержит ряд методов для оперирования готовыми массивами int[] int[][] и String[] произвольной велечины
 *
 * @author Sergey Tikhonov
 */
public class ArrayActions {
    /**
     * Метод сортировки одномерного массива типа int[]
     *
     * @param array вхоодной параметр - массив int[]
     */
    public static void bubbleSort(int[] array) {
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

    /**
     * Метод "переворачивает" квадратный массив на 90 градусов
     *
     * @param array вхоодной параметр - массив int[][]
     */
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

    /**
     * Метод выводит в консоль содержимое массива int[]
     *
     * @param array вхоодной параметр - одномерный массив int[]
     */
    public static void show(int[] array) {
        System.out.println("Одномерный массив из - " + array.length + " элементов:");
        System.out.println();
        for (int i : array)
            System.out.print(i + "  ");
        System.out.println();
        System.out.println();
    }

    /**
     * Метод выводит в консоль содержимое массива int[][]
     *
     * @param array вхоодной параметр - квадратный массив int[][]
     */
    public static void show(int[][] array) {
        System.out.println("Двухмерный квадратный массив размерностью - " + array.length + "х" + array.length + " элементов:");
        System.out.println();
        for (int[] pointer : array) {
            for (int element : pointer)
                System.out.print(element + "  ");
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Метод выводит в консоль массив строк String[]
     *
     * @param array вхоодной параметр - массив String[]
     */
    public static void show(String[] array) {
        System.out.println("Строковый массив из - " + array.length + " элементов:");
        System.out.println();
        for (String s : array)
            System.out.print(s + "  ");
        System.out.println();
        System.out.println();
    }

    /**
     * Метод удаляет дубликаты строк из массива String[]
     *
     * @param strings вхоодной параметр - массив String[]
     * @return возвращает ссылку на новый массив String[] без дубликатов
     */
    public static String[] duplicateStringKill(String[] strings) {
        int fillArrayCounter = 0;
        int duplicateCount = 0;
        final String EMPTY = "EMPTY";
        String[] tempStrings;
        for (int i = 0; i < strings.length; i++)
            if (!(strings[i].equals(EMPTY)))
                for (int j = i; j < strings.length - 1; j++)
                    if (strings[i].equals(strings[j + 1])) {
                        duplicateCount++;
                        strings[j + 1] = EMPTY;
                        j++;
                    }

        tempStrings = new String[strings.length - duplicateCount];

        for (int i = 0; i < strings.length; i++)
            if (!(strings[i].equals(EMPTY))) {
                tempStrings[fillArrayCounter] = strings[i];
                fillArrayCounter++;
            }
        System.out.println("Удаление дубликатов строкового массива выполнено");
        System.out.println();
        return tempStrings;
    }
}
