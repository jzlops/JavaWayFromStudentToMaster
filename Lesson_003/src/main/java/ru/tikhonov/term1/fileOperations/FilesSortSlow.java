package ru.tikhonov.term1.fileOperations;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 * Класс сортировки строк из входного файа в выходной файл. Строки сортируются в порядке возрастания.
 * <p>
 * ВНИМАНИЕ:
 * Максимальная длинна строки в входном файле 2^31 байт
 * Максимальное количество строк - неограниченно
 * Максимальный размер фала - "неограничен"
 * <p>
 * Возможные улучшения класса
 * Сортировка работает медленно, так как используется огромное количество итераций обращения к входному файлу
 * Возможные улучшения - создание файла индекса, содержащего длины строк или длины строк и позиции во входном файле.
 * <p>
 *
 * @author Sergey Tikhonov
 */
class FilesSortSlow implements Sorting {
    private long rsFileLength = 0;
    private int rsMinLineLength = Integer.MAX_VALUE;
    private int rsMaxLineLength = -1;
    private long rsLineCount = 0;
    private long iterationCount = 0;
    private RandomAccessFile rS;
    private RandomAccessFile rD;
    private Logging logger;


    @Override
    public boolean sort(File source, File distance) {
        boolean result;
        try {
            this.init(source, distance);
            this.logger.appendLog(String.format("Начало сортировки: %te%n", new Date()));
            for (int workLineLength = this.rsMinLineLength; workLineLength <= this.rsMaxLineLength; workLineLength++) {
                this.writeToDistance(workLineLength);
            }
            this.logger.appendLog(String.format("Конец сортировки: %te%n", new Date()));
            this.logJobInfo();
            result = true;
        } catch (Exception e) {
            this.logger.appendLog("Не удалось произвести сортировку %n");
            this.logger.appendLog(e.toString());
            result = false;
        } finally {
            try {
                this.rS.close();
            } catch (IOException e) {
                this.logger.appendLog(String.format("Ошибка закрытия файла %s%n", source.getName()));
                this.logger.appendLog(e.toString());
            }
            try {
                this.rD.close();
            } catch (IOException e) {
                this.logger.appendLog(String.format("Ошибка закрытия файла %s%n", distance.getName()));
                this.logger.appendLog(e.toString());
            }
        }
        return result;
    }

    /**
     * Метод единоды обходит входной файл и инициализирует переменные, макимальной и минимальной длинной строки
     *
     * @param rs объект RandomAccessFile содежащий сущность входного файла
     * @throws IOException
     */
    private void initMinMaxLineLength(RandomAccessFile rs) throws IOException {
        int tempLineLength;
        while (rs.getFilePointer() != rs.length()) {
            tempLineLength = rs.readLine().length();
            if (this.rsMinLineLength > tempLineLength) {
                this.rsMinLineLength = tempLineLength;
            }
            if (this.rsMaxLineLength < tempLineLength) {
                this.rsMaxLineLength = tempLineLength;
            }
            this.rsLineCount++;
        }
        rs.seek(0);
    }

    /**
     * Метод для записи строк в выходной файл (обходит входной файл, находит строки определенной длинны и записывает их в выходной файл)
     *
     * @param workLineLength длинна строки для сравнения со строкой взятой из входного файла
     * @throws IOException
     */
    private void writeToDistance(int workLineLength) throws IOException {
        int tempLineLength;
        StringBuilder buffer = new StringBuilder();
        while (this.rS.getFilePointer() != this.rS.length()) {
            this.iterationCount++;
            buffer.append(rS.readLine());
            tempLineLength = buffer.length();
            if (tempLineLength == workLineLength) {
                buffer.append("\r\n");
                this.rD.writeBytes(buffer.toString());
            }
            buffer.setLength(0);
        }
        rS.seek(0);
    }

    /**
     * Инициализация внутренних переменных
     *
     * @param source   входной обхект File
     * @param distance выходной обхект File
     * @throws IOException
     */
    private void init(File source, File distance) throws IOException {
        this.rS = new RandomAccessFile(source, "r");
        this.rD = new RandomAccessFile(distance, "rw");
        this.rsFileLength = rS.length();
        initMinMaxLineLength(this.rS);
    }

    private void logJobInfo() {
        this.logger.appendLog(String.format("Размер входного файла в байтах  - %1$d  %n", this.rsFileLength));
        this.logger.appendLog(String.format("Максимальная длинна строки во входном файле - %d%n", this.rsMaxLineLength));
        this.logger.appendLog(String.format("Минимальная длинна строки во входном файле - %d%n", this.rsMinLineLength));
        this.logger.appendLog(String.format("Количество строк - %d%n", this.rsLineCount));
        this.logger.appendLog(String.format("Количество итераций по входному файлу - %d%n ", this.iterationCount));
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
