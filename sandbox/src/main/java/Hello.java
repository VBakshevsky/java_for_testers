public class Hello {
    public static void main(String[] args) {
            var x = 1;
            var y = 0;
            if (y == 0) {
                System.out.println("Division by zero is not allowed");
            } else {
                var z = divide(x, y);
                System.out.println("Hello, world!");
            }
    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}

//System.out.println(2 + 2);
//System.out.println(2 * 2);
//System.out.println(2 - 2);
//System.out.println(2 / 2);
//System.out.println((2 + 2) * 2);
//System.out.println("Hello," + "world!");
//System.out.println("2 + 2 = " + (2 + 2));

// var configFile = new File("sandbox/build.gradle");
// System.out.println(configFile.getAbsolutePath());
// System.out.println(configFile.exists());