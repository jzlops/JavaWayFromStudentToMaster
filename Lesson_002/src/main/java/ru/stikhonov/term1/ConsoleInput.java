package ru.stikhonov.term1;

/**
 * @author Sergey Tikhonov
 */
public class ConsoleInput {
    Tracker tracker = new Tracker();

    void showItems() {
        this.tracker.show();
    }

    void showFItems() {
        this.tracker.showFiltered();
    }

    void deleteItems() {
        this.tracker.delete();
    }

    void addItems() {
        this.tracker.add();
    }

    void editItems() {
        this.tracker.edit();
    }

}

