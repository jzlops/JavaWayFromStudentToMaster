package ru.stikhonov.term3;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Tikhonov
 */
public class TriangleTest {

    @Test
    public void getTriangleAreaWhenPointsMatch() throws Exception {
        Point pointA1 = new Point(5, 5);
        Point pointB1 = new Point(5, 5);
        Point pointC1 = new Point(6, 7);

        Triangle triangle = new Triangle(pointA1, pointB1, pointC1);
        assertEquals(triangle.getArea(), 0, 0);
    }
    @Test
    public void getTriangleAreaWhenPointsCorrect() throws Exception {
        Point pointA2 = new Point(1, 1);
        Point pointB2 = new Point(3, 5);
        Point pointC2 = new Point(6, 1);

        Triangle triangle = new Triangle(pointA2, pointB2, pointC2);
        assertThat(triangle.getArea(), closeTo(10, 0.01));
    }
}