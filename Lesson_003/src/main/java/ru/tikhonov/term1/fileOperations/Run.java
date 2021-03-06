package ru.tikhonov.term1.fileOperations;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) throws IOException {
        boolean sortingResult;
        System.out.println(System.getProperty("user.dir"));
        String projectDir = System.getProperty("user.dir");
        String sourceFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//files//#source.txt", projectDir);
        String destinationFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//files//#destination.txt", projectDir);
        String logFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//files//#log.txt", projectDir);
        String fileA = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//files//~A.txt", projectDir);
        String fileB = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//files//~B.txt", projectDir);
        String sourceFileCopy = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//files//~SourceCopy.txt", projectDir);

        Logging logger = new Logger();
        FilesInit filesInit = new FilesInit();
        Sorting filesSortSlow = new FilesSortSlow();
        Sorting fileSortFast = new FileSortFast();
        FileDeleter fileDeleter = new FileDeleter();

        if (logger.init(logFile)) {
            filesInit.setLogger(logger);
            if (filesInit.init(sourceFile, destinationFile, fileA, fileB, sourceFileCopy)) {
                ((FilesSortSlow) filesSortSlow).setLogger(logger);
                ((FileSortFast) fileSortFast).setLogger(logger);
                ((FileSortFast) fileSortFast).setAdditionalFiles(filesInit.getFileA(), filesInit.getFileB(), filesInit.getFileSourceCopy());
                sortingResult = fileSortFast.sort(filesInit.getSourceFile(), filesInit.getDestinationFile());
                logger.appendLog(String.format("Результат выполнения сортировки файлов - %b%n", sortingResult));
            }
        }
        logger.close();
        fileDeleter.fileDelete(filesInit.getFileA()).fileDelete(filesInit.getFileB()).fileDelete(filesInit.getFileSourceCopy());
    }
}


