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

        checkCollisions();
        if (!isCollidingWithTile(dx, dy)) {
        setLocation(getX() + dx, getY() + dy); 
        }
    }
    
    private boolean isSolidTile(int tileType) {
    return tileType == 1 || tileType == 2 || tileType == 4;
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
    
    private void checkCollisions() {
       // Obtener la posición del jugador
        int playerX = getX();
        int playerY = getY();

        // Verificar colisión con las baldosas alrededor del jugador
        if (isCollidingWithTile(0, -spriteHeight / 2 - 1)) {
            Tile tileAbove = (Tile) getOneIntersectingObject(0, -spriteHeight / 2 - 1, Tile.class);
            setLocation(playerX, tileAbove.getY() + tileAbove.getImage().getHeight() / 2 + spriteHeight / 2 + 1);
        }
        if (isCollidingWithTile(0, spriteHeight / 2 + 1)) {
        Tile tileBelow = (Tile) getOneIntersectingObject(0, spriteHeight / 2 + 1, Tile.class);
        setLocation(playerX, tileBelow.getY() - tileBelow.getImage().getHeight() / 2 - spriteHeight / 2 - 1);
        }
        if (isCollidingWithTile(-spriteWidth / 2 - 1, 0)) {
            Tile tileLeft = (Tile) getOneIntersectingObject(-spriteWidth / 2 - 1, 0, Tile.class);
            setLocation(tileLeft.getX() + tileLeft.getImage().getWidth() / 2 + spriteWidth / 2 + 1, playerY);
        }
        if (isCollidingWithTile(spriteWidth / 2 + 1, 0)) {
            Tile tileRight = (Tile) getOneIntersectingObject(spriteWidth / 2 + 1, 0, Tile.class);
            setLocation(tileRight.getX() - tileRight.getImage().getWidth() / 2 - spriteWidth / 2 - 1, playerY);
        }
        
        // Verificar si hay colisión con el objeto Obj_Key
        Obj_Key object = (Obj_Key) getOneIntersectingObject(Obj_Key.class);

        // Si hay colisión con el objeto Obj_Key, eliminarlo del mundo
        if (object != null) {
            getWorld().removeObject(object);
        }
    }
    
    private Actor getOneIntersectingObject(int xOffset, int yOffset, Class<?> cls) {
        return getOneObjectAtOffset(xOffset, yOffset, cls);
    }
}
