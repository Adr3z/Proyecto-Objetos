import greenfoot.*;
import java.util.ArrayList;

public class InventoryWindow extends Actor {
    
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private static final int X_OFFSET = 50;
    private static final int Y_OFFSET = 50;
    private static final int ITEM_WIDTH = 50;
    private static final int ITEM_HEIGHT = 50;
    private static final int ITEMS_PER_ROW = 6;
    
    private Protagonic player;
    private ArrayList<SuperObject> items;
    private GreenfootImage image;
    private boolean itemClicked = false;
    private SuperObject selectedItem;
    private boolean showingItemOptions = false;
    
    public InventoryWindow(Protagonic player) {
        this.player = player;
        items = player.getInventory();
        updateInventory();
    }
    
    public void updateInventory() {
        GreenfootImage image = new GreenfootImage(350, 200);
        items = player.getInventory();
        image.setColor(new Color(200, 200, 200, 180)); 
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
        int x = 10;
        int y = 20;
        int itemsInRow = 0;
        for (SuperObject item : items) {
            image.drawImage(item.getImage(), x, y);
            x += ITEM_WIDTH + 10; // Espacio entre objetos
            itemsInRow++;
            if (itemsInRow >= ITEMS_PER_ROW) {
                itemsInRow = 0;
                x = 10;
                y += ITEM_HEIGHT + 40; // Espacio entre filas
            }
        }
        setImage(image);
    }
    
    public void act() {
    if (!showingItemOptions && !itemClicked && Greenfoot.mouseClicked(null)) {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            int clickedX = mouse.getX();
            int clickedY = mouse.getY();
            if (clickedX >= getX() - WIDTH / 2 && clickedX <= getX() + WIDTH / 2 &&
                clickedY >= getY() - HEIGHT / 2 && clickedY <= getY() + HEIGHT / 2) {
                
                int localX = clickedX - (getX() - WIDTH / 2);
                int localY = clickedY - (getY() - HEIGHT / 2);
                
                int col = localX / (ITEM_WIDTH + 10);
                int row = localY / (ITEM_HEIGHT + 40);
                
                int index = row * ITEMS_PER_ROW + col;
                
                if (index >= 0 && index < items.size()) {
                    itemClicked = true;
                    selectedItem = items.get(index);
                    useSelectedItem();
                }
            }
        }
    }
}

private void useSelectedItem() {
    if (selectedItem instanceof Obj_Key) {
        // No hacer nada si es una llave
    } else if (selectedItem instanceof PotionRed) {
        if (player.getLife() < player.getMaxLife()) {
            player.useItem(selectedItem);
            items.remove(selectedItem);
        } else {

        }
    } else if (selectedItem instanceof Boots || selectedItem instanceof ShieldWood || selectedItem instanceof ShieldBlue || selectedItem instanceof SwordNormal || selectedItem instanceof SwordDiamond || selectedItem instanceof SwordPurple) {
        player.equipItem(selectedItem);
        items.remove(selectedItem);
    } else {
        player.useItem(selectedItem);
        items.remove(selectedItem);
    }
    updateInventory();
    itemClicked = false; // Restablecer el estado de clic después de consumir el objeto
}

}
