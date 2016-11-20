package ru.tikhonov.term1.polindromDetector;

/**
 * @author Sergey Tikhonov
 */
public class PalindromeRun {
    public static void main(String[] args) {
        InputOutput cInOut = new InOut();
        PalindromeDetector pd = new PalindromeDetector(cInOut);
        pd.palindromeCheck(cInOut.in());
    }


}
