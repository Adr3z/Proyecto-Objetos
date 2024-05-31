import greenfoot.*;
import java.util.Random;

/**
 * Warvin
 */
public class EnemiSetter  
{
    private World world;
    public Monster[] moustros;

    public EnemiSetter(World world){
        this.world = world;
        moustros =new Monster [6];
        
    }
    
    public void enemiMap (int index){
        if(index==10){
            for(Monster moustros : moustros){
                placeEnemi((int)(Math.random()*12 +2),(int)(Math.random()*7 +2));
            }
        }
    }
    
    private void placeEnemi(int col, int row) {
        int tileWidth = 50;
        int tileHeight = 50;

        int worldX = (col * tileWidth);
        int worldY = (row * tileHeight);

        Monster moustro = new Monster();
        world.addObject(moustro, worldX, worldY);
    }
    
    public void clearEnemi(){
        for (Monster moustros : moustros) {
            world.removeObject(moustros);
        }
    }
}