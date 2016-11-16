package ru.tikhonov.term1;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sergey Tikhonov
 */
interface StreamCheck {
    /**
     * Метод проверят что в потоке записано четное число
     *
     * @param in обхект InputStream
     * @return true если все корректно
     */
    boolean isNumber(InputStream in) throws IOException;
}
