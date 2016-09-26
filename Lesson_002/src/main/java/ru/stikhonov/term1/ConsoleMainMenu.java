package ru.stikhonov.term1;


/**
 * Класс отвечает за отрисовку консольгного UI
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenu {
    private Tracker tracker;
    private Input consoleInputHelper;


    ConsoleMainMenu(Tracker tracker, Input consoleInputHelper) {
        this.consoleInputHelper = consoleInputHelper;
        this.tracker = tracker;
    }

    void start() {
        int inputMenuNumber = 0;
        ConsoleGuiDrawer consoleGuiDrawer = new ConsoleGuiDrawer();
        ConsoleUserInput consoleUserInput = new ConsoleUserInput(this.tracker,this.consoleInputHelper);

        do {
            if (inputMenuNumber == 1) consoleUserInput.menuAction(MenuElements.ADD);
            if (inputMenuNumber == 2) consoleUserInput.menuAction(MenuElements.EDIT);
            if (inputMenuNumber == 3) consoleUserInput.menuAction(MenuElements.DELETE);
            if (inputMenuNumber == 4) consoleUserInput.menuAction(MenuElements.SHOW);
            if (inputMenuNumber == 5) consoleUserInput.menuAction(MenuElements.SHOW_BY_ID);
            if (inputMenuNumber == 6) consoleUserInput.menuAction(MenuElements.SHOW_BY_FILTER);
            consoleGuiDrawer.showMainMenu();
            inputMenuNumber = this.consoleInputHelper.intEntry();
        } while (inputMenuNumber != 0);
    }


}
