package ru.tikhonov.term1.fserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Интерфес для работы с файлами
 *
 * @author Sergey Tikhonov
 */
interface ServerFileOperations {
    boolean execOperation(String commandName, OutputStream outMessage, InputStream inMessage) throws IOException;
}
