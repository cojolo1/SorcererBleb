package entity;

import main.GamePanel;
import main.SniperKeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Sniper extends Entity {
    SniperKeyHandler sniperKeyHandler;
    public int reticuleX;
    public int reticuleY;
    public Rectangle reticuleRect;
    public int shotsFired = 0;
    public boolean shooting = false;

    public Sniper(GamePanel gp, SniperKeyHandler sniperKeyHandler) {
        super(gp);
        this.sniperKeyHandler = sniperKeyHandler;
        Random random = new Random();
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        reticuleX = screenX + gp.tileSize;
        reticuleY = screenY + gp.tileSize;
        setDefaultvalues();
        getPlayerImage();
        hitBox = new Rectangle();
        reticuleRect = new Rectangle();
        reticuleRect.width = 10;
        reticuleRect.height = 10;
        hitBox.x = 12 * gp.scale;
        hitBox.y = 16 * gp.scale;
        solidAreaDefaultX = hitBox.x;
        solidAreaDefaultY = hitBox.y;
        hitBox.width = 8 * gp.scale;
        hitBox.height = 16 * gp.scale;
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/sniperAssets/sniperReticule1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefaultvalues() {
        speed = 3;
        direction = "up";
    }
    public void update() {
        gp.cChecker.checkCollectedOrb(this);
        gp.cChecker.checkActivatedPowerInput(this);
        //If statement makes it so sprite only "animates" when moving
        if(!sniperKeyHandler.forwardSlashPressed){
            shooting = false;
        }
        if(sniperKeyHandler.forwardSlashPressed && !shooting){
            shooting = true;
            gp.playSE(3);
            shotsFired++;
            System.out.println("Checking to see if hit");
            gp.cChecker.checkHit(gp.npc, reticuleRect);
            gp.cChecker.chechHitPlayer(gp.player, reticuleRect);
        }
        if (sniperKeyHandler.upPressed || sniperKeyHandler.downPressed || sniperKeyHandler.leftPressed || sniperKeyHandler.rightPressed) {
            //Check tile collision
            //CHECK OBJECT COLLISION
            if (sniperKeyHandler.upPressed == true) {
                direction = "up";
            } else if (sniperKeyHandler.downPressed == true) {
                direction = "down";
            } else if (sniperKeyHandler.leftPressed == true) {
                direction = "left";
            } else if (sniperKeyHandler.rightPressed == true) {
                direction = "right";
            }

            switch (direction) {
                case "up":
                    screenY -= speed;
                    reticuleY -= speed;
                    break;
                case "down":
                    screenY += speed;
                    reticuleY += speed;
                    break;
                case "left":
                    screenX -= speed;
                    reticuleX -= speed;
                    break;
                case "right":
                    screenX += speed;
                    reticuleX += speed;
                    break;
            }
        }
        reticuleRect.x = reticuleX;
        reticuleRect.y = reticuleY;
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(up1, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
//        Rectangle reticule = new Rectangle(reticuleX, reticuleY, 5, 5);
//        g2.draw(reticuleRect);
//        int hitBoxY = screenY + hitBox.y;
//        int hitBoxX = screenX + hitBox.x;
//        Rectangle hitBoxRect = new Rectangle(hitBoxX, hitBoxY, hitBox.width, hitBox.height);
//        g2.draw(hitBoxRect);
    }
}
