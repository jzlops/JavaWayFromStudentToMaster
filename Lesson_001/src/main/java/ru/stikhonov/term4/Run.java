package ru.stikhonov.term4;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) {
        Square square = new Square(1, 2, 3);
        square.show(5, 20, 2);

        Factorial factorial = new Factorial();
        System.out.printf("Factorial = %1$d", factorial.calculate(5));
    }
}
