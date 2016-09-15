package ru.stikhonov.term1;

/**
 * Основное тело программы
 *
 * @author Sergey Tikhonov
 */
public class StartUI {
    public static void main(String[] args) {

        // Создаем объект консольной менюшки
        ConsoleInput consoleInput = new ConsoleInput();
        // Создаем трэкер
        Tracker tracker = new Tracker();
        //  Отображаем меню
        consoleInput.showMainMenu();

        //ОСНОВНОЙ ЦИКЛ ПРОГРАММЫ

        while (consoleInput.getMainMenuAction() != MainMenuChoice.EXIT) {
            if (consoleInput.getMainMenuAction() == MainMenuChoice.ADD) {
                tracker.add();
            }
            if (consoleInput.getMainMenuAction() == MainMenuChoice.EDIT) {
                tracker.edit();
            }
            if (consoleInput.getMainMenuAction() == MainMenuChoice.DELETE) {
                tracker.delete();
            }
            if (consoleInput.getMainMenuAction() == MainMenuChoice.SHOW) {
                tracker.show();
            }
            if (consoleInput.getMainMenuAction() == MainMenuChoice.SHOWF) {
                tracker.showFiltered();
            }
            consoleInput.showMainMenu();
        }
    }
}

