import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CreditsScreen
 * 
 * Adreez
 */
public class CreditsScreen extends World {
    public CreditsScreen() {
        super(800, 600, 1);
        GreenfootImage bgImage = new GreenfootImage(800, 600);
        bgImage.setColor(Color.BLACK);
        bgImage.fill();
        setBackground(bgImage);

        // Mostrar texto de créditos
        showText("Créditos", getWidth() / 2, getHeight() / 6);
        showText("Proyecto Tecnología Orientada a Objetos 2024", getWidth() / 2, getHeight() / 3);
        showText("Desarrollado por: Fryda Lara y Marvin Torres", getWidth() / 2, getHeight() / 2);
        showText("Música por: RyiSnow", getWidth() / 2, getHeight() / 2 + 50);

        // Añadir botón de volver
        Button backButton = new Button("Volver");
        addObject(backButton, getWidth() / 2, getHeight() - 50);
    }

    @Override
    public void showText(String text, int x, int y) {
        GreenfootImage textImage = new GreenfootImage(text, 24, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, x - textImage.getWidth() / 2, y - textImage.getHeight() / 2);
    }
}
