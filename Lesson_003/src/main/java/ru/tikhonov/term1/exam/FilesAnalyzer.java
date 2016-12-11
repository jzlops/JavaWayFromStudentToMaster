package ru.tikhonov.term1.exam;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Класс фильтрующий папки и файлы
 *
 * @author Sergey Tikhonov
 */
class FilesAnalyzer implements FilenameFilter {
    private String filter;
    private String key;
    private StringBuilder allMatchedFiles;

    /**
     * Выставляем шаблон и ключ для фильтрации
     *
     * @param filter шаблон из комманадной строки
     * @param key    ключ коммандной строки
     * @param result записываем результаты поиска в объект StringBuilder
     */
    void setParameters(String filter, String key, StringBuilder result) {
        this.key = key;
        this.filter = filter;
        this.allMatchedFiles = result;
    }

    /**
     * Фильтр отсеивающий все файлы. Если файл удовлетворяет шаблону, записываем информацию о нем в буфер.
     *
     * @param dir  текущая диретория
     * @param name анализируемый объект на выполнение необходимых условий
     * @return true если анализируемый объект - директория
     */
    @Override
    public boolean accept(File dir, String name) {
        File supposeFile = new File(String.format("%s%s%s", dir, System.getProperty("file.separator"), name));
        if (supposeFile.isDirectory()) {
            return true;
        }
        if (supposeFile.isFile()) {
            if (this.key.equals("-m")) {
                if (name.endsWith(this.filter.substring(1))) {
                    this.allMatchedFiles.append(supposeFile).append(System.getProperty("line.separator"));
                    return false;
                }
            }
            if (this.key.equals("-f")) {
                if (name.equals(this.filter)) {
                    this.allMatchedFiles.append(supposeFile).append(System.getProperty("line.separator"));
                    return false;
                }
            }
        }
        return false;
    }
}
