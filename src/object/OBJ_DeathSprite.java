package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_DeathSprite extends SuperObject{
    int x;
    int y;
    public OBJ_DeathSprite(int x, int y){
        name = "DeathSprite";
        this.x = x;
        this.y = y;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bloodSplatter.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    public void update(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
