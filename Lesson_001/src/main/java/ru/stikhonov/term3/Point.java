package ru.stikhonov.term3;

/**
 * Класс создающий объект точка (Point)
 *
 * @author Sergey Tikhonov
 */

public class Point {
    public double x;
    public double y;

    /**
     * Конструктор для создания точки
     *
     * @param x задает координаты точки по оси x
     * @param y задает координаты точки по оси y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод вычисления расстояния между двумя точками
     *
     * @param point метод принимает в качастве параметра объект Point
     * @return возвращает расстояние между текущей точкой и точкой переданной в качестве параметра метода (-1 в случае наслоения точек)
     */
    public double distanceTo(Point point) {
        double distance;
        distance = Math.sqrt(Math.pow((this.x - point.x), 2) + Math.pow((this.y - point.y), 2));
        if (distance == 0) {
            return -1;
        } else return distance;
    }
}