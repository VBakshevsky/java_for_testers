package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(5.0, 5.0, 5.0);
        double result = s.Perimeter();
        Assertions.assertEquals(15.0, result);
    }

    @Test
    void canCalculateSemiPerimeter() {
        var b = new Triangle(5.0, 5.0, 5.0);
        double result = b.SemiPerimeter();
        Assertions.assertEquals(7.5, result);
    }
    @Test
    void canCalculateArea() {
        var a = new Triangle(5.0, 5.0, 5.0);
        double result = Math.sqrt(a.Area());
        Assertions.assertEquals(10.825317547305483, result);
    }
}
