package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_PowerInputActivated;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player extends Entity {
    KeyHandler keyH;
    public int hasOrb = 0;
    public int powerInputActivated = 0;
    public boolean dead = false;
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        Random random = new Random();

        int startingX = random.nextInt(21)* gp.tileSize;;
        int startingY = random.nextInt(12)* gp.tileSize;;
        screenX = startingX;
        screenY = startingY;
        damageHitBox = new Rectangle();
        damageHitBox.x = startingX;
        damageHitBox.y = startingY;
        damageHitBox.width = gp.tileSize;
        damageHitBox.height = gp.tileSize;

//        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
//        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDefaultvalues();
        getPlayerImage();
        hitBox = new Rectangle();
        hitBox.x = 12 * gp.scale;
        hitBox.y = 16 * gp.scale;

        hitBox.width = 8 * gp.scale;
        hitBox.height = 16 * gp.scale;
        solidAreaDefaultX = hitBox.x;
        solidAreaDefaultY = hitBox.y;
    }

    public void setDefaultvalues() {
        direction = "down";
    }

    public void getPlayerImage() {
        Random random = new Random();
        int playerSkin = random.nextInt(4);
        String playerFileName = "";
        switch (playerSkin){
            case 0: playerFileName = "player";
            break;
            case 1: playerFileName = "snailBobPlayer";
            break;
            case 2: playerFileName = "ratplayer";
            break;
            case 3: playerFileName = "buzzPlayer";
            break;
        }

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/" + playerFileName + "/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //If statement makes it so sprite only "animates" when moving
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            //Go back and check hitBox dimensions
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        screenY -= speed;
                        damageHitBox.y -= speed;
                        break;
                    case "down":
                        screenY += speed;
                        damageHitBox.y += speed;
                        break;
                    case "left":
                        screenX -= speed;
                        damageHitBox.x -= speed;
                        break;
                    case "right":
                        screenX += speed;
                        damageHitBox.x += speed;
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

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "PowerOrb":
                    //To play sound effect:
                    if(!gp.obj[i].collected){
                        gp.obj[i].collected = true;
                        gp.playSE(2);
                        hasOrb++;
                        gp.collectedOrbs.add(gp.obj[i]);

//                        gp.obj[i] = null;
                    }
                    break;
                case "PowerInput":
                    if (hasOrb >= 5) {
                        gp.playSE(1);
                        OBJ_PowerInputActivated pia = new OBJ_PowerInputActivated();
                        pia.worldX = gp.obj[i].worldX;
                        pia.worldY = gp.obj[i].worldY;
                        gp.obj[i] = pia;
//                        gp.obj[i] = null;
                        powerInputActivated ++;
                        hasOrb -= 5;
                    }
                    break;

                case "Boots":
                    speed += 1;
                    gp.obj[i] = null;
                    break;

            }
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
//        g2.draw(damageHitBox);
//        int hitBoxY = screenY + hitBox.y;
//        int hitBoxX = screenX + hitBox.x;
//        Rectangle hitBoxRect = new Rectangle(hitBoxX, hitBoxY, hitBox.width, hitBox.height);
//        g2.draw(hitBoxRect);
    }
}
