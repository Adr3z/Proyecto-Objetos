import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PROTAGONIC
 * ADREEZ
 */

public class Protagonic extends Actor {

    private int speed = 5; // Velocidad de movimiento del cuadrado

    public Protagonic() {
        GreenfootImage image = new GreenfootImage(50, 50); // Creamos una imagen de 20x20 píxeles
        image.setColor(Color.RED); // Establecemos el color de la imagen a blanco
        image.fill(); // Rellenamos la imagen con el color blanco
        setImage(image); // Establecemos la imagen del cuadrado
    }

    public void act() {
        // Verificamos si las teclas de dirección están siendo presionadas y movemos el cuadrado en consecuencia
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
        }
    }
}
