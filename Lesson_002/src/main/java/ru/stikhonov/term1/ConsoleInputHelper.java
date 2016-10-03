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
public class ConsoleInputHelper implements Input {
    /**
     * Метод для ввода даты
     *
     * @return если дата введена корректно, возрващает обхект типа Date, иначе null
     */
    @Override
    public Date dateEntry() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            //System.out.printf("Неверный формат данных %n");
            return null;
        }
    }

    /**
     * Метод для ввода числового значения int
     *
     * @return возвращает числовое значение типа int, если данные введены корректно, иначе (-1)
     */
    @Override
    public int intEntry() {
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Метод для ввода строкового значения
     *
     * @return возвращает строковое значени
     */
    @Override
    public String stringEntry() {
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        return s;
    }

    /**
     * Метод служит для приостановки выполнения программы до нажатия клавиши Enter
     */
    @Override
    public void anyKeyEntry() {
        String s;

        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        do {

        } while (s == "/n");
    }


}
