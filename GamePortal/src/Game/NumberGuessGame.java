package Game;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame implements GameWriteable {
    private int attempts;
    private String score;

    @Override
    public String getGameName() {
        return "Number Guess";
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int secret = rand.nextInt(100) + 1;
        attempts = 0;
        System.out.println("Guess a number between 1 and 100:");
        while (true) {
            int guess = ErrorCheck.getInt(sc);
            attempts++;
            if (guess < secret) {
                System.out.println("Too low. Try again.");
            } else if (guess > secret) {
                System.out.println("Too high. Try again.");
            } else {
                System.out.println("Correct!");
                break;
            }
        }
        score = String.valueOf(attempts);
    }

    @Override
    public String getScore() {
        return score;
    }

    @Override
    public boolean isHighScore(String scoreStr, String currentHighScore) {
        int s = Integer.parseInt(scoreStr);
        int hs = (currentHighScore != null) ? Integer.parseInt(currentHighScore) : Integer.MAX_VALUE;
        return s < hs;
    }
}
