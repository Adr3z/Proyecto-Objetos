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
        return tileType == 16 || tileType == 18 || tileType == 19 || tileType == 20 || tileType == 21 || tileType == 22 || tileType == 23 || tileType == 24
            || tileType == 25 || tileType == 26 || tileType == 27 || tileType == 28 || tileType == 29 || tileType == 30 || tileType == 31
            || tileType == 32 || tileType == 33 || tileType == 34 || tileType == 35; 
    }
    
     private String getImageNameForTileType(int tileType) {
        switch (tileType) {
            case 0:
                return "/Tiles/000.png";
            case 1:
                return "/Tiles/001.png";
            case 2:
                return "/Tiles/002.png";
            case 3:
                return "/Tiles/003.png";
            case 4:
                return "/Tiles/004.png";
            case 5:
                return "/Tiles/005.png";
            case 6:
                return "/Tiles/006.png";
            case 7:
                return "/Tiles/007.png";
            case 8:
                return "/Tiles/008.png";
            case 9:
                return "/Tiles/009.png";
            case 10:
                return "/Tiles/010.png";
            case 11:
                return "/Tiles/011.png";
            case 12:
                return "/Tiles/012.png";
            case 13:
                return "/Tiles/013.png";
            case 14:
                return "/Tiles/014.png";
            case 15:
                return "/Tiles/015.png";
            case 16:
                return "/Tiles/016.jpeg";
            case 17:
                return "/Tiles/017.png";
            case 18:
                return "/Tiles/018.png";
            case 19:
                return "/Tiles/019.png";
            case 20:
                return "/Tiles/020.png";
            case 21:
                return "/Tiles/021.png";
            case 22:
                return "/Tiles/022.png";
            case 23:
                return "/Tiles/023.png";
            case 24:
                return "/Tiles/024.png";
            case 25:
                return "/Tiles/025.png";
            case 26:
                return "/Tiles/026.png";
            case 27:
                return "/Tiles/027.png";
            case 28:
                return "/Tiles/028.png";
            case 29:
                return "/Tiles/029.png";
            case 30:
                return "/Tiles/030.png";
            case 31:
                return "/Tiles/031.png";
            case 32:
                return "/Tiles/032.png";
            case 33:
                return "/Tiles/033.jpeg";
            case 34:
                return "/Tiles/034.png";
            case 35:
                return "/Tiles/035.png";
            case 36:
                return "/Tiles/036.png";
            case 37:
                return "/Tiles/037.png";
            default:
                return null;
        }
    }
}
