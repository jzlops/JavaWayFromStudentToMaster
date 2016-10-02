package ru.stikhonov.term1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
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
    public void addItemsAndTryToFindItemByWrongDateRange() throws ParseException {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "6", "1111.11.11 11:11:11", "1212.11.11 11:11:11", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.getItemsByDataRange(simpleDateFormat.parse("1111.11.11 11:11:11"), simpleDateFormat.parse("1212.11.11 11:11:11")), null);
    }

    /**
     * Добавления 2-х заявок в трекер и попытка поиска зявки по некорректной дате ID
     */
    @Test
    public void addItemsAndTryToFindItemByIncorrectDate() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "6", "Fake", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер и попытка поиска зявки по несущестующему ID
     */
    @Test
    public void addItemsAndTryToFindItemByWrongID() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "5", "Fake", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.getItemByID("fake"),null);
    }

    /**
     * Добавления 2-х заявок в трекер и попытка удаления зявки по несущестующему ID
     */
    @Test
    public void addItemsAndTryToDeleteItemByWrongID() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "3", "Fake", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.deleteItem("Fake"),false);
    }

    /**
     * Добавления 2-х заявок в трекер и попытка редактирования зявки по несущестующему ID
     */
    @Test
    public void addItemsAndTryToEditItemByWrongID() {
        Item item;
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "2", "Fake", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        item=new Item("Fake","Fake",new Date(),"fake");
        Assert.assertFalse(tracker.editItem(item));
    }

    /**
     * Выбор неккоректных значений сновного меню
     */
    @Test
    public void incorrectMenuChoice() {
        Input testInput = new TestInput(new String[]{"Ерунда", "11", "-1", "Опять ерудна", "33", "0"});
        Tracker tracker = new Tracker(1);
        Output testOutput = new TestOutput();
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
    }

    /**
     * Выбор пунктов меню, если трекер пустой
     */

    @Test
    public void menuChoiceWhenTrackerIsEmpty() {
        Input testInput = new TestInput(new String[]{"2", "3", "4", "5", "6", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
    }

    /**
     * Добавления 2-х заявок в трекер
     */
    @Test
    public void addItems() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.getItemsCount(),2);
    }

    /**
     * Добавление 2-х заявок в трекер и вывод результата на экран
     */
    @Test
    public void showItems() {
        Input testInput = new TestInput(new String[]{"1", "Sister", "Adding First Item", "It is working", "1"
                , "Brother", "Adding Second Item again", "It is working again", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.getItemsCount(),2);
    }

    /**
     * Удалаяем заявку из трекера по ее ID, заявку в трекер предварительно заводим в ручную, дабы не генерировать случайный ID
     */
    @Test
    public void deleteItem() {
        Input testInput = new TestInput(new String[]{"3", "RQS888888", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        Item item = new Item("Lena", "Deleting Item", new Date(), "no comment");
        tracker.addItem(item);
        item.setItemID("RQS888888");
        Assert.assertEquals(tracker.getItemsCount(),1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.getItemsCount(),0);

    }

    /**
     * Редактируем заявку пользователем по заданному ID (заявку в трекер заносим в ручную предварительно)
     */
    @Test
    public void editItem() {
        Input testInput = new TestInput(new String[]{"2", "RQS111111", "Mr Serg", "New Edit Entry", "no comment again", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        Item item = new Item("Serg", "Editing Item", new Date(), "no comment");
        tracker.addItem(item);
        item.setItemID("RQS111111");
        Assert.assertEquals(tracker.getItemByID("RQS111111").getComments(),"no comment");
        Assert.assertEquals(tracker.getItemByID("RQS111111").getDescription(),"Editing Item");
        Assert.assertEquals(tracker.getItemByID("RQS111111").getUserName(),"Serg");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        Assert.assertEquals(tracker.getItemByID("RQS111111").getComments(),"no comment again");
        Assert.assertEquals(tracker.getItemByID("RQS111111").getDescription(),"New Edit Entry");
        Assert.assertEquals(tracker.getItemByID("RQS111111").getUserName(),"Mr Serg");
    }

    /**
     * Выводим на экран заявку по ее ID, предварительно ее создав
     */
    @Test
    public void showItemByID() {
        Input testInput = new TestInput(new String[]{"5", "RQS123123", "0"});
        Output testOutput = new TestOutput();
        Tracker tracker = new Tracker(1);
        Item item = new Item("Zora", "Showing Item By ID", new Date(), "Ololo");
        tracker.addItem(item);
        item.setItemID("RQS123123");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
    }

    /**
     * Предварительно создаем 2 заявки с разными датами, далее выводи список заявок из диапазона дат, по факту выведится только одна заявка
     * удовлетворяющая условию поиска
     */
    @Test
    public void showItemByDataRange() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item[] item;
        Input testInput = new TestInput(new String[]{"6", "2222.22.22 22:22:21", "2222.22.22 22:22:23", "0"});
        Output testOutput = new TestOutput();
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
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput, testOutput);
        consoleMainMenu.start();
        item=tracker.getItemsByDataRange(simpleDateFormat.parse("2222.22.22 22:22:21"), simpleDateFormat.parse("2222.22.22 22:22:23"));
        Assert.assertEquals(item[0].getDescription(),"ShowByData");
        Assert.assertEquals(item[0].getUserName(),"Lena1");
        Assert.assertEquals(item[0].getComments(),"Data1");

    }

}
