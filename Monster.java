import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * Adreez 
 * 02/05/2024
 */
public class Monster extends Actor
{
    private int speed = 1; 
    private int spriteWidth = 50; 
    private int spriteHeight = 50; 
    private GreenfootImage[][] movementSprites; // Matriz de imágenes para los sprites del monstruo
    private int direction;
    private int max_life = 4;
    private int actual_life;
    private int animationDelay = 10; // Delay entre cambios de sprite
    private int delayCount = 0;
    
    public Monster(){
        sprites = new GreenfootImage[4][2]; // Matriz de imágenes para 4 direcciones, 2 sprites por dirección
        sprites[0][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_up_1.png"), spriteWidth, spriteHeight);
        sprites[0][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_up_2.png"), spriteWidth, spriteHeight);
        sprites[1][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_down_1.png"), spriteWidth, spriteHeight);
        sprites[1][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_down_2.png"), spriteWidth, spriteHeight);
        sprites[2][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_left_1.png"), spriteWidth, spriteHeight);
        sprites[2][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_left_2.png"), spriteWidth, spriteHeight);
        sprites[3][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_right_1.png"), spriteWidth, spriteHeight);
        sprites[3][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_right_2.png"), spriteWidth, spriteHeight);

        direction = 1; 

        setImage(sprites[direction][0]);
        actual_life = max_life;
    }
    
        private GreenfootImage scaleImage(GreenfootImage image, int width, int height) {
        image.scale(width, height);
        return image;
    }
    
    public void act()
    {
        moveAndAnimate(direction);
        if (getX() % 50 == 0 && getY() % 50 == 0) {
            if (getX() == 0 && getY() == 0 && direction == 2) {
                direction = 1;
            } else if (getX() == 0 && getY() == 100 && direction == 1) {
                direction = 3;
            } else if (getX() == 100 && getY() == 100 && direction == 3) {
                direction = 0;
            } else if (getX() == 100 && getY() == 0 && direction == 0) {    
                direction = 2;
            }
        }
    }

    private void moveAndAnimate(int newDirection) {
        // Cambiar la imagen del monstruo para la animación
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

        // Mover al monstruo en la nueva dirección
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

        // Verificar colisiones y mover al monstruo
        if (!isCollidingWithTile(dx, dy)) {
            setLocation(getX() + dx, getY() + dy); 
        }
    
        // Cambiar de dirección cuando se llega al borde de una plataforma
        if (isAtEdge() || (getX() % 50 == 0 && getY() % 50 == 0)) {
            direction = (direction + 1) % 4;
        }
    }
    private boolean isCollidingWithTile(int dx, int dy) {
        // Obtener posiciones futuras
        int futureX = getX() + dx * speed;
        int futureY = getY() + dy * speed;

        // Calcular las posiciones de las baldosas adyacentes al jugador
        int tileX = futureX + dx;
        int tileY = futureY + dy;

        // Verificar si hay colisión con una baldosa sólida en la posición futura
        Actor tile = getOneObjectAtOffset(dx, dy, Tile.class);
        return tile != null && isSolidTile(((Tile) tile).getTileType());
    }
    
    private boolean isSolidTile(int tileType) {
        return tileType == 1 || tileType == 2 || tileType == 4;
    }
}
