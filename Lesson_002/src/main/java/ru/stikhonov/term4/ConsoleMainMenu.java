package ru.stikhonov.term4;


/**
 * Класс отвечает за оснвной цикл диалога консольногот приложания
 *
 * @author Sergey Tikhonov
 */
class ConsoleMainMenu {
    private Tracker tracker;
    private Input cin;
    private Output cout;

    /**
     * Конструкор принимает в себя объект Tracker и объект реализующий интерфейс ввода Input
     *
     * @param tracker объект класса Tracker
     * @param cin  объект реализующий интерйес ввода Input
     * @param cout объект реализующий интерйес ввода Output
     */
    ConsoleMainMenu(Tracker tracker, Input cin, Output cout) {
        this.cin = cin;
        this.cout = cout;
        this.tracker = tracker;
    }

    /**
     * Основной и единственный метод запуска консольного UI и основного цикла диалога
     */
    void start() {
        int inputMenuNumber = 0;
        ConsoleMenuAction consoleMenuAction = new ConsoleMenuAction(this.tracker, this.cin, this.cout);
        consoleMenuAction.fillActions();
        do {
            if (inputMenuNumber == 1) consoleMenuAction.menuAction(1);
            if (inputMenuNumber == 2) consoleMenuAction.menuAction(2);
            if (inputMenuNumber == 3) consoleMenuAction.menuAction(3);
            if (inputMenuNumber == 4) consoleMenuAction.menuAction(4);
            if (inputMenuNumber == 5) consoleMenuAction.menuAction(5);
            if (inputMenuNumber == 6) consoleMenuAction.menuAction(6);
            consoleMenuAction.showMainMenu();
            inputMenuNumber = cin.intEntry();
        } while (inputMenuNumber != 0);
    }
}
