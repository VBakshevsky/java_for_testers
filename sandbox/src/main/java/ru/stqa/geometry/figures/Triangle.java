package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        } else if (a >= (b + c) || b >= (a + c) || c >= (a + b)) {
            throw new IllegalArgumentException("A triangle cannot have a side whose length is greater than or equal to the other two");
        }
    }

    public static void printTrianglePerimeter(Triangle s) {
        var text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f", s.a, s.b, s.c, s.perimeter());
        System.out.println(text);
    }

    public static void printTriangleArea(Triangle a) {
        var text = (String.format("Площадь треугольника со сторонами %f и %f и %f = %f", a.a, a.b, a.c, (a.area())));
        System.out.println(text);
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

    public double semiPerimeter() {
        return (this.a + this.b + this.c) / 2;
    }

    public double area() {
        var p = semiPerimeter();
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }

    @Override
    public boolean equals(Object o) { //Сравнение сторон треугольника
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)
                || (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                || (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                || (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}


// public static double rectangleArea(double a, double b, double c) {
//var p = semiPerimeter(a,b,c);
//return p * (p - a) * (p - b) * (p - c);
// }

//public static double semiPerimeter(double a, double b, double c) {
//return (a + b + c) / 2;
//}