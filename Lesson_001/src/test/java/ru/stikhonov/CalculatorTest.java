package ru.stikhonov;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sergey Tikhonov on 07.09.2016.
 */
public class CalculatorTest {
    Calculator testcalc = new Calculator();

    @Test
    public void testAdd() throws Exception {
        testcalc.add(5,5);
        Assert.assertEquals(10, testcalc.showResult(),0);

    }

    @Test
    public void testSubtract() throws Exception {
        testcalc.subtract(5,5);
        Assert.assertEquals(0, testcalc.showResult(),0);
    }

    @Test
    public void testDiv() throws Exception {
        testcalc.div(5,5);
        Assert.assertEquals(1, testcalc.showResult(),0);
    }

    @Test
    public void testMultiple() throws Exception {
        testcalc.multiple(5,5);
        Assert.assertEquals(25, testcalc.showResult(),0);

    }
}