import greenfoot.*;

/**
 * SubVentanas
 * Adreez
 * 25/04/24
 */

public class InventoryWindow extends Actor {
    
    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;
    private static final int X_OFFSET = 50;
    private static final int Y_OFFSET = 50;
    
    private Protagonic player;
    
    public InventoryWindow(Protagonic player) {
        this.player = player;
        updateInventory();
    }
    
    private void updateInventory() {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(Color.GRAY);
        image.fillRect(0, 0, WIDTH, HEIGHT);
        int y = Y_OFFSET + 20;
        for (SuperObject item : player.getInventory()) {
            image.drawImage(item.getImage(), X_OFFSET, y);
            y += 50;
        }
        setImage(image);
    }
}


