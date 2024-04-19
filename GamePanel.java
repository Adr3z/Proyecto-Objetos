import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * First Escenario
 * Adreez
 * 18/04/24
 */
public class GameWorld extends World {

    // Características del escenario

    public GameWorld() {
        super(800, 600, 1); // Usamos las dimensiones del escenario para el tamaño
        prepare(); // Método para preparar el escenario
    }

    private void prepare() {
        Protagonic prota = new Protagonic();
        addObject(prota, 100,100);
        prota.setLocation(393,204);
    }
}
