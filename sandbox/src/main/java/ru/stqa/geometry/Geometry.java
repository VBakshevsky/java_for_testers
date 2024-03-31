package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(5.0));

        Rectangle.printRectangleArea(new Rectangle(3.0, 5.0));

        Triangle.printTrianglePerimeter(new Triangle (10.0,-5.0,5.0));
        Triangle.printTriangleArea (new Triangle (5.0,5.0,5.0));
    }
}
