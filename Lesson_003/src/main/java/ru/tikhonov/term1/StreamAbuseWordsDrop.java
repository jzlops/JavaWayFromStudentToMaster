package ru.tikhonov.term1;

import java.io.*;

/**
 * Класс обработки входящих потоков
 *
 * @author Sergey Tikhonov
 */
class StreamAbuseWordsDrop {
    /**
     * Метод переносит байты из входящего потока в исходящий, фильтруя словаиз переданного массива строк
     *
     * @param in    входящий поток
     * @param out   исходящий поток
     * @param abuse массив слов для фильтрации из входящего потока
     * @throws IOException
     */
    void abuseWordsDrop(InputStream in, OutputStream out, String[] abuse) throws IOException {
        final int MAX_READ_CHUNK;
        MAX_READ_CHUNK = getAbuseMaxLength(abuse);

        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(out));

        char[] tempCharArray = new char[MAX_READ_CHUNK];
        StringBuffer buffer = new StringBuffer();
        boolean fit = false;
        int readChars;

        while (!exitCondition(bin)) {

            for (String abuseWord : abuse) {
                bin.mark(MAX_READ_CHUNK);
                readChars = bin.read(tempCharArray, 0, abuseWord.length());
                if (readChars < abuseWord.length()) {
                    bin.reset();
                    continue;
                }
                charArrayToStringBuffer(buffer, tempCharArray, abuseWord.length());
                if (abuseWord.equals(buffer.toString())) {
                    fit = true;
                    zeroBuffer(buffer);
                    break;
                }
                bin.reset();
                zeroBuffer(buffer);
            }
            if (!fit) bout.append((char) bin.read());

            fit = false;
            bout.flush();
        }
        bout.flush();
    }

    /**
     * Получение максимальной длинны строки в массиве слов для фильтрации
     *
     * @param abuse массив "плохих" строк
     * @return максимальная длинна строки в массиве
     */
    private int getAbuseMaxLength(String[] abuse) {
        int length = 0;
        for (String s : abuse) {
            if (s.length() > length) {
                length = s.length();
            }
        }
        return length;
    }

    /**
     * Преобразование массива char[] в объект StringBuffer
     *
     * @param buffer объект  StringBuffer
     * @param chars  массив символов
     * @param length величина выборки из массива
     */
    private void charArrayToStringBuffer(StringBuffer buffer, char[] chars, int length) {
        for (int i = 0; i < length; i++) {
            buffer.append(chars[i]);
        }
    }

    /**
     * Обнуление и обрезание бъекта  StringBuffer
     *
     * @param buffer объект  StringBuffer
     */
    private void zeroBuffer(StringBuffer buffer) {
        buffer.delete(0, buffer.length());
        buffer.setLength(0);
    }

    /**
     * Метод проверки того, что мы достигли конца потока
     *
     * @param bin входящий поток BufferedReader
     * @return true если поток закончился
     * @throws IOException
     */
    private boolean exitCondition(BufferedReader bin) throws IOException {
        boolean result = false;
        bin.mark(1);
        if (bin.read() == -1) result = true;
        bin.reset();
        return result;
    }

    ;
}


