package ru.tikhonov.term1.fileOperations;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 * Более быстрый метод сортировки с методом слияния
 *
 * @author Sergey Tikhonov
 */
class FileSortFast implements Sorting {
    private RandomAccessFile rS;
    private RandomAccessFile rD;
    private Logging logger;
    private RandomAccessFile rA;
    private RandomAccessFile rB;
    private RandomAccessFile rC;
    private File fileA, fileB, fileCopy;
    private long iterationCount = 0;

    @Override
    public boolean sort(File source, File distance) {
        boolean result;
        try {
            this.init(source, distance);
            makeSeries(this.rS, 1);
            fusionSeries();
            makeSeries(this.rC, 2);
            fusionSeries();
////            makeSeries(this.rC, 3);
////            fusionSeries();
            makeSeries(this.rC, 4);
            fusionSeries();
            makeSeries(this.rC, 8);
            fusionSeries();
            this.logger.appendLog(String.format("Начало сортировки: %te%n", new Date()));
            result = true;
        } catch (IOException e) {
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
            try {
                this.rA.close();
            } catch (IOException e) {
                this.logger.appendLog(String.format("Ошибка закрытия файла %s%n", fileA.getName()));
                this.logger.appendLog(e.toString());
            }
            try {
                this.rB.close();
            } catch (IOException e) {
                this.logger.appendLog(String.format("Ошибка закрытия файла %s%n", fileB.getName()));
                this.logger.appendLog(e.toString());
            }
            try {
                this.rC.close();
            } catch (IOException e) {
                this.logger.appendLog(String.format("Ошибка закрытия файла %s%n", fileCopy.getName()));
                this.logger.appendLog(e.toString());
            }
        }
        return result;
    }

    /**
     * Инициализация всех бъектов RandomAccessFile
     *
     * @param source   неотсортированный файл источник
     * @param distance отсортированный файл назначения
     * @throws IOException
     */
    private void init(File source, File distance) throws IOException {
        this.rS = new RandomAccessFile(source, "r");
        this.rD = new RandomAccessFile(distance, "rw");
        this.rA = new RandomAccessFile(this.fileA, "rw");
        this.rB = new RandomAccessFile(this.fileB, "rw");
        this.rC = new RandomAccessFile(this.fileCopy, "rw");
    }

    /**
     * Метод добавления логгера в класс
     *
     * @param logger обхект релизующий общий интерфейс Logging
     */
    void setLogger(Logging logger) {
        this.logger = logger;
    }

    /**
     * Добавление дополнителных служебных файлов к классу
     *
     * @param fileA    веменный файл Ф
     * @param fileB    временный файл B
     * @param fileCopy временный файл копия
     */
    void setAdditionalFiles(File fileA, File fileB, File fileCopy) {
        this.fileA = fileA;
        this.fileB = fileB;
        this.fileCopy = fileCopy;
    }
    /**
     * Разбивание на серии
     *
     * @param rAF временный файл
     * @param p
     * @throws IOException
     */
    private void makeSeries(RandomAccessFile rAF, long p) throws IOException {
        boolean isOdd = true;
        this.rA.setLength(0);
        this.rB.setLength(0);
        StringBuilder bufferA = new StringBuilder();
        StringBuilder bufferB = new StringBuilder();
        while (rAF.getFilePointer() != rAF.length()) {
            if (p == 1) this.iterationCount++;
            if (isOdd) {
                for (int j = 0; j < p; j++) {
                    if (rAF.getFilePointer() != rAF.length())
                        bufferA.append(String.format("%s%n", rAF.readLine()));
                }
                this.rA.writeBytes(bufferA.toString());
                isOdd = false;
                bufferB.setLength(0);
                bufferA.setLength(0);
                continue;
            }
            if (!isOdd) {
                for (int j = 0; j < p; j++) {
                    if (rAF.getFilePointer() != rAF.length())
                        bufferB.append(String.format("%s%n", rAF.readLine()));
                }
                this.rB.writeBytes(bufferB.toString());
                isOdd = true;
                bufferB.setLength(0);
                bufferA.setLength(0);
            }
        }
        this.rA.seek(0);
        this.rB.seek(0);
        rAF.seek(0);
    }

    private void fusionSeries() throws IOException {
        int isOdd = (int) this.iterationCount % 2;
        this.rC.setLength(0);
        long fusionIterCount = this.rA.length();
        StringBuilder bufferA = new StringBuilder();
        StringBuilder bufferB = new StringBuilder();
        for (int i = 0; i < fusionIterCount; i++) {
            if (this.rA.getFilePointer() != this.rA.length()) {
                bufferA.append(String.format("%s%n", this.rA.readLine()));
            }
            if (this.rB.getFilePointer() != this.rB.length()) {
                bufferB.append(String.format("%s%n", this.rB.readLine()));
            }
            if (bufferA.length() < bufferB.length()) {
                this.rC.writeBytes(bufferA.toString());
                this.rC.writeBytes(bufferB.toString());
            } else {
                this.rC.writeBytes(bufferB.toString());
                this.rC.writeBytes(bufferA.toString());
            }
            bufferA.setLength(0);
            bufferB.setLength(0);
        }
        this.rC.seek(0);
        this.rB.seek(0);
        this.rA.seek(0);
    }
}