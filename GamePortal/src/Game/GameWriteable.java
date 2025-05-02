package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface GameWriteable extends Game {

    boolean isHighScore(String score, String currentHighScore);

    @Override
    default void writeHighScore(File f) {
        String score = getScore();
        String highScore = getBestScore(f);
        System.out.println("Thanks for playing! Your score was " + score);

        if (isHighScore(score, highScore)) {
            System.out.println("You got a new high score, which beats the previous high score of " + highScore);
            try {
                Scanner myReader = new Scanner(f);
                StringBuilder newFile = new StringBuilder();
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    String[] data = line.split(",");
                    if (!data[0].equals(getGameName())) {
                        newFile.append(line).append("\n");
                    }
                }
                newFile.append(getGameName()).append(",").append(score).append("\n");
                FileWriter myWriter = new FileWriter(f);
                myWriter.write(newFile.toString());
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    default String getBestScore(File highscoreFile) {
        String highScore = null;
        try {
            Scanner myReader = new Scanner(highscoreFile);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");
                if (data.length == 2 && data[0].equals(getGameName())) {
                    highScore = data[1];
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            try {
                highscoreFile.createNewFile();
            } catch (IOException ioException) {
                System.err.println("Could not create file " + highscoreFile.getName());
            }
        }
        return highScore;
    }
}
