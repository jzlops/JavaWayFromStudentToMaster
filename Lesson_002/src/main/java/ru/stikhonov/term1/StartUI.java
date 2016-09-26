package ru.stikhonov.term1;

/**
 * Основной класс Трекера заявок
 *
 * @author Sergey Tikhonov
 */
public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker(1);
        Input consoleInputHelper = new ConsoleInputHelper();
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, consoleInputHelper);
        consoleMainMenu.start();
    }
}

