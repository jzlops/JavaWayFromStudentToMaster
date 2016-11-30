package ru.tikhonov.term1.palindromeDetector;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */
public class PalindromeServerRunTest {
    @Test
    public void isPalindrome() {
        InputOutput cInOut = new StubInOut();
        PalindromeDetector pd = new PalindromeDetector(cInOut);
        ((StubInOut) cInOut).setInput("11111");
        System.out.println(cInOut.in());
        Assert.assertTrue(pd.palindromeCheck(cInOut.in()));
    }

    @Test
    public void isNotPalindrome() {
        InputOutput cInOut = new StubInOut();
        PalindromeDetector pd = new PalindromeDetector(cInOut);
        ((StubInOut) cInOut).setInput("Gf1Fd");
        Assert.assertFalse(pd.palindromeCheck(cInOut.in()));
    }

    @Test
    public void isIncorrectWordLength() throws Exception {
        InputOutput cInOut = new StubInOut();
        PalindromeDetector pd = new PalindromeDetector(cInOut);
        ((StubInOut) cInOut).setInput("Gf1Fddsfdsfsdf");
        Assert.assertFalse(pd.palindromeCheck(cInOut.in()));
    }
}