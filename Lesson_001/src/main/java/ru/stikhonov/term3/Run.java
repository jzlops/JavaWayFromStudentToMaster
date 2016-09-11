package ru.stikhonov.term3;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) {
        TriangleActions triangleActions = new TriangleActions();
        Triangle triangle = triangleActions.initTriangle(1, 1, 3, 5, 6, 1);
        System.out.println("Triangle area = " + triangle.getArea());
        System.out.println("Triangle max leg length = " + triangleActions.getMaxTriangleLeg(triangle));
    }
}
