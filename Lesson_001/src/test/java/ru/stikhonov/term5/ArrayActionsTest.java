package ru.stikhonov.term5;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Sergey Tikhonov
 */
public class ArrayActionsTest {

    @Test
    public void bubbleSort() throws Exception {
        int[] incomingArray = {0, 7, 3, 11, 99, 4, 4};
        int[] expectedArray = {0, 3, 4, 4, 7, 11, 99};
        new ArrayBubbleSort().bubbleSort(incomingArray);
        assertArrayEquals(expectedArray, incomingArray);
    }

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

    @Test
    public void duplicateStringTruncate() throws Exception {
        String[] incomingStringArray = {"1", "2", "2", "5", "1", "5", "6", "1", "2", "5"};
        String[] expectedStringArray = {"1", "2", "5", "6"};
        assertArrayEquals(expectedStringArray, new ArrayStringDuplicateTruncate().duplicateStringKill(incomingStringArray));
    }
}