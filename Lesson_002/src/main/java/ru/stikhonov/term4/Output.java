package ru.stikhonov.term4;

/**
 * Общий интерфейс вывода данных об объекте
 *
 * @author Sergey Tikhonov
 */
 interface Output {

    /**
     * Метод должен выводить информацию о передаваемом в него объекте
     *
     * @param obj
     */
    void out(Object obj);
}
