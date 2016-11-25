package ru.tikhonov.term1.chatBotTest;

import ru.tikhonov.term1.consoleChat.Printable;

/**
 * Заглушка для вывода на консоль
 *
 * @author Sergey Tikhonov
 */
public class StubConsolePrint implements Printable {
    private String[] messages = new String[10];
    private int inMessagesIndex = -1;
    private int outMessagesIndex = -1;


    @Override
    public void print(String msg) {
        this.inMessagesIndex++;
        this.messages[this.inMessagesIndex] = msg;
    }

    String getMessages() {
        this.outMessagesIndex++;
        return this.messages[this.outMessagesIndex];
    }
}
