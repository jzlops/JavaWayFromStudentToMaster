package ru.stikhonov.term3;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Tikhonov
 */
public class PointTest {

    @Test
    public void testDistanceTo() throws Exception {
        Point pointA1 = new Point(5, 5);
        Point pointB1 = new Point(5, 5);
        assertEquals(pointA1.distanceTo(pointB1), -1, 0);

        Point pointA2 = new Point(5, 5);
        Point pointB2 = new Point(10, 10);
        assertThat(pointA2.distanceTo(pointB2), closeTo(7.07, 0.01));

    }
}