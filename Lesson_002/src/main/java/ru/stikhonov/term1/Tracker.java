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
    private int markedItemIndex = -1;
    private String markedItemID =null;

    Tracker(int capacity) {
        this.items = new Item[capacity];
        this.capacity = capacity;
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

    private String itemIDGenerator() {
        StringBuilder randomString = new StringBuilder();
        randomString.append("RQS").append((int) (Math.random() * 100000));
        return randomString.toString();
    }


    public int getItemsCount() {
        return this.itemsCount;
    }

    public void addItem(Item item) {
        increaseCapacity();
        this.items[this.itemsCount] = item;
        this.items[this.itemsCount].setItemID(this.itemIDGenerator());
        this.itemsCount++;
    }

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
        return fit;
    }

    public void editItem(Item item) {
        if (itemsCount > 0) {
            item.setItemID(this.markedItemID);
            this.items[this.markedItemIndex] = item;
            this.markedItemIndex =-1;
            this.markedItemID =null;
        }
    }

    public Item[] getAllItems() {
        return Arrays.copyOf(this.items, this.itemsCount);
    }

    public boolean itemMarkByID(String itemID) {
        if (itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(itemID)) {
                    this.markedItemIndex = i;
                    this.markedItemID = this.items[i].getItemID();
                    return true;
                }
            }
        }
        return false;
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

    public boolean itemExistence(String itemID) {
        if (this.itemsCount > 0) {
            for (int i = 0; i < this.itemsCount; i++) {
                if (this.items[i].getItemID().equals(itemID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Item[] getItemsByDataRange(Date date1, Date date2) {
        if (this.itemsCount == 0) return null;
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
