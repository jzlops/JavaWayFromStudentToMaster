package ru.stikhonov.term3;

/**
 * Created by Sergey Tikhonov on 09.09.2016.
 */

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point point) {
        double distance;
        distance = Math.sqrt(Math.pow((this.x - point.x), 2) + Math.pow((this.y - point.y), 2));
        if (distance == 0) {
            return -1;
        } else return distance;
    }
}