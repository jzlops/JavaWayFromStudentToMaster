package ru.stikhonov.term1;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
public interface Input {
    Date dateEntry() throws ParseException;

    int intEntry();

    String stringEntry();

    void anyKeyEntry();

}
