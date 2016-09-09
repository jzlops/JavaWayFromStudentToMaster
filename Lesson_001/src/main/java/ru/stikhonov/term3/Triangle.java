package ru.stikhonov.term3;

/**
 * Created by Sergey Tikhonov on 09.09.2016.
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getArea() {
        double halfPerimeter;
        double area;
        double lengthA, lengthB, lengthC;
        lengthA = this.a.distanceTo(b);
        lengthB = this.b.distanceTo(c);
        lengthC = this.c.distanceTo(a);
        if (lengthA > 0 && lengthB > 0 && lengthC > 0) {
            halfPerimeter = (lengthA + lengthB + lengthC) / 2;
            area = Math.sqrt(halfPerimeter * (halfPerimeter - lengthA) * (halfPerimeter - lengthB) * (halfPerimeter - lengthC));
            return area;
        } else {
            return -1;
        }
    }
}