package ru.stikhonov.term1;

/**
 * @author Sergey Tikhonov
 */
public class ConsoleSubMenuUI {
    Tracker tracker;

    public ConsoleSubMenuUI(Tracker tracker) {
        this.tracker = tracker;
    }

    void choiceSubMenuItem(MenuItem menuItem) {
        ConsoleInput consoleInput = new ConsoleInput(this.tracker);
        int inputMenuNumber=0;
        ConsoleMenuInput inputCheck = new ConsoleMenuInput();
        do {
            if (inputMenuNumber == 1) System.out.println("Заглушка");
            this.showSubMenu(menuItem);
            inputMenuNumber = inputCheck.MenuChoice();
        } while (inputMenuNumber != 0);
    }
    private void showSubMenu(MenuItem menuItem) {
        if (menuItem.name() == "ADD") {
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ДОБАВЛЕНИЕ НОВОГО ЭЛЕМЕНТА %n");
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
            System.out.printf("1. Добавить элемент... %n");
            System.out.printf("0. Выйти восновное меню %n");
        }

        if (menuItem.name() == "EDIT") {
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("РЕДАКТИРОВАНИЕ ЗАЯКИ %n");
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
            System.out.printf("1. Редактировать заявку... %n");
            System.out.printf("0. Выйти восновное меню %n");
        }
        if (menuItem.name() == "DELETE") {
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("УДАЛЕНИЕ ЗАЯВКИ %n");
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
            System.out.printf("1. Удалить заявку... %n");
            System.out.printf("0. Выйти восновное меню %n");
        }
        if (menuItem.name() == "SHOW") {
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ОТОБРАЖЕНИЕ ВСЕХ ЗАЯВОК В БАЗЕ %n");
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
            System.out.printf("1. Отобразить заявки... %n");
            System.out.printf("0. Выйти восновное меню %n");
        }
        if (menuItem.name() == "SHOW_BY_ID") {
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ПОИСК ЗАЯВКИ ПО ID %n");
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
            System.out.printf("1. Найти заявку... %n");
            System.out.printf("0. Выйти восновное меню %n");
        }
        if (menuItem.name() == "SHOW_BY_FILTER") {
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ПОИСК ЗАЯВОК ПО ДАТЕ %n");
            for (int i = 0; i < 50; i++) {
                System.out.printf("+");
            }
            System.out.printf("%n");
            System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
            System.out.printf("1. Найти заявки за указанный период... %n");
            System.out.printf("0. Выйти восновное меню %n");
        }

    }
}



