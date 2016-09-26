package ru.stikhonov.term1;

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
    public void addItemTest() {
        Input testInput = new TestInput(new String[]{"1", "Jack", "Adding Item", "It is working", "0",});
        Tracker tracker = new Tracker(1);
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, testInput);
        consoleMainMenu.start();
    }
}
