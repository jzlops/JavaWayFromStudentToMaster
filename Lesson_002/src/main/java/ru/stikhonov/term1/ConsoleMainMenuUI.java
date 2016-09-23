package ru.stikhonov.term1;


/**
 * Класс отвечает за отрисовку консольгного UI
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenuUI {
    private Tracker tracker;

    void start(Tracker tracker) {
        this.tracker = tracker;
        int inputMenuNumber = 0;
        ConsoleUserInput consoleUserInput = new ConsoleUserInput(this.tracker);
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        do {
            if (inputMenuNumber == 1) consoleUserInput.menuAction(MenuElements.ADD);
            if (inputMenuNumber == 2) consoleUserInput.menuAction(MenuElements.EDIT);
            if (inputMenuNumber == 3) consoleUserInput.menuAction(MenuElements.DELETE);
            if (inputMenuNumber == 4) consoleUserInput.menuAction(MenuElements.SHOW);
            if (inputMenuNumber == 5) consoleUserInput.menuAction(MenuElements.SHOW_BY_ID);
            if (inputMenuNumber == 6) consoleUserInput.menuAction(MenuElements.SHOW_BY_FILTER);
            this.showMainMenu();
            inputMenuNumber = consoleInputHelper.intEntry();
        } while (inputMenuNumber != 0);
    }

    private void showMainMenu() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        consoleInputHelper.borderGenerator("*");
        System.out.printf("ДОБРО ПОЖАЛОВАТЬ В ТРЕКЕР ЗАЯВОК %n");
        consoleInputHelper.borderGenerator("#");
        System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
        System.out.printf("1. Создать заявку... %n");
        System.out.printf("2. Редактировать заявку... %n");
        System.out.printf("3. Удалить заявку... %n");
        System.out.printf("4. Отобразить все заявки... %n");
        System.out.printf("5. Найти заявку по номеру ID... %n");
        System.out.printf("6. Найти заявки по дате создания... %n");
        System.out.printf("0. Выйти из программы %n");
        consoleInputHelper.borderGenerator("#");    }

}
