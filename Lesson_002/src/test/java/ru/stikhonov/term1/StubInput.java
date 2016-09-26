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
    ConsoleMainMenu consoleMainMenu;
    ConsoleUserInput consoleUserInput;

    @Test
    public void someTest() {
        TestInput testInput = new TestInput(new String[]{"zmyak", "zmyak opyat", "eshe ras zmyak"});

    }
}
