package ru.stikhonov.term1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс реализующий (эмулирующий) сновные методы ввода данных с консоли, спроэктирован для тестирования пользовательского интерйеса классом StubMainOperations
 *
 * @author Sergey Tikhonov
 */
public class StubIN implements Input {
    private String[] answers;
    private int index = -1;

    StubIN(String[] answers) {
        this.answers = answers;
    }

    @Override
    public Date dateEntry() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            this.index++;
            return simpleDateFormat.parse(answers[index]);
        } catch (ParseException e) {
           // e.printStackTrace();
        }
        return null;
    }

    @Override
    public int intEntry() {
        this.index++;
        try {
            return Integer.parseInt(this.answers[this.index]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public String stringEntry() {
        this.index++;
        return answers[this.index];
    }

    @Override
    public boolean anyKeyEntry() {

        return true;
    }
}
