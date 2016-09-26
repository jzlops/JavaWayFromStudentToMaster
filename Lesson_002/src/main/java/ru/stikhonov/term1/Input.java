package ru.stikhonov.term1;

import java.util.Date;

/**
 * @author Sergey Tikhonov
 */
public interface Input {
    Date dateEntry();

    int intEntry();

    String stringEntry();

    void anyKeyEntry();

}
