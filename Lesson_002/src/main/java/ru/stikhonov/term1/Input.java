package ru.stikhonov.term1;

import java.text.ParseException;
import java.util.Date;

/**
 * Общий интерфейс ввода данных
 *
 * @author Sergey Tikhonov
 */
 interface Input {
    /**
     * Метод должен возвратить значение типа Date при вводе данных
     *
     * @return возвращает объект типа Date
     * @throws ParseException
     */
    Date dateEntry();

    /**
     * Метод должен возвратить значение типа int при вводе данных
     *
     * @return возвращает целое значение int
     */
    int intEntry();

    /**
     * Метод должен возвратить значение типа String при вводе данных
     *
     * @return возвращает строку String
     */
    String stringEntry();

    /**
     * Метод должен ожидать нажатия клавиши "ввод"
     */
    boolean anyKeyEntry();

}
