package ru.stikhonov;

/**
 * Created by Sergey Tikhonov on 07.09.2016.
 */
public class ChangeState {
    public static void main(String[] args) {
        Claim claim = new Claim();
        claim.name = "bug";
        processClaim(claim);
        System.out.println(claim.name);
    }

    private static void processClaim(Claim value) {
        value.name = "task";
    }

    public static class Claim {
        public String name;


    }

}

/*
Сколько объектов класса Сlaim создано?
    -Один объект конструктором Claim(). Тут возник вопрос про вложенный статический класс.... лучше по скайпу.
Что будет выведенно на консоль?
    -Содержимое поля name объекта типа Claim - "task". Передача параметров в метод processClaim осуществляется по значению,
     которое представляет собой копию ссылки на объект типа Claim (сама ссылка содержится в переменной типа Claim - claim).
     В строке value.name="task" мы меняем поле name объекта Сlaim.
  */
