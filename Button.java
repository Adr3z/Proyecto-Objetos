import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Adreez
 */

public class Button extends Actor {
    private String label;
    private GreenfootSound cursor;
    
    public Button(String label) {
        this.label = label;
        updateImage();
        cursor = new GreenfootSound("cursor.wav");
    }
    
    private void updateImage() {
        GreenfootImage image = new GreenfootImage(200, 50);
        image.setColor(Color.GRAY);
        image.fillRect(0, 0, 200, 50);
        image.setColor(Color.WHITE);
        GreenfootImage textImage = new GreenfootImage(label, 24, Color.WHITE, new Color(0, 0, 0, 0));
        image.drawImage(textImage, (200 - textImage.getWidth()) / 2, (50 - textImage.getHeight()) / 2);
        setImage(image);
    }
    
    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            cursor.play();
            if (label.equals("Iniciar Juego")) {
                Greenfoot.setWorld(new GameWorld());
            } else if (label.equals("Créditos")) {
                Greenfoot.setWorld(new CreditsScreen());
            } else if (label.equals("Volver")) {
                Greenfoot.setWorld(new StartScreen());
            }
        }
    }

}


