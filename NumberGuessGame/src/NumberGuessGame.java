import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NumberGuessGame {
    private static final String HIGH_SCORE_FILE = "highscores.txt";
    private static final String USER_SCORES_FILE = "user_scores.json";
    private static int targetNumber;
    private static int guessCount;
    private static int highScore;
    private static int startRange;
    private static int endRange;
    private static Scanner scanner = new Scanner(System.in);
    private static final int HINT_THRESHOLD = 5; // Number of incorrect guesses before giving a hint
    private static ArrayList<Integer> pastGuesses = new ArrayList<>(); // Store past guesses
    private static HashMap<String, ArrayList<Integer>> userScores = new HashMap<>(); // Store usernames and their scores

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game with Timer and Replay Option!");
        loadUserScores(); // Load user scores from file
        playGameRecursive(0); // Start with 0 replays
        saveUserScores(); // Save user scores to file
        printUserScoresJson(); // Print user scores in JSON format
        System.out.println("Thank you for playing!");
        scanner.close();
    }

    // Recursive method for playing the game
    private static void playGameRecursive(int replayCount) {
        if (replayCount >= 3) { // Limit to 3 replays
            System.out.println("You have reached the maximum number of replays (3).");
            return;
        }

        setupGame();
        playGame();

        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        if (response.equals("yes")) {
            playGameRecursive(replayCount + 1); // Increment replay count and call recursively
        }
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

        System.out.print("Enter your username: ");
        String username = scanner.next().trim();

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

        // Store the user's score
        if (!userScores.containsKey(username)) {
            userScores.put(username, new ArrayList<>());
        }
        userScores.get(username).add(guessCount);

        // Check and update the high score if necessary
        if (guessCount < highScore) {
            System.out.println("New High Score for range [" + startRange + " - " + endRange + "]! You set the record with " + guessCount + " guesses.");
            saveHighScore(startRange, endRange, guessCount);
        } else {
            System.out.println("Sorry, you didn't beat the high score. Try again!");
        }
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

    // Load user scores from file
    private static void loadUserScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0].trim();
                String[] scores = parts[1].replace("[", "").replace("]", "").split(",");
                ArrayList<Integer> scoreList = new ArrayList<>();
                for (String score : scores) {
                    scoreList.add(Integer.parseInt(score.trim()));
                }
                userScores.put(username, scoreList);
            }
        } catch (IOException | NumberFormatException e) {
            // No error message is shown
        }
    }

    // Save user scores to file
    private static void saveUserScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_SCORES_FILE))) {
            for (Map.Entry<String, ArrayList<Integer>> entry : userScores.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue().toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving user scores.");
        }
    }

    // Print user scores in JSON format
    private static void printUserScoresJson() {
        System.out.println("User Scores in JSON format:");
        System.out.println("{");
        for (Map.Entry<String, ArrayList<Integer>> entry : userScores.entrySet()) {
            System.out.println("  \"" + entry.getKey() + "\": " + entry.getValue() + ",");
        }
        System.out.println("}");
    }
}