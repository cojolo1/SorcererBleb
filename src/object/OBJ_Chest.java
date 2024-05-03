package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        name = "Test";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/testObject.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
