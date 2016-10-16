package ru.stikhonov.term4;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Класс реализует ввод данных с консоли, а так же проверяет корркетность введеных данных
 *
 * @author Sergey Tikhonov
 */
class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод для ввода даты c консоли
     *
     * @return если дата введена корректно, возрващает обхект типа Date, иначе null
     */
    @Override
    public Date dateEntry() {
        return this.dateParse(this.scanner.nextLine());
    }

    /**
     * Метод для ввода числового значения int с консоли
     *
     * @return возвращает числовое значение типа int, если данные введены корректно, иначе (-1)
     */
    @Override
    public int intEntry() {

        return this.intParse(this.scanner.nextLine());
    }

    /**
     * Метод для ввода строкового значения с консоли
     *
     * @return возвращает строковое значени
     */
    @Override
    public String stringEntry() {
        return this.stringParse(this.scanner.nextLine());
    }

    /**
     * Метод служит для приостановки выполнения программы до нажатия клавиши Enter (в консоли)
     */
    @Override
    public boolean anyKeyEntry() {
        return this.anyKeyParse(this.scanner.nextLine());
    }

    /**
     * Метод парсинга даты из строки
     *
     * @param s строковый параметр для парсинга даты
     * @return если дата введена корректно, возрващает обхект типа Date, иначе null
     */

    Date dateParse(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            //TODO можно ввести логирование, вывод предупреждающей информации в консоль не делал, класс предназначен исключительно для возвращения требуемого значения
            return null;
        }
    }

    /**
     * Метод парсинга строкового значения
     *
     * @param s строковый параметр для возможных дейстивй со строкоый и вывод ее в качестве значения return
     * @return возвращает строковое значение
     */
    String stringParse(String s) {

        return s;
    }

    /**
     * Метод парсинга числового значения в значение типа int
     *
     * @param s строковый параметр для парсинга строки в числовое значение меню
     * @return возвращает числовое значение типа int, если данные введены корректно, иначе (-1)
     */
    int intParse(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            //TODO можно ввести логирование, вывод предупреждающей информации в консоль не делал, класс предназначен исключительно для возвращения требуемого значения
            return -1;
        }
    }

    /**
     * Метод парсит стрововое значение и ищет в нем символ перевода строки в бесконечном цикле
     *
     * @param s строковый параметр содержащий символ перевода строки
     */
    boolean anyKeyParse(String s) {
        do {

        } while (s == "/n");
        return true;
    }

    /**
     * Метод закрывает объект Scanner связанный с потоком System.in (сам поток тоже закрывается)
     *
    */
    void closeInput() {
           this.scanner.close();
    }
}

