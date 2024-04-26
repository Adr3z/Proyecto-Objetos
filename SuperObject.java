import greenfoot.*; 

/**
 * Clase padre para los objetos del mundo
 * Adreez
 * 25/04/24
 */

public class SuperObject extends Actor {
    public GreenfootImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY; 
    
    public void setWorldLocation(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        setLocation(x, y);
    }

}

