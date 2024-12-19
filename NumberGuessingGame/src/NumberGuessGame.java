import java.io.*;
import java.util.*;

/**
 * Number Guessing Game with Timer, Highscore, Replay Option, and Guess Tracking
 * Created by Armin
 */

public class NumberGuessGame {
    private static final String HIGH_SCORE_FILE = "highscores.txt";
    private static int targetNumber;
    private static int guessCount;
    private static int highScore;
    private static int startRange;
    private static int endRange;
    private static Scanner scanner = new Scanner(System.in);
    private static final int HINT_THRESHOLD = 5; // Number of incorrect guesses before giving a hint
    private static ArrayList<Integer> pastGuesses = new ArrayList<>(); // Store past guesses

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game with Timer and Replay Option!");

        boolean playAgain;
        do {
            setupGame();
            playGame();
            playAgain = askReplay();
        } while (playAgain);

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    // Setup the game by defining range and initializing target number
    private static void setupGame() {
        System.out.println("\nStarting a new game...");
        pastGuesses.clear(); // Clear past guesses for a new game

        // Set up the range for the game
        while (true) {
            startRange = getRange("Enter the start of the range: ");
            endRange = getRange("Enter the end of the range: ");

            if (startRange < endRange) {
                break; // Valid range entered
            } else {
                System.out.println("Invalid range. Start of range should be less than end of range.");
            }
        }

        // Load the high score for the specified range
        highScore = loadHighScore(startRange, endRange);
        if (highScore != Integer.MAX_VALUE) {
            System.out.println("Current High Score for range [" + startRange + " - " + endRange + "]: " + highScore + " guesses");
        } else {
            System.out.println("No high score yet for this range. Be the first to set it!");
        }

        targetNumber = new Random().nextInt(endRange - startRange + 1) + startRange;
        guessCount = 0;
    }

    // Play the game
    private static void playGame() {
        double startTime = System.currentTimeMillis(); // Start the timer
        boolean guessedCorrectly = false;

        while (!guessedCorrectly) {
            int guess = getGuess();
            guessCount++;

            if (pastGuesses.contains(guess)) {
                System.out.println("You already guessed that number! Try something new.");
                guessCount--; // Don't penalize for repeating guesses
                continue;
            } else {
                pastGuesses.add(guess); // Add the new guess to the list
            }

            if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
                giveHint(guess);
            } else if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
                giveHint(guess);
            } else {
                guessedCorrectly = true;
            }
        }

        double endTime = System.currentTimeMillis(); // End the timer
        double timeTaken = (endTime - startTime) / 1000; // Time taken in seconds

        System.out.println("Congratulations! You guessed the number in " + guessCount + " attempts."); // Tells user how many tries it took them to guess the number
        System.out.println("Time taken: " + timeTaken + " seconds."); // Tells user time it took to guess the number

        // Check and update the high score if necessary
        if (guessCount < highScore) {
            System.out.println("New High Score for range [" + startRange + " - " + endRange + "]! You set the record with " + guessCount + " guesses.");
            saveHighScore(startRange, endRange, guessCount);
        } else {
            System.out.println("Sorry, you didn't beat the high score. Try again!");
        }
    }

    // Ask if the player wants to play again
    private static boolean askReplay() {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        return response.equals("yes");
    }

    // Function to retrieve and validate the range
    private static int getRange(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Function to retrieve and validate the guess
    private static int getGuess() {
        System.out.print("Enter your guess: ");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That's not a number! Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Give user a hint based on the number of incorrect guesses
    private static void giveHint(int guess) {
        if (guessCount >= HINT_THRESHOLD) {
            int difference = Math.abs(guess - targetNumber);

            if (difference <= 5) {
                System.out.println("Hint: You're so close!");
            } else if (difference <= 10) {
                System.out.println("Hint: You're close.");
            } else {
                System.out.println("Hint: You're far from the target number.");
            }
        }
    }

    // Load the high score for a specific range from the file
    private static int loadHighScore(int start, int end) {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String rangePart = parts[0].trim();
                String scorePart = parts[1].trim();

                // Parse range
                String[] range = rangePart.replace("Range: [", "").replace("]", "").split("-");
                int fileStart = Integer.parseInt(range[0].trim());
                int fileEnd = Integer.parseInt(range[1].trim());

                if (fileStart == start && fileEnd == end) {
                    return Integer.parseInt(scorePart.replace("High Score:", "").replace("guesses", "").trim());
                }
            }
        } catch (IOException | NumberFormatException e) {
            // No error message is shown
        }
        return Integer.MAX_VALUE; // If no high score found, return a large value
    }

    // Save the new high score for a specific range to the file
    private static void saveHighScore(int start, int end, int newHighScore) {
        StringBuilder updatedScores = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String rangePart = parts[0].trim();

                // Parse range
                String[] range = rangePart.replace("Range: [", "").replace("]", "").split("-");
                int fileStart = Integer.parseInt(range[0].trim());
                int fileEnd = Integer.parseInt(range[1].trim());

                if (fileStart == start && fileEnd == end) {
                    updatedScores.append("Range: [").append(start).append(" - ").append(end)
                            .append("] | High Score: ").append(newHighScore).append(" guesses\n");
                    found = true;
                } else {
                    updatedScores.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            // No specific error handling needed for now
        }

        // If no high score for the range was found, add a new line
        if (!found) {
            updatedScores.append("Range: [").append(start).append(" - ").append(end)
                    .append("] | High Score: ").append(newHighScore).append(" guesses\n");
        }

        // Save the updated high scores back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            writer.write(updatedScores.toString());
        } catch (IOException e) {
            System.out.println("Error saving high score.");
        }
    }
}
