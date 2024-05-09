import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Adreez
 * No sé, cosillas de UIX
 */

public class GameOverScreen extends World {
    public GameOverScreen() {
        super(800, 600, 1);
        GreenfootImage bgImage = new GreenfootImage(800, 600);
        bgImage.setColor(Color.BLACK);
        bgImage.fill();
        setBackground(bgImage);
        showText("Game Over", getWidth() / 2, getHeight() / 2);
    }
}
