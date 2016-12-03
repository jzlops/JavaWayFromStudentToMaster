package ru.tikhonov.term1.fclient;

import java.io.*;

/**
 * Общий интерфейс передачи данных между потоками
 *
 * @author Sergey Tikhonov
 */
public interface InputOutput {
    void transferFrom(InputStream inputStream) throws IOException;

    void transferTo(OutputStream outputStream) throws IOException;


}
