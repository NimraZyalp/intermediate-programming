import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Game.Game;
import Game.NumberGuessGame;
import Game.ErrorCheck;
import Store.StoreGame;
import Quiz.Quiz;

public class GamePortal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Game> games = new ArrayList<>();

    public static void main(String[] args) {
        HashMap<String, Integer> gameCounts = new HashMap<>();
        File highscoreFile = new File("Highscore.csv");

        while (true) {
            loadGames();
            System.out.println("\nWhich game would you like to play?");
            printGameChoices();
            Game g = getGameChoice();
            System.out.println("\n--- You're playing: " + g.getGameName() + " ---\n");
            g.play();
            g.writeHighScore(highscoreFile);

            gameCounts.merge(g.getGameName(), 1, Integer::sum);
        }
    }

    static void loadGames() {
        games.clear();
        games.add(new NumberGuessGame());
        games.add(new StoreGame());
        games.add(new Quiz());
    }

    static void printGameChoices() {
        for (int i = 0; i < games.size(); i++) {
            System.out.println("[" + (i+1) + "]: " + games.get(i).getGameName());
        }
    }

    static Game getGameChoice() {
        int choice = ErrorCheck.getInt(sc);
        while (choice < 1 || choice > games.size()) {
            System.out.println("Invalid choice. Try again:");
            choice = ErrorCheck.getInt(sc);
        }
        return games.get(choice - 1);
    }
}
