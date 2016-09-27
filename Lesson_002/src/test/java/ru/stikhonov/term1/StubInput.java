package ru.stikhonov.term1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sergey Tikhonov
 */

public class StubInput {

    @Test
    public void testingAddItems() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "1"
                , "Serg", "Adding Item again", "It is working again", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();

    }

    @Test
    public void testingShowItems() {
        Input testInput = new TestInput(new String[]{"1", "Sister", "Adding First Item", "It is working", "1"
                , "Brother", "Adding Second Item again", "It is working again", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    @Test
    public void testingDeleteItem() {
        Input testInput = new TestInput(new String[]{"3", "RQS888888", "0"});
        Tracker tracker = new Tracker(1);
        Item item = new Item("Lena", "Deleting Item", new Date(), "no comment");
        tracker.addItem(item);
        item.setItemID("RQS888888");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    @Test
    public void testingEditItem() {
        Input testInput = new TestInput(new String[]{"2", "RQS111111", "Mr Serg", "New Edit Entry", "no comment again", "0"});
        Tracker tracker = new Tracker(1);
        Item item = new Item("Serg", "Editing Item", new Date(), "no comment");
        tracker.addItem(item);
        item.setItemID("RQS111111");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    @Test
    public void testingShowItemByID() {
        Input testInput = new TestInput(new String[]{"5", "RQS123123", "0"});
        Tracker tracker = new Tracker(1);
        Item item = new Item("Zora", "Showing Item By ID", new Date(), "Ololo");
        tracker.addItem(item);
        item.setItemID("RQS123123");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    @Test
    public void testingShowItemByDataRange() {
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
