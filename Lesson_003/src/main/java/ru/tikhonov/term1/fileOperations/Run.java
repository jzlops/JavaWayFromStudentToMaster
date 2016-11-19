package ru.tikhonov.term1.fileOperations;

import java.io.IOException;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) throws IOException {
        String sourceFile = ".//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#source.txt";
        String destinationFile = ".//Lesson_003//src//main//java//ru//tikhonov//term1//fileOperations//#destination.txt";
        boolean sortingResult = false;
        FilesInit filesInit = new FilesInit();
        Sorting filesSort = new FilesSort();
        if (filesInit.init(sourceFile, destinationFile)) {
            sortingResult = filesSort.sort(filesInit.getInputFile(), filesInit.getOutputFile());
        }
        System.out.printf("Результат выполнения сортировки файлов - %1$b %n", sortingResult);
    }
}
//        System.out.println(System.getProperty("user.dir"));