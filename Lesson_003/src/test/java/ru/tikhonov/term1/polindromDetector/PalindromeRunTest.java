package ru.tikhonov.term1.polindromDetector;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */
public class PalindromeRunTest {
    @Test
    public void isPalindrome() {
        InputOutput cInOut = new StubInOut();
        PalindromeDetector pd = new PalindromeDetector(cInOut);
        ((StubInOut) cInOut).setInput("Gf1FG");
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