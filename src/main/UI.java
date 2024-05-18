package main;

import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

public class UI {

    GamePanel gp;
    Font maruMonica;
    Graphics2D g2;
    BufferedImage image;
    BufferedImage bulletImage;
    int UISpriteCounter = 0;
    int UISpriteNum = 1;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/powerOrbAlone.png"));
            this.bulletImage = ImageIO.read(getClass().getResourceAsStream("/objects/bullets.png"));
        } catch (Exception e) {
            System.out.println("Error importing orb alone");
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        this.g2.setFont(maruMonica);
        this.g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        String text = "";

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.howToPlayState) {
            drawHowToPlayScreen();
        }

        if (gp.gameState == gp.playState) {
            // Do playState stuff later
            text = "x";
            this.g2.drawImage(image, gp.tileSize / 2, 12 * gp.tileSize + 10, gp.tileSize - 20, gp.tileSize - 20, null);
            this.g2.drawString(text + gp.player.hasOrb, gp.tileSize + 22, 12 * gp.tileSize + 45);

            this.g2.drawImage(bulletImage, gp.tileSize / 2 + gp.tileSize * 4, 12 * gp.tileSize + 10, gp.tileSize - 20, gp.tileSize - 20, null);
            this.g2.drawString(text + (3 - gp.sniper.shotsFired), gp.tileSize + 22 + gp.tileSize * 4, 12 * gp.tileSize + 45);

            this.g2.drawString(gp.timerText, getXforCenteredText(gp.timerText), 12 * gp.tileSize + 45);

        } else if (gp.gameState == gp.pauseState) {
            text = "x";
            this.g2.drawImage(image, gp.tileSize / 2, 12 * gp.tileSize + 10, gp.tileSize - 20, gp.tileSize - 20, null);
            this.g2.drawString(text + gp.player.hasOrb, gp.tileSize + 22, 12 * gp.tileSize + 45);

            this.g2.drawImage(bulletImage, gp.tileSize / 2 + gp.tileSize * 4, 12 * gp.tileSize + 10, gp.tileSize - 20, gp.tileSize - 20, null);
            this.g2.drawString(text + (3 - gp.sniper.shotsFired), gp.tileSize + 22 + gp.tileSize * 4, 12 * gp.tileSize + 45);
            drawPauseScreen();
        } else if (gp.gameState == gp.sniperWinState) {
            text = "PLAYER2 WINS";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            int x = getXforCenteredText(text);
            int y = gp.screenHeight / 2;
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 5, y + 5);

            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            text = "Press Space To Restart";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 5, y + 5);

            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        } else if (gp.gameState == gp.playerWinState) {
            text = "PLAYER2 WINS";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            int x = getXforCenteredText(text);
            int y = gp.screenHeight / 2;
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 5, y + 5);

            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            text = "Press Space To Restart";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.setColor(Color.darkGray);
            g2.drawString(text, x + 5, y + 5);

            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

    }

    public void drawHowToPlayScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "How To Play";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 2;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        text = "Player1 Moves Character With \"W\", \"A\", \"S\", and \"D\".";
        x = getXforCenteredText(text);
        y = gp.tileSize * 3;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Player2 Moves Sniper Reticule With The \"Up\", \"Down\", \"Left\", and \"Right\" Arrow Keys.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Player2 Shoots With \"/\".";
        x = getXforCenteredText(text);
        y = gp.tileSize * 5;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Player1's Goal: Activate All 3 Alchemy Locks.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 6;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Player2's Goal: Find And Shoot Player1. Beware, You Only Have 3 Bullets.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 7;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Player1 Should Blend In By Moving Like The NPC's.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 8;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);



        text = "To Activate An Alchemy Lock:              , Collect 5 Orbs, Then Walk Over The Alchemy Lock.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 9;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        try {
            Image powerInputImage = ImageIO.read(getClass().getResourceAsStream("/objects/powerInput.png"));
            g2.drawImage(powerInputImage, x + gp.tileSize*4 + 35, y - gp.tileSize / 2 - 10, gp.tileSize, gp.tileSize, null);
        } catch (Exception e) {

        }


        text = "Player2 Can Only See Which Orbs Were Picked Up When They Hover Over Them.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 10;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "If The Timer Reaches 0, Player2 Wins.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 11;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "If Player2 Uses All 3 Bullets Without Killing Player1, Player1 Wins.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 12;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Press \"C\" To Return To The Main Menu.";
        x = getXforCenteredText(text);
        y = gp.tileSize * 13 - 30;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.red);
        g2.drawString(text, x, y);

    }

    public void drawTitleScreen() {
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Sorcerer Bleb!";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //SHADOW COLOR
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        try {
            Image titleImage1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            Image titleImage2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
//             titleImage = ImageIO.read(getClass().getResourceAsStream("/objects/bullets.png"));
            //CHAR IMAGE
            x = gp.screenWidth / 2 - (gp.tileSize * 6) / 2;
            y += gp.tileSize * 2 - 10;

            if (UISpriteNum == 1) {
                g2.drawImage(titleImage1, x, y, gp.tileSize * 6, gp.tileSize * 6, null);
            } else {
                g2.drawImage(titleImage2, x, y, gp.tileSize * 6, gp.tileSize * 6, null);
            }

        } catch (Exception e) {
            System.out.println("Error importing orb alone");
        }

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Press Space To Begin";
        x = getXforCenteredText(text);
        y -= gp.tileSize - 10;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        text = "Press \"C\" For Controls/Instructions";
        x = getXforCenteredText(text);
        y += gp.tileSize * 8 - 15;
        g2.setColor(Color.darkGray);
        g2.drawString(text, x + 5, y + 5);

        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        UISpriteCounter++;
        if (UISpriteCounter > 24) {
            if (UISpriteNum == 1) {
                UISpriteNum = 2;
            } else if (UISpriteNum == 2) {
                UISpriteNum = 1;
            }
            UISpriteCounter = 0;
        }

    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
