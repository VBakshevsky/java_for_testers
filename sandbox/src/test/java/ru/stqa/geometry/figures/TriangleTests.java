package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter() {
        var s = new Triangle(5.0, 5.0, 5.0);
        double result = s.perimeter();
        Assertions.assertEquals(15.0, result);
    }

    @Test
    void canCalculateSemiPerimeter() {
        var b = new Triangle(5.0, 5.0, 5.0);
        double result = b.semiPerimeter();
        Assertions.assertEquals(7.5, result);
    }
    @Test
    void canCalculateArea() {
        var a = new Triangle(5.0, 5.0, 5.0);
        double result = a.area();
        Assertions.assertEquals(10.825317547305483, result);
    }
    @Test
    void triangleCheck() { //Проверка сторон и неравенства треугольника
        try {
            new Triangle(-5.0, 10.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }
    @Test
    void  testEquality (){ //Сравнение сторон треугольника, которые совпадают
        var t1 = new Triangle(3.0, 4.0,5.0);
        var t2 = new Triangle(3.0, 4.0,5.0);
        Assertions.assertEquals(t1,t2);
    }
    @Test
    void  testEquality1 (){ //Сравнение сторон треугольника, которые не совпадают
        var t1 = new Triangle(3.0, 4.0,5.0);
        var t2 = new Triangle(4.0, 5.0,3.0);
        Assertions.assertEquals(t1,t2);
    }
}
