package ru.tikhonov.term1.fileOperations;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Date;

/**
 * Более быстрый метод сортировки с методом естественного слияния.
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
    private boolean isNotEnd = true;
    private long iterCount = 0;

    @Override
    public boolean sort(final File source, final File distance) {
        boolean result;
        try {
            this.init(source, distance);
            this.logger.appendLog(String.format("Начало сортировки: %s%n", (new Date()).toString()));
            makeSeries(this.rS);

            while (this.isNotEnd)
                makeSeries(this.rC);
            copyFiles(rC, rD);
            this.logger.appendLog(String.format("Конец сортировки: %s%n", (new Date()).toString()));
            this.logger.appendLog(String.format("Количество итераций %d%n",this.iterCount));

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
     * Инициализация всех бъектов RandomAccessFile.
     *
     * @param source   неотсортированный файл источник.
     * @param distance отсортированный файл назначения.
     * @throws IOException
     */
    private void init(final File source, final File distance) throws IOException {
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
    void setLogger(final Logging logger) {
        this.logger = logger;
    }

    /**
     * Добавление дополнителных служебных файлов к классу
     *
     * @param fileA    веменный файл Ф
     * @param fileB    временный файл B
     * @param fileCopy временный файл копия
     */
    void setAdditionalFiles(final File fileA, final File fileB, final File fileCopy) {
        this.fileA = fileA;
        this.fileB = fileB;
        this.fileCopy = fileCopy;
    }

    /**
     * Разбивание файла на серии по алгоритму естественного слияния
     *
     * @param rSource файл для считывания данных, изначально (в первой итерации это файл истоник, потом временный файл)
     * @throws IOException
     */
    private void makeSeries(final RandomAccessFile rSource) throws IOException {
        boolean overShot = false;
        int fusionRun = 0;
        long temp;
        long tempX;
        this.rA.setLength(0);
        this.rB.setLength(0);
        StringBuilder bufferA = new StringBuilder();
        StringBuilder bufferB = new StringBuilder();
        StringBuilder bufferX = new StringBuilder();
        temp = 0;
        while (rSource.getFilePointer() != rSource.length()) {
            this.iterCount++;
            tempX = bufferX.append(rSource.readLine()).length();
            if (tempX == 0) continue;
            if (!overShot) {
                if (temp > tempX) {
                    fusionRun++;
                    if (fusionRun == 2) {
                        fusionSeries();
                    }
                    bufferB.append(bufferX.toString());
                    overShot = true;
                    temp = tempX;
                } else {
                    bufferA.append(bufferX.toString());
                    temp = tempX;
                }
            } else {
                if (temp > tempX) {
                    fusionRun++;
                    if (fusionRun == 2) {
                        fusionSeries();
                    }
                    bufferA.append(bufferX.toString());
                    overShot = false;
                    temp = tempX;
                } else {
                    bufferB.append(bufferX.toString());
                    temp = tempX;
                }
            }
            if (bufferA.length() != 0)
                this.rA.writeBytes(String.format("%s%n", bufferA.toString()));
            if (bufferB.length() != 0)
                this.rB.writeBytes(String.format("%s%n", bufferB.toString()));
            bufferX.setLength(0);
            bufferA.setLength(0);
            bufferB.setLength(0);
            if (fusionRun == 2) fusionRun = 0;
        }

        if (rA.length() >= rSource.length()) {
            this.isNotEnd = false;
        }
        fusionSeries();

        this.rA.setLength(0);
        this.rB.setLength(0);
        rSource.seek(0);
        rD.seek(0);
        copyFiles(this.rD, this.rC);
    }

    /**
     * Слияние сериализованных временных файлов (первых их кусочков) и добалвнеие полученного в файл назначения.
     *
     * @throws IOException
     */
    private void fusionSeries() throws IOException {

        this.rA.seek(0);
        this.rB.seek(0);
        StringBuilder bufferA = new StringBuilder();
        StringBuilder bufferB = new StringBuilder();

        if (rA.getFilePointer() != rA.length())
            bufferA.append(this.rA.readLine());
        if (rB.getFilePointer() != rB.length())
            bufferB.append(this.rB.readLine());

        do {
            if ((bufferA.length() <= bufferB.length()) && (bufferA.length() != 0)) {
                this.rD.writeBytes((String.format("%s%n", bufferA.toString())));
                bufferA.setLength(0);
                if (rA.getFilePointer() != rA.length())
                    bufferA.append(this.rA.readLine());
                continue;
            }
            if ((bufferB.length() < bufferA.length()) && ((bufferB.length() != 0))) {
                this.rD.writeBytes((String.format("%s%n", bufferB.toString())));
                bufferB.setLength(0);
                if (rB.getFilePointer() != rB.length())
                    bufferB.append(this.rB.readLine());
                continue;
            }
            if ((bufferB.length() > 0) && (bufferA.length() == 0)) {
                this.rD.writeBytes((String.format("%s%n", bufferB.toString())));
                bufferB.setLength(0);
                if (rB.getFilePointer() != rB.length())
                    bufferB.append(this.rB.readLine());
                continue;
            }
            if ((bufferA.length() > 0) && ((bufferB.length() == 0))) {
                this.rD.writeBytes((String.format("%s%n", bufferA.toString())));
                bufferA.setLength(0);
                if (rA.getFilePointer() != rA.length())
                    bufferA.append(this.rA.readLine());
            }

        } while ((bufferA.length() + bufferB.length()) != 0);

        bufferA.setLength(0);
        bufferB.setLength(0);

        this.rB.seek(0);
        this.rA.seek(0);
        this.rA.setLength(0);
        this.rB.setLength(0);
    }

    /**
     * Копирование фалов через каналы
     *
     * @param src  объект RandomaccessFile источник
     * @param dest объект RandomaccessFile назначение
     */
    private void copyFiles(final RandomAccessFile src, final RandomAccessFile dest) throws IOException {
        FileChannel rcD, rcS;
        rcD = dest.getChannel();
        rcS = src.getChannel();
        try {
            rcD.transferFrom(rcS, 0, rcS.size());
            dest.seek(0);
            src.setLength(0);
        } catch (Exception e) {
            this.logger.appendLog("Не удалось переместить данные с временного файла в конечный");
            this.logger.appendLog(e.toString());
        }
        dest.setLength(dest.length() - 1L);
    }
}
