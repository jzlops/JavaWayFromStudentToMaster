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

        try (Scanner scanner = new Scanner(in)) {
            if (scanner.hasNext()) {
                i = scanner.nextInt();
                result = (i % 2 == 0);
          //      System.out.println(i);
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }
}
