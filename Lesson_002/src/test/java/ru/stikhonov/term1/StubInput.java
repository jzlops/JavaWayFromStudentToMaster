package ru.stikhonov.term1;

import org.junit.Before;
import ru.stikhonov.term1.Tracker;

import java.util.Date;
import java.util.logging.StreamHandler;

import org.junit.Test;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.spy;

/**
 * @author Sergey Tikhonov
 */

public class StubInput {

    @Test
    public void testingAddItems() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working","1"
                , "Serg", "Adding Item again", "It is working again", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    @Test
    public void testingAddItemsAndShowItems() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working","1"
                , "Serg", "Adding Item again", "It is working again", "0"});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

    @Test
    public void testingDeleteItem() {
        Input testInput = new TestInput(new String[]{"3", "RQS888888", "0"});
        Tracker tracker = new Tracker(1);
        Item item = new Item("Lena", "Deleting Item", new Date(), "Ololo");
        tracker.addItem(item);
        item.setItemID("RQS888888");
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }

}
