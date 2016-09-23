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
    private int capacity;

    Tracker(int capacity) {
        this.items = new Item[capacity];
        this.capacity = capacity;
    }

    public int getItemsCount() {
        return this.itemsCount;
    }

    public void addItem(Item item) {
        increaseCapacity();
        this.items[this.itemsCount] = item;
        this.itemsCount++;
    }


    private void increaseCapacity() {
        if (this.itemsCount == this.capacity) {
            this.capacity = this.capacity * 2;
            Item[] tempItems = new Item[this.capacity];
            for (int i = 0; i < this.itemsCount; i++) {
                tempItems[i] = this.items[i];
            }
            this.items = tempItems;
        }

    }

//    public void addItem(Item item) {
//        if (itemsCount > 0) {
//            Item[] tempItems = new Item[this.itemsCount + 1];
//            for (int i = 0; i < this.itemsCount; i++) {
//                tempItems[i] = this.items[i];
//            }
//            tempItems[itemsCount] = item;
//            this.items = tempItems;
//            this.itemsCount++;
//        } else {
//            this.itemsCount++;
//            Item[] tempItems = new Item[itemsCount];
//            tempItems[0] = item;
//            this.items = tempItems;
//        }
//    }


    public boolean deleteItem(String itemID) {
        boolean fit = false;
        if (this.itemsCount == 0) {
            return false;
        }
        for (int i = 0; i < this.itemsCount; i++) {
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
//        if (itemsCount > 0) {
//            this.items = Arrays.copyOf(this.items, this.itemsCount);
//        }
        return fit;
    }

    public Item[] getAllItems() {
        return this.items;
    }

    public Item editItem(String itemID) {
        if (itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(itemID)) {
                    return this.items[i];
                }
            }
        }
        return null;
    }

    public Item getItemByID(String itemID) {
        if (itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(itemID)) {
                    return this.items[i];
                }
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
