import greenfoot.*;

/**
 * 
 * Adreez
 * estoy harta de hacer videojuegos saquenme de aquí
 */

public class StatsWindow extends Actor {
    private Protagonic player;

    public StatsWindow(Protagonic player) {
        this.player = player;
        drawStats(player);
    }

    public void drawStats(Protagonic player) {
        GreenfootImage image = new GreenfootImage(100, 150);
        image.setColor(new Color(200, 200, 200, 180)); // Gris tenue con transparencia
        image.fill();
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);

        int x = 10;
        int y = 20;
        int lineHeight = 20;

        image.drawString("Speed: " + player.getSpeed(), x, y);
        y += lineHeight;
        image.drawString("Life: " + player.getLife(), x, y);
        y += lineHeight;
        image.drawString("Max Life: " + player.getMaxLife(), x, y);
        y += lineHeight;
        image.drawString("Level: " + player.getLevel(), x, y);
        y += lineHeight;
        image.drawString("Attack: " + player.getAttack(), x, y);
        y += lineHeight;
        image.drawString("Defense: " + player.getDefense(), x, y);
        y += lineHeight;
        image.drawString("Money: " + player.getMoney(), x, y);

        setImage(image);
    }
}
