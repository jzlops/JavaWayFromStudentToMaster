package ru.tikhonov.term1.fileOperations;

import java.io.File;
import java.io.IOException;

/**
 * Класс для инициализации файла источника и файла приемника
 *
 * @author Sergey Tikhonov
 */
class FilesInit {
    private File sourceFile, destinationFile, fileA, fileB, fileSourceCopy;
    private Logging logger;

    /**
     * Метод проверяющий доступность входного файла и создающий (в случае отсутствия) выходной файл
     *
     * @param sourceFile      тектовая строка представляющая входной файл для обработки
     * @param destinationFile тектовая строка представляющая выходной файл для обработки
     * @param fileA           временный файл A
     * @param fileB           временный файл B
     * @param fileSourceCopy  копия файла источника
     * @return true если удалось получить доступ к входному и выходному файлу
     */
    boolean init(String sourceFile, String destinationFile, String fileA, String fileB, String fileSourceCopy) {
        boolean executeCondition;
        this.sourceFile = new File(sourceFile);
        this.destinationFile = new File(destinationFile);
        this.fileA = new File(fileA);
        this.fileB = new File(fileB);
        this.fileSourceCopy = new File(fileSourceCopy);

        if (this.sourceFile.exists() && this.sourceFile.isFile()) {
            executeCondition = true;
        } else {
            this.logger.appendLog("Нет входного файла или файл недоступен \n");
            executeCondition = false;
        }

        if (executeCondition) {
            executeCondition = fileCreator(this.destinationFile, this.destinationFile.getName());
        }

        if (executeCondition) {
            executeCondition = fileCreator(this.fileA, this.fileA.getName());
        }

        if (executeCondition) {
            executeCondition = fileCreator(this.fileB, this.fileB.getName());
        }
        if (executeCondition) {
            executeCondition = fileCreator(this.fileSourceCopy, this.fileSourceCopy.getName());
        }

        return executeCondition;
    }

    private boolean fileCreator(File file, String logMessage) {
        boolean result = false;
        try {
            if (file.createNewFile()) {
                result = true;
                this.logger.appendLog(String.format("Файл %s успешно создан \n", logMessage));
            } else {
                if (file.delete()) {
                    this.logger.appendLog(String.format("Старый файл %s удален \n", logMessage));
                    if (file.createNewFile()) {
                        this.logger.appendLog(String.format("Создан новый файл %s \n", logMessage));
                        result = true;
                    } else {
                        this.logger.appendLog(String.format("Создать новый файл %s не удалось \n", logMessage));
                    }
                } else {
                    this.logger.appendLog(String.format("Неудалось удалить старый файл %s \n", logMessage));
                }
            }
        } catch (IOException e) {
            result = false;
            this.logger.appendLog(String.format("Ошибка операции при работе с файлом %s \n", logMessage));
            this.logger.appendLog(e.toString());
        }
        return result;
    }

    /**
     * Создание объекта File для входного файла (файла источника)
     *
     * @return объект  File
     */
    File getSourceFile() {
        return this.sourceFile;
    }

    /**
     * Создание объекта File для временного файла А
     *
     * @return объект  File
     */

    File getFileB() {
        return this.fileB;
    }

    /**
     * Создание объекта File для временного файла B
     *
     * @return объект  File
     */

    File getFileSourceCopy() {
        return this.fileSourceCopy;
    }

    /**
     * Создание объекта File для временного файла B
     *
     * @return объект  File
     */

    File getFileA() {
        return this.fileA;
    }


    /**
     * Создание объекта File для выходного файла (файла приемника)
     *
     * @return объект  File
     */


    File getDestinationFile() {
        return this.destinationFile;
    }


    /**
     * Метод добавления логгера в класс
     *
     * @param logger обхект релизующий общий интерфейс Logging
     */
    void setLogger(Logging logger) {
        this.logger = logger;
    }
}
