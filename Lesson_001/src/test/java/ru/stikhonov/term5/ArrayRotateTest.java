package ru.stikhonov.term5;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Sergey Tikhonov
 */
public class ArrayRotateTest {

    @Test
    public void rotate() throws Exception {
        int[][] incomingArray = {
                {0, 1, 22},
                {7, 99, 0},
                {11, 4, 15}
        };
        int[][] expectedArray = {
                {11, 7, 0},
                {4, 99, 1},
                {15, 0, 22}
        };
        new ArrayRotate().rotate(incomingArray);
        assertArrayEquals(expectedArray, incomingArray);
    }
}
