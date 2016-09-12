package ru.stikhonov.term6;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */
public class StringComparationTest {

    @Test
    public void whenEqualsStrings() throws Exception {
        Assert.assertTrue(new StringComparation().contains("QweRty","QweRty"));
    }

    @Test
    public void whenOriginalContainSubstring() throws Exception {
        Assert.assertTrue(new StringComparation().contains("----QweRty---","QweRty"));
    }

    @Test
    public void whenSubstringLagerThanOriginal() throws Exception {
        Assert.assertFalse(new StringComparation().contains("QweRty","QweRty..TaramParamPam !"));
    }

    @Test
    public void whenOriginalNotContainSubstring() throws Exception {
        Assert.assertFalse(new StringComparation().contains("----Qwe-Rty---","QweRty"));
    }
}