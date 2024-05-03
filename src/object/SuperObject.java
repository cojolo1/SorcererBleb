package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean collected = false;

    public void draw(Graphics2D g2, GamePanel gp) {

//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
//            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//
//        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);

//        g2.draw(solidArea);
    }

    public void switchImageOrb() {
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/bigDarkBrick3.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
