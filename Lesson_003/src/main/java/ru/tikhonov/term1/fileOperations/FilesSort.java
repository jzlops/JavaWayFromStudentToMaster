package ru.tikhonov.term1.fileOperations;

import java.io.*;

/**
 * Класс сортировки строк из входного файа в выходной файл. Строки сортируются в порядке возрастания.
 * <p>
 * ВНИМАНИЕ:
 * Максимальная длинна строки в входном файле 2^31 байт
 * Максимальное количество строк - неограниченно
 * Максимальный размер фала - "неограничен"
 * <p>
 * Сортировка работает медленно, так как используется огромное количество итераций обращения к входному файлу
 * Возможные улучшения - создание файла индекса, содержащего длины строк или длины строк и позиции во входном файле.
 * <p>
 * Возможные улучшения класса
 *
 * @author Sergey Tikhonov
 */
class FilesSort implements Sorting {
    private long rsFileLength = 0;
    private int rsMinLineLength = Integer.MAX_VALUE;
    private int rsMaxLineLength = -1;
    private long rsLineCount = 0;
    private long iterCount = 0;
    private RandomAccessFile rs;
    private RandomAccessFile rd;

    @Override
    public boolean sort(File source, File distance) {
        boolean result;
        try {
            this.init(source, distance);
            for (int workLineLength = this.rsMinLineLength; workLineLength <= this.rsMaxLineLength; workLineLength++) {
                this.writeToDistance(workLineLength);
                System.out.printf("*");
            }
            this.printInfo();
            result = true;
        } catch (Exception e) {
            System.out.printf("Не удалось произвести сортировку %n");
            e.printStackTrace();
            result = false;
        } finally {
            try {
                this.rs.close();
            } catch (IOException e) {
                System.out.printf("Не удалось закрыть входной файл %n");
                e.printStackTrace();
            }
            try {
                this.rd.close();
            } catch (IOException e) {
                System.out.printf("Не удалось закрыть выходной файл %n");
                e.printStackTrace();
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
        while (this.rs.getFilePointer() != this.rs.length()) {
            this.iterCount++;
            buffer.append(rs.readLine());
            tempLineLength = buffer.length();
            if (tempLineLength == workLineLength) {
                buffer.append("\r\n");
                this.rd.writeBytes(buffer.toString());
            }
            buffer.setLength(0);
        }
        rs.seek(0);
    }

    /**
     * Инициализация внутренних переменных
     *
     * @param source   входной обхект File
     * @param distance выходной обхект File
     * @throws IOException
     */
    private void init(File source, File distance) throws IOException {
        System.out.printf("Начата обработка входного файла, ждите... %n");
        this.rs = new RandomAccessFile(source, "r");
        this.rd = new RandomAccessFile(distance, "rw");
        this.rsFileLength = rs.length();
        initMinMaxLineLength(this.rs);
    }

    /**
     * Вывод на консоль информации и параметрах входного файла и прочей служебной информации о проделанной работе
     */
    private void printInfo() {
        System.out.printf("Размер входного файла в байтах - %1$d %n", this.rsFileLength);
        System.out.printf("Максимальная длинна строки во входном файле - %1$d %n", this.rsMaxLineLength);
        System.out.printf("Минимальная длинна строки во входном файле - %1$d %n", this.rsMinLineLength);
        System.out.printf("Количество строк - %1$d %n", this.rsLineCount);
        System.out.printf("Количество итераций по входному файлу - %1$d %n", this.iterCount);
    }
}
