import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PROTAGONIC
 * ADREEZ
 */
public class Protagonic extends Actor {
    
    private int speed = 2; // Velocidad de movimiento del cuadrado
    private int spriteWidth = 50; 
    private int spriteHeight = 50; 
    private GreenfootImage[][] sprites; // Matriz de imágenes para los sprites del jugador
    private int direction; // Dirección actual del jugador (0: arriba, 1: abajo, 2: izquierda, 3: derecha)
    private int animationDelay = 10; // Retraso entre cambios de sprite
    private int delayCount = 0;

    public Protagonic() {
       sprites = new GreenfootImage[4][2]; // Matriz de imágenes para 4 direcciones, 2 sprites por dirección
        // Cargar las imágenes de los sprites para cada dirección y escalarlas al tamaño deseado
        sprites[0][0] = scaleImage(new GreenfootImage("up_sprite1.png"), spriteWidth, spriteHeight);
        sprites[0][1] = scaleImage(new GreenfootImage("up_sprite2.png"), spriteWidth, spriteHeight);
        sprites[1][0] = scaleImage(new GreenfootImage("down_sprite1.png"), spriteWidth, spriteHeight);
        sprites[1][1] = scaleImage(new GreenfootImage("down_sprite2.png"), spriteWidth, spriteHeight);
        sprites[2][0] = scaleImage(new GreenfootImage("left_sprite1.png"), spriteWidth, spriteHeight);
        sprites[2][1] = scaleImage(new GreenfootImage("left_sprite2.png"), spriteWidth, spriteHeight);
        sprites[3][0] = scaleImage(new GreenfootImage("right_sprite1.png"), spriteWidth, spriteHeight);
        sprites[3][1] = scaleImage(new GreenfootImage("right_sprite2.png"), spriteWidth, spriteHeight);
        // Inicializar la dirección
        direction = 1; // Por defecto, hacia abajo
        // Establecer la imagen inicial
        setImage(sprites[direction][0]);
    }
    
    private GreenfootImage scaleImage(GreenfootImage image, int width, int height) {
        // Escalar la imagen al tamaño deseado
        image.scale(width, height);
        return image;
    }
    
   public void act() {
        // Verificar si las teclas de dirección están siendo presionadas y mover al jugador en consecuencia
        if (Greenfoot.isKeyDown("up")) {
            moveAndAnimate(0); // Mover hacia arriba (dirección 0)
        } else if (Greenfoot.isKeyDown("down")) {
            moveAndAnimate(1); // Mover hacia abajo (dirección 1)
        } else if (Greenfoot.isKeyDown("left")) {
            moveAndAnimate(2); // Mover hacia la izquierda (dirección 2)
        } else if (Greenfoot.isKeyDown("right")) {
            moveAndAnimate(3); // Mover hacia la derecha (dirección 3)
        }
    }
    
    private void moveAndAnimate(int newDirection) {
        // Si la dirección ha cambiado, cambiar la imagen del jugador para la animación
        if (newDirection != direction) {
            direction = newDirection;
            setImage(sprites[direction][0]); // Mostrar el primer sprite de la nueva dirección
            delayCount = 0; // Reiniciar el contador de retraso
        } else {
            // Incrementar el contador de retraso
            delayCount++;
            // Si ha pasado suficiente tiempo, cambiar al siguiente sprite de la animación
            if (delayCount >= animationDelay) {
                setImage(sprites[direction][(getImage().equals(sprites[direction][0])) ? 1 : 0]);
                delayCount = 0; // Reiniciar el contador de retraso
            }
        }
    
        // Mover al jugador en la nueva dirección
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed); // Mover hacia arriba
        } else if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed); // Mover hacia abajo
        } else if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY()); // Mover hacia la izquierda
        } else if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY()); // Mover hacia la derecha
        }
    }
}
