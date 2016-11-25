package ru.tikhonov.term1.chatBotTest;

import org.junit.Test;
import ru.tikhonov.term1.consoleChat.Answerable;
import ru.tikhonov.term1.consoleChat.ChatBot;
import ru.tikhonov.term1.consoleChat.InOut;
import ru.tikhonov.term1.consoleChat.Printable;

/**
 * Тест чат-бота
 *
 * @author Sergey Tikhonov
 */
public class ChatBotTest {

    @Test
    public void testStopInput() {
        Answerable stubAnswer = new StubAnswer(new String[]{"test", "z-z-z-dz-z", "asdasd", "asdsad"});
        Printable stubLog = new StubLog();
        Printable stubConsolePrint = new StubConsolePrint();
        InOut stubInOut = new StubInOut(new String[]{"test", "stop"});
        ChatBot chatBot = new ChatBot(stubInOut, stubLog, stubAnswer, stubConsolePrint);
        chatBot.chat();
    }

    @Test
    public void testMuteInput() {
        Answerable stubAnswer = new StubAnswer(new String[]{"hi", "z-z-z-z-z", "sadsad", "asdasdas"});
        Printable stubLog = new StubLog();
        Printable stubConsolePrint = new StubConsolePrint();
        InOut stubInOut = new StubInOut(new String[]{"test", "mute", "sdfsdf", "ssdfsd", "go", "sdfsdf", "stop"});
        ChatBot chatBot = new ChatBot(stubInOut, stubLog, stubAnswer, stubConsolePrint);
        chatBot.chat();
    }

    @Test
    public void testGoInput() {
        Answerable stubAnswer = new StubAnswer(new String[]{"test", "z-z-z-z-z"});
        Printable stubLog = new StubLog();
        Printable stubConsolePrint = new StubConsolePrint();
        InOut stubInOut = new StubInOut(new String[]{"hello", "GO"});
        ChatBot chatBot = new ChatBot(stubInOut, stubLog, stubAnswer, stubConsolePrint);
        chatBot.chat();
    }

}
