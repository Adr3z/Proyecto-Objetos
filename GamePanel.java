import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * First Scene
 * Adreez
 * 18/04/24
 */
public class GameWorld extends World {

    // Características del escenario
    public GameWorld() {
        super(800, 600, 1); // Dimensiones
        prepare(); 
    }

    private void prepare() {
        Protagonic prota = new Protagonic();
        addObject(prota, 100,100);
        prota.setLocation(393,204);
    }
}
