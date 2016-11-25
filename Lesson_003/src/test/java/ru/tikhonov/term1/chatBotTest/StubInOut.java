package ru.tikhonov.term1.chatBotTest;

import ru.tikhonov.term1.consoleChat.InOut;
import ru.tikhonov.term1.consoleChat.Printable;

/**
 * @author Sergey Tikhonov
 */
 class StubInOut implements InOut {
    private String[] messages;
    private int messagesIndex;

    StubInOut(final String[] messages) {
        this.messages = messages;
        this.messagesIndex = -1;
    }

    @Override
    public String getMessage() {
        this.messagesIndex++;
        return this.messages[this.messagesIndex];
    }

    @Override
    public void sendMessage(final Printable object, final String msg) {
        object.print(msg);
    }
}
