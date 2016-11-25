package ru.tikhonov.term1.consoleChat;

/**
 * Класс реализующий интерфйс Printable для печати сообщений в консоль
 *
 * @author Sergey Tikhonov
 */
class ConsoleOut implements Printable {

    /**
     * Печать в консоль
     *
     * @param msg объект String
     */
    @Override
    public void print(final String msg) {
        System.out.printf("%s%n", msg);
    }
}
