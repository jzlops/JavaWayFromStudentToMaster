package ru.stikhonov.term1;

import java.util.Arrays;
import java.util.Date;

/**
 * Класс для ведения трекера запявок
 *
 * @author Sergey Tikhonov
 */


public class Tracker {
    private Item[] items;
    private int itemsCount = 0;

    public void addItem(Item item) {
        Item[] tempItems = this.items;
        this.items = new Item[this.itemsCount + 1];
        for (int i = 0; i < this.itemsCount; i++) {
            this.items[i] = tempItems[i];
        }
        this.itemsCount++;
        this.items[itemsCount] = item;
    }

    public void editItem(Item item) {
        for (int i = 0; i < this.itemsCount; i++) {
            if (this.items[i].getItemID() == item.getItemID()) {
                this.items[i] = item;
            }
        }
    }

    public void deleteItem(String itemID) {
        for (int i = 0; i < this.itemsCount; i++) {
            if (this.items[i].getItemID() == itemID) {
                for (int j = i; j < this.itemsCount - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
            }
        }
        this.items[this.itemsCount] = null;
        this.itemsCount--;
        this.items = Arrays.copyOf(this.items, this.itemsCount);
    }

    public Item[] getAllItems() {
         return this.items;
    }

    public Item getItemByID(String itemID) {
        for (int i = 0; i < this.itemsCount; i++) {
            if (this.items[i].getItemID() == itemID) {
                return this.items[i];
            }
        }
        return null;
    }

    public Item[] getItemsByDataRange(Date date1, Date date2) {
        Item[] tempItems = new Item[this.itemsCount];
        int fitCount = 0;
        for (int i = 0; i < this.itemsCount; i++) {
            if (this.items[i].getDate().after(date1) && this.items[i].getDate().before(date2)) {
                tempItems[fitCount] = this.items[i];
                fitCount++;
            }
        }
        tempItems = Arrays.copyOf(tempItems, fitCount);
        return tempItems;
    }
}
