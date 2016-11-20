package ru.tikhonov.term1.palindromeDetector;

import java.util.Scanner;

/**
 * Реализация интерфейса InputOutput для ввода/вывода в консоль
 *
 * @author Sergey Tikhonov
 */
class InOut implements InputOutput {
    /**
     * Ввод с консоли
     *
     * @return возвращенное слово с консоли в нижнем регистре
     */
    @Override
    public String in() {
        String returnString;
        System.out.printf("Введите слово:" + "\n");
        Scanner sc = new Scanner(System.in);
        returnString = sc.nextLine();
        sc.close();
        return returnString;
    }

    /**
     * Вызов метода toString у переданных обхектов и передачи информации на консоль
     *
     * @param object любой обхект
     */
    @Override
    public void out(Object object) {
        System.out.printf(object.toString() + "\n");
    }
}
