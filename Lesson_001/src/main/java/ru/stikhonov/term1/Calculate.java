package ru.stikhonov.term1;

import ru.stikhonov.term2.Calculator;

/**
 *
 * @author Sergey Tikhonov
 */
public class Calculate {
    /**
     * @param args List of command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello  .. lets try to use object");
        Calculator calculator = new Calculator();
        calculator.add(1,1);
        calculator.div(4,0);
        calculator.multiple(4,8);
        calculator.subtract(11,6);

    }

}
