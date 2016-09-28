package ru.stikhonov.term1;


/**
 * Класс отвечает за оснвной цикл диалога консольногот приложания
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenu {
    private Tracker tracker;
    private Input consoleInputHelper;
    private Output consoleOutputHelper;

    /**
     * Конструкор принимает в себя объект Tracker и объект реализующий интерфейс ввода Input
     *
     * @param tracker             объект класса Tracker
     * @param consoleInputHelper  объект реализующий интерйес ввода Input
     * @param consoleOutputHelper объект реализующий интерйес ввода Output
     */
    ConsoleMainMenu(Tracker tracker, Input consoleInputHelper, Output consoleOutputHelper) {
        this.consoleInputHelper = consoleInputHelper;
        this.consoleOutputHelper = consoleOutputHelper;
        this.tracker = tracker;
    }

    /**
     * Основной и единственный метод запуска консольного UI и основного цикла диалога
     */
    void start() {
        int inputMenuNumber = 0;
        ConsoleGuiDrawer consoleGuiDrawer = new ConsoleGuiDrawer(this.consoleOutputHelper);
        ConsoleUserInput consoleUserInput = new ConsoleUserInput(this.tracker, this.consoleInputHelper, this.consoleOutputHelper);

        do {
            if (inputMenuNumber == 1) consoleUserInput.menuAction(MenuElements.ADD);
            if (inputMenuNumber == 2) consoleUserInput.menuAction(MenuElements.EDIT);
            if (inputMenuNumber == 3) consoleUserInput.menuAction(MenuElements.DELETE);
            if (inputMenuNumber == 4) consoleUserInput.menuAction(MenuElements.SHOW);
            if (inputMenuNumber == 5) consoleUserInput.menuAction(MenuElements.SHOW_BY_ID);
            if (inputMenuNumber == 6) consoleUserInput.menuAction(MenuElements.SHOW_BY_FILTER);
            consoleGuiDrawer.showMainMenu();
            inputMenuNumber = consoleInputHelper.intEntry();
        } while (inputMenuNumber != 0);
    }
}
