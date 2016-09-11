package ru.stikhonov.term5;

/**
 * Класс для создания массивов простых массивов int и String
 *
 * @author Sergey Tikhonov
 */
public class ArrayGenerator {
    private static final String[] STRINGS = {"Hello", "Buy", "Welcome", "WTF", "Dude", "Kenny", "etc", "Collaboration", "TV", "Traverse"};

    /**
     * Метод генерации одномерного массива int[]
     *
     * @param size размер массива
     * @return возвращает ссылку на объект массива типа int[size]
     */
    public static int[] intGenerate1X(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = (int) (Math.random() * 10 - 1);
        return array;
    }

    /**
     * Метод генерации квадратного массива int[][]
     *
     * @param size размер массива
     * @return возвращает ссылку на объект массива типа int[size][size]
     */
    public static int[][] intGenerate2X(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                array[i][j] = (int) (Math.random() * 10 - 1);
        return array;
    }

    /**
     * Метод генерации массива String[] из 10 предопределенных значений (значения в ячейках массива распологаются в произвольном порядке)
     *
     * @return возращает ссылку на объект массива String[10]
     */
    public static String[] stringGenerate() {
        String[] array = new String[STRINGS.length];
        for (int i = 0; i < STRINGS.length; i++)
            array[i] = STRINGS[(int) (Math.random() * 10 - 1)];
        return array;
    }
}