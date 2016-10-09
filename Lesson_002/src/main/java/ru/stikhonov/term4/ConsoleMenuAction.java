package ru.stikhonov.term4;

import java.util.Date;

/**
 * Класс реализующий ввод данных польователя с консоли, а так же отбражение вызодных данных, запрашиваемых у трекера
 *
 * @author Sergey Tikhonov
 */
class ConsoleMenuAction {
    private Tracker tracker;
    private Input cin;
    private Output cout;

    private MenuAction[] consoleAction = new MenuAction[6 + 1];

    /**
     * Конструктор принимате на вход 2 параметра
     *
     * @param tracker объект типа Tracker
     * @param cin объет реализующий интерфейс Input
     * @param cout объет реализующий интерфейс Output
     */
    ConsoleMenuAction(Tracker tracker, Input cin, Output cout) {
        this.tracker = tracker;
        this.cin = cin;
        this.cout = cout;
    }

    /**
     * Метод вызывает основной метод execute объекта, реализующего интерфейс MenuAction
     *
     * @param menuElement номер элемента меню
     */
    void menuAction(int menuElement) {
        this.consoleAction[menuElement].execute(this.tracker, this.cin, this.cout);
    }

    /**
     * Метод заполняет массив объектов реализующих интерфейс MenuAction
     */
    void fillActions() {
        this.consoleAction[0] = null;
        this.consoleAction[1] = this.new AddMenuAction();
        this.consoleAction[2] = this.new EditMenuAction();
        this.consoleAction[3] = this.new DeleteMenuAction();
        this.consoleAction[4] = this.new ShowAllMenuAction();
        this.consoleAction[5] = this.new ShowByIDMenuAction();
        this.consoleAction[6] = this.new ShowByFilterMenuAction();
    }

    /**
     * Внутренний класс реализующий интерфейс MenuAction и имеющий один метод - execute отвечающий за добавленрие елемента в трекер
     */
    private class AddMenuAction implements MenuAction {

        /**
         * Метод добавляет элемент в трекер
         *
         * @param tracker объект типа Tracker
         * @param cin объет реализующий интерфейс Input
         * @param cout объет реализующий интерфейс Output
         */
        @Override
        public void execute(Tracker tracker, Input cin, Output cout) {
            ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(cout);
            String username, description, comment;
            Item item;
            cdraw.borderGenerator("+");
            cout.out("ДОБАВЛЕНИЕ НОВОЙ ЗАЯВКИ \n");
            cdraw.borderGenerator("+");

            cout.out("Введите ваше имя: \n");
            username = cin.stringEntry();

            cout.out("Введите описание заявки: \n");
            description = cin.stringEntry();

            cout.out("Введите комментарий: \n");
            comment = cin.stringEntry();
            item = new Item(username, description, new Date(), comment);
            tracker.addItem(item);
            cdraw.borderGenerator("o");
            cout.out("Заявка создана \n");
            cout.out("Номер заявки " + item.getItemID() + "\n");
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
        }
    }

    /**
     * Внутренний класс реализующий интерфейс MenuAction и имеющий один метод - execute отвечающий за редактирование елемента в трекере
     */
    private class EditMenuAction implements MenuAction {
        /**
         * Метод редактирует элемент в трекере
         *
         * @param tracker объект типа Tracker
         * @param cin объет реализующий интерфейс Input
         * @param cout объет реализующий интерфейс Output
         */
        @Override
        public void execute(Tracker tracker, Input cin, Output cout) {
            String username, description, comment, itemID;
            Item item;
            ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(cout);

            if (!itemCountChecker(tracker.getItemsCount())) return;
            cdraw.borderGenerator("+");
            cout.out("РЕДАКТИРОВАНИЕ ЗАЯВКИ \n");
            cdraw.borderGenerator("+");
            cout.out("Введите номер заявки: \n");
            itemID = cin.stringEntry();
            if (!tracker.itemExistence(itemID)) {
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
            tracker.editItem(item);
            cdraw.borderGenerator("-");
            cout.out("Заявка с номером " + itemID + " отредактирована \n");
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
        }
    }

    /**
     * Внутренний класс реализующий интерфейс MenuAction и имеющий один метод - execute отвечающий за удаление елемента в трекере
     */
    private class DeleteMenuAction implements MenuAction {
        /**
         * Метод удаляет элемент в трекере
         *
         * @param tracker объект типа Tracker
         * @param cin объет реализующий интерфейс Input
         * @param cout объет реализующий интерфейс Output
        */
        @Override
        public void execute(Tracker tracker, Input cin, Output cout) {
            ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(cout);
            if (!itemCountChecker(tracker.getItemsCount())) return;
            String itemID;
            cdraw.borderGenerator("+");
            cout.out("УДАЛЕНИЕ ЗАЯВКИ \n");
            cdraw.borderGenerator("+");
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
    }

    /**
     * Внутренний класс реализующий интерфейс MenuAction и имеющий один метод - execute отвечающий за отображение всех элементов в трекере
     */
    private class ShowAllMenuAction implements MenuAction {
        /**
         * Метод отображает в консоль все элементы в трекере
         *
         * @param tracker объект типа Tracker
         * @param сin объет реализующий интерфейс Input
         * @param cout объет реализующий интерфейс Output
         */
        @Override
        public void execute(Tracker tracker, Input сin, Output cout) {
            if (!itemCountChecker(tracker.getItemsCount())) return;
            ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(cout);
            cdraw.borderGenerator("+");
            cout.out("СПИСОК ВСЕХ ЗАЯВОК \n");
            cdraw.borderGenerator("+");
            for (Item item : tracker.getAllItems()) {
                cout.out("ID заявки:  " + item.getItemID() + "\n");
                cout.out("Имя составителя: " + item.getUserName() + "\n");
                cout.out("Описание: " + item.getDescription() + "\n");
                cout.out("Комментарий: " + item.getComments() + "\n");
                cout.out("Дата  последнего изменения: " + item.getDate().toString() + "\n");
                cdraw.borderGenerator("+");
            }
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
        }
    }

    /**
     * Внутренний класс реализующий интерфейс MenuAction и имеющий один метод - execute отвечающий за отображение элемента по его ID
     */
    private class ShowByIDMenuAction implements MenuAction {
        /**
         * Метод отображает в консоль элемент трекера по его ID
         *
         * @param tracker объект типа Tracker
         * @param cin объет реализующий интерфейс Input
         * @param cout объет реализующий интерфейс Output
         */
        @Override
        public void execute(Tracker tracker, Input cin, Output cout) {
            ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(cout);
            Item item;
            String itemID;

            if (!itemCountChecker(tracker.getItemsCount())) return;
            cdraw.borderGenerator("+");
            cout.out("ЗАЯВКА \n");
            cdraw.borderGenerator("+");
            cout.out("Введите номер заявки: \n");
            itemID = cin.stringEntry();

            if (!tracker.itemExistence(itemID)) {
                cout.out("Заявка с номером " + itemID + " ненайдена \n");
                cout.out("Для продолжения - нажмите Enter \n");
                cin.anyKeyEntry();
                return;
            }
            item = tracker.getItemByID(itemID);
            cdraw.borderGenerator("+");
            cout.out("ID заявки: " + item.getItemID() + "\n");
            cout.out("Имя составителя: " + item.getUserName() + "\n");
            cout.out("Описание: " + item.getDescription() + "\n");
            cout.out("Комментарий: " + item.getComments() + "\n");
            cout.out("Дата последнего изменения: " + item.getDate().toString() + "\n");
            cdraw.borderGenerator("+");
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
        }
    }

    /**
     * Внутренний класс реализующий интерфейс MenuAction и имеющий один метод - execute отвечающий за отображение элементов по фильтру
     */
    private class ShowByFilterMenuAction implements MenuAction {
        /**
         * Метод отображает в консоль элементы трекера по фильтру дат
         *
         * @param tracker объект типа Tracker
         * @param cin объет реализующий интерфейс Input
         * @param cout объет реализующий интерфейс Output
         */

        @Override
        public void execute(Tracker tracker, Input cin, Output cout) {
            if (!itemCountChecker(tracker.getItemsCount())) return;
            ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(cout);
            Date beginDate, endDate;
            cdraw.borderGenerator("+");
            cout.out("СПИСОК ЗАЯВОК ОТСОРТИРОВАННЫЙ ПО ДАТАМ \n");
            cdraw.borderGenerator("+");
            cout.out("Введите начальное время в формате yyyy.MM.dd HH:mm:ss \n");
            beginDate = cin.dateEntry();
            if (beginDate == null) {
                cout.out("Неверный  формат данных \n");
                cout.out("Для продолжения - нажмите Enter \n");
                cin.anyKeyEntry();
                return;
            }
            cout.out("Введите конечное время в формате yyyy.MM.dd HH:mm:ss \n");
            endDate = cin.dateEntry();
            if (endDate == null) {
                cout.out("Неверный  формат данных \n");
                cout.out("Для продолжения - нажмите Enter  \n");
                cin.anyKeyEntry();
                return;
            }
            cdraw.borderGenerator("+");
            if (tracker.getItemsByDataRange(beginDate, endDate) != null) {
                cout.out("СПИСОК ЗАЯВОК ЗА УКАЗАННЫЙ ПЕРИОД  \n");
                for (Item item : tracker.getItemsByDataRange(beginDate, endDate)) {
                    cout.out("ID заявки: " + item.getItemID() + "\n");
                    cout.out("Имя составителя: " + item.getUserName() + "\n");
                    cout.out("Описание: " + item.getDescription() + "\n");
                    cout.out("Комментарий:  " + item.getComments() + "\n");
                    cout.out("Дата последнего изменения: " + item.getDate().toString() + "\n");
                    cdraw.borderGenerator("+");
                }
            } else {
                cout.out("Заявок с указанными критериями не найдено \n");
            }
            cout.out("Для продолжения - нажмите Enter \n");
            cin.anyKeyEntry();
        }
    }

    /**
     * Вспомогатльеный метод реагирующий на наличие заявок в трекере
     *
     * @param itemCount количество заявок в трекере
     * @return true если трекер не пуст, иначе false
     */
    private boolean itemCountChecker(int itemCount) {
        ConsoleMenuDrawer cdraw = new ConsoleMenuDrawer(this.cout);
        if (itemCount > 0) {
            return true;
        } else {
            cdraw.borderGenerator("!");
            this.cout.out("В ТРЕКЕРЕ НЕТ ЗАЯВОК \n");
            cdraw.borderGenerator("!");
            this.cout.out("Для продолжения - нажмите Enter \n");
            this.cin.anyKeyEntry();
            return false;
        }
    }
}



