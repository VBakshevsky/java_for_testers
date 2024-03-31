package ru.stqa.geometry.figures;

public record Rectangle(double a,double b ) {

    public  Rectangle {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
    }
    public static void printRectangleArea(Rectangle s) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", s.a, s.b , s.rectangleArea() );
        System.out.println(text);
    }

    private double rectangleArea() {
        return this.a * this.b;
    }
}
