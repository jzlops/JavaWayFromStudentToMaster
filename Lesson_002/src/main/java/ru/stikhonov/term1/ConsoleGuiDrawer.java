package ru.stikhonov.term1;

/**
 * Вспомогательный класс для отрисовки основного меню и границ
 *
 * @author Sergey Tikhonov
 */
class ConsoleGuiDrawer {
    private Output cout;

    ConsoleGuiDrawer(Output output) {
        this.cout = output;
    }

    /**
     * Метод рисует основное меню программы
     */
    void showMainMenu() {
        borderGenerator("*");
        cout.out("ДОБРО ПОЖАЛОВАТЬ В ТРЕКЕР ЗАЯВОК \n");
        borderGenerator("#");
        cout.out("ВЫБЕРИТЕ ПУНКТ МЕНЮ: \n");
        cout.out("1. Создать заявку...\n");
        cout.out("2. Редактировать заявку...\n");
        cout.out("3. Удалить заявку...\n");
        cout.out("4. Отобразить все заявки...\n");
        cout.out("5. Найти заявку по номеру ID...\n");
        cout.out("6. Найти заявки по дате создания...\n");
        cout.out("0. Выйти из программы\n");
        borderGenerator("#");
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
