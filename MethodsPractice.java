public class MethodsPractice {

    public static void main(String[] args) {
        // Test isPositive
        System.out.println("isPositive(5): " + isPositive(5)); // true
        System.out.println("isPositive(-3): " + isPositive(-3)); // false

        // Test findLeast
        System.out.println("findLeast(5, 3, 8): " + findLeast(5, 3, 8)); // 3
        System.out.println("findLeast(-1, 0, 2): " + findLeast(-1, 0, 2)); // -1

        // Test triangle
        System.out.println("triangle(4): " + triangle(4)); // 6.928

        // Test shareLastDigit
        System.out.println("shareLastDigit(23, 13): " + shareLastDigit(23, 13)); // true
        System.out.println("shareLastDigit(24, 18): " + shareLastDigit(24, 18)); // false

        // Test sumDigits
        System.out.println("sumDigits(1234): " + sumDigits(1234)); // 10
        System.out.println("sumDigits(987): " + sumDigits(987)); // 24

        // Test toDecimal
        System.out.println("toDecimal(101): " + toDecimal(101)); // 5
        System.out.println("toDecimal(1011): " + toDecimal(1011)); // 11
    }

    // Method to check if an integer is positive
    public static boolean isPositive(int num) {
        return num > 0;
    }

    // Method to find the smallest of three integers
    public static int findLeast(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    // Method to calculate the area of an equilateral triangle given its side length
    public static double triangle(int sideLength) {
        // Formula: (sqrt(3) / 4) * side^2
        return (Math.sqrt(3) / 4) * (sideLength * sideLength);
    }

    // Method to check if two integers share the same last digit
    public static boolean shareLastDigit(int a, int b) {
        return a % 10 == b % 10;
    }

    // Method to sum the digits of an integer
    public static int sumDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    // Method to convert a binary number (given as an int) to its decimal equivalent
    public static int toDecimal(int binary) {
        int decimal = 0;
        int power = 0;

        // Loop through each digit of the binary number
        while (binary > 0) {
            int lastDigit = binary % 10;
            decimal += lastDigit * Math.pow(2, power);
            binary /= 10;
            power++;
        }

        return decimal;
    }
}
