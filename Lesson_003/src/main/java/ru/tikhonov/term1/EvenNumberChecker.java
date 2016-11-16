package ru.tikhonov.term1;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Реализация интерфейса StreamCheck
 * С входного потока читаем значения int
 * Для сканирования входного потока используем класс Scanner, можно и по байтам разбирать, но тут нет смысла думаю
 *
 * @author Sergey Tikhonov
 */
class EvenNumberChecker implements StreamCheck {
    public boolean isNumber(InputStream in) {
        int i;
        boolean result = false;
        Scanner scanner = new Scanner(in);
        try {
            if (scanner.hasNext()) {
                i = scanner.nextInt();
                result = (i % 2 == 0);
            }
        } catch (Exception e) {
            return false;
        }
        scanner.close();
        return result;
    }
}
