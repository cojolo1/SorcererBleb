package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            } else if (gp.gameState ==gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

        if(code == KeyEvent.VK_C){
            if(gp.gameState == gp.titleState){

                gp.gameState = gp.howToPlayState;

            } else if(gp.gameState == gp.howToPlayState){
                gp.gameState = gp.titleState;
            }
        }


        if(code == KeyEvent.VK_SPACE){

            if(gp.gameState == gp.titleState){
                gp.gameState = gp.playState;
            } else if(gp.gameState == gp.playerWinState || gp.gameState == gp.sniperWinState){
                gp.sniper.shotsFired = 0;
                gp.player.powerInputActivated = 0;
                gp.gameState = gp.resetState;
            }

//            if(gp.gameState == gp.playerWinState || gp.gameState == gp.sniperWinState){
//                gp.gameState = gp.resetState;
//                System.out.println(gp.gameState);
//            }
//            else if(gp.gameState == gp.playState){
//                gp.gameState = gp.titleState;
//            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
