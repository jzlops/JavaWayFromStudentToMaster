package ru.stikhonov.term5;

/**
 * Created by Sergey Tikhonov on 12.09.2016.
 */
public class ArrayStringDuplicateTruncate {
    /**
     * Метод удаляет дубликаты строк из массива String[]
     *
     * @param strings вхоодной параметр - массив String[]
     * @return возвращает ссылку на новый массив String[] без дубликатов
     */
    public String[] duplicateStringKill(String[] strings) {
        int fillArrayCounter = 0;
        int duplicateCount = 0;
        final String EMPTY = "EMPTY";
        String[] tempStrings;
        for (int i = 0; i < strings.length; i++)
            if (!(strings[i].equals(EMPTY)))
                for (int j = i; j < strings.length - 1; j++)
                    if (strings[i].equals(strings[j + 1])) {
                        duplicateCount++;
                        strings[j + 1] = EMPTY;
                        j++;
                    }

        tempStrings = new String[strings.length - duplicateCount];

        for (String s : strings)
            if (!(s.equals(EMPTY))) {
                tempStrings[fillArrayCounter] = s;
                fillArrayCounter++;
            }
        System.out.printf("Удаление дубликатов строкового массива выполнено %n%n");
        return tempStrings;
    }
}
