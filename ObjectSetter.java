import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Object Manager
 * Adreez
 *
 */

public class ObjectSetter {
    private World world;
    public SuperObject[] obj;
    private List<Chest> cofres;
    
    public ObjectSetter(World world) {
        this.world = world;
        obj = new SuperObject[10]; 
        this.cofres = new ArrayList<>();
        
    }
    
     public void placeObjects(int mapIndex) {
        clearCofres();

        if (mapIndex == 2) {
            placeCofre(6, 3, new Obj_Key(), true);
        } else if (mapIndex == 3) {
            placeCofre(1, 9, new SuperObject(), true);
        } else if (mapIndex == 4) {
            placeCofre(1, 1, new SuperObject(), true);
        } else if (mapIndex == 5) {
            placeCofre(9, 11, new SuperObject(), false);
            placeCofre(10, 5, new SuperObject(), true);
        }else if (mapIndex == 7) {
            placeCofre(6, 3, new SuperObject(), false);
        }else if (mapIndex == 8) {
            placeCofre(8,2, new SuperObject(), false);
        }else if (mapIndex == 9) {
            placeCofre(7, 2, new SuperObject(), false);
        }
        
    }
    
    private void placeCofre(int col, int row, SuperObject reward, boolean needKey) {
        int tileWidth = 50;
        int tileHeight = 50;
        int offsetX = (tileWidth - reward.getImage().getWidth()) / 2;
        int offsetY = (tileHeight - reward.getImage().getHeight()) / 2;

        int worldX = (col * tileWidth) + offsetX;
        int worldY = (row * tileHeight) + offsetY;

        Chest c = new Chest(reward, needKey);
        world.addObject(c, worldX, worldY);
        cofres.add(c);
    }
    
    public void clearCofres() {
        for (Chest cofre : cofres) {
            world.removeObject(cofre);
        }
        cofres.clear();
    }
    
    public void placeObjects(int col, int row) {
        // Coloca los objetos en función de las coordenadas de la baldosa
        int tileWidth = 50; // Ancho de la baldosa
        int tileHeight = 50; // Alto de la baldosa
        int offsetX = (tileWidth - obj[0].getImage().getWidth()) / 2; // Compensación en X para centrar el objeto
        int offsetY = (tileHeight - obj[0].getImage().getHeight()) / 2; // Compensación en Y para centrar el objeto
    
        // Calcula las coordenadas de la baldosa en el mundo
        int worldX = (col * tileWidth) + offsetX;
        int worldY = (row * tileHeight) + offsetY;

        // Coloca el objeto en el mundo
        obj[0].setWorldLocation(worldX, worldY); 

        if (col == 4 && row == 2) {
            world.addObject(new Obj_Key(), worldX, worldY); 
        }
    }
}
