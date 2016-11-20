package ru.tikhonov.term1.fileOperations;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) throws IOException {
        boolean sortingResult = false;
        String projectDir = System.getProperty("user.dir");
        String sourceFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#source.txt", projectDir);
        String destinationFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#destination.txt", projectDir);
        String logFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#log.txt", projectDir);

        Logging logger = new Logger();
        FilesInit filesInit = new FilesInit();
        Sorting filesSort = new FilesSort();
        if (logger.init(logFile)) {
            filesInit.setLogger(logger);
            if (filesInit.init(sourceFile, destinationFile)) {
                ((FilesSort) filesSort).setLogger(logger);
                sortingResult = filesSort.sort(filesInit.getInputFile(), filesInit.getOutputFile());
            }
        }
        logger.appendLog(String.format("Результат выполнения сортировки файлов - %b%n", sortingResult));
        logger.close();
    }
}


