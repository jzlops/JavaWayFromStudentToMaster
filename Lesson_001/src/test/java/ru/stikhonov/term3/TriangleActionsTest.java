package ru.stikhonov.term3;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Sergey Tikhonov on 09.09.2016.
 */
public class TriangleActionsTest {

    @Test
    public void testGetMaxTriangleLeg() throws Exception {
        Point pointA1 = new Point(1, 1);
        Point pointB1 = new Point(3, 5);
        Point pointC1 = new Point(6, 1);

        Triangle triangle1 = new Triangle(pointA1, pointB1, pointC1);
        assertThat(TriangleActions.getMaxTriangleLeg(triangle1), closeTo(5, 0.01));
    }
}