import greenfoot.*;

/**
 * 
 * Adreez
 * 
 */
public class Chest extends SuperObject {
    private SuperObject reward;
    private boolean needKey;
    private boolean open;

    public Chest(SuperObject recompensa, boolean necesitaLlave) {
        this.reward = recompensa;
        this.needKey = necesitaLlave;
        this.open = false;
        GreenfootImage image = new GreenfootImage("/Objects/closed_chest.png");
        image.scale(40,40);
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
            setImage("open_chest.png");
            if (reward != null) {
                getWorld().addObject(reward, getX(), getY());
            }
        }
    }

    public SuperObject getReward() {
        return reward;
    }
}
