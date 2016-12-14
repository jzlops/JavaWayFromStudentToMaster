package ru.tikhonov.term1.exam;

import java.io.File;

/**
 * Класс отвечающий за проверку корректности введеных данных
 *
 * @author Sergey Tikhonov
 */
class Validate {
    private Logger logger;

    /**
     * Устанавливаем логгер в парсер
     *
     * @param logger Логгер
     */
    void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Основной парсер аргументов коммандной строки
     *
     * @return код ошибки, 0 - если нет ошибок
     */
    int isCorrect(String args[]) {
        if ((args.length == 1) && (args[0].toLowerCase().equals("-help"))) {
            return -1;
        }
        if (args.length < 7) {
            return 7;
        }

        if (!this.logger.init(args[6])) {
            return 99;
        }
        this.logger.appendLog("Input arguments:", String.format("%s %s %s %s %s %s %s ", args[0], args[1], args[2], args[3], args[4], args[5], args[6]));

        if (!args[0].toLowerCase().equals("-d")) {
            return 1;
        }
        if (!args[2].toLowerCase().equals("-n")) {
            return 2;
        }
        if (!args[4].toLowerCase().equals("-m")) {
            if (!args[4].toLowerCase().equals("-f")) {
                if (!args[4].toLowerCase().equals("-r"))
                    return 4;
            }
        }
        if (!args[5].toLowerCase().equals("-o")) {
            return 5;
        }
        File parentDir = new File(args[1]);
        if ((!parentDir.exists()) || (!parentDir.isDirectory())) {
            return 3;
        }
        if ((args[4].toLowerCase().equals("-m")) && (!args[3].toLowerCase().startsWith("*."))) {
            return 8;
        }
        return 0;
    }
}
