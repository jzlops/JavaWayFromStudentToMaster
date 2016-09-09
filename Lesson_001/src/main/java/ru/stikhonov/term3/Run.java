package ru.stikhonov.term3;

/**
 * Created by Sergey Tikhonov on 09.09.2016.
 */
public class Run {
    public static void main(String[] args) {
        TriangleActions triangleActions = new TriangleActions();
        triangleActions.setTrianglePoints(1,1,3,5,6,1);
        System.out.println("Triangle area = " + triangleActions.getTriangleArea());
    }
}
