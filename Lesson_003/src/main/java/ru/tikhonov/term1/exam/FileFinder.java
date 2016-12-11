package ru.tikhonov.term1.exam;


import java.io.File;

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
    private FilesAnalyzer filesFilter = new FilesAnalyzer();

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
        if (!validateAndInit()) {
            return;
        }
        filesFilter.setParameters(this.filePattern, this.key, this.allMatchedFiles);
        scan(this.parentDir);
        System.out.printf(this.allMatchedFiles.toString());
        logger.appendLog("Request result:", this.allMatchedFiles.toString());
        logger.close();
    }

    /**
     * Метод рекурсивно пробегающий по всем папкам и подпапкам в поиске файлв по заданому шаблону
     *
     * @param dir директория для анализа
     */
    private void scan(File dir) {
        for (File currentDir : dir.listFiles(this.filesFilter)) {
            this.scan(currentDir);
        }
    }

    /**
     * Основной парсер аргументов коммандной строки
     *
     * @return true если все аргументы введены корректно, иначе false
     */
    private boolean validateAndInit() {
        if ((this.args.length == 1) && (this.args[0]).toLowerCase().equals("-help")) {
            printHelp();
            return false;
        }

        this.logger = new Logger();
        if (!this.logger.init(this.args[6])) {
            printError(6);
            return false;
        }
        this.logger.appendLog("Input arguments:", String.format("%s %s %s %s %s %s %s ", this.args[0], this.args[1], this.args[2], this.args[3], this.args[4], this.args[5], this.args[6]));


        if (this.args.length < 7) {
            printError(8);
            return false;
        }
        if (!this.args[0].toLowerCase().equals("-d")) {
            printError(0);
            return false;
        }
        if (!this.args[2].toLowerCase().equals("-n")) {
            printError(2);
            return false;
        }
        if (!this.args[4].toLowerCase().equals("-m")) {
            if (!this.args[4].toLowerCase().equals("-f")) {
                printError(4);
                return false;
            }
        }
        if (!this.args[5].toLowerCase().equals("-o")) {
            printError(5);
            return false;
        }
        File parentDir = new File(this.args[1]);
        if ((!parentDir.exists()) || (!parentDir.isDirectory())) {
            printError(1);
            return false;
        }

        if ((this.args[4].toLowerCase().equals("-m")) && (!this.args[3].toLowerCase().startsWith("*."))) {
            printError(5);
            return false;
        }

        this.parentDir = parentDir;
        this.filePattern = this.args[3].toLowerCase();
        this.key = this.args[4].toLowerCase();
        return true;
    }

    /**
     * Печать справки о программе
     */
    private void printHelp() {
        StringBuilder help = new StringBuilder();
        help.append(System.getProperty("line.separator"));
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
        if (errorCode == 8) {
            error.append("insufficiency arguments").append(System.getProperty("line.separator"));
        }
        if (errorCode == 0) {
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
        if (errorCode == 1) {
            error.append("Directory name is incorrect").append(System.getProperty("line.separator"));
        }
        if (errorCode == 5) {
            error.append("File-mask parameter is incorrect").append(System.getProperty("line.separator"));
        }
        error.append(System.getProperty("line.separator"));
        error.append("Use key -help for assistant").append(System.getProperty("line.separator"));
        error.append(System.getProperty("line.separator"));

        System.out.printf(error.toString());
        this.logger.appendLog("Error message generated:", error.toString());
    }

}
