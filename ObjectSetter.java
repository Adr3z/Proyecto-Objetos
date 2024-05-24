import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Adreez
 *
 */

public class ObjectSetter {
    private World world;
    private List<Chest> cofres;
    private List<Boolean> cofresAbiertos;
    
    public ObjectSetter(World world) {
        this.world = world;
        this.cofres = new ArrayList<>();
        this.cofresAbiertos = new ArrayList<>();
        initializeCofres();
    }
    
    private void initializeCofres() {
        // Crear y agregar los cofres al mundo
        cofres.add(new Chest(createRewardsForMapIndex(2, true), true)); // Cofre 1
        cofres.add(new Chest(createRewardsForMapIndex(3, true), true)); // Cofre 2
        cofres.add(new Chest(createRewardsForMapIndex(5, false), false)); // Cofre 3
        cofres.add(new Chest(createRewardsForMapIndex(5, true), true)); // Cofre que necesita llave
        cofres.add(new Chest(createRewardsForMapIndex(7, false), false)); // Cofre 5
        cofres.add(new Chest(createRewardsForMapIndex(8, false), false)); // Cofre 6
        cofres.add(new Chest(createRewardsForMapIndex(9, false), false)); // Cofre 7

        // Establecer que todos los cofres están cerrados por defecto
        for (int i = 0; i < cofres.size(); i++) {
            cofresAbiertos.add(false);
        }
    }
    
    private List<SuperObject> createRewardsForMapIndex(int mapIndex, boolean needsKey) {
        List<SuperObject> rewards = new ArrayList<>();
        // Agregamos las recompensas correspondientes al mapa
        if (mapIndex == 2 && needsKey) {
            rewards.add(new Obj_Key());
            rewards.add(new SwordPurple());
        } else if (mapIndex == 3) {
            rewards.add(new CoinGold());
            rewards.add(new CoinGold());
            rewards.add(new CoinGold());
            rewards.add(new CoinGold());
            rewards.add(new CoinGold());
        } else if (mapIndex == 5) {
            if (needsKey) {
                rewards.add(new PotionPurple());
                rewards.add(new CoinGold());
                rewards.add(new CoinGold());
                rewards.add(new PotionRed());
                rewards.add(new BlueHeart());
            } else {
                rewards.add(new ShieldWood());
                rewards.add(new CoinBronze());
                rewards.add(new CoinBronze());
            }
        } else if (mapIndex == 7) {
            rewards.add(new Obj_Key());
            rewards.add(new ShieldWood());
        } else if (mapIndex == 8) {
            rewards.add(new SwordNormal());
            rewards.add(new PotionRed());
        } else if (mapIndex == 9) {
            rewards.add(new Obj_Key());
            rewards.add(new Boots());
        }
        return rewards;
    }
    
    public void placeObjects(int mapIndex) {
        clearCofres();

        if (mapIndex == 2) {
            placeCofre(6, 3, 0); // Cofre 1
        } else if (mapIndex == 3) {
            placeCofre(1, 9, 1); // Cofre 2
        } else if (mapIndex == 5) {
            placeCofre(7, 9, 2); // Cofre 3
            placeCofre(10, 5, 3); // Cofre 4
        } else if (mapIndex == 7) {
            placeCofre(6, 3, 4); // Cofre 5
        } else if (mapIndex == 8) {
            placeCofre(11, 5, 5); // Cofre 6
        } else if (mapIndex == 9) {
            placeCofre(7, 2, 6); // Cofre 7
        }
    }
    
    private void placeCofre(int col, int row, int index) {
        int tileWidth = 50;
        int tileHeight = 50;
        int worldX = col * tileWidth;
        int worldY = row * tileHeight;

        Chest c = cofres.get(index);
        boolean isOpen = cofresAbiertos.get(index);
        if (isOpen) {
            c.setOpen(true); 
        }
        world.addObject(c, worldX + 20, worldY + 10);
    }

    public void clearCofres() {
        for (Chest cofre : cofres) {
            world.removeObject(cofre);
        }
    }
    
    public List<Chest> getCofres() {
        return cofres;
    }
    
    public void setCofreAbierto(int indice, boolean abierto) {
        if (indice >= 0 && indice < cofresAbiertos.size()) {
            cofresAbiertos.set(indice, abierto);
        }
    }
}
