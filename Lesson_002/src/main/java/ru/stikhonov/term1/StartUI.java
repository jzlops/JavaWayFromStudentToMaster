package ru.stikhonov.term1;

/**
 * Класс реализующий пользовательский интерфейс
 *
 * @author Sergey Tikhonov
 */
public class StartUI {
    public static void main(String[] args) {
        final int ITEMSCOUNT=10;
        MenuItem currentMainMenuItem;
        MenuItem currentSubMenuItem;
        Tracker tracker = new Tracker(ITEMSCOUNT);
        ConsoleInput consoleInput = new ConsoleInput(tracker);
        ConsoleUI consoleUI = new ConsoleUI();

        currentMainMenuItem = consoleUI.choiceMainMenuItem();

        while (currentMainMenuItem != MenuItem.EXIT) {
            if (currentMainMenuItem == MenuItem.ADD) {
                currentSubMenuItem = consoleUI.choiceSubMenuItem();
                if (currentSubMenuItem == MenuItem.BACK) {
                    currentMainMenuItem = consoleUI.choiceMainMenuItem();
                }
                if (currentSubMenuItem == MenuItem.DOIT) {
                    consoleInput.addItems();
                }
            }
            if (currentMainMenuItem == MenuItem.EDIT) {
                currentSubMenuItem = consoleUI.choiceSubMenuItem();
                if (currentSubMenuItem == MenuItem.BACK) {
                    currentMainMenuItem = consoleUI.choiceMainMenuItem();
                }
                if (currentSubMenuItem == MenuItem.DOIT) {
                    consoleInput.editItems();
                }
            }
            if (currentMainMenuItem == MenuItem.DELETE) {
                currentSubMenuItem = consoleUI.choiceSubMenuItem();
                if (currentSubMenuItem == MenuItem.BACK) {
                    currentMainMenuItem = consoleUI.choiceMainMenuItem();
                }
                if (currentSubMenuItem == MenuItem.DOIT) {
                    consoleInput.deleteItems();
                }
            }
            if (currentMainMenuItem == MenuItem.SHOW) {
                currentSubMenuItem = consoleUI.choiceSubMenuItem();
                if (currentSubMenuItem == MenuItem.BACK) {
                    currentMainMenuItem = consoleUI.choiceMainMenuItem();
                }
                if (currentSubMenuItem == MenuItem.DOIT) {
                    consoleInput.showItems();
                }
            }
            if (currentMainMenuItem == MenuItem.SHOWF) {
                currentSubMenuItem = consoleUI.choiceSubMenuItem();
                if (currentSubMenuItem == MenuItem.BACK) {
                    currentMainMenuItem = consoleUI.choiceMainMenuItem();
                }
                if (currentSubMenuItem == MenuItem.DOIT) {
                    consoleInput.showFItems();
                }
            }
        }
    }
}

