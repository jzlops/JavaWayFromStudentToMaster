package ru.stikhonov.term6;

/**
 * @author Sergey Tikhonov
 */
public class StringComparation {
    /**
     * Метод сравнения двух строк
     *
     * @param string1 исходная строка
     * @param string2 сравниваемая строка
     * @return возвращает логическое значение true если, исходная строка содержит сравниваемую. Иначе - false.
     */
    public boolean contains(String string1, String string2) {
        char[] charSet1;
        char[] charSet2;
        charSet1 = string1.toCharArray();
        charSet2 = string2.toCharArray();
        int countFit = 1;
        for (int i = 0; i <= charSet1.length - charSet2.length; i++)
            if (charSet2[0] == charSet1[i]) {
                for (int j = 1; j < charSet2.length; j++)
                    if (charSet2[j] == charSet1[i + j]) countFit++;
                if (countFit == charSet2.length)
                    return true;
                countFit = 1;
            }
        return false;
    }
}
