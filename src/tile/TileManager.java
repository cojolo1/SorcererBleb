package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/mapData.txt");
    }

    public void getTileImage(){
        try{
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bigDarkBrick3.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/darkWater.png"));
            tile[1].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/darkWaterWithRocks.png"));
            tile[5].collision =true;

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirtyDirt.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/darkTree.png"));
            tile[3].collision =true;

//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/objects/powerInput.png"));
//
//            tile[6] = new Tile();
//            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/objects/powerInputActivated.png"));


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath){
        try{
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

//                System.out.println("IS");

                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }


            }
            br.close();
        } catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
//        int worldCol = 0;
//        int worldRow = 0;
//        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
//            int tileNum = mapTileNum[worldCol][worldRow];
//            int worldX = worldCol * gp.tileSize;
//            int worldY = worldRow * gp.tileSize;
//            int screenX = worldX - gp.player.worldX + gp.player.screenX;
//            int screenY = worldY - gp.player.worldY + gp.player.screenY;
//            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
//                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){
//                            g2.drawImage(tile[tileNum].image, screenX,screenY,gp.tileSize, gp.tileSize, null);
//            }
//            worldCol++;
//            if(worldCol == gp.maxWorldCol){
//                worldCol = 0;
//                worldRow++;
//            }
//        }

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x , y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
