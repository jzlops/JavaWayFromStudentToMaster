package ru.stikhonov.term2;

/**
 * Класс калькулятор, создает объект калькулятор с минимальным набором методов
 *
 * @author Sergey Tikhonov
 */
public class Calculator {

    private double result;

    /**
     * Метод выполняющий простое сложение чисел
     *
     * @param first  первое значение
     * @param second второе значение
     */

    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Метод выполняющий простое вычитание чисел
     *
     * @param first  первое значение
     * @param second второе значение
     */

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Метод выполняющий простое деление чисел
     *
     * @param first  первое значение
     * @param second второе значение
     */

    public void div(double first, double second) {
        if (second != 0) {
            this.result = first / second;
        } else {
            System.out.println("Неккоректный параметр second");
        }
    }

    /**
     * Метод выполняющий простое умножение чисел
     *
     * @param first  первое значение
     * @param second второе значение
     */

    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Метод возращающий последнее вычесленное значение
     *
     * @return возвращаемое значение
     */

    public double getResult() {
        return this.result;
    }
}
