package ru.stikhonov.term1;


/**
 * Класс отвечает за отрисовку консольгного UI
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenuUI {
    private Tracker tracker;
    private Input input;


    ConsoleMainMenuUI(Tracker tracker, Input input) {
        this.input = input;
        this.tracker = tracker;
    }

    void start() {
        int inputMenuNumber = 0;
        GuiDrawer guiDrawer = new GuiDrawer();
        ConsoleUserInput consoleUserInput = new ConsoleUserInput(this.tracker,this.input);

        do {
            if (inputMenuNumber == 1) consoleUserInput.menuAction(MenuElements.ADD);
            if (inputMenuNumber == 2) consoleUserInput.menuAction(MenuElements.EDIT);
            if (inputMenuNumber == 3) consoleUserInput.menuAction(MenuElements.DELETE);
            if (inputMenuNumber == 4) consoleUserInput.menuAction(MenuElements.SHOW);
            if (inputMenuNumber == 5) consoleUserInput.menuAction(MenuElements.SHOW_BY_ID);
            if (inputMenuNumber == 6) consoleUserInput.menuAction(MenuElements.SHOW_BY_FILTER);
            guiDrawer.showMainMenu();
            inputMenuNumber = this.input.intEntry();
        } while (inputMenuNumber != 0);
    }


}
