package ru.stikhonov.term4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sergey Tikhonov on 10.09.2016.
 */
public class SquareTest {

    @Test
    public void calculateSquare() throws Exception {
        Square square=new Square(1,2,3);
        assertEquals(square.calculate(5),38,00.1);
    }
}