package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_Buzz extends Entity{
    public NPC_Buzz(GamePanel gp) {
        super(gp);
        direction = "down";
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/buzzPlayer/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
