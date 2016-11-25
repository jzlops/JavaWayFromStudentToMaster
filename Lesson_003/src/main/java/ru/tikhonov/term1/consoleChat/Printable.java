package ru.tikhonov.term1.consoleChat;

/**
 * Общий интерфейс для объектов умеющих "печатать" объект String
 *
 * @author Sergey Tikhonov
 */
interface Printable {
    /**
     * Метод печатающий сообщение
     *
     * @param msg объект String
     */
    void print(String msg);
}
