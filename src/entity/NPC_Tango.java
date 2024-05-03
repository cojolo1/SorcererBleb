package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Tango extends Entity {
    public NPC_Tango(GamePanel gp) {
        super(gp);
        direction = "down";
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/ratPlayer2/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
