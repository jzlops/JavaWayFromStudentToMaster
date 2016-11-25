package ru.tikhonov.term1.chatBotTest;

import org.junit.Assert;
import org.junit.Test;
import ru.tikhonov.term1.consoleChat.ChatBot;
import ru.tikhonov.term1.consoleChat.InOut;


/**
 * Тест чат-бота
 *
 * @author Sergey Tikhonov
 */
public class ChatBotTest {

    @Test
    public void testGeneralWork() {
        StubAnswer stubAnswer = new StubAnswer(new String[]{"test", "z-z-z-dz-z"});
        StubLog stubLog = new StubLog();
        StubConsolePrint stubConsolePrint = new StubConsolePrint();
        InOut stubInOut = new StubInOut(new String[]{"test1", "test2", "stop"});
        ChatBot chatBot = new ChatBot(stubInOut, stubLog, stubAnswer, stubConsolePrint);
        chatBot.chat();
        Assert.assertEquals(stubLog.getMessages(), "User: test1");
        Assert.assertEquals(stubLog.getMessages(), "Bot: test");
        Assert.assertEquals(stubLog.getMessages(), "User: test2");
        Assert.assertEquals(stubLog.getMessages(), "Bot: z-z-z-dz-z");
        Assert.assertEquals(stubLog.getMessages(), "User: stop");

        Assert.assertEquals(stubConsolePrint.getMessages(), "Bot: test");
        Assert.assertEquals(stubConsolePrint.getMessages(), "Bot: z-z-z-dz-z");

        Assert.assertFalse(chatBot.getRunningStatus());

    }

    @Test
    public void testGeneralWorkWithMute() {
        StubAnswer stubAnswer = new StubAnswer(new String[]{"Hi", "Ehlo", "WFT"});
        StubLog stubLog = new StubLog();
        StubConsolePrint stubConsolePrint = new StubConsolePrint();
        InOut stubInOut = new StubInOut(new String[]{"test1", "mute", "Jhon", "stop",});
        ChatBot chatBot = new ChatBot(stubInOut, stubLog, stubAnswer, stubConsolePrint);
        chatBot.chat();
        Assert.assertEquals(stubLog.getMessages(), "User: test1");
        Assert.assertEquals(stubLog.getMessages(), "Bot: Hi");
        Assert.assertEquals(stubLog.getMessages(), "User: mute");
        Assert.assertEquals(stubLog.getMessages(), "User: Jhon");
        Assert.assertEquals(stubLog.getMessages(), "User: stop");

        Assert.assertEquals(stubConsolePrint.getMessages(), "Bot: Hi");

        Assert.assertFalse(chatBot.getRunningStatus());
    }

    @Test
    public void testGeneralWorkWithMuteAndGo() {
        StubAnswer stubAnswer = new StubAnswer(new String[]{"Hi", "Hello", "AWT", "temp"});
        StubLog stubLog = new StubLog();
        StubConsolePrint stubConsolePrint = new StubConsolePrint();
        InOut stubInOut = new StubInOut(new String[]{"FX", "mute", "Jhon", "gO", "trololo", "stop",});
        ChatBot chatBot = new ChatBot(stubInOut, stubLog, stubAnswer, stubConsolePrint);
        chatBot.chat();
        Assert.assertEquals(stubLog.getMessages(), "User: FX");
        Assert.assertEquals(stubLog.getMessages(), "Bot: Hi");
        Assert.assertEquals(stubLog.getMessages(), "User: mute");
        Assert.assertEquals(stubLog.getMessages(), "User: Jhon");
        Assert.assertEquals(stubLog.getMessages(), "User: gO");
        Assert.assertEquals(stubLog.getMessages(), "Bot: Hello");
        Assert.assertEquals(stubLog.getMessages(), "User: trololo");
        Assert.assertEquals(stubLog.getMessages(), "Bot: AWT");
        Assert.assertEquals(stubLog.getMessages(), "User: stop");

        Assert.assertEquals(stubConsolePrint.getMessages(), "Bot: Hi");
        Assert.assertEquals(stubConsolePrint.getMessages(), "Bot: Hello");
        Assert.assertEquals(stubConsolePrint.getMessages(), "Bot: AWT");

        Assert.assertFalse(chatBot.getRunningStatus());
    }
}
