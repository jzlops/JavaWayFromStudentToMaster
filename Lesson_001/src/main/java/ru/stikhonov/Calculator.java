package ru.stikhonov;

/**
 * Used for simplest math calculations
 *
 * @author Sergey Tikhonov
 */
public class Calculator {

    private double result;

    /**
     * @param first  first value for addition
     * @param second second value for addition
     */

    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * @param first  first value for subtract
     * @param second second value for subtract
     */

    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * @param first  first value for division
     * @param second second value for division
     */

    public void div(double first, double second) {
        if (second != 0) {
            this.result = first / second;
        } else {
            System.out.println("Неккоректный параметр second");
        }
    }

    /**
     * @param first  first value for multiple
     * @param second second value for multiple
     */

    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * @return Return the last computational result
     */

    public double getResult() {
        return this.result;
    }
}
