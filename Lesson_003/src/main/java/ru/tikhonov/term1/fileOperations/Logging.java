package ru.tikhonov.term1.fileOperations;

/**
 * Интерфес для логирования работы
 *
 * @author Sergey Tikhonov
 */
interface Logging {
    void appendLog(String message);

    boolean init(String fileName);

    void close();
}
