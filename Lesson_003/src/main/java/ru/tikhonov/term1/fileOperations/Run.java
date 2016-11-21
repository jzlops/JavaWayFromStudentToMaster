package ru.tikhonov.term1.fileOperations;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) throws IOException {
        boolean sortingResult;
        String projectDir = System.getProperty("user.dir");
        String sourceFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//Files//#source.txt", projectDir);
        String destinationFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//Files//#destination.txt", projectDir);
        String logFile = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//Files//#log.txt", projectDir);
        String fileA = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//Files//~A.txt", projectDir);
        String fileB = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//Files//~B.txt", projectDir);
        String sourceFileCopy = String.format("%s//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//Files//~SourceCopy.txt", projectDir);

        Logging logger = new Logger();
        FilesInit filesInit = new FilesInit();
        Sorting filesSortSlow = new FilesSortSlow();
        Sorting fileSortFast = new FileSortFast();

        if (logger.init(logFile)) {
            filesInit.setLogger(logger);
            if (filesInit.init(sourceFile, destinationFile, fileA, fileB, sourceFileCopy)) {
                ((FilesSortSlow) filesSortSlow).setLogger(logger);
                ((FileSortFast) fileSortFast).setLogger(logger);
                //  sortingResult = filesSortSlow.sort(filesInit.getSourceFile(), filesInit.getDestinationFile());
                // logger.appendLog(String.format("Результат выполнения сортировки файлов - %b%n", sortingResult));

                ((FileSortFast) fileSortFast).setAdditionalFiles(filesInit.getFileA(), filesInit.getFileB(), filesInit.getFileSourceCopy());
                sortingResult = fileSortFast.sort(filesInit.getSourceFile(), filesInit.getDestinationFile());
                logger.appendLog(String.format("Результат выполнения сортировки файлов - %b%n", sortingResult));
            }
        }
        logger.close();
    }
}


