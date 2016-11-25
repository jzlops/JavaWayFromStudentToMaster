package ru.tikhonov.term1.chatBotTest;

import ru.tikhonov.term1.consoleChat.Answerable;

/**
 * @author Sergey Tikhonov
 */
class StubAnswer implements Answerable {
    private String[] answers;
    private int answersIndex;

    public StubAnswer(final String[] answers) {
        this.answers = answers;
        this.answersIndex = -1;
    }

    @Override
    public String getAnswer() {
        this.answersIndex++;
        return this.answers[this.answersIndex];
    }
}
