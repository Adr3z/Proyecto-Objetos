import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * First Scene
 * Adreez
 * 02/05/24
 */

public class GameWorld extends World {
    private int currentMapIndex = 1;
    private ObjectSetter objectPlacer = new ObjectSetter(this);
    
    // Características del escenario
    public GameWorld() {
        super(800, 600, 1); // Dimensiones
        loadmap("map.txt");
        prepare();
    }

    private void loadmap(String filename) {
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
    }
   
    private void prepare() {
        Protagonic prota = new Protagonic(this.objectPlacer);
        Monster skeleton = new Monster();
        addObject(prota, 100,100);
        prota.setLocation(393,204);
        addObject(skeleton,200,400);
   }
}
