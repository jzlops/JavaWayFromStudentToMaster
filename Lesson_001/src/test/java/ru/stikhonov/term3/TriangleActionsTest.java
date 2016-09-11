package ru.stikhonov.term3;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author Sergey Tikhonov
 */
public class TriangleActionsTest {

    @Test
    public void maxTriangleLeg() throws Exception {
        TriangleActions triangleActions=new TriangleActions();
        Point pointA1 = new Point(1, 1);
        Point pointB1 = new Point(3, 5);
        Point pointC1 = new Point(6, 1);

        Triangle triangle1 = new Triangle(pointA1, pointB1, pointC1);
        assertThat(triangleActions.getMaxTriangleLeg(triangle1), closeTo(5, 0.01));
    }
}