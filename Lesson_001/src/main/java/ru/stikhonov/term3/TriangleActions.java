package ru.stikhonov.term3;

/**
 * Created by Sergey Tikhonov on 09.09.2016.
 */
public class TriangleActions {
    private Triangle triangle;
    private Point pointA;
    private Point pointB;
    private Point pointC;


    public void setTrianglePoints(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.pointA = new Point(x1, y1);
        this.pointB = new Point(x2, y2);
        this.pointC = new Point(x3, y3);
    }

    public double getTriangleArea() {
        this.triangle = new Triangle(this.pointA, this.pointB, this.pointC);
        return triangle.getArea();
    }

}
