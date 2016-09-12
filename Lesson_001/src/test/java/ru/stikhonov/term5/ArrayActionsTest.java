package ru.stikhonov.term5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Tikhonov
 */
public class ArrayActionsTest {

    @Test
    public void bubbleSort() throws Exception {
        int[] testInt = {0,7,3,11,99,4,4};
        new ArrayBubbleSort().bubbleSort(testInt);
        assertEquals(testInt[0], 0, 0);
        assertEquals(testInt[1], 3, 0);
        assertEquals(testInt[2], 4, 0);
        assertEquals(testInt[3], 4, 0);
        assertEquals(testInt[4], 7, 0);
        assertEquals(testInt[5], 11, 0);
        assertEquals(testInt[6], 99, 0);
    }

    @Test
    public void rotate() throws Exception {
        int[][] testInt;
        testInt=new int[3][3];

        testInt[0][0] = 0; testInt[0][1] = 1; testInt[0][2] = 22;
        testInt[1][0] = 7; testInt[1][1] = 99; testInt[1][2] = 0;
        testInt[2][0] = 11; testInt[2][1] = 4; testInt[2][2] = 15;

        new ArrayRotate().rotate(testInt);
        assertEquals(testInt[0][0], 11, 0); assertEquals(testInt[0][1], 7, 0); assertEquals(testInt[0][2], 0, 0);
        assertEquals(testInt[1][0], 4, 0); assertEquals(testInt[1][1], 99, 0); assertEquals(testInt[1][2], 1, 0);
        assertEquals(testInt[2][0], 15, 0); assertEquals(testInt[2][1], 0, 0); assertEquals(testInt[2][2], 22, 0);
    }


    @Test
    public void duplicateStringTruncate() throws Exception {
        String[] testString={"1","2","2","5","1","5","6","1","2","5"};
        testString=new ArrayStringDuplicateTruncate().duplicateStringKill(testString);

        assertEquals(testString[0], "1");
        assertEquals(testString[1], "2");
        assertEquals(testString[2], "5");
        assertEquals(testString[3], "6");

    }
}