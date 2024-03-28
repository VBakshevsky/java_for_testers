package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c ) {
    public static void printTrianglePerimeter(Triangle s) {
        var text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f", s.a, s.b, s.c, s.perimeter() );
        System.out.println(text);
    }

    public static void printTriangleArea(Triangle a) {
        var text = (String.format("Площадь треугольника со сторонами %f и %f и %f = %f", a.a, a.b, a.c, (a.area())));
        System.out.println(text);
    }

   // public static double rectangleArea(double a, double b, double c) {
        //var p = semiPerimeter(a,b,c);
        //return p * (p - a) * (p - b) * (p - c);
   // }

    //public static double semiPerimeter(double a, double b, double c) {
        //return (a + b + c) / 2;
    //}

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
}
