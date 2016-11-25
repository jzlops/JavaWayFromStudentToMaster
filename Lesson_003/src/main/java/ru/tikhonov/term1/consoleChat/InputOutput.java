package ru.tikhonov.term1.consoleChat;

import java.util.Scanner;

/**
 * Класс ввода данных с консоли и вывода данных в объект реализующий Printable.
 *
 * @author Sergey Tikhonov
 */
class InputOutput implements InOut {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Вызов метода print у переданного объекта.
     *
     * @param object Объект реализующий интерфейс Printable
     * @param msg    сообщение String передаваемое в метод print
     */
    @Override
    public void sendMessage(final Printable object, final String msg) {
        object.print(msg);
    }

    /**
     * Ввобд данных с консоли
     *
     * @return вотзвращает считанную строку с консли
     */
    @Override
    public String getMessage() {
        if (scanner.hasNext()) return scanner.nextLine();
        return "Error";
    }

    void close() {
        scanner.close();
    }
}
