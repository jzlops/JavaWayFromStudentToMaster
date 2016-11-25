package ru.tikhonov.term1.chatBotTest;

import ru.tikhonov.term1.consoleChat.Answerable;

/**
 * Заглушка для файлов ответов
 *
 * @author Sergey Tikhonov
 */
class StubAnswer implements Answerable {
    private String[] answers;
    private int answersIndex;

    public StubAnswer(String[] answers) {
        this.answers = answers;
        this.answersIndex = -1;
    }

    @Override
    public String getAnswer() {
        this.answersIndex++;
        return this.answers[this.answersIndex];
    }
}
