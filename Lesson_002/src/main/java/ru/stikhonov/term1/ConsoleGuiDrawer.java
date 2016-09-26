package ru.stikhonov.term1;

/**
 * @author Sergey Tikhonov
 */
class ConsoleGuiDrawer {

     void showMainMenu() {
        borderGenerator("*");
        System.out.printf("ДОБРО ПОЖАЛОВАТЬ В ТРЕКЕР ЗАЯВОК %n");
        borderGenerator("#");
        System.out.printf("ВЫБЕРИТЕ ПУНКТ МЕНЮ: %n");
        System.out.printf("1. Создать заявку... %n");
        System.out.printf("2. Редактировать заявку... %n");
        System.out.printf("3. Удалить заявку... %n");
        System.out.printf("4. Отобразить все заявки... %n");
        System.out.printf("5. Найти заявку по номеру ID... %n");
        System.out.printf("6. Найти заявки по дате создания... %n");
        System.out.printf("0. Выйти из программы %n");
        borderGenerator("#");
    }

    void borderGenerator(String s) {
        for (int i = 0; i < 50; i++) {
            System.out.printf(s);
        }
        System.out.printf("%n");
    }



}
