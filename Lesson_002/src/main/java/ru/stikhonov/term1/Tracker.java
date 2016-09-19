package ru.stikhonov.term1;

import java.util.Arrays;
import java.util.Date;

/**
 * Класс для ведения трекера запявок
 *
 * @author Sergey Tikhonov
 */


public class Tracker {
    private int index = 0;
    private Item[] items;
    private int itemsCount;

    Tracker(int itemsCount) {
        Item[] items = new Item[itemsCount];
        this.items = items;
        this.itemsCount = itemsCount;
    }

    public void add(String newName, String newDescription, Date newDate, String newComments) {
        this.items[index].setName(newName);
        this.items[index].setDescription(newDescription);
        this.items[index].setDate(newDate);
        this.items[index].setComments(newComments);
        this.index++;
    }

    public void edit(int index, String newName, String newDescription, Date newDate, String newComments) {
        this.items[index].setName(newName);
        this.items[index].setDescription(newDescription);
        this.items[index].setDate(newDate);
        this.items[index].setComments(newComments);
    }

    public void delete(int index) {
        this.items[index] = null;
    }

    public Item[] show() {
        Item[] tempItems = new Item[this.itemsCount];
        for (int i = 0; i < this.itemsCount; i++) {
            tempItems[i]=this.items[i];
        }
        return tempItems;
    }

    public Item showFiltered(int index) {
        return this.items[index];
    }
}
