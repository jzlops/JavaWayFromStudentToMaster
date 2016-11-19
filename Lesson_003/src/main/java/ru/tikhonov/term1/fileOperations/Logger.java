package ru.tikhonov.term1.fileOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Класс для логирования результатов в файл
 *
 * @author Sergey Tikhonov
 */
class Logger implements Logging {
    private RandomAccessFile logFile;
    private StringBuilder loпBuffer = new StringBuilder();

    @Override
    public boolean init(String logFileName) {
        boolean result = true;
        File file = new File(logFileName);
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
            } catch (Exception e) {
                System.out.printf("Файл лога существует, удалить его не удалось");
                result = false;
            }
        }
        if (result) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.printf("Не удалось создать логфайл %n");
                result = false;
            }
        }

        if (result) {
            try {
                this.logFile = new RandomAccessFile(file, "rw");
            } catch (FileNotFoundException e) {
                System.out.printf("Не удалось открыть файл в режиме чтение/запись %n");
                result = false;
            }
        }

        return result;
    }

    @Override
    public void close() {
        try {
            this.logFile.writeUTF(loпBuffer.toString());
        } catch (IOException e) {
            System.out.printf("Не удалось сохранить данные в файл лога %n");
        } finally {
            try {
                this.logFile.close();
            } catch (IOException e) {
                System.out.printf("Не удалось закрыть файл лога %n");
            }
        }
    }

    @Override
    public void appendLog(String message) {
        this.loпBuffer.append(message);
    }


}
