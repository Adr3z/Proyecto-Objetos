import greenfoot.*;

/**
 * Object Manager
 * Adreez
 * 25/04/24
 */

public class ObjectSetter {
    private World world;
    public SuperObject[] obj;
    
    public ObjectSetter(World world) {
        this.world = world;
        obj = new SuperObject[10]; 
        
        // Inicializa cada elemento del array obj
        for (int i = 0; i < obj.length; i++) {
            obj[i] = new SuperObject();
        }
    }
    
    public void placeObjects(int col, int row) {
        // Coloca los objetos en función de las coordenadas de la baldosa
        int tileWidth = 50; 
        int tileHeight = 50; 
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
