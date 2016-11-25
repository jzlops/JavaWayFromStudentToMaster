package ru.tikhonov.term1.consoleChat;

/**
 * Класс реализующий консольный чат
 *
 * @author Sergey Tikhonov
 */
class ChatBot {
    private final LoggerBot logger;
    private final InOut inOut;
    private final Answerable answerBot;
    private Printable consolePrinter;
    private boolean isExit = false;
    private boolean isMute = false;

    /**
     * Конструктор чата, принимает в себя ряд объектов для работы
     *
     * @param inOut     Объект ввода выдвода даны в чат
     * @param logger Объект работы с логом
     * @param answerBot Объект работы с истоничком ответов
     * @param printBot  Объект печатающий сообщение
     */
    ChatBot(final InOut inOut, final LoggerBot logger, final Answerable answerBot, final Printable printBot) {
        this.inOut = inOut;
        this.logger = logger;
        this.consolePrinter = printBot;
        this.answerBot = answerBot;
    }

    /**
     * Основной цикл чата
     */
    void chat() {
        final StringBuilder inBuffer = new StringBuilder();
        do {
            inBuffer.append(String.format("User: %s", this.inOut.getMessage()));

            if (inBuffer.toString().equals("User: mute")) {
                this.isMute = true;
            }
            if (inBuffer.toString().equals("User: go")) {
                this.isMute = false;
            }
            if (inBuffer.toString().equals("User: stop")) {
                this.isExit = true;
                continue;
            }

            inOut.sendMessage(this.logger, inBuffer.toString());
            inBuffer.setLength(0);
            inBuffer.append(String.format("Bot: %s", answerBot.getAnswer()));

            if (!this.isMute) {
                this.inOut.sendMessage(this.consolePrinter, inBuffer.toString());
                this.inOut.sendMessage(this.logger, inBuffer.toString());
            }
            inBuffer.setLength(0);
        } while (!isExit);

    }
}
