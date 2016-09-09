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
     *
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
     * @return Возвращает длинну ребра А
     */
    public double getLengthA() {
        return lengthA;
    }

    /**
     * @return Возвращает длинну ребра B
     */
    public double getLengthB() {
        return lengthB;
    }

    /**
     * @return Возвращает длинну ребра C
     */
    public double getLengthC() {
        return lengthC;
    }

    /**
     * @return метод возвращает площать треугольника (-1 в случае не корректных координат)
     */
    public double getArea() {
        double halfPerimeter;
        double area;
        if (this.lengthA > 0 && this.lengthB > 0 && this.lengthC > 0) {
            halfPerimeter = (this.lengthA + this.lengthB + this.lengthC) / 2;
            area = Math.sqrt(halfPerimeter * (halfPerimeter - this.lengthA) * (halfPerimeter - this.lengthB) * (halfPerimeter - this.lengthC));
            return area;
        } else {
            return -1;
        }
    }
}
