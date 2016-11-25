package ru.tikhonov.term1.consoleChat;

/**
 * Класс реализующий консольный ботчат
 * Бот понимает 3 команды:
 * stop - выход из чата
 * mute - перестает отвечать в чат
 * go - начинает отвечать в чат
 *
 * @author Sergey Tikhonov
 */
public class ChatBot {
    private final Printable loggerBot;
    private final InOut inOut;
    private final Answerable answerBot;
    private Printable consolePrinter;
    private boolean isExit = false;
    private boolean isMute = false;

    /**
     * Конструктор чата, принимает в себя ряд объектов для работы
     *
     * @param inOut     Объект ввода выдвода даны в чат
     * @param loggerBot Объект работы с логом
     * @param answerBot Объект работы с истоничком ответов
     * @param printBot  Объект печатающий сообщение
     */
    public ChatBot(final InOut inOut, final Printable loggerBot, final Answerable answerBot, final Printable printBot) {
        this.inOut = inOut;
        this.loggerBot = loggerBot;
        this.consolePrinter = printBot;
        this.answerBot = answerBot;
    }

    /**
     * Основной цикл чата
     */
   public void chat() {
        final StringBuilder inBuffer = new StringBuilder();
        do {
            inBuffer.append(String.format("User: %s", this.inOut.getMessage()));

            if (inBuffer.toString().toLowerCase().equals("user: mute")) {
                this.isMute = true;
            }
            if (inBuffer.toString().toLowerCase().equals("user: go")) {
                this.isMute = false;
            }
            if (inBuffer.toString().toLowerCase().equals("user: stop")) {
                this.isExit = true;
                continue;
            }
            inOut.sendMessage(this.loggerBot, inBuffer.toString());
            inBuffer.setLength(0);
            inBuffer.append(String.format("Bot: %s", answerBot.getAnswer()));

            if (!this.isMute) {
                this.inOut.sendMessage(this.consolePrinter, inBuffer.toString());
                this.inOut.sendMessage(this.loggerBot, inBuffer.toString());
            }
            inBuffer.setLength(0);
        } while (!isExit);

    }
}
