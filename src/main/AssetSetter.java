package main;


import entity.NPC_Bleb;
import entity.NPC_Buzz;
import entity.NPC_SnailBob;
import entity.NPC_Tango;
import object.OBJ_Boots;
import object.OBJ_Key;
import object.OBJ_PowerInput;
import object.OBJ_PowerOrb;

import java.util.Random;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        Random random = new Random();
        for (int i = 0; i < 30; i++){
            gp.obj[i] = new OBJ_PowerOrb();
            gp.obj[i].worldX = random.nextInt(21) * gp.tileSize;
            gp.obj[i].worldY = random.nextInt(12)* gp.tileSize;
            gp.obj[i].solidArea.x = gp.obj[i].worldX;
            gp.obj[i].solidArea.y = gp.obj[i].worldY;
            gp.obj[i].collected = false;
        }
        gp.obj[30] = new OBJ_PowerInput();
        gp.obj[30].worldX = 5 * gp.tileSize;
        gp.obj[30].worldY = 10 * gp.tileSize;

        gp.obj[31] = new OBJ_PowerInput();
        gp.obj[31].worldX = 17 * gp.tileSize;
        gp.obj[31].worldY = 10 * gp.tileSize;

        gp.obj[32] = new OBJ_PowerInput();
        gp.obj[32].worldX = 11 * gp.tileSize;
        gp.obj[32].worldY = 2 * gp.tileSize;
    }

    public void setNPC(){
        Random random = new Random();
        for(int i = 0; i < 40; i++){
            int npcChoice = random.nextInt(4);
            if(npcChoice == 0){
//                gp.npc[i] = new NPC_Bleb(gp);
                gp.npc[i] = new NPC_Tango(gp);
            } else if (npcChoice == 1){
                gp.npc[i] = new NPC_Bleb(gp);
            } else if (npcChoice == 2){
                gp.npc[i] = new NPC_SnailBob(gp);
            } else if(npcChoice == 3){
                gp.npc[i] = new NPC_Buzz(gp);
            }
            int x = random.nextInt(21);
            int y = random.nextInt(12);
            gp.npc[i].actionLockLimit = random.nextInt(50, 200);
            int intDirection = random.nextInt(4);
            switch (intDirection){
                case 0: gp.npc[i].direction = "up";
                break;
                case 1: gp.npc[i].direction = "down";
                break;
                case 2: gp.npc[i].direction = "left";
                break;
                case 3: gp.npc[i].direction = "right";
                break;
            }
            gp.npc[i].screenX = gp.tileSize * x;
            gp.npc[i].screenY = gp.tileSize * y;
            gp.npc[i].hitBox.x = 12 * gp.scale;
            gp.npc[i].hitBox.y = 16 * gp.scale;
            gp.npc[i].hitBox.width = 8 * gp.scale;
            gp.npc[i].hitBox.height = 16 * gp.scale;
        }

    }
}
