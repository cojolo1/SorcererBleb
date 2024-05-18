package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PowerOrb extends SuperObject{

    public OBJ_PowerOrb(){
        name = "PowerOrb";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/powerOrb.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    public void switchImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/bigDarkBrick3.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
