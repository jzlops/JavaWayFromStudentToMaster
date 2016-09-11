package ru.stikhonov.term2;

/**
 * Класс для тестирования оболочных типов
 *
 * @author Sergey Tikhonov
 */
public class RefTask {
    public static void main(String[] args) {
        Integer value = 1;
        RefTask.change(value);
        System.out.println(value);
    }

    public
    static void change(Integer value) {
        value++;
    }
}