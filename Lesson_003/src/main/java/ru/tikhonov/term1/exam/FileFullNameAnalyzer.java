package ru.tikhonov.term1.exam;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Sergey Tikhonov
 */
public class FileFullNameAnalyzer implements FilenameFilter {
    private String filter;
    private StringBuilder allMatchedFiles;

    /**
     * Выставляем шаблон и ключ для фильтрации
     *
     * @param filter шаблон из комманадной строки
     * @param result записываем результаты поиска в объект StringBuilder
     */
    FileFullNameAnalyzer(String filter, StringBuilder result) {
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
            if (name.equals(this.filter)) {
                this.allMatchedFiles.append(supposeFile).append(System.getProperty("line.separator"));
                return false;
            }
        }
        return false;
    }
}