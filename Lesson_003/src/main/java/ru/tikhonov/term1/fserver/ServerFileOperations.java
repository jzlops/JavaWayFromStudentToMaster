package ru.tikhonov.term1.fserver;

import java.io.IOException;
import java.io.OutputStream;
/**
 * Интерфейс для работы с файловым менеджером
 *
 * @author Sergey Tikhonov
 */
 interface ServerFileOperations {
    boolean execOperation( String commandName,  OutputStream outMessage) throws IOException;
}
