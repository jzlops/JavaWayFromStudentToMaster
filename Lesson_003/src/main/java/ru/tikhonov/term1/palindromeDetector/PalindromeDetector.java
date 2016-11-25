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
        cInOut.out(printResult(analyzeWord1()));
        cInOut.out(printResult(analyzeWord2()));

        return analyzeWord1();
    }

    /**
     * Возвращает строку результата, в зависимости от переданного значения boolean
     *
     * @param result логическое отображение результата на предмет поиска палиндрома в слове
     * @return строка резуоттата
     */
    private String printResult(boolean result) {
        String resultMessage = "";
        if (result) resultMessage = "Слово является палиндромом";
        if (!result) resultMessage = "Слово не является палиндромом";
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
    private boolean analyzeWord1() {
        return (this.word.equals(new StringBuilder(this.word).reverse().toString()));
    }

    private boolean analyzeWord2() {
        boolean result = true;
        char[] chars = this.word.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
