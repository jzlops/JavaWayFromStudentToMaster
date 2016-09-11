package ru.stikhonov.term4;

/**
 * Класс создания объекта Square для вычисления функции y = a*x2 + b * x + c
 *
 * @author Sergey Tikhonov
 */
public class Square {
    private float a, b, c;

    /**
     * Конструктор объета функции y = a*x2 + b * x + c
     *
     * @param a a параметр в функции  y = a*x2 + b * x + c
     * @param b b параметр в функции  y = a*x2 + b * x + c
     * @param c c параметр в функции  y = a*x2 + b * x + c
     */
    public Square(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления значения y функции y = a*x2 + b * x + c
     *
     * @param x x параметр в функции  y = a*x2 + b * x + c
     * @return возращает значение y функции y = a*x2 + b * x + c
     */
    public float calculate(int x) {
        float y;
        y = this.a * x * x + this.b * x + this.c;
        return y;
    }

    /**
     * Метод выводит на экран значение y в уравнении y = a*x2 + b * x + c при изменении x
     *
     * @param start  начальное значение x в уравнении y = a*x2 + b * x + c
     * @param finish конечное значение x в уравнении y = a*x2 + b * x + c
     * @param step   шаг с которым изменяется x в уравнении y = a*x2 + b * x + c
     */
    public void show(int start, int finish, int step) {
        for (int i = start; i <= finish; i = i + step) {
            System.out.println("x=" + i + " y=" + calculate(i));
        }
    }

}
