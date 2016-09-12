package ru.stikhonov.term4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class FactorialTest {

    @Test
    public void whenFactorialHasNormalArgument() throws Exception {
        Factorial factorial = new Factorial();
        assertEquals(factorial.calculate(4), 24, 0);
    }

    @Test
    public void whenFactorialHasZeroArgument() throws Exception {
        Factorial factorial = new Factorial();
        assertEquals(factorial.calculate(1), 1, 0);
    }

    @Test
    public void whenFactorialHasNegativeArgument() throws Exception {
        Factorial factorial = new Factorial();
        assertEquals(factorial.calculate(-4), 1, 0);
    }
}