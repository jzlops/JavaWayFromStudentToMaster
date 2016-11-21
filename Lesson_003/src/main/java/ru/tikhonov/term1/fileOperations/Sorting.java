package ru.tikhonov.term1.fileOperations;

import java.io.File;
import java.io.IOException;

/**
 * Общий интерфейс для сортировки файлов
 *
 * @author Sergey Tikhonov
 */
interface Sorting {
    boolean sort(File source, File distance) throws IOException;
}
