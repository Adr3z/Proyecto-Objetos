import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Adreez
 * 
 */
public class Chest extends SuperObject {
    private List<SuperObject> rewards;
    private boolean needKey;
    private boolean open;

    public Chest(List<SuperObject> recompensas, boolean necesitaLlave) {
        this.rewards = new ArrayList<>(recompensas);
        this.needKey = necesitaLlave;
        this.open = false;
        GreenfootImage image = new GreenfootImage("/Objects/closed_chest.png");
        image.scale(40, 40);
        setImage(image);
    }

    public boolean needKey() {
        return needKey;
    }

    public boolean isOpen() {
        return open;
    }

    public void Opening() {
        if (!open) {
            open = true;
            GreenfootImage image = new GreenfootImage("/Objects/open_chest.png");
            image.scale(40, 40);
            setImage(image);
            if (rewards != null) {
                for (SuperObject reward : rewards) {
                    getWorld().addObject(reward, getX(), getY());
                }
            }
        }
    }

    public List<SuperObject> getRewards() {
        return rewards;
    }
    
    public void setOpen(boolean isOpen) {
        this.open = true;
        GreenfootImage image = new GreenfootImage("/Objects/open_chest.png");
        image.scale(40, 40);
        setImage(image);
    }

}
