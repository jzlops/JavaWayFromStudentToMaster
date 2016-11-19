package ru.tikhonov.term1.fileOperations;

import java.io.File;

/**
 * Общий интерфейс для сортировки файлов
 *
 * @author Sergey Tikhonov
 */
interface Sorting {
    boolean sort(File source, File distance);
}
