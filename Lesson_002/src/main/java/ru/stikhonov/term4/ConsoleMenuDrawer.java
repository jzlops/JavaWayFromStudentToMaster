package ru.stikhonov.term4;

/**
 * Вспомогательный класс для отрисовки основного меню и границ
 *
 * @author Sergey Tikhonov
 */

class ConsoleMenuDrawer {
    private Output cout;

    ConsoleMenuDrawer(Output cout) {
        this.cout = cout;
    }

    /**
     * Метод чертит границы
     *
     * @param s любой симовол которым будет рисоваться границы меню
     */
    void borderGenerator(String s) {
        for (int i = 0; i < 50; i++) {
            cout.out(s);
        }
        cout.out("\n");
    }
}
