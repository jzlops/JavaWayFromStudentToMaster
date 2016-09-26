package ru.stikhonov.term1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
public class TestInput implements Input {
    private String[] answers;
    private int index = -1;

    TestInput(String[] answers) {
        this.answers = answers;
    }

    @Override
    public Date dateEntry() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        try {
            return simpleDateFormat.parse(answers[index++]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int intEntry() {
        this.index++;
        return Integer.parseInt(this.answers[this.index]);
    }

    @Override
    public String stringEntry() {
        this.index++;
        return answers[this.index];
    }

    @Override
    public void anyKeyEntry() {

    }
}
