package ru.tikhonov.term1.exam;


import java.io.File;
import java.io.FilenameFilter;

/**
 * Класс реализующий поиск файла по заданому шаблону
 *
 * @author Sergey Tikhonov
 */
class FileFinder {
    private File parentDir;
    private String filePattern;
    private Logger logger;
    private String[] args;
    private String key;
    private StringBuilder allMatchedFiles = new StringBuilder();
    private FilenameFilter fileNameFilter;

    /**
     * Конструктор принимающий массив параметров из коммандной строки
     *
     * @param args - массив параметров коммандной строки
     */
    FileFinder(String[] args) {
        this.args = args;
    }

    /**
     * Основной метод класса, для поиска файла и вывода информации на консоль и лог-файл
     */
    void start() {
        int validateState;
        Validate validate = new Validate();
        Logger logger = new Logger();
        this.logger = logger;
        validate.setLogger(this.logger);

        validateState = validate.isCorrect(this.args);

        if (validateState == -1) {
            printHelp();
            return;
        }
        if (validateState > 0) {
            printError(validateState);
            return;
        }
        /*Инициализация переменных*/
        init();
        /*Поиск файла*/
        scan(this.parentDir);
        /*Вывод результато в консоль*/
        System.out.printf(this.allMatchedFiles.toString());
        /*Запись в лог файл*/
        logger.appendLog("Request result:", this.allMatchedFiles.toString());
        logger.close();
    }

    /**
     * Инициализация внуцтренних переменных, введенными аргументами
     */

    private void init() {
        this.parentDir = new File(this.args[1]);
        this.filePattern = this.args[3].toLowerCase();
        this.key = this.args[4].toLowerCase();
        if (this.key.equals("-m")) {
            this.fileNameFilter = new FilesMaskAnalyzer(this.filePattern, this.allMatchedFiles);
        }
        if (this.key.equals("-f")) {
            this.fileNameFilter = new FileFullNameAnalyzer(this.filePattern, this.allMatchedFiles);
        }
        if (this.key.equals("-r")) {
            this.fileNameFilter = new FileRegexAnalyzer(this.filePattern, this.allMatchedFiles);
        }
    }

    /**
     * Метод рекурсивно пробегающий по всем папкам и подпапкам в поиске файлв по заданому шаблону
     *
     * @param dir директория для анализа
     */
    private void scan(File dir) {
        for (File currentDir : dir.listFiles(this.fileNameFilter)) {
            this.scan(currentDir);
        }
    }

    /**
     * Печать справки о программе
     */
    private void printHelp() {
        StringBuilder help = new StringBuilder();
        help.append("Help:").append(System.getProperty("line.separator"));
        help.append(" key -d for define root dir").append(System.getProperty("line.separator"));
        help.append(" key -n for define full filename, file-mask or regular expression").append(System.getProperty("line.separator"));
        help.append(" key -m for mask pattern ").append(System.getProperty("line.separator"));
        help.append(" key -f for full filename pattern ").append(System.getProperty("line.separator"));
        help.append(" key -r for regular expression pattern ").append(System.getProperty("line.separator"));
        help.append(" key -O for define log filename ").append(System.getProperty("line.separator"));
        help.append(" Example: -d c:/ -n *.txt -m -o log.txt").append(System.getProperty("line.separator"));
        help.append(System.getProperty("line.separator"));
        System.out.printf(help.toString());
    }

    /**
     * Печать сообщения об ошибке в консоль и лог-файл
     *
     * @param errorCode код ошибки, зависит от позиции неверного ключа коммандной строки
     */
    private void printError(int errorCode) {
        StringBuilder error = new StringBuilder();
        error.append("Syntax error").append(System.getProperty("line.separator"));
        if (errorCode == 7) {
            error.append("insufficiency arguments").append(System.getProperty("line.separator"));
        }
        if (errorCode == 6) {
            error.append("Logfile name is incorrect").append(System.getProperty("line.separator"));
        }
        if (errorCode == 1) {
            error.append("Uncorrected key -d").append(System.getProperty("line.separator"));
        }
        if (errorCode == 2) {
            error.append("Uncorrected key -n").append(System.getProperty("line.separator"));
        }
        if (errorCode == 4) {
            error.append("Uncorrected key -n, -f or -r").append(System.getProperty("line.separator"));
        }
        if (errorCode == 5) {
            error.append("Uncorrected key -o").append(System.getProperty("line.separator"));
        }
        if (errorCode == 3) {
            error.append("Directory name is incorrect").append(System.getProperty("line.separator"));
        }
        if (errorCode == 8) {
            error.append("File-mask parameter is incorrect").append(System.getProperty("line.separator"));
        }
        error.append(System.getProperty("line.separator"));
        error.append("Use key -help for assistant").append(System.getProperty("line.separator"));
        error.append(System.getProperty("line.separator"));
        System.out.printf(error.toString());

        if ((errorCode == 99) || (errorCode == 7)) {
            return;
        }
        this.logger.appendLog("Error message generated:", error.toString());
    }

}


