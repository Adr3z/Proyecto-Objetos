import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * First Scene
 * Adreez
 * 15/05/24
 */

public class GameWorld extends World {
    public int mapIndex;
    private ObjectSetter objectPlacer = new ObjectSetter(this);
    private MapManager mapManager = new MapManager(this);
    
    private Protagonic protagonist;
    
    // Características del escenario
    public GameWorld() {
        super(800, 600, 1); // Dimensiones
        loadmap("map07.txt");
        mapIndex = 7;
    }

    public void loadmap(String filename) {
        if (protagonist == null) {
        protagonist = new Protagonic(this.objectPlacer);
    }
    // Limpiar el mundo eliminando todos los objetos
    removeObjects(getObjects(null));
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] tiles = line.split(" ");
            for (int col = 0; col < tiles.length; col++) {
                int tileType = Integer.parseInt(tiles[col]);
                addObject(new Tile(tileType), (col * 50) + 25, (row * 50) + 25);
                objectPlacer.placeObjects(col, row);
            }
            row++;
        }
        reader.close();
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
    // Colocar al protagonista en una nueva posición después de cargar el mapa
    if (protagonist.getWorld() != null) {
        // Eliminar la instancia anterior del protagonista (si existe)
        removeObject(protagonist);
    }
    addObject(protagonist, 175, 375); // Colocar al protagonista en la nueva posición
}
   
    public void prepare() {
        
   }
   
   public MapManager getMapManager(){
       return this.mapManager;
   }
   
   public int getCurrentMapIndex(){
       return this.mapIndex;
   }
   
   public Protagonic getProtagonist() {
        return protagonist;
    }

   public void setProtagonist(Protagonic protagonist) {
        this.protagonist = protagonist;
    }
}
