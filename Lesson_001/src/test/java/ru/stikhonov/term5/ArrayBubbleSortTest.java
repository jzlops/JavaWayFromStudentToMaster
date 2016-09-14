package ru.stikhonov.term5;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Sergey Tikhonov
 */
public class ArrayBubbleSortTest {

    @Test
    public void bubbleSort() throws Exception {
        int[] incomingArray = {0, 7, 3, 11, 99, 4, 4};
        int[] expectedArray = {0, 3, 4, 4, 7, 11, 99};
        new ArrayBubbleSort().bubbleSort(incomingArray);
        assertArrayEquals(expectedArray, incomingArray);
    }
}