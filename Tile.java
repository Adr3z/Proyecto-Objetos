import greenfoot.*; 

/**
 * TILES
 * ADREEZ
 */

public class Tile extends Actor {
    public Tile(int tileType) {
        String imageName = getImageNameForTileType(tileType);
        GreenfootImage image = new GreenfootImage(imageName);
        image.scale(50, 50);
        setImage(image);
    }
    
     private String getImageNameForTileType(int tileType) {
        switch (tileType) {
            case 0:
                return "grass.png";
            case 1:
                return "tree.png";
            case 2:
                return "water.png";
            case 3:
                return "sand.png";
            case 4:
                return "wall.png";
            case 5:
                return "earth.png";
            default:
                return null;
        }
    }
}
