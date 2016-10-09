package ru.stikhonov.term4;

/**
 * Основной класс Трекера заявок
 *
 * @author Sergey Tikhonov
 */
public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker(2);
        Input cin = new ConsoleInput();
        Output cout = new ConsoleOutput();
        ConsoleMainMenu consoleMainMenu = new ConsoleMainMenu(tracker, cin, cout);
        consoleMainMenu.start();
    }
}

