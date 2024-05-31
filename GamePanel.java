import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * First Scene
 * Adreez
 * 17/05/24
 */

public class GameWorld extends World {
    public int mapIndex;
    private ObjectSetter objectPlacer = new ObjectSetter(this);
    private MapManager mapManager = new MapManager(this);
    private EnemiSetter enemigos = new EnemiSetter(this);
    private Protagonic protagonist;
    
    public GameWorld() {
        super(800, 600, 1); // Dimensiones
        loadmap("map07.txt");
        mapIndex = 7;
        objectPlacer.placeObjects(mapIndex);
        enemigos.enemiMap(mapIndex);
        
        MusicManager.playBackgroundMusic();
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
                }
                row++;
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        objectPlacer.placeObjects(mapIndex);
        
        // Colocar al protagonista en una nueva posición después de cargar el mapa
        if (protagonist.getWorld() != null) {
            // Eliminar la instancia anterior del protagonista (si existe)
            removeObject(protagonist);
        }
        addObject(protagonist, 175, 375); // Colocar al protagonista en la nueva posición
        
        if (mapIndex == 10) {
            MusicManager.playDungeonMusic();
        } else {
            MusicManager.playBackgroundMusic();
        }
    }
   
    public void prepare() {
        enemigos.enemiMap(mapIndex);
    }
   
    public MapManager getMapManager() {
        return this.mapManager;
    }
   
    public int getCurrentMapIndex() {
        return this.mapIndex;
    }
   
    public Protagonic getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(Protagonic protagonist) {
        this.protagonist = protagonist;
    }

    @Override
    public void act() {
        super.act();
        checkGameOver();
        checkGameComplete();
    }

    private void checkGameOver() {
        if (protagonist.getLife() <= 0) {
            MusicManager.playGameOverMusic();  
            Greenfoot.setWorld(new GameOverScreen());
        }
    }

    private void checkGameComplete() {
        if (mapIndex == 10 && getObjects(Monster.class).isEmpty()) {
            MusicManager.playGameCompleteMusic(); 
            Greenfoot.setWorld(new GameCompleteScreen());
        }
    }
}



