package ru.stikhonov.term1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;


/**
 * Тестирования класса ConsoleInputHelper отвечающего за ввод, обработку, парксинг, прверку введеных данных пользователем
 * а так же за сам диалог с пользователем
 *
 * @author Sergey Tikhonov
 */
public class TestConsoleInputHelper {
    @Test
    public void dateEntry() throws Exception {

    }

    @Test
    public void intEntry() throws Exception {

    }

    @Test
    public void stringEntry() throws Exception {

    }

    @Test
    public void anyKeyEntry() throws Exception {

    }

}


//    /**
//     * Добавления 2-х заявок в трекер и попытка поиска зявки по некорректной дате
//     */
////    @Test
//    public void addItemsAndTryToFindItemByIncorrectDate() {
//        Input testInput = new StubIN(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
//                , "Serg", "Adding Item again", "It is working again", "6", "Fake", "0"});
//        Output testOutput = new StubOUT();
//        Tracker tracker = new Tracker(1);
//        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
//        consoleMainMenu.start();
//
//    }
//    /**
//     * Выбор неккоректных значений сновного меню
//     */
//    @Test
//    public void incorrectMenuChoice() {
//        Input testInput = new StubIN(new String[]{"Ерунда", "11", "-1", "Опять ерудна", "33", "0"});
//        Tracker tracker = new Tracker(1);
//        Output testOutput = new StubOUT();
//        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
//        consoleMainMenu.start();
//        Assert.assertNull(tracker.getAllItems());
//    }
//
