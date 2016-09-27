package ru.stikhonov.term1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Класс для тестирования трекера
 *
 * @author Sergey Tikhonov
 */
public class TrackerTest {
    /**
     * Метод проверяет корректность заполнения трекера (количество заявок = getItemCount())
     *
     * @throws Exception
     */
    @Test
    public void getItemsCount() throws Exception {
        Item item1 = new Item("John Doe 1", "Test", new Date(), "No Comment");
        Item item2 = new Item("John Doe 2", "Test", new Date(), "No Comment");
        Item item3 = new Item("John Doe 3", "Test", new Date(), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1);
        tracker.addItem(item2);
        tracker.addItem(item3);
        Assert.assertEquals(3, tracker.getItemsCount());
    }

    /**
     * Проверка генерации ItemID для элемента
     *
     * @throws Exception
     */
    @Test
    public void itemIDGenerateInTracker() throws Exception {
        Tracker tracker = new Tracker(1);
        String itemIDBefore, itemIDAfter;
        Item item1 = new Item("John Doe 1", "Test", new Date(), "No Comment");
        itemIDBefore = item1.getItemID();
        tracker.addItem(item1);
        itemIDAfter = item1.getItemID();
        Assert.assertNotEquals(itemIDBefore, itemIDAfter);
    }

    /**
     * Проверка уникальности ID разных заявок
     *
     * @throws Exception
     */
    @Test
    public void itemIDGenerateInTrackerIsUnique() throws Exception {
        Tracker tracker = new Tracker(1);
        Item item1 = new Item("John Doe 1", "Test", new Date(), "No Comment");
        Item item2 = new Item("John Doe 1", "Test", new Date(), "No Comment");
        tracker.addItem(item1);
        tracker.addItem(item2);
        Assert.assertNotEquals(item1.getItemID(), item2.getItemID());
    }


    @Test
    public void deleteItem() throws Exception {

    }

    @Test
    public void editItem() throws Exception {

    }

    @Test
    public void getAllItems() throws Exception {

    }

    @Test
    public void getItemByID() throws Exception {

    }

    @Test
    public void itemExistence() throws Exception {

    }

    @Test
    public void getItemsByDataRange() throws Exception {

    }

}