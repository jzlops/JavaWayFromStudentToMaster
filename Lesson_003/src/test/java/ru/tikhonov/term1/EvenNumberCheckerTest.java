package ru.tikhonov.term1;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

/**
 * @author Sergey Tikhonov
 */
public class EvenNumberCheckerTest {
    /**
     * Проверяем что число четное
     */
    @Test
    public void checkNumberIsEven() throws Exception {
        boolean result;
        EvenNumberChecker checker = new EvenNumberChecker();
        result=checker.isNumber(new ByteArrayInputStream("100".getBytes()));
        Assert.assertTrue(result);
    }

    /**
     * Проверяем что число нечетное
     */
    @Test
    public void checkNumberIsOdd() throws Exception {
        boolean result;
        EvenNumberChecker checker = new EvenNumberChecker();
        result=checker.isNumber(new ByteArrayInputStream("101".getBytes()));
        Assert.assertFalse(result);
    }

    /**
     * Делаем проверку если число не является числом
     */
    @Test
    public void checkNumberIsIncorrect() throws Exception {
        boolean result;
        EvenNumberChecker checker = new EvenNumberChecker();
        result=checker.isNumber(new ByteArrayInputStream("asd".getBytes()));
        Assert.assertFalse(result);

    }

}