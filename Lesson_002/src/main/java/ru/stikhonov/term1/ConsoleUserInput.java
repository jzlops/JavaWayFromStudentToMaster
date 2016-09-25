package ru.stikhonov.term1;

import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
class ConsoleUserInput {
    private Tracker tracker;
    private MenuElements menuElements;

    ConsoleUserInput(Tracker tracker) {
        this.tracker = tracker;
    }

    void menuAction(MenuElements menuElements) {
        if (menuElements.equals(menuElements.ADD)) {
            addMenuAction();
        }
        if (menuElements.equals(menuElements.EDIT)) {
            editMenuAction();
        }
        if (menuElements.equals(menuElements.DELETE)) {
            deleteMenuAction();
        }
        if (menuElements.equals(menuElements.SHOW)) {
            showMenuAction();
        }
        if (menuElements.equals(menuElements.SHOW_BY_ID)) {
            showItemByIDAction();
        }
        if (menuElements.equals(menuElements.SHOW_BY_FILTER)) {
            showItemByFilterAction();
        }
    }


    private void showItemByFilterAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        Date beginDate, endDate;

        if (!consoleInputHelper.itemCountChecker(this.tracker.getItemsCount())) return;

        consoleInputHelper.borderGenerator("+");
        System.out.printf("СПИСОК ЗАЯВОК ОТСОРТИРОВАННЫЙ ПО ДАТАМ %n");
        consoleInputHelper.borderGenerator("+");
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
        consoleInputHelper.borderGenerator("+");
        if (this.tracker.getItemsByDataRange(beginDate, endDate) != null) {
            System.out.printf("СПИСОК ЗАЯВОК ЗА УКАЗАННЫЙ ПЕРИОД ЗАЯВОК %n");
            for (Item item : this.tracker.getItemsByDataRange(beginDate, endDate)) {
                System.out.printf("ID заявки: %1$s %n", item.getItemID());
                System.out.printf("Имя составителя:  %1$s %n", item.getUserName());
                System.out.printf("Описание:  %1$s %n", item.getDescription());
                System.out.printf("Комментарий:  %1$s %n", item.getComments());
                System.out.printf("Дата  последнего изменения: %1$s %n", item.getDate().toString());
                consoleInputHelper.borderGenerator("+");
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
        if (!consoleInputHelper.itemCountChecker(this.tracker.getItemsCount())) return;
        consoleInputHelper.borderGenerator("+");
        System.out.printf("ЗАЯВКА %n");
        consoleInputHelper.borderGenerator("+");
        System.out.printf("Введите номер заявки: %n");
        itemID = consoleInputHelper.stringEntry();

        if (!this.tracker.itemExistence(itemID)) {
            System.out.printf("Заявка с номером %1$s ненайдена %n", itemID);
            consoleInputHelper.anyKeyEntry();
            return;
        }
        item = this.tracker.getItemByID(itemID);
        consoleInputHelper.borderGenerator("+");
        System.out.printf("ID заявки: %1$s %n", item.getItemID());
        System.out.printf("Имя составителя:  %1$s %n", item.getUserName());
        System.out.printf("Описание:  %1$s %n", item.getDescription());
        System.out.printf("Комментарий:  %1$s %n", item.getComments());
        System.out.printf("Дата создания:  %1$s %n", item.getDate().toString());
        consoleInputHelper.borderGenerator("+");
        consoleInputHelper.anyKeyEntry();
    }

    private void showMenuAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        if (!consoleInputHelper.itemCountChecker(this.tracker.getItemsCount())) return;
        consoleInputHelper.borderGenerator("+");
        System.out.printf("СПИСОК ВСЕХ ЗАЯВОК %n");
        consoleInputHelper.borderGenerator("+");
        for (Item item : tracker.getAllItems()) {
            System.out.printf("ID заявки: %1$s %n", item.getItemID());
            System.out.printf("Имя составителя:  %1$s %n", item.getUserName());
            System.out.printf("Описание:  %1$s %n", item.getDescription());
            System.out.printf("Комментарий:  %1$s %n", item.getComments());
            System.out.printf("Дата последнего изменения:  %1$s %n", item.getDate().toString());
            consoleInputHelper.borderGenerator("+");
        }
        consoleInputHelper.anyKeyEntry();
    }

    private void deleteMenuAction() {
        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        if (!consoleInputHelper.itemCountChecker(this.tracker.getItemsCount())) return;
        String itemID;
        consoleInputHelper.borderGenerator("+");
        System.out.printf("УДАЛЕНИЕ ЗАЯВКИ %n");
        consoleInputHelper.borderGenerator("+");
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

        if (!consoleInputHelper.itemCountChecker(this.tracker.getItemsCount())) return;
        consoleInputHelper.borderGenerator("+");
        System.out.printf("РЕДАКТИРОВАНИЕ ЗАЯВКИ %n");
        consoleInputHelper.borderGenerator("+");
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
        this.tracker.editItem(itemID, new Item(username, description, new Date(), comment));
        consoleInputHelper.borderGenerator("-");
        System.out.printf("Заявка с номером %1$s отредактирована %n", itemID);
        consoleInputHelper.anyKeyEntry();
    }

    private void addMenuAction() {

        ConsoleInputHelper consoleInputHelper = new ConsoleInputHelper();
        String username, description, comment;
        Item item;
        consoleInputHelper.borderGenerator("+");
        System.out.printf("ДОБАВЛЕНИЕ НОВОЙ ЗАЯВКИ %n");
        consoleInputHelper.borderGenerator("+");

        System.out.printf("Введите ваше имя:%n");
        username = consoleInputHelper.stringEntry();

        System.out.printf("Введите описание заявки:%n");
        description = consoleInputHelper.stringEntry();

        System.out.printf("Введите комментарий:%n");
        comment = consoleInputHelper.stringEntry();
        item = new Item(username, description, new Date(), comment);
        this.tracker.addItem(item);
        consoleInputHelper.borderGenerator("-");
        System.out.printf("Заявка создана %n");
        System.out.printf("Номер заявки %1$s %n", item.getItemID());
        consoleInputHelper.anyKeyEntry();
    }
}



