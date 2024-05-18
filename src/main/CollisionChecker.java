package main;

import entity.Entity;
import entity.Player;
import entity.Sniper;
import object.OBJ_DeathSprite;
import object.OBJ_PowerInputActivated;

import java.awt.*;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkHit(Entity[] entities, Rectangle reticuleRect) {
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                if (entities[i].damageHitBox.intersects(reticuleRect)) {
                    OBJ_DeathSprite deathSprite = new OBJ_DeathSprite(entities[i].screenX, entities[i].screenY);
                    gp.deathSprite.add(deathSprite);
                    System.out.println("HIT!");
                    entities[i] = null;
                }
            }
        }
    }

    public void chechHitPlayer(Player player, Rectangle reticuleRect) {
        if (player.damageHitBox.intersects(reticuleRect)) {
            OBJ_DeathSprite deathSprite = new OBJ_DeathSprite(player.screenX, player.screenY);
            gp.deathSprite.add(deathSprite);
            player = null;
            System.out.println("HIT PLAYER");
            gp.player.dead = true;
            gp.gameState = gp.sniperWinState;
//            System.exit(0);
        }
    }


    public void checkTile(Entity entity) {
        int entityTopWorldY = entity.screenY + entity.hitBox.y;
        int entityLeftWorldX = entity.screenX + entity.hitBox.x;
        int entityRightWorldX = entity.screenX + entity.hitBox.x + entity.hitBox.width;
        int entityBottomWorldY = entity.screenY + entity.hitBox.y + entity.hitBox.height;
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                if (entityTopWorldY <= 0) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                if (entityBottomRow >= gp.maxScreenRow) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }

                break;
            case "left":
                if (entityLeftWorldX <= 0) {
                    entity.collisionOn = true;
                } else {
                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }

                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                if (entityRightCol >= gp.maxScreenCol) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }

                }
                break;
        }
    }

    public void checkCollectedOrb(Sniper sniper) {
        int index = 999;
        for (int i = 0; i < gp.collectedOrbs.size(); i++) {
            if (gp.collectedOrbs.get(i) != null) {
                Rectangle somethingIdk = new Rectangle(gp.collectedOrbs.get(i).worldX, gp.collectedOrbs.get(i).worldY, gp.tileSize, gp.tileSize);

                if (sniper.reticuleRect.intersects(somethingIdk)) {
                    gp.collectedOrbs.get(i).switchImageOrb();
                }
            }

        }
    }

    public void checkActivatedPowerInput(Sniper sniper){
        int index = 999;
        for(int i = 30; i < 33; i++){
            if(gp.obj[i] instanceof OBJ_PowerInputActivated){
                Rectangle powerInputHitBox = new Rectangle(gp.obj[i].worldX, gp.obj[i].worldY, gp.tileSize, gp.tileSize);
                if(sniper.reticuleRect.intersects(powerInputHitBox)){
                    gp.obj[i].switchPowerInputImage();
                }
            }

        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //Get entity's solid area position
                entity.hitBox.x = entity.screenX + entity.hitBox.x;
                entity.hitBox.y = entity.screenY + entity.hitBox.y;

                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
//                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
//                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
//                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
//                                entity.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.hitBox.x = entity.solidAreaDefaultX;
                entity.hitBox.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
