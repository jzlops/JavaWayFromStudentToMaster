package ru.stikhonov.term4;

/**
 * Класс создания объекта факториал
 *
 * @author Sergey Tikhonov
 */
public class Factorial {
    /**
     * Метод вычисления факториала числа
     *
     * @param index факториал какого числа требуется вычислить
     * @return возвращает фактроиал
     */
    public float calculate(int index) {
        long result = 1;
        if (index <= 0) {
            return 1;
        } else {
            for (int i = 2; i <= index; i++) {
                result = result * i;
            }
            return result;
        }
    }
}