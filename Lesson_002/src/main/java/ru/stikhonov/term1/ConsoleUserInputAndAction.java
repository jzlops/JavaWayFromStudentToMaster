package ru.stikhonov.term1;

import java.util.Date;

/**
 * Класс реализующий ввод данных польователя с консоли, а так же отбражение вызодных данных, запрашиваемых у трекера
 *
 * @author Sergey Tikhonov
 */
class ConsoleUserInputAndAction {
    private Tracker tracker;
    private Input cin;
    private Output cout;
    private ConsoleGuiDrawer consoleGuiDrawer;

    /**
     * Конструктор принимате на вход 2 параметра
     *
     * @param tracker объект типа Tracker
     * @param consoleInputHelper   объет реализующий интерфейс Input
     */
    ConsoleUserInputAndAction(Tracker tracker, Input consoleInputHelper, Output consoleOutputHelper) {
        this.tracker = tracker;
        this.cin = consoleInputHelper;
        this.cout = consoleOutputHelper;
        this.consoleGuiDrawer = new ConsoleGuiDrawer(this.cout);
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
        cout.out("СПИСОК ЗАЯВОК ОТСОРТИРОВАННЫЙ ПО ДАТАМ \n");
        consoleGuiDrawer.borderGenerator("+");
        cout.out("Введите начальное время в формате yyyy.MM.dd HH:mm:ss \n");
        beginDate = cin.dateEntry();
        if (beginDate == null) {
            cout.out("Неверный формат данных \n");
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
            return;
        }
        cout.out("Введите конечное время в формате yyyy.MM.dd HH:mm:ss \n");
        endDate = cin.dateEntry();
        if (endDate == null) {
            cout.out("Неверный формат данных \n");
            cout.out("Для продолжения - нажмите Enter  \n");
            cin.anyKeyEntry();
            return;
        }
        consoleGuiDrawer.borderGenerator("+");
        if (this.tracker.getItemsByDataRange(beginDate, endDate) != null) {
            cout.out("СПИСОК ЗАЯВОК ЗА УКАЗАННЫЙ ПЕРИОД ЗАЯВОК \n");
            for (Item item : this.tracker.getItemsByDataRange(beginDate, endDate)) {
                cout.out("ID заявки: " + item.getItemID()+"\n");
                cout.out("Имя составителя: " + item.getUserName()+"\n");
                cout.out("Описание: " + item.getDescription()+"\n");
                cout.out("Комментарий: " + item.getComments()+"\n");
                cout.out("Дата последнего изменения: " + item.getDate().toString()+"\n");
                consoleGuiDrawer.borderGenerator("+");
            }
        } else {
            cout.out("Заявок с указанными критериями не найдено \n");
        }
        cout.out("Для продолжения - нажмите Enter \n");
        cin.anyKeyEntry();
    }

    /**
     * Метод отображает на экран элемент типа Item из трекера, в зависимости от выбраннеого ID
     */
    private void showItemByIDAction() {
        Item item;
        String itemID;

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        cout.out("ЗАЯВКА \n");
        consoleGuiDrawer.borderGenerator("+");
        cout.out("Введите номер заявки: \n");
        itemID = cin.stringEntry();

        if (!this.tracker.itemExistence(itemID)) {
            cout.out("Заявка с номером " + itemID +  " ненайдена \n");
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
            return;
        }
        item = this.tracker.getItemByID(itemID);
        consoleGuiDrawer.borderGenerator("+");
        cout.out("ID заявки: " + item.getItemID()+"\n");
        cout.out("Имя составителя: " + item.getUserName()+"\n");
        cout.out("Описание: " + item.getDescription()+"\n");
        cout.out("Комментарий: " + item.getComments()+"\n");
        cout.out("Дата последнего изменения: " + item.getDate().toString()+"\n");
        consoleGuiDrawer.borderGenerator("+");
        cout.out("Для продолжения - нажмите Enter \n");
        cin.anyKeyEntry();
    }

    /**
     * Метод отображает список всех элементов Item в трекере
     */
    private void showMenuAction() {

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        cout.out("СПИСОК ВСЕХ ЗАЯВОК \n");
        consoleGuiDrawer.borderGenerator("+");
        for (Item item : tracker.getAllItems()) {
            cout.out("ID заявки: " + item.getItemID()+"\n");
            cout.out("Имя составителя: " + item.getUserName()+"\n");
            cout.out("Описание: " + item.getDescription()+"\n");
            cout.out("Комментарий: " + item.getComments()+"\n");
            cout.out("Дата  последнего изменения: " + item.getDate().toString()+"\n");
            consoleGuiDrawer.borderGenerator("+");
        }
        cout.out("Для продолжения - нажмите Enter \n");
        cin.anyKeyEntry();
    }

    /**
     * Удалить заявку Item  из трекера
     */
    private void deleteMenuAction() {

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        String itemID;
        consoleGuiDrawer.borderGenerator("+");
        cout.out("УДАЛЕНИЕ ЗАЯВКИ \n");
        consoleGuiDrawer.borderGenerator("+");
        cout.out("Введите номер заявки: \n");
        itemID = cin.stringEntry();
        if (tracker.deleteItem(itemID)) {
            cout.out("Заявка с номером " + itemID + " удалена \n");
        } else {
            cout.out("Заявка с номером " + itemID + " ненайдена \n");
        }
        cout.out("Для продолжения - нажмите Enter \n");
        cin.anyKeyEntry();
    }

    /**
     * Редактировать заявку Item в трекере
     */
    private void editMenuAction() {

        String username, description, comment, itemID;
        Item item;

        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        cout.out("РЕДАКТИРОВАНИЕ ЗАЯВКИ \n");
        consoleGuiDrawer.borderGenerator("+");
        cout.out("Введите номер заявки: \n");
        itemID = cin.stringEntry();
        if (!this.tracker.itemExistence(itemID)) {
            cout.out("Заявка с номером " + itemID + " ненайдена \n");
            cout.out("Для продолжения -  нажмите Enter \n");
            cin.anyKeyEntry();
            return;
        }
        cout.out("Введите ваше имя: \n");
        username = cin.stringEntry();

        cout.out("Введите новое описание заявки:\n");
        description = cin.stringEntry();

        cout.out("Введите новый комментарий:\n");
        comment = cin.stringEntry();
        item = new Item(username, description, new Date(), comment);
        item.setItemID(itemID);
        this.tracker.editItem(item);
        consoleGuiDrawer.borderGenerator("-");
        cout.out("Заявка с номером " + itemID + " отредактирована \n");
        cout.out("Для продолжения - нажмите Enter \n");
        cin.anyKeyEntry();
    }

    /**
     * Добавить заявку Item в трекер
     */
    private void addMenuAction() {

        String username, description, comment;
        Item item;
        this.consoleGuiDrawer.borderGenerator("+");
        this.cout.out("ДОБАВЛЕНИЕ НОВОЙ ЗАЯВКИ \n");
        this.consoleGuiDrawer.borderGenerator("+");

        this.cout.out("Введите ваше имя: \n");
        username = this.cin.stringEntry();

        cout.out("Введите описание заявки: \n");
        description = cin.stringEntry();

        cout.out("Введите комментарий: \n");
        comment = cin.stringEntry();
        item = new Item(username, description, new Date(), comment);
        this.tracker.addItem(item);
        consoleGuiDrawer.borderGenerator("o");
        cout.out("Заявка создана \n");
        cout.out("Номер заявки " + item.getItemID() +"\n");
        cout.out("Для продолжения - нажмите Enter \n");
        cin.anyKeyEntry();
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
            cout.out("В ТРЕКЕРЕ НЕТ ЗАЯВОК \n");
            consoleGuiDrawer.borderGenerator("!");
            cout.out("Для продолжения - нажмите Enter \n");
            this.cin.anyKeyEntry();
            return false;
        }
    }
}



