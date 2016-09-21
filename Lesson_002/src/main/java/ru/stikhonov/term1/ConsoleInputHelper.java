package ru.stikhonov.term1;

import javafx.scene.chart.PieChart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Sergey Tikhonov
 */
public class ConsoleInputHelper {

    Date dateEntry() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        try {
            return simpleDateFormat.parse(s.toString());

        } catch (ParseException e) {
            System.out.printf("Неверный формат данных %n");
            return null;
        }
    }

    int intEntry() {
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    String stringEntry() {
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        return s;
    }

    void anyKeyEntry() {
        String s;
        System.out.printf("Для продолжения - нажмите Enter %n");
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
    }

    void borderGenerator(String s) {
        for (int i = 0; i < 50; i++) {
            System.out.printf(s);
        }
        System.out.printf("%n");
    }


}
