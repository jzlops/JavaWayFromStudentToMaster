package ru.stikhonov.term1;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Тестирования класса ConsoleInputHelper отвечающего за ввод, обработку, парксинг, прверку введеных данных пользователем
 * Для тестирвания используются методы класса ConsoleInputHelper принимающие в качестве
 * входного параметра - строку (вместо пользовательского ввода). Цель данного класса протестировать именно парсинг
 * и проверку введеных пользователем данных
 *
 * @author Sergey Tikhonov
 */
public class TestConsoleInputHelper {
    /**
     * Проверка корректно введеной даты в виде строки вида "yyyy.MM.dd HH:mm:ss"
     *
     * @throws Exception
     */
    @Test
    public void dateEntryCorrect() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ConsoleInputHelper cInput = new ConsoleInputHelper();
        Assert.assertEquals(cInput.dateEntry("2222.22.22 22:22:22"), simpleDateFormat.parse("2222.22.22 22:22:22"));
    }

    /**
     * Проверка некорректно введеной даты
     *
     * @throws Exception
     */
    @Test
    public void dateEntryIncorrect() throws Exception {
        ConsoleInputHelper cInput = new ConsoleInputHelper();
        Assert.assertNull(cInput.dateEntry("Fake"));
    }

    /**
     * Проверка ввода корректных данных выбора меню
     *
     * @throws Exception
     */
    @Test
    public void intEntryCorrect() throws Exception {
        ConsoleInputHelper cInput = new ConsoleInputHelper();
        Assert.assertEquals(cInput.intEntry("3"), 3);
    }

    /**
     * Проверка ввода некорректных данных выбора меню
     *
     * @throws Exception
     */
    @Test
    public void intEntryIncorrect() throws Exception {
        ConsoleInputHelper cInput = new ConsoleInputHelper();
        Assert.assertEquals(cInput.intEntry("Vasya"), -1);
    }

    /**
     * Проверка ввода символа переноса строки
     *
     * @throws Exception
     */
    @Test
    public void anyKeyEntry() throws Exception {
        ConsoleInputHelper cInput = new ConsoleInputHelper();
        Assert.assertTrue(cInput.anyKeyEntry("\n"));
    }
}
