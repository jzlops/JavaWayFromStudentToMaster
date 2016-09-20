package ru.stikhonov.term1;


/**
 * Класс отвечает за отрисовку консольгного UI
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenuUI {
    Tracker tracker;
    public ConsoleMainMenuUI(Tracker tracker) {
        this.tracker = tracker;
    }
    void choiceMainMenuItem() {
        int inputMenuNumber=-1;
        this.showMainMenu();
        ConsoleSubMenuUI consoleSubMenuUI = new ConsoleSubMenuUI(this.tracker);
        ConsoleInputChecker inputCheck = new ConsoleInputChecker();
        do {
            if (inputMenuNumber == 1) consoleSubMenuUI.choiceSubMenuItem(MenuItem.ADD);
            if (inputMenuNumber == 2) consoleSubMenuUI.choiceSubMenuItem(MenuItem.EDIT);
            if (inputMenuNumber == 3) consoleSubMenuUI.choiceSubMenuItem(MenuItem.DELETE);
            if (inputMenuNumber == 4) consoleSubMenuUI.choiceSubMenuItem(MenuItem.SHOW);
            if (inputMenuNumber == 5) consoleSubMenuUI.choiceSubMenuItem(MenuItem.SHOW_BY_ID);
            if (inputMenuNumber == 6) consoleSubMenuUI.choiceSubMenuItem(MenuItem.SHOW_BY_FILTER);
            this.showMainMenu();
            inputMenuNumber = inputCheck.MenuChoice();
        } while (inputMenuNumber != 0);
    }

    private void showMainMenu() {
        for (int i = 0; i < 50; i++) {
            System.out.printf("*");
        }
        System.out.printf("%n");
        System.out.printf("ДОБРО ПОЖАЛОВАТЬ В ТРЕКЕР ЗАЯВОК %n");
        for (int i = 0; i < 50; i++) {
            System.out.printf("#");
        }
        System.out.printf("%n");
        System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
        System.out.printf("1. Создать заявку... %n");
        System.out.printf("2. Редактировать заявку... %n");
        System.out.printf("3. Удалить заявку... %n");
        System.out.printf("4. Отобразить все заявки... %n");
        System.out.printf("5. Найти заявку по номеру ID... %n");
        System.out.printf("6. Найти заявки по дате создания ID... %n");
        System.out.printf("0. Выйти из программы %n");
        for (int i = 0; i < 50; i++) {
            System.out.printf("#");
        }
        System.out.printf("%n");
    }

}
