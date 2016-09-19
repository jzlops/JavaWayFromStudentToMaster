package ru.stikhonov.term1;


/**
 * Класс отвечает за отрисовку консольгного UI
 *
 * @author Sergey Tikhonov
 */
class ConsoleUI {


    MenuItem choiceMainMenuItem() {
        this.showMainMenu();
//      Ожидаем ввобда пользователя в консоли
        if (true) return MenuItem.ADD;
        if (true) return MenuItem.EDIT;
        if (true) return MenuItem.DELETE;
        if (true) return MenuItem.SHOW;
        if (true) return MenuItem.SHOWF;
        return MenuItem.EXIT;
    }

    MenuItem choiceSubMenuItem() {
        this.showSubMenu();
//      Ожидаем ввобда пользователя в консоли
        if (true) {
            return MenuItem.DOIT;
        } else {
            return MenuItem.BACK;
        }
    }


    private void showMainMenu() {
        // отображаем осносное меню
    }

    private void showSubMenu() {
        // отображаем подменю меню
    }


}
