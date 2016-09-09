package ru.stikhonov.term3;

/**
 * @author Sergey Tikhonov
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;
    private double lengthA;
    private double lengthB;
    private double lengthC;

    /**
     * Конструктор в качестве параметра принимает три объекта Point
     * @param a точка a
     * @param b точка b
     * @param c точка c
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.lengthA = this.a.distanceTo(this.b);
        this.lengthB = this.b.distanceTo(this.c);
        this.lengthC = this.c.distanceTo(this.a);
    }

    /**
     * @return Возвращает длинну ребра А (x1 x2 - y1 y2)
     */
    public double getLengthA() {
        return this.lengthA;
    }

    /**
     * @return Возвращает длинну ребра B (x2 x3 - y2 y3)
     */
    public double getLengthB() {
        return this.lengthB;
    }

    /**
     * @return Возвращает длинну ребра C (x3 x1 - y3 y1)
     */
    public double getLengthC() {
        return this.lengthC;
    }

    /**
     * @return метод возвращает площать треугольника (-1 в случае некорректных координат)
     */
    public double getArea() {
        double triangelHalfPerimetr;
        double triangleArea;
        if (this.lengthA > 0 && this.lengthB > 0 && this.lengthC > 0) {
            triangelHalfPerimetr = (this.lengthA + this.lengthB + this.lengthC) / 2;
            triangleArea = Math.sqrt(triangelHalfPerimetr * (triangelHalfPerimetr - this.lengthA) * (triangelHalfPerimetr - this.lengthB) * (triangelHalfPerimetr - this.lengthC));
            return triangleArea;
        } else {
            return -1;
        }
    }
}
