package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity {

    //    public int worldX,worldY;
    public int screenX, screenY;
    //    public int entityX, entityY;
    public int speed = 1;
    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    boolean still = false;

    public Rectangle hitBox = new Rectangle(12, 16, 9, 16);
    public Rectangle damageHitBox;

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public int actionLockLimit = 0;

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == actionLockLimit) {
            still = false;
            Random random = new Random();
            int i = random.nextInt(110) + 1;
            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            } else if (i > 100 && i <= 110) {
                still = true;
            }
            actionLockCounter = 0;
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if (!still) {
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        screenY -= speed;
                        break;
                    case "down":
                        screenY += speed;
                        break;
                    case "left":
                        screenX -= speed;
                        break;
                    case "right":
                        screenX += speed;
                        break;
                }
            }

        }

        spriteCounter++;
        if (spriteCounter > 17) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//        int hitBoxY = screenY + hitBox.y;
//        int hitBoxX = screenX + hitBox.x;
//        Rectangle hitBoxRect = new Rectangle(hitBoxX, hitBoxY, hitBox.width, hitBox.height);
//        g2.draw(hitBoxRect);

        int hitBoxY = screenY;
        int hitBoxX = screenX;
        damageHitBox = new Rectangle(hitBoxX, hitBoxY, gp.tileSize, gp.tileSize);
//        g2.draw(damageHitBox);
    }

    public Entity(GamePanel gp) {
        this.gp = gp;

    }
}
