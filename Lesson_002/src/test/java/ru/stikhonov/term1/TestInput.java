package ru.stikhonov.term1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
public class TestInput implements Input {
    private String[] answers;
    private int index;

    TestInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public Date dateEntry() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return simpleDateFormat.parse(answers[index++]);
    }

    @Override
    public int intEntry() {
        return Integer.parseInt(answers[index++]);
    }

    @Override
    public String stringEntry() {
        return answers[index++];
    }

    @Override
    public void anyKeyEntry() {

    }
}
