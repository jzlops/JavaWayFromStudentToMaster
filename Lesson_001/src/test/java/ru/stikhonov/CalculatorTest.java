package ru.stikhonov;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Sergey Tikhonov on 07.09.2016.
 */
public class CalculatorTest {
    Calculator testcalc = new Calculator();

    @Test
    public void testAdd() throws Exception {
        testcalc.add(5, 5);
        assertThat(testcalc.getResult(), closeTo(10,0.001));
    }

    @Test
    public void testSubtract() throws Exception {
        testcalc.subtract(5, 5);
        assertThat(testcalc.getResult(), closeTo(0,0.001));
    }

    @Test
    public void testDiv() throws Exception {
        testcalc.div(5, 5);
        assertThat(testcalc.getResult(), closeTo(1,0.001));
    }

    @Test
    public void testMultiple() throws Exception {
        testcalc.multiple(5, 5);
        assertThat(testcalc.getResult(), closeTo(25,0.001));

    }
}