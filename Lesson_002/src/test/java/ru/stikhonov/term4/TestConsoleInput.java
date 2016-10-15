package ru.stikhonov.term4;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;


/**
 * Тестирования класса ConsoleInput отвечающего за ввод, обработку, парксинг, прверку введеных данных пользователем
 * Для тестирвания используются методы класса ConsoleInput принимающие в качестве
 * входного параметра - строку (вместо пользовательского ввода). Цель данного класса протестировать именно парсинг
 * и проверку введеных пользователем данных
 *
 * @author Sergey Tikhonov
 */
public class TestConsoleInput {
    /**
     * Проверка корректно введеной даты в виде строки вида "yyyy.MM.dd HH:mm:ss"
     *
     * @throws Exception
     */
    @Test
    public void dateEntryCorrect() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ConsoleInput cInput = new ConsoleInput();
        Assert.assertEquals(cInput.dateParse("2222.22.22 22:22:22"), simpleDateFormat.parse("2222.22.22 22:22:22"));
    }

    /**
     * Проверка некорректно введеной даты
     *
     * @throws Exception
     */
    @Test
    public void dateEntryIncorrect() throws Exception {
        ConsoleInput cInput = new ConsoleInput();
        Assert.assertNull(cInput.dateParse("Fake"));
    }

    /**
     * Проверка ввода корректных данных выбора меню
     *
     * @throws Exception
     */
    @Test
    public void intEntryCorrect() throws Exception {
        ConsoleInput cInput = new ConsoleInput();
        Assert.assertEquals(cInput.intParse("3"), 3);
    }

    /**
     * Проверка ввода некорректных данных выбора меню
     *
     * @throws Exception
     */
    @Test
    public void intEntryIncorrect() throws Exception {
        ConsoleInput cInput = new ConsoleInput();
        Assert.assertEquals(cInput.intParse("Vasya"), -1);
    }

    /**
     * Проверка ввода символа переноса строки
     *
     * @throws Exception
     */
    @Test
    public void anyKeyEntry() throws Exception {
        ConsoleInput cInput = new ConsoleInput();
        Assert.assertTrue(cInput.anyKeyParse("\n"));
    }
}
