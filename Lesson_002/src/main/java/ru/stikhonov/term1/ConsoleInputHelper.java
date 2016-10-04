package ru.stikhonov.term1;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Класс реализует ввод данных с консоли, а так же проверяет корркетность введеных данных
 *
 * @author Sergey Tikhonov
 */
class ConsoleInputHelper implements Input {
    /**
     * Метод для ввода даты
     *
     * @return если дата введена корректно, возрващает обхект типа Date, иначе null
     */
    @Override
    public Date dateEntry() {
        Scanner scanner = new Scanner(System.in);
        return this.dateEntry(scanner.nextLine());
    }

    /**
     * Метод для ввода числового значения int
     *
     * @return возвращает числовое значение типа int, если данные введены корректно, иначе (-1)
     */
    @Override
    public int intEntry() {
        Scanner scanner = new Scanner(System.in);
        return this.intEntry(scanner.nextLine());
    }

    /**
     * Метод для ввода строкового значения
     *
     * @return возвращает строковое значени
     */
    @Override
    public String stringEntry() {
        Scanner scanner = new Scanner(System.in);
        return this.stringEntry(scanner.nextLine());
    }

    /**
     * Метод служит для приостановки выполнения программы до нажатия клавиши Enter
     */
    @Override
    public boolean anyKeyEntry() {
        Scanner scanner = new Scanner(System.in);
        return this.anyKeyEntry(scanner.nextLine());
    }

    /**
     * Метод для ввода даты
     *
     * @param s строковый параметр для парсинга даты
     * @return если дата введена корректно, возрващает обхект типа Date, иначе null
     */

    public Date dateEntry(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {

            return null;
        }
    }

    /**
     * Метод для ввода строкового значения
     *
     * @param s строковый параметр для возможных дейстивй со строкоый и вывод ее в качестве значения return
     * @return возвращает строковое значение
     */
    public String stringEntry(String s) {

        return s;
    }

    /**
     * Метод для ввода числового значения int
     *
     * @param s строковый параметр для парсинга строки в числовое значение меню
     * @return возвращает числовое значение типа int, если данные введены корректно, иначе (-1)
     */
    public int intEntry(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Метод служит для приостановки выполнения программы до нажатия клавиши Enter (перевода строки)
     *
     * @param s строковый параметр содержащий символ перевода строки
     */
    public boolean anyKeyEntry(String s) {
        do {

        } while (s == "/n");
        return true;
    }

}

