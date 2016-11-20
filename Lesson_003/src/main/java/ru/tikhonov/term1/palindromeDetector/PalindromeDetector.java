package ru.tikhonov.term1.palindromeDetector;

/**
 * Класс для анализа поиска палиндрома в слове из 5 букв
 *
 * @author Sergey Tikhonov
 */
class PalindromeDetector {
    private String word;
    private InputOutput cInOut;

    PalindromeDetector(InputOutput cInOut) {
        this.cInOut = cInOut;
    }

    /**
     * Проверяем является ли слово палиндромом
     *
     * @param word проверяемое слово
     * @return возвращаемый результат
     */
    boolean palindromeCheck(String word) {
        this.word = word.toLowerCase();
        boolean result = false;
        if ((this.word.length() > 5) || (this.word.length() < 5)) {
            cInOut.out(printError());
            return result;
        }
        result = analyzeWord();
        cInOut.out(printResult(result));
        return result;
    }

    /**
     * Возвращает строку результата, в зависимости от переданного значения boolean
     *
     * @param result логическое отображение результата на предмет поиска палиндрома в слове
     * @return строка резуоттата
     */
    private String printResult(boolean result) {
        String resultMessage = "";
        if (result) resultMessage = "Слово является полиндромом";
        if (!result) resultMessage = "Слово не является полиндромом";
        return resultMessage;
    }

    /**
     * Возвращаем строку с текстом ошибки
     *
     * @return строка ошибки
     */
    private String printError() {
        return ("Размерность слова " + this.word + " должна быть 5 символов" + "\n");
    }

    /**
     * Анализируем слово на "палиндромность"
     *
     * @return tгue если переданное слово - палиндром
     */
    private boolean analyzeWord() {
        int signOddEven = this.word.length() % 2;
        StringBuilder s1 = new StringBuilder(this.word.substring(0, this.word.length() / 2));
        StringBuilder s2 = new StringBuilder(this.word.substring(this.word.length() / 2 + signOddEven));
        return (s1.toString().equals(s2.reverse().toString()));
    }

}
