package ru.tikhonov.term1.chatBotTest;

import ru.tikhonov.term1.consoleChat.Printable;

/**
 * Заглушка вывода в лог файл
 *
 * @author Sergey Tikhonov
 */
class StubLog implements Printable {
    private String[] messages = new String[10];
    private int inLogIndex = -1;
    private int outLogIndex = -1;

    @Override
    public void print(String msg) {
        this.inLogIndex++;
        this.messages[this.inLogIndex] = msg;

    }

    String getMessages() {
        this.outLogIndex++;
        return this.messages[this.outLogIndex];
    }
}
