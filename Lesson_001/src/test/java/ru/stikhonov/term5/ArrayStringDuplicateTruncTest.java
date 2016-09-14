package ru.stikhonov.term5;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author Sergey Tikhonov
 */
public class ArrayStringDuplicateTruncTest {
    @Test
    public void duplicateStringTruncate() throws Exception {
        String[] incomingStringArray = {"1", "2", "2", "5", "1", "5", "6", "1", "2", "5"};
        String[] expectedStringArray = {"1", "2", "5", "6"};
        assertArrayEquals(expectedStringArray, new ArrayStringDuplicateTruncate().duplicateStringKill(incomingStringArray));
    }
}