package ru.tikhonov.term1.fileOperations;

import java.io.File;

/**
 * @author Sergey Tikhonov
 */
public class FileDeleter {
    FileDeleter fileDelete(File file) {
        file.delete();
        return this;
    }
}
