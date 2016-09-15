package ru.stikhonov.term1;

import java.util.Date;

/**
 * Класс для ведения трекера запявок
 *
 * @author Sergey Tikhonov
 *
 */


//TODO где реализовывать консольный ввод в методах? использовать для этого класс ConsoleInput? или же создать отдельный класс для диалога? Или же прям в методах трекера? (но это прям нехорошо).

public class Tracker {
    private Item[] items;
    private int itemsCount = 0;

    public void add() {
        // Процесс создания/добавления элементов к массиву Items
        Date date = new Date(121212);
        this.items[itemsCount] = new Item("Ivan", "Admin", date, "");
        itemsCount++;
    }

    public void edit() {
        // Процесс редактирования элемента в массиве Items
    }

    public void delete() {
        // Процесс удаления элемента в массиве Items
        itemsCount--;
    }

    public void show() {
        // Отображение всех элементов Items
    }

    public void showFiltered() {
        // Отображение по фильтру элементов массива Items
    }

}
