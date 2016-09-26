package ru.stikhonov.term1;

import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
class ConsoleUserInput {
    private Tracker tracker;
    private Input consoleInputHelper;
    private ConsoleGuiDrawer consoleGuiDrawer = new ConsoleGuiDrawer();

    ConsoleUserInput(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.consoleInputHelper = input;
    }

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


    private void showItemByFilterAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        Date beginDate, endDate;

        if (!itemCountChecker(this.tracker.getItemsCount())) return;

        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("СПИСОК ЗАЯВОК ОТСОРТИРОВАННЫЙ ПО ДАТАМ %n");
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Введите начальное время в формате yyyy.MM.dd HH:mm:ss %n");
        beginDate = consoleInputHelper.dateEntry();
        if (beginDate == null) {
            consoleInputHelper.anyKeyEntry();
            return;
        }
        System.out.printf("Введите конечное время в формате yyyy.MM.dd HH:mm:ss %n");
        endDate = consoleInputHelper.dateEntry();
        if (endDate == null) {
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
        consoleInputHelper.anyKeyEntry();
    }

    private void showItemByIDAction() {
        Item item;
        String itemID;
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        if (!itemCountChecker(this.tracker.getItemsCount())) return;
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("ЗАЯВКА %n");
        consoleGuiDrawer.borderGenerator("+");
        System.out.printf("Введите номер заявки: %n");
        itemID = consoleInputHelper.stringEntry();

        if (!this.tracker.itemExistence(itemID)) {
            System.out.printf("Заявка с номером %1$s ненайдена %n", itemID);
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
        consoleInputHelper.anyKeyEntry();
    }

    private void showMenuAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
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
        consoleInputHelper.anyKeyEntry();
    }

    private void deleteMenuAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
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
        consoleInputHelper.anyKeyEntry();
    }

    private void editMenuAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
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
        consoleInputHelper.anyKeyEntry();
    }

    private void addMenuAction() {

        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
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
        consoleInputHelper.anyKeyEntry();
    }

    private boolean itemCountChecker(int itemCount) {
        if (itemCount > 0) {
            return true;
        } else {
            consoleGuiDrawer.borderGenerator("!");
            System.out.printf("В ТРЕКЕРЕ НЕТ ЗАЯВОК %n");
            consoleGuiDrawer.borderGenerator("!");
            this.consoleInputHelper.anyKeyEntry();
            return false;
        }
    }
}



