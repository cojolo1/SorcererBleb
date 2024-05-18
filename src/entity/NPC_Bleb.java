package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Bleb extends Entity{
    public NPC_Bleb(GamePanel gp) {
        super(gp);
        direction = "down";
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void setAction() {
//        actionLockCounter++;
//        if (actionLockCounter == 120) {
//            Random random = new Random();
//            int i = random.nextInt(100) + 1;
//
//            if (i <= 25) {
//                direction = "up";
//            } else if (i > 25 && i <= 50) {
//                direction = "down";
//            } else if (i > 50 && i <= 75) {
//                direction = "left";
//            } else if (i > 75 && i <= 100) {
//                direction = "right";
//            }
//            actionLockCounter = 0;
//        }
//    }
}
