package ru.tikhonov.term1.exam;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Класс для логирования результатов в файл
 *
 * @author Sergey Tikhonov
 */
class Logger {
    private RandomAccessFile logFile;
    private StringBuilder logBuffer = new StringBuilder();

    public boolean init(String logFileName) {
        boolean result = true;
        File file = new File(logFileName);
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
            } catch (Exception e) {
                System.out.printf("LogFile is exist, but it is not possible to delete it %s", System.getProperty("line.separator"));
                result = false;
            }
        }
        if (result) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.printf("Creation logFile is unsuccessful %s", System.getProperty("line.separator"));
                result = false;
            }
        }

        if (result) {
            try {
                RandomAccessFile logFile = new RandomAccessFile(file, "rw");
                this.logFile = logFile;
            } catch (IOException e) {
                System.out.printf("Opening logFile in READ/WRITE mode is unsuccessful %s", System.getProperty("line.separator"));
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    void appendLog(String comment, String message) {
        this.logBuffer.append(comment).append(System.getProperty("line.separator"));
        this.logBuffer.append(message).append(System.getProperty("line.separator"));
        this.logBuffer.append(System.getProperty("line.separator"));
        try {
            this.logFile.write(this.logBuffer.toString().getBytes());
        } catch (IOException e) {
            System.out.printf("Error adding information to logFile %s", System.getProperty("line.separator"));
            e.printStackTrace();
        }
        logBuffer.setLength(0);
    }

    public void close() {
        try {
            this.logFile.close();
        } catch (IOException e) {
            System.out.printf("Error closing logFile %s", System.getProperty("line.separator"));
            e.printStackTrace();
        }
    }
}


