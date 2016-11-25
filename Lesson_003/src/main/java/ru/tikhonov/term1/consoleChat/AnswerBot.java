package ru.tikhonov.term1.consoleChat;

import java.io.Closeable;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Класс работающий с файлом ответов
 *
 * @author Sergey Tikhonov
 */
public class AnswerBot implements Answerable, Closeable {
    private RandomAccessFile raA;
    private long answersPointer[];

    /**
     * Инициалиация класса объектом типа File, содержащий пусть до файла ответов
     *
     * @param file объект File
     */
    void init(final File file) {
        if (file.exists() && file.isFile()) {
            try {
                this.raA = new RandomAccessFile(file, "r");
                this.answersPointer = fileScan(this.raA);
            } catch (Exception e) {
                System.out.printf("Не удалось прочитать файл ответов");
                e.printStackTrace();
                try {
                    raA.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.printf("Не удалось закрыть файл ответов");
                }
                System.exit(0);
            }
        } else {
            System.out.printf("Не удалось найти файл ответов");
            System.exit(0);
        }
    }

    /**
     * Заверешение работы с файлом
     */
    @Override
    public void close() {
        try {
            this.raA.close();
        } catch (IOException e) {
            System.out.printf("Неудалось закрыть файл ответов");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Отдаем случайную строку из фала ответов
     *
     * @return объект String из фала ответов
     */
    @Override
    public String getAnswer() {
        String line = null;
        int randomIndex;
        Random random = new Random();
        randomIndex = random.nextInt(this.answersPointer.length - 1);
        try {
            this.raA.seek(this.answersPointer[randomIndex]);
            line = this.raA.readLine();
        } catch (IOException e) {
            System.out.printf("Неудалось прочитать из фала ответов строки");
            e.printStackTrace();
            try {
                this.raA.close();
            } catch (IOException e1) {
                System.out.printf("Неудалось закрыть файл ответов");
                e1.printStackTrace();
            }
            System.exit(0);
        }
        return line;
    }

    /**
     * Сканировение файла ответов и инициализация массива с указателями на положение строк в файле
     *
     * @param raA Оъект RandomAccessFile связанный с фалом ответов переданным в методе инициализации
     * @return массв указателей на положение строк в файле
     */
    private long[] fileScan(final RandomAccessFile raA) {
        int i = 0;
        long[] array = null;
        try {
            while (raA.getFilePointer() != raA.length()) {
                raA.readLine();
                i++;
            }
            array = new long[i];
            raA.seek(0);
            i = 0;
            while (raA.getFilePointer() != raA.length()) {
                array[i] = raA.getFilePointer();
                raA.readLine();
                i++;
            }
            raA.seek(0);
        } catch (IOException e) {
            System.out.printf("Неудалось прочитать строки из фала ответов");
            e.printStackTrace();
            try {
                raA.close();
            } catch (IOException e1) {
                System.out.printf("Неудалось закрыть файл ответов");
                e1.printStackTrace();
            }
            System.exit(0);
        }
        return array;
    }


}
