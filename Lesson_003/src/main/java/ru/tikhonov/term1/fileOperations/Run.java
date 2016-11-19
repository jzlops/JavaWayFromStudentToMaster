package ru.tikhonov.term1.fileOperations;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) throws IOException {
        boolean sortingResult = false;
        String projectDir = System.getProperty("user.dir");
        String sourceFile = projectDir + "//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#source.txt";
        String destinationFile = projectDir + "//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#destination.txt";
        String logFile = projectDir + "//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#log.txt";

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
        logger.appendLog("Результат выполнения сортировки файлов - " + sortingResult + "\n");
        logger.close();
    }
}


