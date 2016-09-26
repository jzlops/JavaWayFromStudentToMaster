package ru.stikhonov.term1;


/**
 * Класс отвечает за оснвной цикл диалога консольногот приложания
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenu {
    private Tracker tracker;
    private Input consoleInputHelper;


    /**
     * Конструкор принимает в себя объект Tracker и объект реализующий интерфейс ввода Input
     *
     * @param tracker  объект класса Tracker
     * @param consoleInputHelper объект реализующий интерйес ввода @Input
     */
    ConsoleMainMenu(Tracker tracker, Input consoleInputHelper) {
        this.consoleInputHelper = consoleInputHelper;
        this.tracker = tracker;
    }

    /**
     * Основной и единственный метод запуска консольного UI и основного цикла диалога
     */
    void start() {
        int inputMenuNumber = 0;
        ConsoleGuiDrawer consoleGuiDrawer = new ConsoleGuiDrawer();
        ConsoleUserInput consoleUserInput = new ConsoleUserInput(this.tracker, this.consoleInputHelper);

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
