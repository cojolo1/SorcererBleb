package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PowerInputActivated extends SuperObject{
    public OBJ_PowerInputActivated(){
            name = "PowerInputActivated";
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/objects/powerInput.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
            collision = true;
        }
}
