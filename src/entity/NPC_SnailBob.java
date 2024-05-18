package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_SnailBob extends Entity {
    public NPC_SnailBob(GamePanel gp) {
        super(gp);
        direction = "down";
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/snailBobPlayer/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
