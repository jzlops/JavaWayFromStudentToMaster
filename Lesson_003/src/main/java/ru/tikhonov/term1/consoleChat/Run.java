package ru.tikhonov.term1.consoleChat;

import java.io.File;

/**
 * @author Sergey Tikhonov
 */
class Run {
    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        String logFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//consoleChat//files//#log.txt", projectDir);
        String answerFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//consoleChat//files//#answers.txt", projectDir);

        LoggerBot logger = new LoggerBot();
        InOut inOut = new InputOutput();
        Printable printer = new ConsoleOut();
        AnswerBot answerBot = new AnswerBot();

        logger.init(new File(logFile));
        answerBot.init(new File(answerFile));

        ChatBot chatBot = new ChatBot(inOut, logger, answerBot, printer);

        chatBot.chat();

        logger.close();
        answerBot.close();
    }
}
