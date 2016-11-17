package ru.tikhonov.term1;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

/**
 * Тестируем работу класса реализующего дроп ненужных слов
 *
 * @author Sergey Tikhonov
 */
public class StreamAbuseWordsDropTest {
    @Test
    public void testAbuseWordsDrop() throws Exception {
        String[] abuse = {"проймет", "акваланг", "очень", "кекс", "да здравстует велкий Мао", "он"};
        StreamAbuseWordsDrop streamAbuseWordsDrop = new StreamAbuseWordsDrop();
        ByteArrayInputStream streamIn = new ByteArrayInputStream("Мишка очень любит мёд, мёд ядренный он проймет".getBytes());
        ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
        streamAbuseWordsDrop.abuseWordsDrop(streamIn, streamOut, abuse);
        Assert.assertEquals(streamOut.toString(),"Мишка  любит мёд, мёд ядренный  ");

    }

}