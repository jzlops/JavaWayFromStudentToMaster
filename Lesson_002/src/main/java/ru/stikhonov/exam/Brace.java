package ru.stikhonov.exam;

/**
 * @author Sergey Tikhonov
 */
class Brace {
    boolean verify(String braces) {
        int fit = 0;
        boolean result = false;

        for (int i = 0; i < braces.length(); i++) {
            if (braces.charAt(i) == '(') {
                fit++;
            }
            if (braces.charAt(i) == ')') {
                fit--;
            }
        }
        if (fit == 0) {
            result = true;
        }
        return result;
    }
}
