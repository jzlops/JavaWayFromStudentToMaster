package ru.stikhonov.term3;

/**
 * @author Sergey Tikhonov
 */
public class Run {
    public static void main(String[] args) {
        TriangleActions triangleActions = new TriangleActions();
        Triangle triangle = triangleActions.initTriangle(1, 1, 3, 5, 6, 1);
        System.out.printf("Triangle area = %1$.3f %n",triangle.getArea());
        System.out.printf("Triangle max leg length = %1.3f %n", triangleActions.getMaxTriangleLeg(triangle));
    }
}
