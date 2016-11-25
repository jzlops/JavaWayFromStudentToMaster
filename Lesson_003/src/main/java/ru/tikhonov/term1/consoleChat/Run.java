package ru.tikhonov.term1.consoleChat;

import java.io.File;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        String logFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//consoleChat//files//#log.txt", projectDir);
        String answerFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//consoleChat//files//#answers.txt", projectDir);

        LoggerBot loggerBot = new LoggerBot();
        InputOutput inOut = new InputOutput();
        ConsoleOut consoleOut = new ConsoleOut();
        AnswerBot answerBot = new AnswerBot();

        loggerBot.init(new File(logFile));
        answerBot.init(new File(answerFile));

        ChatBot chatBot = new ChatBot(inOut, loggerBot, answerBot, consoleOut);

        chatBot.chat();

        loggerBot.close();
        answerBot.close();
        inOut.close();
    }
}
