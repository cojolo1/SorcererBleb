package main;

import entity.Entity;
import entity.Player;
import entity.Sniper;
import object.OBJ_DeathSprite;
import object.OBJ_PowerOrb;
import object.SuperObject;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 32; //16x16 tile, default size of characters and such
    public final int scale = 2;

    public final int tileSize = originalTileSize * scale; //48x48 tiles but for us 96x96
    public final int maxScreenCol = 22;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD SETTINGS
//    public final int maxWorldCol = 50;
//    public final int maxWorldRow = 50;

    //FPS
    int FPS = 60;
    String timerText;
    // SYSTEM
    public TileManager tileM = new TileManager(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Graphics2D g2;

    public UI ui = new UI(this);
    KeyHandler keyH = new KeyHandler(this);
    SniperKeyHandler sniperKeyH = new SniperKeyHandler();
    Thread gameThread;
    Sound se = new Sound();
    Sound music = new Sound();

    Image darkness;
    public boolean gotStartTime = false;
    // ENTITY AND OBJECT
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public Sniper sniper = new Sniper(this, sniperKeyH);

    public Entity npc[] = new Entity[40];
    public SuperObject obj[] = new SuperObject[35];
    public ArrayList<SuperObject> collectedOrbs = new ArrayList<>();
    public ArrayList<OBJ_DeathSprite> deathSprite = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int playerWinState = 3;
    public final int sniperWinState = 4;
    public final int resetState = 5;
    public final int howToPlayState = 6;

    long startTime;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addKeyListener(sniperKeyH);
        this.setFocusable(true);

    }

    public void setupGame() {
        aSetter.setNPC();
        aSetter.setObject();
        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (1000000000 / FPS) / 1.25;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(gameState == sniperWinState){
                repaint();
            }
            if (sniper.shotsFired >= 3 || player.powerInputActivated >= 3) {
                if(!player.dead){
                    gameState = playerWinState;
                }
            }

            if (gameState == resetState) {
                stopMusic();
                this.player = new Player(this, keyH);
                this.sniper = new Sniper(this, sniperKeyH);
                this.tileM = new TileManager(this);
                this.aSetter = new AssetSetter(this);
                npc = new Entity[40];
                obj = new SuperObject[35];
                collectedOrbs = new ArrayList<>();
                deathSprite = new ArrayList<>();
                gotStartTime = false;
                setupGame();
                startGameThread();
                break;
            }

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }

            if(gameState == playState){
                if(!gotStartTime){
                    startTime = System.nanoTime();
                    gotStartTime = true;
                }
                long elapsedTimeInSeconds = (System.nanoTime() - startTime) / 1000000000;
                long remainingTimeInSeconds = 240 - elapsedTimeInSeconds; // 5 minutes in seconds
                int minutes = (int) (remainingTimeInSeconds / 60);
                int seconds = (int) (remainingTimeInSeconds % 60);
                if(minutes == 0 && seconds == 0){
                    gameState = sniperWinState;
                }
                timerText = String.format("%02d:%02d", minutes, seconds);
            }


        }
    }

    public void update() {

        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            sniper.update();
        } else if (gameState == pauseState) {
            //nothing for now
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.g2 = (Graphics2D) g;

        if (gameState == titleState || gameState == howToPlayState) {
            tileM.draw(g2);
            ui.draw(g2 );
        } else {
            //Draw Tiles
            tileM.draw(g2);
            //Draw Death Sprites
            for (int i = 0; i < deathSprite.size(); i++) {
                deathSprite.get(i).update(g2, this);
            }
            //Draw Objects
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            //Draw NPCs
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }

            //Player
            if(gameState != sniperWinState){
                player.draw(g2);

            }

            //Sniper
            sniper.draw(g2);

            // UI
            ui.draw(g2);
            g2.dispose();

        }


    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
