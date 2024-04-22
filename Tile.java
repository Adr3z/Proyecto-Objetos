import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TILES
 * ADREEZ
 */

public class Tile extends Actor {
    
    private boolean collision;
    private int tileType;
    
    public Tile(int tileType) {
        this.tileType = tileType;
        String imageName = getImageNameForTileType(tileType);
        GreenfootImage image = new GreenfootImage(imageName);
        image.scale(50, 50);
        setImage(image);
        
        collision = isSolid(tileType);
    }
    
    public int getTileType() {
        return tileType;
    }
    
     public boolean isSolid(int tileType) {
        return tileType == 1 || tileType == 2 || tileType == 4; 
    }
    
     private String getImageNameForTileType(int tileType) {
        switch (tileType) {
            case 0:
                return "/Tiles/grass.png";
            case 1:
                return "/Tiles/tree.png";
            case 2:
                return "/Tiles/water.png";
            case 3:
                return "/Tiles/sand.png";
            case 4:
                return "/Tiles/wall.png";
            case 5:
                return "/Tiles/earth.png";
            default:
                return null;
        }
    }
}
