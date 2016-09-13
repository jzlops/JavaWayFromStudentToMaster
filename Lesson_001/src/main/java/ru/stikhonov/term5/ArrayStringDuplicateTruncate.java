package ru.stikhonov.term5;

import java.util.Arrays;

/**
 * Created by Sergey Tikhonov on 12.09.2016.
 */
public class ArrayStringDuplicateTruncate {
    /**
     * Метод удаляет дубликаты строк из массива String[]
     *
     * @param strings входной параметр - массив String[]
     * @return возвращает ссылку на новый массив String[] без дубликатов
     */

    public String[] duplicateStringKill(String[] strings) {
        int fitCount = 0;

        for (int i = 0; i <= strings.length - 1; i++) {
            for (int k = i + 1; k <= strings.length - 1; k++) {
                if (strings[i] != null && strings[k] != null && strings[i].equals(strings[k])) {
                    strings[k] = null;
                    fitCount++;
                }
            }
        }
        for (int i = strings.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (strings[j] == null) {
                    strings[j] = strings[j + 1];
                    strings[j + 1] = null;
                }
            }
        }
        return Arrays.copyOf(strings, strings.length - fitCount);
    }
}
