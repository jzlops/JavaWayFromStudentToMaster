package ru.stikhonov.term4;

import java.util.Arrays;
import java.util.Date;

/**
 * Класс для ведения трекера заявок
 *
 * @author Sergey Tikhonov
 */


class Tracker {

    private Item[] items;
    private int itemsCount = 0;
    private int capacity = 0;

    /**
     * @param capacity начальная "емкость" трекера (увеличивается автоматически в два раза при нехватке места)
     */
    Tracker(int capacity) {
        this.items = new Item[capacity];
        this.capacity = capacity;
    }

    /**
     * Внутренний метод, служит для увеличения емкости трекера х2 при большом количестве заявок
     */
    private void increaseCapacity() {
        if (this.itemsCount == this.capacity) {
            this.capacity = this.capacity * 3;
            Item[] tempItems = new Item[this.capacity];
            for (int i = 0; i < this.itemsCount; i++) {
                tempItems[i] = this.items[i];
            }
            this.items = tempItems;
        }
    }

    /**
     * Внутренний метод
     *
     * @return случайно сгенерированный ID для вновь созданной заявки
     */
    private String itemIDGenerator() {
        StringBuilder randomString = new StringBuilder();
        randomString.append("RQS").append((int) (Math.random() * 100000));
        return randomString.toString();
    }

    /**
     * @return общее количество заявок в трекере
     */
    int getItemsCount() {
        return this.itemsCount;
    }

    /**
     * Метод отвечающий за добаление заявок в трекер
     *
     * @param item принимает в качестве параметра объект класса трекер
     */
    Item addItem(Item item) {
        increaseCapacity();
        this.items[this.itemsCount] = item;
        this.items[this.itemsCount].setItemID(this.itemIDGenerator());
        this.itemsCount++;
        return item;
    }

    /**
     * Метод удаляющий заявку из трекера по ее ID
     *
     * @param itemID строковое поле ID заявки
     * @return true в случае успешной операции, иначе false
     */
    boolean deleteItem(String itemID) {
        boolean fit = false;
        for (int i = 0; i < itemsCount; i++) {
            if (this.items[i].getItemID().equals(itemID)) {
                fit = true;
                for (int j = i; j < this.itemsCount - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
            }
        }
        if (fit) {
            this.itemsCount--;
            this.items[this.itemsCount] = null;
        }
        return fit;
    }

    /**
     * Метод служит для редактирования заявки
     *
     * @param item объект тива Item с выбранным пользователем ID, находит в трекере по даному ID заявку и менает ее на новый объект Item
     */
    boolean editItem(Item item) {
        if (itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(item.getItemID())) {
                    this.items[i] = item;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод служит для возвращения всех заявок в трекере
     *
     * @return указатель на массив заявок
     */
    Item[] getAllItems() {
        if (this.itemsCount == 0) {
            return null;
        } else {
            return Arrays.copyOf(this.items, this.itemsCount);
        }
    }


    /**
     * Метод служит для поиска и возвращения заявки по ее ID
     *
     * @param itemID строковый ID заявки
     * @return указатель на найденную заявку
     */
    Item getItemByID(String itemID) {
        if (itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(itemID)) {
                    return this.items[i];
                }
            }
        }
        return null;
    }

    /**
     * Метод проверяет существование заявки в трекере по ее ID
     *
     * @param itemID строковый ID заявки
     * @return true - если заявка найдена в трекере, иначе false
     */
    boolean itemExistence(String itemID) {
        if (this.itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(itemID)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод ищет и возвращает набор заявко из трекера по диапазону дат создания(редактирования)
     *
     * @param date1 начальная дата (объект тива Date)
     * @param date2 конечная дата (объект тива Date)
     * @return указатель на массив найденых заявок
     */
    Item[] getItemsByDataRange(Date date1, Date date2) {
        if (this.itemsCount == 0) return null;
        Item[] tempItems = new Item[this.itemsCount];
        int fitCount = 0;
        for (int i = 0; i < itemsCount; i++) {
            if (this.items[i].getDate().after(date1) && this.items[i].getDate().before(date2)) {
                tempItems[fitCount] = this.items[i];
                fitCount++;
            }
        }
        if (fitCount == 0) return null;
        tempItems = Arrays.copyOf(tempItems, fitCount);
        return tempItems;
    }
}
