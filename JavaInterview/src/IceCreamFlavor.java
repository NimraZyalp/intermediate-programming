import java.util.Scanner;

public class IceCreamFlavor {
    public static void main(String[] args) {
        // Scanner gets user input
        Scanner scanner = new Scanner(System.in);

        // Asks for the user's name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // Use say command to say the user's name 
        try {
            Runtime.getRuntime().exec("say Hi " + name + "What's your favorite ice cream flavor?");
        } catch (Exception e) {
            System.out.println("An error occurred while trying to use the say command for the name.");
            e.printStackTrace();
        }

        // Asks for the user's favorite ice cream flavor
        System.out.print("Hi " + name + "! What's your favorite ice cream flavor? ");
        String flavor = scanner.nextLine();

        // Ternary operator to decide the response
        String response = flavor.equalsIgnoreCase("vanilla") ? "Nice flavor!" : "I haven't tried that ice cream flavor.";

        // Output the response
        System.out.println(response);

        // Use the say command for the response
        try {
            Runtime.getRuntime().exec("say " + response);
        } catch (Exception e) {
            System.out.println("An error occurred while trying to use the say command for the flavor.");
            e.printStackTrace();
        }

        // Close the scanner
        scanner.close();
    }
}
