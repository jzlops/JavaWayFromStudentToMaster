package ru.tikhonov.term1.consoleChat;

import java.io.*;

/**
 * Класс для работф с файлом лога
 *
 * @author Sergey Tikhonov
 */
class LoggerBot implements Printable, Closeable {
    private RandomAccessFile raL;
    private StringBuilder buffer = new StringBuilder();

    /**
     * Инициализация класса объектов File, содержащим путь до файла логов
     *
     * @param file объект File
     */
    void init(final File file) {
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
            } catch (Exception e) {
                System.out.printf("Файл лога уже существует и удалить его не удалось");
                e.printStackTrace();
                System.exit(0);
            }
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.printf("Не  удалось создать логфайл %n");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            this.raL = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            System.out.printf("Не удалось открыть файл в режиме чтение/запись %n");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Печать полученных сообщений в файл лога, через объект RandomAccessFile
     *
     * @param msg объект String
     */
    @Override
    public void print(String msg) {
        this.buffer.append(String.format("%s%n", msg.toString()));
        try {
            this.raL.writeBytes(this.buffer.toString());
            this.buffer.setLength(0);
        } catch (IOException e) {
            System.out.printf("Неудалось записать сообщение в логфайл");
            e.printStackTrace();
            try {
                raL.close();
            } catch (IOException e1) {
                System.out.printf("Неудалось закрыть файл логовов");
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }

    /**
     * Закрытие файла
     */
    @Override
    public void close() {
        try {
            this.raL.close();
        } catch (IOException e) {
            System.out.printf("Неудалось закрыть логфайл");
            e.printStackTrace();
            System.exit(0);
        }
    }

}
