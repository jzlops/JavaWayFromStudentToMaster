package ru.stikhonov.term1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * Клсаа содержит набор тестов, для проверки консольного UI
 *
 * @author Sergey Tikhonov
 */

public class StubInput {

    /**
     * Добавления 2-х заявок в трекер и попытка поиска зявки по несущестующей дате ID
     */
    @Test
    public void addItemsAndTryToFindItemByWrongDateRange() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "6", "1111.11.11 11:11:11", "1212.11.11 11:11:11", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер и попытка поиска зявки по некорректной дате ID
     */
    @Test
    public void addItemsAndTryToFindItemByIncorrectDate() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "6", "Fake", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер и попытка поиска зявки по несущестующему ID
     */
    @Test
    public void addItemsAndTryToFindItemByWrongID() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "5", "Fake", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер и попытка удаления зявки по несущестующему ID
     */
    @Test
    public void addItemsAndTryToDeleteItemByWrongID() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "3", "Fake", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер и попытка редактирования зявки по несущестующему ID
     */
    @Test
    public void addItemsAndTryToEditItemByWrongID() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "2", "Fake", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Выбор неккоректных значений сновного меню
     */
    @Test
    public void incorrectMenuChoice() {
        Input testInput = new TestInput(new String[]{"Ерунда", "11", "-1", "Опять ерудна", "33", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Выбор пунктов меню, если трекер пустой
     */

    @Test
    public void menuChoiceWhenTrackerIsEmpty() {
        Input testInput = new TestInput(new String[]{"2", "3", "4", "5", "6", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер
     */
    @Test
    public void addItems() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();

    }

    /**
     * Добавление 2-х заявок в трекер и вывод результата на экран
     */
    @Test
    public void showItems() {
        Input testInput = new TestInput(new String[]{"1", "Sister", "Adding First Item", "It is working", "1"
                , "Brother", "Adding Second Item again", "It is working again", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Удалаяем заявку из трекера по ее ID, заявку в трекер предварительно заводим в ручную, дабы не генерировать случайный ID
     */
    @Test
    public void deleteItem() {
        Input testInput = new TestInput(new String[]{"3", "RQS888888", "0"});
        Tracker tracker = new Tracker(1);
        Item item = new Item("Lena", "Deleting Item", new Date(), "no comment");
        tracker.addItem(item);
        item.setItemID("RQS888888");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Редактируем заявку пользователем по заданному ID (заявку в трекер заносим в ручную предварительно)
     */
    @Test
    public void editItem() {
        Input testInput = new TestInput(new String[]{"2", "RQS111111", "Mr Serg", "New Edit Entry", "no comment again", "0"});
        Tracker tracker = new Tracker(1);
        Item item = new Item("Serg", "Editing Item", new Date(), "no comment");
        tracker.addItem(item);
        item.setItemID("RQS111111");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Выводим на экран заявку по ее ID, предварительно ее создав
     */
    @Test
    public void showItemByID() {
        Input testInput = new TestInput(new String[]{"5", "RQS123123", "0"});
        Tracker tracker = new Tracker(1);
        tracker.addItem(new Item("Zora", "Showing Item By ID", new Date(), "Ololo")).setItemID("RQS123123");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    /**
     * Предварительно создаем 2 заявки с разными датами, далее выводи список заявок из диапазона дат, по факту выведится только одна заявка
     * удовлетворяющая условию поиска
     */
    @Test
    public void showItemByDataRange() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        Input testInput = new TestInput(new String[]{"6", "2222.22.22 22:22:21", "2222.22.22 22:22:23", "0"});
        Tracker tracker = new Tracker(1);
        Item item1 = null;
        try {
            item1 = new Item("Lena1", "ShowByData", simpleDateFormat.parse("2222.22.22 22:22:22"), "Data1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tracker.addItem(item1);
        Item item2 = null;
        try {
            item2 = new Item("Lena2", "ShowByData", simpleDateFormat.parse("2222.22.22 22:22:24"), "Data2");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tracker.addItem(item2);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

}
