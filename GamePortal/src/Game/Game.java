package Game;

import java.io.File;

public interface Game {
    String getGameName();
    void play();
    String getScore();
    void writeHighScore(File f);
}
