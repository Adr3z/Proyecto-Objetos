import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class EnemiSetter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemiSetter  
{
    // instance variables - replace the example below with your own
    private World world;
    public Monster[] moustros;

    /**
     * Constructor for objects of class EnemiSetter
     */
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