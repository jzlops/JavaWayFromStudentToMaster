package ru.stikhonov.term3;

/**
 * Класс для оперирования объектом Triangle
 *
 * @author Sergey Tikhonov
 */
public class TriangleActions {
    /**
     * Метод инициализирует (создает) объект треугольник (Triangle)
     * В качетве параметров метод принимает координаты 3-х точек по оси X и Y соотвественно
     */
    public static Triangle initTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        Point pointA = new Point(x1, y1);
        Point pointB = new Point(x2, y2);
        Point pointC = new Point(x3, y3);
        return new Triangle(pointA, pointB, pointC);
    }

    /**
     * Метод вычисляет максимальную длинну ребра треугольника
     *
     * @param triangle - в качестве параметра принимает объект Triangle
     * @return возвращает максимальную длинну стороны треугольника
     */
    public static double getMaxTriangleLeg(Triangle triangle) {
        double maxLeg;
        if (triangle.getLengthA() > triangle.getLengthB()) {
            maxLeg = triangle.getLengthA();
        } else {
            maxLeg = triangle.getLengthB();
        }
        if (triangle.getLengthC() > maxLeg) {
            maxLeg = triangle.getLengthC();
        }
        return maxLeg;
    }
}
