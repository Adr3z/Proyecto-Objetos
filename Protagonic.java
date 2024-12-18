import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * PROTAGONIC
 * ADREEZ
 */

public class Protagonic extends Actor {
    //stats
    private int speed = 2; 
    private int max_life = 100;
    private int life;
    private int level;
    private int experience;
    private int experienceThreshold = 30;
    private int money;
    private int attack;
    private int defense;
    
    //Animaciones y cosas
    private int spriteWidth = 50; 
    private int spriteHeight = 50; 
    private GreenfootImage[][] sprites; // Matriz de imágenes para los sprites del jugador
    private GreenfootImage[][] attacksprites;
    private int direction; // Dirección actual del jugador (0: arriba, 1: abajo, 2: izquierda, 3: derecha)
    private int animationDelay = 10; // Delay entre cambios de sprite
    private int delayCount = 0;
    private boolean attacking = false;
    
    //Sonidos
    private GreenfootSound attackSound;
    private GreenfootSound coin;
    private GreenfootSound levelup;
    private GreenfootSound locked;
    private GreenfootSound unlocked;
    
    //Otras cosas
    private ObjectSetter objectSetter;
    private ArrayList<SuperObject> inventory;
    private StatsWindow statsWindow;

    public Protagonic(ObjectSetter objectSetter) {
        this.objectSetter = objectSetter;
        
        sprites = new GreenfootImage[4][2]; // Matriz de imágenes para 4 direcciones, 2 sprites por dirección
        sprites[0][0] = scaleImage(new GreenfootImage("/player/up_sprite1.png"), spriteWidth, spriteHeight);
        sprites[0][1] = scaleImage(new GreenfootImage("/player/up_sprite2.png"), spriteWidth, spriteHeight);
        sprites[1][0] = scaleImage(new GreenfootImage("/player/down_sprite1.png"), spriteWidth, spriteHeight);
        sprites[1][1] = scaleImage(new GreenfootImage("/player/down_sprite2.png"), spriteWidth, spriteHeight);
        sprites[2][0] = scaleImage(new GreenfootImage("/player/left_sprite1.png"), spriteWidth, spriteHeight);
        sprites[2][1] = scaleImage(new GreenfootImage("/player/left_sprite2.png"), spriteWidth, spriteHeight);
        sprites[3][0] = scaleImage(new GreenfootImage("/player/right_sprite1.png"), spriteWidth, spriteHeight);
        sprites[3][1] = scaleImage(new GreenfootImage("/player/right_sprite2.png"), spriteWidth, spriteHeight);

        
        attacksprites = new GreenfootImage[4][2];
        attacksprites[0][0] = scaleImage(new GreenfootImage("/player/attack_up1.png"), 60, 90);
        attacksprites[0][1] = scaleImage(new GreenfootImage("/player/attack_up2.png"), 60, 90);
        attacksprites[1][0] = scaleImage(new GreenfootImage("/player/attack_down1.png"), 60, 90);
        attacksprites[1][1] = scaleImage(new GreenfootImage("/player/attack_down2.png"), 60, 90);
        attacksprites[2][0] = scaleImage(new GreenfootImage("/player/attack_left1.png"), 90, 60);
        attacksprites[2][1] = scaleImage(new GreenfootImage("/player/attack_left2.png"), 90, 60);
        attacksprites[3][0] = scaleImage(new GreenfootImage("/player/attack_right1.png"), 90, 60);
        attacksprites[3][1] = scaleImage(new GreenfootImage("/player/attack_right2.png"), 90, 60);

        //Sonidos
        attackSound = new GreenfootSound("hitmonster.wav");
        coin = new GreenfootSound("coin.wav");
        levelup = new GreenfootSound("powerup.wav");
        locked = new GreenfootSound("blocked.wav");
        unlocked = new GreenfootSound("unlock.wav");
        
        
        //Set Up
        direction = 1; 
        setImage(sprites[direction][0]);
        inventory = new ArrayList<SuperObject>();
        life = max_life;
        attack = 1;
        level = 1;
        
        this.statsWindow = new StatsWindow(this);
    }
    
    public void act() {
        for (Chest chest : getWorld().getObjects(Chest.class)) {
        if (isTouching(chest.getClass())) {
            // Verificar si el jugador está colisionando con el cofre
            if (getOneIntersectingObject(Chest.class) != null) {
                int dx = 0;
                int dy = 0;
                if (direction == 0) {
                    dy = -speed;
                } else if (direction == 1) {
                    dy = speed;
                } else if (direction == 2) {
                    dx = -speed;
                } else if (direction == 3) {
                    dx = speed;
                }
                setLocation(getX() - dx, getY() - dy); 
            }
        }
        }
        if (Greenfoot.isKeyDown("up")) {
            moveAndAnimate(0); 
        } else if (Greenfoot.isKeyDown("down")) {
            moveAndAnimate(1); 
        } else if (Greenfoot.isKeyDown("left")) {
            moveAndAnimate(2);
        } else if (Greenfoot.isKeyDown("right")) {
            moveAndAnimate(3); 
        }
        if (Greenfoot.isKeyDown("e")) {
            if( Greenfoot.isKeyDown("shift") ){
                closeInventoryWindow();
            } else {
                showInventoryWindow();
            }
        }
        if (Greenfoot.isKeyDown("f")) {
            attack();
        }
        if (Greenfoot.isKeyDown("x")) {
            checkForCofres();
        }
        if (isAtEdge()) {
            int newMapIndex = calculateNewMapIndex();
            // Obtener el MapManager del mundo actual
            MapManager mapManager = ((GameWorld) getWorld()).getMapManager();
            // Cambiar al siguiente mapa y dirección apropiada
            mapManager.changeMap(newMapIndex, direction);
        }
    }

    //Animaciones
    private GreenfootImage scaleImage(GreenfootImage image, int width, int height) {
        image.scale(width, height);
        return image;
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
    
    //Mapa
    private int calculateNewMapIndex() {
        // Obtener el MapManager del mundo actual
        MapManager mapManager = ((GameWorld) getWorld()).getMapManager();
        // Obtener el índice actual del mapa
        int currentMapIndex = ((GameWorld) getWorld()).getCurrentMapIndex();
        // Obtener las conexiones del mapa actual
        int[] connectedMaps = mapManager.getConnectedMaps(currentMapIndex);
        // Calcular el nuevo índice del mapa según la dirección del jugador
        int newMapIndex = connectedMaps[direction];
        return newMapIndex;
    }

    //Funciones de inventario y recolección de objetos
    public ArrayList<SuperObject> getInventory() {
        return inventory;
    }
    
    private void showInventoryWindow() {
        InventoryWindow inventoryWindow = new InventoryWindow(this);
        getWorld().addObject(inventoryWindow, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }
    
    private void closeInventoryWindow() {
        World world = getWorld();
        if (world != null) {
            world.removeObjects(world.getObjects(InventoryWindow.class));
        }
    }

    private void checkForCofres() {
        int range = 50;
        List<Chest> cofres = getObjectsInRange(range, Chest.class);
        for (Chest cofre : cofres) {
            if (isTouching(cofre.getClass()) && !cofre.isOpen()) {
                if (cofre.needKey() && hasKey()) {
                    unlocked.play();
                    cofre.Opening();
                    objectSetter.setCofreAbierto(objectSetter.getCofres().indexOf(cofre), true);
                    useKey();
                    addRewardsToInventory(cofre.getRewards());
                } else if (!cofre.needKey()) {
                    cofre.Opening();
                    objectSetter.setCofreAbierto(objectSetter.getCofres().indexOf(cofre), true);
                    addRewardsToInventory(cofre.getRewards());
                }else
                locked.play();
            }
        }
    }
    
    private void addRewardsToInventory(List<SuperObject> rewards) {
        if (rewards != null) {
            for (SuperObject reward : rewards) {
                inventory.add(reward);
                getWorld().removeObject(reward);
            }
        }
    }
    
    public boolean hasKey() {
        for (SuperObject obj : inventory) {
            if (obj instanceof Obj_Key) {
                return true;
            }
        }
        return false;
    }
    
    public void useKey() {
        for (SuperObject obj : inventory) {
            if (obj instanceof Obj_Key) {
                inventory.remove(obj);
                break;
            }
        }
    }
    
    public void useItem(SuperObject object) {
        if (object instanceof PotionRed) {
            addLife(20);
        } else if (object instanceof PotionPurple) {
            addLevel(2);
        } else if (object instanceof BlueHeart) {
            addMaxLife(20);
        } else if (object instanceof CoinBronze) {
            addMoney(1);
        } else if (object instanceof CoinGold) {
            addMoney(5);
        }
        inventory.remove(object);
    }

    public void equipItem(SuperObject object) {
        if (object instanceof Boots) {
            addSpeed(2);
        } else if (object instanceof ShieldWood) {
            addDefense(1);
        } else if (object instanceof ShieldBlue) {
            addDefense(3);
        } else if (object instanceof SwordNormal) {
            addAttack(3);
        } else if (object instanceof SwordDiamond) {
            addAttack(5);
        } else if (object instanceof SwordPurple) {
            addAttack(10);
        }
        inventory.remove(object);
    }
    
    private Actor getOneIntersectingObject(int xOffset, int yOffset, Class<?> cls) {
        return getOneObjectAtOffset(xOffset, yOffset, cls);
    }

    public void addedToWorld(World world) {
        if (!world.getObjects(StatsWindow.class).isEmpty()) {
            world.removeObject(world.getObjects(StatsWindow.class).get(0)); // Eliminar instancia anterior (si hay alguna)
        }
        world.addObject(statsWindow, 75, 90);
    }
    

    //Colisiones
    private boolean isSolidTile(int tileType) {
        return tileType == 16 || tileType == 18 || tileType == 19 || tileType == 20 || tileType == 21 || tileType == 22 || tileType == 23 || tileType == 24
            || tileType == 25 || tileType == 26 || tileType == 27 || tileType == 28 || tileType == 29 || tileType == 30 || tileType == 31
            || tileType == 32 || tileType == 33 || tileType == 34 || tileType == 35 || tileType == 38; 
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
        
    }
    
    //Attack functions
    private void attack() {
        if (!attacking) {
          attacking = true;
          attackSound.play();
          
          // Guardar la posición actual del jugador
          int startX = getX();
          int startY = getY();
          
          // Cambiar la imagen a la de ataque
          if(direction == 0){
              setLocation(startX, startY - 20);
          } else if(direction == 1){
              setLocation(startX, startY +20);
          } else if( direction == 2){
              setLocation(startX - 20, startY);
          }else{
              setLocation(startX +20, startY );
          }
          
          setImage(attacksprites[direction][0]);
          
          Greenfoot.delay(10);
          
          // Cambiar al segundo sprite de la animación de ataque
          setImage(attacksprites[direction][1]);
          
          // Realizar el ataque
          Monster enemy = (Monster) getOneIntersectingObject(Monster.class);
          if (enemy != null) {
              enemy.reduceLife(attack);
          }
          
          Greenfoot.delay(10);
          // Restaurar la imagen y la posición del jugador
          setImage(sprites[direction][0]);
          setLocation(startX, startY);
          
          attacking = false;
        }
      }

    //Setters
    public void reduceLife(int damage) {
        this.life -= damage; 
        statsWindow.drawStats(this);
    }
    private void addLife(int amount) {
        life += amount;
        if (life > max_life) {
            life = max_life;
        }
        statsWindow.drawStats(this);
    }
    
    private void addMaxLife(int amount) {
        max_life += amount;
        life = max_life;
        statsWindow.drawStats(this);
    }
    
    private void addSpeed(int amount) {
        speed += amount;
        statsWindow.drawStats(this);
    }
    
    private void addDefense(int amount) {
        defense += amount;
        statsWindow.drawStats(this);
    }
    
    private void addAttack(int amount) {
        attack += amount;
        statsWindow.drawStats(this);
    }
    
    private void addLevel(int amount) {
        level += amount;
        statsWindow.drawStats(this);
    }
    
    public void addMoney(int amount) {
        money += amount;
        statsWindow.drawStats(this);
        coin.play();
    }
    
    public void addExperience(int exp) {
        experience += exp;
        while (experience >= experienceThreshold && level < 10) {
            experience -= experienceThreshold;
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        max_life += 10; 
        life = max_life; 
        attack += 3; 
        defense += 1; 
        levelup.play();
    }

    //Getters
    public int getSpeed() {
        return speed;
    }

    public int getLife() {
        return life;
    }

    public int getMaxLife() {
        return max_life;
    }

    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMoney() {
        return money;
    }

    public void gameOver() {
        Greenfoot.setWorld(new GameOverScreen());
    }
}