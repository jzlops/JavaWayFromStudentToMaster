package ru.stikhonov.exam;

/**
 * @author Sergey Tikhonov
 */
public class Main {
    public static void main(String[] args) {
        Object test;
        MyArray myArray = new MyArray(5);
        myArray.add(0,0);
        myArray.add(1,1);
        myArray.add(1,4);
        myArray.add(2,2);
        myArray.add(3,3);
        myArray.add(4,4);
        test = myArray.get(1);
        System.out.println(test);
        System.out.println(myArray.get(1));
        test=5;
        System.out.println(test);
        System.out.println(myArray.get(1));

        Brace brace = new Brace();
        System.out.println(brace.verify("((())()()))("));
        System.out.println(brace.verify("((())()))"));

    }
}
