import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 *Adreez
 */

public class StartScreen extends World {
    public StartScreen() {
        //Dimensiones y diseño
        super(800, 600, 1);
        GreenfootImage bgImage = new GreenfootImage(800, 600);
        bgImage.setColor(Color.BLACK);
        bgImage.fill();
        setBackground(bgImage);
        showText("Nombre del Juego", getWidth() / 2, getHeight() / 3);
        
        // Crear botones
        Button startButton = new Button("Iniciar Juego");
        addObject(startButton, getWidth() / 2, getHeight() / 2 - 25); // Ajustar la posición para el espaciado
        
        Button creditsButton = new Button("Créditos");
        addObject(creditsButton, getWidth() / 2, getHeight() / 2 + 75); // Ajustar la posición para el espaciado

        // Iniciar la música de fondo
        MusicManager.playBackgroundMusic();
    }

    @Override
    public void showText(String text, int x, int y) {
        GreenfootImage textImage = new GreenfootImage(text, 50, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, x - textImage.getWidth() / 2, y - textImage.getHeight() / 2);
    }
}


