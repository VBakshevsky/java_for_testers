package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
//        var squares = List.of(new Square(7.0),new Square(5.0),new Square(3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }
        //        Consumer<Square> print = square -> {
//            Square.printSquareArea(square);
//            Square.printSquarePerimeter(square);
//        };
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);

        squares.peek(Square::printSquareArea).forEach(Square::printSquarePerimeter);

//        Rectangle.printRectangleArea(new Rectangle(3.0, 5.0));
//        Rectangle.printRectangleArea(new Rectangle(7.0, 9.0));
//
//        Triangle.printTrianglePerimeter(new Triangle (6.0,4.0,5.0));
//        Triangle.printTriangleArea (new Triangle (5.0,5.0,5.0));
    }
}
