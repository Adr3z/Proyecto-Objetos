import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * 
 * Adreez 
 * 02/05/2024
 */
public class Monster extends Actor
{
    //stats
    private int speed = 1; 
    private int agroRange = 100; // Rango de detección del jugador
    private int attackRange = 20; // Rango de ataque al jugador
    private int attackDamage = 10; // Daño del ataque
    private int max_life = 20;
    private int actual_life;

    //sprites y delays
    private int spriteWidth = 50; 
    private int spriteHeight = 50; 
    private GreenfootImage[][] movementSprites;
    private GreenfootImage[][] attackSprites;
    private int animationDelay = 10; // Delay entre cambios de sprite
    private int delayCount = 0;
    private int attackFrame = 0;
    private int detectionDelay = 30; // Retraso en la detección del jugador
    private int detectionCount = 0; 
    private int direction;
    private boolean attacking;
    
    public Monster(){
        //movementSprites
        movementSprites = new GreenfootImage[4][2]; // Matriz de imágenes para 4 direcciones, 2 sprites por dirección
        movementSprites[0][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_up_1.png"), spriteWidth, spriteHeight);
        movementSprites[0][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_up_2.png"), spriteWidth, spriteHeight);
        movementSprites[1][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_down_1.png"), spriteWidth, spriteHeight);
        movementSprites[1][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_down_2.png"), spriteWidth, spriteHeight);
        movementSprites[2][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_left_1.png"), spriteWidth, spriteHeight);
        movementSprites[2][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_left_2.png"), spriteWidth, spriteHeight);
        movementSprites[3][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_right_1.png"), spriteWidth, spriteHeight);
        movementSprites[3][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_right_2.png"), spriteWidth, spriteHeight);

        //Attacking sprites
        attackSprites = new GreenfootImage[4][2];
        attackSprites[0][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_up_1.png"), spriteWidth, spriteHeight);
        attackSprites[0][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_up_2.png"), spriteWidth, spriteHeight);
        attackSprites[1][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_down_1.png"), spriteWidth, spriteHeight);
        attackSprites[1][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_down_2.png"), spriteWidth, spriteHeight);
        attackSprites[2][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_left_1.png"), spriteWidth, spriteHeight);
        attackSprites[2][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_left_2.png"), spriteWidth, spriteHeight);
        attackSprites[3][0] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_right_1.png"), spriteWidth, spriteHeight);
        attackSprites[3][1] = scaleImage(new GreenfootImage("/monsters/skeletonlord_attack_right_2.png"), spriteWidth, spriteHeight);
        
        direction = 1; 

        setImage(movementSprites[direction][0]);
        actual_life = max_life;
        attacking = false;
    }
    
    private GreenfootImage scaleImage(GreenfootImage image, int width, int height) {
        image.scale(width, height);
        return image;
    }
    
    public void act()
    {
        detectionCount++;
        if (attacking) {
            setImage(attackSprites[direction][attackFrame]);
            attackFrame = (attackFrame + 1) % 2;
            Greenfoot.delay(10); 
        }else{
            moveAndAnimate(direction);
        }
        if (detectionCount >= detectionDelay) {
            detectPlayer();
            detectionCount = 0; 
        }
    }
    
     private void detectPlayer() {
        Protagonic player = (Protagonic) getOneObjectInRange(agroRange, Protagonic.class);
        if (player != null && !attacking) {
            moveToPlayer(player);
            if (getDistance(player) <= attackRange) {
                attackPlayer(player);
            }
        }
    }
    
     private double getDistance(Protagonic player) {
        int dx = getX() - player.getX();
        int dy = getY() - player.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private void moveToPlayer(Protagonic player) {
        int dx = player.getX() - getX();
        int dy = player.getY() - getY();

        if (Math.abs(dx) > Math.abs(dy)) {
            // Mover en el eje X
            if (dx > 0) {
                direction = 3; // Derecha
            } else {
                direction = 2; // Izquierda
            }
        } else {
            // Mover en el eje Y
            if (dy > 0) {
                direction = 1; // Abajo
            } else {
                direction = 0; // Arriba
            }
        }
    }

    private void attackPlayer(Protagonic player) {
       attacking = true;
        player.reduceLife(attackDamage);
        setImage(attackSprites[direction][attackFrame]);
        attackFrame = (attackFrame + 1) % 2;
        attacking = false;
    }
    
   public Actor getOneObjectInRange(int range, Class<?> cls) {
        Actor object = getOneObjectAtOffset(0, 0, cls); // Verifica si hay un objeto en la ubicación actual
        if (object != null) {
            return object; // Retorna el objeto 
        }
    
        // Verifica en las posiciones adyacentes
        for (int dx = -range; dx <= range; dx++) {
            for (int dy = -range; dy <= range; dy++) {
                if (dx != 0 || dy != 0) { // Evita la posición actual
                    object = getOneObjectAtOffset(dx, dy, cls);
                    if (object != null) {
                        return object; // Retorna el objeto si se encuentra en rango
                    }
                }
            }
        }
    
        return null; // Retorna null si no se encontró ningún objeto en rango
    }
    private void moveAndAnimate(int newDirection) {
        // Cambiar la imagen del monstruo para la animación
        if (newDirection != direction) {
            direction = newDirection;
            setImage(movementSprites[direction][0]); // Mostrar el primer sprite de la nueva dirección
            delayCount = 0; // Reiniciar el contador de delay
        } else {
            // Incrementar el contador de delay
            delayCount++;
            // Cambiar al siguiente sprite de la animación
            if (delayCount >= animationDelay) {
                setImage(movementSprites[direction][(getImage().equals(movementSprites[direction][0])) ? 1 : 0]);
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
        if (!isCollidingWithTile(dx, dy)){
            setLocation(getX() + dx, getY() + dy); 
        } else {
            // Cambiar de dirección
            int alternativeDirection = (newDirection + Greenfoot.getRandomNumber(3) + 1) % 4; // Dirección aleatoria entre las restantes
            moveAndAnimate(alternativeDirection);
        }
    }
    
    private boolean isSolidTile(int tileType) {
         return tileType == 16 || tileType == 18 || tileType == 19 || tileType == 20 || tileType == 21 || tileType == 22 || tileType == 23 || tileType == 24
            || tileType == 25 || tileType == 26 || tileType == 27 || tileType == 28 || tileType == 29 || tileType == 30 || tileType == 31
            || tileType == 32 || tileType == 33 || tileType == 34 || tileType == 35 || tileType == 38; 
    }
    
    private boolean isCollidingWithTile(int dx, int dy) {
        int futureX = getX() + dx * speed;
        int futureY = getY() + dy * speed;
    
        int halfWidth = spriteWidth / 2;
        int halfHeight = spriteHeight / 2;

        // Calcular las coordenadas de los bordes del monstruo
        int leftBorder = futureX - halfWidth;
        int rightBorder = futureX + halfWidth;
        int topBorder = futureY - halfHeight;
        int bottomBorder = futureY + halfHeight;

        // Verificar si alguno de los bordes del monstruo está dentro de una baldosa sólida
        return isSolidTileAt(leftBorder, topBorder) || isSolidTileAt(rightBorder, topBorder) || isSolidTileAt(leftBorder, bottomBorder) || isSolidTileAt(rightBorder, bottomBorder) ||
                isOutOfBounds(leftBorder, topBorder) || isOutOfBounds(rightBorder, topBorder) || isOutOfBounds(leftBorder, bottomBorder) || isOutOfBounds(rightBorder, bottomBorder);
    }
    
    private boolean isSolidTileAt(int x, int y) {
        Actor tile = getOneObjectAtOffset(x - getX(), y - getY(), Tile.class);
        return tile != null && isSolidTile(((Tile) tile).getTileType());
    }
    
    private boolean isOutOfBounds(int x, int y) {
        World world = getWorld();
        return x < 0 || y < 0 || x >= world.getWidth() || y >= world.getHeight();
    }
    
     private boolean isCollidingWithPlayer(int dx, int dy) {
        Actor player = getOneObjectAtOffset(dx, dy, Protagonic.class);
        return player != null;
    }
    
    public void reduceLife(int damage) {
        actual_life -= damage;
        if (actual_life <= 0) {
            Protagonic player = (Protagonic) getOneIntersectingObject(Protagonic.class);
            if (player != null) {
                giveRewardsToPlayer(player);
            }
            getWorld().removeObject(this);
        }
    }
    private void giveRewardsToPlayer(Protagonic player) {
        player.addExperience(10);
        player.addMoney(3);
    }
    
}