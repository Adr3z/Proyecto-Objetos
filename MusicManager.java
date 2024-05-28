import greenfoot.*;

/**
 * 
 * Adreez
 */

public class MusicManager {
    private static GreenfootSound backgroundMusic = new GreenfootSound("gamesound.wav");
    private static GreenfootSound dungeonMusic = new GreenfootSound("Dungeon.wav");
    private static GreenfootSound gameOverMusic = new GreenfootSound("gameover.wav");
    private static GreenfootSound gameCompleteMusic = new GreenfootSound("fanfare.wav");
    
    private static GreenfootSound currentMusic = null;

    public static void playBackgroundMusic() {
        if (currentMusic != backgroundMusic) {
            stopAllMusic();
            backgroundMusic.playLoop();
            currentMusic = backgroundMusic;
        }
    }

    public static void playDungeonMusic() {
        if (currentMusic != dungeonMusic) {
            stopAllMusic();
            dungeonMusic.playLoop();
            currentMusic = dungeonMusic;
        }
    }

    public static void playGameOverMusic() {
        if (currentMusic != gameOverMusic) {
            stopAllMusic();
            gameOverMusic.play();
            currentMusic = gameOverMusic;
        }
    }

    public static void playGameCompleteMusic() {
        if (currentMusic != gameCompleteMusic) {
            stopAllMusic();
            gameCompleteMusic.play();
            currentMusic = gameCompleteMusic;
        }
    }

    public static void stopAllMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic = null;
        }
    }
}
