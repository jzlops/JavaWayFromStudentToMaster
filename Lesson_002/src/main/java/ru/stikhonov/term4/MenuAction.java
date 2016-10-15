package ru.stikhonov.term4;

/**
 * Общий интерфейс выполнения консольного пункта меню
 *
 * @author Sergey Tikhonov
 */
public interface MenuAction {
    /**
     * Метод выполнения пункта меню
     *
     * @param tracker объект Tracker
     * @param input   объект реализующий интрфейс ввода данных - Input
     * @param output  объект реализующий интрфейс вывода данных - Output
     */
    void execute(Tracker tracker, Input input, Output output);

    /**
     * Метод вохзвращающий информацию о пункте меню
     *
     * @return информация о пункте выбранного меню
     */
    String info();
}

