package ru.tikhonov.term1.fclient;

import java.io.*;

/**
 * Общий интерфейс передачи данных между потоками
 *
 * @author Sergey Tikhonov
 */
public interface InputOutput {
    void transferFrom(InputStream inputStream);

    void transferTo(OutputStream outputStream);


}
