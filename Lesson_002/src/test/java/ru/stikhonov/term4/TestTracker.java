package ru.stikhonov.term4;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Класс для тестирования трекера
 *
 * @author Sergey Tikhonov
 */
public class TestTracker {
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
        itemIDAfter = tracker.addItem(item1).getItemID();
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
        Assert.assertNotEquals(tracker.addItem(new Item("John Doe 1", "Test", new Date(), "No Comment")).getItemID(), tracker.addItem(new Item("John Doe 2", "Test", new Date(), "No Comment")).getItemID());
    }


    /**
     * Метод проверяет корректность удаления 1 заявки из трех.
     * Проверка делается по сравнению всех оставшихся ID в трекере с ID удаленной заявки
     *
     * @throws Exception
     */
    @Test
    public void deleteItemAndTryToFindItInTrackerByID() throws Exception {
        Item[] items;
        Item item1 = new Item("John Doe 1", "Test", new Date(), "No Comment");
        Item item2 = new Item("John Doe 2", "Test", new Date(), "No Comment");
        Item item3 = new Item("John Doe 3", "Test", new Date(), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        tracker.deleteItem("456");
        items = tracker.getAllItems();
        for (Item item : items) {
            Assert.assertNotEquals(item.getItemID(), "456");
        }
    }

    /**
     * Метод проверяет корректность удаления 1 заявки из трех.
     * Проверка деалается по сравнению количества первоночального и конечного количества заявок
     *
     * @throws Exception
     */
    @Test
    public void deleteItemAndCalculateCountItemsInTracker() throws Exception {
        int countBeforeDelete, countAfterDelete;
        Item item1 = new Item("John Doe 4", "Test", new Date(), "No Comment");
        Item item2 = new Item("John Doe 5", "Test", new Date(), "No Comment");
        Item item3 = new Item("John Doe 6", "Test", new Date(), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        countBeforeDelete = tracker.getItemsCount();
        tracker.deleteItem("456");
        countAfterDelete = tracker.getItemsCount();
        Assert.assertNotEquals(countBeforeDelete, countAfterDelete);
    }


    /**
     * Метод создает 3 элемента Item помещает их в трекер, далее мы создаем еще 1 элемент Item и присваиваем ему уже сучествующий ID
     * после чего помещаем его в метод edit для замены уже существующего объекта в трекере с такми ID.
     * В итоге сравниваем идентичность вновь созданного объекта Item и объекта в трекера по его ID.
     *
     * @throws Exception
     */
    @Test
    public void editItem() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item[] items;
        Item tempItem;
        Item item1 = new Item("John Doe 88", "Test", simpleDateFormat.parse("2222.22.22 11:11:10"), "No Comment");
        Item item2 = new Item("John Doe 99", "Test", simpleDateFormat.parse("2222.22.22 11:11:20"), "No Comment");
        Item item3 = new Item("John Doe 77", "Test", simpleDateFormat.parse("2222.22.22 11:11:30"), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        tempItem = new Item("John Doe 007", "TEST", simpleDateFormat.parse("1111.22.22 11:11:30"), "No Comment again");
        tempItem.setItemID("456");
        tracker.editItem(tempItem);
        Assert.assertEquals(tempItem, tracker.getItemByID("456"));
    }

    /**
     * Метод создает 3 заявки помещает их в трекер, далее ищем в трекере заявку по ее ID и сранвиаем с первоначальным элементом
     *
     * @throws Exception
     */
    @Test
    public void getItemByID() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item item1 = new Item("John Doe 88", "Test", simpleDateFormat.parse("2222.22.22 11:11:10"), "No Comment");
        Item item2 = new Item("John Doe 99", "Test", simpleDateFormat.parse("2222.22.22 11:11:20"), "No Comment");
        Item item3 = new Item("John Doe 77", "Test", simpleDateFormat.parse("2222.22.22 11:11:30"), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        Assert.assertEquals(item2, tracker.getItemByID("456"));
    }

    /**
     * Метод создает 3 элемента и помещает их в трекер, далее вызываем метод трекера для возвращения массива всех заявок
     * и сравнимаем их поэлементно с элементами Item создаными первоначально
     *
     * @throws Exception
     */
    @Test
    public void getAllItemsAndEqualsTheirByStartValue() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item[] items;
        Item item1 = new Item("John Doe 88", "Test", simpleDateFormat.parse("2222.22.22 11:11:10"), "No Comment");
        Item item2 = new Item("John Doe 99", "Test", simpleDateFormat.parse("2222.22.22 11:11:20"), "No Comment");
        Item item3 = new Item("John Doe 77", "Test", simpleDateFormat.parse("2222.22.22 11:11:30"), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        items = tracker.getAllItems();
        Assert.assertEquals(item1, items[0]);
        Assert.assertEquals(item2, items[1]);
        Assert.assertEquals(item3, items[2]);
    }


    /**
     * Метод создает 3 элемента Item и помещает их в трекер, далее запрашиваем трекер на количество элементов в нем и
     * стравниваем с количеством первоначальных заявок
     *
     * @throws Exception
     */
    @Test
    public void getAllItemsAndEqualsReturnedItemsCountWithInitialQuantityItems() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item item1 = new Item("John Doe 88", "Test", simpleDateFormat.parse("2222.22.22 11:11:10"), "No Comment");
        Item item2 = new Item("John Doe 99", "Test", simpleDateFormat.parse("2222.22.22 11:11:20"), "No Comment");
        Item item3 = new Item("John Doe 77", "Test", simpleDateFormat.parse("2222.22.22 11:11:30"), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        Assert.assertEquals(3, tracker.getItemsCount());
    }

    /**
     * Метод проверяет количество возвращаемых заявок из трекера по фильтру - диапазон дат
     * Сравнение делается на основе количества возвращаемых элементов
     *
     * @throws Exception
     */
    @Test
    public void getItemsByDataRangeAndCheckCountOfFilteredItems() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item[] items;
        Item item1 = new Item("John Doe 11", "Test", simpleDateFormat.parse("2222.22.22 11:11:10"), "No Comment");
        Item item2 = new Item("John Doe 12", "Test", simpleDateFormat.parse("2222.22.22 11:11:20"), "No Comment");
        Item item3 = new Item("John Doe 13", "Test", simpleDateFormat.parse("2222.22.22 11:11:30"), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        items = tracker.getItemsByDataRange(simpleDateFormat.parse("2222.22.22 11:11:00"), simpleDateFormat.parse("2222.22.22 11:11:22"));
        Assert.assertEquals(2, items.length);
    }

    /**
     * Метод проверяет количество возвращаемых заявок из трекера по фильтру - диапазону дат
     * Сравнение делается на основе еквивалентнсти возращаемых обхектов фильтром и заданных
     *
     * @throws Exception
     */
    @Test
    public void getItemsByDataRangeAndCheckEqualsOfItems() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Item[] items;
        Item item1 = new Item("John Doe 11", "Test", simpleDateFormat.parse("2222.22.22 11:11:10"), "No Comment");
        Item item2 = new Item("John Doe 12", "Test", simpleDateFormat.parse("2222.22.22 11:11:20"), "No Comment");
        Item item3 = new Item("John Doe 13", "Test", simpleDateFormat.parse("2222.22.22 11:11:30"), "No Comment");
        Tracker tracker = new Tracker(1);
        tracker.addItem(item1).setItemID("123");
        tracker.addItem(item2).setItemID("456");
        tracker.addItem(item3).setItemID("789");
        items = tracker.getItemsByDataRange(simpleDateFormat.parse("2222.22.22 11:11:00"), simpleDateFormat.parse("2222.22.22 11:11:22"));
        Assert.assertEquals(item1, items[0]);
        Assert.assertEquals(item2, items[1]);
    }


}