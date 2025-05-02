package Game;

import java.util.Scanner;

public class ErrorCheck {
    public static int getInt(Scanner sc) {
        while (true) {
            try {
                String line = sc.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer:");
            }
        }
    }
}
