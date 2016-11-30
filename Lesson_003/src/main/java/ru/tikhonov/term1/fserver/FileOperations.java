package ru.tikhonov.term1.fserver;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Общий интерфейс для выдачи помоще по поддерживаемым коммандам
 *
 * @author Sergey Tikhonov
 */
public interface FileOperations {
    boolean execOperation(final String commandName, final DataOutputStream outMessage) throws IOException;
}
