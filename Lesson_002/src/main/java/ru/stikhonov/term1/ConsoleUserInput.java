package ru.stikhonov.term1;

import java.util.Date;

/**
 * Класс реализующий ввод данных польователя с консоли, а так же отбражение вызодных данных, запрашиваемых у трекера
 *
 * @author Sergey Tikhonov
 */
class ConsoleUserInput {
    private Tracker tracker;
    private Input consoleInputHelper;
    private ConsoleGuiDrawer consoleGuiDrawer = new ConsoleGuiDrawer();

    /**
     * Конструктор принимате на вход 2 параметра
     *
     * @param tracker объект типа Tracker
     * @param input   объет реализующий интерфейс Input
     */
    ConsoleUserInput(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.consoleInputHelper = input;
    }

    /**
     * Метод анализирующий выбор меню пользователем и запускающий тот или иной метод (логичесикй блок):
     * Создать заявку
     * Удалить заявку
     * Редактировать заявку
     * Показать все заявки в трекере
     * Показать заявку по ее ID
     * Показать заявки в промежутке дат
     *
     * @param menuElements принимает в качество входного параметра элемент множетва MenuElements
     */
    void menuAction(MenuElements menuElements) {

        if (menuElements.name().equals("ADD")) {
            addMenuAction();
        }
        if (menuElements.name().equals("EDIT")) {
            editMenuAction();
        }
        if (menuElements.name().equals("DELETE")) {
            deleteMenuAction();
        }
        if (menuElements.name().equals("SHOW")) {
            showMenuAction();
        }
        if (menuElements.name().equals("SHOW_BY_ID")) {
            showItemByIDAction();
        }
        if (menuElements.name().equals("SHOW_BY_FILTER")) {
            showItemByFilterAction();
        }
    }


    /**
     * Метод отображает на экран (в консоль) набор элементов типа Item из трекера в промежутке требуемых дат
     */
    private void showItemByFilterAction() {
        Date beginDate, endDate;

        if (!itemCountChecker(this.tracker.getItemsCount())) return;

        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("СПИСОК ЗАЯВОК ОТСОРТИРОВАННЫЙ ПО ДАТАМ %n");
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Введите начальное время в формате yyyy.MM.dd HH:mm:ss %n");
        beginDate = consoleInputHelper.dateEntry();
        if (beginDate == null) {
            System.out.printf("Для продолжения - нажмите Enter %n");
            consoleInputHelper.anyKeyEntry();
            return;
        }
        System.out.printf("Введите конечное время в формате yyyy.MM.dd HH:mm:ss %n");
        endDate = consoleInputHelper.dateEntry();
        if (endDate == null) {
            System.out.printf("Для продолжения - нажмите Enter %n");
            consoleInputHelper.anyKeyEntry();
            return;
        }
        consoleGuiDrawer.borderGenerator("+");
        if (this.tracker.getItemsByDataRange(beginDate, endDate) != null) {
            System.out.printf("СПИСОК ЗАЯВОК ЗА УКАЗАННЫЙ ПЕРИОД ЗАЯВОК %n");
            for (Item item : this.tracker.getItemsByDataRange(beginDate, endDate)) {
                System.out.printf("ID заявки: %1$s %n", item.getItemID());
                System.out.printf("Имя составителя:  %1$s %n", item.getUserName());
                System.out.printf("Описание:  %1$s %n", item.getDescription());
                System.out.printf("Комментарий:  %1$s %n", item.getComments());
                System.out.printf("Дата  последнего изменения: %1$s %n", item.getDate().toString());
                consoleGuiDrawer.borderGenerator("+");
            }
        } else {
            System.out.printf("Заявок с указанными критериями не найдено %n");
        }
        System.out.printf("Для продолжения - нажмите Enter %n");
        consoleInputHelper.anyKeyEntry();
    }

    /**
     * Метод отображает на экран элемент типа Item из трекера, в зависимости от выбраннеого ID
     */
    private void showItemByIDAction() {
        Item item;
        String itemID;

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("ЗАЯВКА %n");
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Введите номер заявки: %n");
        itemID = consoleInputHelper.stringEntry();

        if (!this.tracker.itemExistence(itemID)) {
            System.out.printf("Заявка с номером %1$s ненайдена %n", itemID);
            System.out.printf("Для продолжения - нажмите Enter %n");
            consoleInputHelper.anyKeyEntry();
            return;
        }
        item = this.tracker.getItemByID(itemID);
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("ID заявки: %1$s %n", item.getItemID());
        System.out.printf("Имя составителя:  %1$s %n", item.getUserName());
        System.out.printf("Описание:  %1$s %n", item.getDescription());
        System.out.printf("Комментарий:  %1$s %n", item.getComments());
        System.out.printf("Дата создания:  %1$s %n", item.getDate().toString());
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Для продолжения - нажмите Enter %n");
        consoleInputHelper.anyKeyEntry();
    }

    /**
     * Метод отображает список всех элементов Item в трекере
     */
    private void showMenuAction() {

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("СПИСОК ВСЕХ ЗАЯВОК %n");
        consoleGuiDrawer.borderGenerator("+");
        for (Item item : tracker.getAllItems()) {
            System.out.printf("ID заявки: %1$s %n", item.getItemID());
            System.out.printf("Имя составителя:  %1$s %n", item.getUserName());
            System.out.printf("Описание:  %1$s %n", item.getDescription());
            System.out.printf("Комментарий:  %1$s %n", item.getComments());
            System.out.printf("Дата последнего изменения:  %1$s %n", item.getDate().toString());
            consoleGuiDrawer.borderGenerator("+");
        }
        System.out.printf("Для продолжения - нажмите Enter %n");
        consoleInputHelper.anyKeyEntry();
    }

    /**
     * Удалить заявку Item  из трекера
     */
    private void deleteMenuAction() {

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        String itemID;
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("УДАЛЕНИЕ ЗАЯВКИ %n");
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Введите номер заявки: %n");
        itemID = consoleInputHelper.stringEntry();
        if (tracker.deleteItem(itemID)) {
            System.out.printf("Заявка с номером %1$s удалена %n", itemID);
        } else {
            System.out.printf("Заявка с номером %1$s не найдена %n", itemID);
        }
        System.out.printf("Для продолжения - нажмите Enter %n");
        consoleInputHelper.anyKeyEntry();
    }

    /**
     * Редактировать заявку Item в трекере
     */
    private void editMenuAction() {

        String username, description, comment, itemID;
        Item item;

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("РЕДАКТИРОВАНИЕ ЗАЯВКИ %n");
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Введите номер заявки: %n");
        itemID = consoleInputHelper.stringEntry();
        if (!this.tracker.itemExistence(itemID)) {
            System.out.printf("Заявка с номером %1$s ненайдена %n", itemID);
            System.out.printf("Для продолжения -  нажмите Enter %n");
            consoleInputHelper.anyKeyEntry();
            return;
        }
        System.out.printf("Введите ваше имя: %n");
        username = consoleInputHelper.stringEntry();

        System.out.printf("Введите новое описание заявки:%n");
        description = consoleInputHelper.stringEntry();

        System.out.printf("Введите новый комментарий:%n");
        comment = consoleInputHelper.stringEntry();
        item = new Item(username, description, new Date(), comment);
        item.setItemID(itemID);
        this.tracker.editItem(item);
        consoleGuiDrawer.borderGenerator("-");
        System.out.printf("Заявка с номером %1$s отредактирована %n", itemID);
        System.out.printf("Для продолжения - нажмите Enter %n");
        consoleInputHelper.anyKeyEntry();
    }

    /**
     * Добавить заявку Item в трекер
     */
    private void addMenuAction() {

        String username, description, comment;
        Item item;
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("ДОБАВЛЕНИЕ НОВОЙ ЗАЯВКИ %n");
        consoleGuiDrawer.borderGenerator("+");

        System.out.printf("Введите ваше имя:%n");
        username = consoleInputHelper.stringEntry();

        System.out.printf("Введите описание заявки:%n");
        description = consoleInputHelper.stringEntry();

        System.out.printf("Введите комментарий:%n");
        comment = consoleInputHelper.stringEntry();
        item = new Item(username, description, new Date(), comment);
        this.tracker.addItem(item);
        consoleGuiDrawer.borderGenerator("-");
        System.out.printf("Заявка создана %n");
        System.out.printf("Номер заявки %1$s %n", item.getItemID());
        System.out.printf("Для продолжения - нажмите Enter %n");
        consoleInputHelper.anyKeyEntry();
    }

    /**
     * Вспомогатльеный метод реагирующий на наличие заявок в трекере
     *
     * @param itemCount количество заявок в трекере
     * @return true если трекер не пуст, иначе false
     */
    private boolean itemCountChecker(int itemCount) {
        if (itemCount > 0) {
            return true;
        } else {
            consoleGuiDrawer.borderGenerator("!");
            System.out.printf("В ТРЕКЕРЕ НЕТ ЗАЯВОК %n");
            consoleGuiDrawer.borderGenerator("!");
            System.out.printf("Для продолжения - нажмите Enter %n");
            this.consoleInputHelper.anyKeyEntry();
            return false;
        }
    }
}



