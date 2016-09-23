package ru.stikhonov.term1;

/**
 * Класс реализующий пользовательский интерфейс
 *
 * @author Sergey Tikhonov
 */
public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker(1);
        ConsoleMainMenuUI consoleMainMenuUI = new ConsoleMainMenuUI();
        consoleMainMenuUI.start(tracker);
    }
}

