package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PowerOrbCollected extends SuperObject{
    public OBJ_PowerOrbCollected(){
        name = "PowerOrbCollected";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/bigDarkBrick3.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
