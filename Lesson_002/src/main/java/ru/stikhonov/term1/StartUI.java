package ru.stikhonov.term1;

/**
 * Класс реализующий пользовательский интерфейс
 *
 * @author Sergey Tikhonov
 */
public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker(1);
        Input consoleInputHelper = new ConsoleInputHelper();
        ConsoleMainMenuUI consoleMainMenuUI = new ConsoleMainMenuUI(tracker, consoleInputHelper);
        consoleMainMenuUI.start();
    }
}

