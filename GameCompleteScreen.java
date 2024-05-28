import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Adreez
 * Semestre ya acabate por favor
 */

public class GameCompleteScreen extends World {
    public GameCompleteScreen() {
        super(800, 600, 1);
        GreenfootImage bgImage = new GreenfootImage(800, 600);
        bgImage.setColor(Color.BLACK);
        bgImage.fill();
        setBackground(bgImage);
        showText("¡Juego Completado!", getWidth() / 2, getHeight() / 2 - 50);
        showText("Presiona Enter para ver los créditos", getWidth() / 2, getHeight() / 2 + 50);

        MusicManager.playGameCompleteMusic();
    }
    
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new CreditsScreen());
        }
    }
    
    @Override
    public void showText(String text, int x, int y) {
        GreenfootImage textImage = new GreenfootImage(text, 24, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, x - textImage.getWidth() / 2, y - textImage.getHeight() / 2);
    }
}
