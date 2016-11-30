package ru.tikhonov.term1.fserver;

import java.io.*;

/**
 * Общий интерфейс передачи данных между потоками
 *
 * @author Sergey Tikhonov
 */
public interface InputOutput {
    boolean transferFromTo(final DataOutputStream outputStream, final DataInputStream inputStream) throws IOException;
    boolean transferToFrom(final DataOutputStream outputStream, final DataInputStream inputStream) throws IOException;


}
