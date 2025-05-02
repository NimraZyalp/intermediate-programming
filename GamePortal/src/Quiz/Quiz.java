package Quiz;

import Game.GameWriteable;
import Game.ErrorCheck;
import java.util.Scanner;

public class Quiz implements GameWriteable {
    private int correct;
    private String score;

    private final String[][] qna = {
        {"What is the capital of France?", "Paris"},
        {"Compute 2 + 2 * 2 =", "6"},
        {"What color is the sky on a clear day?", "blue"}
    };

    @Override
    public String getGameName() {
        return "Quiz";
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        correct = 0;
        System.out.println("Welcome to the Quiz!");
        for (String[] pair : qna) {
            System.out.println(pair[0]);
            String ans = sc.nextLine().trim();
            if (ans.equalsIgnoreCase(pair[1])) {
                System.out.println("Correct!");
                correct++;
            } else {
                System.out.println("Wrong! The answer is " + pair[1]);
            }
        }
        System.out.println("Quiz complete! You got " + correct + " out of " + qna.length);
        score = String.valueOf(correct);
    }

    @Override
    public String getScore() {
        return score;
    }

    @Override
    public boolean isHighScore(String scoreStr, String currentHighScore) {
        int s = Integer.parseInt(scoreStr);
        int hs = (currentHighScore != null) ? Integer.parseInt(currentHighScore) : 0;
        return s > hs;
    }
}
