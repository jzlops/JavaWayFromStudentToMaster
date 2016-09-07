package ru.stikhonov;

/**
 * Created by Sergey Tikhonov on 07.09.2016.
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
/*
* Используется ссылочный тип Integer.
* В метод change передается копия ссылки на объект типа Integer. При операции value++ происходит анбоксинг и изменения значения и создание нового объекта типа Integer???
* Так это же не String.
* Есть вопросы ...
* */