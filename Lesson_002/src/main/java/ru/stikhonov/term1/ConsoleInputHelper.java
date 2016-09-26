package ru.stikhonov.term1;

import javafx.scene.chart.PieChart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Sergey Tikhonov
 */
public class ConsoleInputHelper implements Input {
    @Override
    public Date dateEntry() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            System.out.printf("Неверный формат данных %n");
            return null;
        }
    }

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

    @Override
    public String stringEntry() {
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        return s;
    }

    @Override
    public void anyKeyEntry() {
        String s;
        System.out.printf("Для продолжения - нажмите Enter %n");
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
    }


}
