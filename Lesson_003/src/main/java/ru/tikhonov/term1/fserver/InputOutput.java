package ru.tikhonov.term1.fserver;

import java.io.*;

/**
 * Общий интерфейс передачи данных между потоками
 *
 * @author Sergey Tikhonov
 */
public interface InputOutput {
    boolean getMessage(final InputStream inputStream, final OutputStream outputStream);

    boolean setMessage(final OutputStream outputStream, final InputStream inputStream);


}
