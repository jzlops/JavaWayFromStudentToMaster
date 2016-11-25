package ru.tikhonov.term1.consoleChat;

/**
 * Общий интерйес ввода выдода
 *
 * @author Sergey Tikhonov
 */
interface InOut {
    /**
     * Запросить сообщение String у объекта
     *
     * @return String
     */
    String getMessage();

    /**
     * Послать сообщение String объекту реализующему интефейс Prinable
     *
     * @param object Объект реализующий интерфейс Printable
     * @param msg    сообщение String
     */
    void sendMessage(Printable object, String msg);
}
