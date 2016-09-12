package ru.stikhonov.term6;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */
public class StringComparationTest {

    @Test
    public void EqualsStrings() throws Exception {
        Assert.assertTrue(new StringComparation().contains("QweRty","QweRty"));
    }

    @Test
    public void OriginalContainSubstring() throws Exception {
        Assert.assertTrue(new StringComparation().contains("----QweRty---","QweRty"));
    }

    @Test
    public void SubstringLagerThanOriginal() throws Exception {
        Assert.assertFalse(new StringComparation().contains("QweRty","QweRty..TaramParamPam !"));
    }

    @Test
    public void OriginalNotContainSubstring() throws Exception {
        Assert.assertFalse(new StringComparation().contains("----Qwe-Rty---","QweRty"));
    }
}