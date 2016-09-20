package ru.stikhonov.term1;

import java.util.Scanner;

/**
 * @author Sergey Tikhonov
 */
public class ConsoleInputChecker {

    int MenuChoice() {
        String s;
        Scanner scanner = new Scanner(System.in);
        s = scanner.nextLine();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
