import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PROTAGONIC
 * ADREEZ
 */

public class Protagonic extends Actor {
    
    private int speed = 2; 
    private int spriteWidth = 50; 
    private int spriteHeight = 50; 
    private GreenfootImage[][] sprites; // Matriz de imágenes para los sprites del jugador
    private int direction; // Dirección actual del jugador (0: arriba, 1: abajo, 2: izquierda, 3: derecha)
    private int animationDelay = 10; // Delay entre cambios de sprite
    private int delayCount = 0;

    public Protagonic() {
       sprites = new GreenfootImage[4][2]; // Matriz de imágenes para 4 direcciones, 2 sprites por dirección
        sprites[0][0] = scaleImage(new GreenfootImage("/player/up_sprite1.png"), spriteWidth, spriteHeight);
        sprites[0][1] = scaleImage(new GreenfootImage("/player/up_sprite2.png"), spriteWidth, spriteHeight);
        sprites[1][0] = scaleImage(new GreenfootImage("/player/down_sprite1.png"), spriteWidth, spriteHeight);
        sprites[1][1] = scaleImage(new GreenfootImage("/player/down_sprite2.png"), spriteWidth, spriteHeight);
        sprites[2][0] = scaleImage(new GreenfootImage("/player/left_sprite1.png"), spriteWidth, spriteHeight);
        sprites[2][1] = scaleImage(new GreenfootImage("/player/left_sprite2.png"), spriteWidth, spriteHeight);
        sprites[3][0] = scaleImage(new GreenfootImage("/player/right_sprite1.png"), spriteWidth, spriteHeight);
        sprites[3][1] = scaleImage(new GreenfootImage("/player/right_sprite2.png"), spriteWidth, spriteHeight);

        direction = 1; 

        setImage(sprites[direction][0]);
    }
    
    private GreenfootImage scaleImage(GreenfootImage image, int width, int height) {
        image.scale(width, height);
        return image;
    }
    
   public void act() {
        if (Greenfoot.isKeyDown("up")) {
            moveAndAnimate(0); 
        } else if (Greenfoot.isKeyDown("down")) {
            moveAndAnimate(1); 
        } else if (Greenfoot.isKeyDown("left")) {
            moveAndAnimate(2);
        } else if (Greenfoot.isKeyDown("right")) {
            moveAndAnimate(3); 
        }
    }
    
    private void moveAndAnimate(int newDirection) {
        // Cambiar la imagen del jugador para la animación
        if (newDirection != direction) {
            direction = newDirection;
            setImage(sprites[direction][0]); // Mostrar el primer sprite de la nueva dirección
            delayCount = 0; // Reiniciar el contador de delay
        } else {
            // Incrementar el contador de delay
            delayCount++;
            // Cambiar al siguiente sprite de la animación
            if (delayCount >= animationDelay) {
                setImage(sprites[direction][(getImage().equals(sprites[direction][0])) ? 1 : 0]);
                delayCount = 0; // Reiniciar el contador de delay
            }
        }
    
        // Mover al jugador en la nueva dirección
        int dx = 0;
        int dy = 0;
        if (newDirection == 0) {
            dy = -speed; 
        } else if (newDirection == 1) {
            dy = speed; 
        } else if (newDirection == 2) {
            dx = -speed; 
        } else if (newDirection == 3) {
            dx = speed; 
        }

        // Verificar si hay una colisión en la nueva posición del jugador
        if (!isCollidingWithTile(dx, dy)) {
            setLocation(getX() + dx, getY() + dy); 
        }
    }
    
    private boolean isCollidingWithTile(int dx, int dy) {
        // Obtener posiciones futuras
        int futureX = getX() + dx * speed;
        int futureY = getY() + dy * speed;

        // Ajustar las coordenadas futuras para detenerse antes de la colisión
        if (dx != 0) {
            futureX += (dx > 0) ? -speed / 2 : speed / 2;
        }
        if (dy != 0) {
            futureY += (dy > 0) ? -speed / 2 : speed / 2;
        }
    
        Actor tile = getOneObjectAtOffset(dx, dy, Tile.class);
        if (tile != null) {
            int tileType = ((Tile) tile).getTileType();
            return ((Tile) tile).isSolid(tileType);
        }

        return false;
    }
}
