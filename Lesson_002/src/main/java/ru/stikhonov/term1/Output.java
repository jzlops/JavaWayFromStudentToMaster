package ru.stikhonov.term1;

/**
 * Общий интерфейс вывода в консоль
 *
 * @author Sergey Tikhonov
 */
public interface Output {

    /**
     * Метод должен выводить на консоль информацию о передаваемом в него объекте
     *
     * @param obj
     */
    void out(Object obj);
}
