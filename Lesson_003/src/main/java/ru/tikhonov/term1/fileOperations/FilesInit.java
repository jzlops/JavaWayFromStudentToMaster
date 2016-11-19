package ru.tikhonov.term1.fileOperations;

import java.io.File;
import java.io.IOException;

/**
 * Класс для инициализации файла источника и файла приемника
 *
 * @author Sergey Tikhonov
 */
class FilesInit {
    private File inFile, outFile;

    /**
     * Метод проверяющий доступность входного файла и создающий (в случае отсутствия) выходной файл
     *
     * @param sourceFile      тектовая строка представляющая входной файл для обработки
     * @param destinationFile тектовая строка представляющая выходной файл для обработки
     * @return true если удалось получить доступ к входному и выходному файлу
     */
    boolean init(String sourceFile, String destinationFile) {
        boolean executeCondition;
        this.inFile = new File(sourceFile);
        this.outFile = new File(destinationFile);

        if (inFile.exists() && inFile.isFile()) {
            executeCondition = true;
        } else {
            System.out.printf("Нет входного файла или файл недоступен %n");
            executeCondition = false;
        }
        if (executeCondition) {
            try {
                if (outFile.createNewFile()) {
                    executeCondition = true;
                    System.out.printf("Выходной файл успешно создан %n");
                } else {
                    if (outFile.delete()) {
                        System.out.printf("Старый выходной файл удален %n");
                        if (outFile.createNewFile()) {
                            System.out.printf("Создан новый выходной файл %n");
                            executeCondition = true;
                        } else {
                            System.out.printf("Создать новый выходной файл не удалось %n");
                        }
                    } else {
                        System.out.printf("Неудалось удалить старый выходной файл %n");
                    }
                }
            } catch (IOException e) {
                executeCondition = false;
                System.out.printf("Ошибка операции при работе с выходным файлом файла %n");
                e.printStackTrace();
            }
        }
        return executeCondition;
    }

    /**
     * Создание объекта File для входного файла (файла источника)
     *
     * @return объект  File
     */
    File getInputFile() {
        return this.inFile;
    }

    /**
     * Создание объекта File для выходного файла (файла приемника)
     *
     * @return объект  File
     */

    File getOutputFile() {
        return this.outFile;
    }
}
