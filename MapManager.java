import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * El hijo de su chingada madre cambio de mapa
 * Adreez
 * 15/05/24
 */

public class MapManager {
    private GameWorld world;
    private String[] mapFiles = {"map01.txt", "map02.txt", "map03.txt", "map04.txt", "map05.txt", "map06.txt", "map07.txt", "map08.txt", "map09.txt","map10.txt"};
    private int[][] mapConnections = {{10,4,0, 2}, {0,5, 1, 3}, {0,6, 2,0}, {1,7,0,5}, {2,8,4,6}, {3,9,5,0}, {4,0,0, 8}, {5,0, 7, 9}, {6,0, 8,0},{0,1,0,0}};
    
    public MapManager(GameWorld world) {
        this.world = world;
    }
    
    public void changeMap(int newMapIndex, int direction) {
    if (newMapIndex < 0 || newMapIndex > 10) {
        System.err.println("Índice de mapa inválido: " + newMapIndex);
        return;
    }
    
    // Guardar la posición actual del jugador
    int startX = world.getProtagonist().getX();
    int startY = world.getProtagonist().getY();
    
    world.mapIndex = newMapIndex;
    
    // Cargar el nuevo mapa
    world.loadmap(mapFiles[newMapIndex - 1]);

    // Obtener la referencia al protagonista del mundo
    Protagonic protagonist = world.getProtagonist();
    
    // Colocar al jugador en la nueva posición según la dirección
    if (direction == 0) {
        protagonist.setLocation(startX, 550);
    } else if (direction == 1) {
        protagonist.setLocation(startX, 50);
    } else if (direction == 2) {
        protagonist.setLocation(750, startY);
    } else {
        protagonist.setLocation(50, startY);
    }
    world.prepare();
}
    
    public int[] getConnectedMaps(int currentMapIndex) {
        return mapConnections[currentMapIndex - 1];
    }
    

}